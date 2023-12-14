package kr.ac.usu.classroom.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.classroom.vo.ClassroomBoardVO;
import kr.ac.usu.classroom.vo.TestQuestionVO;
import kr.ac.usu.lecture.vo.LectureVO;

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
 * 2023.11.29.      김재성       클래스룸 시험출제 목록
 * 2023.11.30.      김재성       클래스룸 과제등록 목록
 * 2023.12.02.      김재성       시험응시 학생목록 메소드 재수정
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public interface ProfessorClassroomService {
	
	
	/**
	 * 교수가 강의중인 강의목록
	 * @param lctreNo 교수번호
	 * @return List<LectureVO> studentList
	 */
	public List<LectureVO> retrieveClassroomProfessorLecture(String prfsorNo);
	
	/**
	 * 시험 출제 -> 시험 PDF , 답안 Excel , 시험구분 , 제한시간 받아 등록
	 * @param testFile
	 * @param answerFile
	 * @param test
	 * @throws Exception
	 */
	public void createClassRoomTest(MultipartFile testFile, MultipartFile answerFile, TestQuestionVO test) throws Exception;

	/**
	 * 과제 등록 -> 과제 파일, 과제 제목,내용, 클래스룸 구분 받아 등록
	 * @param assignmentFile : 과제 파일
	 * @param classroomBoard : 과제 파일 내용 클래스룸보드 테이블 insert
	 * @return
	 */
	public void createClassRoomBoard(MultipartFile assignmentFile, ClassroomBoardVO classroomBoard)throws Exception;
	
	/**
	 * 한 교수의 한 강의에 클래스룸에 출제한 시험출제 목록
	 * @param prfsorNo
	 * @return
	 */
	public List<TestQuestionVO> retrieveClassRoomTestList(TestQuestionVO testQuestionVO);
	
	/**
	 * 한 교수의 한강의에 클래스룸에 등록한 과제 목록
	 * @param classBoardVO
	 * @return
	 */
	public List<ClassroomBoardVO> retrieveClassRoomAssignmentList(ClassroomBoardVO classBoardVO);
	
	/**
	 * 클래스룸에서 강의선택후 시험탭에 시험출제 목록중 하나를 선택시 나오는 상세정보 View
	 * @param lctreNo : 강의번호
	 * @param testSe : 시험 구분코드
	 * @return
	 */
	public TestQuestionVO retrieveClassRoomTestView(TestQuestionVO testQuestionVO);
	
	/**
	 * 클래스룸 한 시험출제에 응시한 학생목록
	 * @param testVO
	 * @return
	 */
	public List<TestQuestionVO> retrieveTestStareStudentList(TestQuestionVO testQuestionVO);
	
	/**
	 * 클래스룸에서 시험파일 , 과제파일, 자료파일 다운로드용 
	 * @param atchFileNo : 통합첨부파일 코드 
	 * @return
	 */
	public AttatchingFileVO retrieveAttatchingFile(String atchFileNo);
	
	/**
	 * 한 교수가 한 강의에 클래스룸에 등록한 자료 목록
	 * @param classBoardVO
	 * @return
	 */
	public List<ClassroomBoardVO> retrieveClassRoomDataList(ClassroomBoardVO classBoardVO);
	
	/**
	 * 한 교수가 한 강의에 클래스룸에 등록한 자료 목록
	 * @param classBoardVO
	 * @return
	 */
	public List<ClassroomBoardVO> retrieveClassRoomNoticeList(ClassroomBoardVO classBoardVO);
	
	/**
	 * 클래스룸 과제 탭 상세정보 View & 과제제출 학생 정보 가져오기
	 * @param classroomBoardVO 
	 * @return crNo : 클래스룸 글번호
	 */
	public ClassroomBoardVO retrieveClassRoomAssignmentView(String crNo);
	
	/**
	 * 클래스룸 자료 or 공지사항 상세정보 View
	 * @param crCn :클래스룸 글번호
	 * @return ClassroomBoardVO
	 */
	public ClassroomBoardVO retrieveClassRoomDataOrNoticeView(String crNo);
	
	/**
	 * 클래스룸 과제 제출 학생 정보 가져오기
	 * @param crNo
	 * @return
	 */
	public List<ClassroomBoardVO> retrieveClassRoomAssignmentSubmitStudentList(@RequestParam("crNo") String crNo);

}
