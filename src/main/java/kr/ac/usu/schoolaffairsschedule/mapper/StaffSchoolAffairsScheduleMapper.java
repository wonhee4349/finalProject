package kr.ac.usu.schoolaffairsschedule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.schoolaffairsschedule.vo.SchoolAffairsScheduleVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.ProfessorVO;


/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 22.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StaffSchoolAffairsScheduleMapper {

	public int selectTotalRecord(PaginationInfo<SchoolAffairsScheduleVO> paging);
	
	public List<ComCodeVO> selectComCode(@Param("comCodeGrp") String comCodeGroup);
	
	public List<SchoolAffairsScheduleVO> selectStaffSchoolAffairsScheduleList(PaginationInfo<SchoolAffairsScheduleVO> paging);
	public int insertSchoolAffairsSchedule(SchoolAffairsScheduleVO schoolaffairsschedule);
	
	public int removeSchoolAffairsSchedule(SchoolAffairsScheduleVO schoolaffairsschedule) ;
	
}
