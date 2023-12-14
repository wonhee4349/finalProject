package kr.ac.usu.scholarship.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.user.vo.StudentVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = {"stdntNo", "schlshipNo"})
@ToString
public class ScholarshipStudentVO implements Serializable{
	
	private int rnum;
	
	@NotBlank
	@Size(min = 8 , max = 8)
	private String stdntNo;
	
	@NotBlank
	@Size(min = 10 , max = 10)
	private String schlshipNo;

	
	
	
	
	
	private StudentVO student;	
	private ScholarshipVO scholarship;
	
}
