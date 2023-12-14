package kr.ac.usu.club.service;

import java.util.List;
import kr.ac.usu.club.vo.ClubSubscribeVO;
import kr.ac.usu.club.vo.ClubVO;
import kr.ac.usu.paging.vo.PaginationInfo;

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
 * 2023. 11. 7.      작성자명       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public interface StaffClubService {
	
	// 동아리 리스트
	public void retrieveClubList(PaginationInfo<ClubVO> paging);
	public ClubSubscribeVO selectClubSubscribe(ClubSubscribeVO csvo);
	

	// 동아리 정보
	public ClubVO retrieveClubInfo(String clubNo);
	
	// 동아리 회원 정보
	public List<ClubVO> retrieveClubMember(String clubNo);
}
