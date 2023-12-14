package kr.ac.usu.student.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 김석호
 * @since 2023. 11. 8.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * 학적변동이력 테이블 VO
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일         수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 8.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = {"semstrNo","stdntNo"})
public class SchoolRegisterHistoryVO implements Serializable {
	@NotBlank
	private String semstrNo;
	@NotBlank
	private String stdntNo;
	@NotBlank
	private String sknrgHistDate;
	@NotBlank
	private String sknrgsSe;
}
