/*
 * package kr.ac.usu.club.service.impl;
 * 
 * import java.util.List;
 * 
 * import org.springframework.stereotype.Service;
 * 
 * import kr.ac.usu.club.mapper.StaffClubMapper; import
 * kr.ac.usu.club.mapper.StudentClubMapper; import
 * kr.ac.usu.club.service.ClubService; import kr.ac.usu.club.vo.ClubSubscribeVO;
 * import kr.ac.usu.club.vo.ClubVO; import kr.ac.usu.paging.vo.PaginationInfo;
 * import lombok.RequiredArgsConstructor;
 * 
 *//**
	 * 
	 * @author PC-25
	 *
	 * @author 이재혁
	 * @since 2023. 11. 7.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * 
	 *      <pre>
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2023. 11. 7.      작성자명       최초작성
	 * Copyright (c) 2023 by DDIT All right reserved
	 *      </pre>
	 *//*
		 * @Service
		 * 
		 * @RequiredArgsConstructor public class ClubServiceImpl implements ClubService{
		 * 
		 * private final StaffClubMapper clubDAO; private final StudentClubMapper
		 * clubsubDAO;
		 * 
		 * @Override public void retrieveClubList(PaginationInfo<ClubVO> paging) {
		 * 
		 * int totalRecord = clubsubDAO.selectTotalRecord(paging);
		 * paging.setTotalRecord(totalRecord); List<ClubVO> dataList =
		 * clubDAO.selectClubList(paging); paging.setDataList(dataList);
		 * 
		 * }
		 * 
		 * @Override public ClubSubscribeVO selectClubSubscribe(ClubSubscribeVO csvo) {
		 * return (ClubSubscribeVO) clubsubDAO.selectClubSubscribe(csvo); }
		 * 
		 * @Override public int insertClubSubscribe(ClubSubscribeVO clubSubscribe) {
		 * 
		 * int totalRecord = clubsubDAO.insertClubSubscribe(clubSubscribe);
		 * 
		 * return totalRecord;
		 * 
		 * }
		 * 
		 * }
		 */