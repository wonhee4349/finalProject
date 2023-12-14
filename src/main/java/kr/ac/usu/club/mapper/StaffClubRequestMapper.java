package kr.ac.usu.club.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.club.vo.ClubEstblVO;
import kr.ac.usu.club.vo.ClubVO;
import kr.ac.usu.paging.vo.PaginationInfo;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 16.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 16.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Mapper
public interface StaffClubRequestMapper {

	// 페이징
	public int selectTotalRecord(PaginationInfo<ClubEstblVO> paging);
	
	// 동아리 신청 리스트
	public List<ClubEstblVO> selectClubRequestList(PaginationInfo<ClubEstblVO> paging);
	
	// 동아리 신청 정보
	public ClubEstblVO selectClubRequestInfo(@Param("clubEsNo") String clubEsNo);
	
	// 동아리 신청 반려 (동아리 신청 정보 업데이트)
	public int updateRefusedClubRequestInfo(ClubEstblVO clubEsNo);
	
	// 동아리 신청 승인 (동아리 신청 정보 업데이트)
	public int updateAppliedClubRequestInfo(ClubEstblVO clubEsNo);
	
	// 동아리 신청 승인 (동아리 목록에 인서트)
	public int insertAppliedClubRequest(ClubVO clubNo);
	
}
