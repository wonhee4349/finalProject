package kr.ac.usu.club.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
 * 2023. 11. 8.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = "clubEsNo")
@ToString
public class ClubEstblVO {
	
	private int rnum;

	@NotBlank
	@Size(min = 6 , max = 6)
	private String clubEsNo;
	
	@NotBlank(groups = InsertGroup.class)
	private String clubEsNm;	
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 8 , max = 8, groups = InsertGroup.class)
	private String stdntNo;
	
	@Size(min = 5 , max = 5)
	private String fcltsNo;		// 동아리실 코드
	
	private String clubEsIntrcn;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 2 , max = 2, groups = InsertGroup.class)
	private String clubSe;		// 동아리 종류 코드 
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 2 , max = 2, groups = InsertGroup.class)
	private String confmSe;
	
	private String clubEsReturn;
	private String buldNo;
	
	private String clubSeNm;	// 동아리 종류
	private String fcltsNoNm;	// 동아리실
	
	private StudentVO student;
	
}
