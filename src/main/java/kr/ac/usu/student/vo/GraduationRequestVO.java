package kr.ac.usu.student.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.validate.grouphint.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <pre>
 * 졸업요건테이블
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
@EqualsAndHashCode(of = {"grdtnRqisitNo", "subjctNo"})
@ToString
public class GraduationRequestVO {

	@NotBlank(groups = InsertGroup.class)
	@Size(min = 7 , max = 7)
	private String grdtnRqisitNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 2 , max = 2)
	private String subjctNo;
	
	private String grdtnRqisitRm;
	
	
	
	
	private SubjectVO subject;	// has a
	
}
