package kr.ac.usu.lecture.service;

import java.util.List;

import kr.ac.usu.lecture.vo.LectureRequestVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.paging.vo.PaginationInfo;


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
public interface StaffLectureRequestService {

	// 상담 신청 리스트
		public void retrieveLectureRequestList(PaginationInfo<LectureRequestVO> paging);

		// 상담 신청 정보
		public LectureRequestVO retrieveLectureRequestInfo(String lctreReqstNo);
		
		// 상담 신청 승인 (상담 목록에 인서트, 업데이트)
		public int modifyAppliedLectureRequest(String lctreReqstNo);
		
		// 상담 신청 반려 (상담 신청 정보 업데이트)
		public int modifyRefusedLectureRequest(LectureRequestVO lctreReqstNo);
}
