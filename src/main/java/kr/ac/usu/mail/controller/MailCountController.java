package kr.ac.usu.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.usu.mail.service.MailService;
import kr.ac.usu.mail.vo.EmailRoomVO;

@Controller
@RequestMapping("/mail")
public class MailCountController {

    private final MailService mailService;

    @Autowired
    public MailCountController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("count")
    public String countMail(Model model, Authentication auth) {
       String authId = auth.getName();
    	// 이메일 수를 가져오는 비즈니스 로직 호출
       EmailRoomVO email = new EmailRoomVO();
       email.setEmailRcver(authId);
        int emailCount = mailService.countMail(email);
        System.out.println(emailCount);

        // 모델에 이메일 수를 추가하여 뷰에 전달
        model.addAttribute("emailCount", emailCount);

        // 뷰의 이름 반환 (이 예제에서는 뷰 이름이 "countMail.jsp"라고 가정)
        return "jsonView";
    }
}
