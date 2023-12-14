package kr.ac.usu.club.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.club.mapper.StudentClubMapper;

import kr.ac.usu.club.service.StudentClubService;
import kr.ac.usu.club.vo.ClubSubscribeVO;
import kr.ac.usu.club.vo.ClubVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author PC-25
 *
 * @author 김원희
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
@Service
@RequiredArgsConstructor
public class StudentClubServiceImpl implements StudentClubService{

	private final StudentClubMapper clubDAO;
	
	// 동아리 리스트
		@Override
		public void retrieveClubList(PaginationInfo<ClubVO> paging) {

			int totalRecord = clubDAO.selectTotalRecord(paging);
			paging.setTotalRecord(totalRecord);
			List<ClubVO> dataList = clubDAO.selectClubList(paging);
			paging.setDataList(dataList);
			
		}

		// 동아리 정보
		@Override
		public ClubVO selectClubInfo(String clubNo) {
			ClubVO clubInfo = clubDAO.selectClubInfo(clubNo);
			if(clubInfo==null) {
				
			}
			return clubInfo;
		}

		// 동아리 회원 정보
		@Override
		public List<ClubVO> retrieveClubMember(String clubNo) {
			List<ClubVO> clubMember = clubDAO.selectClubMember(clubNo);
			if(clubMember==null) {
				
			}
			return clubMember;
		}

		@Override
		public ClubSubscribeVO selectClubSubscribe(ClubSubscribeVO csvo) {
			// TODO Auto-generated method stub
			return null;
		}
		

}
