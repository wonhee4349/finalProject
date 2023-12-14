package kr.ac.usu.scholarship.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.scholarship.vo.ScholarshipRequestVO;
import kr.ac.usu.scholarship.vo.ScholarshipStudentVO;
import kr.ac.usu.scholarship.vo.ScholarshipVO;

/**
 * 학생 장학금 기능 Mapper
 * @author 김석호
 * @since 2023. 11. 21.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 11. 21.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StudentScholarshipMapper {
	
	public int selectselectScholarshipTotalRecord(PaginationInfo<ScholarshipVO> paging);
	public List<ScholarshipVO> selectScholarshipList(PaginationInfo<ScholarshipVO> paging);

	public ScholarshipVO selectScholarship(@Param("schlshipNo") String schlshipNo);
	
	public int insertStudentScholarship(ScholarshipRequestVO scholarshipRequest);
	
	
	public int insertStudentScholarshipAttatchFile(AttatchingFileVO attatch);
	
	/**
	 * 학번 key : id
	 * 학기코드 key : semCd
	 * @param paramMap
	 * @return
	 */
	public List<ScholarshipRequestVO> selectStudentRequestScholarshipList(Map<String, String> paramMap);
	
	/**
	 * 학번 key : id
	 * 학기코드 key : semCd
	 * @param paramMap
	 * @return
	 */
	public List<ScholarshipStudentVO> selectStudentScholarList(Map<String, String> paramMap);
	
	public List<Map<String,String>> selectStudentScholarSemCdList(@Param("stdntNo") String stdntNo );
	
	
}
