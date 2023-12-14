package kr.ac.usu.score.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.lecture.vo.AttendanceLectureVO;
import kr.ac.usu.lecture.vo.LectureEvaluationStandardVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.score.vo.ScoreObjectionVO;
import kr.ac.usu.score.vo.ScoreVO;
import kr.ac.usu.user.vo.StudentVO;



/**
 * @author PC-21
 *
 * @author 김재성
 * @since 2023. 12. 04.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 	수정일            수정자            수정내용
 * ----------     --------    ----------------------
 * 2023.12.04.      김재성       최초작성
 * 2023.12.05.      김재성       성적이의신청 정정
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface ProfessorScoreMapper {
	
	/**
	 * 교수가 강의중인 강의목록
	 * @param lctreNo 교수번호
	 * @return List<LectureVO>
	 */
	public List<LectureVO> selectScoreProfessorLecture(String prfsorNo);
	
	/**
	 * 한 강의에 성적에 대한 이의신청 목록
	 * @param lctreNo, prfsorNo
	 * @return List<ScoreObjectionVO>
	 */
	public List<ScoreObjectionVO> selectScoreObjectionList(@RequestParam("lctreNo") String lctreNo);
	
	/**
	 * 한 강의에 성적에대한 이의신청 상세내역
	 * @param attendanceLectureVO lctreNo : 강의코드 , stdntNo : 학번
	 * @return
	 */
	public ScoreObjectionVO selectScoreObjectionDetailView(AttendanceLectureVO attendanceLectureVO);
	
	/**
	 * 성적이의신청에 대한 반려 처리
	 * @param scoreObjection = lctreNo : 강의코드, stdntNo : 학번, confmSe : 03 [반려], 반려사유
	 * @return
	 */
	public int updateRefuseScoreObjection(ScoreObjectionVO scoreObjection);
	
	/**
	 * 성적이의신청에 대한 정정처리
	 * @param scoreObjection =  lctreNo : 강의코드, stdntNo : 학번, confmSe : 02 [승인완료], 정정사유
	 * @return
	 */
	public int updateResetScoreObjection(ScoreObjectionVO scoreObjection);
	
	/**
	 * 성적이의신청에 승인 후 점수 정정
	 * @param scoreVO
	 * @return
	 */
	public int updateResetScore(ScoreVO scoreVO);

	/**
	 * 성적 등록 하기 위한 수강학생 목록 리스트
	 * @param lctreNo
	 * @return
	 */
	public List<StudentVO> selectScoreRegistrationStudentList(String lctreNo);
	
	/**
	 * 성적 등록하기위한 모달 열엇을때 강의평가기준
	 * @param lctreNo
	 * @return scoreSe,lctreEvlStdrReflct
	 */
	public List<LectureEvaluationStandardVO> selectLectureEvaluationStandard(String lctreNo);
	
	/**
	 * 성적 등록 ( 한 학생 별 시험구분별 )
	 * @param scoreObejct
	 * @return
	 */
	public int insertScoreRegistration(ScoreVO scoreVO);
}
