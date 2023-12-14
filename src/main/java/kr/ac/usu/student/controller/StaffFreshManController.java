package kr.ac.usu.student.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.student.mapper.StaffFreshManMapper;
import kr.ac.usu.student.service.StaffFreshManService;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 25.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 25.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Slf4j
@Controller
@RequestMapping("/staff/freshMan")
public class StaffFreshManController {
	
	@Inject
	private StaffFreshManService service;
	
	@Inject
	private StaffFreshManMapper mapper;
	
	@Value("#{appInfo.excelFolderForMac}")
	private Resource excelFolderResourceForMac;
	@Value("#{appInfo.excelFolderForNormal}")
	private Resource excelFolderResourceForWindow;
	
	private File excelFolder;
	
	@PostConstruct
	public void setExcelFolder() throws IOException {
		String osName = System.getProperty("os.name").toLowerCase();
		if(osName.contains("win")) {
			excelFolder = excelFolderResourceForWindow.getFile();
		}
		if(osName.contains("mac")) {
			excelFolder = excelFolderResourceForMac.getFile();
		}
		if(!excelFolder.exists()) {
			excelFolder.mkdirs();
		}
		log.info("생성된 폴더 : {}",excelFolder);
	}

	// 신입생 등록 UI 불러오는 메소드
	@GetMapping("/freshManUI")
	public String freshManUI(Model model) {
		
		List<ComCodeVO> enterenceSe = mapper.selectComCode("SEC012");
		List<ComCodeVO> nltySe = mapper.selectComCode("SEC002");
		List<ComCodeVO> genderSe = mapper.selectComCode("SEC001");
		List<ComCodeVO> bankSe = mapper.selectComCode("SEC004");
		List<CollegeVO> college = mapper.selectCollegeList();
		List<SubjectVO> subject = mapper.selectSubjectList();
		
		model.addAttribute("enterenceSe",enterenceSe);
		model.addAttribute("nltySe",nltySe);
		model.addAttribute("genderSe",genderSe);
		model.addAttribute("bankSe",bankSe);
		model.addAttribute("college",college);
		model.addAttribute("subject",subject);
			
		return "staff/student/freshManListUI";
	}
	
	// 신입생 리스트 불러오는 메소드
	@GetMapping("/freshManList")
	public String freshManList(Model model) {
		List<StudentVO> freshManList = service.retrieveFreshManList();
		
		model.addAttribute("freshManList",freshManList);
		
		return "jsonView";
	}
	
	// 엑셀 파일 업로드
	// @ResponseBody
	@PostMapping("ajax/excelUpload")
	public String excelUpload(MultipartFile excelFile, Model model) throws Exception {
		
		if(excelFile==null || excelFile.isEmpty()) {
			throw new RuntimeException("엑셀파일을 선택해주세요.");
		}
		
		//File destFile = new File("/Users/sunyoungmun/Desktop/FreshMan"+excelFile.getOriginalFilename());
		File destFile = new File(excelFolder, excelFile.getOriginalFilename());
	
		try {
			excelFile.transferTo(destFile);
		}catch(IllegalStateException | IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		try {
			service.excelUpload(destFile);
			model.addAttribute("result", true);
		} catch (Exception e) {
			model.addAttribute("result", false);
		}
		
		destFile.delete();
		
		return "jsonView";		
	}
	
	
	// 개별 등록
	@PostMapping("insertFreshMan")
	public String insertFreshMan(
		StudentVO freshMan
		, Model model
	) {	
		try {
			int res = service.createFreshMan(freshMan);
			model.addAttribute("success", true);
		}catch (Exception e) {
			model.addAttribute("success", false);
		}
		return "jsonView";
	}

	
	
}




































