package kr.ac.usu.staff.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.staff.service.StaffMypageService;
import kr.ac.usu.user.mapper.LoginMapper;
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
@RequestMapping("staff/mypage") 
public class StaffMypageController {
	
	@Inject
	private LoginMapper logIn;
	
	@Inject
	private StaffMypageService service;
	
	// 마이페이지 UI 불러오는 메소드
	@GetMapping("staff/myPageUI")
	public String myPageUI(
		Authentication auth
		, Model model
	) {
		String sklstfNo = auth.getName();
		
		StaffVO staffInfo = service.retrieveStaffInfo(sklstfNo);
		
		model.addAttribute("staffInfo", staffInfo);
		
		return "staff/mypage/staffMypage";
	}
	
	// 내정보 수정
	@PostMapping("updateMypage")
	@ResponseBody
	public boolean updateMypage(
		StaffVO sklstfNo
	) {			
		try {
			service.modifyMypage(sklstfNo);
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
		StaffVO staff = logIn.selectStaff(auth.getName());
		
		String userPass = staff.getSklstfPassword();
		
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
		StaffVO staff = logIn.selectStaff(auth.getName());
		
		String sklstfNo = staff.getSklstfNo();
		
		log.info("{bbbbbbbbbbbbbbbbbbbbbbb : {}}", sklstfNo);
		log.info("{bbbbbbbbbbbbbbbbbbbbbbb : {}}", inputPass);
		
		try {
			service.modifyPass(sklstfNo, inputPass);
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




























