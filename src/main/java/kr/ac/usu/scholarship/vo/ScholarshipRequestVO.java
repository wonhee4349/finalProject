package kr.ac.usu.scholarship.vo;

import kr.ac.usu.user.vo.StudentVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"stdntNo","schlshipNo"})
public class ScholarshipRequestVO {
	
	private int rnum;
	
	private String schlshipNo;
	private String stdntNo;
	private String schlshipReqstDate;
	private String atchFileNo;
	
	private String result;
	
	private ScholarshipVO scholarship;
	
	private StudentVO student;
}
