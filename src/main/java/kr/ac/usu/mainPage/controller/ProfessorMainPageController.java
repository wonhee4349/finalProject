package kr.ac.usu.mainPage.controller;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.mainPage.mapper.ProfessorMainPageMapper;
import kr.ac.usu.mainPage.service.ProfessorMainPageService;
import kr.ac.usu.schoolaffairsschedule.vo.CalendarVO;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 12. 5.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 12. 5.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Slf4j
@Controller
@RequestMapping("/professor")
public class ProfessorMainPageController {
	
	@Inject
	private ProfessorMainPageService service;
	
	@Inject
	private ProfessorMainPageMapper mapper;
	
	@GetMapping("/ajax/professorMain")
	@ResponseBody
	public List<CalendarVO> selectProfessorSchoolAffairsScheduleList(){ 
    	return service.selectProfessorSchoolAffairsScheduleList();
   }
	
	// 일반공지 리스트
 	@GetMapping("/mainPage/commonBoardList")
 	public String commonBoardList(Model model) {

 		List<BoardVO> common = service.retrieveCommonBoardList();
 		
 		model.addAttribute("common", common);
 		
 		return "jsonView";
 	}
 	
 	// 학사공지 리스트
  	@GetMapping("/mainPage/commonNoticeList")
  	public String commonNoticeList(Model model) {
  		List<BoardVO> notice = service.retrieveNoticeBoardList();
  		
  		model.addAttribute("notice", notice);
  		
  		return "jsonView";
  	}

}
