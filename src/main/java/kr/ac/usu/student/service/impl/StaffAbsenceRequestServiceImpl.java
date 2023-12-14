package kr.ac.usu.student.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.student.mapper.StaffAbsenceRequestMapper;
import kr.ac.usu.student.service.StaffAbsenceRequestService;
import kr.ac.usu.student.vo.AbsenceSchoolVO;
import kr.ac.usu.student.vo.SchoolRegisterHistoryVO;
import kr.ac.usu.student.vo.SchoolRegisterVO;
import lombok.RequiredArgsConstructor;

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
@Service
@RequiredArgsConstructor
public class StaffAbsenceRequestServiceImpl implements StaffAbsenceRequestService{
	
	private final StaffAbsenceRequestMapper absenceMapper;
	
	// 휴학 신청 리스트
	@Override
	public void retrieveAbsenceRequestList(PaginationInfo<AbsenceSchoolVO> paging) {
		int totalRecord = absenceMapper.selectAbsenceRequestTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<AbsenceSchoolVO> dataList = absenceMapper.selectAbsenceRequestList(paging);
		paging.setDataList(dataList);
	}

	// 복학 신청 리스트
	@Override
	public void retrieveBackToSchoolRequestList(PaginationInfo<SchoolRegisterVO> paging) {
		int totalRecord = absenceMapper.selectBackToSchoolRequestTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<SchoolRegisterVO> dataList = absenceMapper.selectBackToSchoolRequestList(paging);
		paging.setDataList(dataList);
	}

	// 휴학 신청 정보
	@Override
	public AbsenceSchoolVO retrieveAbsenceInfo(String abssklNo) {
		AbsenceSchoolVO absenceInfo = absenceMapper.selectAbsenceInfo(abssklNo);
		return absenceInfo;
	}

	// 복학 신청 정보
	@Override
	public SchoolRegisterVO retrieveBackToSchoolInfo(String sknrgNo) {
		SchoolRegisterVO backToSchoolInfo = absenceMapper.selectBackToSchoolInfo(sknrgNo);
		return backToSchoolInfo;
	}

	// 휴학 신청 반려 (휴학 신청 정보 업데이트)
	@Override
	public int modifyRefusedAbsenceInfo(AbsenceSchoolVO abssklNo) {
		int res = absenceMapper.updateRefusedAbsenceInfo(abssklNo);
		return res;
	}

	// 복학 신청 반려 (복학 신청 정보 업데이트)
	@Override
	public int modifyRefusedBackToSchoolInfo(SchoolRegisterVO sknrgNo) {
		int res = absenceMapper.updateRefusedBackToSchoolInfo(sknrgNo);
		return res;
	}

	// 휴학 신청 승인 (휴학 신청 정보 업데이트, 학적 정보 인서트)
	@Override
	public int modifyAppliedAbsenceInfo(AbsenceSchoolVO abssklNo, SchoolRegisterHistoryVO history) {
		history.setStdntNo(abssklNo.getStdntNo());
		
		int res = absenceMapper.updateAppliedAbsenceInfo(abssklNo);
		res += absenceMapper.insertAppliedAbsenceInfo(history);
		
		return res;
	}

	// 복학 신청 승인 (복학 신청 정보 업데이트, 학적 정보 인서트)
	@Override
	public int modifyAppliedBackToSchoolInfo(SchoolRegisterVO sknrgNo, SchoolRegisterHistoryVO history) {
		history.setStdntNo(sknrgNo.getStdntNo());
		
		int res = absenceMapper.updateAppliedBackToSchoolInfo(sknrgNo);
		res += absenceMapper.insertAppliedBackToSchoolInfo(history);
		
		return res;
	}

	

}
