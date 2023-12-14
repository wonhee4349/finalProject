package kr.ac.usu.consultation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.consultation.mapper.StudentConsultationMapper;
import kr.ac.usu.consultation.service.StudentConsultationService;
import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.consultation.vo.ConsultationVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import lombok.RequiredArgsConstructor;

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

@Service
@RequiredArgsConstructor
public class StudentConsultationServiceImpl implements StudentConsultationService {

	
	private final StudentConsultationMapper ConsultationMapper;
	


	// 상담 신청
	@Override
	public int createConsultationRequest(ConsultationRequestVO consult) {
		int res = ConsultationMapper.createConsultationRequest(consult);		 
		return res;
	}




	// 상담 신청 리스트
	@Override
	public void retrieveConsultationRequestList(PaginationInfo<ConsultationRequestVO> paging) {

		int totalRecord = ConsultationMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ConsultationRequestVO> dataList = ConsultationMapper.selectConsultationRequestList(paging);
		paging.setDataList(dataList);
	}

	// 상담 신청 정보
	@Override
	public ConsultationRequestVO retrieveConsultationRequestInfo(String cnsltNo) {
		ConsultationRequestVO consultationRequestInfo = ConsultationMapper.selectConsultationRequestInfo(cnsltNo);
		return consultationRequestInfo;
	}




	@Override
	public void retrieveConsultationList(PaginationInfo<ConsultationRequestVO> paging) {

		int totalRecord = ConsultationMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ConsultationRequestVO> dataList = ConsultationMapper.retrieveConsultationList(paging);
		paging.setDataList(dataList);
		
	}




	@Override
	public ConsultationRequestVO selectConsultationInfo(String cnsltNo) {
		ConsultationRequestVO consultationRequestInfo = ConsultationMapper.selectConsultationInfo(cnsltNo);
		return consultationRequestInfo;
	}




	@Override
	public ConsultationRequestVO selectConsultationFinishInfo(String cnsltNo) {
		ConsultationRequestVO consultationFinishInfo = ConsultationMapper.selectConsultationFinishInfo(cnsltNo);
		return consultationFinishInfo;
	}




	@Override
	public void retrieveConsultationFinishList(PaginationInfo<ConsultationRequestVO> paging) {

		int totalRecord = ConsultationMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ConsultationRequestVO> dataList = ConsultationMapper.retrieveConsultationFinishList(paging);
		paging.setDataList(dataList);
		
		
	}






}
