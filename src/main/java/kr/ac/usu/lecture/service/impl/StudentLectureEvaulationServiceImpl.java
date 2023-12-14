package kr.ac.usu.lecture.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.ac.usu.lecture.mapper.StudentLectureEvaulationMapper;
import kr.ac.usu.lecture.service.StudentLectureEvaulationService;
import kr.ac.usu.lecture.vo.LectureEvaulationItemVO;
import kr.ac.usu.lecture.vo.LectureEvaulationScoreVO;
import kr.ac.usu.lecture.vo.LectureEvaulationVO;
import kr.ac.usu.lecture.vo.LectureVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 학생 강의평가 기능 서비스로직 구현체
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
@Service
@Slf4j
public class StudentLectureEvaulationServiceImpl implements StudentLectureEvaulationService {

	@Inject
	private StudentLectureEvaulationMapper mapper;
	
	@Override
	public List<LectureEvaulationItemVO> retrieveLectureEvaulationItems() {
		return mapper.selectLectureEvaulationItems();
	}

	@Override
	public List<LectureEvaulationScoreVO> retrieveLectureEvaulationScores() {
		return mapper.selectLectureEvaulationScores();
	}

	@Override
	public List<LectureVO> retrieveLectrueListForEvaulation(String id, String semCd) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		return mapper.selectLectrueListForEvaulation(paramMap);
	}

	@Override
	public void createLectureEvaulation(Map<String, String> requestParamMap) {
		log.info("{}",requestParamMap);
		List<LectureEvaulationVO> evaulationList = new ArrayList<LectureEvaulationVO>();
		String stdntNo = requestParamMap.get("stdntNo");
		String lctreNo = requestParamMap.get("lctreNo");
		List<LectureEvaulationItemVO> list = mapper.selectLectureEvaulationItems();
		for(LectureEvaulationItemVO item : list) {
			LectureEvaulationVO evaulationItem = new LectureEvaulationVO();
			evaulationItem.setStdntNo(stdntNo);
			evaulationItem.setLctreNo(lctreNo);
			evaulationItem.setLctreEvlIem(item.getLctreEvlIemNo());
			evaulationItem.setLctreEvlScoreNo(Integer.parseInt(requestParamMap.get(item.getLctreEvlIemNo())));
			
			evaulationList.add(evaulationItem);
		}
		log.info("보내는 리스트의 사이즈 : {}",evaulationList.size());
		int resultCount = mapper.insertLectureEvaulation(evaulationList);
		log.info("실행된 로우 수 : {}" , resultCount);
	}
}