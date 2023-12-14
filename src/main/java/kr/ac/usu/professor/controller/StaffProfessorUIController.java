package kr.ac.usu.professor.controller;
import java.util.List;

import javax.inject.Inject;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 7.      작성자명       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.ac.usu.professor.mapper.StaffProfessorMapper;
import kr.ac.usu.professor.service.ProfessorListService;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;

@Controller
public class StaffProfessorUIController {

	@Inject
	private StaffProfessorMapper mapper;
	
	@Inject
	private ProfessorListService service;
	
	@GetMapping("/staff/professorListUI")
	public String professorView(Model model) {
		
		List<SubjectVO> subject = mapper.selectSubjectList();
		List<ComCodeVO> nltySe = mapper.selectComCode("SEC002");
		List<ComCodeVO> genderSe = mapper.selectComCode("SEC001");
		List<ComCodeVO> emplySe = mapper.selectComCode("SEC003");
		
		model.addAttribute("subject",subject);
		model.addAttribute("nltySe",nltySe);
		model.addAttribute("genderSe",genderSe);
		model.addAttribute("emplySe",emplySe);
		
		return "staff/professor/professorListUI";
	}
	
	
	
	
	@GetMapping("/staff/professor/staffProfessorInsertUI")
	public String staffProfessorInsertUI(
			Model model
		) {
		
		List<ComCodeVO> sexdstn = mapper.selectComCode("SEC001");
		List<ComCodeVO> nlty = mapper.selectComCode("SEC002");
		List<ComCodeVO> emplmnForm = mapper.selectComCode("SEC003");
		List<SubjectVO> subject = mapper.selectSubjectList(); 
		
		model.addAttribute("sexdstn", sexdstn);
		model.addAttribute("nlty", nlty);
		model.addAttribute("emplmnForm", emplmnForm);
		model.addAttribute("subject", subject);
		
		return "staff/professor/staffProfessorInsertUI";
	}
	
	@GetMapping("/ajax/staff/professor/professorView")
	public String staffProfessorUpdateView(
			Model model
		) {
		
		List<ComCodeVO> sexdstn = mapper.selectComCode("SEC001");
		List<ComCodeVO> nlty = mapper.selectComCode("SEC002");
		List<ComCodeVO> emplmnForm = mapper.selectComCode("SEC003");
		List<SubjectVO> subject = mapper.selectSubjectList(); 
		
		model.addAttribute("sexdstn", sexdstn);
		model.addAttribute("nlty", nlty);
		model.addAttribute("emplmnForm", emplmnForm);
		model.addAttribute("subject", subject);
		
		return "/ajax/staff/professor/professorView";
	
	}
	
}
/*

고용형태 emplmnForm
국적 nlty
성별 sexdstn

학과코드 subject

SEC001	성별
SEC002	국가
SEC003	고용형태




*/