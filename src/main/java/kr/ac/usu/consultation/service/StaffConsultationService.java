package kr.ac.usu.consultation.service;

import kr.ac.usu.consultation.vo.ConsultationVO;
import kr.ac.usu.paging.vo.PaginationInfo;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 15.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 15.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
public interface StaffConsultationService {
	
	// 상담 리스트
	public void retrieveConsultationList(PaginationInfo<ConsultationVO> paging);
	
	// 상담 정보
	public ConsultationVO retrieveConsultationInfo(String cnsltNo);
	
	// 상담 정보 수정
	public int modifyConsultationInfo(ConsultationVO cnsltNo);

}
