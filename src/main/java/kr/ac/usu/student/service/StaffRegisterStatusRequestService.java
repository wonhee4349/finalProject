package kr.ac.usu.student.service;

import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.student.vo.SchoolRegisterHistoryVO;
import kr.ac.usu.student.vo.SchoolRegisterVO;
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
public interface StaffRegisterStatusRequestService {
	
	// 복수전공 신청 리스트
	public void retrieveDoubleMajorRequestList(PaginationInfo<SchoolRegisterVO> paging);
	
	// 부전공 신청 리스트
	public void retrieveMinorRequestList(PaginationInfo<SchoolRegisterVO> paging);
	
	// 전과 신청 리스트
	public void retrieveMoveMajorRequestList(PaginationInfo<SchoolRegisterVO> paging);
	
	// 학적변동 신청 정보
	public SchoolRegisterVO retrieveRequestInfo(String sknrgNo);
		
	// 학적변동 신청 반려 (학적변동 신청 정보 업데이트)
	public int modifyRefusedRequestInfo(SchoolRegisterVO sknrgNo);
	
	// 학적변동 신청 승인 (학적변동 신청 정보 업데이트, 학적변동 인서트)
	public int modifyAppliedRequestInfo(SchoolRegisterVO sknrgNo, SchoolRegisterHistoryVO history, StudentVO stdntNo);
	
	
	
	
	
	
	
	
//	// 부전공 신청 정보
//	public SchoolRegisterVO retrieveMinorRequestInfo(String sknrgNo);
//	
//	// 전과 신청 정보
//	public SchoolRegisterVO retrieveMoveMajorRequestInfo(String sknrgNo);
	
//	// 부전공 신청 반려 (학적변동 신청 정보 업데이트)
//	public int modifyRefusedMinorRequestInfo(SchoolRegisterVO sknrgNo);
//	
//	// 전과 신청 반려 (학적변동 신청 정보 업데이트)
//	public int modifyRefusedMoveMajorRequestInfo(SchoolRegisterVO sknrgNo);
//	
//	
//	
//	// 부전공 신청 승인 (학적변동 신청 정보 업데이트, 학적변동 인서트)
//	public int modifyAppliedMinorRequestInfo(SchoolRegisterVO sknrgNo, SchoolRegisterHistoryVO history);
//	
//	// 전과 신청 승인 (학적변동 신청 정보 업데이트, 학적변동 인서트)
//	public int modifyAppliedMoveMajorRequestInfo(SchoolRegisterVO sknrgNo, SchoolRegisterHistoryVO history);

	
	
	
	
}
