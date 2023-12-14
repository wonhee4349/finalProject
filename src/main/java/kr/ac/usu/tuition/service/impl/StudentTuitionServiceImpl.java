package kr.ac.usu.tuition.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.ac.usu.tuition.mapper.StudentTuitionMapper;
import kr.ac.usu.tuition.service.StudentTuitionService;
import kr.ac.usu.tuition.vo.TuitionVO;

@Service
public class StudentTuitionServiceImpl implements StudentTuitionService {

	@Inject
	private StudentTuitionMapper mapper;
	
	@Override
	public List<Map<String, String>> retrieveSemesterListForStudentTuition(String id, String semCd) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		return mapper.selectSemesterListForStudentTuition(paramMap);
	}

	@Override
	public TuitionVO retrieveTuitionInformationForStudentWithSemesterCode(TuitionVO searchCondition) {
		return mapper.selectTuitionInformationForStudentWithSemesterCode(searchCondition);
	}
}
