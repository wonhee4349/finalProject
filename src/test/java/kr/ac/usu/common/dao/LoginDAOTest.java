package kr.ac.usu.common.dao;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import kr.ac.usu.AbstractRootContextTestFor;
import kr.ac.usu.user.mapper.LoginMapper;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StaffVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class LoginDAOTest extends AbstractRootContextTestFor {

	@Inject
	private LoginMapper dao;
	
	@Test
	void testSelectStudent() {
		String sn = "20211007";
		StudentVO student =dao.selectStudent(sn);
		
		log.info("{}",student);
		
		
	}

	@Test
	void testSelectProfessor() {
		String prof = "00000106";
		
		ProfessorVO proff = dao.selectProfessor(prof);
		log.info("{}",proff);
	}

	@Test
	void testSelectStaff() {
		String staff = "11000001";
		StaffVO vo = dao.selectStaff(staff);
		
		log.info("{}",vo);
	}

}
