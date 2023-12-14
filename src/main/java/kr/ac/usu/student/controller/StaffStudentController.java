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
import kr.ac.usu.student.mapper.StaffStudentMapper;
import kr.ac.usu.student.service.StudentService;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.StudentVO;
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
 * 2023. 11. 9.		 문선영		  수정
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/staff/student")
public class StaffStudentController {
	
	@Inject
	private StudentService service;
	
	@Inject
	private StaffStudentMapper mapper;
	
	// 학생 UI 불러오는 메소드
	@GetMapping("/studentListUi")
	public String studentView(Model model) {
				
		List<CollegeVO> college = mapper.selectCollegeList();
		List<SubjectVO> subject = mapper.selectSubjectList();
		List<ComCodeVO> nltySe = mapper.selectComCode("SEC002");
		List<ComCodeVO> genderSe = mapper.selectComCode("SEC001");
		
		model.addAttribute("college",college);
		model.addAttribute("subject",subject);
		model.addAttribute("nltySe",nltySe);
		model.addAttribute("genderSe",genderSe);
		
		return "staff/student/studentListUI";
	}
	
	// 학생 리스트 불러오는 메소드
	@GetMapping("ajax/studentList")
	public String listData(
		@ModelAttribute("detailCondition") StudentVO detailCodition
		, CollegeVO college
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		
		SubjectVO subject = new SubjectVO();
		subject.setCollege(college);
		detailCodition.setSubject(subject);
		
		PaginationInfo<StudentVO> paging = new PaginationInfo<>(10,5);
		paging.setDetailCondition(detailCodition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveStudentList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 학생 상세 페이지 조회하는 메소드
	@RequestMapping("ajax/studentView")
	public String memberView(
		@RequestParam("who") String stdntNo
		, Model model
	){
		StudentVO studentInfo = service.retrieveStudentInfo(stdntNo);		// 학생 인적 정보
		StudentVO studentRegister = service.retrieveStudentRegisterStatus(stdntNo);		// 학생 학적 정보
		StudentVO studentScholarship = service.retrieveStudentScholarship(stdntNo);		// 학생 장학금 정보
		StudentVO studentTuition = service.retrieveStudentTuition(stdntNo);				// 학생 등록금 정보
		StudentVO studentClub = service.retrieveStudentClub(stdntNo);		// 학생 동아리 정보
		List<LectureVO> lectureList = service.retrieveLectureList(stdntNo);
		
		
		model.addAttribute("studentInfo", studentInfo);
		model.addAttribute("studentRegister", studentRegister);
		model.addAttribute("studentScholarship", studentScholarship);
		model.addAttribute("studentTuition", studentTuition);
		model.addAttribute("studentClub", studentClub);
		model.addAttribute("lectureList",lectureList);
		
		return "ajax/staff/student/studentView";
	}
	
	// 신입생 등록 UI 불러오는 메소드
	@GetMapping("/studentInsertUI")
	public String studentInsertView() {		
		return "staff/student/studentInsertUI";
	}

}
