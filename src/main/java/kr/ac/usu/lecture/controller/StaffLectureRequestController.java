package kr.ac.usu.lecture.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.consultation.service.StaffConsultationRequestService;
import kr.ac.usu.consultation.service.StaffConsultationService;
import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.consultation.vo.ConsultationVO;
import kr.ac.usu.lecture.service.StaffLectureRequestService;
import kr.ac.usu.lecture.service.StaffLectureService;
import kr.ac.usu.lecture.vo.LectureRequestVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import kr.ac.usu.user.vo.StaffVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 30.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 30.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/staff/lecture")
public class StaffLectureRequestController {
	
		
		@Inject
		private StaffLectureRequestService requestService;
		
		@Inject StaffLectureService service;
		
		// 상담 신청 UI 불러오는 메소드
		@GetMapping("/lectureRequestListUI")
		public String lectureRequestView() {
			return "staff/lecture/lectureRequestUI";
		}
		
		
		// 강의 신청 리스트 불러오는 메소드
		@GetMapping("ajax/lectureRequest")
		public String listlectureRequestData(
			@ModelAttribute("simpleCondition") SearchVO simpleCondition
			, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
			, Model model
		) {
			PaginationInfo<LectureRequestVO> paging = new PaginationInfo<>(10,5);
			paging.setSimpleCondition(simpleCondition);
			paging.setCurrentPage(currentPage);
			
			requestService.retrieveLectureRequestList(paging);
			
			paging.setRenderer(new BootstrapPaginationRenderer());
			
			model.addAttribute("paging", paging);
			
			return "jsonView";
		}
		
		
		// 상담 신청 내역 상세 페이지 조회하는 메소드
		@GetMapping("ajax/lectureRequestView")
		public String lectureRequestDetailView(
			@RequestParam("what") String lctreReqstNo
			, Model model	
		) {
			LectureRequestVO lectureRequestInfo = requestService.retrieveLectureRequestInfo(lctreReqstNo);
			
			
			model.addAttribute("lectureRequestInfo", lectureRequestInfo);
			
			return "/ajax/staff/lecture/lectureRequestView";
		}
		
		// 상담 신청 승인 (상담 신청 정보 업데이트, 인서트) 메소드
		@PostMapping("updateAppliedLectureRequest")
		public String updateAppliedLectureRequest(
				String lctreReqNo
				, Model model
		) {
			//LectureRequestVO(rnum=0, lctreReqstNo=RF05824102, lctreReqstDate=null, prfsorNo=00000106, courseNo=null, complSe=,, 
			//complSeNm=null, semstrSe=20231, semstrSeNm=null, confmSe=null, confmSeNm=null, lctreReqstReturn=null, lctreSe=null, 
			//lctreSeNm=null, fcltsNo=, buldNo=null, professor=null, course=null, facilitiess=null, lctreAcnutnoNo=null, 
			//lectureRequestInfoVOList=null, lectureActionPlanVO=null, facilities=null, building=null)
			log.info("신청 : {}", lctreReqNo);
			//LectureVO(rnum=0, buldNm=null, fcltsNm=null, semstrSeNm=null, lctreNo=null, lctreNmpr=30, lctreSe=01, fcltsNo=, 
			//prfsorNo=00000106, semstrSe=20231, complSe=,, courseNo=null, professor=null, facilities=null, course=null, 
			//timetable=null, tmtbSeNm=null, student=null, currCnt=null, coursePnt=null, courseDayOfWeek=null, 
			//courseNm=null, prfsorNm=null, currentPage=null, lctreReqstNo=RF05824102, evaStatus=null)
			try {			
				requestService.modifyAppliedLectureRequest(lctreReqNo);
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
		@PostMapping("updateRefusedLectureRequest")
		public String updateRefusedLectureRequest(
				LectureRequestVO lctreReqstNo
			, Model model
		) {
			try {
				requestService.modifyRefusedLectureRequest(lctreReqstNo);
				model.addAttribute("success", true);
				model.addAttribute("message", "반려되었습니다");
			}catch (Exception e) {
				model.addAttribute("success", false);
				model.addAttribute("message", "실패하였습니다");
			}
			return "jsonView";
		}
		
		
	}

