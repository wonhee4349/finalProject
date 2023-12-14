package kr.ac.usu.common.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.ac.usu.user.vo.wrapper.ProfessorVOWrapper;
import kr.ac.usu.user.vo.wrapper.StaffVOWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그인 처리 구체화를 위한 컨트롤러
 * @author 김석호
 * @since 2023. 12. 07.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 12. 07.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
public class LoginController {

	
	@GetMapping("/login")
	public String loginUIRedirect(
			@RequestParam(required = false) String failure
			, @RequestParam(required = false) String expired
			, @RequestParam(required = false) String invalid
			, RedirectAttributes redi
			, Authentication auth
		) {
		String viewName = null;
		log.info("login으로 들어옴! 인증객체 ROLE : {}");
		if(auth == null || !auth.isAuthenticated()) {
			viewName = "redirect:/loginForm";
		}else if(auth.getPrincipal() instanceof StaffVOWrapper ) {
			viewName = "redirect:/staff";
		}else if (auth.getPrincipal() instanceof ProfessorVOWrapper) {
			viewName = "redirect:/professor";
		}else {
			viewName = "redirect:/student";
		}
		if(StringUtils.isNotBlank(failure) && "true".equals(failure)) {
			log.info("로그인 실패후 들어옴");
			redi.addFlashAttribute("failure", "실패");
		}else if(StringUtils.isNotBlank(expired) && "true".equals(expired)) {
			log.info("세션 만료후 들어옴");
			redi.addFlashAttribute("expired", "만료");
		}else if(StringUtils.isNotBlank(invalid) && "true".equals(invalid)) {
			log.info("세션 invalid로 들어옴");
			redi.addFlashAttribute("invalid", "invalid");
		}
		return viewName;
	}
}
