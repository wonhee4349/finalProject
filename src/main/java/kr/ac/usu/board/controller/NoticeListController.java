package kr.ac.usu.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.swing.border.Border;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.board.mapper.NoticeListMapper;
import kr.ac.usu.board.service.NoticeListService;
import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.mail.vo.EmailRoomVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StaffVO;
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
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/staff/board")
@Slf4j
public class NoticeListController {

	@Inject
	private NoticeListService service;
	
	@Inject
	private NoticeListMapper mapper;
	
	@GetMapping("ajax/staffNoticeList")
	public String listData(
		@ModelAttribute("detailCondition") BoardVO detailCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		
		StaffVO staff = new StaffVO();
		detailCondition.setStaff(staff);
		
		PaginationInfo<BoardVO> paging = new PaginationInfo<>(10,5);
		paging.setDetailCondition(detailCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveNoticeList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@GetMapping("ajax/staffCommonNoticeList")
	public String CommonlistData(
			@ModelAttribute("detailCondition") BoardVO detailCondition
			, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
			, Model model
			) {
		
		StaffVO staff = new StaffVO();
		detailCondition.setStaff(staff);
		
		PaginationInfo<BoardVO> paging = new PaginationInfo<>(10,5);
		paging.setDetailCondition(detailCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveCommonNoticeList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@GetMapping("/noticeView/{boNo}")
	public String boardView(
		@PathVariable String boNo
		, Model model
	){
		BoardVO notice = service.retrieveNotice(boNo);
//		List<ComCodeVO> bdse = mapper.selectComCode("SEC008"); 
		
//		model.addAttribute("bdse", bdse);
		model.addAttribute("board", notice);
		
		return "staff/board/staffNoticeView";
	}
	
	@GetMapping("/commonNoticeView/{boNo}")
	public String boardCommonView(
			@PathVariable String boNo
			, Model model
			){
		BoardVO notice = service.retrieveCommonNotice(boNo);
//		List<ComCodeVO> bdse = mapper.selectComCode("SEC008"); 
		
//		model.addAttribute("bdse", bdse);
		model.addAttribute("board", notice);
		
		return "staff/board/staffCommonNoticeView";
	}
	
	@PostMapping("/staffNoticeInsertData")
	public String staffNoticeData(
		BoardVO prof
		, Model model
		, Authentication auth
	) {
		prof.setBoWrter(auth.getName());
		log.info("{}",prof);
		try {
			int res = service.createNotice(prof);
			model.addAttribute("success", true);
			model.addAttribute("message", "성공하였습니다");
		} catch (Exception e) {
			log.info("{}",e);
			model.addAttribute("success", false);
			model.addAttribute("message", "실패하였습니다");
		}
		return "jsonView";
	}
	
	@PostMapping("/staffNoticeUpdateData")
	public String staffNoticeUpdateData(
		BoardVO prof
		, Model model
	) {
		try {
			int res = service.modifyNotice(prof);
			model.addAttribute("success", true);
			model.addAttribute("message", "수정되었습니다");
		} catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "실패하였습니다");
		}
		return "jsonView";
	}
	
	@RequestMapping("ajax/noticeUpdateView")
	public String memberView(
		@RequestParam("who") String boNo
		, Model model
	){
		BoardVO board = service.retrieveNotice(boNo);
		List<ComCodeVO> bdse = mapper.selectComCode("SEC008");
		List<StaffVO> staff = mapper.selectStaffList();
		
		model.addAttribute("bdse", bdse);
		model.addAttribute("board", board);
		model.addAttribute("staff", staff);
		
		return "/ajax/staff/board/noticeUpdateView";
	}
	@PostMapping("/staffCommonNoticeUpdateData")
	public String staffCommonNoticeUpdateData(
			BoardVO prof
			, Model model
			) {
		try {
			int res = service.modifyCommonNotice(prof);
			model.addAttribute("success", true);
			model.addAttribute("message", "수정되었습니다");
		} catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "실패하였습니다");
		}
		return "jsonView";
	}
	
	@RequestMapping("ajax/commonNoticeUpdateView")
	public String memberCommonView(
			@RequestParam("who") String boNo
			, Model model
			){
		BoardVO board = service.retrieveCommonNotice(boNo);
		List<ComCodeVO> bdse = mapper.selectComCode("SEC008");
		List<StaffVO> staff = mapper.selectStaffList();
		
		model.addAttribute("bdse", bdse);
		model.addAttribute("board", board);
		model.addAttribute("staff", staff);
		
		return "/ajax/staff/board/commonNoticeUpdateView";
	}
	

	
}
