package kr.ac.usu.user.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.ac.usu.validate.grouphint.InsertGroup;
import kr.ac.usu.validate.grouphint.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 김석호
 * @since 2023. 11. 8.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일             수정자        수정내용
 * --------    	  --------    ----------------------
 * 2023. 11. 8.      김석호       최초작성
 * 2023. 11. 8.      문선영       최초작성22
 * 2023. 11. 9.      김석호       시큐리티 롤 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = "sklstfNo")
@ToString
public class StaffVO implements Serializable {
	
	private Integer rnum;
	
	@NotBlank
	@Size(min = 8 , max = 8)
	private String sklstfNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 6, min = 2,groups = InsertGroup.class)
	private String sklstfNm;
	
	@NotBlank
	@JsonIgnore
	private String sklstfPassword;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 6 , max = 6, groups = InsertGroup.class)
	private String sklstfIhidnum1;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 7 , max = 7, groups = InsertGroup.class)
	private transient String sklstfIhidnum2;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 5 , max = 5, groups = InsertGroup.class)
	private String sklstfZip;
	
	@NotBlank(groups = InsertGroup.class)
	private String sklstfAdres1;
	
	private String sklstfAdres2;
	
	@NotBlank(groups = InsertGroup.class)
	@Pattern(regexp = "010-\\d{3,4}-\\d{4}")
	private String sklstfTelno;
	
	@NotBlank(groups = InsertGroup.class)
	private String sklstfEncpn;
	
	private String sklstfRetire;
	
	@NotBlank(groups = InsertGroup.class)
	private String emplymSe;
	
	@NotBlank(groups = InsertGroup.class)
	private String sklstfNlty;
	
	@NotBlank(groups = InsertGroup.class)
	private String sklstfSexdstn;
	
	private String atchFileNo;
	
	private MultipartFile profileImage;
	
	
	
	
	public String getUserRole() {
		return "ROLE_STAFF";
	}
	
}
