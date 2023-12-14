package kr.ac.usu.lecture.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.lecture.mapper.ProfessorLectureMapper;
import kr.ac.usu.lecture.service.ProfessorLecturetService;
import kr.ac.usu.lecture.vo.LectureActionPlanVO;
import kr.ac.usu.lecture.vo.LectureEvaluationStandardVO;
import kr.ac.usu.lecture.vo.LectureRequestInfoVO;
import kr.ac.usu.lecture.vo.LectureRequestVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.user.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 7.  이재혁        최초작성
 * 2023. 11.10.  김재성        클래스타입 수정
 * 2023. 11.13.  김재성        메소드 수정
 * 2023. 11.17.  김재성       교수 강의 수강학생 목록 조회 메서드 생성
 * 2023. 11.24.  김재성      강의개설 신청 등록 insert, 강의개설 신청목록 select
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Service
public class ProfessorLectureServiceImpl implements ProfessorLecturetService {
	
	@Inject
	private ProfessorLectureMapper lectureMapper;
	
	// 강의목록
	@Override
	public void retrieveLectureList(PaginationInfo<LectureVO> paging) {
		
		log.info("강의내역 내역 매개변수 : {}", paging);
		
		int totalRecord = lectureMapper.lectureSelectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		
		List<LectureVO> dataList = lectureMapper.selectLectureList(paging);
		paging.setDataList(dataList);
		
		log.info("강의내역 내역 리스트 : {}", dataList);
	}
	
	// 강의 수강학생 목록 리스트
	@Override
	public List<StudentVO> retrieveLectureStudentList(String lctreNo) {
		
		log.info("강의 번호 유무 확인 : {}", lctreNo);
		
		List<StudentVO> studentList = lectureMapper.selectLectureStudentList(lctreNo);
		
		return studentList;
	}
	
	//강의개설 신청 목록
	@Override
	public void retrieveLectureRequestList(PaginationInfo<LectureRequestVO> paging) {
		
		//log.info("강의내역 내역 매개변수 : {}", paging);
		
		int totalRecord = lectureMapper.selectLectureRequestTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		
		List<LectureRequestVO> dataList = lectureMapper.selectLectureRequestList(paging);
		paging.setDataList(dataList);
		
		//log.info("강의내역 내역 리스트 : {}", dataList);
		
	}
	
	//강의 개설 신청 상세 조회
	@Override
	public LectureRequestVO retrieveLectureRequestView(LectureRequestVO lectureRequest) {
		
		log.info("LectureRequestVO에 담아온 값 유무 확인 : {}", lectureRequest);
		
		LectureRequestVO lectureRequestView = lectureMapper.selectLectureRequestView(lectureRequest);
		
		log.info("selectLectureRequestView(lectureRequest)담아온 값 유무 확인 : {}", lectureRequest);
		
		return lectureRequestView;
	}

	// 강의 개설 신청
	@Transactional
	@Override
	public int newLectureRequest(LectureRequestVO lectureRequestVO) {

		//1) LECTURE_REQUEST 에 insert
		int result = this.lectureMapper.insertLectureRequest(lectureRequestVO);
		
		//2) LECTURE_REQUEST_INFO 에 insert
		List<LectureRequestInfoVO> lectureRequestInfoVOList = lectureRequestVO.getLectureRequestInfoVOList();
		for(LectureRequestInfoVO lectureRequestInfoVO : lectureRequestInfoVOList) {
			lectureRequestInfoVO.setLctreReqstNo(lectureRequestVO.getLctreReqstNo());
			result += this.lectureMapper.insertLectureRequestInfo(lectureRequestInfoVO);
		}
		
		//3) LECTURE_ACTION_PLAN 에 insert
		LectureActionPlanVO lectureActionPlanVO = lectureRequestVO.getLectureActionPlanVO();
		lectureActionPlanVO.setLctreAcnutnoNo(lectureRequestVO.getLctreReqstNo());
		result += this.lectureMapper.insertLectureActionPlan(lectureActionPlanVO);
		
		//4) LECTURE_EVALUATION_STANDARD 에 insert
		List<LectureEvaluationStandardVO> lectureEvaluationStandardVOList = lectureActionPlanVO.getLectureEvaluationStandardVOList();
		for(LectureEvaluationStandardVO lectureEvaluationStandardVO : lectureEvaluationStandardVOList) {
			lectureEvaluationStandardVO.setLctreAcnutnoNo(lectureActionPlanVO.getLctreAcnutnoNo());
			result += this.lectureMapper.insertLectureEvaluationStandard(lectureEvaluationStandardVO);
		}
		
		return result;
	}
	
}
