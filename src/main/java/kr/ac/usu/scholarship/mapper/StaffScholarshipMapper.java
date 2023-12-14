package kr.ac.usu.scholarship.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.scholarship.vo.ScholarshipListVO;
import kr.ac.usu.scholarship.vo.ScholarshipVO;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StaffScholarshipMapper {
	
	// 학기별 장학금 페이징
	public int selectTotalRecord(PaginationInfo<ScholarshipVO> paging);
	
	// 총장학금 페이징
	public int selectTotalScholarshipRecord(PaginationInfo<ScholarshipListVO> paging); 
	
	// 장학금 리스트
	public List<ScholarshipVO> selectScholarshipList(PaginationInfo<ScholarshipVO> paging);

	// 장학금 정보
	public ScholarshipVO selectScholarshipInfo(@Param("schlshipNo") String schlshipNo);
	
	// 총장학금 리스트
	public List<ScholarshipListVO> selectTotalScholarshipList(PaginationInfo<ScholarshipListVO> paging);
	
	// 새로운 장학금 인서트
	public int insertTotalScholarship(ScholarshipListVO schoNm);
	
	// 장학금 추가 장학금 셀렉트박스 불러오기
	public List<ScholarshipListVO> selectInsertScholarshipList();
	
	// 학기별 장학금 추가
	public int insertScholarship(ScholarshipVO scholarship);
	
	// 장학금 목록 가져오기
	public List<ScholarshipListVO> selectScholarshipList();
}
