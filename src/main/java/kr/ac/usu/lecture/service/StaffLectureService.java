package kr.ac.usu.lecture.service;

import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.user.vo.ProfessorVO;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 13.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public interface StaffLectureService {

	public void retrieveLectureList(PaginationInfo<LectureVO> paging);
	
	public LectureVO retrieveLecture(String lctreNo);
}
