package kr.ac.usu.consultation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.consultation.vo.ConsultationVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.user.vo.ComCodeVO;

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
 * 2023. 11. 21.     	김재성       승인 반려 업데이트 / 상담등록 목록리스트
 * 2023. 11. 22.     	김재성       상담신청 승인완료 신청 1개 데이터 조회 및 상담등록
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Mapper
public interface ProfessorConsultationMapper {
	
	
	/**
	 * 교수 상담이력 리스트 페이징 총 레코드 수 메소드
	 * @param paging
	 * @return int 
	 */
	public int selectConsultationTotalRecord(PaginationInfo<ConsultationVO> paging);
	
	/**
	 * 교수 상담이력 리스트 출력
	 * @param paging
	 * @return List<ConsultationVO>
	 */
	public List<ConsultationVO> selectProfessorConsultationList(PaginationInfo<ConsultationVO> paging);
	
	/**
	 * 교수 상담이력 상세정보 출력
	 * @param cnsltNo
	 * @return ConsultationVO
	 */
	public ConsultationVO selectProfessorConsultationInfo(@Param("cnsltNo") String cnsltNo);
	
	/**
	 * 교수 상담신청 리스트 페이징 총 레코드 수 메소드
	 * @param paging
	 * @return
	 */
	public int selectConsultationRequestTotalRecord(PaginationInfo<ConsultationRequestVO> paging);
	
	/**
	 * 교수 상담신청 리스트 출력
	 * @param paging
	 * @return List<ConsultationRequestVO>
	 */
	public List<ConsultationRequestVO> selectProfessorConsultationRequestList(PaginationInfo<ConsultationRequestVO> paging);

	/**
	 * 교수 상담신청 건에 대한 상세정보 출력
	 * @param cnsltNo
	 * @return ConsultationRequestVO
	 */
	public ConsultationRequestVO selectProfessorConsultationRequestInfo(@Param("cnsltNo") String cnsltNo);
	
	/**
	 * 상담신청 건에 대한 승인 or 반려 처리
	 * @param consultationRequest
	 * @return ConsultationRequestVO
	 */
	public ConsultationRequestVO updateProfessorConsultationRequestInfo(ConsultationRequestVO consultationRequest);

	
	/**
	 * 상담신청 건에 대한 반려 처리
	 * @param consultationRequest
	 * @return ConsultationRequestVO
	 */
	public int udpateRefuseConsultationRequest(ConsultationRequestVO consultationRequest);

	/**
	 * 상담신청 한 건에 대한 승인 처리
	 * @param consultationRequest
	 * @return
	 */
	public int udpateAllowConsultationRequest(ConsultationRequestVO consultationRequest);
	
	
	
	/**
	 * 교수 상담신청 승인와료 한 리스트 페이징 총 레코드 수 메소드
	 * @param paging
	 * @return
	 */
	public int selectRecognizeConsultationRequestTotalRecord(PaginationInfo<ConsultationRequestVO> paging);
	
	/**
	 * 상담신청 승인완료 한 상담신청목록 리스트 
	 * @param paging
	 * @return List<ConsultationRequestVO>
	 */
	public List<ConsultationRequestVO> selectProfessorRecognizeConsultationRequestList(PaginationInfo<ConsultationRequestVO> paging);
	
	/**
	 * 상담신청 승인완료한 신청 상세정보
	 * @param cnsltNo
	 * @return consultationRequestVO
	 */
	public ConsultationRequestVO selectProfessorRecognizeConsultationRequestInfo(@Param("cnsltNo") String cnsltNo);
	
	/**
	 * 상담신청 승인완료 된 상담에 대해 상담완료 후에 내역 등록
	 * @param consultationRequest
	 * @return ConsultationVO
	 */
	public int insertProfessorRecognizeConsultationRequest(ConsultationVO consultation);
	
	
	/**
	 * 상담신청 등록 시에 일정선택에 필요한 시간표 테이블 리스트
	 * @return
	 */
	public List<ComCodeVO> selectTimeTable();
		
}
