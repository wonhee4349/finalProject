package kr.ac.usu.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.mail.vo.EmailRoomVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StaffVO;

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
@Mapper
public interface NoticeListMapper {

	public List<ComCodeVO> selectComCode(@Param("comCodeGrp") String comCodeGroup);
	
	public int selectTotalRecord(PaginationInfo<BoardVO> paging);
	public List<BoardVO> selectNoticeList(PaginationInfo<BoardVO> paging);

	public int selectCommonTotalRecord(PaginationInfo<BoardVO> paging);
	public List<BoardVO> selectCommonNoticeList(PaginationInfo<BoardVO> paging);
	
	public BoardVO selectNotice(@Param("boNo") String boNo);
	
	public BoardVO selectCommonNotice(@Param("boNo") String boNo);
	
	public int updateCntNotice(@Param("boNo") String boNo);

	public int insertNotice(BoardVO board);
	
	public int updateNotice(BoardVO board);

	public int updateCommonNotice(BoardVO board);
	public List<StaffVO> selectStaffList();
	
	public int removeNotice(BoardVO board) ;
	
	public int removeCommonNotice(BoardVO board) ;
}
