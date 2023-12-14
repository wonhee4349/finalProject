package kr.ac.usu.lecture.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.consultation.mapper.StaffConsultationRequestMapper;
import kr.ac.usu.lecture.mapper.StaffLectureRequestMapper;
import kr.ac.usu.lecture.service.StaffLectureRequestService;
import kr.ac.usu.lecture.service.StaffLectureService;
import kr.ac.usu.lecture.vo.LectureRequestVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 30.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 30.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StaffLectureRequestServiceImpl implements StaffLectureRequestService{

	private final StaffLectureRequestMapper lectureReqMapper;
	
	// 상담 신청 리스트
	@Override
	public void retrieveLectureRequestList(PaginationInfo<LectureRequestVO> paging) {

		int totalRecord = lectureReqMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<LectureRequestVO> dataList = lectureReqMapper.selectLectureRequestList(paging);
		paging.setDataList(dataList);
		
	}

	// 상담 신청 정보
	@Override
	public LectureRequestVO retrieveLectureRequestInfo(String lctreReqstNo) {
		LectureRequestVO lectureRequestInfo = lectureReqMapper.selectLectureRequestInfo(lctreReqstNo);
		return lectureRequestInfo;
	}

	//상담 신청 승인 (상담 목록에 인서트, 업데이트)
	@Override
	public int modifyAppliedLectureRequest(String lctreReqstNo) {
		// 강의개설 신청 처리 하는 방법
		// 1. 개설강의 신청목록에서 승인처리
		int res = lectureReqMapper.updateAppliedLectureRequest(lctreReqstNo);
		
		// 2. 강의 테이블에 실제 강의 처리
		res += lectureReqMapper.insertLecture(lctreReqstNo);
		// 3. 개설강의 신청에 있는 시간표를 실제 강의시간표 테이블에 삽입
		res += lectureReqMapper.insertLectureTimeTable(lctreReqstNo);
		return res;
	}

	//상담 신청 반려 (상담 신청 정보 업데이트)
	@Override
	public int modifyRefusedLectureRequest(LectureRequestVO lctreReqstNo) {
		int res = lectureReqMapper.updateRefusedLectureRequest(lctreReqstNo);
		return res;
	}

}
