package kr.ac.usu.lecture.service;

import java.util.List;

import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.lecture.vo.LectureRequestVO;
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
 * 수정일              수정자               수정내용
 * --------        --------    ----------------------
 * 2023. 11. 7.      이재혁      최초작성
 * 2023. 11.10.      김재성 	   클래스 타입 수정
 * 2023. 11.11.      김재성 	   service 생성
 * 2023. 11.11.      김재성 	   매개변수 변경
 * 2023. 11.17.   	 김재성      교수 강의 수강학생 목록 조회 메서드 생성
 * 2023. 11.24.   	 김재성      강의개설 신청 등록 insert, 강의개설 신청목록 select
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public interface ProfessorLecturetService {
	
	/**
	 *  한 교수의 강의중인 내역 리스트 페이징 년도 학기 검색 조건
	 * @param paging
	 * @return paging
	 */
	public void retrieveLectureList(PaginationInfo<LectureVO> paging);
	
	/**
	 * 교수의 강의에 수강중인 학생목록 리스트 조회
	 * @param lctreNo 교수번호
	 * @return List<LectureVO> studentList
	 */
	public List<StudentVO> retrieveLectureStudentList(String lctreNo);
	
	/**
	 *  교수의 강의 개설 신청 목록 조회
	 * @param paging
	 * @return List<LectureRequestVO>
	 */
	public void retrieveLectureRequestList(PaginationInfo<LectureRequestVO> paging);
	
	/**
	 * 교수의 강의개설 신청 상세 조회
	 * @param lectureRequest
	 * @return LectureRequestVO
	 */
	public LectureRequestVO retrieveLectureRequestView(LectureRequestVO lectureRequest);

	
	/**
	 * 강의 개설 신청 ( 강의개설 신청, 강의계획서, 강의개설 시간표, 강의개설 성적평가기준 )
	 * @param lectureRequestVO
	 * @return
	 */
	public int newLectureRequest(LectureRequestVO lectureRequestVO);

	
}
