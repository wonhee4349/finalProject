package kr.ac.usu.user.mapper;
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
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 7.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StaffVO;
import kr.ac.usu.user.vo.StudentVO;

@Mapper
public interface IdFindMapper {

	/**
	 * 찾는사람 이름 key : name
	 * 찾는사람 ssn1 key : ssn1
	 * 찾는사람 ssn2 key : ssn2
	 * @param paramMap
	 * @return
	 */
	public String findIdFromNameAndSsn1AndSsn2(Map<String, String> paramMap);
	/**
	 * 찾는사람 id key : id
	 * 찾는사람 이름 key : name
	 * 찾는사람 ssn1 key : ssn1
	 * 찾는사람 ssn2 key : ssn2
	 * @param paramMap
	 * @return
	 */
	public int findPassWord(Map<String, String> paramMap);
	/**
	 * 아이디 key : id
	 * 비밀번호 key : pass
	 * @param paramMap
	 * @return
	 */
	public int updatePassWord(Map<String, String> paramMap);
}
