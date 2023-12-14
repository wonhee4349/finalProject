package kr.ac.usu.student.service.impl;

import java.io.IOException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.usu.common.exception.StudentNotFoundException;
import kr.ac.usu.student.mapper.StudentMypageMapper;
import kr.ac.usu.student.service.StudentMypageService;
import kr.ac.usu.user.service.UserImageUploadService;
import kr.ac.usu.user.vo.StudentVO;
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
public class StudentMypageServiceImpl implements StudentMypageService{
	
	private final UserImageUploadService imgService;
	
	private final StudentMypageMapper mapper;
	
	private final PasswordEncoder encoder;
	
	// 학생 인적 정보
	@Override
	public StudentVO retrieveStudentInfo(String stdntNo) {
		StudentVO studentInfo = mapper.selectStudentInfo(stdntNo);
		if(studentInfo==null) {
			throw new StudentNotFoundException();
		}
		return studentInfo;
	}

	// 학생 학적 정보
	@Override
	public StudentVO retrieveStudentRegisterStatus(String stdntNo) {
		StudentVO studentRegister = mapper.selectStudentRegisterStatus(stdntNo);
		if(studentRegister==null) {
			throw new StudentNotFoundException();
		}
		return studentRegister;
	}

	// 내정보 수정
	@Override
	public int modifyMypage(StudentVO stdntNo) throws IOException, RuntimeException {
		
		MultipartFile img = stdntNo.getProfileImage();
		boolean isEmpty = img.isEmpty();
		
		if(!isEmpty && img.getContentType().toLowerCase().contains("image")) {
			imgService.createUserImage(stdntNo.getStdntNo(), "STUDENT", stdntNo.getProfileImage());
		}
		
		int res = mapper.updateMypage(stdntNo);
		log.info("서비스단에서 이제 나갈꺼에요!");
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
	public int modifyPass(String stdntNo, String checkPass) {
		
		StudentVO student = new StudentVO();
		student.setStdntNo(stdntNo);
		
		String encoded = encoder.encode(checkPass);
		student.setStdntPassword(encoded);
		
		log.info("studentVO : {}", student);
		
		int res = mapper.updatePass(student);
		return res;
	}

}
