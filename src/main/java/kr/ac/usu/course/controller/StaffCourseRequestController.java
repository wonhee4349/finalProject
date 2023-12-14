package kr.ac.usu.course.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import kr.ac.usu.course.service.StaffCourseRequestService;
import kr.ac.usu.course.service.StaffCourseService;
import kr.ac.usu.course.vo.CourseRequestVO;
import kr.ac.usu.course.vo.CourseVO;

import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 28.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

@Slf4j
@Controller
@RequestMapping("/staff/course")
public class StaffCourseRequestController {
	
	@Inject
	private StaffCourseRequestService requestService;
	
	@Inject StaffCourseService service;
	
	// 상담 신청 UI 불러오는 메소드
	@GetMapping("/courseRequestListUI")
	public String courseRequestView() {
		return "staff/course/courseRequestUI";
	}
	
	
	// 상담 신청 리스트 불러오는 메소드
	@GetMapping("ajax/courseRequest")
	public String listCourseRequestData(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		PaginationInfo<CourseRequestVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		requestService.retrieveCourseRequestList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
		
	
	// 상담 신청 내역 상세 페이지 조회하는 메소드
	@GetMapping("ajax/courseRequestView")
	public String courseRequestDetailView(
		@RequestParam("what") String courseReqstNo
		, Model model	
	) {
		CourseRequestVO courseRequestInfo = requestService.retrieveCourseRequestInfo(courseReqstNo);
		
		model.addAttribute("courseRequestInfo", courseRequestInfo);
		
		return "/ajax/staff/course/courseRequestView";
	}
	
	// 상담 신청 승인 (상담 신청 정보 업데이트, 인서트) 메소드
	@PostMapping("updateAppliedCourseRequest")
	public String updateAppliedCourseRequest(
		CourseRequestVO courseReqstNo
		, CourseVO courseNo
		, Model model
	) {
		log.info("신청 : {}", courseReqstNo);
		log.info("교과목 : {}", courseNo);
		
		
		try {			
			requestService.modifyAppliedCourseRequest(courseReqstNo, courseNo);
			model.addAttribute("success", true);
			model.addAttribute("message", "승인되었습니다");
		}catch (Exception e) {
			log.info("eeeeeeeeeeeee{}", e);
			model.addAttribute("success", false);
			model.addAttribute("message", "실패하였습니다");
		}
		return "jsonView";
	}
	
	// 상담 신청 반려 (상담 신청 정보 업데이트) 메소드
	@PostMapping("updateRefusedCourseRequest")
	public String updateRefusedCourseRequest(
			CourseRequestVO courseReqstNo
		, Model model
	) {
		try {
			requestService.modifyRefusedCourseRequest(courseReqstNo);
			model.addAttribute("success", true);
			model.addAttribute("message", "반려되었습니다");
		}catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "실패하였습니다");
		}
		return "jsonView";
	}
	

}
