package kr.ac.usu.student.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.common.exception.StudentNotFoundException;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.student.mapper.StaffGraduationMapper;
import kr.ac.usu.student.service.GraduationService;
import kr.ac.usu.user.vo.StudentVO;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class GraduationServiceImpl implements GraduationService{
	
	private final StaffGraduationMapper graduationMapper;
	
	@Override
	public void retrieveGraduationList(PaginationInfo<StudentVO> paging) {
		
		int totalRecord = graduationMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<StudentVO> dataList = graduationMapper.selectGraduationList(paging);
		paging.setDataList(dataList);
		
	}

	@Override
	public StudentVO retrieveGraduationInfo(String stdntNo) {
		StudentVO graduationInfo = graduationMapper.selectGraduationInfo(stdntNo);
		if(graduationInfo==null) {
			throw new StudentNotFoundException();
		}
		return graduationInfo;
	}

	@Override
	public StudentVO retrieveGraduationRegisterStatus(String stdntNo) {
		StudentVO graduationRegister = graduationMapper.selectGraduationRegisterStatus(stdntNo);
		if(graduationRegister==null) {
			throw new StudentNotFoundException();
		}
		return graduationRegister;
	}

	@Override
	public StudentVO retrieveGraduationScholarship(String stdntNo) {
		StudentVO graduationScholarship = graduationMapper.selectGraduationScholarship(stdntNo);
		return graduationScholarship;
	}

	@Override
	public StudentVO retrieveGraduationTuition(String stdntNo) {
		StudentVO graduationTuition = graduationMapper.selectGraduationTuition(stdntNo);
		return graduationTuition;
	}

	@Override
	public StudentVO retrieveGraduationClub(String stdntNo) {
		StudentVO graduationClub = graduationMapper.selectGraduationClub(stdntNo);
		return graduationClub;
	}

	@Override
	public List<LectureVO> retrieveLectureList(String stdntNo) {
		List<LectureVO> lectureList = graduationMapper.selectLectureList(stdntNo);
		return lectureList;
	}

}
