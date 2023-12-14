package kr.ac.usu.club.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.ac.usu.club.mapper.StaffClubRequestMapper;
import kr.ac.usu.club.mapper.StudentClubRequestMapper;
import kr.ac.usu.club.service.StaffClubRequestService;
import kr.ac.usu.club.service.StudentClubRequestService;
import kr.ac.usu.club.vo.ClubEstblVO;
import kr.ac.usu.club.vo.ClubVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 
 * </pre>
 * @author 김원희
 * @since 2023. 11. 16.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 24.      김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Service
@RequiredArgsConstructor
public class StudentClubRequestServiceImpl implements StudentClubRequestService{
	
	private final StudentClubRequestMapper clubReqMapper;

	
	//동아리 개설하기
	@Override
	public int createClubRequest(ClubEstblVO clubRe) {
		int res= clubReqMapper.createClubRequest(clubRe);
		return res;
	}

	@Override
	public List<Map<String, String>> comCodeNm() {
		return clubReqMapper.comCodeNm();
	}

	@Override
	public List<Map<String, String>> buldCodeNm() {
		return clubReqMapper.buldCodeNm();
	}

	// 동아리 신청 리스트
	@Override
	public void retrieveClubRequestList(PaginationInfo<ClubEstblVO> paging) {
		int totalRecord = clubReqMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ClubEstblVO> dataList = clubReqMapper.selectClubRequestList(paging);
		paging.setDataList(dataList);
		
	}

	@Override
	public ClubEstblVO retrieveClubRequestInfo(String clubEsNo) {
		ClubEstblVO clubRequestInfo = clubReqMapper.selectClubRequestInfo(clubEsNo);
		return clubRequestInfo;
		
	}

}









	