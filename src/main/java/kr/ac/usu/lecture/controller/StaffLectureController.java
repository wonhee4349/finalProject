package kr.ac.usu.lecture.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.course.service.StaffCourseService;
import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.lecture.service.StaffLectureService;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import kr.ac.usu.user.vo.ProfessorVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 7.      작성자명       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/staff/lecture")
public class StaffLectureController {
	
	@Inject
	private StaffLectureService service;
	
	@GetMapping("lectureListUI")
	public String lectureListUI(HttpServletRequest req) {
		return "staff/lecture/lectureListUI";
	}
	@GetMapping("ajax/lectureList")
	public String listData(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		
		PaginationInfo<LectureVO> paging = new PaginationInfo<>(10,5);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveLectureList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("ajax/lectureView")
	public String memberView(
		@RequestParam("who") String lctreNo
		, Model model
	){
		LectureVO lecture = service.retrieveLecture(lctreNo);
		log.info("강의번호 : {}",lctreNo);
		
		model.addAttribute("lecture", lecture);
		
		return "/ajax/staff/lecture/lectureView";
	}

}
