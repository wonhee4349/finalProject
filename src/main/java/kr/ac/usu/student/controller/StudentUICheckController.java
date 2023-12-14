package kr.ac.usu.student.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.student.mapper.StudentRegisterMapper;
import kr.ac.usu.student.service.StudentRegisterService;
import kr.ac.usu.student.vo.SchoolRegisterVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.mapper.LoginMapper;
import kr.ac.usu.user.vo.StudentVO;
import kr.ac.usu.user.vo.wrapper.StudentVOWrapper;

/**
 * @author 김석호
 * @since 2023. 11. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * 학생 메뉴에서 뷰로 이동하기 전에 해당 뷰를 학생의 상태에 따라 이용여부를 판단하고 분기하기 위해 만든 컨트롤러
 * [[개정이력(Modification Information)]]
 * 수정일         수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 13.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Controller
public class StudentUICheckController {
	@Inject
	private StudentRegisterMapper mapper;
	
	@GetMapping("/student/vacRequest")
	public String vacRequest(
			Authentication auth
			, Model model
		) {
		String viewName = "student/academic/vacRequest";
		StudentVO student = ((StudentVOWrapper)auth.getPrincipal()).getRealUser();
		
		String sknrgs = student.getSchoolRegisterHistory().getSknrgsSe();
		String messageFormat = "%s생은 사용할 수 없는 메뉴입니다.";
		if(!"재학".equals(sknrgs)) {
			model.addAttribute("message", String.format(messageFormat, sknrgs));
			return viewName;
		}
		SchoolRegisterVO register = new SchoolRegisterVO();
		String semCd = LoginMapper.getNextRegularSemesterCode();
		register.setTargetSem(semCd);
		register.setStdntNo(auth.getName());
		int checkDuplicate = mapper.selecteDuplicateRequest(register);
		if(checkDuplicate>0){
			model.addAttribute("message", "한 학기에 하나의 학적변동 신청만 가능합니다.");
		}
		
		return viewName;
	}
	
	@GetMapping("/student/subjectRequest")
	public String subjectRequest(
			Authentication auth
			, Model model
		) {
		String viewName = "student/academic/studentSubjectRequest";
		StudentVO student = ((StudentVOWrapper)auth.getPrincipal()).getRealUser();
		boolean grade = (student.getSknrgSttusGrade().equals("1")? false : true);
		String semCd = LoginMapper.getNextRegularSemesterCode();
		model.addAttribute("semCd", semCd);
		String sknrgs = student.getSchoolRegisterHistory().getSknrgsSe();
		String messageFormat = "%s생은 사용할 수 없는 메뉴입니다.";
		if(!grade) {
			model.addAttribute("message", String.format(messageFormat, "1학년"));
		}else if(!"재학".equals(sknrgs)) {
			model.addAttribute("message", String.format(messageFormat, sknrgs));
		}else {
			SchoolRegisterVO register = new SchoolRegisterVO();
			register.setTargetSem(semCd);
			register.setStdntNo(auth.getName());
			int res = mapper.selecteDuplicateRequest(register);
			if(res>0) {
				model.addAttribute("message", "한 학기에 하나의 학적변동 신청만 가능합니다.");
			}else {
				List<SubjectVO> subjectList = mapper.selectRequestableSubject(student);
				model.addAttribute("subjectList", subjectList);
				List<CollegeVO> collegeList = mapper.selectCollegeList();
				model.addAttribute("collegeList", collegeList);
			}
		}
		return viewName;
	}
	
	@GetMapping("/student/backRequest")
	public String backRequest(
			Authentication auth
			, Model model
		) {
		StudentVO student = ((StudentVOWrapper)auth.getPrincipal()).getRealUser();
		String messageFormat = "%s생은 사용할 수 없는 메뉴입니다.";
		String sknrgs = student.getSchoolRegisterHistory().getSknrgsSe();
		String semCd = LoginMapper.getNextRegularSemesterCode();
		model.addAttribute("semCd", semCd);
		if(!sknrgs.equals("휴학")) {
			model.addAttribute("message", String.format(messageFormat, sknrgs));
		}else {
			SchoolRegisterVO register = new SchoolRegisterVO();
			register.setTargetSem(semCd);
			register.setStdntNo(auth.getName());
			int res = mapper.selecteDuplicateRequest(register);
			if(res>0) {
				model.addAttribute("message", "한 학기에 하나의 학적변동 신청만 가능합니다.");
			}
		}
		return "student/academic/backRequest";
	}
	
	
	
	
	
	
	
}
