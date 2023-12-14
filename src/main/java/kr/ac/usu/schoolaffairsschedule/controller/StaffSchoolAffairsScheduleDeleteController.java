package kr.ac.usu.schoolaffairsschedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.board.controller.NoticeDeleteController;
import kr.ac.usu.board.service.NoticeListService;
import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.schoolaffairsschedule.service.StaffSchoolAffairsScheduleService;
import kr.ac.usu.schoolaffairsschedule.vo.SchoolAffairsScheduleVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 28.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

@Slf4j
@Controller
public class StaffSchoolAffairsScheduleDeleteController {

private final StaffSchoolAffairsScheduleService service;
	
	@Autowired
    public StaffSchoolAffairsScheduleDeleteController(StaffSchoolAffairsScheduleService schoolAffairsScheduleService) {
        this.service = schoolAffairsScheduleService;
    }
	
	//{boNo:BD01231127001}  
	@PostMapping("/schoolaffairsschedule/deleteSchoolAffairsSchedule")
    @ResponseBody
    public String deleteSchoolAffairsSchedule(@RequestBody SchoolAffairsScheduleVO schoolaffairsschedule) {
        try {
            // EmailRoomVO 객체 생성 및 emailNo 설정
//            BoardVO board = new BoardVO();
//            board.setBoNo(boNo);
        	log.info("schoolaffairsschedule : " + schoolaffairsschedule);
            // 삭제 처리
            int result = service.removeSchoolAffairsSchedule(schoolaffairsschedule);
            // 삭제 성공 여부에 따라 응답 처리
            if (result > 0) {
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            // 에러 발생 시 예외 처리
            e.printStackTrace();
            return "error";
        }
    }
	
}
