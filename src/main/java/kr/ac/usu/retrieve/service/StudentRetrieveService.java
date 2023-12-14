package kr.ac.usu.retrieve.service;

import java.util.List;
import java.util.Map;

import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.score.vo.ScoreRankVO;

/**
 * 학생 조회전용 서비스 인터페이스
 * @author 김석호
 * @since 2023. 12. 1.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 12. 1.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public interface StudentRetrieveService {
	public List<Map<String, String>> retrieveStudentSemesterList(String id);
	
	public List<LectureVO> retriveStudentLectureListOnOneSemester(String id, String semCd);
	
	public Map<String, Integer> retrieveStudentInformationAboutScore(String id, String semCd);
	
	public ScoreRankVO retrieveStudentRankInformation(String id, String semCd);
	
	public double retrieveTotalScore(String id);
	
	public List<Map<String, String>> retrieveStudentTimetable(String id, String semCd);
}
