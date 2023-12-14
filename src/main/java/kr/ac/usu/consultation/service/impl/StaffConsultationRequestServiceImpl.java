package kr.ac.usu.consultation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.consultation.mapper.StaffConsultationRequestMapper;
import kr.ac.usu.consultation.service.StaffConsultationRequestService;
import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.consultation.vo.ConsultationVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.user.vo.StaffVO;
import lombok.RequiredArgsConstructor;


/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 13.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 13.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Service
@RequiredArgsConstructor
public class StaffConsultationRequestServiceImpl implements StaffConsultationRequestService{
	
	private final StaffConsultationRequestMapper consultReqMapper;
	
	// 상담 신청 리스트
	@Override
	public void retrieveConsultationRequestList(PaginationInfo<ConsultationRequestVO> paging) {

		int totalRecord = consultReqMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ConsultationRequestVO> dataList = consultReqMapper.selectConsultationRequestList(paging);
		paging.setDataList(dataList);
		
	}

	// 상담 신청 정보
	@Override
	public ConsultationRequestVO retrieveConsultationRequestInfo(String cnsltNo) {
		ConsultationRequestVO consultationRequestInfo = consultReqMapper.selectConsultationRequestInfo(cnsltNo);
		return consultationRequestInfo;
	}

	// 상담교직원 리스트
	@Override
	public List<StaffVO> retrieveStaffList() {
		List<StaffVO> staffList = consultReqMapper.selectStaffList();
		return staffList;
	}

	//상담 신청 승인 (상담 목록에 인서트, 업데이트)
	@Override
	public int modifyAppliedConsultationRequest(ConsultationRequestVO cnsltReqNo, ConsultationVO cnsltNo) {
		cnsltNo.setCnsltNo(cnsltReqNo.getCnsltNo());
		cnsltNo.setStdntNo(cnsltReqNo.getStdntNo());
		
		int res = consultReqMapper.updateAppliedConsultationRequest(cnsltReqNo);
		res += consultReqMapper.insertConsultation(cnsltNo);
		
		return res;
	}

	//상담 신청 반려 (상담 신청 정보 업데이트)
	@Override
	public int modifyRefusedConsultationRequest(ConsultationRequestVO cnsltReqNo) {
		int res = consultReqMapper.updateRefusedConsultationRequest(cnsltReqNo);
		return res;
	}

}
