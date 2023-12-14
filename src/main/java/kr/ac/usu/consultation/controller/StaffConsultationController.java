package kr.ac.usu.consultation.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.club.vo.ClubEstblVO;
import kr.ac.usu.consultation.mapper.StaffConsultationMapper;
import kr.ac.usu.consultation.mapper.StaffConsultationRequestMapper;
import kr.ac.usu.consultation.service.StaffConsultationRequestService;
import kr.ac.usu.consultation.service.StaffConsultationService;
import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.consultation.vo.ConsultationVO;
import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.StaffVO;
import kr.ac.usu.user.vo.StudentVO;
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
@RequestMapping("/staff/consultation")
public class StaffConsultationController {
	
	@Inject
	private StaffConsultationService service;
	
	@Inject
	private StaffConsultationRequestService requestService;
		
	@Inject
	private StaffConsultationMapper mapper;
	
	@Inject
	private StaffConsultationRequestMapper requestMapper;
	
	// 상담 신청 UI 불러오는 메소드
	@GetMapping("/consultationRequestListUI")
	public String consultationRequestView(Model model) {
		
		List<CollegeVO> college = mapper.selectCollegeList();
		List<SubjectVO> subject = mapper.selectSubjectList();
		
		model.addAttribute("college",college);
		model.addAttribute("subject",subject);
		
		return "staff/consultation/consultationRequestUI";
	}
	
	
	// 상담 신청 리스트 불러오는 메소드
	@GetMapping("ajax/consultationRequest")
	public String listConsultationRequestData(
		@ModelAttribute("detailCondition") ConsultationRequestVO detailCondition
		, StudentVO student
		, SubjectVO subject
		, CollegeVO college
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		
		subject.setCollege(college);
		student.setSubject(subject);
		detailCondition.setStudent(student);
		
		PaginationInfo<ConsultationRequestVO> paging = new PaginationInfo<>(10,5);
		paging.setDetailCondition(detailCondition);
		paging.setCurrentPage(currentPage);
		
		requestService.retrieveConsultationRequestList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	
	// 상담 내역 UI 불러오는 메소드
	@GetMapping("/consultationUI")
	public String consultationView(Model model) {
		
		List<CollegeVO> college = mapper.selectCollegeList();
		List<SubjectVO> subject = mapper.selectSubjectList();
		List<StaffVO> staff = mapper.selectStaffList();
		
		model.addAttribute("college",college);
		model.addAttribute("subject",subject);
		model.addAttribute("staff",staff);
		
		return "staff/consultation/consultationUI";
	}
	
	// 상담 내역 리스트 불러오는 메소드
	@GetMapping("ajax/consultation")
	public String listConsultationData(
		@ModelAttribute("detailCondition") ConsultationVO detailCondition
		, StudentVO student
		, SubjectVO subject
		, CollegeVO college
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model		
	) {
		
		subject.setCollege(college);
		student.setSubject(subject);
		detailCondition.setStudent(student);
		
		PaginationInfo<ConsultationVO> paging = new PaginationInfo<>(10,5);
		paging.setDetailCondition(detailCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveConsultationList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 상담 내역 상세 페이지 조회하는 메소드
	@GetMapping("ajax/consultationView")
	public String consultationDetailView(
		@RequestParam("what") String consultNo
		, Model model	
	) {
		ConsultationVO consultationInfo = service.retrieveConsultationInfo(consultNo);
		
		model.addAttribute("consultationInfo", consultationInfo);

		return "/ajax/staff/consultation/consultationView";
	}
	
	// 상담 신청 내역 상세 페이지 조회하는 메소드
	@GetMapping("ajax/consultationRequestView")
	public String consultationRequestDetailView(
		@RequestParam("what") String consultNo
		, Model model	
	) {
		ConsultationRequestVO consultationRequestInfo = requestService.retrieveConsultationRequestInfo(consultNo);
		List<StaffVO> staffList = requestService.retrieveStaffList();
		
		model.addAttribute("consultationRequestInfo", consultationRequestInfo);
		model.addAttribute("staffList", staffList);
		
		return "/ajax/staff/consultation/consultationRequestView";
	}
	
	// 상담 신청 승인 (상담 신청 정보 업데이트, 인서트) 메소드
	@PostMapping("updateAppliedConsultationRequest")
	public String updateAppliedConsultationRequest(
		ConsultationRequestVO cnsltReqNo
		, ConsultationVO cnsltNo
		, Model model
	) {
		log.info("신청 : {}", cnsltReqNo);
		log.info("상담 : {}", cnsltNo);
		try {			
			requestService.modifyAppliedConsultationRequest(cnsltReqNo, cnsltNo);
			model.addAttribute("success", true);
		}catch (Exception e) {
			model.addAttribute("success", false);
		}
		return "jsonView";
	}
	
	// 상담 신청 반려 (상담 신청 정보 업데이트) 메소드
	@PostMapping("updateRefusedConsultationRequest")
	public String updateRefusedConsultationRequest(
			ConsultationRequestVO cnsltReqNo
		, Model model
	) {
		try {
			requestService.modifyRefusedConsultationRequest(cnsltReqNo);
			model.addAttribute("success", true);
		}catch (Exception e) {
			model.addAttribute("success", false);
		}
		return "jsonView";
	}
	
	// 상담수정 모달 불러오는 메소드
	@GetMapping("ajax/consultationModifyView")
	public String consultationModifyView(
		@RequestParam("what") String cnsltNo
		, Model model		
	) {
		
		ConsultationVO consultationInfo = service.retrieveConsultationInfo(cnsltNo);
		
		model.addAttribute("consultationInfo", consultationInfo);
		
		return "/ajax/staff/consultation/modifyConsultationView";
	}
	
	// 상담 수정하는 메소드
	@PostMapping("ajax/consultationModify")
	public String consultationModify(
		ConsultationVO cnsltNo
		, Model model
	) {
		try {
			int res = service.modifyConsultationInfo(cnsltNo);
			model.addAttribute("success", true);
		}catch (Exception e) {
			model.addAttribute("success", false);
		}

		return "jsonView";
	}
	
}






















