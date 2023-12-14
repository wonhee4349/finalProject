package kr.ac.usu.tuition.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.schoolaffairsschedule.vo.SemesterVO;
import kr.ac.usu.tuition.mapper.StaffTuitionStudentMapper;
import kr.ac.usu.tuition.service.StaffTuitionStudentService;
import kr.ac.usu.tuition.vo.TuitionVO;
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
public class StaffTuitionStudentServiceImpl implements StaffTuitionStudentService{
	
	private final StaffTuitionStudentMapper tuitionStudentMapper;
	
	// 납부대상자 리스트
	@Override
	public void retrieveTuitionStudentList(PaginationInfo<StudentVO> paging) {
		int totalRecord = tuitionStudentMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<StudentVO> dataList = tuitionStudentMapper.selectTuitionStudent(paging);
		paging.setDataList(dataList);
		
	}

	// 납부대상자 정보
	@Override
	public StudentVO retrieveTuitionStudentInfo(String stdntNo) {
		StudentVO studentInfo = tuitionStudentMapper.selectTuitionStudentInfo(stdntNo);
		return studentInfo;
	}

	// 납부대상자 등록금 불러오기
	@Override
	public StudentVO retrieveTuitionStudentScholarshipInfo(String stdntNo) {
		StudentVO studentScholarshipInfo = tuitionStudentMapper.seletTuitionStudentScholarshipInfo(stdntNo);
		return studentScholarshipInfo;
	}
	
	// 납부 학기 정보
	@Override
	public SemesterVO retrieveTuitionSemesterInfo() {
		SemesterVO semester = tuitionStudentMapper.selectTuitionSemesterInfo();
		return semester;
	}

	// 납부대상자 등록
	@Override
	public int createTuition(TuitionVO tuition) {
		int res = tuitionStudentMapper.insertTuition(tuition);
		return res;
	}


}
