package kr.ac.usu.professor.service.impl;

import java.io.IOException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.usu.professor.mapper.ProfessorMypageMapper;
import kr.ac.usu.user.service.UserImageUploadService;
import kr.ac.usu.user.vo.ProfessorVO;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 29.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 29.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Service
@RequiredArgsConstructor
public class ProfessorMypageServiceImpl implements ProfessorMypageService{
	
	private final UserImageUploadService imgService;
	
	private final ProfessorMypageMapper mapper;
	
	private final PasswordEncoder encoder;

	// 교수 인적 정보
	@Override
	public ProfessorVO retrieveProfessorInfo(String prfsorNo) {
		ProfessorVO professorInfo = mapper.selectProfessorInfo(prfsorNo);
		return professorInfo;
	}

	// 내정보 수정
	@Override
	public int modifyMypage(ProfessorVO prfsorNo) throws IOException, RuntimeException {
		
		MultipartFile img = prfsorNo.getProfileImage();
		boolean isEmpty = img.isEmpty();
		
		if(!isEmpty && img.getContentType().toLowerCase().contains("image")) {
			imgService.createUserImage(prfsorNo.getPrfsorNo(), "PROFESSOR", prfsorNo.getProfileImage());
		}
		
		int res = mapper.updateMypage(prfsorNo);
		return res;
	}

	// 비밀번호 일치 확인
	@Override
	public boolean checkPassword(String realPass, String userPass) {
		
		boolean matches = encoder.matches(realPass, userPass);
		return matches;
	}

	// 비밀번호 수정
	@Override
	public int modifyPass(String prfsorNo, String checkPass) {

		ProfessorVO professor = new ProfessorVO();
		professor.setPrfsorNo(prfsorNo);
		
		String encoded = encoder.encode(checkPass);
		professor.setPrfsorPassword(encoded);
		
		int res = mapper.updatePass(professor);
		
		return res;
	}

}
