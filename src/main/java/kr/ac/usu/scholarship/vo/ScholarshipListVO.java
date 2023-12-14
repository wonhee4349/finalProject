package kr.ac.usu.scholarship.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@EqualsAndHashCode(of = "schoSe")
@ToString
public class ScholarshipListVO implements Serializable{
	
	private int rnum;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 8 , max = 8)
	private String schoSe;
	
	@NotBlank
	private String schoNm;

	
	
	
	private List<ScholarshipVO> scholarship;		// has many
}
