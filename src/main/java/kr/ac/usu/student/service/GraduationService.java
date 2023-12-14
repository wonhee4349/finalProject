package kr.ac.usu.student.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.paging.vo.PaginationInfo;
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
public interface GraduationService {
	
	// 	졸업생 리스트
	public void retrieveGraduationList(PaginationInfo<StudentVO> paging);
	
	// 졸업생 인적 정보
	public StudentVO retrieveGraduationInfo(String stdntNo);
	
	// 졸업생 학정 정보
	public StudentVO retrieveGraduationRegisterStatus(String stdntNo);
	
	// 졸업생 장학금 정보
	public StudentVO retrieveGraduationScholarship(String stdntNo);
	
	// 졸업생 등록금 정보
	public StudentVO retrieveGraduationTuition(String stdntNo);
	
	// 졸업생 동아리 정보
	public StudentVO retrieveGraduationClub(String stdntNo);
	
	// 수강 강의 목록
	public List<LectureVO> retrieveLectureList(String stdntNo);

}
