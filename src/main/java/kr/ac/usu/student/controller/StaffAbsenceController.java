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
import kr.ac.usu.student.mapper.StaffAbsenceRequestMapper;
import kr.ac.usu.student.service.StaffAbsenceRequestService;
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
 * @since 2023. 11. 23.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 23.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Slf4j
@Controller
@RequestMapping("/staff/absence")
public class StaffAbsenceController {
	
	@Inject
	private StaffAbsenceRequestService service;
	
	@Inject
	private StaffAbsenceRequestMapper mapper;
	
	// 휴학 신청 리스트 UI 불러오는 메소드
	@GetMapping("/absenceRequestListUI")
	public String absenceListUI(Model model) {
		
		List<CollegeVO> college = mapper.selectCollegeList();
		List<SubjectVO> subject = mapper.selectSubjectList();
		List<ComCodeVO> nltySe = mapper.selectComCode("SEC002");
		List<ComCodeVO> genderSe = mapper.selectComCode("SEC001");
		
		model.addAttribute("college",college);
		model.addAttribute("subject",subject);
		model.addAttribute("nltySe",nltySe);
		model.addAttribute("genderSe",genderSe);
		
		return "staff/student/absenceRequestListUI";
	}
	
	// 휴복학 신청 리스트 데이터 불러오는 메소드
	@GetMapping("ajax/absenceRequestData")
	public String listData(
		@ModelAttribute("abDetailCondition") AbsenceSchoolVO abDetailCondition
		, @ModelAttribute("srDetailCondition") SchoolRegisterVO srDetailCondition
		, StudentVO student
		, SubjectVO subject
		, CollegeVO college
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
		, @RequestParam String searchSe
	) {
		
		// 휴학 신청
		if(searchSe.equals("tab-1")) {
			
			subject.setCollege(college);
			student.setSubject(subject);
			abDetailCondition.setStudent(student);
						
			PaginationInfo<AbsenceSchoolVO> paging = new PaginationInfo<>(10,5);
			paging.setDetailCondition(abDetailCondition);
			paging.setCurrentPage(currentPage);
			
			service.retrieveAbsenceRequestList(paging);
			
			paging.setRenderer(new BootstrapPaginationRenderer());
			
			model.addAttribute("paging", paging);
			
		// 복학 신청	
		} else {
			
			subject.setCollege(college);
			student.setSubject(subject);
			srDetailCondition.setStudent(student);
			
			PaginationInfo<SchoolRegisterVO> paging = new PaginationInfo<>(10,5);
			paging.setDetailCondition(srDetailCondition);
			paging.setCurrentPage(currentPage);
			
			service.retrieveBackToSchoolRequestList(paging);
			
			paging.setRenderer(new BootstrapPaginationRenderer());
			
			model.addAttribute("paging", paging);
		}
		
		return "jsonView";
		
	}
	
	// 휴학 신청 모달 불러오는 메소드
	@GetMapping("ajax/absenceRequestView")
	public String absenceRequestView(
		@RequestParam("what") String abssklNo
		, Model model		
	) {
		AbsenceSchoolVO absenceInfo = service.retrieveAbsenceInfo(abssklNo);
		
		model.addAttribute("absenceInfo", absenceInfo);
		
		return"/ajax/staff/student/absenceRequestView";
	}
	
	// 복학 신청 모달 불러오는 메소드
	@GetMapping("ajax/backToSchoolRequestView")
	public String backToSchoolRequestView(
		@RequestParam("what") String sknrgNo
		, Model model		
	) {
		SchoolRegisterVO backToSchoolInfo = service.retrieveBackToSchoolInfo(sknrgNo);
		
		model.addAttribute("backToSchoolInfo", backToSchoolInfo);
		
		return"/ajax/staff/student/backToSchoolRequestView";
	}
	
	// 신청 반려 (신청 정보 업데이트) 메소드
	@PostMapping("updateRefusedRequest")
	public String updateRefusedRequest(
		AbsenceSchoolVO abssklNo
		, SchoolRegisterVO sknrgNo
		, Model model
	) {
		if(abssklNo.getAbssklNo() != null && !abssklNo.getAbssklNo().equals("")) {	// 	휴학 신청
			try {
				service.modifyRefusedAbsenceInfo(abssklNo);
				model.addAttribute("success", true);
			}catch (Exception e) {
				model.addAttribute("success", false);
			}
		}else {		// 복학신청 
			try {
				service.modifyRefusedBackToSchoolInfo(sknrgNo);
				model.addAttribute("success", true);
			}catch (Exception e) {
				model.addAttribute("success", false);
			}
		}
				
		return "jsonView";
	}
	
	// 휴학 신청 승인 (휴학 신청 정보 업데이트, 학적 정보 인서트)
	@PostMapping("updateAppliedAbsenceRequest")
	public String updateAppliedAbsenceRequest(
		AbsenceSchoolVO abssklNo
		, SchoolRegisterHistoryVO history
		, Model model
	) {
		try {			
			service.modifyAppliedAbsenceInfo(abssklNo, history);
			model.addAttribute("success", true);
		}catch (Exception e) {
			model.addAttribute("success", false);
		}
		
		return "jsonView";
	}
	
	// 복학 신청 승인 (복학 신청 정보 업데이트, 학적 정보 인서트)
	@PostMapping("updateAppliedBackToSchoolRequest")
	public String updateAppliedBackToSchoolRequest(
		SchoolRegisterVO sknrgNo
		, SchoolRegisterHistoryVO history
		, Model model
	) {
		log.info("/////////////////////////////{}", sknrgNo);
		
		try {			
			service.modifyAppliedBackToSchoolInfo(sknrgNo, history);
			model.addAttribute("success", true);
		}catch (Exception e) {
			model.addAttribute("success", false);
		}
		return "jsonView";
	}

}
























