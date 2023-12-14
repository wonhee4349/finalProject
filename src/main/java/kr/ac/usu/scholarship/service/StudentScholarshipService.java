package kr.ac.usu.scholarship.service;

import java.util.List;
import java.util.Map;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.scholarship.vo.ScholarshipRequestVO;
import kr.ac.usu.scholarship.vo.ScholarshipStudentVO;
import kr.ac.usu.scholarship.vo.ScholarshipVO;

public interface StudentScholarshipService {
	public void retrieveScholarshipList(PaginationInfo<ScholarshipVO> paging);

	public ScholarshipVO retrieveScholarship(String schlshipNo);
	
	public ServiceResult createStudentScholarship(ScholarshipRequestVO scholarshipRequest);
	
	public List<ScholarshipRequestVO> retrieveStudentRequestScholarshipList(String id, String semCd);
	
	public List<ScholarshipStudentVO> retrieveStudentScholarList(String id, String semCd);
	
	public List<Map<String,String>> retrieveStudentScholarSemCdList(String stdntNo);
}
