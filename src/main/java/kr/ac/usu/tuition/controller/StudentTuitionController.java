package kr.ac.usu.tuition.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.tuition.service.StudentTuitionService;
import kr.ac.usu.tuition.vo.TuitionVO;
import kr.ac.usu.user.mapper.LoginMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 학생 등록금 관련 기능 컨트롤러
 * @author 김석호
 * @since 2023. 11. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 11. 24.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Controller
@Slf4j
public class StudentTuitionController {

	@Inject
	private StudentTuitionService service;
	
	@GetMapping("/student/tuition")
	public String sudentTuitionPage(
			Authentication auth
			, Model model
		) {
		List<Map<String, String>> list = service.retrieveSemesterListForStudentTuition(auth.getName(), LoginMapper.getCurrentRegularSemesterCode());
		model.addAttribute("list", list);
		model.addAttribute("currSemCd", LoginMapper.getPrintSemCd(LoginMapper.getCurrentRegularSemesterCode(), "_"));
		
		return "student/tuition/tuitionList";
	}
	
	@GetMapping("/student/tuition/{semCd}")
	public String getStudentTuitionInformation(
			Authentication auth
			, Model model
			, @PathVariable String semCd
		) {
		String viewName = null;
		TuitionVO tuition = new TuitionVO();
		tuition.setStdntNo(auth.getName());
		log.info("semCd 값 : {}",semCd);
		if("curr".equals(semCd)) {
			tuition.setSemstrNo(LoginMapper.getCurrentRegularSemesterCode());
			viewName = "iframe/studentCurrTuitionDetailView";
		}else {
			tuition.setSemstrNo(semCd);
			viewName = "iframe/studentTuitionDetailView";
		}
		tuition = service.retrieveTuitionInformationForStudentWithSemesterCode(tuition);
		model.addAttribute("tuitionInfo", tuition);
		log.info("{}",tuition);
		return viewName;
	}
	
}
