package kr.ac.usu.staff.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface StaffStaffMapper {

	public List<ComCodeVO> selectComCode(@Param("comCodeGrp") String comCodeGroup);
	public int selectTotalRecord(PaginationInfo<StaffVO> paging);
	

	public List<StaffVO> selectStaffList(PaginationInfo<StaffVO> paging);	
	
	public StaffVO selectStaff(@Param("sklstfNo") String sklstfNo);
	
	public int insertStaff(StaffVO staff);
	public int updateStaffPassword(StaffVO staff);

	public int updateStaff(StaffVO staff);
	
}
