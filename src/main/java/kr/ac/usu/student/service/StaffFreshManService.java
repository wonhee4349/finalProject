package kr.ac.usu.student.service;

import java.io.File;
import java.util.List;

import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.StudentVO;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 27.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 27.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
public interface StaffFreshManService {
	
	// 신입생 리스트 
	public List<StudentVO> retrieveFreshManList();

	// 엑셀 업로드
	public void excelUpload(File destFile) throws Exception;
	
	// 엑셀 cell 1개 데이터 가져오기
	public String excelDate(File destFile) throws Exception;
	
	// 개별 등록
	public int createFreshMan(StudentVO freshMan);

}
