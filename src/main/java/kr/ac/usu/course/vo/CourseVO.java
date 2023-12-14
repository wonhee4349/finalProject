package kr.ac.usu.course.vo;



import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 김재성
 * @since 2023. 11. 08
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023.11.08      김재성       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of="courseNo")
public class CourseVO {
	
	private Integer rnum;
	
	//검색조건 페이징 하기 위해 추가 - simpleCondition 용
	private String clgNo;
	private String subjctNo;
	private String clgNm;
	private String subjctNm;
	private String subjctTelno;
	private String buldNm;
	private String fcltsNm;
	
	@NotBlank
	private String courseNo;
	@NotBlank
	private String courseNm;
	@NotBlank
	private String coursePnt;
	@NotBlank
	private String complSe;
	
	private CourseRequestVO courseRequest;	// has 1:1 관계
	
	private String courseReqstNo; 
	private String courseReqstNm; 
		
	}
	

