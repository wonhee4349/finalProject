package kr.ac.usu.consultation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.consultation.mapper.StaffConsultationMapper;
import kr.ac.usu.consultation.service.StaffConsultationService;
import kr.ac.usu.consultation.vo.ConsultationVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import lombok.RequiredArgsConstructor;

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
@Service
@RequiredArgsConstructor
public class StaffConsultationServiceImpl implements StaffConsultationService{
	
	private final StaffConsultationMapper consultationMapper;

	// 상담 리스트
	@Override
	public void retrieveConsultationList(PaginationInfo<ConsultationVO> paging) {
		int totalRecord = consultationMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ConsultationVO> dataList = consultationMapper.selectConsultationList(paging);
		paging.setDataList(dataList);
		
	}

	// 상담 정보
	@Override
	public ConsultationVO retrieveConsultationInfo(String cnsltNo) {
		ConsultationVO consultationInfo = consultationMapper.selectConsultationInfo(cnsltNo);
		return consultationInfo;
	}

	// 상담 정보 수정
	@Override
	public int modifyConsultationInfo(ConsultationVO cnsltNo) {
		int res = consultationMapper.updateConsultationInfo(cnsltNo);
		return res;
	}
	
	

}
