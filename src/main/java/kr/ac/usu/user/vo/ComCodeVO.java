package kr.ac.usu.user.vo;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * COM_CODE 테이블에서 공통코드를 가져와야할 때 사용하는 VO객체
 * @author 김석호
 * @since 2023. 11. 17.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일         수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 17.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"comCode","comCodeGrp"})
public class ComCodeVO implements Serializable {
	
	private String comCode;
	private String comCodeGrp;
	private String comCodeNm;
	private String comCodeDesc;
	private String useYn;
	private Integer comCodeSeq;
	private String upperCodeGrp;
	private String upperComCode;
}
