package kr.ac.usu.lecture.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@EqualsAndHashCode(of = "scoreSe")
@ToString
public class LectureEvaluationStandardVO {

	@NotBlank
	@Size(min = 2 , max = 2)
	private String scoreSe;
	
	@NotBlank
	@Size(min = 10 , max = 10)
	private String lctreAcnutnoNo;
	
	@NotBlank
	private Integer lctreEvlStdrReflct;
	
	private LectureRequestVO lecturerequest;
}
