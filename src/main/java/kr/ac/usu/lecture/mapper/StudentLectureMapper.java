package kr.ac.usu.lecture.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.ac.usu.lecture.vo.LectureVO;

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
 * 2023. 11. 7.   이재혁      최초작성
 * 2023. 11. 10.  김석호      필요 메소드 작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StudentLectureMapper {
	/**
	 * 학번 key : id
	 * 학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public List<LectureVO> selectStudentLectureList(Map<String, String> paramMap);
}
