package kr.ac.usu.lecture.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import kr.ac.usu.AbstractRootContextTestFor;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class StudentAttendanceLectureMapperTest extends AbstractRootContextTestFor {

	@Inject
	private StudentAttendanceLectureMapper mapper;
	
	@Test
	void testSelectRequestableLectureInfo() {
		PaginationInfo<LectureVO> paging = new PaginationInfo<LectureVO>();
		LectureVO lectureVO = new LectureVO();
		lectureVO.setSemstrSe("20232");
		paging.setDetailCondition(lectureVO);
		List<LectureVO> list = mapper.selectRequestableLectureList(paging);
		
		log.info("불러온 값의 사이즈 : {}", list.size());
		
	}

	@Test
	void testSelectRequestableLectureListTotalCount() {
		fail("Not yet implemented");
	}

}
