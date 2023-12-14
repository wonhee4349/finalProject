package kr.ac.usu.student.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.usu.student.mapper.StudentMypageMapper;
import kr.ac.usu.student.service.StudentMypageService;
import kr.ac.usu.user.mapper.LoginMapper;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.StaffVO;
import kr.ac.usu.user.vo.StudentVO;
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
@RequestMapping("student/mypage") 
public class StudentMypageController {
	
	@Inject
	private LoginMapper logIn;
	
	@Inject
	private StudentMypageService service;
	
	@Inject
	private StudentMypageMapper mapper;
	
	// 마이페이지 UI 불러오는 메소드
	@GetMapping("student/myPageUI")
	public String myPageUI(
		Authentication auth
		, Model model				
	) {
		String stdntNo = auth.getName();
		
		StudentVO studentInfo = service.retrieveStudentInfo(stdntNo);
		StudentVO studentRegister = service.retrieveStudentRegisterStatus(stdntNo);
		List<ComCodeVO> bankSe = mapper.selectComCode("SEC004");
		
		model.addAttribute("studentInfo", studentInfo);
		model.addAttribute("studentRegister", studentRegister);
		model.addAttribute("bankSe", bankSe);
		
		return "student/mypage/mypage";		
	}
	
	// 내정보 수정
	@PostMapping("updateMypage")
	@ResponseBody
	public boolean updateMypage(
		StudentVO stdntNo
	) {
		log.info("33333333333333333 : {}", stdntNo);
		try {
			service.modifyMypage(stdntNo);
			return true;
		}catch (Exception e) {
			log.info("111111111111111111111 : {}", e);
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
		
		StudentVO student = logIn.selectStudent(auth.getName());
		
		String userPass = student.getStdntPassword();
		
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
		StudentVO student = logIn.selectStudent(auth.getName());
		
		String stdntNo = student.getStdntNo();
		
		log.info("{bbbbbbbbbbbbbbbbbbbbbbb : {}}", stdntNo);
		log.info("{bbbbbbbbbbbbbbbbbbbbbbb : {}}", inputPass);
		
		try {
			service.modifyPass(stdntNo, inputPass);
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































