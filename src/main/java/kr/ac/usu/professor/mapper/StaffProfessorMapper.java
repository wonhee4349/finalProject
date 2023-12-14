package kr.ac.usu.professor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.club.vo.ClubVO;
import kr.ac.usu.facilities.vo.FacilitiesVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.student.vo.AbsenceSchoolVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
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
@Mapper
public interface StaffProfessorMapper {
	
public int selectTotalRecord(PaginationInfo<ProfessorVO> paging);
	
	public List<ComCodeVO> selectComCode(@Param("comCodeGrp") String comCodeGroup);
	public List<SubjectVO> selectSubjectList();

	public List<ProfessorVO> selectProfessorList(PaginationInfo<ProfessorVO> paging);

	public ProfessorVO selectProfessor(@Param("prfsorNo") String prfsorNo);
	
	public int insertProfessor(ProfessorVO professor);
	
	public int updateProfessorPassword(ProfessorVO professor);
	
	public int updateProfessor(ProfessorVO professor);
	
	
}
