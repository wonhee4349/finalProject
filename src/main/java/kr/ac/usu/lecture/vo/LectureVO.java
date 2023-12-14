package kr.ac.usu.lecture.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.facilities.vo.FacilitiesVO;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 9.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      이재혁      최초작성
 * 2023. 11. 10.      김석호     과목,시간표 릴레이션 추가
 * 2023. 11. 20.      김석호     수강신청 검색용 프로퍼티 추가
 * 2023. 11. 22.      김석호     강의평가용 논리프로퍼티 추가
 * 2023. 12. 01.      김석호     공통코드 네임,학과명 프로퍼티 추가
 * 2023. 12. 03.      김재성     학기 및 강의평가기준 테이블 컬럼 프로퍼티 추가
 * 2023. 12. 09.      김석호     검색조건용 프로퍼티 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = "lctreNo")
@ToString
public class LectureVO {
	
	private int rnum;
	private String buldNm;
	private String fcltsNm;
	private String semstrSeNm;
	
	
	@NotBlank
	@Size(min = 9 , max = 9)
	private String lctreNo;
	
	@NotBlank
	@Size(min = 3 , max = 3)
	private Integer lctreNmpr;
	
	@NotBlank
	@Size(min = 4 , max = 4)
	private String lctreSe;
	private String lctreSeNm;

	
	@NotBlank
	@Size(min = 5 , max = 5)
	private String fcltsNo;
	
	@NotBlank
	@Size(min = 8 , max = 8)
	private String prfsorNo;
	

//	@Size(min = 2 , max = 2)
	private String semstrSe;
	private String semstrYr;
	private String semstr;
	private String semstrSeYy;
	
	
	@Size(min = 2 , max = 2)
	private String complSe;
	
	private String complSeNm;
	
	@NotBlank
	@Size(min = 4 , max = 4)
	private String courseNo;
	
	private ProfessorVO professor;
	private FacilitiesVO facilities;
	private CourseVO course;
	private List<LectureTimetableVO> timetable;
	private String tmtbSeNm;
	private List<StudentVO> student;
	private Integer currCnt;
	
	private String subjctNm;
	
	private String printScore;
	
	// 입력받는 검색조건 받는 프로퍼티
	private String coursePnt;
	private String courseDayOfWeek;
	private String courseNm;
	private String prfsorNm;
	private String currentPage;
	private String lctreReqstNo;
	private String clgNo;
	private String subjctNo;
	
	// 강의평가용
	private String evaStatus;
}
