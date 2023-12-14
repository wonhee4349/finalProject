package kr.ac.usu.mainPage.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.mainPage.mapper.ProfessorMainPageMapper;
import kr.ac.usu.mainPage.service.ProfessorMainPageService;
import kr.ac.usu.schoolaffairsschedule.vo.CalendarVO;
import kr.ac.usu.user.vo.ComCodeVO;
import lombok.RequiredArgsConstructor;

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
@Service
@RequiredArgsConstructor
public class ProfessorMainPageServiceImpl implements ProfessorMainPageService{
	
	@Inject
	private final ProfessorMainPageMapper mapper;

	@Override
	public List<ComCodeVO> selectComCode(String comCodeGroup) {
		List<ComCodeVO> comdata = mapper.selectComCode(comCodeGroup);
		return comdata;
	}

	@Override
	public List<CalendarVO> selectProfessorSchoolAffairsScheduleList() {
		return mapper.selectProfessorSchoolAffairsScheduleList();
	}

	@Override
	public List<BoardVO> retrieveCommonBoardList() {
		List<BoardVO> comBoardList = mapper.selectCommonBoardList();
		return comBoardList;
	}

	@Override
	public List<BoardVO> retrieveNoticeBoardList() {
		List<BoardVO> noticeBoardList = mapper.selectNoticeBoardList();
		return noticeBoardList;
	}

}
