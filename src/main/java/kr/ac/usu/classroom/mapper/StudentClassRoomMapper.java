package kr.ac.usu.classroom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.classroom.vo.ClassroomBoardVO;
import kr.ac.usu.classroom.vo.TestAnswerVO;
import kr.ac.usu.classroom.vo.TestQuestionVO;
import kr.ac.usu.classroom.vo.TestVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.lecture.vo.LectureVideoVO;

/**
 * 학생 클래스룸 관련 기능 Mapper
 * @author 김석호
 * @since 2023. 11. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 11. 27.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StudentClassRoomMapper {

	/**
	 * 이번학기 듣는 강의 목록 조회
	 * 학생 학번 key : id
	 * 학기 코드 key : semCd
	 * @param paramMap
	 * @return
	 */
	public List<LectureVO> selectClassRoomList(Map<String, String> paramMap);
	/**
	 * 이번학기에 수강하는 특정 강의에 대한 과제 목록 조회
	 * 학생 학번 key : id
	 * 학기 코드 key : semCd
	 * 강의 코드 key : lctreNo
	 * @param paramMap
	 * @return
	 */
	public List<ClassroomBoardVO> selectHomeWorkList(Map<String, String> paramMap);
	
	/**
	 * 특정 강의의 시험 목록 조회
	 * 학생 학번 key : id
	 * 학기 코드 key : semCd
	 * 강의 코드 key : lctreNo
	 * @param paramMap
	 * @return
	 */
	public List<TestQuestionVO> selectTestQuestionList(Map<String, String> paramMap);
	
	/**
	 * 특정 시험에 대한 정보 조회
	 * @param test
	 * @return
	 */
	public TestQuestionVO selectTestQuestion(TestVO test);
	
	/**
	 * 한 통합첨부파일 코드가 가지고 있는 파일을 조회
	 * @param atchFileNo
	 * @return
	 */
	public List<AttatchingFileVO> selectAttatchingFileList(@Param("atchFileNo") String atchFileNo);
	
	/**
	 * 특정 강의의 시험 목록 조회
	 * 학생 학번 key : id
	 * 학기 코드 key : semCd
	 * 강의 코드 key : lctreNo
	 * @param paramMap
	 * @return
	 */
	public List<ClassroomBoardVO> selectClassRoomBoardList(Map<String, String> paramMap);
	
	/**
	 * 특정 강의의 시험 목록 조회
	 * 학생 학번 key : id
	 * 학기 코드 key : semCd
	 * 강의 코드 key : lctreNo
	 * @param paramMap
	 * @return
	 */
	public List<LectureVideoVO> selectOnlineLectureList(Map<String, String> paramMap);
	
	/**
	 * 파일 정보 조회
	 * @param fileInfo
	 * @return
	 */
	public AttatchingFileVO selectOneAttatchingFile(AttatchingFileVO fileInfo);
	
	
	/**
	 * 시험 답안 제출
	 * @param answerList
	 * @return
	 */
	public int insertSubmitTestAnswer(List<TestAnswerVO> answerList);
	
	
	/**
	 * 시험 점수 조회
	 * @param test
	 * @return
	 */
	public int selectTestResultInfo(TestVO test);
	
	/**
	 * 제출된 과제 파일 삽입
	 * @param assignment
	 * @return
	 */
	public int insertAssignmentFile(AttatchingFileVO assignment);
	
	/**
	 * 과제정보 삽입
	 * 학번 key : id
	 * 글번호 key : crNo
	 * 첨부파일 번호 key : atchFileNo
	 * @param paramMap
	 * @return
	 */
	public int insertAssignment(Map<String, String> paramMap);
	
	/**
	 * 제출 과제 파일 정보 조회
	 * 학번 key : id
	 * 글번호 key : crNo
	 * @param paramMap
	 * @return
	 */
	public AttatchingFileVO selectSubmittedAssignment(Map<String, String> paramMap);
	
	/**
	 * 클래스룸 게시글 하나 조회
	 * 학번 key : id
	 * 글번호 key : crNo
	 * @param paramMap
	 * @return
	 */
	public ClassroomBoardVO selectClassroomBoard(Map<String, String> paramMap);
	
	/**
	 * 클래스룸 파일 접근 권한 조회
	 * @param fileInfo
	 * @return
	 */
	public int selectAuthorityFile(AttatchingFileVO fileInfo);
}