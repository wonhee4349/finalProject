package kr.ac.usu.schoolaffairsschedule.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import kr.ac.usu.professor.mapper.StaffProfessorMapper;
import kr.ac.usu.professor.service.ProfessorListService;
import kr.ac.usu.schoolaffairsschedule.mapper.StaffSchoolAffairsScheduleMapper;
import kr.ac.usu.schoolaffairsschedule.service.StaffSchoolAffairsScheduleService;
import kr.ac.usu.schoolaffairsschedule.vo.SchoolAffairsScheduleVO;
import kr.ac.usu.user.vo.ProfessorVO;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 22.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/staff/staffschoolaffairsschedule")
public class StaffSchoolAffairsScheduleController {

	@Inject
	private StaffSchoolAffairsScheduleService service;
	
	@Inject
	private StaffSchoolAffairsScheduleMapper mapper;
	
	@GetMapping("ajax/staffschoolaffairsscheduleList")
	public String listData(
		@ModelAttribute("detailCondition") SchoolAffairsScheduleVO detailCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		PaginationInfo<SchoolAffairsScheduleVO> paging = new PaginationInfo<>(10,5);
		paging.setDetailCondition(detailCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveStaffSchoolAffairsScheduleList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@PostMapping("/staffSchoolAffairsScheduleInsertData")
	public String staffSchoolAffairsScheduleData(
			SchoolAffairsScheduleVO prof
		, Model model
	) {
		try {
			int res = service.createSchoolAffairsSchedule(prof);
			model.addAttribute("success", true);
			model.addAttribute("message", "성공했습니다!");
		} catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "실패했습니다!");
		}
		return "jsonView";
	}		
}
