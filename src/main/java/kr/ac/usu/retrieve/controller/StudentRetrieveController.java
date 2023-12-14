package kr.ac.usu.retrieve.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.retrieve.service.StudentRetrieveService;
import kr.ac.usu.score.vo.ScoreRankVO;
import kr.ac.usu.user.mapper.LoginMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 학생 조회전용 컨트롤러
 * @author 김석호
 * @since 2023. 12. 1.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 12. 1.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
public class StudentRetrieveController {
	
	@Inject
	private StudentRetrieveService service;
	
	@GetMapping("/student/class")
	public String studentClassListUI(
			Model model
			, Authentication auth
		) {
		List<Map<String, String>> list = service.retrieveStudentSemesterList(auth.getName());
		model.addAttribute("list", list);
		return "student/course/class";
	}
	
	@GetMapping("/student/letureList/{semCd}")
	public String studentLectureListOnOneSemCd(
			Model model
			, Authentication auth
			, @PathVariable String semCd
		) {
		String id = auth.getName();
		List<LectureVO> list = service.retriveStudentLectureListOnOneSemester(id, semCd);
		model.addAttribute("list", list);
		return "jsonView";
	}
	
	@GetMapping("/student/timetable")
	public String studentLectureTimeTable(
			Authentication auth
			, Model model
		) {
		String semCd = LoginMapper.getCurrentRegularSemesterCode();
		List<Map<String, String>> data = service.retrieveStudentTimetable(auth.getName(), semCd);
		model.addAttribute("data", data);
		
		return "jsonView";
	}
	
}
