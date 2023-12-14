package kr.ac.usu.staff.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.professor.mapper.StaffProfessorMapper;
import kr.ac.usu.staff.mapper.StaffStaffMapper;
import kr.ac.usu.staff.service.StaffListService;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StaffVO;
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
@Slf4j
public class StaffListServiceImpl implements StaffListService {

private final StaffStaffMapper StaffListDAO;
private final PasswordEncoder encoder;
	
	@Override
	public void retrieveStaffList(PaginationInfo<StaffVO> paging) {
		
		int tatalRecord = StaffListDAO.selectTotalRecord(paging);
		paging.setTotalRecord(tatalRecord);
		List<StaffVO> dataList = StaffListDAO.selectStaffList(paging);
		paging.setDataList(dataList);
	}
	
	@Override
	public StaffVO retrieveStaff(String sklstfNo) {
		StaffVO staff = StaffListDAO.selectStaff(sklstfNo);
		if(staff==null) {
			
		}
		return staff;
	}
	
	@Override
	public int createStaff(StaffVO staff) {
		int res = StaffListDAO.insertStaff(staff);
		log.info("hjvgjhybgjuybgubuj");
		String origin = staff.getSklstfPassword();
		String encoded = encoder.encode(origin);
		staff.setSklstfPassword(encoded);
		res += StaffListDAO.updateStaffPassword(staff);
		return res;
	}
	
	@Override
	public int modifyStaff(StaffVO staff) {
	int res = StaffListDAO.updateStaff(staff);
	return res;
	
	}
}
