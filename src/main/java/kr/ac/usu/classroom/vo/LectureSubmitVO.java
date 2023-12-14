package kr.ac.usu.classroom.vo;

import javax.validation.constraints.NotBlank;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @author PC-21
 *
 * @author 김재성
 * @since 2023. 12. 01.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 *  수정일          수정자            수정내용
 * --------     ---------    ----------------------
 * 2023.12.01.    김재성        최초작성
 * 2023.12.02.    김재성        파일 VO has 프로퍼티

 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = {"stdntNo","crNo"})
@ToString
public class LectureSubmitVO {

	@NotBlank	
	private String stdntNo;
	@NotBlank
	private String crNo;
	
	private String atchFileNo;
	
	private StudentVO student;
	
	private AttatchingFileVO atchFile;
}
