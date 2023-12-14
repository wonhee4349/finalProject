package kr.ac.usu.course.service;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.course.vo.CourseRequestVO;
import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.paging.vo.PaginationInfo;

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
public interface ProfessorCourseService {
	
	/**
	 * 교수가 교과목내역을 보기위해 교과목 리스트 페이징 리스트 조회
	 * @param paging
	 * @return paging
	 */
	public void retrieveCourseList(PaginationInfo<CourseVO> paging);
	
	/**
	 * 하나의 교과목의 상세정보를 조회
	 * @param courseNo
	 * @return CourseVO 한 교과목 정보
	 */
	public CourseVO retrieveCourse(String courseNo);
	
	/**
	 * 교수가 교과목 개설 신청
	 * @param courseVO
	 * @return OK,PKDUPLICATE
	 */
	public ServiceResult createCourseRequest(CourseRequestVO courseRequest);
	
//--------------------------------------------------------------------------------------------------	
	
	/**
	 * 교수가 교과목 개설신청 내역 보기위한 리스트 페이징 리스트 조회
	 * @param paging
	 * @return paging ,totalrecord,dataList 등
	 */
	public void retrieveCourseRequestList(PaginationInfo<CourseRequestVO> paging);
	
	/**
	 * 교수 교과목 개설 신청 한건에 대한 상세정보 조회
	 * @param courseReqstNo 교과목 개설 신청 번호
	 * @return CourseRequestVO 
	 */
	public CourseRequestVO retrieveCourseRequest(CourseRequestVO courseRequestVO);
}
