package kr.ac.usu.student.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.student.vo.SchoolRegisterHistoryVO;
import kr.ac.usu.student.vo.SchoolRegisterVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.StudentVO;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 24.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 24.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Mapper
public interface StaffRegisterStatusRequestMapper {
	
	// 복수전공 신청 페이징
	public int selectDoubleMajorRequestTotalRecord(PaginationInfo<SchoolRegisterVO> paging);
	
	// 부전공 신청 페이징
	public int selectMinorRequestTotalRecord(PaginationInfo<SchoolRegisterVO> paging);
	
	// 전과 신청 페이징
	public int selectMoveRequestTotalRecord(PaginationInfo<SchoolRegisterVO> paging);
	
	// 복수전공 신청 리스트
	public List<SchoolRegisterVO> selectDoubleMajorRequestList(PaginationInfo<SchoolRegisterVO> paging);
	
	// 부전공 신청 리스트
	public List<SchoolRegisterVO> selectMinorRequestList(PaginationInfo<SchoolRegisterVO> paging);
	
	// 전과 신청 리스트
	public List<SchoolRegisterVO> selectMoveMajorRequestList(PaginationInfo<SchoolRegisterVO> paging);
	
	// 학적변동 신청 정보
	public SchoolRegisterVO selectRequestInfo(@Param("sknrgNo") String sknrgNo);
	
	// 학적변동 신청 반려 (학적변동 신청 정보 업데이트)
	public int updateRefusedRequestInfo(SchoolRegisterVO sknrgNo);
	
	// 학적변동 신청 승인 (학적변동 업데이트)
	public int updateAppliedRequestInfo(SchoolRegisterVO sknrgNo);
	
	// 학적변동 신청 승인 (학적변동 인서트)
	public int insertAppliedRequestInfo(SchoolRegisterHistoryVO history);
	
	// 공통코드 셀렉트박스 가져오기
	public List<ComCodeVO> selectComCode(@Param("comCodeGrp") String ComCodeGrp);
	
	// 단과대 목록 가져오기
	public List<CollegeVO> selectCollegeList();
	
	// 학과 목록 가져오기
	public List<SubjectVO> selectSubjectList();
	
	// 학적변동 업데이트 (복수전공)
	public int updateDoubleMajorStatus(StudentVO stdntNo);
	
	// 학적변동 업데이트 (부전공)
	public int updateMinorStatus(StudentVO stdntNo);
	
	// 학적변동 업데이트 (전과)
	public int updateMoveMajorStatus(StudentVO stdntNo);
	
	
	/*
	// 부전공 신청 정보
	public SchoolRegisterVO selectMinorRequestInfo(@Param("sknrgNo") String sknrgNo);
	
	// 전과 신청 정보
	public SchoolRegisterVO selectMoveMajorRequestInfo(@Param("sknrgNo") String sknrgNo);
			
	// 부전공 신청 반려 (학적변동 신청 정보 업데이트)
	public int updateRefusedMinorRequestInfo(SchoolRegisterVO sknrgNo);
	
	// 전과 신청 반려 (학적변동 신청 정보 업데이트)
	public int updateRefusedMoveMajorRequestInfo(SchoolRegisterVO sknrgNo);
	
	// 복수전공 신청 승인 (학적변동 신청 정보 업데이트)
	public int updateAppliedDoubleMajorRequestInfo(SchoolRegisterVO sknrgNo);
			
	// 부전공 신청 승인 (학적변동 신청 정보 업데이트)
	public int updateAppliedMinorRequestInfo(SchoolRegisterVO sknrgNo);
	
	// 부전공 신청 승인 (학적변동 인서트)
	public int insertAppliedMinorRequestInfo(SchoolRegisterHistoryVO history);
	
	// 전과 신청 승인 (학적변동 신청 정보 업데이트)
	public int updateAppliedMoveMajorRequestInfo(SchoolRegisterVO sknrgNo);
	
	// 전과 신청 승인 (학적변동 인서트)
	public int insertAppliedMoveMajorRequestInfo(SchoolRegisterHistoryVO history);
	
	
	*/
}
