package kr.ac.usu.lecture.service;
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
 * 수정일           수정자      수정내용
 * --------     --------    ----------------------
 * 2023. 11. 07.  이재혁      최초작성
 * 2023. 11. 10.  김석호      필요메소드 작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

import java.util.List;

import kr.ac.usu.lecture.vo.LectureVO;

public interface StudentLectureListService {
	public List<LectureVO> retrieveStudentLectureList(String id,String semCd);
}
