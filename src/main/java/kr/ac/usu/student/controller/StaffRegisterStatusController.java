package kr.ac.usu.student.controller;

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
import kr.ac.usu.student.mapper.StaffRegisterStatusRequestMapper;
import kr.ac.usu.student.service.StaffRegisterStatusRequestService;
import kr.ac.usu.student.vo.AbsenceSchoolVO;
import kr.ac.usu.student.vo.SchoolRegisterHistoryVO;
import kr.ac.usu.student.vo.SchoolRegisterVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 24.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 24.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Slf4j
@Controller
@RequestMapping("/staff/registerStatus")
public class StaffRegisterStatusController {
	
	@Inject
	private StaffRegisterStatusRequestService service;
	
	@Inject
	private StaffRegisterStatusRequestMapper mapper;
	
	// 학적 변동 신청 리스트 UI 불러오는 메소드
	@GetMapping("/registerStatusListUI")
	public String registerStatusListUI(Model model) {
		
		List<CollegeVO> college = mapper.selectCollegeList();
		List<SubjectVO> subject = mapper.selectSubjectList();
		List<ComCodeVO> nltySe = mapper.selectComCode("SEC002");
		List<ComCodeVO> genderSe = mapper.selectComCode("SEC001");
		
		model.addAttribute("college",college);
		model.addAttribute("subject",subject);
		model.addAttribute("nltySe",nltySe);
		model.addAttribute("genderSe",genderSe);
		
		return "staff/student/registerStatusRequestListUI";
	}
	
	// 학적 변동 신청 리스트 데이터 불러오는 메소드
	@GetMapping("ajax/registerStatusData")
	public String registerStatusData(
		@ModelAttribute("detailCondition") SchoolRegisterVO detailCondition
		, StudentVO student
		, SubjectVO subject
		, CollegeVO college
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
		, @RequestParam String searchSe		
	) {
		
		log.info("ssssssssssssssss : {}",detailCondition);
		
		// 복수전공 신청
		if(searchSe.equals("tab-1")) {
			
			subject.setCollege(college);
			student.setSubject(subject);
			detailCondition.setStudent(student);
			
			PaginationInfo<SchoolRegisterVO> paging = new PaginationInfo<>(10,5);
			paging.setDetailCondition(detailCondition);
			paging.setCurrentPage(currentPage);
			
			service.retrieveDoubleMajorRequestList(paging);
			
			paging.setRenderer(new BootstrapPaginationRenderer());
			
			model.addAttribute("paging", paging);
			
		// 부전공 신청
		}else if(searchSe.equals("tab-2")){
			
			subject.setCollege(college);
			student.setSubject(subject);
			detailCondition.setStudent(student);
			
			PaginationInfo<SchoolRegisterVO> paging = new PaginationInfo<>(10,5);
			paging.setDetailCondition(detailCondition);
			paging.setCurrentPage(currentPage);
			
			service.retrieveMinorRequestList(paging);
			
			paging.setRenderer(new BootstrapPaginationRenderer());
			
			model.addAttribute("paging", paging);
			
		// 전과 신청
		}else {
			
			subject.setCollege(college);
			student.setSubject(subject);
			detailCondition.setStudent(student);
			
			PaginationInfo<SchoolRegisterVO> paging = new PaginationInfo<>(10,5);
			paging.setDetailCondition(detailCondition);
			paging.setCurrentPage(currentPage);
			
			service.retrieveMoveMajorRequestList(paging);
			
			paging.setRenderer(new BootstrapPaginationRenderer());
			
			model.addAttribute("paging", paging);
			
		}

		return "jsonView";
	}
	
	// 학적변동 신청 모달 불러오는 메소드
	@GetMapping("ajax/doubleMajorRequestView")
	public String doubleMajorRequestView(
		@RequestParam("what") String sknrgNo
		, Model model		
	) {
		SchoolRegisterVO requestInfo = service.retrieveRequestInfo(sknrgNo);
		
		model.addAttribute("requestInfo", requestInfo);
		
		return "/ajax/staff/student/doubleMajorRequestView";
	}
	
	// 신청 반려 (신청 정보 업데이트) 메소드
	@PostMapping("updateRefusedRequest")
	public String updateRefusedRequest(
		SchoolRegisterVO sknrgNo
		, Model model
		) {
		
		try {
			log.info("ㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂ{}", sknrgNo);
			
			service.modifyRefusedRequestInfo(sknrgNo);
			model.addAttribute("success", true);
		}catch (Exception e) {
			model.addAttribute("success", false);
		}
		
		return "jsonView";
	}
	
	// 신청 승인 (신청 정보 업데이트, 학적 정보 인서트)
	@PostMapping("updateAppliedRequest")
	public String updateAppliedRequest(
		SchoolRegisterVO sknrgNo
		, SchoolRegisterHistoryVO history
		, StudentVO stdntNo
		, Model model
	) {		

		try {			
			service.modifyAppliedRequestInfo(sknrgNo, history, stdntNo);
			model.addAttribute("success", true);
		}catch (Exception e) {
			model.addAttribute("success", false);
		}
		return "jsonView";
	}
	
}


































