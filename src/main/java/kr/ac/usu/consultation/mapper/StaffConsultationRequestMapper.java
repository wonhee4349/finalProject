package kr.ac.usu.consultation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.consultation.vo.ConsultationRequestVO;
import kr.ac.usu.consultation.vo.ConsultationVO;
import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.StaffVO;


/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 13.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 13.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Mapper
public interface StaffConsultationRequestMapper {
	
	// 페이징
	public int selectTotalRecord(PaginationInfo<ConsultationRequestVO> paging);
	
	// 상담 신청 리스트
	public List<ConsultationRequestVO> selectConsultationRequestList(PaginationInfo<ConsultationRequestVO> paging);

	// 상담 신청 정보
	public ConsultationRequestVO selectConsultationRequestInfo(@Param("cnsltNo") String cnsltNo);
	


	
	// 상담교직원 리스트
	public List<StaffVO> selectStaffList();
	
	// 상담 신청 승인 (상담 목록에 인서트)
	public int insertConsultation(ConsultationVO cnsltNo);
	
	// 상담 신청 승인 (상담 신청 정보 업데이트)
	public int updateAppliedConsultationRequest(ConsultationRequestVO cnsltReqNo);
	
	// 상담 신청 반려 (상담 신청 정보 업데이트)
	public int updateRefusedConsultationRequest(ConsultationRequestVO cnsltReqNo);
	
	// 공통코드 셀렉트박스 가져오기
	public List<ComCodeVO> selectComCode(@Param("comCodeGrp") String ComCodeGrp);
	
	// 단과대 목록 가져오기
	public List<CollegeVO> selectCollegeList();
	
	// 학과 목록 가져오기
	public List<SubjectVO> selectSubjectList();
	
}
