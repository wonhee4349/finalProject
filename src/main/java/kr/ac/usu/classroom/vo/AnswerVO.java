package kr.ac.usu.classroom.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.ac.usu.board.vo.BoardVO;
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
@EqualsAndHashCode(of = "answerNo")
@ToString
public class AnswerVO implements Serializable{

	@NotBlank(groups = InsertGroup.class)
	private String answerNo;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 8 , max = 8)
	private String answerWrter;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 10 , max = 10)
	private String answerDate;
	
	@NotBlank
	private String answerCn;
	
	@NotBlank(groups = InsertGroup.class)
	private String boNo;
	
	
	
	
	private BoardVO board;		// has a
	
}
