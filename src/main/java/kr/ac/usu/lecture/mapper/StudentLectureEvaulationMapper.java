package kr.ac.usu.lecture.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.ac.usu.lecture.vo.LectureEvaulationItemVO;
import kr.ac.usu.lecture.vo.LectureEvaulationScoreVO;
import kr.ac.usu.lecture.vo.LectureEvaulationVO;
import kr.ac.usu.lecture.vo.LectureVO;

/**
 * 학생 강의평가에 사용되는 Mapepr
 * @author 김석호
 * @since 2023. 11. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 11. 22.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StudentLectureEvaulationMapper {
	public List<LectureEvaulationItemVO> selectLectureEvaulationItems();
	public List<LectureEvaulationScoreVO> selectLectureEvaulationScores();
	
	/**
	 * 학번 key : id
	 * 학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public List<LectureVO> selectLectrueListForEvaulation(Map<String, String> paramMap);
	
	public int insertLectureEvaulation(List<LectureEvaulationVO> evaulationList);
}