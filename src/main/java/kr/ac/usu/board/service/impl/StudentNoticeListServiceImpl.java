package kr.ac.usu.board.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.usu.board.mapper.NoticeListMapper;
import kr.ac.usu.board.mapper.StudentNoticeListMapper;
import kr.ac.usu.board.service.NoticeListService;
import kr.ac.usu.board.service.StudentNoticeListService;
import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.professor.mapper.StaffProfessorMapper;
import kr.ac.usu.user.vo.ProfessorVO;
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
public class StudentNoticeListServiceImpl implements StudentNoticeListService {
	
	private final StudentNoticeListMapper NoticeDAO;


	@Override
	public void retrieveCommonNoticeList(PaginationInfo<BoardVO> paging) {
		
		int tatalRecord = NoticeDAO.selectTotalRecord(paging);
		paging.setTotalRecord(tatalRecord);
		List<BoardVO> dataList = NoticeDAO.selectCommonNoticeList(paging);
		paging.setDataList(dataList);
	}




	@Override
	public BoardVO retrieveCommonNotice(String boNo) {
		BoardVO notice = NoticeDAO.selectCommonNotice(boNo);
		return notice;
	}
	
}
