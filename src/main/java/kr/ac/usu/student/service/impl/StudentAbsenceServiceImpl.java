package kr.ac.usu.student.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.ac.usu.student.mapper.StudentAbsenceMapper;
import kr.ac.usu.student.service.StudentAbsenceService;
import kr.ac.usu.student.vo.AbsenceSchoolVO;
import kr.ac.usu.user.mapper.LoginMapper;

@Service
public class StudentAbsenceServiceImpl implements StudentAbsenceService {

	@Inject
	private StudentAbsenceMapper mapper;
	
	@Override
	public List<AbsenceSchoolVO> retrieveAbsenceList(String stdntNo) {
		return mapper.selectAbsenceList(stdntNo);
	}

	@Override
	public AbsenceSchoolVO retrieveAbsence(AbsenceSchoolVO absence) {
		return mapper.selectAbsence(absence);
	}

	@Override
	public int createAbsence(AbsenceSchoolVO absence) {
		
		String nextSemCd = LoginMapper.getNextRegularSemesterCode();
		String yr = nextSemCd.substring(2);
		String abssklNo = String.format("R%s%s",yr,absence.getStdntNo() );
		absence.setAbssklNo(abssklNo);
		return mapper.insertAbsence(absence);
	}

	@Override
	public int modifyAbsence(AbsenceSchoolVO absence) {
		return mapper.updateAbsence(absence);
	}

}
