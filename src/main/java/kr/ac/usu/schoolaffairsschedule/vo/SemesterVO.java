package kr.ac.usu.schoolaffairsschedule.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.scholarship.vo.ScholarshipVO;
import kr.ac.usu.tuition.vo.TuitionVO;
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
@EqualsAndHashCode(of = "semstrNo")
@ToString
public class SemesterVO implements Serializable{

	@NotBlank(groups = InsertGroup.class)
	@Size(min = 5 , max = 5)
	private String semstrNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 4 , max = 4)
	private String semstrYr;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 1 , max = 1)
	private String semstr;
	
	
	
	
	private List<TuitionVO> tuition;			// has many
	private List<ScholarshipVO> scholarship;	// has many
	
}
