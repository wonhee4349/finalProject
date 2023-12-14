package kr.ac.usu.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import kr.ac.usu.user.mapper.LoginMapper;
import kr.ac.usu.user.vo.AccessLogVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 시큐리티 로그인 인증 핸들러
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
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	@Inject
	private LoginMapper loginMapper;
	
	public CustomAuthenticationSuccessHandler() {
		super();
		setAlwaysUseDefaultTargetUrl(true);
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		boolean isStudent = authentication.getAuthorities().stream().anyMatch(auth -> "ROLE_STUDENT".equals(auth.getAuthority()));
		boolean isProfessor = authentication.getAuthorities().stream().anyMatch(auth -> "ROLE_PROFESSOR".equals(auth.getAuthority()));
		
		String target = null;
		String name = authentication.getName();
//			log.info("로그인한 유저 인증객체의 getName메소드 반환값 : {}", name);
		AccessLogVO logVO = new AccessLogVO();
		logVO.setId(name);
		String ip = getClientIp(request);
		logVO.setId(name);
		logVO.setAcsIp(ip);
		logVO.setAcsSe("로그인");
		loginMapper.insertAccessLog(logVO);
		
		if(!isStudent && !isProfessor) {
			target = "/staff";
		}else if(isStudent && !isProfessor) {
			target = "/student";
		}else if(!isStudent && isProfessor){
			target = "/professor";
		}
		setDefaultTargetUrl(target);
		
		request.getSession().setMaxInactiveInterval(1800);
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		log.info(ip);
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			log.info(ip);
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			log.info(ip);
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			log.info(ip);
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			log.info(ip);
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			log.info(ip);
		}
		return ip;
	}
}
