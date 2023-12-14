package kr.ac.usu.user.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.club.vo.ClubVO;
import kr.ac.usu.scholarship.vo.ScholarshipVO;
import kr.ac.usu.score.vo.ScoreVO;
import kr.ac.usu.student.vo.SchoolRegisterHistoryVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.tuition.vo.TuitionVO;
import kr.ac.usu.validate.grouphint.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 김석호
 * @since 2023. 11. 8.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일         수정자        수정내용
 * --------     --------    ----------------------
 * 2023. 11. 8.  문선영       최초작성
 * 2023. 11. 8.  김석호       최초작성 후 바톤터치
 * 2023. 11. 9.  김석호       시큐리티 롤 추가
 * 2023. 12. 6.  김재성       강의코드 프로퍼티 추가 및 성적등록 확인용 ScoreVO 프로퍼티 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = "stdntNo")
@ToString(exclude = {"stdntIhidnum2","stdntPassword","stdntAdres1","stdntAdres2","stdntAcnutno"})
public class StudentVO implements Serializable{
	
	private int rnum;
	
	@NotBlank
	@Size(min = 8 , max = 8)
	private String stdntNo;
	@NotBlank
	@JsonIgnore
	private transient String stdntPassword;
	
	@NotBlank(groups = InsertGroup.class)
	private String stdntNm;
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 6, max = 6, groups = InsertGroup.class)
	private String stdntIhidnum1;
	@NotBlank(groups = InsertGroup.class)
	@JsonIgnore
	private transient String stdntIhidnum2;
	@NotBlank(groups = InsertGroup.class)
	private String stdntZip;
	@NotBlank(groups = InsertGroup.class)
	private String stdntAdres1;
	private String stdntAdres2;
	@NotBlank(groups = InsertGroup.class)
	private String stdntTelno;
	@NotBlank(groups = InsertGroup.class)
	private String nltySe;
	@NotBlank(groups = InsertGroup.class)
	private String sexdstnSe;
	@NotBlank(groups = InsertGroup.class)
	private String bankSe;
	@NotBlank(groups = InsertGroup.class)
	private String stdntAcnutno;
	
	private String atchFileNo;
	
	private String subjctNm;
	
	private MultipartFile profileImage;
	
	private AttatchingFileVO profileImg;
	
	public void setAtchFileNo(String atchFileNo) {
		this.atchFileNo = atchFileNo;
	}
	
	private String bankNo;	// 내정보 수정을 위한 은행 코드
	
	// Relation
	private SubjectVO subject;
	private List<ScholarshipVO> scholarship;
	private List<TuitionVO> tuition;
	private List<ClubVO> club;
	
	
	private String stdntPosition;
	
	
	@NotBlank(groups = InsertGroup.class)
	private String sknrgSttusGrade;
	@NotBlank(groups = InsertGroup.class)
	private String sknrgSttusMajor1;
	private String sknrgSttusMajor2;
	private String sknrgSttusMinor;
	
	private String sknrgSttusMajor1Nm;
	private String sknrgSttusMajor2Nm;
	private String sknrgSttusMinorNm;
	
	@NotBlank(groups = InsertGroup.class)
	private String sknrgSttusEntsch;
	private String sknrgSttusGrdtn;
	@NotBlank(groups = InsertGroup.class)
	private String sknrgSttusEnterenceSe;
	
	private String sknrgsSe;	// 학적상태 구분코드
	
	private SchoolRegisterHistoryVO schoolRegisterHistory;
	
	private String lctreNo;
	
	// 성적등록 확인용
	private List<ScoreVO> scoreVO;
	
	
	
	
	public String getUserRole() {
		return "ROLE_STUDENT";
	}
}