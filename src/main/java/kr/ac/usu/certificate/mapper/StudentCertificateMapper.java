package kr.ac.usu.certificate.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.user.vo.StudentVO;

@Mapper
public interface StudentCertificateMapper {

	// 학생 학적 정보
		public Map<String, String> retrieveStudentCertificate(String stdntNo);

}
