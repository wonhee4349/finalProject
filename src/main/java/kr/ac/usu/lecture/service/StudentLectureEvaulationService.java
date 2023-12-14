package kr.ac.usu.lecture.service;

import java.util.List;
import java.util.Map;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.lecture.vo.LectureEvaulationItemVO;
import kr.ac.usu.lecture.vo.LectureEvaulationScoreVO;
import kr.ac.usu.lecture.vo.LectureEvaulationVO;
import kr.ac.usu.lecture.vo.LectureVO;

/**
 * 학생 강의평가 기능 서비스로직 인터페인스
 * @author 김석호
 * @since 2023. 11. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 11. 22.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public interface StudentLectureEvaulationService {
	public List<LectureEvaulationItemVO> retrieveLectureEvaulationItems();
	public List<LectureEvaulationScoreVO> retrieveLectureEvaulationScores();
	
	public List<LectureVO> retrieveLectrueListForEvaulation(String id, String semCd);
	
	public void createLectureEvaulation(Map<String, String> requestParamMap);
}
