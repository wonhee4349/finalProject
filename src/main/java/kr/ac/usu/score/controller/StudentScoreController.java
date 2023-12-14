package kr.ac.usu.score.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.retrieve.service.StudentRetrieveService;
import kr.ac.usu.score.service.StudentScoreService;
import kr.ac.usu.score.vo.ScoreObjectionVO;
import kr.ac.usu.score.vo.ScoreRankVO;
import kr.ac.usu.score.vo.ScoreVO;
import kr.ac.usu.user.mapper.LoginMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class StudentScoreController {
	
	@Inject
	private StudentScoreService service;
	
	@Inject
	private StudentRetrieveService retrieveService;
	
	
	
	@GetMapping("/student/totalScore")
	public String studentTotalScoreView(
			Authentication auth
			, Model model
		) {
		String currSemCd = LoginMapper.getCurrentSemesterCode();
		List<Map<String, String>> dataList = service.retrieveSemCdList(auth.getName(), currSemCd);
		log.info("{}",dataList);
		model.addAttribute("dataList", dataList);
		model.addAttribute("dataMap", retrieveService.retrieveStudentInformationAboutScore(auth.getName(), currSemCd));
		model.addAttribute("totalScore", retrieveService.retrieveTotalScore(auth.getName()));
		return "student/score/studentTotalScore";
	}
	
	@GetMapping("/student/ajax/scoreList/{semCd}")
	public String studentScoreList(
			Authentication auth
			, @PathVariable String semCd
			, Model model
		) {
		List<ScoreVO> list = service.retrieveScoreList(auth.getName(), semCd);
		double calScore = service.retrieveCalScore(auth.getName(), semCd);
		ScoreRankVO rank = retrieveService.retrieveStudentRankInformation(auth.getName(), semCd);
		model.addAttribute("dataList", list);
		model.addAttribute("calScore", calScore);
		model.addAttribute("rank", rank);
		return "jsonView";
	}
	
	@GetMapping("/student/currScore")
	public String getStudentCurrentSemesterScore(
			Model model
			, Authentication auth
		) {
		String currSemCd = LoginMapper.getCurrentRegularSemesterCode();
		String printCurrSemCd = LoginMapper.getPrintSemCd(currSemCd, " ");
		log.info(printCurrSemCd);
		model.addAttribute("currSemCd", currSemCd);
		model.addAttribute("printCurrSemCd", printCurrSemCd);
		return "student/score/studentCurrScore";
	}
	
	@GetMapping("/student/ajax/checkLectureEvaulation/{semCd}")
	public String checkLectureEvaulaionBeforeGetCurrentSemesterScore(
			@PathVariable String semCd
			, Model model
			, Authentication auth
		) {
		boolean availability = service.retrieveAvailabilityForSelectCurrentSemesterScore(auth.getName(), semCd);
		model.addAttribute("result", availability);
		return "jsonView";
	}
	
	@GetMapping("/student/scoreObjection/{lctreNo}")
	public String getScoreObjectionInfo(
			@PathVariable String lctreNo
			, Authentication auth
			, Model model
		) {
		ScoreObjectionVO objectionInfo = service.retrieveScoreObjectionInformation(auth.getName(), lctreNo);
		model.addAttribute("objectionInfo", objectionInfo);
		return "jsonView";
	}
	
	@PostMapping("/student/scoreObjection")
	public String studentScoreObjection(
			ScoreObjectionVO scoreObjection
			, Authentication auth
			, Model model
			) {
		log.info("넘어온 이의제기 값  : {}",scoreObjection);
		scoreObjection.setStdntNo(auth.getName());
		ServiceResult result = service.createScoreObjection(scoreObjection);
		model.addAttribute("result", result);
		return "jsonView";
	}
}
