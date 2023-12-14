package kr.ac.usu.student.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import kr.ac.usu.student.mapper.StaffGraduationMapper;
import kr.ac.usu.student.service.GraduationService;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 10.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 10.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Slf4j
@Controller
@RequestMapping("/staff/graduation")
public class StaffGraduationController {
	
	@Inject
	private GraduationService service;
	
	@Inject
	private StaffGraduationMapper mapper;
	
	// 졸업생 UI 불러오는 메소드
	@GetMapping("/graduationListUI")
	public String graduationView(Model model) {
		
		List<CollegeVO> college = mapper.selectCollegeList();
		List<SubjectVO> subject = mapper.selectSubjectList();
		List<ComCodeVO> nltySe = mapper.selectComCode("SEC002");
		List<ComCodeVO> genderSe = mapper.selectComCode("SEC001");
		
		model.addAttribute("college",college);
		model.addAttribute("subject",subject);
		model.addAttribute("nltySe",nltySe);
		model.addAttribute("genderSe",genderSe);
		
		return "staff/student/graduationListUI";
	}
	
	// 졸업생 리스트 불러오는 메소드
	@GetMapping("ajax/graduationList")
	public String listData(
		@ModelAttribute("detailCondition") StudentVO detailCondition
		, CollegeVO college
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		
		SubjectVO subject = new SubjectVO();
		subject.setCollege(college);
		detailCondition.setSubject(subject);
		
		PaginationInfo<StudentVO> paging = new PaginationInfo<>(10,5);
		paging.setDetailCondition(detailCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveGraduationList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 졸업생 상세 페이지 조회하는 메소드
	@RequestMapping("ajax/graduationView")
	public String memberView(
		@RequestParam("who") String stdntNo
		, Model model
	) {
		StudentVO graduationInfo = service.retrieveGraduationInfo(stdntNo);
		StudentVO graduationRegister = service.retrieveGraduationRegisterStatus(stdntNo);
		StudentVO graduationScholarship = service.retrieveGraduationScholarship(stdntNo);
		StudentVO graduationTuition = service.retrieveGraduationTuition(stdntNo);
		StudentVO graduationClub = service.retrieveGraduationClub(stdntNo);
		List<LectureVO> lectureList = service.retrieveLectureList(stdntNo);
		
		
		model.addAttribute("graduationInfo",graduationInfo);
		model.addAttribute("graduationRegister",graduationRegister);
		model.addAttribute("graduationScholarship",graduationScholarship);
		model.addAttribute("graduationTuition",graduationTuition);
		model.addAttribute("graduationClub",graduationClub);
		model.addAttribute("lectureList",lectureList);
		
		
		return "/ajax/staff/student/graduationView";
	}

}























