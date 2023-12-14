package kr.ac.usu.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.board.mapper.NoticeListMapper;
import kr.ac.usu.board.service.NoticeListService;
import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.mail.service.MailService;
import kr.ac.usu.professor.mapper.StaffProfessorMapper;
import kr.ac.usu.professor.service.ProfessorListService;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StaffVO;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 23.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Controller
public class NoticeListUIController {
	
	@Inject
	private NoticeListService service;
	
	@Inject
	private NoticeListMapper mapper;
	
	@GetMapping("/staff/staffNoticeListUI")
	public String noticeView(Model model) {
		
		List<StaffVO> staff = mapper.selectStaffList();
		
		model.addAttribute("staff",staff);
		
		return "staff/board/staffNoticeListUI";
	}
	
	@GetMapping("/staff/staffCommonNoticeListUI")
	public String commonNoticeView(Model model) {
		
		List<StaffVO> staff = mapper.selectStaffList();
		
		model.addAttribute("staff",staff);
		
		return "staff/board/staffCommonNoticeListUI";
	}
	

	@GetMapping("/staff/board/staffNoticeInsertUI")
	public String staffNoticeInsertUI(
			Model model
		) {
		
		List<ComCodeVO> bdse = mapper.selectComCode("SEC008");
		 
		
		model.addAttribute("bdse", bdse);
		
		
		return "staff/board/staffNoticeInsertUI";
	}
	
	@GetMapping("/ajax/staff/board/noticeUpdateView")
	public String staffNoticeUpdateView(
			Model model
		) {
		
		List<ComCodeVO> bdse = mapper.selectComCode("SEC008");
		List<StaffVO> staff = mapper.selectStaffList(); 
		
		model.addAttribute("bdse", bdse);
		model.addAttribute("staff", staff);
		
		
		return "/ajax/staff/board/noticeUpdateView";
	
	}
	@GetMapping("/ajax/staff/board/commonNoticeUpdateView")
	public String staffCommonNoticeUpdateView(
			Model model
			) {
		
		List<ComCodeVO> bdse = mapper.selectComCode("SEC008");
		List<StaffVO> staff = mapper.selectStaffList(); 
		
		model.addAttribute("bdse", bdse);
		model.addAttribute("staff", staff);
		
		
		return "/ajax/staff/board/commonNoticeUpdateView";
		
		
	}
	

	
}
