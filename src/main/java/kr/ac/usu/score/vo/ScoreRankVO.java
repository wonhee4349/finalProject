package kr.ac.usu.score.vo;

import java.io.Serializable;

import kr.ac.usu.user.vo.StudentVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 석차 조회용 논리프로퍼티 VO
 * @author 김석호
 * @since 2023. 12. 07.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일          수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 12. 07. 김석호        최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "stdntNo")
public class ScoreRankVO implements Serializable{
	// 학번
	private String stdntNo;
	// 학과석차
	private String subjctRank;
	// 학과인원
	private String subjctPeople;
	// 학과내 학년석차
	private String subjctGradeRank;
	// 학과내 학년인원
	private String subjctGradePeople;
	
	private StudentVO student; /* has 관계 */
	
	private String semCd; /* 학기코드 */
}
