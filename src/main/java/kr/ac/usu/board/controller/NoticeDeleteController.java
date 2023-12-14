package kr.ac.usu.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.board.service.NoticeListService;
import kr.ac.usu.board.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NoticeDeleteController {
	
	private final NoticeListService service;
	
	@Autowired
    public NoticeDeleteController(NoticeListService noticeService) {
        this.service = noticeService;
    }
	
	//{boNo:BD01231127001}  
	@PostMapping("/board/deleteNotice")
    @ResponseBody
    public String deleteNotice(@RequestBody BoardVO board) {
        try {
            // EmailRoomVO 객체 생성 및 emailNo 설정
//            BoardVO board = new BoardVO();
//            board.setBoNo(boNo);
        	log.info("board : " + board);
            // 삭제 처리
            int result = service.removeNotice(board);
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
	
	//{boNo:BD01231127001}  
	@PostMapping("/board/deleteCommonNotice")
    @ResponseBody
    public String deleteCommonNotice(@RequestBody BoardVO board) {
        try {
            // EmailRoomVO 객체 생성 및 emailNo 설정
//            BoardVO board = new BoardVO();
//            board.setBoNo(boNo);
        	log.info("board : " + board);
            // 삭제 처리
            int result = service.removeCommonNotice(board);
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
