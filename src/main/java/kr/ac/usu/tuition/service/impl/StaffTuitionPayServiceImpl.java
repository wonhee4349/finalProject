package kr.ac.usu.tuition.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.tuition.mapper.StaffTuitionPayMapper;
import kr.ac.usu.tuition.service.StaffTuitionPayService;
import kr.ac.usu.tuition.vo.TuitionVO;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 22.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 22.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Service
@RequiredArgsConstructor
public class StaffTuitionPayServiceImpl implements StaffTuitionPayService{
	
	private final StaffTuitionPayMapper tuitionPayMapper;
	
	// 납부여부 리스트
	@Override
	public void retrieveTuitionList(PaginationInfo<TuitionVO> paging) {
		int totalRecord = tuitionPayMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<TuitionVO> dataList = tuitionPayMapper.selectTuitionList(paging);
		paging.setDataList(dataList);
		
	}
	
	// 납부 정보
	@Override
	public TuitionVO retrieveTuitionInfo(String tutnNhtNo) {
		TuitionVO tuitionInfo = tuitionPayMapper.selectTuitionInfo(tutnNhtNo);
		return tuitionInfo;
	}

	// 납부 정보 수정
	@Override
	public int modifyTuitionInfo(TuitionVO tutnNhtNo) {
		int res = tuitionPayMapper.updateTuitionInfo(tutnNhtNo);
		return res;
	}


}
