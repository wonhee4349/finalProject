package kr.ac.usu.schoolaffairsschedule.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.schoolaffairsschedule.mapper.StaffSchoolAffairsScheduleMapper;
import kr.ac.usu.schoolaffairsschedule.service.StaffSchoolAffairsScheduleService;
import kr.ac.usu.schoolaffairsschedule.vo.SchoolAffairsScheduleVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 22.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StaffSchoolAffairsScheduleServiceImpl implements StaffSchoolAffairsScheduleService {

	private final StaffSchoolAffairsScheduleMapper SasDAO;
	
	@Override
	public void retrieveStaffSchoolAffairsScheduleList(PaginationInfo<SchoolAffairsScheduleVO> paging) {
		
		int tatalRecord = SasDAO.selectTotalRecord(paging);
		paging.setTotalRecord(tatalRecord);
		List<SchoolAffairsScheduleVO> dataList = SasDAO.selectStaffSchoolAffairsScheduleList(paging);
		paging.setDataList(dataList);
	}
	
	@Override
	public int createSchoolAffairsSchedule(SchoolAffairsScheduleVO schoolaffairsschedule) {
		String[] dateText = schoolaffairsschedule.getSchafsBeginDate().split("-");
		String resText = "";
		int dateInt = Integer.parseInt(dateText[1]);
		if(dateInt >= 1 && dateInt <= 7) {
			resText = dateText[0] + "1";
		}else{
			resText = dateText[0] + "2";
		}
		schoolaffairsschedule.setSemstrSe(resText);

		int res = SasDAO.insertSchoolAffairsSchedule(schoolaffairsschedule);	
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}", res);
		return res;
 }
	@Override
	public int removeSchoolAffairsSchedule(SchoolAffairsScheduleVO schoolaffairsschedule) {
		
		return SasDAO.removeSchoolAffairsSchedule(schoolaffairsschedule);
	}
	
}
