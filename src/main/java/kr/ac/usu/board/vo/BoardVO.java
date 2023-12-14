package kr.ac.usu.board.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.classroom.vo.AnswerVO;
import kr.ac.usu.user.vo.StaffVO;
import kr.ac.usu.validate.grouphint.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 8.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 8.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Data
@EqualsAndHashCode(of = "boNo")
@ToString
public class BoardVO implements Serializable{
	
	private Integer rnum;

	@NotBlank(groups = InsertGroup.class)
	private String boNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 8 , max = 8)
	private String boWrter;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 10 , max = 10)
	private String boDate;
	
	@NotBlank
	private String boTitle;
	
	@NotBlank
	private String boCn;
	
	private Integer boCnt;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 4 , max = 4)
	private String bdSe;
	
	private String atchFileNo;
	
	private StaffVO staff;
	
	private String sklstfNm;
	
	private String sklstfNo;
	
	private List<AnswerVO> answer;		// has many
}
