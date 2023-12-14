package kr.ac.usu.board.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.usu.board.mapper.NoticeListMapper;
import kr.ac.usu.board.service.NoticeListService;
import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.mail.vo.EmailRoomVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.professor.mapper.StaffProfessorMapper;
import kr.ac.usu.user.vo.ProfessorVO;
import lombok.RequiredArgsConstructor;

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
@Service
@RequiredArgsConstructor
public class NoticeListServiceImpl implements NoticeListService {
	
	private final NoticeListMapper NoticeDAO;

	@Override
	public void retrieveNoticeList(PaginationInfo<BoardVO> paging) {
		
		int tatalRecord = NoticeDAO.selectTotalRecord(paging);
		paging.setTotalRecord(tatalRecord);
		List<BoardVO> dataList = NoticeDAO.selectNoticeList(paging);
		paging.setDataList(dataList);
	}
	@Override
	public void retrieveCommonNoticeList(PaginationInfo<BoardVO> paging) {
		
		int tatalRecord = NoticeDAO.selectTotalRecord(paging);
		paging.setTotalRecord(tatalRecord);
		List<BoardVO> dataList = NoticeDAO.selectCommonNoticeList(paging);
		paging.setDataList(dataList);
	}
	
	@Override
	public BoardVO retrieveNotice(String boNo) {
		BoardVO notice = NoticeDAO.selectNotice(boNo);
		NoticeDAO.updateCntNotice(boNo);
		return notice;
	}
	@Override
	public BoardVO retrieveCommonNotice(String boNo) {
		BoardVO notice = NoticeDAO.selectCommonNotice(boNo);
		NoticeDAO.updateCntNotice(boNo);
		return notice;
	}
	@Override
	@Transactional
	public int createNotice(BoardVO board) {
		int res = NoticeDAO.insertNotice(board);
		return res;
	}
	
	@Override
	public int modifyNotice(BoardVO board) {
	int res = NoticeDAO.updateNotice(board);
	return res;
	
	}
	@Override
	public int modifyCommonNotice(BoardVO board) {
		int res = NoticeDAO.updateCommonNotice(board);
		return res;
		
	}
	@Override
	public int removeNotice(BoardVO board) {
		
		return NoticeDAO.removeNotice(board);
	}
	@Override
	public int removeCommonNotice(BoardVO board) {
		
		return NoticeDAO.removeCommonNotice(board);
	}
}
