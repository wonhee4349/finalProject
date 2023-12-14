package kr.ac.usu.subject.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.student.vo.GraduationRequestVO;
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
@EqualsAndHashCode(of = "subjctNo")
@ToString
public class SubjectVO {

	@NotBlank(groups = InsertGroup.class)
	@Size(min = 2 , max = 2)
	private String subjctNo;
	
	@NotBlank(groups = InsertGroup.class)
	private String subjctNm;
	
	@NotBlank
	private String subjctOffm;
	
	@NotBlank
	@Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}")
	private String subjctTelno;
	
	private String subjctDean;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 1 , max = 1)
	private String clgNo;
	
	
	
	
	private CollegeVO college;		// has a
	private List<GraduationRequestVO> graduationRequest;	// has many
	private List<ProfessorVO> professor;	// has many
	private List<SubjectCurriculumVO> curriculum;	// has many
	
}
