package kr.ac.usu.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ac.usu.mail.vo.EmailRoomVO;
import kr.ac.usu.paging.vo.PaginationInfo;

/**
 * 
 *
 *
 * @author 김원희
 * @since 2023. 11. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 13.      김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 *      </pre>
 */

@Mapper
public interface MailMapper {

	public int selectTotalRecord(PaginationInfo<EmailRoomVO> paging);

	public List<EmailRoomVO>retrieveSendMail(PaginationInfo<EmailRoomVO> paging);
	
	public List<EmailRoomVO> selectMailList(PaginationInfo<EmailRoomVO> paging);
	
	public  int createMail(EmailRoomVO email);
	
   public int countMail(EmailRoomVO email);
   
   public EmailRoomVO  getMail(EmailRoomVO emailRoomVO);
   public EmailRoomVO  getSendMail(EmailRoomVO emailRoomVO);
   
   public int updateMail(EmailRoomVO email);
   
   public int removeMail(EmailRoomVO email) ;

}
