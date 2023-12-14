package kr.ac.usu.facilities.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.club.vo.ClubEstblVO;
import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.facilities.mapper.StaffFacilitiesListMapper;
import kr.ac.usu.facilities.mapper.StudentFacilitiesListMapper;
import kr.ac.usu.facilities.mapper.StudentFacilitiesRequestMapper;
import kr.ac.usu.facilities.service.FacilitiesListService;
import kr.ac.usu.facilities.service.StudentFacilitiesRequestService;
import kr.ac.usu.facilities.vo.BuildingVO;
import kr.ac.usu.facilities.vo.FacilitiesRequestVO;
import kr.ac.usu.facilities.vo.FacilitiesVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import kr.ac.usu.professor.mapper.StaffProfessorMapper;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StudentVO;

/**
 * 
 * @author PC-25
 *
 * @author 김원희
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
@RequestMapping("/student/facilities")
public class StudentFacilitiesListController {
	@Inject
	private StudentFacilitiesListMapper mapper;
	@Inject
	private FacilitiesListService service;
	
	@Inject
	private StudentFacilitiesRequestService reqService;
	
	@Inject
	private StudentFacilitiesRequestMapper reqMapper;
	
	@GetMapping("ajax/facilitiesList")
	public String listData(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		PaginationInfo<FacilitiesVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveFacilitiesList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	
	@PostMapping("ajax/facilitiesRequest")
	public String facilitiesReservation(Authentication auth,FacilitiesRequestVO facil, Model model) {
		String studentNo = auth.getName();
	    facil.setStudent(studentNo);
	    try {
			int res = reqService.createFacilitiesRequest(facil);
			model.addAttribute("success",true);
			model.addAttribute("message","완료");
			
		} catch (Exception e) {
			model.addAttribute("success",false);
			model.addAttribute("message","실패");
		}
		
		
		return "jsonView";
			
	}


}	

		

