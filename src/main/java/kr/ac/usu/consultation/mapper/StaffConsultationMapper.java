package kr.ac.usu.consultation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.consultation.vo.ConsultationVO;
import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.StaffVO;

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
@Mapper
public interface StaffConsultationMapper {
	
	// 페이징
	public int selectTotalRecord(PaginationInfo<ConsultationVO> paging);
	
	// 상담 리스트
	public List<ConsultationVO> selectConsultationList(PaginationInfo<ConsultationVO> paging);
	
	// 상담 정보
	public ConsultationVO selectConsultationInfo(@Param("cnsltNo") String cnsltNo);
	
	// 상담 정보 수정
	public int updateConsultationInfo(ConsultationVO cnsltNo);
	
	// 단과대 목록 가져오기
	public List<CollegeVO> selectCollegeList();
	
	// 학과 목록 가져오기
	public List<SubjectVO> selectSubjectList();
	
	// 상담사 목록 가져오기
	public List<StaffVO> selectStaffList();

}
