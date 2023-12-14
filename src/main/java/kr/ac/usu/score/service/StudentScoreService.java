package kr.ac.usu.score.service;

import java.util.List;
import java.util.Map;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.score.vo.ScoreObjectionVO;
import kr.ac.usu.score.vo.ScoreVO;

/**
 * 학생의 성적 관련 기능 Service 인터페이스
 * @author 김석호
 * @since 2023. 11. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일            수정자         수정내용
 * --------       --------    ----------------------
 * 2023. 11. 16.   김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public interface StudentScoreService {
	
	public List<Map<String,String>> retrieveSemCdList(String id, String semCd);
	
	public List<ScoreVO> retrieveScoreList(String id, String semCd);
	
	public double retrieveCalScore(String id, String semCd);
	
	public boolean retrieveAvailabilityForSelectCurrentSemesterScore(String id, String semCd);
	
	public ScoreObjectionVO retrieveScoreObjectionInformation(String id, String lctreNo);
	
	public ServiceResult createScoreObjection(ScoreObjectionVO scoreObjection);
}
