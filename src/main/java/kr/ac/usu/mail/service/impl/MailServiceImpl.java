package kr.ac.usu.mail.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.ac.usu.mail.mapper.MailMapper;
import kr.ac.usu.mail.service.MailService;
import kr.ac.usu.mail.vo.EmailRoomVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 13.      김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

	@Inject
	private final MailMapper mailDAO;
	
	@Override
	public void retrieveMailList(PaginationInfo<EmailRoomVO> paging) {
		int totalRecord = mailDAO.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<EmailRoomVO> dataList = mailDAO.selectMailList(paging);
		paging.setDataList(dataList);
		
	}
	@Override
	public int createMail(EmailRoomVO email) {
		return mailDAO.createMail(email);
		
	}
	@Override
	public int countMail(EmailRoomVO email) {
		return mailDAO.countMail(email) ;
	}
	@Override
	public EmailRoomVO getMail(EmailRoomVO emailRoomVO) {
		EmailRoomVO result = mailDAO.getMail(emailRoomVO);
		if(result != null) {
			mailDAO.updateMail(emailRoomVO);
		}
		return result;
	}

	public int updateMail(EmailRoomVO email) {
		return mailDAO.updateMail(email);
	}
	
	@Override
	public int removeMail(EmailRoomVO email) {
		
		return mailDAO.removeMail(email);
	}

	@Override
	public void retrieveSendMail(PaginationInfo<EmailRoomVO> paging) {
		int totalRecord = mailDAO.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<EmailRoomVO> dataList = mailDAO.retrieveSendMail(paging);
		paging.setDataList(dataList);
		
	}
	@Override
	public EmailRoomVO getSendMail(EmailRoomVO emailRoomVO) {
		EmailRoomVO result = mailDAO.getSendMail(emailRoomVO);

		return result;
	}
	
	
}
