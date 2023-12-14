package kr.ac.usu.lecture.vo;

import java.util.List;

import lombok.Data;

@Data
public class LectureActionPlanVO {
	private String lctreAcnutnoNo;
	private String lctreAcnutnoGoal;//강의목표 및 개요
	private String lctreAcnutnoPlan;//주차별 강의계획
	private String lctreAcnutnoRefer;//참고사항
	
	//강의계획서 : 강의평가기준 = 1 : N
	private List<LectureEvaluationStandardVO> lectureEvaluationStandardVOList;
}
