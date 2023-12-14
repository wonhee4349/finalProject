package kr.ac.usu.student.service;

import java.util.List;

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
 * 2023. 11. 7.      작성자명       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public interface StudentService {

	// 학생 리스트
	public void retrieveStudentList(PaginationInfo<StudentVO> paging);
	
	// 학생 인적 정보
	public StudentVO retrieveStudentInfo(String stdntNo);
	
	// 학생 학적 정보
	public StudentVO retrieveStudentRegisterStatus(String stdntNo);
	
	// 학생 장학금 정보
	public StudentVO retrieveStudentScholarship(String stdntNo);
	
	// 학생 등록금 정보
	public StudentVO retrieveStudentTuition(String stdntNo);
	
	// 학생 동아리 정보
	public StudentVO retrieveStudentClub(String stdntNo);
	
	// 수강 강의 목록
	public List<LectureVO> retrieveLectureList(String stdntNo);
}
