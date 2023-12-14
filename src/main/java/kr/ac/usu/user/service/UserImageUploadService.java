package kr.ac.usu.user.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * 유저 이미지 업로드 서비스 인터페이스
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
public interface UserImageUploadService {
	
	public void createUserImage(String id, String role, MultipartFile imageFile) throws IOException, RuntimeException;

}
