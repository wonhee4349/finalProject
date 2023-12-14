package kr.ac.usu.classroom.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.classroom.vo.ClassroomBoardVO;
import kr.ac.usu.classroom.vo.TestQuestionVO;
import kr.ac.usu.classroom.vo.TestVO;
import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.lecture.vo.LectureVideoVO;

/**
 * 학생 클래스룸 관련 기능 서비스 인터페이스
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
public interface StudentClassroomService {
	
	public List<LectureVO> retrieveClassRoomList(String id, String semCd);
	public List<ClassroomBoardVO> retrieveHomeWorkList(String id, String semCd, String lctreNo);
	public List<TestQuestionVO> retrieveTestQuestionList(String id, String semCd, String lctreNo);
	public TestQuestionVO retrieveTestQuestion(TestVO test);
	
	public List<AttatchingFileVO> retrieveAttatchingFileList(String atchFileNo);
	public List<ClassroomBoardVO> retrieveClassRoomBoardList(String id, String semCd, String lctreNo);
	public List<LectureVideoVO> retrieveOnlineLectureList(String id, String semCd, String lctreNo);
	public AttatchingFileVO retrieveOneAttatchingFile(AttatchingFileVO fileInfo);
	
	public void createSubmitTest(Map<String, String> paramMap, String id, String lctreNo, String testSe, int questionCnt);
	
	public int retrieveTestResultInfo(String id, String lctreNo, String testSe);
	
	public void createSubmitAssignment(String id, String crNo, MultipartFile assignmentFile) throws Exception;
	public AttatchingFileVO retrieveSubmittedAssignment(String id, String crNo);
	
	public ClassroomBoardVO retrieveClassRoomBoard(String id, String crNo);
	
	public boolean retrieveAuthorityFile(String id, String atchFileNo);
	
	public List<String> getBase64(AttatchingFileVO atch) throws Exception;
}
