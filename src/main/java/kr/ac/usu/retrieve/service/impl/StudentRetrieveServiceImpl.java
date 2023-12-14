package kr.ac.usu.retrieve.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.retrieve.mapper.StudentRetrieveMapper;
import kr.ac.usu.retrieve.service.StudentRetrieveService;
import kr.ac.usu.score.vo.ScoreRankVO;

/**
 * 학생 조회전용 서비스 구현체
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
@Service
public class StudentRetrieveServiceImpl implements StudentRetrieveService {

	@Inject
	private StudentRetrieveMapper mapper;
	
	@Override
	public List<Map<String, String>> retrieveStudentSemesterList(String id) {
		return mapper.selectStudentSemesterList(id);
	}

	@Override
	public List<LectureVO> retriveStudentLectureListOnOneSemester(String id, String semCd) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		return mapper.selectStudentLectureListOnOneSemester(paramMap);
	}

	@Override
	public Map<String, Integer> retrieveStudentInformationAboutScore(String id, String semCd) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		resultMap.put("allCredit", mapper.selectAllCreditPoint(paramMap));
		resultMap.put("acceptedCredit", mapper.selectAcceptedCreditPoint(paramMap));
		resultMap.put("requiredMajorCredit", mapper.selectRequiredMajorCreditPoint(paramMap));
		resultMap.put("notRequiredMajorCredit", mapper.selectNotRequiredMajorCreditPoint(paramMap));
		resultMap.put("requiredCultureCredit", mapper.selectRequiredCultureCreditPoint(paramMap));
		resultMap.put("notRequiredCultureCredit", mapper.selectNotRequiredCultureCreditPoint(paramMap));
		resultMap.put("major1Credit", mapper.selectMajor1CreditPoint(paramMap));
		resultMap.put("major2Credit", mapper.selectMajor2CreditPoint(paramMap));
		resultMap.put("minorCredit", mapper.selectMinorCreditPoint(paramMap));
		resultMap.put("onlineLectureCredit", mapper.selectOnlineLectureCreditPoint(paramMap));
		resultMap.put("untactLectureCredit", mapper.selectUntactLectureCreditPoint(paramMap));
		
		return resultMap;
	}

	@Override
	public ScoreRankVO retrieveStudentRankInformation(String id, String semCd) {
		ScoreRankVO info = new ScoreRankVO();
		info.setSemCd(semCd);
		info.setStdntNo(id);
		return mapper.selectRankInfoOnOneSemester(info);
	}

	@Override
	public double retrieveTotalScore(String id) {
		return mapper.totalScore(id);
	}

	@Override
	public List<Map<String, String>> retrieveStudentTimetable(String id, String semCd) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		return mapper.selectStudentTimeTable(paramMap);
	}
	
}
