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

import kr.ac.usu.consultation.mapper.ProfessorConsultationMapper;
import kr.ac.usu.consultation.service.ProfessorConsultationService;
import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.consultation.vo.ConsultationVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import kr.ac.usu.user.vo.ComCodeVO;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 
 * </pre>
 * @author 김재성
 * @since 2023. 11. 17.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 	  수정일    		    수정자       수정내용
 * --------------     --------    ----------------------
 * 2023. 11. 17.     	김재성       최초작성
 * 2023. 11. 21.     	김재성       승인 반려 기능
 * 2023. 11. 22.     	김재성       상담신청 승인완료 신청 1개 데이터 조회 및 상담등록
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/professor/consultation")
public class ProfessorConsultationController {
	
	@Inject
	private ProfessorConsultationService service;
	@Inject
	private ProfessorConsultationMapper mapper;
	
	// 상담 신청 UI 불러오는 메소드
	@GetMapping("/professorConsultationRequestListUI")
	public String consultationRequestView() {
		return "professor/consultation/professorConsultationRequestUI";
	}
	
	
	// 상담 신청 리스트 불러오는 메소드
	@GetMapping("ajax/consultationRequest")
	public String listConsultationRequestData(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		PaginationInfo<ConsultationRequestVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveProfessorConsultationRequestList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 학업상담 신청 내역 상세 페이지 조회하는 메소드
	@RequestMapping("ajax/consultationRequestView")
	public String consultationRequestDetailView(
		@RequestParam("consultNo") String consultNo
		, Model model	
	) {
		ConsultationRequestVO consultationRequestInfo = service.retrieveProfessorConsultationRequestInfo(consultNo);
		
		model.addAttribute("consultationRequest", consultationRequestInfo);
		
		return "ajax/professor/consultation/professorConsultationRequestView";
	}

	
	
	// 상담 내역 UI 불러오는 메소드
	@GetMapping("/professorConsultationUI")
	public String consultationView() {
		return "professor/consultation/professorConsultationUI";
	}
	
	// 상담 내역 리스트 불러오는 메소드
	@GetMapping("ajax/professorConsultation")
	public String listConsultationData(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model		
	) {
		PaginationInfo<ConsultationVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveProfessorConsultationList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 학업상담 내역 상세 페이지 조회하는 메소드
	@RequestMapping("ajax/professorConsultationView")
	public String consultationDetailView(
		@RequestParam("consultNo") String consultNo
		, Model model	
	) {
		ConsultationVO consultationInfo = service.retrieveProfessorConsultationInfo(consultNo);
		
		model.addAttribute("consultation", consultationInfo);

		return "ajax/professor/consultation/professorConsultationView";
	}
	
	// 학업상담 신청 승인
	@PostMapping("/updateAllowConsultationRequest")
	public String consultationAllow(
		ConsultationRequestVO consultationRequest
		,Model model
	) {
		
		try {			
			service.modifyAllowConsultationRequest(consultationRequest);
			model.addAttribute("success", true);
			model.addAttribute("message", "승인 완료!");
		}catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "승인 실패!");
		}
		return "jsonView";
	}
	// 학업상담 신청 반려
	@PostMapping("/updateRefuseConsultationRequest")
	public String consultationRefuse(
			ConsultationRequestVO consultationRequest 
			,Model model
	) {
		
		log.info("************************************************반려 매개변수 값 조회 : {}",consultationRequest);
		
		try {			
			service.modifyRefuseConsultationRequest(consultationRequest);
			model.addAttribute("success", true);
			model.addAttribute("message", "반려 완료!");
		}catch(Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "반려 실패!");
		}
		return "jsonView";
	}
	
	
	// 승인완료한 상담신청 UI 불러오는 메소드
	@GetMapping("/professorRecognizeConsultationRequestUI")
	public String recognizeConsultationRequestView() {
		return "professor/consultation/professorRecognizeConsultationRequestUI";
	}
	
	// 승인완료한 상담신청 리스트 목록 
	@GetMapping("ajax/professorRecognizeConsultationRequest")
	public String listRecognizeConsultationRequestData(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		PaginationInfo<ConsultationRequestVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		service.retrieveProfessorRecognizeConsultationRequestList(paging);
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 상담신청 승인완료 리스트에 등록 상세페이지 
	@RequestMapping("ajax/professorRecognizeConsultationView")
	public String recognizeConsultationDetailView(
		@RequestParam("consultNo") String consultNo
		, Model model	
	) {
		ConsultationRequestVO recognizeConsultationInfo = service.retrieveProfessorRecognizeConsultationRequestInfo(consultNo);
		
		// 시간표 테이블
		List<ComCodeVO> tmtbe = mapper.selectTimeTable();
		model.addAttribute("timetable",tmtbe);
		
		model.addAttribute("recognizeConsultation", recognizeConsultationInfo);
		
		return "ajax/professor/consultation/professorRecognizeConsultationRequestView";
	}
	
	// 상담신청 상담 후 등록 
	@PostMapping("/createProfessorRecognizeConsultation")
	public String recognizeConsultationRegistration(
			ConsultationVO recongnizeConsultation
			,Model model
	) {
		
		//log.info("************************************************반려 매개변수 값 조회 : {}",recongnizeConsultation);
		
		try {			
			service.createProfessorRecognizeConsultationRequest(recongnizeConsultation);
			model.addAttribute("success", true);
			model.addAttribute("message", "상담 등록 완료!");
		}catch(Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "상담 등록 실패!");
		}
		
		return "jsonView";
	}

}
