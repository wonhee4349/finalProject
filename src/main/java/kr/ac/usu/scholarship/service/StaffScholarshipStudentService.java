package kr.ac.usu.scholarship.service;

import java.util.List;

import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.scholarship.vo.ScholarshipRequestVO;
import kr.ac.usu.scholarship.vo.ScholarshipStudentVO;
import kr.ac.usu.scholarship.vo.ScholarshipVO;
import kr.ac.usu.user.vo.StudentVO;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 16.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 16.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
public interface StaffScholarshipStudentService {

	// 장학생 리스트
	public void retrieveScholarshipStudent(PaginationInfo<ScholarshipStudentVO> paging);
	
	// 장학생 인적 정보
	public ScholarshipStudentVO retrieveScholarshipStudentInfo(String stdntNo);
	                            
	// 장학생 학적 정보
	public ScholarshipStudentVO retrieveScholarshipStudentStatus(String stdntNo);
	
	// 장학생 장학금 정보
	public ScholarshipStudentVO retrieveScholarshipStudentScholarship(String stdntNo);
	
	// 장학생 추가 장학금 셀렉트박스 불러오기
	public List<ScholarshipVO> retrieveScholarshipList();
	
	// 셀렉트박스에서 고른 장학금 정보 불러오기
	public ScholarshipVO retrieveScholarship(String schlshipNo);
	
	// 추가 장학생 정보
	public StudentVO retrieveStudent(String stdntNo);
	
	// 추가 장학생 인서트
	public int createScholarshipStudent(ScholarshipStudentVO scholarshipStudent);
	
	
	//------------------------------------------------------------------------------------------------
	
	// 장학생 신청 리스트
	public void retrieveScholarshipRequestStudent(PaginationInfo<ScholarshipRequestVO> paging);
	
	// 장학생 신청 장학학기 셀렉트박스 불러오기
	public List<ScholarshipVO> retrieveScholarshipSemesterInfo();
}
