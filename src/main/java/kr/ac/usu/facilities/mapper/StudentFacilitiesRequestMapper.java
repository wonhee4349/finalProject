package kr.ac.usu.facilities.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.club.vo.ClubEstblVO;
import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.facilities.vo.BuildingVO;
import kr.ac.usu.facilities.vo.FacilitiesRequestVO;
import kr.ac.usu.facilities.vo.FacilitiesVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.ProfessorVO;

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
public interface StudentFacilitiesRequestMapper {

public int selectTotalRecord(PaginationInfo<FacilitiesRequestVO> paging);
	


	//시설물예약
	public int createFacilitiesRequest(FacilitiesRequestVO facilit);
	
	// 상담 신청 리스트
	public List<FacilitiesRequestVO> selectFacilitiesRequestList(PaginationInfo<FacilitiesRequestVO> paging);

	
}
