package kr.ac.usu.student.service;

import java.util.List;

import kr.ac.usu.student.vo.SchoolRegisterVO;

/**
 * 학생 휴학을 제외한 학적변동 신청 처리 비즈니스 로직 인터페이스
 * @author 김석호
 * @since 2023. 11. 15.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일         수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 15.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public interface StudentRegisterService {
	
	public List<SchoolRegisterVO> retrieveRegisterRequestList(String id);
	public SchoolRegisterVO retrieveRegisterRequest(SchoolRegisterVO register);
	public int creatreRegisterRequest(SchoolRegisterVO register);
	public int removeRegisterRequest(SchoolRegisterVO register);
}
