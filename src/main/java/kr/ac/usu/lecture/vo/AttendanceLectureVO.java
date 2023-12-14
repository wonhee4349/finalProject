package kr.ac.usu.lecture.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.score.vo.ScoreObjectionVO;
import kr.ac.usu.score.vo.ScoreVO;
import kr.ac.usu.user.vo.StudentVO;
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
 *    수정일         수정자               수정내용
 * ----------     --------    ----------------------
 * 2023.11.9.      이재혁      최초작성
 * 2023.12.05.     김재성      성적이의신청 성적 프로퍼티 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = "stdntNo")
@ToString
public class AttendanceLectureVO {

	@NotBlank
	@Size(min = 8 , max = 8)
	private String stdntNo;
	
	@NotBlank
	@Size(min = 9 , max = 9)
	private String lctreNo;
	
	private StudentVO student;
	private LectureVO lecture;
	
	private List<ScoreObjectionVO> scoreObjection;
	private List<ScoreVO> score;
}
