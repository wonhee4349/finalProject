package kr.ac.usu.club.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.club.service.StaffClubRequestService;
import kr.ac.usu.club.service.StaffClubService;
import kr.ac.usu.club.vo.ClubEstblVO;
import kr.ac.usu.club.vo.ClubVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 9.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Slf4j
@Controller
@RequestMapping("/staff/club")
public class StaffClubController {
	
	@Inject
	private StaffClubService service;
	
	@Inject StaffClubRequestService requestService;
	
	// 동아리 UI 불러오는 메소드
	@GetMapping("/clubListUI")
	public String clubView() {
		return "staff/club/clubListUI";
	}
	
	// 동아리 리스트 불러오는 메소드
	@GetMapping("ajax/clubList")
	public String listData(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		PaginationInfo<ClubVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveClubList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 동아리 상세 페이지 조회하는 메소드 
	@RequestMapping("ajax/clubView")
	public String clubDetail(
		@RequestParam("what") String clubNo
		, Model model
	) {
		ClubVO clubInfo = service.retrieveClubInfo(clubNo);
		List<ClubVO> clubMember = service.retrieveClubMember(clubNo);
		
		
		model.addAttribute("clubInfo", clubInfo);
		model.addAttribute("clubMember", clubMember);
		
		return "/ajax/staff/club/clubView";
	}
	
	// 동아리 개설 신청 UI 불러오는 메소드
	@GetMapping("clubRequestListUI")
	public String clubRequestView() {
		return "staff/club/clubRequestUI";
	}
	
	// 동아리 개설 신청 리스트 불러오는 메소드
	@GetMapping("ajax/clubRequest")
	public String clubRequestListData(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		PaginationInfo<ClubEstblVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		 
		requestService.retrieveClubRequestList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 동아리 개설 신청 상세 페이지 조회하는 메소드
	@RequestMapping("ajax/clubRequestView")
	public String clubRequestDetailView(
		@RequestParam("what") String consultNo
		, Model model	
	) {
		ClubEstblVO clubRequestInfo = requestService.retrieveClubRequestInfo(consultNo);
		
		model.addAttribute("clubRequestInfo", clubRequestInfo);
		
		return "/ajax/staff/club/clubRequestView";
		
	}
	
	// 동아리 신청 반려 (동아리 신청 정보 업데이트) 메소드
	@PostMapping("updateRefusedClubRequest")
	public String updateRefusedClubRequest(
		ClubEstblVO clubEsNo
		, Model model
	) {
		try {
			requestService.modifyRefusedClubRequestInfo(clubEsNo);
			model.addAttribute("success", true);
		}catch (Exception e) {
			model.addAttribute("success", false);
		}
		return "jsonView";
	}
	
	// 동아리 신청 승인 (동아리 신청 정보 업데이트, 인서트) 메소드
	@PostMapping("updateAppliedClubRequest")
	public String updateAppliedClubRequest(
		ClubEstblVO clubEsNo
		, ClubVO clubNo
		, Model model		
	) {
		try {			
			requestService.modifyAppliedClubRequestInfo(clubEsNo, clubNo);
			model.addAttribute("success", true);
		}catch (Exception e) {
			model.addAttribute("success", false);
		}
		return "jsonView";
	}

}

















