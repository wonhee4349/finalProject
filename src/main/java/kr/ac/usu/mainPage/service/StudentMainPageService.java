package kr.ac.usu.mainPage.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.mainPage.vo.PortletVO;
import kr.ac.usu.schoolaffairsschedule.vo.CalendarVO;
import kr.ac.usu.user.vo.ComCodeVO;

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
public interface StudentMainPageService {
	
	public List<ComCodeVO> selectComCode(@Param("comCodeGrp") String comCodeGroup);

	public List<CalendarVO> selectStudentSchoolAffairsScheduleList();
	
	// 일반공지 리스트
	public List<BoardVO> retrieveCommonBoardList();
	
	// 학사공지 리스트
	public List<BoardVO> retrieveNoticeBoardList();
	
	// 위젯 위치 저장 (인서트)
	public int createWidget(List<PortletVO> portlet, String id);
	
	// 저장된 위젯 정보 불러오기
	public List<PortletVO> retrieveSavedWidgetInfo(String userNo);

}
