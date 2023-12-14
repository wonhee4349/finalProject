package kr.ac.usu.classroom.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 시험기능 정답관련 테이블 VO
 * 학생이 정답을 제출할 때, 교수가 정답 등록할 때 List의 제네릭으로 사용
 * @author 김석호
 * @since 2023. 11. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 11. 27.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of= {"testQuesNo","testSe","lctreNo"})
@ToString
public class TestAnswerVO implements Serializable{
	
	private String stdntNo;
	
	private String testQuesNo;
	private String testSe;
	private String lctreNo;
	// 정답 등록용
	private String testAswper;
	// 시험 제출 정답용
	private String submitAswper;
}
