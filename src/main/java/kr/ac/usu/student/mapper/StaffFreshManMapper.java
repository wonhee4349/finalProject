package kr.ac.usu.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.student.vo.FreshManVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.StudentVO;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 25.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 25.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Mapper
public interface StaffFreshManMapper {
	
	// 신입생 리스트 
	public List<StudentVO> selectFreshManList();
	
	// 공통코드 셀렉트박스 가져오기
	public List<ComCodeVO> selectComCode(@Param("comCodeGrp") String ComCodeGrp);

	// 단과대 목록 가져오기
	public List<CollegeVO> selectCollegeList();
	
	// 학과 목록 가져오기
	public List<SubjectVO> selectSubjectList();
	
	// 엑셀 업로드
	public int excelUpload(List<FreshManVO> paramMap);
	
	// 개별 등록
	public int insertFreshMan(StudentVO freshMan);
	
}
