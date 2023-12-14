package kr.ac.usu.classroom.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.classroom.mapper.ProfessorClassroomMapper;
import kr.ac.usu.classroom.service.ProfessorClassroomService;
import kr.ac.usu.classroom.vo.ClassroomBoardVO;
import kr.ac.usu.classroom.vo.TestAnswerVO;
import kr.ac.usu.classroom.vo.TestQuestionVO;
import kr.ac.usu.classroom.vo.TestVO;
import kr.ac.usu.common.utils.ExcelRead;
import kr.ac.usu.common.utils.ExcelReadOption;
import kr.ac.usu.lecture.vo.LectureVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author PC-21
 *
 * @author 김재성
 * @since 2023. 11. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 	수정일            수정자            수정내용
 * ----------     --------    ----------------------
 * 2023.11.27.      김재성       최초작성
 * 2023.11.28.      김석호       단순화
 * 2023.11.29.      김재성       자료 업로드 및 시험출제 목록 
 * 2023.11.30.      김재성       과제 등록목록 리스트
 * 2023.12.01.      김재성       공지사항 목록 리스트
 * 2023.12.02.      김재성       과제제출 학생 목록 리스트
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Service
public class ProfessorClassroomServiceImpl implements ProfessorClassroomService   {
	
	@Inject
	private ProfessorClassroomMapper mapper;
	
	@Value("#{appInfo.excelFolderForMac}")
	private Resource excelFolderResourceForMac;
	
	@Value("#{appInfo.excelFolderForTest}")
	private Resource excelFolderResourceForWindow;
	
	@Value("#{appInfo.saveFolederForApplicationKJS}")
	private Resource saveFolderResource;
	
	private File excelFolder;
	
	private File saveFolder;
	
	@PostConstruct
	public void setExcelFolder() throws IOException{
		String osName = System.getProperty("os.name").toLowerCase();
		if(osName.contains("win")) {
			excelFolder = excelFolderResourceForWindow.getFile();
			saveFolder = saveFolderResource.getFile();
		}
		if(osName.contains("mac")) {
			excelFolder = excelFolderResourceForMac.getFile();
			saveFolder = excelFolder;
		}
		if(!excelFolder.exists()) {
			excelFolder.mkdirs();
		}
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		log.info("생성된 폴더 : {}",excelFolder);
	}
	
	// 클래스룸 강의 선택 리스트
	@Override
	public List<LectureVO> retrieveClassroomProfessorLecture(String prfsorNo) {
		List<LectureVO> lectureList = mapper.selectClassroomProfessorLecture(prfsorNo);
		
		
		return lectureList;
	}
	
	@Override
	public void createClassRoomTest(MultipartFile testFile, MultipartFile answerFile, TestQuestionVO test) throws Exception {
		// test 테이블 데이터 삽입
		TestVO testVO = new TestVO();
		testVO.setTestSe(test.getTestSe());
		testVO.setLctreNo(test.getLctreNo());
		mapper.insertTest(testVO);
		
		// 문제 파일 삽입
		test.setMultipartFile(testFile);
		AttatchingFileVO atch = test.getAtchFile();
		log.info("어탯치파일 정보 : {}",atch);
		atch.setAtchFileStrePath(saveFolder.getCanonicalPath());
		mapper.insertTestFile(atch);
		
		// 문제 테이블 데이터 삽입
		test.setAtchFileNo(atch.getAtchFileNo());
		mapper.insertTestQuestion(test);
		
		// 문제 정답 테이블 데이터 삽입
		List<TestAnswerVO> answerList = excelUploadTestAnswer(answerFile, test);
		mapper.insertClassroomTestQuest(answerList);
		
		// 파일 저장
		atch.saveTo(saveFolder);
	}

	// 시험 답안 업로드 
	private List<TestAnswerVO> excelUploadTestAnswer(MultipartFile answerFile, TestQuestionVO test) throws Exception {
		File destFile = new File(excelFolder,answerFile.getOriginalFilename());
		
		try {
			answerFile.transferTo(destFile);
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		
		ExcelReadOption excelreadOption = new ExcelReadOption();
		
		excelreadOption.setFilePath(destFile.getAbsolutePath());
		excelreadOption.setOutputColumns("A","B");
		excelreadOption.setStartRow(2);
		
		List<Map<String,String>> excelContent = ExcelRead.read(excelreadOption);
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("excelContent", excelContent);
		
		log.info("paramMap 받아온값 확인 : {}",paramMap);
		List<TestAnswerVO> list = new ArrayList<>();
		for(Map<String,String> answer : excelContent) {
			TestAnswerVO answerVO = new TestAnswerVO();
			answerVO.setLctreNo(test.getLctreNo());
			answerVO.setTestSe(test.getTestSe());
			answerVO.setTestQuesNo(answer.get("A"));
			answerVO.setTestAswper(answer.get("B"));
//			log.info("문항과 답안 출력 유무 체크 :A :{}", answer.get("A"));
//			log.info("문항과 답안 출력 유무 체크 :B :{}", answer.get("B"));
			list.add(answerVO);
		}
		destFile.delete();
		log.info("완성된 답변 리스트 : {}" ,list);
		return list;
	}
	
	// 클래스룸 과제 및 자료 업로드
	@Override
	public void createClassRoomBoard(MultipartFile assignmentFile, ClassroomBoardVO classroomBoard)
			throws Exception {
		
		//classBoard 테이블 데이터 삽입
		ClassroomBoardVO classBoardVO = new ClassroomBoardVO();
		classBoardVO.setCrSe(classroomBoard.getCrSe());
		classBoardVO.setCrTitle(classroomBoard.getCrTitle());
		classBoardVO.setCrCn(classroomBoard.getCrCn());
		classBoardVO.setCrWrter(classroomBoard.getCrWrter());
		classBoardVO.setLctreNo(classroomBoard.getLctreNo());
		
		classBoardVO.setMultipartFile(assignmentFile);
		
		AttatchingFileVO atch = classBoardVO.getAtchFile();
		log.info("어탯치파일 정보 : {}",atch);
		atch.setAtchFileStrePath(saveFolder.getCanonicalPath());
		mapper.insertClassRoomFile(atch);
		
		// 문제 테이블 데이터 삽입
		classBoardVO.setAtchFileNo(atch.getAtchFileNo());
		mapper.insertClassRoomBoard(classBoardVO);
		
		// 파일 저장
		atch.saveTo(saveFolder);
	}
	
	
	// 시험출제 목록 조회
	@Override
	public List<TestQuestionVO> retrieveClassRoomTestList(TestQuestionVO testQuestionVO) {
		
		List<TestQuestionVO> testList = mapper.selectClassRoomTestList(testQuestionVO);
		
		return testList;
		
		
	}
	// 과제등록 목록 조회
	@Override
	public List<ClassroomBoardVO> retrieveClassRoomAssignmentList(ClassroomBoardVO classBoardVO) {
		
		List<ClassroomBoardVO> assignmentList = mapper.selectClassRoomAssignmentList(classBoardVO);
		
		return assignmentList;
	}
	
	// 시험출제 목록 한개의 상세조회
	@Override
	public TestQuestionVO retrieveClassRoomTestView(TestQuestionVO testQuestionVO) {
		
		TestQuestionVO classBoard = mapper.selectClassRoomTestView(testQuestionVO);
		
		return classBoard;
	}
	
	
	
	// 시험 응시 학생 목록
	@Override
	public List<TestQuestionVO> retrieveTestStareStudentList(TestQuestionVO testQuestionVO) {
		List<TestQuestionVO> studentList = mapper.selectTestStareStudentList(testQuestionVO);
		
		return studentList;
	}
	
	// 클래스룸 다운로드용
	@Override
	public AttatchingFileVO retrieveAttatchingFile(String atchFileNo) {
		
		AttatchingFileVO atch = mapper.selectAttatchingFile(atchFileNo);
		
		if(atch ==null) {
			throw new NotFoundException(String.format("%d 해당하는 시험출제 자료가 없음",atch));
		}
		try {
			atch.saveTo(saveFolderResource.getFile());
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
					
		return atch;
	}
	
	// 자료등록 목록 조회
	@Override
	public List<ClassroomBoardVO> retrieveClassRoomDataList(ClassroomBoardVO classBoardVO) {
		
		List<ClassroomBoardVO> dataList = mapper.selectClassRoomDataList(classBoardVO);
		
		return dataList;
	}
	// 공지사항 등록 목록 조회
	@Override
	public List<ClassroomBoardVO> retrieveClassRoomNoticeList(ClassroomBoardVO classBoardVO) {
		
		List<ClassroomBoardVO> noticeList = mapper.selectClassRoomNoticeList(classBoardVO);
		
		return noticeList;
	}
	
	// 과제 상세정보 View And 과제제출 학생목록
	@Override
	public ClassroomBoardVO retrieveClassRoomAssignmentView(String crNo) {
		
		ClassroomBoardVO assignment = mapper.selectClassRoomAssignmentView(crNo);
		
		return assignment;
	}
	
	// 자료 or 공지사항 상세정보 View 
	@Override
	public ClassroomBoardVO retrieveClassRoomDataOrNoticeView(String crNo) {
		
		ClassroomBoardVO viewInfo = mapper.selectClassRoomDataOrNoticeView(crNo);
		
		return viewInfo;
	}

	@Override
	public List<ClassroomBoardVO> retrieveClassRoomAssignmentSubmitStudentList(String crNo) {
		
		List<ClassroomBoardVO> assignmetSubmitStudentList =mapper.selectClassRoomAssignmentSubmitStudentList(crNo);
		
		return assignmetSubmitStudentList;
	}


}
