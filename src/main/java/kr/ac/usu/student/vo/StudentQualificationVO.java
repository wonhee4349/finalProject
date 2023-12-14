package kr.ac.usu.student.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.user.vo.StudentVO;
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
@EqualsAndHashCode(of = "crqfcSe")
@ToString
public class StudentQualificationVO {
	
	@NotBlank(groups = InsertGroup.class)
	private String crqfcSe;
	
	@NotBlank
	@Size(min = 8 , max = 8)
	private String stdntNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 10, max = 10, groups = InsertGroup.class)
	private String stdntQfcDate;
	
	private StudentVO student;
}
