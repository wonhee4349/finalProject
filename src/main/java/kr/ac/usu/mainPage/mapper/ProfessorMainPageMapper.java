package kr.ac.usu.mainPage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.schoolaffairsschedule.vo.CalendarVO;
import kr.ac.usu.user.vo.ComCodeVO;

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
@Mapper
public interface ProfessorMainPageMapper {
	
	public List<ComCodeVO> selectComCode(@Param("comCodeGrp") String comCodeGroup);

	public List<CalendarVO> selectProfessorSchoolAffairsScheduleList();
	
	// 일반공지 리스트
	public List<BoardVO> selectCommonBoardList();
	
	// 학사공지 리스트
	public List<BoardVO> selectNoticeBoardList();

}
