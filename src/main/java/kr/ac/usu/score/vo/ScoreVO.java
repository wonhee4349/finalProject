package kr.ac.usu.score.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.lecture.vo.LectureVO;
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
 * 수정일           수정자          수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.   이재혁          최초작성
 * 2023. 11. 22.  김석호          성적 이의제기용 프로퍼티 추가
 * 2023. 12. 05.  김재성          점수구분명 프로퍼티 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

@Data
@EqualsAndHashCode(of = "scoreSe")
@ToString
public class ScoreVO {
	
	@NotBlank
	@Size(min = 2 , max = 2)
	private String scoreSe;
	
	@NotBlank
	@Size(min = 8 , max = 8)
	private String stdntNo;
	
	@NotBlank
	@Size(min = 9 , max = 9)
	private String lctreNo;
	
	@NotBlank
	private Integer scoreScore;
	
	private String printScore;	/* 표시학점 */
	
	private StudentVO student;
	private LectureVO lecture;
	
	// 성적 이의제기 확인용 프로퍼티
	private ScoreObjectionVO scoreObjection;
	
	private String scoreSeNm;
	
	
}
