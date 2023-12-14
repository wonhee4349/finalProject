package kr.ac.usu.student.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.student.vo.AbsenceSchoolVO;

/**
 * 학생의 휴학신청 관련 Mapper
 * @author 김석호
 * @since 2023. 11. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일         수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 13.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StudentAbsenceMapper {
	public List<AbsenceSchoolVO> selectAbsenceList(@Param("stdntNo") String stdntNo);
	public AbsenceSchoolVO selectAbsence(AbsenceSchoolVO absence);
	public int insertAbsence(AbsenceSchoolVO absence);
	public int updateAbsence(AbsenceSchoolVO absence);
}
