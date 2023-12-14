package kr.ac.usu.course.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.course.mapper.StaffCourseMapper;
import kr.ac.usu.course.service.StaffCourseService;
import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import lombok.RequiredArgsConstructor;

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
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 7.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class StaffCourseServiceImpl implements StaffCourseService {
	
	private final StaffCourseMapper CourseListDAO;
	
	@Override
	public void retrieveCourseList(PaginationInfo<CourseVO> paging) {
		
		int tatalRecord = CourseListDAO.selectTotalRecord(paging);
		paging.setTotalRecord(tatalRecord);
		List<CourseVO> dataList = CourseListDAO.selectCourseList(paging);
		paging.setDataList(dataList);
	}

}
