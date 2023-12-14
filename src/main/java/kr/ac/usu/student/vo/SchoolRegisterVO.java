package kr.ac.usu.student.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.ac.usu.user.vo.StudentVO;
import kr.ac.usu.validate.grouphint.DeleteGroup;
import kr.ac.usu.validate.grouphint.InsertGroup;
import kr.ac.usu.validate.grouphint.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 9.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자     수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      이재혁      최초작성
 * 2023. 11. 15.     김석호      컬럼추가로 인한 프로퍼티 추가, 검증조건 변경 
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
//@Data
@EqualsAndHashCode(of = "sknrgNo")
@ToString
@Getter
@Setter
//@JsonAutoDetect
//@NoArgsConstructor
//@JsonAutoDetect
public class SchoolRegisterVO implements Serializable {
	
	private int rnum;
	
	@NotBlank(groups = {DeleteGroup.class,UpdateGroup.class})
	@Size(min = 13 , max = 13,groups = {DeleteGroup.class,UpdateGroup.class})
	private String sknrgNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 2 , max = 2 , groups = InsertGroup.class)
	private String sknrgSe;
	
//	@NotBlank
//	@Size(min = 8 , max = 8)
	private String stdntNo;
	
//	@NotBlank
//	@Size(min = 10 , max = 10)
	private String sknrgDate;
	
//	@NotBlank
//	@Size(min = 2 , max = 2)
	private String confmSe;
	
	private String sknrgReturn;
	
	private String subjctNo;
	
	private String subjctNm;
	
	private String reqSubjctNo;
	
	private String reqSubjctNm;

	private String targetSem;
	
	private StudentVO student;
}
