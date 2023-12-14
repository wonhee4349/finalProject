package kr.ac.usu.user.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.ac.usu.common.exception.SendMailErrorException;
import kr.ac.usu.user.MailSend;
import kr.ac.usu.user.mapper.IdFindMapper;
import kr.ac.usu.user.mapper.LoginMapper;
import kr.ac.usu.user.service.IdFindService;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StaffVO;
import kr.ac.usu.user.vo.StudentVO;


/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 07.      이재혁       최초작성
 * 2023. 12. 10.      김석호       초기화 암호 난수화 추가
 * Copyright (c) 2023 by DDIT All right reserved
 *      </pre>
 */
@Service
public class IdFindServiceImpl implements IdFindService {
    @Autowired
    private IdFindMapper idFindMapper;
    @Inject
    private PasswordEncoder encoder;
    
    @Inject
    private MailSend mailsend;

	@Override
	public String retrieveFindId(String name, String ssn1, String ssn2) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("name", name);
		paramMap.put("ssn1", ssn1);
		paramMap.put("ssn2", ssn2);
		return idFindMapper.findIdFromNameAndSsn1AndSsn2(paramMap);
	}

	@Override
	public boolean retrieveFindPassWord(Map<String, String> paramMap) {
		
		String id = paramMap.get("id");
		
		try {
			int res = idFindMapper.findPassWord(paramMap);
			String generatedPass = generatePassword(8);
			
			if(res > 0) {
				
				String pass = encoder.encode(generatedPass);
				
				paramMap.put("pass", pass);
				if(id.startsWith("0")) {
					paramMap.put("TABLE", "PROFESSOR");
					paramMap.put("COLS1", "PRFSOR_PASSWORD");
					paramMap.put("COLS2", "PRFSOR_NO");
				}else if(id.startsWith("1")) {
					paramMap.put("TABLE", "STAFF");
					paramMap.put("COLS1", "SKLSTF_PASSWORD");
					paramMap.put("COLS2", "SKLSTF_NO");
				}else {
					paramMap.put("TABLE", "STUDENT");
					paramMap.put("COLS1", "STDNT_PASSWORD");
					paramMap.put("COLS2", "STDNT_NO");
				}
				idFindMapper.updatePassWord(paramMap);
				
			}else {
				throw new RuntimeException("입력한 정보로 찾는 비밀번호 없음");
			}
				String contentFormat = "비밀번호가 초기화 되었습니다<br/>초기화된 비밀번호 : %s";
				mailsend.mailSend(paramMap.get("email"), String.format(contentFormat, generatedPass));
		} catch (IOException e) {
			e.printStackTrace();
			throw new SendMailErrorException(e);
		}
		return true;
	}

	private String generatePassword(int passwordLength) {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		
		Random random = new Random();

		String generatedString = random.ints(leftLimit,rightLimit + 1)
		  .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		  .limit(passwordLength)
		  .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		  .toString();
		
		String genKey = generatedString.toLowerCase();
		
		return genKey;
	}
	
}

