package kr.ac.usu.student.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.student.mapper.StaffRegisterStatusRequestMapper;
import kr.ac.usu.student.service.StaffRegisterStatusRequestService;
import kr.ac.usu.student.vo.SchoolRegisterHistoryVO;
import kr.ac.usu.student.vo.SchoolRegisterVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Service
@RequiredArgsConstructor
public class StaffRegisterStatusRequestServiceImpl implements StaffRegisterStatusRequestService{
	
	private final StaffRegisterStatusRequestMapper mapper;
	
	// 복수전공 신청 리스트
	@Override
	public void retrieveDoubleMajorRequestList(PaginationInfo<SchoolRegisterVO> paging) {
		int totalRecord = mapper.selectDoubleMajorRequestTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<SchoolRegisterVO> dataList = mapper.selectDoubleMajorRequestList(paging);
		paging.setDataList(dataList);
	}

	// 부전공 신청 리스트
	@Override
	public void retrieveMinorRequestList(PaginationInfo<SchoolRegisterVO> paging) {
		int totalRecord = mapper.selectMinorRequestTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<SchoolRegisterVO> dataList = mapper.selectMinorRequestList(paging);
		paging.setDataList(dataList);
	}

	// 전과 신청 리스트
	@Override
	public void retrieveMoveMajorRequestList(PaginationInfo<SchoolRegisterVO> paging) {
		int totalRecord = mapper.selectMoveRequestTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<SchoolRegisterVO> dataList = mapper.selectMoveMajorRequestList(paging);
		paging.setDataList(dataList);
	}

	// 학적변동 신청 정보
	@Override
	public SchoolRegisterVO retrieveRequestInfo(String sknrgNo) {
		SchoolRegisterVO registerRequest = mapper.selectRequestInfo(sknrgNo);
		return registerRequest;
	}

	// 학적변동 신청 반려 (학적변동 신청 정보 업데이트)
	@Override
	public int modifyRefusedRequestInfo(SchoolRegisterVO sknrgNo) {
		int res = mapper.updateRefusedRequestInfo(sknrgNo);
		return res;
	}

	// 학적변동 신청 승인 (학적변동 신청 정보 업데이트, 학적변동 인서트)
	@Override
	public int modifyAppliedRequestInfo(SchoolRegisterVO sknrgNo, SchoolRegisterHistoryVO history, StudentVO stdntNo) {
		history.setStdntNo(sknrgNo.getStdntNo());
		stdntNo.setSknrgSttusMajor1(sknrgNo.getReqSubjctNo());
		stdntNo.setSknrgSttusMajor2(sknrgNo.getReqSubjctNo());
		stdntNo.setSknrgSttusMinor(sknrgNo.getReqSubjctNo());
		
		log.info("tttttttttttttttttt : {}", stdntNo);
		
		String sknrgSe = sknrgNo.getSknrgSe();
		int res = 0;
		
		if(sknrgSe.equals("02")) {			// 복수전공
			res = mapper.updateAppliedRequestInfo(sknrgNo);
			res += mapper.insertAppliedRequestInfo(history);
			res += mapper.updateDoubleMajorStatus(stdntNo);
		}else if(sknrgSe.equals("03")) {	// 부전공
			res = mapper.updateAppliedRequestInfo(sknrgNo);
			res += mapper.insertAppliedRequestInfo(history);
			res += mapper.updateMinorStatus(stdntNo);
		}else {								// 전과
			res = mapper.updateAppliedRequestInfo(sknrgNo);
			res += mapper.insertAppliedRequestInfo(history);
			res += mapper.updateMoveMajorStatus(stdntNo);
		}
		
		return res;
	}


	
}
