package kr.ac.usu.scholarship.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.scholarship.mapper.StudentScholarshipMapper;
import kr.ac.usu.scholarship.service.StudentScholarshipService;
import kr.ac.usu.scholarship.vo.ScholarshipRequestVO;
import kr.ac.usu.scholarship.vo.ScholarshipStudentVO;
import kr.ac.usu.scholarship.vo.ScholarshipVO;

@Service
public class StudentScholarshipServiceImpl implements StudentScholarshipService {

	@Inject
	private StudentScholarshipMapper mapper;
	
	@Override
	public void retrieveScholarshipList(PaginationInfo<ScholarshipVO> paging) {
		int totRec = mapper.selectselectScholarshipTotalRecord(paging);
		paging.setTotalRecord(totRec);
		List<ScholarshipVO> dataList = mapper.selectScholarshipList(paging);
		paging.setDataList(dataList);
	}

	@Override
	public ScholarshipVO retrieveScholarship(String schlshipNo) {
		return mapper.selectScholarship(schlshipNo);
	}

	@Override
	public ServiceResult createStudentScholarship(ScholarshipRequestVO scholarshipRequest) {
//		mapper.insertStudentScholarshipAttatchFile(null);
		int res = mapper.insertStudentScholarship(scholarshipRequest);
		return res > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public List<ScholarshipRequestVO> retrieveStudentRequestScholarshipList(String id, String semCd) {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		return mapper.selectStudentRequestScholarshipList(paramMap);
	}

	@Override
	public List<ScholarshipStudentVO> retrieveStudentScholarList(String id, String semCd) {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		return mapper.selectStudentScholarList(paramMap);
	}

	@Override
	public List<Map<String, String>> retrieveStudentScholarSemCdList(String stdntNo) {
		return mapper.selectStudentScholarSemCdList(stdntNo);
	}

}
