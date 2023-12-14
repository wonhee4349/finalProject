package kr.ac.usu.score.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import kr.ac.usu.AbstractRootContextTestFor;
import kr.ac.usu.score.vo.ScoreVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class StudentScoreMapperTest extends AbstractRootContextTestFor {

	@Inject
	private StudentScoreMapper mapper;
	
	@Test
	void test() {
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", "20233202");
		paramMap.put("semCd", "20231");
		
		List<ScoreVO> list = mapper.selectScoreList(paramMap);
		log.info("{}",list);
	}

}
