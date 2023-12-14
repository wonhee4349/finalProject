package kr.ac.usu.scholarship.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.common.exception.StudentNotFoundException;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.scholarship.mapper.StaffScholarshipStudentMapper;
import kr.ac.usu.scholarship.service.StaffScholarshipStudentService;
import kr.ac.usu.scholarship.vo.ScholarshipRequestVO;
import kr.ac.usu.scholarship.vo.ScholarshipStudentVO;
import kr.ac.usu.scholarship.vo.ScholarshipVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 16.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 16.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Service
@RequiredArgsConstructor
public class StaffScholarshipStudentServiceImpl implements StaffScholarshipStudentService{
	
	private final StaffScholarshipStudentMapper scholarshipStudentMapper;
	
	// 장학생 리스트
	@Override
	public void retrieveScholarshipStudent(PaginationInfo<ScholarshipStudentVO> paging) {
		int totalRecord = scholarshipStudentMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ScholarshipStudentVO> dataList = scholarshipStudentMapper.selectScholarshipStudentList(paging);
		paging.setDataList(dataList);
		
	}

	// 장학생 인적 정보
	@Override
	public ScholarshipStudentVO retrieveScholarshipStudentInfo(String stdntNo) {
		ScholarshipStudentVO scholarshipStudentInfo = scholarshipStudentMapper.selectScholarshipStudentInfo(stdntNo);
		if(scholarshipStudentInfo==null) {
			throw new StudentNotFoundException();
		}
		return scholarshipStudentInfo;
	}

	// 장학생 학적 정보
	@Override
	public ScholarshipStudentVO retrieveScholarshipStudentStatus(String stdntNo) {
		ScholarshipStudentVO scholarshipStudentInfo = scholarshipStudentMapper.selectScholarshipStudentStatus(stdntNo);
		if(scholarshipStudentInfo==null) {
			throw new StudentNotFoundException();
		}
		return scholarshipStudentInfo;
	}

	// 장학생 장학금 정보
	@Override
	public ScholarshipStudentVO retrieveScholarshipStudentScholarship(String stdntNo) {
		ScholarshipStudentVO scholarshipStudentInfo = scholarshipStudentMapper.selectScholarhipStudentScholarship(stdntNo);

		return scholarshipStudentInfo;
	}

	// 장학생 추가 장학금 목록 불러오기
	@Override
	public List<ScholarshipVO> retrieveScholarshipList() {
		List<ScholarshipVO> scholarshipList = scholarshipStudentMapper.selectScholarshipList();
		return scholarshipList;
	}
	
	// 셀렉트박스에서 고른 장학금 정보 불러오기
	@Override
	public ScholarshipVO retrieveScholarship(String schlshipNo) {
		ScholarshipVO scholarshipInfo = scholarshipStudentMapper.selectScholarshipInfo(schlshipNo);
		return scholarshipInfo;
	}

	// 추가 장학생 정보
	@Override
	public StudentVO retrieveStudent(String stdntNo) {
		StudentVO studentInfo = scholarshipStudentMapper.selectStudentInfo(stdntNo);
		return studentInfo;
	}

	// 추가 장학생 인서트
	@Override
	public int createScholarshipStudent(ScholarshipStudentVO scholarshipStudent) {
		int res = scholarshipStudentMapper.insertScholarshipStudent(scholarshipStudent);
		return res;
	}

	
	
	//------------------------------------------------------------------------------------------------

	// 장학생 신청 리스트
	@Override
	public void retrieveScholarshipRequestStudent(PaginationInfo<ScholarshipRequestVO> paging) {
		int totalRecord = scholarshipStudentMapper.selectRequestTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List< ScholarshipRequestVO> dataList = scholarshipStudentMapper.selectScholarshipRequestStudent(paging);
		paging.setDataList(dataList);
		
	}

	// 장학생 신청 장학학기 셀렉트박스 불러오기
	@Override
	public List<ScholarshipVO> retrieveScholarshipSemesterInfo() {
		List<ScholarshipVO> scholarshipSemesterInfo = scholarshipStudentMapper.selectScholarshipSemesterInfo();
		return scholarshipSemesterInfo;
	}
		

}
