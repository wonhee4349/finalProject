package kr.ac.usu.club.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import kr.ac.usu.club.vo.ClubVO;
import kr.ac.usu.paging.vo.PaginationInfo;

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
 * 2023. 11. 7.      김원희      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

@Mapper
public interface StudentClubMapper {
	
	// 페이징
		public int selectTotalRecord(PaginationInfo<ClubVO> paging);
		
		// 동아리 리스트
		public List<ClubVO> selectClubList(PaginationInfo<ClubVO> paging);

		// 동아리 정보
		public ClubVO selectClubInfo(@Param("clubNo") String clubNo);
		
		// 동아리 회원 정보
		public List<ClubVO> selectClubMember(@Param("clubNo") String clubNo);

}
