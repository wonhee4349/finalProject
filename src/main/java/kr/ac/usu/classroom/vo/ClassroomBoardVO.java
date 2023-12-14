package kr.ac.usu.classroom.vo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.validate.grouphint.InsertGroup;
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
 * 2023. 11. 28.     김석호      과제 제출여부 확인용 프로퍼티 추가
 * 2023. 11. 30.     김재성      시험 관련 has 관계 프로퍼티 추가
 * 2023. 12. 01.     김석호      교수 관계 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = "crNo")
@ToString
public class ClassroomBoardVO {

	@NotBlank
	private String crNo;
	
	@NotBlank
	@Size(min = 2 , max = 2)
	private String crSe;
	private String crSeNm;
	
	@NotBlank(groups = InsertGroup.class)
	private String crTitle;
	
	@NotBlank(groups = InsertGroup.class)
	private String crCn;
	
	@NotBlank
	@Size(min = 8 , max = 8)
	private String crWrter;
	
	@NotBlank
	@Size(min = 10 , max = 10)
	private String crDate;
	
	
	private String atchFileNo;
	
	@NotBlank
	@Size(min = 9 , max = 9)
	private String lctreNo;
	
	private String prfsorNo;
	private String testSe;
	
	private LectureVO lecture;
	private ProfessorVO professor;
	private CourseVO course;
	private TestVO test;
	private List<LectureSubmitVO> lectureSubmit;
	
	
	// 과제용 제출여부
	private String submitted;
	
	private MultipartFile multipartFile;
	
	private AttatchingFileVO atchFile;
	
	public void setMultipartFile(MultipartFile multipartFile) {
		if(!multipartFile.isEmpty()) {
			this.multipartFile = multipartFile;
			this.atchFile = new AttatchingFileVO(multipartFile);
		}
	}
	
	public void saveTo(File saveFolder) throws IllegalStateException, IOException {
		if(atchFile != null) {
			atchFile.saveTo(saveFolder);
		}
	}
	
}
