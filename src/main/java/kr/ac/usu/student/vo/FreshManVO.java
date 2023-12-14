package kr.ac.usu.student.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 26.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 26.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Data
@EqualsAndHashCode(of="stdntNo")
@ToString
public class FreshManVO {
	
	private String stdntNo;
	
	private String stdntPassword;
	
	private String stdntNm;
	
	private String stdntIhidnum1;
	
	private String stdntIhidnum2;
	
	private String stdntZip;
	
	private String stdntAdres1;
	
	private String stdntAdres2;
	
	private String stdntTelno;
	
	private String nltySe;
	
	private String sexdstnSe;
	
	private String bankSe;
	
	private String stdntAcnutno;
	
	private String sknrgSttusGrade;
	
	private String sknrgSttusMajor1;
	
	private String sknrgSttusEntsch;
	
	private String sknrgSttusEnterenceSe;
	
	private String semstrNo;
	
	private String sknrgHistDate;
	
	private String sknrgsSe;

}
