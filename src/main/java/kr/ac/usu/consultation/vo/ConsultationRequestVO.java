package kr.ac.usu.consultation.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.user.vo.StudentVO;
import kr.ac.usu.validate.grouphint.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 8.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 8.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = "cnsltNo")
@ToString
public class ConsultationRequestVO {
	
	private int rnum;
	private String complSe;
	
	
	@NotBlank
	@Size(min = 10 , max = 10)
	private String cnsltNo;
	
	@NotBlank
	@Size(min = 8 , max = 8)
	private String stdntNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 10, min = 10,groups = InsertGroup.class)
	private String cnsltRequstDate;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 2, min = 2,groups = InsertGroup.class)
	private String cnsltSe;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 10, min = 10,groups = InsertGroup.class)
	private String cnsltRequstTime;
	
	@NotBlank(groups = InsertGroup.class)
	private String cnsltRequstCn;
	
	@NotBlank(groups = InsertGroup.class)
	private String cnsltRequstProcess;
	
	
	private String cnsltRequstReturn;
	
	private String timeTable;	// 한글로 된 시간표
	
	
	private ConsultationVO consulatation;		// has a
	private StudentVO student;					// has a
	
	// 임시방편!
	private String whMsg;

}
