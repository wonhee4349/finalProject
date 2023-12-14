package kr.ac.usu.club.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.club.vo.ClubEstblVO;
import kr.ac.usu.club.vo.ClubVO;
import kr.ac.usu.paging.vo.PaginationInfo;

/**
 * <pre>
 * 
 * </pre>
 * @author 김원희
 * @since 2023. 11. 23.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 23.      김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Mapper
public interface StudentClubRequestMapper {


	// 페이징
	public int selectTotalRecord(PaginationInfo<ClubEstblVO> paging);
	
	// 동아리 신청 리스트
	public List<ClubEstblVO> selectClubRequestList(PaginationInfo<ClubEstblVO> paging);
	
	
	
	// 동아리 구분 리스트
	public List<Map<String, String>> comCodeNm(); 

	// 동아리실 위치 건물 선택
	public List<Map<String, String>> buldCodeNm(); 


	// 시설물
	public List<Map<String, String>> getFacilitiesByBuilding(); 
	
	
	//동아리 개설 신청
	public int createClubRequest(ClubEstblVO clubRe);
	
	
	// 동아리 개설신청 정보
	public ClubEstblVO selectClubRequestInfo(@Param("clubEsNo") String clubEsNo);

	
}
