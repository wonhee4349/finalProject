package kr.ac.usu.schoolaffairsschedule.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.ac.usu.professor.mapper.StaffProfessorMapper;
import kr.ac.usu.schoolaffairsschedule.mapper.StaffSchoolAffairsScheduleMapper;
import kr.ac.usu.schoolaffairsschedule.service.StaffSchoolAffairsScheduleService;
import kr.ac.usu.schoolaffairsschedule.vo.SchoolAffairsScheduleVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 22.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Controller
public class StaffSchoolAffairsScheduleUIController {
	
	@Inject
	private StaffSchoolAffairsScheduleMapper mapper;
	
	@Inject
	private StaffSchoolAffairsScheduleService service;

	@GetMapping("/staff/staffSchoolAffairsScheduleUI")
	public String staffSchoolAffairsScheduleView(Model model) {

		List<ComCodeVO> scduSe = mapper.selectComCode("SEC010");
		
		model.addAttribute("scduSe",scduSe);
		
		
		return "staff/schoolaffairsschedule/staffSchoolAffairsScheduleListUI";
		
	}
	
	@GetMapping("/staff/schoolaffairsschedule/staffSchoolAffairsScheduleInsertUI")
	public String staffSchoolAffairsScheduleInsertUI(
			Model model
		) {
		
		List<ComCodeVO> scduse = mapper.selectComCode("SEC010");
		
		model.addAttribute("scdu", scduse);
		
		
		return "staff/schoolaffairsschedule/staffSchoolAffairsScheduleInsertUI";
	}
}
