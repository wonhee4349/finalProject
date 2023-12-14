package kr.ac.usu.user.controller;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.user.mapper.LoginMapper;
import kr.ac.usu.user.service.UserImageUploadService;
import kr.ac.usu.user.vo.wrapper.ProfessorVOWrapper;
import kr.ac.usu.user.vo.wrapper.StaffVOWrapper;
import kr.ac.usu.user.vo.wrapper.StudentVOWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 유저 이미지 업로드 컨트롤러
 * @author 김석호
 * @since 2023. 11. 29.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 11. 29.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
public class UserImageUploadController {

	@Inject
	private UserImageUploadService service;
	
	@Inject
	private LoginMapper loginMapper;
	
	
	/**
	 * @param id 업로드할 이미지의 대상
	 * @param imageFile
	 * @return
	 */
	@PostMapping("/staff/uploadUserImage")
	@ResponseBody
	public ServiceResult uploadUserImage(
			@RequestParam(required = true) String id
			, MultipartFile imageFile
		) {
		if(imageFile.isEmpty() || !imageFile.getContentType().toLowerCase().contains("image")) {
			return ServiceResult.FILETYPEERROR;
		}
		String role = null;
		UserDetails userInfo = loginMapper.loadUserByUsername(id);
		if(userInfo instanceof StudentVOWrapper) role = "STUDENT";
		if(userInfo instanceof ProfessorVOWrapper) role = "PROFESSOR";
		if(userInfo instanceof StaffVOWrapper) role = "STAFF";
		try {
			service.createUserImage(id, role, imageFile);
			return ServiceResult.OK;
		} catch (Exception e) {
			log.info("{}",e);
			return ServiceResult.FAIL;
		}
	}
	
}
