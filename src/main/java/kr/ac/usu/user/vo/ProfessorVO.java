package kr.ac.usu.user.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.validate.grouphint.DeleteGroup;
import kr.ac.usu.validate.grouphint.InsertGroup;
import kr.ac.usu.validate.grouphint.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 김재성
 * @since 2023. 11. 08
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일            수정자       수정내용
 * --------     --------    ----------------------
 * 2023.11.08      김재성       최초작성
 * 2023.11.08      김석호       시큐리티 롤 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of="prfsorNo")
@ToString
public class ProfessorVO implements Serializable{
	
	private Integer rnum;
	
	@NotBlank
	private String prfsorNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min=0,max=8, groups = InsertGroup.class)
	private String prfsorNm;
	
	@NotBlank
	@JsonIgnore
	private String prfsorPassword;
	
	@Size(max=6, min=6, groups = InsertGroup.class)
	private String prfsorIhidnum1;
	
	@Size(max=8, min=8, groups = InsertGroup.class)
	private String prfsorIhidnum2;
	
	@NotBlank
	private String prfsorZip;
	
	@NotBlank
	private String prfsorAdres1;
	
	private String prfsorAdres2;
	
	@NotBlank
	@Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}")
	private String prfsorTelno;
	@NotBlank
	private String prfsorEmplmn;
	
	private String prfsorRetire;
	@NotBlank
	private String prfsorEmplynForm;
	
	@NotBlank
	private String prfsorNlty;
	
	@NotBlank
	private String sexdstnSe;
	
	@NotBlank
	private String subjctNo;
	
	private String subjctNm;
	
	private String subjctTelno;
	
	private String atchFileNo;
	
	private MultipartFile profileImage;
	
	private String professorImg;	// 통합첨부파일 테이블 insert
	
	private SubjectVO subject ; // has 1:1 관계
	
	public String getUserRole() {
		return "ROLE_PROFESSOR";
	}
	
	
	
}
