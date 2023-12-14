package kr.ac.usu.lecture.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.classroom.vo.TestQuestionVO;
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
 * 수정일           수정자               수정내용
 * --------     --------    ----------------------
 * 2023.11.9.     이재혁      최초작성
 * 2023.12.02.    김재성      has 프로퍼티 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = "stdntNo")
@ToString
public class SubmitAnswerPaperVO {

	@NotBlank
	@Size(min = 8 , max = 8)
	private String stdntNo;
	
	@NotBlank
	private String testQuesNo;
	
	@NotBlank
	@Size(min = 2 , max = 2)
	private String testSe;
	
	@NotBlank
	@Size(min = 9 , max = 9)
	private String lctreNo;
	
	
	private String submitAswper;
	
	private TestQuestionVO testquestion;
	private StudentVO student;
	
	
}
