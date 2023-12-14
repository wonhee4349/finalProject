package kr.ac.usu.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StaffVO;
import kr.ac.usu.user.vo.StudentVO;

//@Mapper
/**
 * 처음 데이터 입력할때 암호화 안하고 넣은것들을 일괄적으로 변경하기 위해 만든 맵퍼
 * @author 김석호
 * @since 2023. 11. 10.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일         수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 10.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public interface UtilForChange {
	public List<StudentVO> selectStudentList();
	public List<ProfessorVO> selectProfessorList();
	
	public int updateStudentPassword(StudentVO target);
	
	public int updateProfessorPassword(ProfessorVO target);
	
	
	
//	public List<StaffVO> selectStaffList();
}
