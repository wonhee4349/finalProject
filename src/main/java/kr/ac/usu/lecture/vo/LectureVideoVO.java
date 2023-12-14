package kr.ac.usu.lecture.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.ac.usu.board.vo.AttatchingFileVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = "lctreVidoNo")
@ToString
public class LectureVideoVO {
	
	@NotBlank
	private String lctreVidoNo;
	
	@NotBlank
	@Size(min = 9 , max = 9)
	private String lctreNo;
	
	@NotBlank
	@Size(min = 2 , max = 2)
	private String lctreVdWeek;
	
	@NotBlank
	private String lctreVdTm;
	
	@NotBlank
	private String lctreVdStdrTm;
	
	
	private String lctreVdBeginTm;
	
	
	private String lctreVdEndTm;
	
	@NotBlank
	private String atchFileNo;
	
	private LectureVO lecture;
	
	private List<AttatchingFileVO> attatchingFileList;
	
}