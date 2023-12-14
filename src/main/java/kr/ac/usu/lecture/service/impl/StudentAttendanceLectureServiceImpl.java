package kr.ac.usu.lecture.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.lecture.mapper.StudentAttendanceLectureMapper;
import kr.ac.usu.lecture.service.StudentAttendanceLectureService;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.lecture.vo.StudentAttendanceLectureVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentAttendanceLectureServiceImpl implements StudentAttendanceLectureService {

	@Inject
	private StudentAttendanceLectureMapper mapper;
	
	@Override
	public int retrieveRequestablePoint(String id, String preSemCd, String currSemCd) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("preSemCd", preSemCd);
		paramMap.put("currSemCd", currSemCd);
		return mapper.selectRequestablePoint(paramMap);
	}

	@Override
	public LectureVO retrieveRequestableLectureInfo(String lctreNo) {
		return mapper.selectRequestableLectureInfo(lctreNo);
	}

	@Override
	public void retrieveRequestableLectureList(PaginationInfo<LectureVO> paging) {
		int totalCount = mapper.selectRequestableLectureListTotalCount(paging);
		paging.setTotalRecord(totalCount);
		List<LectureVO> dataList = mapper.selectRequestableLectureList(paging);
		paging.setDataList(dataList);
	}

	@Override
	public synchronized ServiceResult createAttendanceLecture(StudentAttendanceLectureVO attendance) {
		LectureVO target = mapper.selectRequestableLectureInfo(attendance.getLctreNo());
		int alreadyDone = mapper.checkAlreadyDone(attendance);
		if(alreadyDone > 0 ) {
			return ServiceResult.ALREADYDONE;
		}
		int requestablePoint = attendance.getPnt();
		int targetPnt = Integer.parseInt(target.getCoursePnt());
		if(requestablePoint < targetPnt) {
			return ServiceResult.NOTENOUGHPOINT;
		}
		int currCnt = target.getCurrCnt();
		int limitCnt = target.getLctreNmpr();
		log.info("currCnt : {}, limitCnt : {}", currCnt, limitCnt);
		if(currCnt >= limitCnt) {
			return ServiceResult.LIMITPERSON;
		}
		mapper.insertAttendanceLecture(attendance);
		mapper.insertAttendanceLectureTimeTable(attendance);
		attendance.setLecture(target);
		return ServiceResult.OK;
	}

	@Override
	public ServiceResult removeAttendanceLecture(StudentAttendanceLectureVO attendance) {
		mapper.deleteAttendanceLecture(attendance);
		mapper.deleteAttendanceLectureTimeTable(attendance);
		attendance.setLecture(mapper.selectRequestableLectureInfo(attendance.getLctreNo()));
		return ServiceResult.OK;
	}

	@Override
	public List<LectureVO> retrieveRequestedLectureList(String id, String semCd) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		return mapper.selectRequestedLectureList(paramMap);
	}

	@Override
	public List<LectureVO> retrievePrepareLectureList(String id, String semCd) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		return mapper.selectPrepareLectureList(paramMap);
	}

	@Override
	public ServiceResult createPrepareLecture(StudentAttendanceLectureVO attendance) {
		attendance.setLecture(mapper.selectRequestableLectureInfo(attendance.getLctreNo()));
		return mapper.insertPrepareLecture(attendance) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult removePrepareLecture(StudentAttendanceLectureVO attendance) {
		return mapper.deletePrepareLecture(attendance) > 0? ServiceResult.OK : ServiceResult.FAIL;
	}




}
