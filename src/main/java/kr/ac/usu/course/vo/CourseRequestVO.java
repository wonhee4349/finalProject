package kr.ac.usu.course.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.ac.usu.subject.vo.SubjectVO;
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
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = "courseReqstNo")
@ToString
public class CourseRequestVO {
	
	private int rnum;
	private String clgNo;
	private String clgNm;
	private String buldNm;
	
	@NotBlank
	@Size(min = 5 , max = 5)
	private String courseReqstNo;
	
	@NotBlank(groups = InsertGroup.class)
	private String courseReqstNm;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 4, min = 4,groups = InsertGroup.class)
	private String coursePnt;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 2, min = 2,groups = InsertGroup.class)
	private String complSe;
	private String complSeNm;
	
	@NotBlank
	@Size(min = 2 , max = 2)
	private String subjctNo;
	
	@NotBlank
	@Size(min = 10 , max = 10)
	private String courseReqstDate;
	
	@NotBlank
	@Size(min = 8 , max = 8)
	private String prfsorNo;
	
	@NotBlank
	@Size(min = 2 , max = 2)
	private String confmSe;
	private String confmSeNm;
	
	
	private String courseReqstReturn;

	
	private ProfessorVO professor;
	private SubjectVO subjectvo;
	
}
