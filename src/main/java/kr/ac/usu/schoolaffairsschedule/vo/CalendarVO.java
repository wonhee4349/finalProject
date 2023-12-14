package kr.ac.usu.schoolaffairsschedule.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 22.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = "scduSe")
@ToString
public class CalendarVO {
	private String id;
	private String title;
	private String start;
	private String end;
	private String allDay;
	private String backgroundColor;
	private String textColor;
	private List<ExtendedPropsVO> extendedProps;
}
