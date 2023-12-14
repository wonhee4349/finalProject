package kr.ac.usu.club.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
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

import kr.ac.usu.club.service.StudentClubRequestService;
import kr.ac.usu.club.service.StudentClubService;
import kr.ac.usu.club.vo.ClubEstblVO;
import kr.ac.usu.club.vo.ClubVO;
import kr.ac.usu.common.mapper.CommonOptionsMapper;
import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.facilities.vo.FacilitiesVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 김원희
 * @since 2023. 11. 9.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/student/club")
public class StudentClubController {
	
	@Inject
	private StudentClubService service;

	@Autowired
	private StudentClubRequestService reqService;
	
	@Inject
	private CommonOptionsMapper opMepper;
	
	
	
	
	// 동아리 UI 불러오는 메소드
	@GetMapping("/clubList")
	public String clubView() {
		return "student/club/clubList";
	}
	
	// 동아리 개설 UI 불러오는 메소드
	@GetMapping("/clubOpen")
	public String clubOpenView(FacilitiesVO prof,Model model) {
		List<Map<String, String>> comCodeList = reqService.comCodeNm();
		log.info("체킁:{}",comCodeList);
		List<Map<String, String>> buildList = reqService.buldCodeNm();
		
		
		model.addAttribute("comCodeList",comCodeList);
		model.addAttribute("buildList", buildList);
		return "student/club/clubOpen";
	}
	
	

	// 동아리개설신청 리스트 UI 불러오는 메소드
	@GetMapping("/clubRequestList")
	public String clubRequestView() {
		return "student/club/clubRequestList";
	}
	
	
	
	// 동아리 개설 신청 리스트 불러오는 메소드
	@GetMapping("ajax/clubRequestList")
	public String clubRequestListData(
			Authentication auth,	
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
			
		PaginationInfo<ClubEstblVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
	
	
		
		ClubEstblVO detailCondition = new ClubEstblVO();
		detailCondition.setStdntNo(auth.getName());
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		paging.setDetailCondition(detailCondition);
		log.info("ck:{}",paging);
		reqService.retrieveClubRequestList(paging);
		
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	
	
	// 동아리 리스트 불러오는 메소드
	@GetMapping("ajax/clubList")
	public String listData(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		,Model model
	) {
		PaginationInfo<ClubVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveClubList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	

	@GetMapping("wh/clubList")
	public String list2Data(
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
		
		return "student/club/clubList" ;
	}

	
	

	
	 // 건물 선택별 강의실 선택 리스트
	   @ResponseBody
	   @GetMapping("/clubOpenRequest")
	   public List<FacilitiesVO> facilitiesList(
	         @RequestParam(value="buldNo") String buldNo
	         ,Model model
	         ) {
	      log.info("&&&&&&&&&&&&&&&&&&&&&&&프론트 보내온 매개변수 : {}",buldNo);
	      //학과 구분
	      List<FacilitiesVO> facilities = opMepper.getFacilitiesList(buldNo);
	      log.info("&&&&&&&&&&&&&&&&&&&돌아오는 학과 리스트 확인 : {}",facilities);
	      
	      return facilities;
	   }
	
	
	// 동아리 신청 메소드
		@PostMapping("/ajax/clubOpenRequest")
		@ResponseBody
		public String updateAppliedClubRequest(
			Authentication auth	
			,@RequestBody ClubEstblVO clubEsNo
		) {
			String studentNo = auth.getName();
			clubEsNo.setStdntNo(studentNo);
			int res = reqService.createClubRequest(clubEsNo);
			
			String msg = "NG";
			if(res == 1) {
				msg = "OK";
			}
			
			return msg;
		}
	
		// 동아리 개설 신청 상세 페이지 조회하는 메소드
		@RequestMapping("ajax/clubRequestView")
		public String clubRequestDetailView(
			@RequestParam("what") String consultNo
			, Model model	
		) {
			ClubEstblVO clubRequestInfo = reqService.retrieveClubRequestInfo(consultNo);
			
			model.addAttribute("clubRequestInfo", clubRequestInfo);
			
			return "/ajax/student/club/clubRequestView";
		}
		
		
		// 동아리 상세 페이지 조회하는 메소드 
		@ResponseBody
		@RequestMapping("/ajax/clubView")
		public ClubVO clubDetail(String clubNo) {
			ClubVO clubInfo = service.selectClubInfo(clubNo);	
			return clubInfo;
		}
		
		

}
