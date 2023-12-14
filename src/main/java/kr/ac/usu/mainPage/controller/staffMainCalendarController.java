package kr.ac.usu.mainPage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.board.vo.BoardVO;
import kr.ac.usu.mainPage.mapper.StaffCalendarNoticeMapper;
import kr.ac.usu.mainPage.service.StaffCalendarNoticeService;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.schoolaffairsschedule.vo.CalendarVO;
import kr.ac.usu.schoolaffairsschedule.vo.SchoolAffairsScheduleVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/staff")
public class staffMainCalendarController {

    @Inject
    private StaffCalendarNoticeService calendarService;
    
    @Inject
    private StaffCalendarNoticeMapper calendarDAO;

	/*
	 * @GetMapping("ajax/staffMain")
	 * 
	 * @ResponseBody public List<BoardVO> listData() { PaginationInfo<BoardVO>
	 * paging = new PaginationInfo<>(10, 5);
	 * 
	 * 
	 * 
	 * 
	 * // 서비스에서 캘린더 데이터를 가져와 설정합니다.
	 * paging.setDataList(calendarService.retrieveNoticeCalendar());
	 * paging.setRenderer(new BootstrapPaginationRenderer());
	 * 
	 * return paging.getDataList(); }
	 * 
	 */
    
    @GetMapping("/ajax/staffMain")
    @ResponseBody
	public List<CalendarVO> selectStaffSchoolAffairsScheduleList(){  
    	return calendarService.selectStaffSchoolAffairsScheduleList();
   }
    
	// 일반공지 리스트
 	@GetMapping("/mainPage/commonBoardList")
 	public String commonBoardList(Model model) {

 		List<BoardVO> common = calendarService.retrieveCommonBoardList();
 		
 		model.addAttribute("common", common);
 		
 		return "jsonView";
 	}
 	
 	// 학사공지 리스트
 	@GetMapping("/mainPage/commonNoticeList")
 	public String commonNoticeList(Model model) {
 		List<BoardVO> notice = calendarService.retrieveNoticeBoardList();
 		
 		model.addAttribute("notice", notice);
 		
 		return "jsonView";
 	}
}
