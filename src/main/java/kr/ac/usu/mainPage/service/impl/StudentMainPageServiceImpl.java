package kr.ac.usu.mainPage.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.mainPage.mapper.StudentMainPageMapper;
import kr.ac.usu.mainPage.service.StudentMainPageService;
import kr.ac.usu.mainPage.vo.PortletVO;
import kr.ac.usu.schoolaffairsschedule.vo.CalendarVO;
import kr.ac.usu.user.vo.ComCodeVO;
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
@Service
public class StudentMainPageServiceImpl implements StudentMainPageService{
	
	@Inject
	private StudentMainPageMapper mapper;
	
	// 일반공지 리스트
	@Override
	public List<BoardVO> retrieveCommonBoardList() {
		List<BoardVO> comBoardList = mapper.selectCommonBoardList();
		return comBoardList;
	}

	// 학사공지 리스트
	@Override
	public List<BoardVO> retrieveNoticeBoardList() {
		List<BoardVO> noticeBoardList = mapper.selectNoticeBoardList();
		return noticeBoardList;
	}

	// 위젯 위치 저장 (인서트)
	@Override
	public int createWidget(List<PortletVO> portlet, String id) {
		
		int check = mapper.checkSavedWidget(id);
		int res = 0;
		
		if(check>0) {
			for(PortletVO portletVO : portlet) {
				portletVO.setUserNo(id);
				res += mapper.updateWidget(portletVO);			
			}
		}else {
			for(PortletVO portletVO : portlet) {
				portletVO.setUserNo(id);
				res += mapper.insertWidget(portletVO);	
			}
		}
	
		return res;
	}

	// 저장된 위젯 정보 불러오기
	@Override
	public List<PortletVO> retrieveSavedWidgetInfo(String userNo) {
		List<PortletVO> portlet = mapper.selectSavedWidgetInfo(userNo);
		return portlet;
	}

	@Override
	public List<ComCodeVO> selectComCode(String comCodeGroup) {
		List<ComCodeVO> comdata = mapper.selectComCode(comCodeGroup);
		return comdata;
	}

	@Override
	public List<CalendarVO> selectStudentSchoolAffairsScheduleList() {
		return mapper.selectStudentSchoolAffairsScheduleList();
	}

}
