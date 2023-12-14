package kr.ac.usu.lecture.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import kr.ac.usu.AbstractRootContextTestFor;
import kr.ac.usu.lecture.vo.LectureEvaulationItemVO;
import kr.ac.usu.lecture.vo.LectureEvaulationScoreVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.user.mapper.LoginMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class StudentLectureEvaulationMapperTest extends AbstractRootContextTestFor {

	@Inject
	private StudentLectureEvaulationMapper mapper;
	
	@Test
	void testSelectLectureEvaulationItems() {
		List<LectureEvaulationItemVO> list = mapper.selectLectureEvaulationItems();
		log.info("{}",list);
	}

	@Test
	void testSelectLectureEvaulationScores() {
		List<LectureEvaulationScoreVO> list = mapper.selectLectureEvaulationScores();
		log.info("{}",list);
	}

	@Test
	void testSelectLectrueListForEvaulation() {
		String semCd = LoginMapper.getCurrentRegularSemesterCode();
		String id = "20230404";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		List<LectureVO> list = mapper.selectLectrueListForEvaulation(paramMap);
		log.info("{}",list);
	}

}
