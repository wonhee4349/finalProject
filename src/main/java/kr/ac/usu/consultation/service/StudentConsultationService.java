package kr.ac.usu.consultation.service;


import kr.ac.usu.consultation.vo.ConsultationRequestVO;
/**
 * <pre>
 * 
 * </pre>
 * @author 김원희
 * @since 2023. 11. 15.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 20.      김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 

import kr.ac.usu.paging.vo.PaginationInfo;

public interface StudentConsultationService {

		
	
	// 상담 신청
	public int createConsultationRequest(ConsultationRequestVO consult);

	
	// 상담 신청 리스트
	public void retrieveConsultationRequestList(PaginationInfo<ConsultationRequestVO> paging);

	// 상담 신청 정보
	public ConsultationRequestVO retrieveConsultationRequestInfo(String cnsltNo);

	//상담내역 리스트
	public void retrieveConsultationList (PaginationInfo<ConsultationRequestVO> paging);
	
	// 상담 신청내역 정보
	public ConsultationRequestVO selectConsultationInfo(String cnsltNo);

	// 상담 내역 정보
		public ConsultationRequestVO selectConsultationFinishInfo(String cnsltNo);

		// 상담 내역 리스트
		public void retrieveConsultationFinishList(PaginationInfo<ConsultationRequestVO> paging);


}
