package kr.ac.usu.certificate.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.ac.usu.user.vo.StudentVO;

public interface StudentCertificateService {


	// 재학생 학적 정보
	public Map<String, String> retrieveStudentCertificate(String stdntNo);
	
}
