package kr.ac.usu.professor.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.club.vo.ClubVO;
import kr.ac.usu.facilities.vo.FacilitiesVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import kr.ac.usu.professor.mapper.StaffProfessorMapper;
import kr.ac.usu.professor.service.ProfessorListService;
import kr.ac.usu.student.vo.AbsenceSchoolVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.validate.grouphint.InsertGroup;

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
@Controller
@RequestMapping("/staff/professor")
public class StaffProfessorListController {

	@Inject
	private ProfessorListService service;
	
	@Inject
	private StaffProfessorMapper mapper;
	
	@GetMapping("ajax/professorList")
	public String listData(
		@ModelAttribute("detailCondition") ProfessorVO detailCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		
		SubjectVO subject = new SubjectVO();
		detailCondition.setSubject(subject);
		
		
		PaginationInfo<ProfessorVO> paging = new PaginationInfo<>(10,5);
		paging.setDetailCondition(detailCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveProfessorList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("ajax/professorView")
	public String memberView(
		@RequestParam("who") String prfsorNo
		, Model model
	){
		ProfessorVO professor = service.retrieveProfessor(prfsorNo);
		List<ComCodeVO> sexdstn = mapper.selectComCode("SEC001");
		List<ComCodeVO> nlty = mapper.selectComCode("SEC002");
		List<ComCodeVO> emplmnForm = mapper.selectComCode("SEC003");
		List<SubjectVO> subject = mapper.selectSubjectList(); 
		
		model.addAttribute("sexdstn", sexdstn);
		model.addAttribute("nlty", nlty);
		model.addAttribute("emplmnForm", emplmnForm);
		model.addAttribute("subject", subject);
		model.addAttribute("professor", professor);
		
		return "/ajax/staff/professor/professorView";
	}
	@PostMapping("/staffProfessorInsertData")
	public String staffProfessorData(
		ProfessorVO prof
		, Model model
	) {
		try {
			int res = service.createProfessor(prof);
			model.addAttribute("success", true);
			model.addAttribute("message", "성공하였습니다");
		} catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "실패하였습니다");
		}
		return "jsonView";
	}
	
	@PostMapping("/staffProfessorUpdateData")
	public String staffProfessorUpdateData(
		ProfessorVO prof
		, Model model
	) {
		try {
			int res = service.modifyProfessor(prof);
			model.addAttribute("success", true);
			model.addAttribute("message", "수정되었습니다");
		} catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "실패하였습니다");
		}
		return "jsonView";
	}
	
}
