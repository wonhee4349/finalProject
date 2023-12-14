package kr.ac.usu.tuition.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.tuition.mapper.StaffTuitionPayMapper;
import kr.ac.usu.tuition.service.StaffTuitionPayService;
import kr.ac.usu.tuition.vo.TuitionVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 22.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 22.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Slf4j
@Controller
@RequestMapping("/staff/tuitionPay")
public class StaffTuitionPayControllser {
	
	@Inject
	private StaffTuitionPayService service;
	
	@Inject
	private StaffTuitionPayMapper mapper;
	
	// 납부여부 UI 불러오는 메소드
	@GetMapping("/tuitionPayListUI")
	public String tuitionPayView(Model model) {
		
		List<CollegeVO> college = mapper.selectCollegeList();
		List<SubjectVO> subject = mapper.selectSubjectList();
		
		model.addAttribute("college",college);
		model.addAttribute("subject",subject);
		
		return "staff/tuition/tuitionPayListUI";
	}
		
	// 납부여부 리스트 불러오는 메소드
	@GetMapping("ajax/tuitionPayList")
	public String tuitionPayListData(
		@ModelAttribute("detailCondition") TuitionVO detailCondition
		, StudentVO student
		, SubjectVO subject
		, CollegeVO college
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		
		subject.setCollege(college);
		student.setSubject(subject);
		detailCondition.setStudent(student);
		
		PaginationInfo<TuitionVO> paging = new PaginationInfo<>(10,5);
		paging.setDetailCondition(detailCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveTuitionList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 납부여부 모달 불러오는 메소드
	@GetMapping("ajax/tuitionDetailView")
	public String tuitionDetailView(
		@RequestParam("what") String tutnNhtNo
		, Model model		
	) {
		TuitionVO tuitionInfo = service.retrieveTuitionInfo(tutnNhtNo);
		
		model.addAttribute("tuitionInfo", tuitionInfo);
		
		return "/ajax/staff/tuition/tuitionDetailView";
	}
	
	// 납부수정 모달 불러오는 메소드
	@GetMapping("ajax/tuitionModifyView")
	public String tuitionModifyView(
		@RequestParam("what") String tutnNhtNo
		, Model model		
	) {
		TuitionVO tuitionInfo = service.retrieveTuitionInfo(tutnNhtNo);
		
		model.addAttribute("tuitionInfo", tuitionInfo);
		
		return "/ajax/staff/tuition/modifyTuitionView";
	}
	
	// 납부 수정하는 메소드
	@PostMapping("ajax/tuitionModify")
	public String tuitionModify(
		TuitionVO tutnNhtNo
		, Model model			
	) {
		
		try {
			int res = service.modifyTuitionInfo(tutnNhtNo);
			model.addAttribute("success", true);
		}catch (Exception e) {
			model.addAttribute("success", false);
		}

		return "jsonView";
	}
	
}
























