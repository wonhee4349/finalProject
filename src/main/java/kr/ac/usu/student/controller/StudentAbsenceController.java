package kr.ac.usu.student.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import kr.ac.usu.student.service.StudentAbsenceService;
import kr.ac.usu.student.vo.AbsenceSchoolVO;
import kr.ac.usu.validate.grouphint.InsertGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StudentAbsenceController {
	
	@Inject
	private StudentAbsenceService service;

	// GET 요청 처리 - 단일 학생의 휴학 정보 검색
	@GetMapping("/student/vacRequest/{abssklNo}")
	public String vacRequest(
			Model model,
			@PathVariable(name = "abssklNo") String abssklNo,
			Authentication auth
		) {
		// 새로운 휴학 객체 생성
		AbsenceSchoolVO absence = new AbsenceSchoolVO();
		
		// 경로에서 추출한 휴학 번호와 현재 사용자의 학번 설정
		absence.setAbssklNo(abssklNo);
		absence.setStdntNo(auth.getName());
		
		// 검색된 데이터 모델에 추가
		model.addAttribute("data", service.retrieveAbsence(absence)); 
		
		// JSON으로 응답
		return "jsonView";
	}
	
	// GET 요청 처리 - 학생의 전체 휴학 목록 검색
	@GetMapping("/student/vacRequestList")
	public String studentVacRequsetList(
			Model model,
			Authentication auth
		) {
		// 현재 사용자의 학번을 기반으로 휴학 신청 목록 검색
		model.addAttribute("dataList", service.retrieveAbsenceList(auth.getName()));
		
		// JSON으로 응답
		return "jsonView";
	}
	
	// DELETE 요청 처리 - 학생의 휴학 신청 취소
	@DeleteMapping("/student/vacRequest/{abssklNo}")
	public String cancelVacRequest(
			Model model,
			@PathVariable(name = "abssklNo") String abssklNo,
			Authentication auth
		) {
		// 새로운 휴학 객체 생성
		AbsenceSchoolVO absence = new AbsenceSchoolVO();
		
		// 경로에서 추출한 휴학 번호와 현재 사용자의 학번 설정
		absence.setAbssklNo(abssklNo);
		absence.setStdntNo(auth.getName());
		
		try {
			// 휴학 취소를 시도하고 결과를 모델에 추가
			service.modifyAbsence(absence);
			model.addAttribute("success", true);
			model.addAttribute("message", "휴학신청 취소를 성공했습니다");
		} catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "취소할 수 없습니다");
		}
		
		// JSON으로 응답
		return "jsonView";
	}
	
	// POST 요청 처리 - 학생의 휴학 신청
	@PostMapping("/student/vacRequest")
	public String studentVacRequest(
		Authentication auth,
		@Validated(InsertGroup.class) AbsenceSchoolVO absence,
		Model model
	) {
		// 현재 인증된 사용자의 학번 가져오기
		String authId = auth.getName();
		
		// 학번이 일치하는지 확인
		if(authId.equals(absence.getStdntNo())) {
			try {
				// 휴학 신청을 생성하고 결과를 모델에 추가
				int res = service.createAbsence(absence);
				model.addAttribute("success", true);
				model.addAttribute("message", "신청 완료!");
			} catch (Exception e) {
				model.addAttribute("success", false);
				model.addAttribute("message", "이미 동일학기에 신청 내역이 있습니다.");
			}
		} else {
			model.addAttribute("success", false);
			model.addAttribute("message", "잘못된 요청입니다.");
		}
		
		// 휴학 객체 정보 로깅
		log.info("{}", absence);
		
		// JSON으로 응답
		return "jsonView";
	}
}
