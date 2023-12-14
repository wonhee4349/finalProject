package kr.ac.usu.lecture.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.ac.usu.lecture.mapper.StudentLectureMapper;
import kr.ac.usu.lecture.service.StudentLectureListService;
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
 * 수정일               수정자        수정내용
 * -------------     --------    ----------------------
 * 2023. 11. 07.      이재혁        최초작성
 * 2023. 11. 10.      김석호        필요메소드 작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Service
public class StudentLectureListServiceImpl implements StudentLectureListService{

	@Inject
	private StudentLectureMapper dao;

	@Override
	public List<LectureVO> retrieveStudentLectureList(String id,String semCd) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		return dao.selectStudentLectureList(paramMap);
	}
	

}
