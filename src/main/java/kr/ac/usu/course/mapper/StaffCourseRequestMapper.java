package kr.ac.usu.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import kr.ac.usu.course.vo.CourseRequestVO;
import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.paging.vo.PaginationInfo;

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
@Mapper
public interface StaffCourseRequestMapper {
	   // 페이징
		public int selectTotalRecord(PaginationInfo<CourseRequestVO> paging);
		
		// 상담 신청 리스트
		public List<CourseRequestVO> selectCourseRequestList(PaginationInfo<CourseRequestVO> paging);

		// 상담 신청 정보
		public CourseRequestVO selectCourseRequestInfo(@Param("courseReqstNo") String courseReqstNo);
			
		// 상담 신청 승인 (상담 목록에 인서트)
		public int insertCourse(CourseVO courseNo);
		
		// 상담 신청 승인 (상담 신청 정보 업데이트)
		public int updateAppliedCourseRequest(CourseRequestVO courseReqstNo);
		
		// 상담 신청 반려 (상담 신청 정보 업데이트)
		public int updateRefusedCourseRequest(CourseRequestVO courseReqstNo);

}
