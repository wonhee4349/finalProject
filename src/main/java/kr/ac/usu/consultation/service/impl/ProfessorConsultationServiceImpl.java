package kr.ac.usu.consultation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.consultation.mapper.ProfessorConsultationMapper;
import kr.ac.usu.consultation.service.ProfessorConsultationService;
import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.consultation.vo.ConsultationVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import lombok.RequiredArgsConstructor;

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
@Service
@RequiredArgsConstructor
public class ProfessorConsultationServiceImpl implements ProfessorConsultationService{
	
	private final ProfessorConsultationMapper consultationMapper;

	// 상담 리스트
	@Override
	public void retrieveProfessorConsultationList(PaginationInfo<ConsultationVO> paging) {
		int totalRecord = consultationMapper.selectConsultationTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ConsultationVO> dataList = consultationMapper.selectProfessorConsultationList(paging);
		paging.setDataList(dataList);
	}

	// 상담 정보
	@Override
	public ConsultationVO retrieveProfessorConsultationInfo(String cnsltNo) {
		ConsultationVO consultationInfo = consultationMapper.selectProfessorConsultationInfo(cnsltNo);
		return consultationInfo;
	}
	
	// 상담 신청 리스트
	@Override
	public void retrieveProfessorConsultationRequestList(PaginationInfo<ConsultationRequestVO> paging) {
		int totalRecord = consultationMapper.selectConsultationRequestTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ConsultationRequestVO> dataList = consultationMapper.selectProfessorConsultationRequestList(paging);
		paging.setDataList(dataList);
	}
	
	// 상담 신청 정보
	@Override
	public ConsultationRequestVO retrieveProfessorConsultationRequestInfo(String cnsltNo) {
		ConsultationRequestVO consultationRequestInfo = consultationMapper.selectProfessorConsultationRequestInfo(cnsltNo);
		return consultationRequestInfo;
	}
	
	// 상담 신청 반려
	@Override
	public ServiceResult modifyRefuseConsultationRequest(ConsultationRequestVO consultationRequest) {
		
		int cnt = consultationMapper.udpateRefuseConsultationRequest(consultationRequest);
		ServiceResult res = null;
		if(cnt>0) {
			res=ServiceResult.OK;
		}else {
			res=ServiceResult.FAIL;
		}
		
		return res;
	}
	// 상담 신청 승인
	@Override
	public ServiceResult modifyAllowConsultationRequest(ConsultationRequestVO consultationRequest) {
		
		int cnt = consultationMapper.udpateAllowConsultationRequest(consultationRequest);
		ServiceResult res = null;
		if(cnt>0) {
			res=ServiceResult.OK;
		}else {
			res=ServiceResult.FAIL;
		}
		
		return res;
	}
	
	// 승인완료 상담신청 목록 리스트 등록 메뉴 사용
	@Override
	public void retrieveProfessorRecognizeConsultationRequestList(PaginationInfo<ConsultationRequestVO> paging) {
		int totalRecord = consultationMapper.selectRecognizeConsultationRequestTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ConsultationRequestVO> dataList = consultationMapper.selectProfessorRecognizeConsultationRequestList(paging);
		paging.setDataList(dataList);
	}
	
	// 승인완료 상담신청 하나의 상세정보 내역
	@Override
	public ConsultationRequestVO retrieveProfessorRecognizeConsultationRequestInfo(String cnsltNo) {
		
		ConsultationRequestVO recognizeConsultationRequestInfo = consultationMapper.selectProfessorRecognizeConsultationRequestInfo(cnsltNo);
		return recognizeConsultationRequestInfo;
	}
	
	// 상담신청 승인완료 된 상담에 대해 상담완료 후에 내역 등록
	public ServiceResult createProfessorRecognizeConsultationRequest(ConsultationVO consultation) {
		
		int cnt = consultationMapper.insertProfessorRecognizeConsultationRequest(consultation);
		ServiceResult res = null;
		if(cnt>0) {
			res=ServiceResult.OK;
		}else {
			res=ServiceResult.FAIL;
		}
		return res;
	}

}
