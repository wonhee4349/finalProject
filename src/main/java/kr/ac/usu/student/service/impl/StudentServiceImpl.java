package kr.ac.usu.student.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.common.exception.StudentNotFoundException;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.student.mapper.StaffStudentMapper;
import kr.ac.usu.student.service.StudentService;
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
 * 2023. 11. 7.      작성자명       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
	
	private final StaffStudentMapper studentMapper;

	// 학생 리스트
	@Override
	public void retrieveStudentList(PaginationInfo<StudentVO> paging) {
		
		int totalRecord = studentMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<StudentVO> dataList = studentMapper.selectStudentList(paging);
		paging.setDataList(dataList);
		
	}

	// 학생 인적 정보
	@Override
	public StudentVO retrieveStudentInfo(String stdntNo) {
		StudentVO studentInfo = studentMapper.selectStudentInfo(stdntNo);
		if(studentInfo==null) {
			throw new StudentNotFoundException();
		}
		return studentInfo;
	}

	// 학생 학적 정보
	@Override
	public StudentVO retrieveStudentRegisterStatus(String stdntNo) {
		StudentVO studentRegister = studentMapper.selectStudentRegisterStatus(stdntNo);
		if(studentRegister==null) {
			throw new StudentNotFoundException();
		}
		return studentRegister;
	}

	// 학생 등록금 정보
	@Override
	public StudentVO retrieveStudentScholarship(String stdntNo) {
		StudentVO studentScholarship = studentMapper.selectStudentScholarship(stdntNo);
		return studentScholarship;
	}
	
	// 학생 등록금 정보
	@Override
	public StudentVO retrieveStudentTuition(String stdntNo) {
		StudentVO studentTuition = studentMapper.selectStudentTuition(stdntNo);
		return studentTuition;
	}

	// 학생 동아리 정보
	@Override
	public StudentVO retrieveStudentClub(String stdntNo) {
		StudentVO studentClub = studentMapper.selectStudentClub(stdntNo);
		return studentClub;
	}

	@Override
	public List<LectureVO> retrieveLectureList(String stdntNo) {
		List<LectureVO> lectureList = studentMapper.selectLectureList(stdntNo);
		return lectureList;
	}
	

}
