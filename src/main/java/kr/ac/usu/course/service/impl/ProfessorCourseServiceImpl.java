package kr.ac.usu.course.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.course.mapper.ProfessorCourseMapper;
import kr.ac.usu.course.service.ProfessorCourseService;
import kr.ac.usu.course.vo.CourseRequestVO;
import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author PC-21
 *
 * @author 김재성
 * @since 2023. 11. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 *   수정일           수정자            수정내용
 * ----------    --------    ----------------------
 * 2023.11.19.     김재성       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Service
public class ProfessorCourseServiceImpl implements ProfessorCourseService {
	
	@Inject
	private ProfessorCourseMapper courseMapper;
	
	// 교과목 내역
	@Override
	public void retrieveCourseList(PaginationInfo<CourseVO> paging) {
		
		log.info("교과목 내역 매개변수 : {}", paging);
		
		int totalRecord = courseMapper.courseSelectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		
		List<CourseVO> dataList = courseMapper.selectCourseList(paging);
		paging.setDataList(dataList);
		
		log.info("교과목 내역 리스트 : {}", dataList);
	}
		
	// 교과목 상세내역 조회
	@Override
	public CourseVO retrieveCourse(String courseNo) {
		
		log.info("교과목 번호 조회  : {}", courseNo);
		
		CourseVO course = courseMapper.selectCourse(courseNo);
		
		log.info("교과목 상세정보 : {}", course);
		
		return course;
	}
	
	// 교수 교과목 개설신청 리스트 조회
	public void retrieveCourseRequestList(PaginationInfo<CourseRequestVO> paging){
		
		log.info("교수 교과목신청 매개변수 : {}", paging);
		
		int totalRecord = courseMapper.courseRequestSelectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		
		List<CourseRequestVO> dataList = courseMapper.selectCourseRequestList(paging);
		paging.setDataList(dataList);
		
		log.info("교수 교과목신청 리스트 : {}", dataList);
	}
	
	// 교과목 개설 신청
	@Override
	public ServiceResult createCourseRequest(CourseRequestVO courseRequest) {
		
		int cnt = courseMapper.insertCourseRequest(courseRequest);
		ServiceResult res = null;
		
		if(cnt>0) {
			res=ServiceResult.OK;
		}else {
			res=ServiceResult.PKDUPLICATE;
		}
		return res;
	}

	@Override
	public CourseRequestVO retrieveCourseRequest(CourseRequestVO courseRequestVO) {
		
		log.info("교과목 개설신청 매개변수 객체 조회  : {}", courseRequestVO);
		
		CourseRequestVO courseRequest = courseMapper.selectCourseRequest(courseRequestVO);
		log.info("교과목 개설신청 상세정보 : {}", courseRequest);
		
		return courseRequest;
	}
	
	
}
