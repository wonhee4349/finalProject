package kr.ac.usu.consultation.service;

import java.util.List;

import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.consultation.vo.ConsultationVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.user.vo.StaffVO;


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
public interface StaffConsultationRequestService {
	
	// 상담 신청 리스트
	public void retrieveConsultationRequestList(PaginationInfo<ConsultationRequestVO> paging);

	// 상담 신청 정보
	public ConsultationRequestVO retrieveConsultationRequestInfo(String cnsltNo);
	
	// 상담교직원 리스트
	public List<StaffVO> retrieveStaffList();
	
	// 상담 신청 승인 (상담 목록에 인서트, 업데이트)
	public int modifyAppliedConsultationRequest(ConsultationRequestVO cnsltReqNo, ConsultationVO cnsltNo);
	
	// 상담 신청 반려 (상담 신청 정보 업데이트)
	public int modifyRefusedConsultationRequest(ConsultationRequestVO cnsltReqNo);
}
