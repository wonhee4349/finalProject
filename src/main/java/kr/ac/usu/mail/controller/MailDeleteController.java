package kr.ac.usu.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.mail.service.MailService;
import kr.ac.usu.mail.vo.EmailRoomVO;

@Controller
@RequestMapping("{actor}/mail")
public class MailDeleteController {

    private final MailService mailService;

    @Autowired
    public MailDeleteController(MailService mailService) {
        this.mailService = mailService;
    }

    // 메일 삭제
    @PostMapping("/deleteMail")
    @ResponseBody
    public String deleteMail(@RequestParam("emailNo") String emailNo, Authentication authentication) {
        try {
            // EmailRoomVO 객체 생성 및 emailNo 설정
            EmailRoomVO email = new EmailRoomVO();
            email.setEmailNo(emailNo);

            // 삭제 처리
            int result = mailService.removeMail(email);

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
