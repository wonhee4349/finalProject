package kr.ac.usu.professor.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.usu.facilities.vo.FacilitiesVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.professor.mapper.StaffProfessorMapper;
import kr.ac.usu.professor.service.ProfessorListService;
import kr.ac.usu.student.vo.AbsenceSchoolVO;
import kr.ac.usu.user.mapper.LoginMapper;
import kr.ac.usu.user.vo.ProfessorVO;
import lombok.RequiredArgsConstructor;

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
 * 2023. 11. 14.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class ProfessorListServiceImpl implements ProfessorListService{
	
	private final StaffProfessorMapper ProfessorListDAO;
	private final PasswordEncoder encoder;
	
	@Override
	public void retrieveProfessorList(PaginationInfo<ProfessorVO> paging) {
		
		int tatalRecord = ProfessorListDAO.selectTotalRecord(paging);
		paging.setTotalRecord(tatalRecord);
		List<ProfessorVO> dataList = ProfessorListDAO.selectProfessorList(paging);
		paging.setDataList(dataList);
	}
	
	@Override
	public ProfessorVO retrieveProfessor(String prfsorNo) {
		ProfessorVO professor = ProfessorListDAO.selectProfessor(prfsorNo);
		if(professor==null) {
			
		}
		return professor;
	}
	
	@Override
	@Transactional
	public int createProfessor(ProfessorVO professor) {
		int res = ProfessorListDAO.insertProfessor(professor);
		String origin = professor.getPrfsorPassword();
		String encoded = encoder.encode(origin);
		professor.setPrfsorPassword(encoded);
		res += ProfessorListDAO.updateProfessorPassword(professor);
		return res;
	}
	
	@Override
	public int modifyProfessor(ProfessorVO professor) {
	int res = ProfessorListDAO.updateProfessor(professor);
	return res;
	
	}
}
