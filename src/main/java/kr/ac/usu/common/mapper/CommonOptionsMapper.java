package kr.ac.usu.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.facilities.vo.BuildingVO;
import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.facilities.vo.FacilitiesVO;
import kr.ac.usu.lecture.vo.LectureTimetableVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;

/**
 * 공통코드 등의 목록을 가져갈때 사용하려고 만든 Mapper
 * @author 김석호
 * @since 2023. 11. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 11. 20.      김석호         최초작성
 * 2023. 11. 23.      김재성         공통 select 리스트 생성
 * 2023. 12. 09.      김석호         학과리스트 조건없는 조회 작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface CommonOptionsMapper {

	/**
	 * 과목 이수구분
	 * @return
	 */
	public List<Map<String,String>> getComplSeList();
	/**
	 * 과목 학점
	 * @return
	 */
	public List<Map<String,String>> getcoursePntList();
	/**
	 * 시간표 요일목록
	 * @return
	 */
	public List<Map<String,String>> getLectureDayOfWeekList();
	
	/**
	 * 공통코드명 (ex:"SEC001") 입력 코드 별 목록 불러오기
	 * @param comCodeGrp
	 * @return
	 */
	public List<ComCodeVO> getComeCodeList(@Param("comCodeGrp") String comCodeGrp);
	
	/**
	 * 건물명 , 건물코드 가져오는 목록
	 * @return
	 */
	public List<BuildingVO> getBuildingList();
	
	/**
	 * 건물 select 선택시 건물 별 시설물명 , 시설물코드 가져오기
	 * @param buldNo
	 * @return
	 */
	public List<FacilitiesVO> getFacilitiesList(@Param("buldNo") String buldNo);
	
	/**
	 * 단과대학명, 단과대학코드 가져오는 목록
	 * @return
	 */
	public List<CollegeVO> getCollegeList();
	
	/**
	 * 단과 대학 select 선택 시 단과대학 별 학과명 가져오기
	 * @param clgNo
	 * @return
	 */
	public List<SubjectVO> getSubjectList(@Param("clgNo") String clgNo);
	
	/**
	 * 단과대학 -> 학과 선택 후 그 학과의 교과목명, 교과목코드 가져오기
	 * @param subjctNo
	 * @return
	 */
	public List<CourseVO> getCourseList(@Param("subjctNo") String subjctNo);
	
	
	/**
	 * 교과목 선택 시 그 교과목에 해당하는 학점 불러오기
	 * @param courseNo
	 * @return
	 */
	public CourseVO getCoursePnt(@Param("courseNo") String courseNo);
	
	/**
	 * 조건없이 모든 학과 리스트 조회
	 * @return
	 */
	public List<SubjectVO> getFullSubjectList();
}
