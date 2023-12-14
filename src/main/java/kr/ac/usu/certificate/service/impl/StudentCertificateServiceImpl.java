package kr.ac.usu.certificate.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import kr.ac.usu.certificate.mapper.StudentCertificateMapper;
import kr.ac.usu.certificate.service.StudentCertificateService;
import kr.ac.usu.common.exception.StudentNotFoundException;
import kr.ac.usu.user.vo.StudentVO;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class StudentCertificateServiceImpl implements StudentCertificateService {

	private final StudentCertificateMapper certfiMapper;
	
	
	@Override
	public Map<String, String> retrieveStudentCertificate(String stdntNo) {
		Map<String, String> studentCertificate = certfiMapper.retrieveStudentCertificate(stdntNo);
		if(studentCertificate==null) {
			throw new StudentNotFoundException();
		}
		return studentCertificate;
	}

}
