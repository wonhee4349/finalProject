package kr.ac.usu.course.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;


import kr.ac.usu.course.mapper.StaffCourseRequestMapper;
import kr.ac.usu.course.service.StaffCourseRequestService;
import kr.ac.usu.course.vo.CourseRequestVO;
import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 28.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class StaffCourseRequestServiceImpl implements StaffCourseRequestService{

private final StaffCourseRequestMapper courseReqMapper;
	
	// 상담 신청 리스트
	@Override
	public void retrieveCourseRequestList(PaginationInfo<CourseRequestVO> paging) {

		int totalRecord = courseReqMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<CourseRequestVO> dataList = courseReqMapper.selectCourseRequestList(paging);
		paging.setDataList(dataList);
		
	}

	// 상담 신청 정보
	@Override
	public CourseRequestVO retrieveCourseRequestInfo(String courseReqstNo) {
		CourseRequestVO courseRequestInfo = courseReqMapper.selectCourseRequestInfo(courseReqstNo);
		return courseRequestInfo;
	}

	//상담 신청 승인 (상담 목록에 인서트, 업데이트)
	@Override
	public int modifyAppliedCourseRequest(CourseRequestVO courseReqstNo, CourseVO courseNo) {
		courseNo.setCourseNo(courseReqstNo.getCourseReqstNo());	
	
		int res = courseReqMapper.updateAppliedCourseRequest(courseReqstNo);
		res += courseReqMapper.insertCourse(courseNo);
		
		return res;
	}

	//상담 신청 반려 (상담 신청 정보 업데이트)
	@Override
	public int modifyRefusedCourseRequest(CourseRequestVO courseReqstNo) {
		int res = courseReqMapper.updateRefusedCourseRequest(courseReqstNo);
		return res;
	}
}
