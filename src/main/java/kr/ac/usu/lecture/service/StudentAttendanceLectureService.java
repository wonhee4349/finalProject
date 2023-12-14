package kr.ac.usu.lecture.service;

import java.util.List;
import java.util.Map;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.lecture.vo.StudentAttendanceLectureVO;
import kr.ac.usu.paging.vo.PaginationInfo;

/**
 * 수강신청 기능의 Service로직 인터페이스
 * @author 김석호
 * @since 2023. 11. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 11. 20.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public interface StudentAttendanceLectureService {
	
	public int retrieveRequestablePoint(String id, String preSemCd, String currSemCd);
	public LectureVO retrieveRequestableLectureInfo(String lctreNo);
	
	
	public void retrieveRequestableLectureList(PaginationInfo<LectureVO> paging);
	
	public List<LectureVO> retrieveRequestedLectureList(String id, String semCd);
	
	public List<LectureVO> retrievePrepareLectureList(String id, String semCd);
	
	
	
	/**
	 * @param attendance
	 * @return 기신청 : ALREADYDONE , 인원제한 : LIMITPERSON , 학점부족 : NOTENOUGHPOINT, 성공 : OK
	 */
	public ServiceResult createAttendanceLecture(StudentAttendanceLectureVO attendance);
	public ServiceResult removeAttendanceLecture(StudentAttendanceLectureVO attendance);

	
	public ServiceResult createPrepareLecture(StudentAttendanceLectureVO attendance);
	public ServiceResult removePrepareLecture(StudentAttendanceLectureVO attendance);
	
	
	
	
}
