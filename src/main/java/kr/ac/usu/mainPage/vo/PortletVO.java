package kr.ac.usu.mainPage.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.ToString;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 30.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 30.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Data
@ToString
public class PortletVO implements Serializable{
	
	@NotBlank
	private String widgetId;
	@NotBlank
	private String userNo;
	
	private String content;
	
	private Integer x;
	private Integer y;
	private Integer w;
	private Integer h;
	

}
