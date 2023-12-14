package kr.ac.usu.schoolaffairsschedule.service;



import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.schoolaffairsschedule.vo.SchoolAffairsScheduleVO;


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
public interface StaffSchoolAffairsScheduleService {
	
	public void retrieveStaffSchoolAffairsScheduleList(PaginationInfo<SchoolAffairsScheduleVO> paging);
	
	public int createSchoolAffairsSchedule(SchoolAffairsScheduleVO schoolaffairsschedule);
	
	public int removeSchoolAffairsSchedule(SchoolAffairsScheduleVO schoolaffairsschedule) ;
}
