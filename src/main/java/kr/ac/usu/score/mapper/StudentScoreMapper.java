package kr.ac.usu.score.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.score.vo.ScoreObjectionVO;
import kr.ac.usu.score.vo.ScoreVO;

/**
 * 학생의 성적 관련 기능 Mapper
 * @author 김석호
 * @since 2023. 11. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일            수정자         수정내용
 * --------       --------    ----------------------
 * 2023. 11. 16.   김석호         최초작성
 * 2023. 11. 22.   김석호         금학기 성적 및 이의제기 관련 기능 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StudentScoreMapper {
	
	/**
	 * 특정 학생의 성적이 있는 학기 목록 조회
	 * 검색하려는 학생의 학번 key : id
	 * 제외하려는 현재 학기코드 key : semCd
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, String>> selectSemCdList(Map<String, String> paramMap);
	
	
	/**
	 * 특정 학생의 특정 학기 성적 조회
	 * 검색하려는 학생의 학번 key : id
	 * 검색하려는 학기코드 key : semCd
	 * @param paramMap
	 * @return
	 */
	public List<ScoreVO> selectScoreList(Map<String, String> paramMap);
	
	/**
	 * 특정 학생의 특정 학기 환산점수 조회
	 * 검색하려는 학생의 학번 key : id
	 * 제외하려는 현재 학기코드 key : semCd
	 * @param paramMap
	 * @return
	 */
	public double selectCalScore(Map<String, String> paramMap);
	
	/**
	 * 현재학기 강의평가 완료여부 조회
	 * 학생 학번 key : id
	 * 현재 학기 key : semCd
	 * @param paramMap
	 * @return 완료 : OK , 미완료 : NO
	 */
	public String selectAvailabilityForSelectCurrentSemesterScore(Map<String, String> paramMap);
	
	/**
	 * 성적 이의제기 한 건에 대한 정보 조회
	 * @param scoreObjection 학생 학번, 강의번호가 담긴 객체
	 * @return
	 */
	public ScoreObjectionVO selectScoreObjectionInformation(ScoreObjectionVO scoreObjection);
	
	
	/**
	 * 성적 이의제기 입력
	 * @param scoreObjection
	 * @return
	 */
	public int insertScoreObjection(ScoreObjectionVO scoreObjection);
}
