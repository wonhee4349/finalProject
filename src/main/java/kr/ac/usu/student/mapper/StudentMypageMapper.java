package kr.ac.usu.student.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.StudentVO;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 29.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 29.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Mapper
public interface StudentMypageMapper {
	
	// 학생 인적 정보
	public StudentVO selectStudentInfo(@Param("studentNo") String stdntNo);
	
	// 학생 학적 정보
	public StudentVO selectStudentRegisterStatus(@Param("studentNo") String stdntNo);

	// 공통코드 셀렉트박스 가져오기
	public List<ComCodeVO> selectComCode(@Param("comCodeGrp") String ComCodeGrp);
	
	// 내정보 수정
	public int updateMypage(StudentVO stdntNo);
	
	// 비밀번호 수정
	public int updatePass(StudentVO student);
	
	
}
