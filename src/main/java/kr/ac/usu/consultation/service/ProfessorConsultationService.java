package kr.ac.usu.consultation.service;

import org.apache.ibatis.annotations.Param;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.consultation.vo.ConsultationVO;
import kr.ac.usu.paging.vo.PaginationInfo;

/**
 * <pre>
 * 
 * </pre>
 * @author 김재성
 * @since 2023. 11. 17.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 	  수정일    		    수정자       수정내용
 * --------------     --------    ----------------------
 * 2023. 11. 17.     	김재성       최초작성
 * 2023. 11. 21.     	김재성       상담등록 목록 리스트
 * 2023. 11. 22.     	김재성       상담신청 승인완료 신청 1개 데이터 조회 및 상담등록
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
public interface ProfessorConsultationService {
	
	/**
	 * 교수 상담이력 리스트 출력
	 * @param paging
	 * @return List<ConsultationVO>
	 */
	public void retrieveProfessorConsultationList(PaginationInfo<ConsultationVO> paging);
	
	/**
	 * 교수 상담이력 상세정보 출력
	 * @param cnsltNo
	 * @return ConsultationVO
	 */
	public ConsultationVO retrieveProfessorConsultationInfo(String cnsltNo);
	
	/**
	 * 교수 상담신청 리스트 출력
	 * @param paging
	 * @return List<ConsultationRequestVO>
	 */
	public void retrieveProfessorConsultationRequestList(PaginationInfo<ConsultationRequestVO> paging);

	/**
	 * 교수 상담신청 건에 대한 상세정보 출력
	 * @param cnsltNo
	 * @return ConsultationRequestVO
	 */
	public ConsultationRequestVO retrieveProfessorConsultationRequestInfo(String cnsltNo);
	
	/**
	 * 상담신청 건에 대한 반려 처리
	 * @param consultationRequest
	 * @return ConsultationRequestVO
	 */
	public ServiceResult modifyRefuseConsultationRequest(ConsultationRequestVO consultationRequest);

	/**
	 * 상담신청 한 건에 대한 승인 처리
	 * @param consultationRequest
	 * @return
	 */
	public ServiceResult modifyAllowConsultationRequest(ConsultationRequestVO consultationRequest);
	
	/**
	 * 교수 승인완료한 상담신청 목록리스트 출력
	 * @param paging
	 * @return List<ConsultationRequestVO>
	 */
	public void retrieveProfessorRecognizeConsultationRequestList(PaginationInfo<ConsultationRequestVO> paging);
	
	/**
	 * 상담신청 승인완료한 신청 상세정보
	 * @param cnsltNo
	 * @return consultationRequestVO
	 */
	public ConsultationRequestVO retrieveProfessorRecognizeConsultationRequestInfo(@Param("cnsltNo") String cnsltNo);
	
	/**
	 * 상담신청 승인완료 된 상담에 대해 상담완료 후에 내역 등록
	 * @param consultationRequest
	 * @return ConsultationVO
	 */
	public ServiceResult createProfessorRecognizeConsultationRequest(ConsultationVO consultation);
}
