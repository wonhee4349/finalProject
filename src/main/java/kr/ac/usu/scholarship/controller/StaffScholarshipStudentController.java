package kr.ac.usu.scholarship.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

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
import kr.ac.usu.scholarship.service.StaffScholarshipStudentService;
import kr.ac.usu.scholarship.vo.ScholarshipRequestVO;
import kr.ac.usu.scholarship.vo.ScholarshipStudentVO;
import kr.ac.usu.scholarship.vo.ScholarshipVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 16.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 16.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Slf4j
@Controller
@RequestMapping("/staff/scholarshipStudent")
public class StaffScholarshipStudentController {
	
	@Inject
	private StaffScholarshipStudentService service;
	
	// 장학생 UI 불러오는 메소드
	@GetMapping("/scholarshipStudentListUI")
	public String scholarshipStudentListUI(HttpServletRequest req) {
		return "staff/scholarship/scholarshipStudentListUI";
	}
	
	// 장학생 리스트 불러오는 메소드
	@GetMapping("ajax/scholarshipStudentList")
	public String scholarshipStudentData(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		PaginationInfo<ScholarshipStudentVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveScholarshipStudent(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 장학생 상세 페이지 조회하는 메소드
	@RequestMapping("ajax/scholarshipStudentView")
	public String scholarshipStudentDetail(
		@RequestParam("who") String stdntNo
		, Model model
	) {
		ScholarshipStudentVO studentInfo = service.retrieveScholarshipStudentInfo(stdntNo);
		ScholarshipStudentVO studentRegister = service.retrieveScholarshipStudentStatus(stdntNo);
		ScholarshipStudentVO studentScholarship = service.retrieveScholarshipStudentScholarship(stdntNo);
		
		model.addAttribute("studentInfo", studentInfo);
		model.addAttribute("studentRegister", studentRegister);
		model.addAttribute("studentScholarship", studentScholarship);
		
		return "/ajax/staff/scholarship/scholarshipStudentView";
	}
	
	// 장학생 등록 모달폼 띄우는 메소드
	@GetMapping("ajax/scholarshipStudentInsertView")
	public String scholarshipStudentInsertView(Model model) {
		
		List<ScholarshipVO> scholarshipList = service.retrieveScholarshipList();
		
		model.addAttribute("scholarshipList", scholarshipList);
		
		return "/ajax/staff/scholarship/scholarshipStudentInsertView";
	}
	
	// 장학생 등록 모달폼에서 학생 정보 불러오는 메소드
	@GetMapping("ajax/scholarshipStudentInsertData")
	public String scholarshipStudentInsertData(
		@RequestParam("searchWord") String stdntNo
		, Model model
	) {
		StudentVO studentInfo = service.retrieveStudent(stdntNo);
		
		model.addAttribute("studentInfo", studentInfo);
		
		return "jsonView";
	}
	
	// 장학금 셀렉트박스에서 고른 장학금 정보 불러오는 메소드
	@GetMapping("ajax/scholarshipInsertData")
	public String scholarshipInsertData(
		@RequestParam("scholarship") String schlshipNo
		, Model model
	) {
		ScholarshipVO scholarshipInfo = service.retrieveScholarship(schlshipNo);

		model.addAttribute("scholarshipInfo", scholarshipInfo);
		
		return "jsonView";
	}
	
	// 장학생 인서트하는 메소드
	@PostMapping("/insertScholarshipStudent")
	public String insertScholarshipStudent(
		ScholarshipStudentVO scholarshipStudent
		, Model model
	) {
		try {
			int res = service.createScholarshipStudent(scholarshipStudent);
			model.addAttribute("success", true);
		}catch (Exception e) {
			model.addAttribute("success", false);
		}
		return "jsonView";
	}
	
	//-------------------------------------------------------------------------------------------
	
	// 장학생 신청 리스트 불러오는 메소드
	@GetMapping("ajax/scholarshipRequestList")
	public String scholarshipRequestData(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		PaginationInfo<ScholarshipRequestVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveScholarshipRequestStudent(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		List<ScholarshipVO> semesterInfo = service.retrieveScholarshipSemesterInfo();

		model.addAttribute("paging", paging);
		model.addAttribute("semesterInfo", semesterInfo);
		
		return "jsonView";
	}


}





















