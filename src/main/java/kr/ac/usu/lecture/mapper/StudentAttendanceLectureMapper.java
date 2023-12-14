package kr.ac.usu.lecture.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.lecture.vo.StudentAttendanceLectureVO;
import kr.ac.usu.paging.vo.PaginationInfo;

/**
 * 수강신청 기능 Mapper
 * @author 김석호
 * @since 2023. 11. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 11. 20.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StudentAttendanceLectureMapper {
	
	/**
	 * 학생 학번 key : id
	 * 직전학기 key : preSemCd
	 * 신청학기 key : currSemCd
	 * @param paramMap
	 * @return
	 */
	public int selectRequestablePoint(Map<String,String> paramMap);
	public LectureVO selectRequestableLectureInfo(@Param("lctreNo") String lctreNo);
	
	public int selectRequestableLectureListTotalCount(PaginationInfo<LectureVO> paging);
	public List<LectureVO> selectRequestableLectureList(PaginationInfo<LectureVO> paging);
	
	/**
	 * 학생 학번 key : id
	 * 신청학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public List<LectureVO> selectRequestedLectureList(Map<String,String> paramMap);
	
	public int insertAttendanceLecture(StudentAttendanceLectureVO attendance);
	public int insertAttendanceLectureTimeTable(StudentAttendanceLectureVO attendance);
	
	public int deleteAttendanceLecture(StudentAttendanceLectureVO attendance);
	public int deleteAttendanceLectureTimeTable(StudentAttendanceLectureVO attendance);
	
	/**
	 * 학생 학번 key : id
	 * 신청학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public List<LectureVO> selectPrepareLectureList(Map<String,String> paramMap);
	public int insertPrepareLecture(StudentAttendanceLectureVO attendance);
	public int deletePrepareLecture(StudentAttendanceLectureVO attendance);
	
	public int checkAlreadyDone(StudentAttendanceLectureVO attendance);
}
