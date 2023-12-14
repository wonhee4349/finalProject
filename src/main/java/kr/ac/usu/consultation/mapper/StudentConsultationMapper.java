package kr.ac.usu.consultation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.consultation.vo.ConsultationVO;
import kr.ac.usu.paging.vo.PaginationInfo;

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
 * 2023. 11. 15.      김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Mapper
public interface StudentConsultationMapper {
	

	//상담신청
	public int createConsultationRequest(ConsultationRequestVO consult);



	// 페이징
		public int selectTotalRecord(PaginationInfo<ConsultationRequestVO> paging);
		
		// 상담 신청 리스트
		public List<ConsultationRequestVO> selectConsultationRequestList(PaginationInfo<ConsultationRequestVO> paging);

		// 상담 신청 정보
		public ConsultationRequestVO selectConsultationRequestInfo(@Param("cnsltNo") String cnsltNo);

		//상담신청내역 리스트
		public List<ConsultationRequestVO> retrieveConsultationList (PaginationInfo<ConsultationRequestVO> paging);
		
		
		//상담내역 리스트
		public List<ConsultationRequestVO> retrieveConsultationFinishList (PaginationInfo<ConsultationRequestVO> paging);
				
				
		// 상담 내역 정보
		public ConsultationRequestVO selectConsultationInfo(@Param("cnsltNo") String cnsltNo);

		// 상담 내역 정보
		public ConsultationRequestVO selectConsultationFinishInfo(@Param("cnsltNo") String cnsltNo);
		
	
		
		
}
