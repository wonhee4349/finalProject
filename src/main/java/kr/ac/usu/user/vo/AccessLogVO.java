package kr.ac.usu.user.vo;

import lombok.Data;

/**
 * 로그인 이력용 VO
 * 테이블 네임 ACCESS_LOG
 * @author 김석호
 * @since 2023. 11. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일         수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 13.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
public class AccessLogVO {
	private String id;
	private String acsLgDate;
	private String acsIp;
	private String acsSe;
	
}
