package kr.ac.usu.classroom.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.remoting.RemoteTimeoutException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.classroom.service.ProfessorClassroomService;
import kr.ac.usu.classroom.vo.ClassroomBoardVO;
import kr.ac.usu.classroom.vo.TestQuestionVO;
import kr.ac.usu.common.mapper.CommonOptionsMapper;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.user.vo.ComCodeVO;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author PC-21
 *
 * @author 김재성
 * @since 2023. 11. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 *    수정일           수정자             수정내용
 * -----------     --------    ----------------------
 * 2023.11.26.       김재성       최초작성
 * 2023.11.27.       김재성       URL 맵핑 및 강의목록 출력
 * 2023.11.28.       김재성       엑셀 업로드
 * 2023.11.29.       김재성       자료파일 내용 업로드
 * 2023.11.30.       김재성       과제 등록 목록 리스트 조회
 * 2023.12.01.       김재성       과제 , 자료, 공지사항 상세내역
 * 2023.12.02.       김재성       과제제출 학생목록 
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/professor/classroom")
public class ProfessorClassroomController {
	
	@Inject
	private ProfessorClassroomService service;
	
	@Inject
	private CommonOptionsMapper commonMapper;
	
	@Value("#{appInfo.saveFolederForApplication}")
	private Resource saveFolderResource;
	
	//클래스룸 베이스 UI
	@GetMapping("/classroomBaseUI")
	public String classroomBaseUI(
			Model model
			,Authentication principal
	) {
		String prfsorNo = principal.getName();
		List<LectureVO> lectureList =service.retrieveClassroomProfessorLecture(prfsorNo);
		
		log.info("클래스룸 메뉴 클릭시 가져오는 강의관련 데이터 : {}",lectureList);
		
		model.addAttribute("lectureList",lectureList);
		
		return "professor/classroom/professorClassroomBaseUI";
	}
	
	// 클래스룸 페이지 시험 Tab View
	@GetMapping("/test/{lctreNo}")
	public String classroomTestTabUI(
			Model model
			,@PathVariable String lctreNo
	) {
		
		model.addAttribute("lctreNo", lctreNo);
		
		return "ajax/professor/classroom/professorClassroomTestView";
	}
	
	// 클래스룸 페이지 시험 Tab add View 
	@GetMapping("/addTest/{lctreNo}")
	public String classroomAddTestTabUI(
			Model model
			,@PathVariable String lctreNo
	) {
		List<ComCodeVO> testSe = commonMapper.getComeCodeList("SEC021");
		
		model.addAttribute("testSe",testSe);
		model.addAttribute("lctreNo", lctreNo);
		return "ajax/professor/classroom/professorClassroomAddTestView";
	}
	
	// 클래스룸 페이지 과제 Tab View 
	@GetMapping("/assignment/{lctreNo}")
	public String classroomAssignmentTabUI(
			Model model
			,@PathVariable String lctreNo
	) {
		List<ComCodeVO> crSe = commonMapper.getComeCodeList("SEC020");
		
		log.info("crSe 클래스룸 구분 코드 오는지 확인 : {}",crSe);
		
		model.addAttribute("crSe",crSe);
		model.addAttribute("lctreNo",lctreNo);
		
		return "ajax/professor/classroom/professorClassroomAssignmentView";
	}
	
	//클래스룸 페이지 자료 Tab View
	@GetMapping("/data/{lctreNo}")
	public String classroomDataTabUI(
			Model model
			,@PathVariable String lctreNo
	
	) {
		List<ComCodeVO> crSe = commonMapper.getComeCodeList("SEC020");
		
		log.info("crSe 클래스룸 구분 코드 오는지 확인 : {}",crSe);
		
		model.addAttribute("crSe",crSe);
		model.addAttribute("lctreNo",lctreNo);
		
		return "ajax/professor/classroom/professorClassroomDataView";
	}
	
	//클래스룸 공지사항 Tab View
	@GetMapping("/notice/{lctreNo}")
	public String classroomNoticeTabUI(
			Model model
			,@PathVariable String lctreNo			
	) {
		List<ComCodeVO> crSe = commonMapper.getComeCodeList("SEC020");
		
		model.addAttribute("crSe",crSe);
		model.addAttribute("lctreNo",lctreNo);
		
		return "ajax/professor/classroom/professorClassroomNoticeView";
	}
	
	//클래스룸 온라인강의 Tab View
	@GetMapping("/onlineLecture/{lctreNo}")
	public String classroomLectureOnlineTabUI(
			Model model
			,@PathVariable String lctreNo			
			) {
		List<ComCodeVO> crSe = commonMapper.getComeCodeList("SEC020");
		
		model.addAttribute("crSe",crSe);
		model.addAttribute("lctreNo",lctreNo);
		
		return "ajax/professor/classroom/professorClassroomOnlineLectureView";
	}
	
	//------------------------------- Tab View End -----------------------------------------
	
	
	//시험출제 등록***
	@PostMapping("/test/new")
	public String excelUploadTestAnswer(
			Model model
			, MultipartFile testFile
			, MultipartFile answerFile 
			, TestQuestionVO test
			,Authentication principle
	)throws Exception{
		log.info("넘어온 파라미터 test : {}",test);
		if(answerFile == null || answerFile.isEmpty()) {
			throw new RemoteTimeoutException("엑셀파일을 선택해주세요.");
		}
		
		String prfsorNo = principle.getName();
		
		test.setTestQustTime((test.getTestQustTime() * 60));
		
		service.createClassRoomTest(testFile, answerFile, test);
		
		TestQuestionVO testQuestionVO = new TestQuestionVO();
		testQuestionVO.setLctreNo(test.getLctreNo());//강의코드
		testQuestionVO.setPrfsorNo(prfsorNo);
		
		log.info("TestQuestionVO 넘어온 값 확인 : {},{},{}",testFile, answerFile, test);
		log.info("test.getLctreNo() 넘어온 값 확인 : {}}",test.getLctreNo());
		
//		List<TestQuestionVO> testList =service.retrieveClassRoomTestList(testQuestionVO);
		
//		model.addAttribute("testList",testList);
		model.addAttribute("result", true);
		
		return "jsonView";
//		return testList;
	}
	
	//과제 등록 
	@PostMapping("/assignment/new")
	public String classroomAssignmentUpload(
			Model model
			, MultipartFile assignmentFile
			, ClassroomBoardVO classroomBoard
			, Authentication principle
	)throws Exception {
		
		log.info("classroomBoard 넘어온 값 확인 : {}",classroomBoard);
		log.info("넘어온 파라미터 attachingFile 확인 ! : {}",assignmentFile);
		if(assignmentFile == null || assignmentFile.isEmpty()) {
			throw new RemoteTimeoutException("과제파일을 선택해주세요.");
		}
		
		//작성자
		String crWriter = principle.getName();
		
		classroomBoard.setCrWrter(crWriter);
		classroomBoard.setCrSe("01");
		
		service.createClassRoomBoard(assignmentFile,classroomBoard);
		
		model.addAttribute("result", true);
		
		log.info("assignmentFile 과제파일 오는지 확인! {}",assignmentFile);
		
		
		return"jsonView";
	}
	
	//자료 등록 
	@PostMapping("/data/new")
	public String classroomDataUpload(
			Model model
			, MultipartFile dataFile
			, ClassroomBoardVO classroomBoard
			, Authentication principle
			)throws Exception {
		
		log.info("classroomBoard 넘어온 값 확인 : {}",classroomBoard);
		log.info("넘어온 파라미터 attachingFile 확인 ! : {}",dataFile);
		if(dataFile == null || dataFile.isEmpty()) {
			throw new RemoteTimeoutException("자료파일을 선택해주세요.");
		}
		
		//작성자
		String crWriter = principle.getName();
		
		classroomBoard.setCrWrter(crWriter);
		
		service.createClassRoomBoard(dataFile,classroomBoard);
		
		model.addAttribute("result", true);
		
		log.info("assignmentFile 과제파일 오는지 확인! {}",dataFile);
		
		return"jsonView";
	}
	
	//공지사항 등록 
	@PostMapping("/notice/new")
	public String classroomNoticeUpload(
			Model model
			, MultipartFile noticeFile
			, ClassroomBoardVO classroomBoard
			, Authentication principle
			)throws Exception {
		
		log.info("classroomBoard 넘어온 값 확인 : {}",classroomBoard);
		log.info("넘어온 파라미터 attachingFile 확인 ! : {}",noticeFile);
		
		//작성자
		String crWriter = principle.getName();
		
		classroomBoard.setCrWrter(crWriter);
		classroomBoard.setCrSe("04");
		service.createClassRoomBoard(noticeFile,classroomBoard);
		
		model.addAttribute("result", true);
		
		log.info("assignmentFile 과제파일 오는지 확인! {}",noticeFile);
		
		return"jsonView";
	}
	
	//--------------------------------- Insert and Upload End -------------------------------
	
	// 시험출제 목록 리스트
	@GetMapping("/test/ajax/testList/{lctreNo}")
	public String classroomTestList(
			Model model
			,@PathVariable String lctreNo
			,Authentication principle
	) {
		String prfsorNo = principle.getName();
		
		TestQuestionVO testQuestionVO = new TestQuestionVO();
		
		testQuestionVO.setLctreNo(lctreNo);
		testQuestionVO.setPrfsorNo(prfsorNo);
		
		List<TestQuestionVO> testList =service.retrieveClassRoomTestList(testQuestionVO);
		
		model.addAttribute("testList",testList);
		
		return "jsonView";
	}
	
	// 과제등록 목록 리스트
	@GetMapping("/test/ajax/assignmentList/{lctreNo}")
	public String classroomAssignmentList(
			Model model
			,@PathVariable String lctreNo
			,Authentication principle
			) {
		String prfsorNo = principle.getName();
		
		ClassroomBoardVO classroomBoardVO = new ClassroomBoardVO();
		
		classroomBoardVO.setLctreNo(lctreNo);
		classroomBoardVO.setPrfsorNo(prfsorNo);
		classroomBoardVO.setCrSe("01");
		
		List<ClassroomBoardVO> assignmentList =service.retrieveClassRoomAssignmentList(classroomBoardVO);
		
		model.addAttribute("assignmentList",assignmentList);
		
		return "jsonView";
	}
	
	// 자료등록 목록 리스트
	@GetMapping("/test/ajax/data/{lctreNo}")
	public String classroomDataList(
			Model model
			,@PathVariable String lctreNo
			,Authentication principle
			) {
		String prfsorNo = principle.getName();
		
		ClassroomBoardVO classroomBoardVO = new ClassroomBoardVO();
		
		classroomBoardVO.setLctreNo(lctreNo);
		classroomBoardVO.setPrfsorNo(prfsorNo);
		classroomBoardVO.setCrSe("03");
		
		List<ClassroomBoardVO> dataList =service.retrieveClassRoomDataList(classroomBoardVO);
		
		log.info("====================================================dataList",dataList);
		model.addAttribute("dataList",dataList);
		
		return "jsonView";
	}
	
	// 공지사항 목록 리스트
	@GetMapping("/test/ajax/notice/{lctreNo}")
	public String classroomNoticeList(
			Model model
			,@PathVariable String lctreNo
			,Authentication principle
			) {
		String prfsorNo = principle.getName();
		
		ClassroomBoardVO classroomBoardVO = new ClassroomBoardVO();
		log.info("강의코드 넘어 왔니 : {}",lctreNo);
		
		
		classroomBoardVO.setLctreNo(lctreNo);
		classroomBoardVO.setPrfsorNo(prfsorNo);
		classroomBoardVO.setCrSe("04");
		
		List<ClassroomBoardVO> noticeList =service.retrieveClassRoomNoticeList(classroomBoardVO);
		
		log.info("====================================================noticeList",noticeList);
		model.addAttribute("noticeList",noticeList);
		
		return "jsonView";
	}
	
	
	// -------------------------- 클래스룸 각 기능 목록 리스트 End ----------------------------------
	
	
	//시험출제 정보 View 조회
	@GetMapping("/test/testView/{lctreNo}/{testSe}")
	public String classroomTestView(
			@PathVariable String lctreNo
			,@PathVariable String testSe
			,Model model
			,Authentication principle
			) {
		String prfsorNo = principle.getName();
		TestQuestionVO testQuestion = new TestQuestionVO();
		testQuestion.setLctreNo(lctreNo);
		testQuestion.setPrfsorNo(prfsorNo);
		testQuestion.setTestSe(testSe);
		
		TestQuestionVO testQuestionVO = service.retrieveClassRoomTestView(testQuestion);
		
		TestQuestionVO testStareStudentList = new TestQuestionVO();
		testStareStudentList.setLctreNo(lctreNo);
		testStareStudentList.setTestSe(testSe);
		
		List<TestQuestionVO> studentStareList = service.retrieveTestStareStudentList(testStareStudentList);
		
		log.info("studentStareList 왜 안나올까",studentStareList);
		
		log.info(" 시험응시 학생목록 나와라 제발 ========================== {}",studentStareList);
		
		
//		String mimeType =classroomBoardVO.getAtchFile().getMimeType();
//		String fileName =classroomBoardVO.getAtchFile().getAtchFileStreNm();
//		String filePath =classroomBoardVO.getAtchFile().getAtchFileStrePath();
//		
//		String outputFilename="";
//		try {
//            File file = new File("C:/USU/"+fileName+mimeType);
//            
//            PDDocument document = PDDocument.load(file);
//            PDFRenderer renderer = new PDFRenderer(document);
//            
//            for (int pageNumber = 0; pageNumber < document.getNumberOfPages(); pageNumber++) {
//                BufferedImage image = renderer.renderImageWithDPI(pageNumber, 300);
//                
//                outputFilename = "C:/USU/"+fileName+ pageNumber + mimeType;
//                ImageIO.write(image, "png", new File(outputFilename));
//                
//                System.out.println("Extracted image: " + outputFilename);
//            }
//            
//            document.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//		model.addAttribute("outputFilename",outputFilename);
		
		model.addAttribute("classroomBoardVO",testQuestionVO);
		model.addAttribute("studentStareList",studentStareList);
		
		return "ajax/professor/classroom/professorClassroomTestDetailView";
	}
	
	// 파일 다운로드용
	@GetMapping("/file/{atchFileNo}")
	public ResponseEntity<Resource> download(
			@PathVariable String atchFileNo) throws IOException {
		
		AttatchingFileVO atch = service.retrieveAttatchingFile(atchFileNo);
		Resource File = saveFolderResource.createRelative(atch.getAtchFileStreNm());
		if(!File.exists()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 파일이 없음");
		}
		
		HttpHeaders headers = new HttpHeaders();
		ContentDisposition disposition = ContentDisposition.attachment()
										.filename(atch.getAtchFileNm(), Charset.defaultCharset())
										.build();
		
		headers.setContentDisposition(disposition);
		headers.setContentLength(atch.getAtchFileMg());
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return ResponseEntity.ok()
					.headers(headers)
					.body(File);
				
	}
	
	// 클래스룸 과제 상세정보 및 과제제출 학생목록 및 자료
	@GetMapping("/assignment/view/{crNo}")
	public String classroomAssignmentView(
			@PathVariable String crNo
			,Model model	
	) {
		log.info("클래스룸 코드번호 오는지 확인 : {}",crNo);
		
		ClassroomBoardVO assignmentView = service.retrieveClassRoomAssignmentView(crNo);
		List<ClassroomBoardVO> assignmetSubmitStudentList = service.retrieveClassRoomAssignmentSubmitStudentList(crNo);
		
		model.addAttribute("assignmentView", assignmentView);
		model.addAttribute("assignmetSubmitStudentList",assignmetSubmitStudentList);
		
		log.info("assignmentView 과제 상세내역 조회 값 : {}",assignmentView);
		log.info("assignmetSubmitStudentList 과제제출 학생목록 정보 : {}",assignmetSubmitStudentList);
		
		return "ajax/professor/classroom/professorClassroomAssignmentDetailView";
	}
	// 클래스룸 자료 상세정보
	@GetMapping("/data/view/{crNo}")
	public String classroomDataView(
		@PathVariable String crNo
		,Model model	
	) {
		log.info("클래스룸 코드번호 오는지 확인 : {}",crNo);
		
		ClassroomBoardVO dataView = service.retrieveClassRoomDataOrNoticeView(crNo);
		
		log.info("자료 상세조회 정보 : {}",dataView);
		
		model.addAttribute("dataView",dataView);
		
		return "ajax/professor/classroom/professorClassroomDataDetailView";
	}
	// 클래스룸 공지사항 상세정보
	@GetMapping("/notice/view/{crNo}")
	public String classroomNoticetView(
		@PathVariable String crNo
		,Model model			
	) {
		log.info("클래스룸 코드번호 오는지 확인 : {}",crNo);
		
		ClassroomBoardVO viewInfo = service.retrieveClassRoomDataOrNoticeView(crNo);
		
		
		model.addAttribute("viewInfo",viewInfo);
		
		
		log.info("공지사항 상세조회 정보 : {}",viewInfo);
		
		
		return "ajax/professor/classroom/professorClassroomNoticeDetailView";
	}
	
	
	
	
	
	
	
	

}