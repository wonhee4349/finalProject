package kr.ac.usu.lecture.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
@EqualsAndHashCode(of = "lctreEvlScoreNo")
@ToString
public class LectureEvaulationScoreVO {
	
	@NotBlank
	@Size(min = 1 , max = 1)
	private Integer lctreEvlScoreNo;
	
	@NotBlank
	private String lctreEvlScoreQstn;

}
