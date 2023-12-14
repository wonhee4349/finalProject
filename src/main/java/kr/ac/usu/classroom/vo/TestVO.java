package kr.ac.usu.classroom.vo;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.lecture.vo.SubmitAnswerPaperVO;
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
 * 수정일              수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      이재혁      최초작성
 * 2023. 11. 27.     김석호      학번 프로퍼티 추가
 * 2023. 11. 30.     김재성      시험응시 목록 관련 프로퍼티 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

@Data
@EqualsAndHashCode(of = "testSe")
@ToString
public class TestVO {

	@NotBlank
	@Size(min = 2 , max = 2)
	private String testSe;
	
	@NotBlank
	@Size(min = 9 , max = 9)
	private String lctreNo;
	
	private String stdntNo;
	
	private LectureVO lecture;
	
	private TestQuestionVO testQuestion;
	private SubmitAnswerPaperVO submitAnswerPaper;
	private StudentVO student;
	
	
}
