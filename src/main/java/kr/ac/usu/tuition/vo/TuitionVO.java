package kr.ac.usu.tuition.vo;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.ac.usu.schoolaffairsschedule.vo.SemesterVO;
import kr.ac.usu.user.vo.StudentVO;
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
@EqualsAndHashCode(of = "tutnNhtNo")
@ToString
public class TuitionVO implements Serializable{
	
	private int rnum;

	@NotBlank(groups = InsertGroup.class)
	@Size(min = 30 , max = 30)
	private String tutnNhtNo;
	
	private Integer tutnEtrncf;
	
	@NotBlank(groups = InsertGroup.class)
	private Integer tutnTutfee;
	
	private Integer tutnSchlship;
	
	private String tutnPayde;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 5 , max = 5)
	private String semstrNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 8 , max = 8)
	private String stdntNo;
	
	@NotBlank
	private Integer realPay;	// 총 등록금액
	
	@NotBlank
	private String tutnPay;		// 등록금 납부 여부
	
	@NotBlank
	private String tutnPayYr;	// 등록금 년도
	
	@NotBlank
	private String tutnPaySems;	// 등록금 학기
	
	private String tutnEtrncfStr; // 표시금액용 프로퍼티
	private String tutnTutfeeStr; // 표시금액용 프로퍼티
	private String tutnSchlshipStr; // 표시금액용 프로퍼티
	private String realPayStr; // 표시금액용 프로퍼티
	
	
	private StudentVO student;		// has a
	private SemesterVO semester;	// has a
	
}
