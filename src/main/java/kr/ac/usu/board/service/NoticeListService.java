package kr.ac.usu.board.service;

import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.mail.vo.EmailRoomVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.user.vo.ProfessorVO;

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
public interface NoticeListService {

	public void retrieveNoticeList(PaginationInfo<BoardVO> paging);
	
	public void retrieveCommonNoticeList(PaginationInfo<BoardVO> paging);
	
	public BoardVO retrieveNotice(String boNo);
	
	public BoardVO retrieveCommonNotice(String boNo);
	
	public int createNotice(BoardVO board);
	
	public int modifyNotice(BoardVO board);
	
	public int modifyCommonNotice(BoardVO board);
	
	public int removeNotice(BoardVO board) ;
	
	public int removeCommonNotice(BoardVO board) ;
	
	
}
