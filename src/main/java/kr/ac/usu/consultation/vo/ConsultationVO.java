package kr.ac.usu.consultation.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.user.vo.StaffVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 13.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 13.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Data
@EqualsAndHashCode
@ToString
public class ConsultationVO {
	
	private int rnum;

	@NotBlank
	@Size(min = 10 , max = 10)
	private String cnsltNo;
	
	@NotBlank
	@Size(min = 8 , max = 8)
	private String cnsltCnsltnt;
	
	@NotBlank
	@Size(min = 8 , max = 8)
	private String stdntNo;
	
	@NotBlank
	@Size(min = 10 , max = 10)
	private String cnsltDate;
	
	@NotBlank
	@Size(min = 2 , max = 2)
	private String tmtbSe;
	
	private String cnsltDtls;
	
	
	private StudentVO student;
	private StaffVO staff;
	private ConsultationRequestVO request;
	
	
	private ConsultationRequestVO consultationRequest;		// has a
	
}
