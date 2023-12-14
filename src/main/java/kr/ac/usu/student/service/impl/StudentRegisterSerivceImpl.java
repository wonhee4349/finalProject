package kr.ac.usu.student.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.ac.usu.student.mapper.StudentRegisterMapper;
import kr.ac.usu.student.service.StudentRegisterService;
import kr.ac.usu.student.vo.SchoolRegisterVO;

/**
 * 학생 휴학신청을 제외한 학적변동 신청 처리 서비스로직 구현체
 * @author 김석호
 * @since 2023. 11. 15.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일  		      수정자       수정내용
 * --------          --------    ----------------------
 * 2023. 11. 15.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Service
public class StudentRegisterSerivceImpl implements StudentRegisterService {

	@Inject
	private StudentRegisterMapper mapper;
	
	@Override
	public List<SchoolRegisterVO> retrieveRegisterRequestList(String id) {
		return mapper.selectRegisterRequestList(id);
	}

	@Override
	public SchoolRegisterVO retrieveRegisterRequest(SchoolRegisterVO register) {
		return mapper.selectRegisterRequest(register);
	}

	@Override
	public int creatreRegisterRequest(SchoolRegisterVO register) {
		return mapper.insertRegisterRequest(register);
	}

	@Override
	public int removeRegisterRequest(SchoolRegisterVO register) {
		return mapper.deleteRegisterRequest(register);
	}

}
