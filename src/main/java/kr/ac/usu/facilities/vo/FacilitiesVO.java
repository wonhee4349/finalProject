package kr.ac.usu.facilities.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.user.vo.ProfessorVO;
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
@EqualsAndHashCode(of = "fcltsNo")
@ToString
public class FacilitiesVO {
	
	private Integer rnum;

	@NotBlank(groups = InsertGroup.class)
	@Size(min = 5 , max = 5)
	private String fcltsNo;
	
	@NotBlank
	private String fcltsNm;
	
	@NotBlank
	private Integer fcltsNmpr;
	
	@NotBlank
	private String fcltsPurps;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 2 , max = 2)
	private String buldNo;
	
	@NotBlank
	@Size(min = 8 , max = 8)
	private String prfsorNo;
	
	
	
	
	
	private BuildingVO building;	// has a
	private List<FacilitiesTimetableVO> facilitiesTimetable;	// has many
	private ProfessorVO professor;	// has a
}
