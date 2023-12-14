package kr.ac.usu.lecture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.lecture.vo.LectureActionPlanVO;
import kr.ac.usu.lecture.vo.LectureEvaluationStandardVO;
import kr.ac.usu.lecture.vo.LectureRequestInfoVO;
import kr.ac.usu.lecture.vo.LectureRequestVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.StudentVO;

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
 * 수정일          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 7.   이재혁       최초작성
 * 2023. 11.11.   김재성       강의리스트 dao 생성
 * 2023. 11.14.   김재성       교과목내역 리스트 메서드 생성
 * 2023. 11.16.   김재성       교과목 상세내역 정보 조회 메서드 생성
 * 2023. 11.17.   김재성       교수 강의 수강학생 목록 조회 메서드 생성
 * 2023. 11.24.   김재성      강의개설 신청 등록 insert, 강의개설 신청목록 select
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface ProfessorLectureMapper {
	
	
	/**
	 * 전체 단과대학 조회
	 * @return
	 */
	public List<CollegeVO> othersCollegeList();
	
	/**
	 * 특정 단과대학 해당하는 목록조회
	 * @Param subjctNo 없을 경우는 전체 제조사 목록 조회
	 * @return
	 */
	public List<SubjectVO> othersSubjectList(@Param("subjctNo") String subjctNo);

	/**
	 *  교과목 리스트를 불러올때 전체 레코드수로 페이징 할 전체수 조회
	 * @param paging
	 * @return int 
	 */
	public int lectureSelectTotalRecord(PaginationInfo<LectureVO> paging);
	

	/**
	 *  한 교수의 강의중인 내역 리스트와 년도 학기 검색 조건
	 * @param searchConditionVO
	 * @return
	 */
	public List<LectureVO> selectLectureList(PaginationInfo<LectureVO> paging);
	
	
	/**
	 * 교수의 강의에 수강중인 학생목록 리스트 조회
	 * @param lctreNo 교수번호
	 * @return List<StudentVO> studentList
	 */
	public List<StudentVO> selectLectureStudentList(String lctreNo);
	
	
	/**
	 *  교수의 강의 개설 신청 목록의 페이징 레코드수 가져오기
	 * @param paging
	 * @return int 
	 */
	public int selectLectureRequestTotalRecord(PaginationInfo<LectureRequestVO> paging);
	
	/**
	 *  교수의 강의 개설 신청 목록 조회
	 * @param paging
	 * @return List<LectureRequestVO>
	 */
	public List<LectureRequestVO> selectLectureRequestList(PaginationInfo<LectureRequestVO> paging);
	
	/**
	 * 교수의 강의개설 신청 상세 조회
	 * @param lectureRequest
	 * @return LectureRequestVO
	 */
	public LectureRequestVO selectLectureRequestView(LectureRequestVO lectureRequest);

	//1) LECTURE_REQUEST 에 insert
	public int insertLectureRequest(LectureRequestVO lectureRequestVO);

	//2) LECTURE_REQUEST_INFO 에 insert
	public int insertLectureRequestInfo(LectureRequestInfoVO lectureRequestInfoVO);

	//3) LECTURE_ACTION_PLAN 에 insert
	public int insertLectureActionPlan(LectureActionPlanVO lectureActionPlanVO);

	//4) LECTURE_EVALUATION_STANDARD 에 insert
	public int insertLectureEvaluationStandard(LectureEvaluationStandardVO lectureEvaluationStandardVO);
}
