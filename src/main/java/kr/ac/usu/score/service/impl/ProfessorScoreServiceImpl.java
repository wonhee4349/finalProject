package kr.ac.usu.score.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.lecture.vo.AttendanceLectureVO;
import kr.ac.usu.lecture.vo.LectureEvaluationStandardVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.score.mapper.ProfessorScoreMapper;
import kr.ac.usu.score.service.ProfessorScoreService;
import kr.ac.usu.score.vo.ScoreObjectionVO;
import kr.ac.usu.score.vo.ScoreVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

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
 * 2023.12.05.      김재성       성적이의신청 상세내역 및 성적 등록
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Service
public class ProfessorScoreServiceImpl implements ProfessorScoreService {
	
	@Inject
	private ProfessorScoreMapper mapper;	
	
	@Override
	public List<LectureVO> retrieveScoreProfessorLecture(String prfsorNo) {
		
		// 성적관리 강의선택 리스트
		List<LectureVO> lectureList = mapper.selectScoreProfessorLecture(prfsorNo);
		
		return lectureList;
	}
	
	//성적 이의신청 리스트
	@Override
	public List<ScoreObjectionVO> retrieveScoreObjectionList(String lctreNo) {
		List<ScoreObjectionVO> scoreObjectionList = mapper.selectScoreObjectionList(lctreNo);
		
		return scoreObjectionList;
	}

	// 성적이의신청 상세내역
	@Override
	public ScoreObjectionVO retrieveScoreObjectionDetailView(AttendanceLectureVO attendanceLectureVO) {
		
		ScoreObjectionVO scoreObjectionView = mapper.selectScoreObjectionDetailView(attendanceLectureVO);
		
		return scoreObjectionView;
	}
	
	// 성적이의신청 반려
	@Override
	public ServiceResult modifyRefuseScoreObjection(ScoreObjectionVO scoreObjection) {
		
		int cnt = mapper.updateRefuseScoreObjection(scoreObjection);
		
		ServiceResult result;
		if(cnt >0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		
		return result;
	}
	
	// 성적이의신청 승인 정정
	@Transactional
	@Override
	public ServiceResult modifyResetScoreObjection(ScoreObjectionVO scoreObjection) {
		
		int cnt = this.mapper.updateResetScoreObjection(scoreObjection);
		
		List<ScoreVO> scoreList = scoreObjection.getScoreVO();
			for(ScoreVO scoreVO : scoreList) {
				scoreVO.setLctreNo(scoreObjection.getLctreNo());
				scoreVO.setStdntNo(scoreObjection.getStdntNo());
				cnt += this.mapper.updateResetScore(scoreVO);
				
				log.info("점수 변경 데이터 뭐나오는지 확인 : {}",scoreVO);
			}
				
		ServiceResult result;
		if(cnt >0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		
		return result;
	}
	
	// 성적등록 하기위한 수강학생 목록 리스트
	@Override
	public List<StudentVO> retrieveScoreRegistrationStudentList(String lctreNo) {
		
		List<StudentVO> studentList = mapper.selectScoreRegistrationStudentList(lctreNo);
		
		log.info("수강학생 목록 리스트 체크 ! : {}", studentList);
		
		return studentList;
	}

	// 성적등록 Modal Open 할때 강의평가기준
	@Override
	public List<LectureEvaluationStandardVO> retrieveLectureEvaluationStandard(String lctreNo) {
		
		List<LectureEvaluationStandardVO> lctreEvlStdrList = mapper.selectLectureEvaluationStandard(lctreNo);
		
		return lctreEvlStdrList;
	}
	
	// 성적 등록 한 학생별, 시험구분별
	@Transactional
	@Override
	public ServiceResult createScoreRegistration(ScoreObjectionVO scoreObejct) {
			int cnt= 0;
			
			List<ScoreVO> scoreList = scoreObejct.getScoreVO();
			for(ScoreVO scoreVO : scoreList) {
				scoreVO.setLctreNo(scoreObejct.getLctreNo());
				scoreVO.setStdntNo(scoreObejct.getStdntNo());
				cnt += this.mapper.insertScoreRegistration(scoreVO);
				
				log.info("점수 변경 데이터 뭐나오는지 확인 : {}",scoreVO);
			}
				
		ServiceResult result;
		if(cnt >0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		
		return result;
	}
	

}
