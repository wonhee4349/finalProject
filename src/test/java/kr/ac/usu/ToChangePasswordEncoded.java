package kr.ac.usu;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.ac.usu.common.mapper.UtilForChange;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ToChangePasswordEncoded extends AbstractRootContextTestFor {
	
	@Inject
	private UtilForChange dao;
	
	@Inject
	private PasswordEncoder encoder;
	
	@Test
	void test2() {
		String origin = "00000001";
		String encoded = encoder.encode(origin);
		log.info("target : {}",encoded);
	}
	
	@Test
	void test() {
		List<StudentVO> list1 = dao.selectStudentList();
		log.info("{}",list1.size());
		
		int cnt = 0;
		for(StudentVO vo : list1) {
			String origin = vo.getStdntNo();
			String encoded = encoder.encode(origin);
			vo.setStdntPassword(encoded);
			cnt += dao.updateStudentPassword(vo);
		}
		
		log.info("변환 완료 갯수 : {}", cnt);
	}
	
	@Test
	void test3() {
		List<ProfessorVO> list = dao.selectProfessorList();
		
		int cnt = 0;
		for(ProfessorVO vo : list) {
			String origin = vo.getPrfsorNo();
			String encoded = encoder.encode(origin);
			vo.setPrfsorPassword(encoded);
			cnt += dao.updateProfessorPassword(vo);
		}
		log.info("변환 완료 갯수 : {}",cnt);
		
		
	}
	
	
	
	
}
