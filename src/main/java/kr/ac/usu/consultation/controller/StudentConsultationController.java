package kr.ac.usu.consultation.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.consultation.service.StudentConsultationService;
import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import kr.ac.usu.user.vo.StaffVO;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;


/**
 * <pre>
 * 
 * </pre>
 * 
 * @author 김원희
 * @since 2023. 11. 20.
 * @version 1.0
 * 
 *          <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 20.      김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 *          </pre>
 */

@Slf4j
@Controller
@RequestMapping("/student/consulting")
public class StudentConsultationController {

	@Inject
	private StudentConsultationService service;
	

	
	// 상담 신청내역 UI 불러오는 메소드
	@GetMapping("/consultingList")
	public String consultationView() {
		log.info("consultationView에 왔다");
		
		return "student/consulting/consultingRequestList";
	}

	// 상담 요청 UI 불러오는 메소드
	@GetMapping("/consultingSubmit")
	public String consultationRequestView() {
		return "student/consulting/consultingSubmit";
	}
	
	//상담 내역 UI 불러오는 메소드
		@GetMapping("/consultingFinishList")
		public String consultationFinishView() {
			log.info("상담내역에 왔다");
			
			return "student/consulting/consultingFinishList";
		}

	
	
	@PostMapping("/ajax/studentConsultation")
	public String studentConsultInsert(Authentication auth, ConsultationRequestVO consult, Model model) {
		String studentNo = auth.getName();
		consult.setStdntNo(studentNo);
		
		try {
			int res = service.createConsultationRequest(consult);
			model.addAttribute("success",true);
			model.addAttribute("message","완료");
			
		} catch (Exception e) {
			model.addAttribute("success",false);
			model.addAttribute("message","실패");
		}
		
		
		return "jsonView";

	}
	
	
	
	// 상담 신청 리스트 불러오는 메소드
	/*
	 요청URI : /student/consulting/consultingList
	 요청파라미터 : {"searchType":"","searchWord":"","currentPage":"4"}
	 요청방식 : post
	 */
	@ResponseBody
	@PostMapping("/consultingList")
	public PaginationInfo<ConsultationRequestVO> listConsultationRequestData(
		  SearchVO simpleCondition
		, @RequestParam(value="currentPage",required=false,defaultValue="1") int currentPage
		, Authentication auth
	) {
		
		log.info("요청내용:{}",simpleCondition);//{"searchType":"","searchWord":""}
		log.info("page:"+ currentPage); //4
		ConsultationRequestVO detailcondition = new ConsultationRequestVO();
		detailcondition.setStdntNo(auth.getName());
		
		PaginationInfo<ConsultationRequestVO> paging = new PaginationInfo<>(10,5);
		paging.setDetailCondition(detailcondition);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveConsultationRequestList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
				
		return paging;
	}
	
	
	
	@ResponseBody
	@PostMapping("/consultingFinishList")
	public PaginationInfo<ConsultationRequestVO> listConsultationData(
		  SearchVO simpleCondition
		, @RequestParam(value="currentPage",required=false,defaultValue="1") int currentPage
		, Authentication auth
	) {
		
		log.info("요청내용:{}",simpleCondition);//{"searchType":"","searchWord":""}
		log.info("page:"+ currentPage); //4
		ConsultationRequestVO detailcondition = new ConsultationRequestVO();
		detailcondition.setStdntNo(auth.getName());
		
		PaginationInfo<ConsultationRequestVO> paging = new PaginationInfo<>(10,5);
		paging.setDetailCondition(detailcondition);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveConsultationFinishList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
				
		return paging;
	}
	
	
	// 상담 신청 내역 상세 페이지 조회하는 메소드
		@ResponseBody
		@RequestMapping("/ajax/consultationRequestView")
			public ConsultationRequestVO retrieveConsultationRequestInfo(String consultNo) {
			ConsultationRequestVO conReqInfo = service.retrieveConsultationRequestInfo(consultNo);
				
			return conReqInfo;
		}
		

		// 상담 신청 내역 상세 페이지 조회하는 메소드
			@ResponseBody
			@RequestMapping("/ajax/consultationView")
				public ConsultationRequestVO selectConsultationFinishInfo(String consultNo) {
				ConsultationRequestVO conReqInfo = service.selectConsultationFinishInfo(consultNo);
					
				return conReqInfo;
			}
		


		

}
