package kr.ac.usu.tuition.service;

import org.apache.ibatis.annotations.Param;

import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.tuition.vo.TuitionVO;

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
public interface StaffTuitionPayService {

	// 납부여부 리스트
	public void retrieveTuitionList(PaginationInfo<TuitionVO> paging);

	// 납부 정보
	public TuitionVO retrieveTuitionInfo(String tutnNhtNo);
	
	// 납부 정보 수정
	public int modifyTuitionInfo(TuitionVO tutnNhtNo);
}
