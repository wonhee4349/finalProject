package kr.ac.usu.professor.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.professor.mapper.ProfessorMypageMapper;
import kr.ac.usu.professor.service.impl.ProfessorMypageService;
import kr.ac.usu.user.mapper.LoginMapper;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StaffVO;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Controller
@RequestMapping("professor/mypage") 
public class ProfessorMypageController {
	
	@Inject
	private LoginMapper logIn;
	
	@Inject
	private ProfessorMypageService service;
	
	@Inject
	private ProfessorMypageMapper mapper;
	
	// 마이페이지 UI 불러오는 메소드
	@GetMapping("professor/myPageUI")
	public String myPageUI(
		Authentication auth
		, Model model
	) {
		String prfsorNo = auth.getName();
		
		ProfessorVO professorInfo = service.retrieveProfessorInfo(prfsorNo);
		List<ComCodeVO> bankSe = mapper.selectComCode("SEC004");
		
		model.addAttribute("professorInfo", professorInfo);
		model.addAttribute("bankSe", bankSe);
		
		return "professor/mypage/professorMypage";
	}
	
	// 내정보 수정
	@PostMapping("updateMypage")
	@ResponseBody
	public boolean updateMypage(
		ProfessorVO prfsorNo
	) {
		
		try {
			service.modifyMypage(prfsorNo);
			return true;
		}catch (Exception e) {
			return false;
		}

	}
	
	// 비밀번호 확인
	@ResponseBody
	@PostMapping("/checkPass")
    public boolean checkPass(
    	Authentication auth
    	, String realPass
    	, Model model
    ){
		ProfessorVO professor = logIn.selectProfessor(auth.getName());
		
		String userPass = professor.getPrfsorPassword();
		
		boolean res = service.checkPassword(realPass, userPass);
		
		return res;
		
    }
	
	// 비밀번호 변경
	@PostMapping("/updatePass")
	public String updatePass(
		Authentication auth
		, String inputPass
		, Model model		
	) {
		ProfessorVO professor = logIn.selectProfessor(auth.getName());
		
		String prfsorNo = professor.getPrfsorNo();
		
		log.info("{bbbbbbbbbbbbbbbbbbbbbbb : {}}", prfsorNo);
		log.info("{bbbbbbbbbbbbbbbbbbbbbbb : {}}", inputPass);
		
		try {
			service.modifyPass(prfsorNo, inputPass);
			model.addAttribute("success", true);
			model.addAttribute("message", "등록 완료!");
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("success", false);
			model.addAttribute("message", "등록 실패!");
		}
		return "jsonView";
		
	}

}
































