package kr.ac.usu.retrieve.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StaffRetrieveMapper {
	
	public List<Map<String, String>> selectSubjectGradePeopleCount();
	
	public int selectTotalCountAbsenceStudentThisYear();
	public List<Map<String, String>> selectCountAbsenceStudentThisYearEachSubject();
	
	
	
}
