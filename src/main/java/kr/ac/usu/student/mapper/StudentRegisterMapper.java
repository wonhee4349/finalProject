package kr.ac.usu.student.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.student.vo.SchoolRegisterVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.StudentVO;

/**
 * 휴학을 제외한 학적 변동 관련 처리 Mapper
 * @author 김석호
 * @since 2023. 11. 14.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일         수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 14.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StudentRegisterMapper {
	
	public List<SchoolRegisterVO> selectRegisterRequestList(@Param("stdntNo") String id);
	public SchoolRegisterVO selectRegisterRequest(SchoolRegisterVO register);
	public int insertRegisterRequest(SchoolRegisterVO register);
	public int deleteRegisterRequest(SchoolRegisterVO register);
	
	public List<SubjectVO> selectRequestableSubject(StudentVO student);
	public List<CollegeVO> selectCollegeList();
	public int selecteDuplicateRequest(SchoolRegisterVO register);
}
