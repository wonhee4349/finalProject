package kr.ac.usu.lecture.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.usu.lecture.service.StudentLectureListService;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.user.mapper.LoginMapper;
import kr.ac.usu.user.vo.StudentVO;
import kr.ac.usu.user.vo.wrapper.StudentVOWrapper;

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
 * 수정일          수정자      수정내용
 * --------     --------    ----------------------
 * 2023. 11. 07. 이재혁      최초작성
 * 2023. 11. 10. 김석호      필요메소드 작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Controller
public class StudentLectureListController {
	
	@Inject
	private StudentLectureListService service;
	
	@GetMapping("/student/ajax/LectureList")
	public String studentLectureList(
		Model model
		, Authentication auth
	) {
		String semCd = LoginMapper.getCurrentRegularSemesterCode();
		List<LectureVO> list = service.retrieveStudentLectureList(auth.getName(),semCd);
		model.addAttribute("list", list);
		return "jsonView";
	}
	
	
	
	
}
