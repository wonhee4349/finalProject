package kr.ac.usu.certificate.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.certificate.service.StudentCertificateService;

import kr.ac.usu.user.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/student/certificate")
public class StudentCertificateController {

	
	
	@Inject
	private StudentCertificateService service;

	// 증명서 출력 UI 불러오기
	@GetMapping("/certificateDown")
	public String certiticateView() {
		log.info("여기가 목록");
		
		return "student/certificate/certificateDown";
	}
	
	
	@ResponseBody
	@GetMapping("ajax/certificateDown")
	public Map<String, String> memberView(Authentication auth ){
		
		Map<String, String> studentRegister = service.retrieveStudentCertificate(auth.getName());		// 학생 학적 정보
		
		return studentRegister;
	}
	


	
	

}
