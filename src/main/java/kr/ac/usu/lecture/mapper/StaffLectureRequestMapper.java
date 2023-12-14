package kr.ac.usu.lecture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface StaffLectureRequestMapper {

		// 페이징
		public int selectTotalRecord(PaginationInfo<LectureRequestVO> paging);
		
		// 상담 신청 리스트
		public List<LectureRequestVO> selectLectureRequestList(PaginationInfo<LectureRequestVO> paging);

		// 상담 신청 정보
		public LectureRequestVO selectLectureRequestInfo(@Param("lctreReqstNo") String lctreReqstNo);
		
		// 상담 신청 승인 (상담 목록에 인서트)
		public int insertLecture(@Param("lctreReqstNo") String lctreReqstNo);
		
		public int insertLectureTimeTable(@Param("lctreReqstNo") String lctreReqstNo);
		
		
		// 상담 신청 승인 (상담 신청 정보 업데이트)
		public int updateAppliedLectureRequest(@Param("lctreReqstNo") String lctreReqstNo);
		
		// 상담 신청 반려 (상담 신청 정보 업데이트)
		public int updateRefusedLectureRequest(LectureRequestVO lctreReqstNo);
}
