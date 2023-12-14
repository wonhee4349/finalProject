package kr.ac.usu.scholarship.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.schoolaffairsschedule.vo.SemesterVO;
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
 * 2023. 11. 24.     김석호       장학금액 논리 프로퍼티 추가     
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Data
@EqualsAndHashCode(of = "schlshipNo")
@ToString
public class ScholarshipVO implements Serializable{
	
	private int rnum;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 11 , max = 11)
	private String schlshipNo;
	
	@NotBlank(groups = InsertGroup.class)
	private String schlshipTrgter;
	
	@NotBlank(groups = InsertGroup.class)
	private String schlshipSelectn;
	
	@NotBlank(groups = InsertGroup.class)
	private Integer schlshipAmount;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 2 , max = 2)
	private String pymntSe;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 5 , max = 5)
	private String semstrNo;	// 학기
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 5 , max = 5)
	private String schoSe;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 1 , max = 1)
	private String stndtReq;
	
	private String semstrYear;	// 년도
	
	
	
	private String schlshipAmountStr; // 표시금액용 프로퍼티
	
	private SemesterVO semester;	// has a
	private ScholarshipListVO scholarshipList;	// has a
	private List<ScholarshipStudentVO> scholarshipStudent;
	
}
