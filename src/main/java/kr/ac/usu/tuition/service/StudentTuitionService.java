package kr.ac.usu.tuition.service;

import java.util.List;
import java.util.Map;

import kr.ac.usu.tuition.vo.TuitionVO;

/**
 * 학생 등록금 관련 기능 서비스 로직 인터페이스
 * @author 김석호
 * @since 2023. 11. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 11. 24.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public interface StudentTuitionService {
	
	public List<Map<String, String>> retrieveSemesterListForStudentTuition(String id, String semCd);
	public TuitionVO retrieveTuitionInformationForStudentWithSemesterCode(TuitionVO searchCondition);
}
