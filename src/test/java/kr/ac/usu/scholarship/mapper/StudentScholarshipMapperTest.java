package kr.ac.usu.scholarship.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import kr.ac.usu.AbstractRootContextTestFor;
import kr.ac.usu.scholarship.vo.ScholarshipStudentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class StudentScholarshipMapperTest extends AbstractRootContextTestFor {

	@Inject
	private StudentScholarshipMapper mapper;
	
	
	@Test
	void testSelectStudentRequestScholarshipList() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectStudentScholarList() {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("id", "20233524");
//		paramMap.put("semCd", "20232");
		List<ScholarshipStudentVO> list = mapper.selectStudentScholarList(paramMap);
		log.info("{}",list);
	}

}
