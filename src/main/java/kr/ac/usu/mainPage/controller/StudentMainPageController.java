package kr.ac.usu.mainPage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.mainPage.service.StudentMainPageService;
import kr.ac.usu.mainPage.vo.PortletVO;
import kr.ac.usu.schoolaffairsschedule.vo.CalendarVO;
import kr.ac.usu.user.mapper.LoginMapper;
import kr.ac.usu.user.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 27.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 27.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Slf4j
@Controller
public class StudentMainPageController {
	
	@Inject
	private StudentMainPageService service;
	
	// 메인페이지 이동 (저장되어 있는 위젯 정보 불러오기)
	@GetMapping("/student")
	public String studentMainPage() {					
		return "student/indexStudent";
	}
	@GetMapping("/staff")
	public String staffMainPage() {					
		return "staff/indexStaff";
	}
	@GetMapping("/professor")
	public String professorfMainPage() {					
		return "professor/indexProfessor";
	}
	
	@GetMapping("/student/ajax/studentMain")
    @ResponseBody
	public List<CalendarVO> selectStaffSchoolAffairsScheduleList(){  
    	return service.selectStudentSchoolAffairsScheduleList();
   }
		
	// 위젯 정보 불러오기
	@GetMapping("/widgetLoad")
	public String widgetLoad(
		Authentication auth
		, Model model
	) {
		String stdntNo = auth.getName();
		List<PortletVO> portletList = service.retrieveSavedWidgetInfo(stdntNo);
		
		model.addAttribute("portletList", portletList);
		
		return "jsonView";
	}
	
	
	// 일반공지 리스트
	@GetMapping("student/mainPage/commonBoardList")
	public String commonBoardList(Model model) {
		List<BoardVO> common = service.retrieveCommonBoardList();
		
		model.addAttribute("common", common);
		
		return "jsonView";
	}
	
	// 학사공지 리스트
	@GetMapping("student/mainPage/commonNoticeList")
	public String commonNoticeList(Model model) {
		List<BoardVO> notice = service.retrieveNoticeBoardList();
		
		model.addAttribute("notice", notice);
		
		return "jsonView";
	}
	
	// 위젯 위치 저장
	@PostMapping("student/mainPage/saveWidget")
	public String saveWidget(
		Authentication auth
		, @RequestBody List<PortletVO> portlet
		, Model model
	) {
		
		String userNo = auth.getName();
		

		try {
			service.createWidget(portlet, userNo);
			model.addAttribute("success", true);
			model.addAttribute("message", "등록 완료!");
		}catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "등록 실패!");
		}

		
		return "jsonView";
	}
	
	

}




























