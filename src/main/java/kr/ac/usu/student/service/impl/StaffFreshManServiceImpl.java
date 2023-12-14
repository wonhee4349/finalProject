package kr.ac.usu.student.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.ac.usu.common.utils.ExcelRead;
import kr.ac.usu.common.utils.ExcelReadOption;
import kr.ac.usu.student.mapper.StaffFreshManMapper;
import kr.ac.usu.student.service.StaffFreshManService;
import kr.ac.usu.student.vo.FreshManVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Service
public class StaffFreshManServiceImpl implements StaffFreshManService{
	
	@Inject
	private StaffFreshManMapper mapper;
	
	@Inject
	PasswordEncoder encoder;
	
	// 신입생 리스트 
	@Override
	public List<StudentVO> retrieveFreshManList() {
		List<StudentVO> freshManList = mapper.selectFreshManList();
		return freshManList;
	}


	@Override
	public void excelUpload(File destFile) throws Exception {
		ExcelReadOption excelreadOption = new ExcelReadOption();
		excelreadOption.setFilePath(destFile.getAbsolutePath());
		excelreadOption.setOutputColumns(
				"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T"
		);
		excelreadOption.setStartRow(2);
		
		List<Map<String, String>> excelContent = ExcelRead.read(excelreadOption);
		log.info("hhhhhhhhhhhhhhhhhhhh : {}", excelContent);
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("excelContent", excelContent);
		
		List<FreshManVO> list = new ArrayList<>();
		for(Map<String, String> freshMan : excelContent) {
			FreshManVO studentVO = new FreshManVO();
		
			studentVO.setStdntNo(freshMan.get("A"));
			String originPass = freshMan.get("B");
			String encoded = encoder.encode(originPass);
			studentVO.setStdntPassword(encoded);
			studentVO.setStdntNm(freshMan.get("C"));
			studentVO.setStdntIhidnum1(freshMan.get("D"));
			studentVO.setStdntIhidnum2(freshMan.get("E"));
			studentVO.setStdntZip(freshMan.get("F"));
			studentVO.setStdntAdres1(freshMan.get("G"));
			studentVO.setStdntAdres2(freshMan.get("H"));
			studentVO.setStdntTelno(freshMan.get("I"));
			studentVO.setNltySe(freshMan.get("J"));
			studentVO.setSexdstnSe(freshMan.get("K"));
			studentVO.setBankSe(freshMan.get("L"));
			studentVO.setStdntAcnutno(freshMan.get("M"));
			studentVO.setSknrgSttusGrade(freshMan.get("N"));
			studentVO.setSknrgSttusMajor1(freshMan.get("O"));
			studentVO.setSknrgSttusEntsch(freshMan.get("P"));
			studentVO.setSknrgSttusEnterenceSe(freshMan.get("Q"));
			studentVO.setSemstrNo(freshMan.get("R"));
			studentVO.setSknrgHistDate(freshMan.get("S"));
			studentVO.setSknrgsSe(freshMan.get("T"));
			
			list.add(studentVO);
		}
		
		try {
			mapper.excelUpload(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
//		for(Map<String, String> article : excelContent) {
//			System.out.println(article.get("A"));
//			System.out.println(article.get("B"));
//			System.out.println(article.get("C"));
//			System.out.println(article.get("D"));
//			System.out.println(article.get("E"));
//			System.out.println(article.get("F"));
//			System.out.println(article.get("G"));
//			System.out.println(article.get("H"));
//			System.out.println(article.get("I"));
//			System.out.println(article.get("J"));
//			System.out.println(article.get("K"));
//			System.out.println(article.get("L"));
//			System.out.println(article.get("M"));
//			System.out.println(article.get("N"));
//			System.out.println(article.get("O"));
//			System.out.println(article.get("P"));
//			System.out.println(article.get("Q"));
//			System.out.println(article.get("R"));
//			System.out.println(article.get("S"));
//			System.out.println(article.get("T"));			
//		}
		
	}


	@Override
	public String excelDate(File destFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	// 개별 등록
	@Override
	public int createFreshMan(StudentVO freshMan) {
		int res = mapper.insertFreshMan(freshMan);
		return res;
	}





}
