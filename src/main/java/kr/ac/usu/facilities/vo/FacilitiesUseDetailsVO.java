package kr.ac.usu.facilities.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@EqualsAndHashCode(of = "fcltsUseNo")
@ToString
public class FacilitiesUseDetailsVO {

	@NotBlank
	private String fcltsUseNo;
	
	@NotBlank(groups = InsertGroup.class)
	private String fcltUseUser;
	
	private String fcltUsePurps;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 5 , max = 5, groups = InsertGroup.class)
	private String fcltsNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 2 , max = 2, groups = InsertGroup.class)
	private String tmtbSe;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 10 , max = 10, groups = InsertGroup.class)
	private String fcltsUseDate;
	
	private FacilitiesVO facilities;
}
