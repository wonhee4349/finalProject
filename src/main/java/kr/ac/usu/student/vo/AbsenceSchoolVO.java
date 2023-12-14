package kr.ac.usu.student.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.ac.usu.user.vo.StudentVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author PC-25
 * 휴학신청 테이블
 * @author 이재혁
 * @since 2023. 11. 9.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      이재혁      최초작성
 * 2023. 11. 13.     김석호      검증 조건 수정
 * 2023. 11. 14.     김석호      신청학기만 따로 표기할 용도의 논리프로퍼티 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = "abssklNo")
@ToString
@NoArgsConstructor
public class AbsenceSchoolVO implements Serializable {
	
	private int rnum;
	
//	@NotBlank
//	@Size(min = 12 , max = 12)
	private String abssklNo;
	
	private String reqSem;
	
//	@NotBlank
//	@Size(min = 8 , max = 8)
	private String stdntNo;
	
//	@NotBlank
//	@Size(min = 10 , max = 10)
	private String abssklDate;
	
	@NotBlank
	private String abssklCn;
	
//	@Size(min = 4, max = 4)
//	@Pattern(regexp = "[\\d]{4}")
	private String abssklYr;
	
	
//	@Size(min = 5, max = 5)
//	@Pattern(regexp = "[\\d]{5}")
	private String abssklSemstr;
	
//	@NotBlank
//	@Size(min = 2 , max = 2)
	private String confmSe;
	
	
	private String abssklReturn;

	private StudentVO student;
}
