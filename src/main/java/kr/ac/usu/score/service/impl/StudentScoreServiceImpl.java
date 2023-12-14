package kr.ac.usu.score.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.score.mapper.StudentScoreMapper;
import kr.ac.usu.score.service.StudentScoreService;
import kr.ac.usu.score.vo.ScoreObjectionVO;
import kr.ac.usu.score.vo.ScoreVO;

/**
 * 학생의 성적 관련 기능 Service 구현체
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
@Service
public class StudentScoreServiceImpl implements StudentScoreService {

	@Inject
	private StudentScoreMapper mapper;

	@Override
	public List<ScoreVO> retrieveScoreList(String id, String semCd) {
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		return mapper.selectScoreList(paramMap);
	}


	@Override
	public List<Map<String, String>> retrieveSemCdList(String id, String semCd) {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		return mapper.selectSemCdList(paramMap);
	}


	@Override
	public double retrieveCalScore(String id, String semCd) {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		return mapper.selectCalScore(paramMap);
	}


	@Override
	public boolean retrieveAvailabilityForSelectCurrentSemesterScore(String id, String semCd) {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		String availability = mapper.selectAvailabilityForSelectCurrentSemesterScore(paramMap);
		return "OK".equals(availability);
	}


	@Override
	public ScoreObjectionVO retrieveScoreObjectionInformation(String id, String lctreNo) {
		ScoreObjectionVO scoreObjection = new ScoreObjectionVO();
		scoreObjection.setStdntNo(id);
		scoreObjection.setLctreNo(lctreNo);
		return mapper.selectScoreObjectionInformation(scoreObjection);
	}


	@Override
	public ServiceResult createScoreObjection(ScoreObjectionVO scoreObjection) {
		int resultCount = mapper.insertScoreObjection(scoreObjection); 
		return resultCount > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}
	

}
