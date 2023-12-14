package kr.ac.usu.facilities.vo;

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
@EqualsAndHashCode(of = {"tmtbSe", "fcltsNo"})
@ToString
public class FacilitiesTimetableVO {

	@NotBlank(groups = InsertGroup.class)
	@Size(min = 2 , max = 2)
	private String tmtbSe;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 5 , max = 5)
	private String fcltsNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 9 , max = 9)
	private String lctreReqstNo;
	
	
	
	
	private FacilitiesVO facilities;	// has a
	
}
