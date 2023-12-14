package kr.ac.usu.mail.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.mail.service.MailService;
import kr.ac.usu.mail.vo.EmailRoomVO;

@Controller
@RequestMapping("{actor}/mail")
public class MailWriteController {

    @Inject
    private final MailService service;

    public MailWriteController(MailService service) {
        this.service = service;
    }

    @GetMapping("mailWrite")
    public String mailWriteView(@PathVariable String actor, Model model) {
        // 액터 정보를 모델에 추가
        model.addAttribute("actor", actor);
        return "ajax/mail/mailWrite";
    }

    @GetMapping("mailDetail")
    public String mailDetailView(@ModelAttribute EmailRoomVO emailRoomVO, Authentication auth, Model model, @PathVariable String actor) {
    	emailRoomVO.setEmailRcver(auth.getName());
        EmailRoomVO email = service.getMail(emailRoomVO);
        model.addAttribute("email", email);
        return "ajax/mail/mailDetail";
    }
    
    @GetMapping("mailSendDetail")
    public String mailSendDetailView(@ModelAttribute EmailRoomVO emailRoomVO, Authentication auth, Model model, @PathVariable String actor) {
        emailRoomVO.setEmailSender(auth.getName());
        EmailRoomVO email = service.getSendMail(emailRoomVO);
        model.addAttribute("email", email);
        return "ajax/mail/mailSendDetail";
    }


    @PostMapping("saveMail")
    @ResponseBody
    public String saveMail(@RequestBody EmailRoomVO emailRoomVO) {
        int r = service.createMail(emailRoomVO);
        return (r > 0) ? "success" : "실패";
    }
}
