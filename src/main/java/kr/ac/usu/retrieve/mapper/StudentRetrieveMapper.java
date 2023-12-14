package kr.ac.usu.retrieve.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.score.vo.ScoreRankVO;

/**
 * 학생 조회전용 맵퍼
 * @author 김석호
 * @since 2023. 12. 1.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 12. 1.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StudentRetrieveMapper {
	
	/**
	 * 한 학생의 현재학기 포함 이수 학기 목록 조회
	 * @param stdntNo
	 * @return
	 */
	public List<Map<String, String>> selectStudentSemesterList(@Param("stdntNo") String stdntNo);
	
	/**
	 * 한 한기 수강 강의 목록 조회
	 * 학번 key : id
	 * 학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public List<LectureVO> selectStudentLectureListOnOneSemester(Map<String, String> paramMap);
	
	/**
	 * 현재학기를 제외한 총 이수학점 조회
	 * 학번 key : id
	 * 현재 학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public int selectAllCreditPoint(Map<String, String> paramMap);
	
	/**
	 * 현재학기를 제외한 총 취득학점 조회(성적 F인 과목제외 총이수학점)
	 * 학번 key : id
	 * 현재 학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public int selectAcceptedCreditPoint(Map<String, String> paramMap);
	
	/**
	 * 전공필수 취득학점
	 * 학번 key : id
	 * 현재 학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public int selectRequiredMajorCreditPoint(Map<String, String> paramMap);
	/**
	 * 전공선택 취득학점
	 * 학번 key : id
	 * 현재 학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public int selectNotRequiredMajorCreditPoint(Map<String, String> paramMap);
	/**
	 * 교양필수 취득학점
	 * 학번 key : id
	 * 현재 학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public int selectRequiredCultureCreditPoint(Map<String, String> paramMap);
	/**
	 * 교양선택 취득학점
	 * 학번 key : id
	 * 현재 학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public int selectNotRequiredCultureCreditPoint(Map<String, String> paramMap);
	
	/**
	 * 1전공 취득학점
	 * 학번 key : id
	 * 현재 학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public int selectMajor1CreditPoint(Map<String, String> paramMap);
	
	/**
	 * 2전공 취득학점
	 * 학번 key : id
	 * 현재 학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public int selectMajor2CreditPoint(Map<String, String> paramMap);
	
	/**
	 * 부전공 취득학점
	 * 학번 key : id
	 * 현재 학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public int selectMinorCreditPoint(Map<String, String> paramMap);
	
	/**
	 * 비대면영상 취득학점
	 * 학번 key : id
	 * 현재 학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public int selectOnlineLectureCreditPoint(Map<String, String> paramMap);
	/**
	 * 비대면실시간 취득학점
	 * 학번 key : id
	 * 현재 학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public int selectUntactLectureCreditPoint(Map<String, String> paramMap);
	/**
	 * 학기별 석차 관련 정보 조회
	 * @param info 학번, 학기코드가 들어간 정보 조회용 ScoreRankVO 객체
	 * @return
	 */
	public ScoreRankVO selectRankInfoOnOneSemester(ScoreRankVO info);
	
	/**
	 * 전체 총 학점 평균
	 * @param id
	 * @return
	 */
	public double totalScore(@Param("stdntNo") String id);
	
	
	/**
	 * 금학기 학생 시간표 조회
	 * 학번 key : id
	 * 현재학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, String>> selectStudentTimeTable(Map<String, String> paramMap);
	
}
