package kr.ac.usu.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.user.vo.ProfessorVO;

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
 * 2023. 11. 7.      작성자명       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StaffCourseMapper {
	
public int selectTotalRecord(PaginationInfo<CourseVO> paging);
	

	public List<CourseVO> selectCourseList(PaginationInfo<CourseVO> paging);


}
