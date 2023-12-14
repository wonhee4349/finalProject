package kr.ac.usu.lecture.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.ac.usu.common.mapper.CommonOptionsMapper;
import kr.ac.usu.user.vo.StudentVO;
import kr.ac.usu.user.vo.wrapper.StudentVOWrapper;

@Controller
public class StudentAttendanceLectureUIController {
	
	@Inject
	private CommonOptionsMapper mapper;
	
	@GetMapping("/student/classApplication")
	public String getStudetnAttendanceLectureUI(
			Authentication auth
			, Model model
		) {
		// 여기서 재학생인지 확인후 넘긴다.
		String viewName = "student/classApp/classApplication";
		StudentVO student = ((StudentVOWrapper)auth.getPrincipal()).getRealUser();
		String sknrgs = student.getSchoolRegisterHistory().getSknrgsSe();
		String messageFormat = "재학생만 사용 가능한 메뉴입니다! [ %s생 ]";
		if(!sknrgs.equals("재학")) {
			model.addAttribute("message", String.format(messageFormat, sknrgs));
			return viewName;
		}
		
		model.addAttribute("complSeList", mapper.getComplSeList());
		model.addAttribute("coursePntList", mapper.getcoursePntList());
		model.addAttribute("dayOfWeekList", mapper.getLectureDayOfWeekList());
		model.addAttribute("dayOfWeekList", mapper.getLectureDayOfWeekList());
		model.addAttribute("dayOfWeekList", mapper.getLectureDayOfWeekList());
		model.addAttribute("subjectList", mapper.getFullSubjectList());
		model.addAttribute("collegeList", mapper.getCollegeList());
		return viewName;
	}
	
	

}
