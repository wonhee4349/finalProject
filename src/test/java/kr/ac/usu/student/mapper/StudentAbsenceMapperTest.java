package kr.ac.usu.student.mapper;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import kr.ac.usu.AbstractRootContextTestFor;
import kr.ac.usu.student.vo.AbsenceSchoolVO;
import kr.ac.usu.user.mapper.LoginMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class StudentAbsenceMapperTest extends AbstractRootContextTestFor {

	@Inject
	private StudentAbsenceMapper mapper;
	
	@Test
	void testInsertAbsence() {
		AbsenceSchoolVO test = new AbsenceSchoolVO();
		String semCd = LoginMapper.getCurrentSemesterCode();
		String nextSemCd = LoginMapper.getNextRegularSemesterCode();
		log.info("다음학기 코드 : {}",nextSemCd);
		String yr = nextSemCd.substring(2);
		log.info("짜른거 : {}",yr);
		String stdntNo = "20220101";
		String abssklNo = String.format("R%s%s",yr,stdntNo );
		
		test.setAbssklNo(abssklNo);
		test.setStdntNo(stdntNo);
		test.setAbssklCn("테스트하려구요");
//		test.set
		
		
		int cnt = mapper.insertAbsence(test);
		
		assertEquals(1, cnt);
	}

}
