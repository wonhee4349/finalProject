package kr.ac.usu.user.vo.wrapper;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import kr.ac.usu.user.vo.ProfessorVO;

/**
 * 시큐리티 로그인 인증객체용 랩퍼
 * 
 * @author 김석호
 * @since 2023. 11. 9.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일         수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public class ProfessorVOWrapper extends User {

	private ProfessorVO realUser;

	public ProfessorVOWrapper(ProfessorVO realUser) {
		super(realUser.getPrfsorNo(), realUser.getPrfsorPassword(), AuthorityUtils.createAuthorityList(realUser.getUserRole()));
		this.realUser = realUser;
	}
	
	public ProfessorVO getRealUser() {
		return realUser;
	}
	
}
