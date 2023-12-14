package kr.ac.usu.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.course.vo.CourseRequestVO;
import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;

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
@Mapper
public interface ProfessorCourseMapper {
	
	/**
	 *  교과목내역 리스트를 불러올때 전체 레코드수로 페이징 할 전체수 조회
	 * @param paging
	 * @return int 
	 */
	public int courseSelectTotalRecord(PaginationInfo<CourseVO> paging);
	

	/**
	 * 교수가 교과목내역을 보기위해 교과목 리스트 페이징 리스트 조회
	 * @param paging
	 * @return paging ,totalrecord,dataList 등
	 */
	public List<CourseVO> selectCourseList(PaginationInfo<CourseVO> paging);
	
	/**
	 * 하나의 교과목의 상세정보를 조회
	 * @param courseNo
	 * @return CourseVO 한 교과목 정보
	 */
	public CourseVO selectCourse(String courseNo);
	
//--------------------------------------------------------------------------------------------------
	
	
	
	/**
	 *   교수가 교과목 개설신청 내역 보기위한 전체 레코드수로 페이징 할 전체수 조회
	 * @param paging
	 * @return int 
	 */
	public int courseRequestSelectTotalRecord(PaginationInfo<CourseRequestVO> paging);
	
	/**
	 * 교수가 교과목 개설신청 내역 보기위한 리스트 페이징 리스트 조회
	 * @param paging
	 * @return paging ,totalrecord,dataList 등
	 */
	public List<CourseRequestVO> selectCourseRequestList(PaginationInfo<CourseRequestVO> paging);
	
	/**
	 * 교수 교과목 개설 신청 한건에 대한 상세정보 조회
	 * @param courseReqstNo 교과목 개설 신청 번호
	 * @return CourseRequestVO 
	 */
	public CourseRequestVO selectCourseRequest(CourseRequestVO courseRequestVO);
	
	/**
	 * 교수가 교과목 개설 신청
	 * @param courseVO
	 * @return OK,PKDUPLICATE
	 */
	public int insertCourseRequest(CourseRequestVO courseRequest);
	
	/**
	 * 교수가 신청한 교과목 개설신청 내용을 수정
	 * @param courseVO
	 * @return OK,FAIL
	 */
	public int updateCourseRequest(CourseRequestVO courseRequest);
	
	/**
	 * 교수가 신청한 교과목 개설 신청에 대해 삭제
	 * @param courseVO
	 * @return
	 */
	public int deleteCourseRequest(CourseRequestVO courseRequest);
	
	/**
	 * 
	 * @param comCodeGroup
	 * @return
	 */
	public List<ComCodeVO> selectComCode(@Param("comCodeGrp") String comCodeGroup);
	
	/**
	 * insert,update 등 학과목록 select option으로 넣을때 하기 위한 리스트
	 * @return
	 */
	public List<SubjectVO> selectSubjectList();
}
