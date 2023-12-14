package kr.ac.usu.course.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.common.mapper.CommonOptionsMapper;
import kr.ac.usu.course.mapper.ProfessorCourseMapper;
import kr.ac.usu.course.service.ProfessorCourseService;
import kr.ac.usu.course.vo.CourseRequestVO;
import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author PC-21
 *
 * @author 김재성
 * @since 2023. 11. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 *   수정일           수정자            수정내용
 * ----------    --------    ----------------------
 * 2023.11.19.     김재성       최초작성
 * 2023.11.20.     김재성       교과목 개설신청 리스트 및 교과목 개설신청 등록
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/professor/course")
public class ProfessorCourseController {
	
	@Inject
	private ProfessorCourseService service;
	@Inject
	private ProfessorCourseMapper mapper;
	@Inject
	private CommonOptionsMapper comOptionMapper;
	
	// 교과목 개설신청 학과 리스트 
	public List<SubjectVO> selectSubjectList(){
		
		List<SubjectVO> subjectList = mapper.selectSubjectList();
		
		return subjectList;
	}
	
	public List<ComCodeVO> selectComCode(){
			
		return mapper.selectComCode("SEC014");
	}
	
	
	
	// 교과목 목록 UI
	@GetMapping("/courseListUI")
	public String courseListUI() {
		return "professor/course/professorCourseUI";
	}
	
	// 교과목 검색조건 페이징 리스트 
	@GetMapping("/ajax/courseList")
	public String courseList(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		
		PaginationInfo<CourseVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		log.info("검색 받아오는 값 : - simpleCondition : {}",simpleCondition);
		
		service.retrieveCourseList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 교과목 상세내역
	@RequestMapping(value="/ajax/courseView", method = RequestMethod.GET)
	public String courseView(
		@RequestParam("courseNo") String courseNo
		, Model model
	) {
		
		log.info("교과목번호 가져온 값 : {}", courseNo);
		
		CourseVO course = service.retrieveCourse(courseNo);
		
		log.info("courseVO : {}" ,course);
		
		model.addAttribute("course",course);
		
		return "ajax/professor/course/professorCourseView";
	}
	
	//------------------------------------------------------------------------
	
	// 교과목 개설신청 UI
	@GetMapping("/courseRequestUI")
	public String courseRequestUI(
			Model model
			,String comCodeGrp
			,Authentication principle
	) {
		
		//단과대학 구분
		List<CollegeVO> college = comOptionMapper.getCollegeList();
		
		
		log.info("******************************************************** {}", selectComCode());
		log.info("*******************************************************{}", selectSubjectList());
		
		model.addAttribute("college",college);
		model.addAttribute("confmSe",selectComCode());
		model.addAttribute("subjectList",selectSubjectList());
		
		return "professor/course/professorCourseRequestUI";
	}
	
	// 교과목 개설 신청 상세내역
	@RequestMapping(value="/ajax/courseRequestView", method = RequestMethod.GET)
	public String courseRequestView(
		@RequestParam("courseReqstNo") String courseReqstNo
		, Model model
		, Authentication principal
	) {
		
		log.info("교과목번호 가져온 값 : {}", courseReqstNo);
		
		String prfsorNo = principal.getName();
		
		CourseRequestVO courseRequestVO = new CourseRequestVO();
		courseRequestVO.setPrfsorNo(prfsorNo);
		courseRequestVO.setCourseReqstNo(courseReqstNo);
		
		CourseRequestVO courseRequest = service.retrieveCourseRequest(courseRequestVO);
		log.info("courseRequestVO : {}" ,courseRequest);
		
		model.addAttribute("courseRequest",courseRequest);
		
		return "ajax/professor/course/professorCourseRequestView";
	}
	
	// 교과목 개설신청 리스트
	@GetMapping("/ajax/courseRequestList")
	public String courseRequestList(
			@ModelAttribute("simpleCondition") SearchVO simpleCondition
			, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
			, Model model
			, Authentication principle
	) {
		
		PaginationInfo<CourseRequestVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		String prfsorNo = principle.getName();
		CourseRequestVO courseRequestVO = new CourseRequestVO();
		courseRequestVO.setPrfsorNo(prfsorNo);
		
		paging.setDetailCondition(courseRequestVO);
		
		log.info("검색 받아오는 값 : - simpleCondition : {}",simpleCondition);
		
		service.retrieveCourseRequestList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
		
	}
	
	//교과목 개설 신청
	@PostMapping("/new")
	public String ProfessorCourseInsert(
		CourseRequestVO courseRequest
		,String clgNo
		,Errors errors
		,Model model
		,Authentication principle
	){	

		log.info("단과대학번호 나오기 전 : {}",clgNo);
		courseRequest.setClgNo(clgNo);
		log.info("단과대학번호 나오기 후 : {}",clgNo);
		
		try {			
			service.createCourseRequest(courseRequest);
			model.addAttribute("success", true);
			model.addAttribute("message", "등록 완료!");
		}catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "등록 실패!");
		}
		return "jsonView";
	}
	
}
