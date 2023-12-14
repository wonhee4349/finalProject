package kr.ac.usu.staff.service.impl;

import java.io.IOException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.usu.staff.mapper.StaffMypageMapper;
import kr.ac.usu.staff.service.StaffMypageService;
import kr.ac.usu.user.service.UserImageUploadService;
import kr.ac.usu.user.vo.StaffVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Service
@RequiredArgsConstructor
public class StaffMypageServiceImpl implements StaffMypageService{
	
	private final UserImageUploadService imgService;
	
	private final StaffMypageMapper mapper;
	
	private final PasswordEncoder encoder;


	// 교직원 인적 정보
	@Override
	public StaffVO retrieveStaffInfo(String sklstfNo) {
		StaffVO staffInfo = mapper.selectStaffInfo(sklstfNo);
		return staffInfo;
	}

	// 내정보 수정
	@Override
	public int modifyMypage(StaffVO sklstfNo) throws IOException, RuntimeException {
		
		MultipartFile img = sklstfNo.getProfileImage();
		boolean isEmpty = img.isEmpty();
		
		if(!isEmpty && img.getContentType().toLowerCase().contains("image")) {
			imgService.createUserImage(sklstfNo.getSklstfNo(), "STAFF", sklstfNo.getProfileImage());;
		}
		
		int res = mapper.updateMypage(sklstfNo);
		return res;
	}

	// 비밀번호 일치 확인
	@Override
	public boolean checkPassword(String realPass, String userPass) {

		boolean matches = encoder.matches(realPass, userPass);
		
		log.info("결과 : {}", matches);
		return matches;
	}

	// 비밀번호 수정
	@Override
	public int modifyPass(String sklstfNo, String checkPass) {
		
		StaffVO staff = new StaffVO();
		staff.setSklstfNo(sklstfNo);

		String encoded = encoder.encode(checkPass);
		staff.setSklstfPassword(encoded);
		
		int res = mapper.updatePass(staff);
		return res;
	}
	
	

}
