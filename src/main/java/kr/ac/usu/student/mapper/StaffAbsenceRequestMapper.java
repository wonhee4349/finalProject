package kr.ac.usu.student.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.student.vo.AbsenceSchoolVO;
import kr.ac.usu.student.vo.SchoolRegisterHistoryVO;
import kr.ac.usu.student.vo.SchoolRegisterVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 23.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 23.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Mapper
public interface StaffAbsenceRequestMapper {
	
	// 휴학 신청 페이징
	public int selectAbsenceRequestTotalRecord(PaginationInfo<AbsenceSchoolVO> paging);
	
	// 복학 신청 페이징
	public int selectBackToSchoolRequestTotalRecord(PaginationInfo<SchoolRegisterVO> paging);
	
	// 휴학 신청 리스트
	public List<AbsenceSchoolVO> selectAbsenceRequestList(PaginationInfo<AbsenceSchoolVO> paging);
	
	// 복학 신청 리스트
	public List<SchoolRegisterVO> selectBackToSchoolRequestList(PaginationInfo<SchoolRegisterVO> paging);

	// 휴학 신청 정보
	public AbsenceSchoolVO selectAbsenceInfo(@Param("abssklNo") String abssklNo);
	
	// 복학 신청 정보
	public SchoolRegisterVO selectBackToSchoolInfo(@Param("sknrgNo") String sknrgNo);
	
	// 휴학 신청 반려 (휴학 신청 정보 업데이트)
	public int updateRefusedAbsenceInfo(AbsenceSchoolVO abssklNo);
	
	// 복학 신청 반려 (복학 신청 정보 업데이트)
	public int updateRefusedBackToSchoolInfo(SchoolRegisterVO sknrgNo);
	
	// 휴학 신청 승인 (휴학 신청 정보 업데이트)
	public int updateAppliedAbsenceInfo(AbsenceSchoolVO abssklNo);
	
	// 휴학 신청 승인 (휴학 신청 정보 인서트)
	public int insertAppliedAbsenceInfo(SchoolRegisterHistoryVO history);
	
	// 복학 신청 승인 (복학 신청 정보 업데이트)
	public int updateAppliedBackToSchoolInfo(SchoolRegisterVO sknrgNo);
	
	// 복학 신청 승인 (복학 신청 정보 인서트)
	public int insertAppliedBackToSchoolInfo(SchoolRegisterHistoryVO history);
	
	// 공통코드 셀렉트박스 가져오기
	public List<ComCodeVO> selectComCode(@Param("comCodeGrp") String ComCodeGrp);
	
	// 단과대 목록 가져오기
	public List<CollegeVO> selectCollegeList();
	
	// 학과 목록 가져오기
	public List<SubjectVO> selectSubjectList();
}























