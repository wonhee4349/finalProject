package kr.ac.usu.student.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.StudentVO;

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
 * 2023. 11. 9.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StaffGraduationMapper {
	
	// 페이징
	public int selectTotalRecord(PaginationInfo<StudentVO> paging);
	
	// 졸업생 리스트
	public List<StudentVO> selectGraduationList(PaginationInfo<StudentVO> paging);

	// 졸업생 인적 정보
	public StudentVO selectGraduationInfo(@Param("studentNo") String stdntNo);
	
	// 졸업생 학적 정보
	public StudentVO selectGraduationRegisterStatus(@Param("studentNo") String stdntNo);
	
	// 졸업생 장학금 정보
	public StudentVO selectGraduationScholarship(@Param("studentNo") String stdntNo);
	
	// 졸업생 등록금 정보
	public StudentVO selectGraduationTuition(@Param("studentNo") String stdntNo);
	
	// 졸업생 동아리 정보
	public StudentVO selectGraduationClub(@Param("studentNo") String stdntNo);
	
	// 공통코드 셀렉트박스 가져오기
	public List<ComCodeVO> selectComCode(@Param("comCodeGrp") String ComCodeGrp);
	
	// 단과대 목록 가져오기
	public List<CollegeVO> selectCollegeList();
	
	// 학과 목록 가져오기
	public List<SubjectVO> selectSubjectList();
	
	// 수강 강의 목록
	public List<LectureVO> selectLectureList(@Param("studentNo") String stdntNo);
}
































