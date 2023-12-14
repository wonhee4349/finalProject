package kr.ac.usu.user.mapper;

import java.time.YearMonth;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.ac.usu.user.vo.AccessLogVO;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StaffVO;
import kr.ac.usu.user.vo.StudentVO;
import kr.ac.usu.user.vo.wrapper.ProfessorVOWrapper;
import kr.ac.usu.user.vo.wrapper.StaffVOWrapper;
import kr.ac.usu.user.vo.wrapper.StudentVOWrapper;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일             수정자        수정내용
 * --------     --------    ----------------------
 * 2023. 11. 7.      이재혁       최초작성
 * 2023. 11. 8.      김석호       시큐리티용 로그인 메소드 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface LoginMapper extends UserDetailsService {
	@Override
	default UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		StudentVO student = selectStudent(username);
		ProfessorVO professor = selectProfessor(username);
		StaffVO staff = selectStaff(username);
		if(student != null) {
			return new StudentVOWrapper(student);
		}else if(professor != null) {
			return new ProfessorVOWrapper(professor);
		}else if(staff != null) {
			return new StaffVOWrapper(staff);
		}
		throw new UsernameNotFoundException("해당 사용자 찾을 수 없음");
	}
	
	public StudentVO selectStudent(@Param("id") String id);
	public ProfessorVO selectProfessor(@Param("id") String id);
	public StaffVO selectStaff(@Param("id") String id);
	
	public int insertAccessLog(AccessLogVO log);
	
	public static String getCurrentSemesterCode(){
		String semCd = null;
		
		YearMonth now = YearMonth.now();
		int currYear = now.getYear();
		int currMonth = now.getMonthValue();
		String year = String.valueOf(currYear);
		String Sem = null;
		
		switch(currMonth) {
			case 3 :
			case 4 :
			case 5 :
			case 6 :
				Sem = "1";
				break;
			case 9 :
			case 10 :
			case 11 :
			case 12 :
				Sem = "2";
				break;
				
			case 7 :
			case 8 :
				Sem = "3";
				break;
			case 1 :
			case 2 :
				Sem = "4";
				break;
		}
		
		semCd = String.format("%s%s",year,Sem);
	
		return semCd;
	}
	
	public static String getNextSemesterCodeIncludeVacationSemester() {
		String semCd = null;
		
		YearMonth now = YearMonth.now();
		int targetYear = now.getYear();
		int currMonth = now.getMonthValue();
		String Sem = null;
		switch(currMonth) {
			case 3 :
			case 4 :
			case 5 :
			case 6 :
				Sem = "3";
				break;
			case 9 :
			case 10 :
			case 11 :
			case 12 :
				Sem = "4";
				break;
				
			case 7 :
			case 8 :
				Sem = "2";
				break;
			case 1 :
			case 2 :
				targetYear++;
				Sem = "1";
				break;
		}
		String year = String.valueOf(targetYear);
		semCd = String.format("%s%s",year,Sem);
		return semCd;
	}
	
	public static String getNextRegularSemesterCode() {
		String semCd = null;
		
		YearMonth now = YearMonth.now();
		int targetYear = now.getYear();
		int currMonth = now.getMonthValue();
		String Sem = null;
		switch(currMonth) {
			case 1 :
			case 2 :
			case 3 :
			case 4 :
			case 5 :
			case 6 :
				Sem = "2";
				break;
			default :
				targetYear++;
				Sem = "1";
				break;
		}
		String year = String.valueOf(targetYear);
		semCd = String.format("%s%s",year,Sem);
		return semCd;
	}
	
	public static String getPrevRegularSemesterCode() {
		String semCd = null;
		YearMonth now = YearMonth.now();
		int targetYear = now.getYear();
		int currMonth = now.getMonthValue();
		String Sem = null;
		switch(currMonth) {
			case 1 :
			case 2 :
			case 3 :
			case 4 :
			case 5 :
			case 6 :
				targetYear--;
				Sem = "2";
				break;
			default :
				Sem = "1";
				break;
		}
		String year = String.valueOf(targetYear);
		semCd = String.format("%s%s",year,Sem);
		return semCd;
	}
	
	public static String getCurrentRegularSemesterCode() {
		String semCd = null;
		YearMonth now = YearMonth.now();
		int targetYear = now.getYear();
		int currMonth = now.getMonthValue();
		String Sem = null;
		switch(currMonth) {
		case 1 :
		case 2 :
		case 3 :
		case 4 :
		case 5 :
		case 6 :
			Sem = "1";
			break;
		default :
			Sem = "2";
			break;
		}
		String year = String.valueOf(targetYear);
		semCd = String.format("%s%s",year,Sem);
		return semCd;
		
	}
	
	public static String getPrintSemCd(String semCd, String seperator) {
		return String.format("%s년도%s%s학기",semCd.substring(0, 4),seperator ,semCd.substring(4));
	}
}
