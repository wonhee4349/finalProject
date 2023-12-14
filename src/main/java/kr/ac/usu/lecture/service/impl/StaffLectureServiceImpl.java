package kr.ac.usu.lecture.service.impl;
/**
 * 
 */
import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.lecture.mapper.StaffLectureMapper;
import kr.ac.usu.lecture.service.StaffLectureService;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.user.vo.ProfessorVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffLectureServiceImpl implements StaffLectureService {

private final StaffLectureMapper LectureList;
	
	@Override
	public void retrieveLectureList(PaginationInfo<LectureVO> paging) {
		
		int tatalRecord = LectureList.selectTotalRecord(paging);
		paging.setTotalRecord(tatalRecord);
		List<LectureVO> dataList = LectureList.selectLectureList(paging);
		paging.setDataList(dataList);
	}
	
	@Override
	public LectureVO retrieveLecture(String lctreNo) {
		LectureVO lecture = LectureList.selectLecture(lctreNo);
		if(lecture==null) {
			
		}
		return lecture;
	}
}
