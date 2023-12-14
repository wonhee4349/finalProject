package kr.ac.usu.score.vo;


import java.util.List;

import kr.ac.usu.lecture.vo.LectureEvaluationStandardVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SCORE_OBJECTION 테이블 VO
 * @author 김석호
 * @since 2023. 11. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일          수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 22. 김석호        최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"stdntNo","lctreNo"})
@ToString(exclude = {"scoreObjcCn","scoreObjcReturn"})
public class ScoreObjectionVO {
	
	private String stdntNo;
	private String lctreNo;
	private String scoreObjcCn;
	private String confmSe;
	private String scoreObjcReturn;
	
	private LectureVO lecture;
	//SCHOOL_REGISTER_STATUS 테이블도 포함됨
	private StudentVO student;
	
	//이의신청 : 성적 = 1 : N
	private List<ScoreVO> scoreVO;
}
