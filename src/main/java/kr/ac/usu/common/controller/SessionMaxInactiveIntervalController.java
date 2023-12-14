package kr.ac.usu.common.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SessionMaxInactiveIntervalController {
	
	@PostMapping("/session")
	@ResponseBody
	public int setMaxIncativeIntervalSession(
			HttpSession session
		) {
		int timeSet = 1800;
		session.setMaxInactiveInterval(timeSet);
		return timeSet;
	}
}
