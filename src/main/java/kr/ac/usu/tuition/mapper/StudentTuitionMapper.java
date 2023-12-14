package kr.ac.usu.tuition.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.ac.usu.tuition.vo.TuitionVO;

/**
 * 학생 등록금 관련 기능 맵퍼
 * @author 김석호
 * @since 2023. 11. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 11. 24.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StudentTuitionMapper {

	/**
	 * 학번 key : id
	 * 제외될 현재 학기 key : semCd
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, String>> selectSemesterListForStudentTuition(Map<String, String> paramMap);
	
	/**
	 * 특정 학기에 대한 등록금 내역 조회
	 * @param searchCondition
	 * @return
	 */
	public TuitionVO selectTuitionInformationForStudentWithSemesterCode(TuitionVO searchCondition);
}
