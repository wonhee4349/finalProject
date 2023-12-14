package kr.ac.usu.subject.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.validate.grouphint.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 8.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 8.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Data
@EqualsAndHashCode(of = {"courseNo", "subjctNo"})
@ToString
public class SubjectCurriculumVO {

	@NotBlank(groups = InsertGroup.class)
	@Size(min = 4 , max = 4)
	private String courseNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 2 , max = 2)
	private String subjctNo;
	
	private String complSe1;
	
	private String complSe2;
	
	private String subjctCurrGrade;
	
	
	
	
	private SubjectVO subject;	// has a
	
}
