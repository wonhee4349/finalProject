package kr.ac.usu.tuition.service;

import org.apache.ibatis.annotations.Param;

import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.schoolaffairsschedule.vo.SemesterVO;
import kr.ac.usu.tuition.vo.TuitionVO;
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
public interface StaffTuitionStudentService {
	
	// 납부대상자 리스트
	public void retrieveTuitionStudentList(PaginationInfo<StudentVO> paging);
	
	// 납부대상자 정보
	public StudentVO retrieveTuitionStudentInfo(String stdntNo);
	
	// 납부대상자 등록금 불러오기
	public StudentVO retrieveTuitionStudentScholarshipInfo(@Param("studentNo") String stdntNo);

	// 납부 학기 정보
	public SemesterVO retrieveTuitionSemesterInfo();
	
	// 납부대상자 등록
	public int createTuition(TuitionVO tuition);
}
