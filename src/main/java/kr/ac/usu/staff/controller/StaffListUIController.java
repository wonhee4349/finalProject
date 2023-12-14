package kr.ac.usu.staff.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.ac.usu.professor.mapper.StaffProfessorMapper;
import kr.ac.usu.staff.mapper.StaffStaffMapper;
import kr.ac.usu.staff.service.StaffListService;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 11.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

@Controller
public class StaffListUIController {
	
	@Inject
	private StaffListService service;
	
	@Inject
	private StaffStaffMapper mapper;
	
	@GetMapping("/staff/staffListUI")
	public String professorView(Model model) {
		
		List<ComCodeVO> nltySe = mapper.selectComCode("SEC002");
		List<ComCodeVO> genderSe = mapper.selectComCode("SEC001");
		List<ComCodeVO> emplySe = mapper.selectComCode("SEC003");
		
		model.addAttribute("nltySe",nltySe);
		model.addAttribute("genderSe",genderSe);
		model.addAttribute("emplySe",emplySe);
		
		return "staff/staff/staffListUI";
	}
	
	@GetMapping("/staff/staff/staffStaffInsertUI")
	public String staffStaffInsertUI(
			Model model
		) {
		
		List<ComCodeVO> sexdstn = mapper.selectComCode("SEC001");
		List<ComCodeVO> nlty = mapper.selectComCode("SEC002");
		List<ComCodeVO> emplmnForm = mapper.selectComCode("SEC003");
		
		model.addAttribute("sexdstn", sexdstn);
		model.addAttribute("nlty", nlty);
		model.addAttribute("emplmnForm", emplmnForm);
		
		return "staff/staff/staffStaffInsertUI";
	}
	
	@GetMapping("/ajax/staff/staff/staffView")
	public String staffStaffUpdateView( 
			Model model
		) {
		
		List<ComCodeVO> sexdstn = mapper.selectComCode("SEC001");
		List<ComCodeVO> nlty = mapper.selectComCode("SEC002");
		List<ComCodeVO> emplmnForm = mapper.selectComCode("SEC003");
		
		model.addAttribute("sexdstn", sexdstn);
		model.addAttribute("nlty", nlty);
		model.addAttribute("emplmnForm", emplmnForm);
		
		return "/ajax/staff/staff/staffView";
	
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

