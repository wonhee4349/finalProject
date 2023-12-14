package kr.ac.usu.club.service;

import java.util.List;
import java.util.Map;

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
 * 2023. 11. 24.      김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
public interface StudentClubRequestService {
	

	public List<Map<String, String>> comCodeNm(); 
	
	// 동아리 개설신청 정보
	public ClubEstblVO retrieveClubRequestInfo(String clubEsNo);
		
	public List<Map<String, String>> buldCodeNm();
	
	//동아리 개설 신청
	public int createClubRequest(ClubEstblVO clubRe);
	
	//동아리 개설 신청리스트
	public void retrieveClubRequestList(PaginationInfo<ClubEstblVO> paging);

}
