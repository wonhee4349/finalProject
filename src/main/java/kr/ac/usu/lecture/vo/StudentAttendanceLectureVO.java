package kr.ac.usu.lecture.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.subject.vo.SubjectVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 수강신청 웹소켓 통신용 VO
 * @author 김석호
 * @since 2023. 11. 17.
 * @version 1.0
 * @param <T>
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일         수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 17.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "stdntNo")
public class StudentAttendanceLectureVO implements Serializable {
	private String command;
	private String lctreNo;
	private String semCd;
	private String message;
	private String stdntNo;
	private LectureVO lecture;
	private Map<String, String> paramMap;
	private Integer pnt;
	
	private List<LectureVO> lectureList;
	private List<LectureVO> prepareList;
	
	private PaginationInfo<LectureVO> paging;
	
	private List<CollegeVO> collegeList;
	private List<SubjectVO> subjectList;

	
}