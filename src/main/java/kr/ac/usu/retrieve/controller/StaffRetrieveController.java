package kr.ac.usu.retrieve.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.usu.retrieve.mapper.StaffRetrieveMapper;

@Controller
@RequestMapping("/staff/retrieve")
public class StaffRetrieveController {
	
	@Inject
	private StaffRetrieveMapper mapper;
	
	@ModelAttribute("retrievePage")
	public String retrievePageModel() {
		return "true";
	}
	
	
	
	@GetMapping("absenceStudentList")
	public String absenceStudentDataUI() {
		return "staff/retrieve/absenceStudent";
	}
	@GetMapping("studentListOnSubject")
	public String studentDataUI() {
		return "staff/retrieve/studentListOnSubject";
	}
	@GetMapping("ajax/studentListOnSubject")
	public String dataListForChart(
			Model model
		) {
		model.addAttribute("subjectPeople", mapper.selectSubjectGradePeopleCount());
		return "jsonView";
	}
	
}
