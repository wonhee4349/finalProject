package kr.ac.usu.mainPage.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.mainPage.mapper.StaffCalendarNoticeMapper;
import kr.ac.usu.mainPage.service.StaffCalendarNoticeService;
import kr.ac.usu.schoolaffairsschedule.vo.CalendarVO;
import kr.ac.usu.schoolaffairsschedule.vo.SchoolAffairsScheduleVO;
import kr.ac.usu.user.vo.ComCodeVO;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author PC-25
 *
 * @author 김원희
 * @since 2023. 11. 29.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 29.      작성자명       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class StaffCalendarNoticeServiceImpl implements StaffCalendarNoticeService{
	@Inject
	private final StaffCalendarNoticeMapper CalendarDAO;
	
	
	/*
	 * @Override public List<BoardVO> retrieveNoticeCalendar() { List<BoardVO> data
	 * = CalendarDAO.retrieveNoticeCalendar();
	 * 
	 * return data; }
	 */
	@Override
	public List<ComCodeVO> selectComCode(String comCodeGroup) {
		List<ComCodeVO> comdata = CalendarDAO.selectComCode(comCodeGroup);
		return comdata;
	}

	@Override
	public List<CalendarVO> selectStaffSchoolAffairsScheduleList() {
		return CalendarDAO.selectStaffSchoolAffairsScheduleList();
	}

	@Override
	public List<BoardVO> retrieveCommonBoardList() {
		List<BoardVO> comBoardList = CalendarDAO.selectCommonBoardList();
		return comBoardList;
	}

	@Override
	public List<BoardVO> retrieveNoticeBoardList() {
		List<BoardVO> noticeBoardList = CalendarDAO.selectNoticeBoardList();
		return noticeBoardList;
	}

	





}
