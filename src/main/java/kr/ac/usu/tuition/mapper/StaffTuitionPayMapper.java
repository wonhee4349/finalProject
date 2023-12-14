package kr.ac.usu.tuition.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.tuition.vo.TuitionVO;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 22.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 22.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Mapper
public interface StaffTuitionPayMapper {
	
	// 페이징
	public int selectTotalRecord(PaginationInfo<TuitionVO> paging);
	
	// 납부여부 리스트
	public List<TuitionVO> selectTuitionList(PaginationInfo<TuitionVO> paging);
	
	// 납부 정보
	public TuitionVO selectTuitionInfo(@Param("tutnNhtNo") String tutnNhtNo);
	
	// 납부 정보 수정
	public int updateTuitionInfo(TuitionVO tutnNhtNo);
	
	// 단과대 목록 가져오기
	public List<CollegeVO> selectCollegeList();
	
	// 학과 목록 가져오기
	public List<SubjectVO> selectSubjectList();

}
