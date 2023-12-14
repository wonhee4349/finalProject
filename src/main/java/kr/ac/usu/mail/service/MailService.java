package kr.ac.usu.mail.service;

import java.util.List;

import kr.ac.usu.mail.vo.EmailRoomVO;
import kr.ac.usu.paging.vo.PaginationInfo;

/**
 * 
 *
 *
 * @author 김원희 
 * @since 2023. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 13.     김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public interface MailService {
	public void retrieveMailList(PaginationInfo<EmailRoomVO> paging);
	
	
	/*
	 * @param email 작성할 메일 정보
     */
     public int createMail(EmailRoomVO email);
     
     /*
      * @param email 카운트 읽음여부
      */
     public int countMail(EmailRoomVO email);


     /**
      * 
      * @param email 상세페이지
      */
	public EmailRoomVO  getMail(EmailRoomVO emailRoomVO);
	public EmailRoomVO  getSendMail(EmailRoomVO emailRoomVO);
	
	public int updateMail(EmailRoomVO email);
	

	public int removeMail(EmailRoomVO email) ;

	/**
	 * 
	 * @param email 보낸쪽지함
	 */
	public void retrieveSendMail(PaginationInfo<EmailRoomVO> paging);
}
