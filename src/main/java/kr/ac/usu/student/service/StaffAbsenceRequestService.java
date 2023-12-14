package kr.ac.usu.student.service;

import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.student.vo.AbsenceSchoolVO;
import kr.ac.usu.student.vo.SchoolRegisterHistoryVO;
import kr.ac.usu.student.vo.SchoolRegisterVO;

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
public interface StaffAbsenceRequestService {
	
	// 휴학 신청 리스트
	public void retrieveAbsenceRequestList(PaginationInfo<AbsenceSchoolVO> paging);

	// 복학 신청 리스트
	public void retrieveBackToSchoolRequestList(PaginationInfo<SchoolRegisterVO> paging);
	
	// 휴학 신청 정보
	public AbsenceSchoolVO retrieveAbsenceInfo(String abssklNo);
	
	// 복학 신청 정보
	public SchoolRegisterVO retrieveBackToSchoolInfo(String sknrgNo);
	
	// 휴학 신청 반려 (휴학 신청 정보 업데이트)
	public int modifyRefusedAbsenceInfo(AbsenceSchoolVO abssklNo);
	
	// 복학 신청 반려 (복학 신청 정보 업데이트)
	public int modifyRefusedBackToSchoolInfo(SchoolRegisterVO sknrgNo);
	
	// 휴학 신청 승인 (휴학 신청 정보 업데이트, 학적 정보 인서트)
	public int modifyAppliedAbsenceInfo(AbsenceSchoolVO abssklNo, SchoolRegisterHistoryVO history);
	
	// 복학 신청 승인 (복학 신청 정보 업데이트, 학적 정보 인서트)
	public int modifyAppliedBackToSchoolInfo(SchoolRegisterVO sknrgNo, SchoolRegisterHistoryVO history);


}
