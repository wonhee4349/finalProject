package kr.ac.usu.tuition.controller;

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
import kr.ac.usu.schoolaffairsschedule.vo.SemesterVO;
import kr.ac.usu.tuition.service.StaffTuitionStudentService;
import kr.ac.usu.tuition.vo.TuitionVO;
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
@RequestMapping("/staff/tuitionStudent")
public class StaffTuitionStudentController {

	@Inject
	private StaffTuitionStudentService service;
	
	// 납부대상자 UI 불러오는 메소드
	@GetMapping("/tuitionStudentListUI")
	public String tuitionStudentView() {
		return "staff/tuition/tuitionStudentListUI";
	}
		
	// 납부대상자 리스트 불러오는 메소드
	@GetMapping("ajax/tuitionStudentList")
	public String tuitionStudentListData(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		PaginationInfo<StudentVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveTuitionStudentList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 등록금 고지 모달 불러오는 메소드
	@GetMapping("ajax/tuitionInsertView")
	public String tuitionInsertView(
		@RequestParam("who") String stdntNo
		, Model model		
	) {
		StudentVO studentInfo = service.retrieveTuitionStudentInfo(stdntNo);
		StudentVO studentScholarship = service.retrieveTuitionStudentScholarshipInfo(stdntNo);
		SemesterVO semester = service.retrieveTuitionSemesterInfo();
		
		model.addAttribute("studentInfo", studentInfo);
		model.addAttribute("studentScholarship", studentScholarship);
		model.addAttribute("semester", semester);
				
		return "/ajax/staff/tuition/tuitionStudentView";
	}
	
	// 등록금 고지하는 메소드 
	@PostMapping("/insertTuition")
	public String insertTuition(
		TuitionVO tuition
		, Model model
	) {
		try {
			int res = service.createTuition(tuition);
			model.addAttribute("success", true);
		}catch (Exception e) {
			model.addAttribute("success", false);
		}
		return "jsonView";
	}
	
	
}


























