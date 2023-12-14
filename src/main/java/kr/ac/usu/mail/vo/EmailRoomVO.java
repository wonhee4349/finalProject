package kr.ac.usu.mail.vo;

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
@EqualsAndHashCode(of = "emailNo")
@ToString
public class EmailRoomVO {
	
	@NotBlank
	@Size(min = 1 , max = 40)
	private String emailNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 40, groups = InsertGroup.class)
	private String emailSender;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 40, groups = InsertGroup.class)
	private String emailRcver;
	
	@NotBlank(groups = InsertGroup.class)
	private String emailTitle;
	
	@NotBlank(groups = InsertGroup.class)
	private String emailCn;
	
	@NotBlank
	private String emailSenderSave;
	
	@NotBlank
	private String emailRcverSave;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 20, groups = InsertGroup.class)
	private String emailSndngDate;
	
	
	private String atchFileNo;
	
}
