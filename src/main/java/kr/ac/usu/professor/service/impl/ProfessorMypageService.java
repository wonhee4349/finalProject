package kr.ac.usu.professor.service.impl;

import kr.ac.usu.user.vo.ProfessorVO;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 29.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 29.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
public interface ProfessorMypageService {
	
	// 교수 인적 정보
	public ProfessorVO retrieveProfessorInfo(String prfsorNo);

	// 내정보 수정
	public int modifyMypage(ProfessorVO prfsorNo) throws Exception;
	
	// 비밀번호 일치 확인
	public boolean checkPassword(String realPass, String userPass);
	
	// 비밀번호 수정
	public int modifyPass(String prfsorNo, String checkPass);

}
