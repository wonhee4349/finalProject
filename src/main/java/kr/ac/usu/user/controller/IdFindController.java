package kr.ac.usu.user.controller;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.common.exception.SendMailErrorException;
import kr.ac.usu.user.MailSend;
import kr.ac.usu.user.service.IdFindService;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StaffVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 7.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
public class IdFindController {
    @Autowired
    private IdFindService idFindService;

    
    @PostMapping("/findId")
    @ResponseBody
    public String findId(@RequestBody Map<String, String> param) {
    	
    	String result = idFindService.retrieveFindId(param.get("name"), param.get("ssn1"), param.get("ssn2"));
    	
    	if(StringUtils.isNotBlank(result)) {
    		return result;
    	}else {
    		return "일치하는 아이디가 없습니다";
    	}
    }
    @PostMapping("/findPassword")
    public String findPassword(@RequestBody Map<String, String> param,Model model) {
    	
    	try {
    		boolean result = idFindService.retrieveFindPassWord(param);
    		if(result) {
    			// 여기서 메일로 보내줘야함
    			model.addAttribute("result", result);
    		}
		}catch (SendMailErrorException e) {
			model.addAttribute("message", "메일서버오류!<br/>관리자에게 문의해주세요!");
		}catch (Exception e) {
			log.info("{}",e);
			model.addAttribute("message", "일치하는 정보의 회원 없음!");
		}
    	return "jsonView";
    }
    
    
    
    
    
    
}

