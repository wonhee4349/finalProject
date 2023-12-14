package kr.ac.usu.mail.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import kr.ac.usu.mail.service.MailService;
import kr.ac.usu.mail.vo.EmailRoomVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 *
 * @author 김원희
 * @since 2023. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 7.      김원희      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("{actor}/mail")
public class MailController {

    @Inject
    private MailService service;

    // 메일 UI 불러오기
    @GetMapping("mailList")
    public String mailView(
    		@PathVariable String actor
		) {
    	log.info("{}",actor);
    	
        return String.format("%s/mail/mailList", actor);
    }
    
    
    // 메일 UI 불러오기
    @GetMapping("mailSendList")
    public String mailSendView(
    		@PathVariable String actor
		) {
    	log.info("여기오 오는 것만 확인!");
        return String.format("%s/mail/mailSendList", actor);
    }
    
    


    // 메일 리스트 불러오기
    @GetMapping("ajax/mailList")
    public String listData(
    		Authentication auth,
            @ModelAttribute("simpleCondition") SearchVO simpleCondition,
            @RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
            Model model
    ) {
    	
    	log.info("체킁:{}",simpleCondition);
        // PaginationInfo 객체를 생성하고 초기화합니다.
        PaginationInfo<EmailRoomVO> paging = new PaginationInfo<>(10, 5);
        
        // 현재 페이지와 검색 조건을 설정합니다.
        paging.setSimpleCondition(simpleCondition);
        paging.setCurrentPage(currentPage);
        EmailRoomVO vo = new EmailRoomVO();
        vo.setEmailRcver(auth.getName());
        System.out.println("########################");
        System.out.println(vo.getEmailRcver());
        System.out.println("########################");
        paging.setDetailCondition(vo);
        // MailService를 사용하여 메일 리스트를 조회하고 PaginationInfo에 결과를 저장합니다.
        service.retrieveMailList(paging);

        // PaginationRenderer를 설정합니다. (BootstrapPaginationRenderer를 사용)
        paging.setRenderer(new BootstrapPaginationRenderer());

        // Model에 페이징 정보를 추가하여 뷰로 전달합니다.
        model.addAttribute("paging", paging);
        // jsonView를 리턴하여 비동기 요청에 대한 응답을 생성합니다.
		 return "jsonView";

    }
    
    

 // 보낸 메일 리스트 불러오기
    @GetMapping("ajax/mailSendList")
    public String sendListData(
            Authentication auth,
            @ModelAttribute("simpleCondition") SearchVO simpleCondition,
            @RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
            Model model
    ) {
    	
    	// sender 기준으로 가져오려면 
    	simpleCondition.setSearchType("emailSender");
    	simpleCondition.setSearchWord(auth.getName());
    	
        // PaginationInfo 객체를 생성하고 초기화합니다.
        PaginationInfo<EmailRoomVO> paging = new PaginationInfo<>(10, 5);

        // 현재 페이지와 검색 조건을 설정합니다.
        paging.setSimpleCondition(simpleCondition);
        paging.setCurrentPage(currentPage);
        EmailRoomVO vo = new EmailRoomVO();
        vo.setEmailSender(auth.getName());
        paging.setDetailCondition(vo);
        
        
        vo.setEmailSender(auth.getName()); // 수정된 부분
        System.out.println("########################");
        System.out.println(vo.getEmailSender());
        System.out.println("########################");
        paging.setDetailCondition(vo);

        // MailService를 사용하여 메일 리스트를 조회하고 PaginationInfo에 결과를 저장합니다.
        service.retrieveSendMail(paging);

        // PaginationRenderer를 설정합니다. (BootstrapPaginationRenderer를 사용)
        paging.setRenderer(new BootstrapPaginationRenderer());

        // Model에 페이징 정보를 추가하여 뷰로 전달합니다.
        model.addAttribute("paging", paging);
        // jsonView를 리턴하여 비동기 요청에 대한 응답을 생성합니다.
        return "jsonView";
    }

    
    
    

		
}
