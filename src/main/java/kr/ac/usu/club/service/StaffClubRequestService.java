package kr.ac.usu.club.service;

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
public interface StaffClubRequestService {
	
	// 동아리 신청 리스트
	public void retrieveClubRequestList(PaginationInfo<ClubEstblVO> paging);
	
	// 동아리 신청 정보
	public ClubEstblVO retrieveClubRequestInfo(String clubEsNo);
	
	// 동아리 신청 반려 (동아리 신청 정보 업데이트)
	public int modifyRefusedClubRequestInfo(ClubEstblVO clubEsNo);
	
	// 동아리 신청 승인 (동아리 신청 정보 업데이트)
	public int modifyAppliedClubRequestInfo(ClubEstblVO clubEsNo, ClubVO clubNo);

}
