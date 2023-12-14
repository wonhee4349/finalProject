package kr.ac.usu.facilities.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.club.vo.ClubEstblVO;
import kr.ac.usu.facilities.mapper.StudentFacilitiesListMapper;
import kr.ac.usu.facilities.mapper.StudentFacilitiesRequestMapper;
import kr.ac.usu.facilities.service.StudentFacilitiesListService;
import kr.ac.usu.facilities.service.StudentFacilitiesRequestService;
import kr.ac.usu.facilities.vo.FacilitiesRequestVO;
import kr.ac.usu.facilities.vo.FacilitiesVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.facilities.mapper.StudentFacilitiesListMapper;

import kr.ac.usu.user.vo.StudentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@Service
@RequiredArgsConstructor
public class StudentFacilitiesRequestServiceImpl implements StudentFacilitiesRequestService{
	
	private final StudentFacilitiesRequestMapper reqMapper ;
	
	


	@Override
	public int createFacilitiesRequest(FacilitiesRequestVO facilit) {
		int res = reqMapper.createFacilitiesRequest(facilit);
				return res;
	}

	@Override
	public void retrieveFacilitiesRequestList(PaginationInfo<FacilitiesRequestVO> paging) {
		int totalRecord = reqMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<FacilitiesRequestVO> dataList = reqMapper.selectFacilitiesRequestList(paging);
		
		
	}

}
