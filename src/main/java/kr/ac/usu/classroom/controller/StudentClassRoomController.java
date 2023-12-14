package kr.ac.usu.classroom.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.classroom.service.StudentClassroomService;
import kr.ac.usu.classroom.vo.ClassroomBoardVO;
import kr.ac.usu.classroom.vo.TestQuestionVO;
import kr.ac.usu.classroom.vo.TestVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.lecture.vo.LectureVideoVO;
import kr.ac.usu.user.mapper.LoginMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 학생 클래스룸 관련 기능 컨트롤러
 * @author 김석호
 * @since 2023. 11. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 11. 27.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/student/classroom")
@Slf4j
public class StudentClassRoomController {
	
	@Inject
	private StudentClassroomService service;
	
	@Value("#{appInfo.excelFolderForMac}")
	private Resource excelFolderResourceForMac;
	@Value("#{appInfo.excelFolderForTest}")
	private Resource excelFolderResourceForWindow;
	@Value("#{appInfo.saveFolederForApplication}")
	private Resource saveFolderResource;
	
	private Resource folderResource;
	private File excelFolder;
	private File saveFolder;
	
	@PostConstruct
	public void setExcelFolder() throws IOException{
		String osName = System.getProperty("os.name").toLowerCase();
		if(osName.contains("win")) {
			excelFolder = excelFolderResourceForWindow.getFile();
			saveFolder = saveFolderResource.getFile();
			folderResource = saveFolderResource;
		}
		if(osName.contains("mac")) {
			excelFolder = excelFolderResourceForMac.getFile();
			saveFolder = excelFolder;
			folderResource = excelFolderResourceForMac;
		}
		if(!excelFolder.exists()) {
			excelFolder.mkdirs();
		}
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		log.info("생성된 폴더 : {}",excelFolder);
	}
	
	
	
	@GetMapping
	public String classRoomListUI(
			Model model
			, Authentication auth
		) {
		String id = auth.getName();
		String semCd = LoginMapper.getCurrentRegularSemesterCode();
		List<LectureVO> list = service.retrieveClassRoomList(id, semCd);
		model.addAttribute("list", list);
		return "student/classroom/studentClassRoomUI";
	}
	
	@GetMapping("test/{lctreNo}")
	public String classRoomTestPage(
			Model model
			, Authentication auth
			, @PathVariable String lctreNo
		) {
		String id = auth.getName();
		String semCd = LoginMapper.getCurrentRegularSemesterCode();
		List<TestQuestionVO> list = service.retrieveTestQuestionList(id, semCd, lctreNo);
		model.addAttribute("list", list);
		return "ajax/student/classroom/classroomTestPage";
	}
	
	@GetMapping("assignment/{lctreNo}")
	public String classRoomAssignmentPage(
			Model model
			, Authentication auth
			, @PathVariable String lctreNo
		) {
		String id = auth.getName();
		String semCd = LoginMapper.getCurrentRegularSemesterCode();
		List<ClassroomBoardVO> list = service.retrieveHomeWorkList(id, semCd, lctreNo);
		model.addAttribute("list", list);
		return "ajax/student/classroom/classroomAssignmentPage";
	}
	@GetMapping("classroomBoard/{lctreNo}")
	public String classRoomClassroomBoardPage(
			Model model
			, Authentication auth
			, @PathVariable String lctreNo
		) {
		String id = auth.getName();
		String semCd = LoginMapper.getCurrentRegularSemesterCode();
		List<ClassroomBoardVO> list = service.retrieveClassRoomBoardList(id, semCd, lctreNo);
		model.addAttribute("list", list);
		return "ajax/student/classroom/classroomClassroomBoardPage";
	}
	@GetMapping("onlineLecture/{lctreNo}")
	public String classRoomOnlineLecturePage(
			Model model
			, Authentication auth
			, @PathVariable String lctreNo
		) {
		String id = auth.getName();
		String semCd = LoginMapper.getCurrentRegularSemesterCode();
		List<LectureVideoVO> list = service.retrieveOnlineLectureList(id, semCd, lctreNo);
		model.addAttribute("list", list);
		return "ajax/student/classroom/classroomClassroomBoardPage";
	}
	
	@GetMapping("test/{lctreNo}/{testSe}")
	public String classRoomGetTest(
			Model model
			, Authentication auth
			, @PathVariable String lctreNo
			, @PathVariable String testSe
		) throws Exception {
		TestVO test = new TestVO();
		test.setStdntNo(auth.getName());
		test.setLctreNo(lctreNo);
		test.setTestSe(testSe);
		try {
			TestQuestionVO data = service.retrieveTestQuestion(test);
			AttatchingFileVO atch = data.getAtchFile();
			List<String> list = service.getBase64(atch);
			model.addAttribute("test", data);
			model.addAttribute("list", list);
			model.addAttribute("testAlreadyDone", false);
			model.addAttribute("fileDoesNotExist", false);
		}catch (FileNotFoundException e) {
			log.info("fileNotFound를 찹아서 이쪽으로 들어옴");
			model.addAttribute("fileDoesNotExist", true);
		} catch (Exception e) {
			log.info("{}",e);
			model.addAttribute("testAlreadyDone", true);
		}
		return "iframe/studentTestPage";
	}
	
	@PostMapping("test/{lctreNo}/{testSe}")
	public String submitTest(
			Model model
			, Authentication auth
			, @PathVariable String lctreNo
			, @PathVariable String testSe
			, @RequestParam Map<String, String> param
			, @RequestParam int questionCnt
		) {
		log.info("post핸들러 파라미터맵 : {}",param);
		String id = auth.getName();
		service.createSubmitTest(param, id, lctreNo, testSe, questionCnt);
		return "jsonView";
	}

	
	@GetMapping("testInfo/{lctreNo}/{testSe}")
	@ResponseBody
	public int getTestResultInfo(
			Model model
			, Authentication auth
			, @PathVariable String lctreNo
			, @PathVariable String testSe
		) {
		return service.retrieveTestResultInfo(auth.getName(), lctreNo, testSe);
	}
	
	@PostMapping("assignment")
	public String submitAssignment(
			Model model
			, Authentication auth
			, String lctreNo
			, String crNo
			, MultipartFile assignmentFile
		) {
		
		log.info("넘어온 파라미터 lctreNo : {}",lctreNo);
		log.info("넘어온 파라미터 crNo : {}",crNo);
		log.info("넘어온 파라미터 assignmentFile : {}",assignmentFile.getOriginalFilename());
		
		try {
			service.createSubmitAssignment(auth.getName(), crNo, assignmentFile);
			model.addAttribute("result", true);
		} catch (Exception e) {
			log.info("오류 : {}",e);
			model.addAttribute("result", false);
		}
		
		return "jsonView";
	}
	
	@GetMapping("assignmentSubmitInfo/{crNo}")
	public String submittedAssignmentInfo(
			Model model
			, Authentication auth
			, @PathVariable String crNo
		) {
		String id = auth.getName();
		AttatchingFileVO info = service.retrieveSubmittedAssignment(id, crNo);
		
		model.addAttribute("info", info);
		
		return "jsonView";
	}
	
	@GetMapping("assignmentInfo/{crNo}")
	public String assignmentInfo(
			Model model
			, Authentication auth
			, @PathVariable String crNo
		) {
		String id = auth.getName();
		ClassroomBoardVO info = service.retrieveClassRoomBoard(id, crNo);
		model.addAttribute("info", info);
		return "jsonView";
	}
	
	@GetMapping("fileDownload/{atchFileNo}/{atchFileSn}")
	public ResponseEntity<Resource> classroomFileDownload(
			Authentication auth
			, @PathVariable String atchFileNo
			, @PathVariable Integer atchFileSn
		) throws IOException{
		
		if(!service.retrieveAuthorityFile(auth.getName(), atchFileNo)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"권한 없음");
		}
		
		AttatchingFileVO fileInfo = new AttatchingFileVO();
		fileInfo.setAtchFileNo(atchFileNo);
		fileInfo.setAtchFileSn(atchFileSn);
		AttatchingFileVO atch = service.retrieveOneAttatchingFile(fileInfo);
		
		Resource requestedResource = folderResource.createRelative(atch.getAtchFileStreNm());
		
		if(!requestedResource.exists()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 파일이 없음.");
		}
		
		ContentDisposition disposition = ContentDisposition.attachment()
				.filename(atch.getAtchFileNm(), Charset.defaultCharset())
				.build();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(disposition);
//		headers.setContentLength(boFile.contentLength());
		headers.setContentLength(atch.getAtchFileMg());
		// 보통 스트림카피로 전달되는 컨텐츠의 타입을 이렇게 세팅한다.
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		return ResponseEntity.ok()
				.headers(headers)
				.body(requestedResource);
	}
	
}
