package kr.ac.usu.club.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.facilities.vo.FacilitiesVO;
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
 * 2023. 11. 8.      김원희      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = {"clubNo", "stdntNo"})
@ToString
public class ClubSubscribeVO {



	@NotBlank
	@Size(min = 5 , max = 5)
	private String clubNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 8 , max = 8, groups = InsertGroup.class)
	private String stdntNo;
	
	
	private StudentVO student;
	private String clubSbscrbDate;
	private String confmSe;
	private String cluSbscrbCn;
	private String clubSbscrbReturn;
	
	
}
