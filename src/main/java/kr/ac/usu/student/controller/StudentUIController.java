package kr.ac.usu.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 9.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Controller
public class StudentUIController {
	
	@GetMapping("/staff/studentListUi")
	public String studentView() {
		return "staff/student/studentListUI";
	}

}
