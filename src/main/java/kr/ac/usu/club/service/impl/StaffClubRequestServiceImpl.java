package kr.ac.usu.club.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.club.mapper.StaffClubRequestMapper;
import kr.ac.usu.club.service.StaffClubRequestService;
import kr.ac.usu.club.vo.ClubEstblVO;
import kr.ac.usu.club.vo.ClubVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import lombok.RequiredArgsConstructor;

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
@Service
@RequiredArgsConstructor
public class StaffClubRequestServiceImpl implements StaffClubRequestService{
	
	private final StaffClubRequestMapper clubReqMapper;
	
	// 동아리 신청 리스트
	@Override
	public void retrieveClubRequestList(PaginationInfo<ClubEstblVO> paging) {
		int totalRecord = clubReqMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ClubEstblVO> dataList = clubReqMapper.selectClubRequestList(paging);
		paging.setDataList(dataList);
		
	}

	// 동아리 신청 정보
	@Override
	public ClubEstblVO retrieveClubRequestInfo(String clubEsNo) {
		ClubEstblVO clubRequestInfo = clubReqMapper.selectClubRequestInfo(clubEsNo);
		return clubRequestInfo;
	}

	// 동아리 신청 반려 (동아리 신청 정보 업데이트)
	@Override
	public int modifyRefusedClubRequestInfo(ClubEstblVO clubEsNo) {
		int res = clubReqMapper.updateRefusedClubRequestInfo(clubEsNo);
		return res;
	}

	//동아리 신청 승인 (동아리 신청 정보 업데이트, 인서트)
	@Override
	public int modifyAppliedClubRequestInfo(ClubEstblVO clubEsNo, ClubVO clubNo) {
		clubNo.setClubNo(clubEsNo.getClubEsNo());
		clubNo.setClubNm(clubEsNo.getClubEsNm());
		clubNo.setClubIntrcn(clubEsNo.getClubEsIntrcn());
		
		int res = clubReqMapper.updateAppliedClubRequestInfo(clubEsNo);
		res += clubReqMapper.insertAppliedClubRequest(clubNo);
				
		return res;
	}

}









	