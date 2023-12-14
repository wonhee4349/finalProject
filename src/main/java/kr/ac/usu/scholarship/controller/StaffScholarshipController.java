package kr.ac.usu.scholarship.controller;

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
import kr.ac.usu.scholarship.service.StaffScholarshipService;
import kr.ac.usu.scholarship.vo.ScholarshipListVO;
import kr.ac.usu.scholarship.vo.ScholarshipVO;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 13.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 13.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Slf4j
@Controller
@RequestMapping("/staff/scholarship")
public class StaffScholarshipController {
	
	@Inject
	private StaffScholarshipService service;
	
	// 장학금 UI 불러오는 메소드
	@GetMapping("/scholarshipListUI")
	public String scholarshipView() {
		return "staff/scholarship/scholarshipListUI";
	}
	
	// 장학금 리스트 불러오는 메소드
	@GetMapping("ajax/scholarshipList")
	public String listData(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model	
	) {
		PaginationInfo<ScholarshipVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveScholarshipList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 총장학금 리스트 불러오는 메소드
	@GetMapping("ajax/totalScholarshipList")
	public String totalScholarshipList(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model			
	) {
		PaginationInfo<ScholarshipListVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveTotalScholarshipList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 장학금 상세페이지 UI 불러오는 메소드
	@GetMapping("ajax/scholarshipListUI")
	public String scholarshipDetailView() {
		return "staff/scholarship/scholarshipView";
	}
	
	// 장학금 상세페이지 데이터 가져오는 메소드
	@RequestMapping("ajax/scholarshipView")
	public String scholarshipDetailData(
		@RequestParam("what") String schlshipNo
		, Model model	
	) {
		ScholarshipVO scholarshipInfo = service.retrieveScholarshipInfo(schlshipNo);
		
		model.addAttribute("scholarshipInfo", scholarshipInfo);
		
		return "/ajax/staff/scholarship/scholarshipView";
	}
	
	// 총장학금 인서트하는 메소드
	@PostMapping("/insertTotalScholarship")
	public String insertTotalScholarship(
		ScholarshipListVO schoNm
		, Model model
	) {
		try {
			int res = service.createTotalScholarship(schoNm);
			model.addAttribute("success", true);
		}catch (Exception e) {
			model.addAttribute("success", false);
		}
		return "jsonView";
	}
	
	// 학기별 장학금 등록 모달폼 띄우는 메소드
	@GetMapping("ajax/scholarshipInsertView")
	public String scholarshipInsertView(Model model) {
		
		List<ScholarshipListVO> scholarshipList = service.retrieveInsertScholarshipList();
		
		model.addAttribute("scholarshipList", scholarshipList);
		
		return "/ajax/staff/scholarship/scholarshipInsertView";
	}
	
	// 학기별 장학금 인서트하는 메소드
	@PostMapping("/insertScholarship")
	public String insertScholarship(
		ScholarshipVO scholarship
		, Model model
	) {
		
		try {
			int res = service.createScholarship(scholarship);
			model.addAttribute("success", true);
		}catch (Exception e) {
			model.addAttribute("success", false);
		}
		
		return "jsonView";
	}
	
}
























