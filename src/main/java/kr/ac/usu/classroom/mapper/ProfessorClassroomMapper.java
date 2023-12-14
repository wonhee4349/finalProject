package kr.ac.usu.classroom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.classroom.vo.ClassroomBoardVO;
import kr.ac.usu.classroom.vo.TestAnswerVO;
import kr.ac.usu.classroom.vo.TestQuestionVO;
import kr.ac.usu.classroom.vo.TestVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.user.vo.StudentVO;

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
 * 2023.11.28.      김석호       개편
 * 2023.11.29.      김재성       클래스룸 시험출제 목록
 * 2023.11.30.      김재성       클래스룸 과제등록 목록
 * 2023.12.02.      김재성       클래스룸 과제제출 학생목록
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface ProfessorClassroomMapper {
	
	/**
	 * 교수가 강의중인 강의목록
	 * @param lctreNo 교수번호
	 * @return List<LectureVO> studentList
	 */
	public List<LectureVO> selectClassroomProfessorLecture(String prfsorNo);
	
	/**
	 * 시험 파일 업로드
	 * @param testFile
	 * @return
	 */
	public int insertTestFile(AttatchingFileVO testFile);
	
	
	/**
	 * 교수가 강의중인 한 강의에 시험을 등록
	 * @param test
	 * @return
	 */
	public int insertTest(TestVO test);
	
	/**
	 * 시험 등록, 파일등록 후 시험문제 테이블에 등록
	 * @param testQuestion
	 * @return
	 */
	public int insertTestQuestion(TestQuestionVO testQuestion);
	
	/**
	 * 교수가 강의중인 한 강의에 시험 출제 등록
	 * @param testQeust
	 * @return 
	 */
	public int insertClassroomTestQuest(List<TestAnswerVO> testQeustList);

	/**
	 * 클래스룸에 등록하는 과제 , 자료 파일 업로드
	 * @param atch
	 */
	public int insertClassRoomFile(AttatchingFileVO atch);
	
	/**
	 * 교수가 클래스룸에서 시험,과제,자료,공지사항 등록 통합 사용
	 * @param classboard
	 * @return
	 */
	public int insertClassRoomBoard(ClassroomBoardVO classboard);
	
	/**
	 * 한 교수의 한 강의에 클래스룸에 출제한시험출제 목록
	 * @param prfsorNo
	 * @return
	 */
	public List<TestQuestionVO> selectClassRoomTestList(TestQuestionVO testQuestionVO);
	
	/**
	 * 한 교수가 한 강의에 클래스룸에 등록한 과제 목록
	 * @param classBoardVO
	 * @return
	 */
	public List<ClassroomBoardVO> selectClassRoomAssignmentList(ClassroomBoardVO classBoardVO);
	
	/**
	 * 클래스룸에서 강의선택후 시험탭에 시험출제 목록중 하나를 선택시 나오는 상세정보 View
	 * @param lctreNo : 강의번호
	 * @param testSe : 시험 구분코드
	 * @return
	 */
	public TestQuestionVO selectClassRoomTestView(TestQuestionVO testQuestionVO);
	
	/**
	 * 클래스룸에서 시험파일 , 과제파일, 자료파일 다운로드용 
	 * @param atchFileNo : 통합첨부파일 코드 
	 * @return
	 */
	public AttatchingFileVO selectAttatchingFile(String atchFileNo);
	
	/**
	 * 클래스룸 한 시험출제에 응시한 학생목록
	 * @param testVO
	 * @return
	 */
	public List<TestQuestionVO> selectTestStareStudentList(TestQuestionVO testQuestionVO);
	
	/**
	 * 한 교수가 한 강의에 클래스룸에 등록한 자료 목록
	 * @param classBoardVO
	 * @return
	 */
	public List<ClassroomBoardVO> selectClassRoomDataList(ClassroomBoardVO classBoardVO);
	/**
	 * 한 교수가 한 강의에 클래스룸에 등록한 공지사항 목록
	 * @param classBoardVO
	 * @return
	 */
	public List<ClassroomBoardVO> selectClassRoomNoticeList(ClassroomBoardVO classBoardVO);
	
	/**
	 * 클래스룸 과제 탭 상세정보 View
	 * @param crNo : 클래스룸 글번호 
	 * @return classroomBoardVO
	 */
	public ClassroomBoardVO selectClassRoomAssignmentView(@RequestParam("crNo") String crNo);
	
	/**
	 * 클래스룸 자료 or 공지사항 상세정보 View
	 * @param crCn :클래스룸 글번호
	 * @return ClassroomBoardVO
	 */
	public ClassroomBoardVO selectClassRoomDataOrNoticeView(String crNo);
	
	/**
	 * 클래스룸 과제 제출 학생 정보 가져오기
	 * @param crNo
	 * @return
	 */
	public List<ClassroomBoardVO> selectClassRoomAssignmentSubmitStudentList(@RequestParam("crNo") String crNo);

	
	
	
}
