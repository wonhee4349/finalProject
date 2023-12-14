package kr.ac.usu.course.service;




import java.util.List;

import kr.ac.usu.consultation.vo.ConsultationVO;
import kr.ac.usu.course.vo.CourseRequestVO;
import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.user.vo.StaffVO;


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
public interface StaffCourseRequestService {
	// 상담 신청 리스트
		public void retrieveCourseRequestList(PaginationInfo<CourseRequestVO> paging);

		// 상담 신청 정보
		public CourseRequestVO retrieveCourseRequestInfo(String courseReqstNo);
			
		// 상담 신청 승인 (상담 목록에 인서트, 업데이트)
		public int modifyAppliedCourseRequest(CourseRequestVO courseReqstNo, CourseVO courseNo);
		
		// 상담 신청 반려 (상담 신청 정보 업데이트)
		public int modifyRefusedCourseRequest(CourseRequestVO courseReqstNo);

}
