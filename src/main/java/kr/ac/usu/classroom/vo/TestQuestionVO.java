package kr.ac.usu.classroom.vo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.lecture.vo.SubmitAnswerPaperVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 9.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      이재혁      최초작성
 * 2023. 11. 27.     김석호      테이블 구조 변경으로 인한 VO 수정
 * 2023. 11. 27.     김재성      제한시간 프로퍼티 작성
 * 2023. 11. 30.     김재성      시험출제 프로퍼티 작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = {"testSe","lctreNo"})
@ToString
public class TestQuestionVO {
	
	@NotBlank
	@Size(min = 2 , max = 2)
	private String testSe;
	private String testSeNm;
	@NotBlank
	@Size(min = 9 , max = 9)
	private String lctreNo;
	
	private String prfsorNo;
	private String semstrSeYear;
	private String semstrSeSemstr;
	
//	@NotBlank
	private String atchFileNo;
	
	private Integer questionCnt;
	private String questionable;
	
	private MultipartFile multipartFile;
	
	private Integer testQustTime;
	private String testTimeNm;
	
	public void setMultipartFile(MultipartFile multipartFile) {
		if(!multipartFile.isEmpty()) {
			this.multipartFile = multipartFile;
			this.atchFile = new AttatchingFileVO(multipartFile);
		}
	}
	
	private AttatchingFileVO atchFile;
	
	private LectureVO lecture;
	private TestVO test;
	private List<TestAnswerVO> testAnswer;
	private SubmitAnswerPaperVO submitAnswerPaper;

	public void saveTo(File saveFolder) throws IllegalStateException, IOException {
		if(atchFile != null) {
			atchFile.saveTo(saveFolder);
		}
	}
	
}
