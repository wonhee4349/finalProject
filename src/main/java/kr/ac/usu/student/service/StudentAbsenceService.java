package kr.ac.usu.student.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.usu.student.vo.AbsenceSchoolVO;

public interface StudentAbsenceService {
	public List<AbsenceSchoolVO> retrieveAbsenceList(@Param("stdntNo") String stdntNo);
	public AbsenceSchoolVO retrieveAbsence(AbsenceSchoolVO absence);
	public int createAbsence(AbsenceSchoolVO absence);
	public int modifyAbsence(AbsenceSchoolVO absence);
}
