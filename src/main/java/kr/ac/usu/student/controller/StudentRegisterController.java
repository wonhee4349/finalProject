package kr.ac.usu.student.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.student.service.StudentRegisterService;
import kr.ac.usu.student.vo.SchoolRegisterVO;

@Controller
@RequestMapping("/student/ajax/register")
public class StudentRegisterController {

	@Inject
	private StudentRegisterService service;
	
	@GetMapping("/{sknrgNo}")
	public String studentRegisterInformation(
			@PathVariable String sknrgNo
			, Authentication auth
			, Model model
		) {
		SchoolRegisterVO register = new SchoolRegisterVO();
		register.setSknrgNo(sknrgNo);
		register.setStdntNo(auth.getName());
		register =service.retrieveRegisterRequest(register);
		model.addAttribute("register", register);
		
		return "jsonView";
	}
	
	@GetMapping
	public String studentRegisterList(
			Authentication auth
			, Model model
		) {
		model.addAttribute("dataList", service.retrieveRegisterRequestList(auth.getName()));
		return "jsonView";
	}
	
	@PostMapping
	public String studentRegister(
			SchoolRegisterVO register
			, Authentication auth
			, Model model
		) {
		register.setStdntNo(auth.getName());
		try {
			service.creatreRegisterRequest(register);
			model.addAttribute("success", true);
			model.addAttribute("message", "신청 성공했습니다.");
		} catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "신청 실패했습니다.");
		}
		
		return "jsonView";
	}
	
	@DeleteMapping("/{sknrgNo}")
	public String studentRegisterCancel(
			@PathVariable String sknrgNo
			, Authentication auth
			, Model model
		) {
		SchoolRegisterVO register = new SchoolRegisterVO();
		register.setSknrgNo(sknrgNo);
		register.setStdntNo(auth.getName());
		
		int res = service.removeRegisterRequest(register);
		if(res>0) {
			model.addAttribute("success", true);
			model.addAttribute("message", "신청 취소에 성공했습니다.");
		}else {
			model.addAttribute("success", false);
			model.addAttribute("message", "신청 취소에 실패했습니다.");
		}
		
		return "jsonView";
	}
	
}
