package kr.ac.usu.professor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.ProfessorVO;

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
public interface ProfessorMypageMapper {
	
	// 교수 인적 정보
	public ProfessorVO selectProfessorInfo(@Param("prfsorNo") String prfsorNo);
	
	// 공통코드 셀렉트박스 가져오기
	public List<ComCodeVO> selectComCode(@Param("comCodeGrp") String ComCodeGrp);
	
	// 내정보 수정
	public int updateMypage(ProfessorVO prfsorNo);
	
	// 비밀번호 수정
	public int updatePass(ProfessorVO professor);

}
