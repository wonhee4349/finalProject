package kr.ac.usu.board.service;

import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.paging.vo.PaginationInfo;


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
 * 2023. 11. 7.      작성자명       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public interface StudentNoticeListService {


	public void retrieveCommonNoticeList(PaginationInfo<BoardVO> paging);
	public BoardVO retrieveCommonNotice(String boNo);
	

}
