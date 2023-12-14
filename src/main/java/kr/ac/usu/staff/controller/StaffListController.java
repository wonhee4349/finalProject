package kr.ac.usu.staff.controller;

import java.util.List;

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
import kr.ac.usu.staff.service.StaffListService;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StaffVO;

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
@RequestMapping("/staff/staff")
public class StaffListController {
	
	@Inject
	private StaffProfessorMapper mapper;
	
	@Inject
	private StaffListService service;
	
	@GetMapping("ajax/staffList")
	public String listData(
		@ModelAttribute("detailCondition") StaffVO detailCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		
		PaginationInfo<StaffVO> paging = new PaginationInfo<>(10,5);
		paging.setDetailCondition(detailCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveStaffList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("ajax/staffView")
	public String memberView(
		@RequestParam("who") String sklstfNo
		, Model model
	){
		StaffVO staff = service.retrieveStaff(sklstfNo);
		List<ComCodeVO> sexdstn = mapper.selectComCode("SEC001");
		List<ComCodeVO> nlty = mapper.selectComCode("SEC002");
		List<ComCodeVO> emplmnForm = mapper.selectComCode("SEC003");
		
		model.addAttribute("sexdstn", sexdstn);
		model.addAttribute("nlty", nlty);
		model.addAttribute("emplmnForm", emplmnForm);
		model.addAttribute("staff", staff);
						
		return "/ajax/staff/staff/staffView";
	}
	
	@PostMapping("/staffStaffInsertData")
	public String staffProfessorData(
		StaffVO prof
		, Model model
	) {
		try {
			int res = service.createStaff(prof);
			model.addAttribute("success", true);
			model.addAttribute("message", "성공하였습니다");
		} catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "실패하였습니다");
		}
		return "jsonView";
	}
	
	@PostMapping("/staffStaffUpdateData")
	public String staffStaffUpdateData(
		StaffVO prof
		, Model model
	) {
		try {
			int res = service.modifyStaff(prof);
			model.addAttribute("success", true);
			model.addAttribute("message", "수정되었습니다");
		} catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "실패하였습니다");
		}
		return "jsonView";
	}

}
