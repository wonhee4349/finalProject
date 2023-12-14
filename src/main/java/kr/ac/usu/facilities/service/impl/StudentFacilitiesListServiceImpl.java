package kr.ac.usu.facilities.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.facilities.mapper.StaffFacilitiesListMapper;
import kr.ac.usu.facilities.mapper.StudentFacilitiesListMapper;
import kr.ac.usu.facilities.mapper.StudentFacilitiesRequestMapper;
import kr.ac.usu.facilities.service.StudentFacilitiesListService;
import kr.ac.usu.facilities.service.StudentFacilitiesRequestService;
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
public class StudentFacilitiesListServiceImpl implements StudentFacilitiesListService{
	private final StudentFacilitiesListMapper FacilitiesListDAO;

@Override
public void retrieveFacilitiesList(PaginationInfo<FacilitiesVO> paging) {
		
		int tatalRecord = FacilitiesListDAO.selectTotalRecord(paging);
		paging.setTotalRecord(tatalRecord);
		List<FacilitiesVO> dataList = FacilitiesListDAO.selectFacilitiesList(paging);
		paging.setDataList(dataList);
	}
	
	@Override
	public FacilitiesVO retrieveFacilities(String fcltsNo) {
		FacilitiesVO facilities = FacilitiesListDAO.selectFacilities(fcltsNo);
		if(facilities==null) {
			
		}
		return facilities;
	}
	
	@Override
	public int createFacilities(FacilitiesVO facilities) {
		int res = FacilitiesListDAO.insertFacilities(facilities);
		return res;
	}
	
	@Override
	public int modifyFacilities(FacilitiesVO facilities) {
	int res = FacilitiesListDAO.updateFacilities(facilities);
	return res;
	
	}
	

	
}
