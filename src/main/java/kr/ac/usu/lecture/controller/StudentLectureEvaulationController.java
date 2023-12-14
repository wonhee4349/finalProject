package kr.ac.usu.lecture.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.lecture.service.StudentLectureEvaulationService;
import kr.ac.usu.lecture.vo.LectureEvaulationItemVO;
import kr.ac.usu.lecture.vo.LectureEvaulationScoreVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.score.service.StudentScoreService;
import kr.ac.usu.user.mapper.LoginMapper;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/student/courseEvaluation")
@Slf4j
public class StudentLectureEvaulationController {
	
	@Inject
	private StudentLectureEvaulationService service;
	
	@Inject
	private StudentScoreService scoreService;
	
	@GetMapping
	public String getStudentLectureEvaulationPage(
			Model model
			, Authentication auth
		) {
		String semCd = LoginMapper.getCurrentRegularSemesterCode();
		List<LectureVO> lectureList = service.retrieveLectrueListForEvaulation(auth.getName(), semCd);
		List<LectureEvaulationItemVO> items = service.retrieveLectureEvaulationItems();
		List<LectureEvaulationScoreVO> scores = service.retrieveLectureEvaulationScores();
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("items", items);
		model.addAttribute("scores", scores);
		
		boolean availability = scoreService.retrieveAvailabilityForSelectCurrentSemesterScore(auth.getName(), semCd);
		model.addAttribute("result", availability);
		
		return "student/course/courseEvaluation";
	}
	
	@PostMapping
	public String studentLectureEvaulation(
			Model model
			, @RequestParam Map<String, String> param
		) {
		boolean result = false;
		try {
			service.createLectureEvaulation(param);
			result = true;
		} catch (Exception e) {
			log.info("오류 발생! {}");
		}
		model.addAttribute("result", result);
		return "jsonView";
	}
	
}
