package kr.ac.usu.lecture.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.facilities.vo.BuildingVO;
import kr.ac.usu.facilities.vo.FacilitiesVO;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.validate.grouphint.InsertGroup;
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
 * 수정일            수정자               수정내용
 * --------       --------    ----------------------
 * 2023.11.09.     이재혁      최초작성
 * 2023.11.24.     김재성      프로퍼티 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = "lctreReqstNo")
@ToString
public class LectureRequestVO {
	
	private int rnum;
	
	@NotBlank
	@Size(min = 10 , max = 10)
	private String lctreReqstNo;
	
	@NotBlank
	@Size(min = 10 , max = 10)
	private String lctreReqstDate;
	
	@NotBlank
	@Size(min = 8 , max = 8)
	private String prfsorNo;
	
	@NotBlank
	@Size(min = 4 , max = 4)
	private String courseNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 2 , max = 2, groups = InsertGroup.class)
	private String complSe;
	private String complSeNm;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 2 , max = 2, groups = InsertGroup.class)
	private String semstrSe;
	private String semstrSeNm;
	
	@NotBlank
	@Size(min = 2 , max = 2)
	private String confmSe;
	private String confmSeNm;
	
	private String lctreReqstReturn;
	
	@NotBlank
	@Size(min = 2 , max = 2)
	private String lctreSe;
	private String lctreSeNm;
	
	@NotBlank
	@Size(min = 5 , max = 5)
	private String fcltsNo;
	
	@NotBlank
	@Size(min = 2 , max = 2)
	private String buldNo;
	
	private ProfessorVO professor;
	private CourseVO course;
	private FacilitiesVO facilitiess;
	
	private String courseNm;
	private String coursePnt;
	private String fcltsNm;
	private String fcltsPurps;
	private String buldNm;
	private String clgNm;
	
	private String lctreAcnutnoNo;
	
	//강의개설신청 : 강의개설신청정보 = 1 : N
	private List<LectureRequestInfoVO> lectureRequestInfoVOList;
	
	//강의개설신청 : 강의계획서 = 1 : 1
	private LectureActionPlanVO lectureActionPlanVO;
	
	private List<FacilitiesVO> facilities;	// has 1:many
	
	private BuildingVO building;	// has 1:1

}





