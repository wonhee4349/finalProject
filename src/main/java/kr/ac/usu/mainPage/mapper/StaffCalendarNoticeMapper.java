package kr.ac.usu.mainPage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.schoolaffairsschedule.vo.CalendarVO;
import kr.ac.usu.schoolaffairsschedule.vo.SchoolAffairsScheduleVO;
import kr.ac.usu.user.vo.ComCodeVO;

/**
 * 
 * @author PC-25
 *
 * @author 김원희
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
@Mapper
public interface StaffCalendarNoticeMapper {


	public List<ComCodeVO> selectComCode(@Param("comCodeGrp") String comCodeGroup);

	public List<CalendarVO> selectStaffSchoolAffairsScheduleList();
	
	// 일반공지 리스트
	public List<BoardVO> selectCommonBoardList();
	
	// 학사공지 리스트
	public List<BoardVO> selectNoticeBoardList();

	
}
