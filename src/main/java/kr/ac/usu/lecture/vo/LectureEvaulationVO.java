package kr.ac.usu.lecture.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 강의평가 기록 테이블에 사용할 VO
 * @author 김석호
 * @since 2023. 11. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 11. 22.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"stdntNo","lctreEvlIem","lctreNo"})
public class LectureEvaulationVO {
	private String stdntNo;
	private String lctreEvlIem;
	private String lctreNo;
	private Integer lctreEvlScoreNo;
}
