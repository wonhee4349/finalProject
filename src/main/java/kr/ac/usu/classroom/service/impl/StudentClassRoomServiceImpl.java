package kr.ac.usu.classroom.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.classroom.mapper.StudentClassRoomMapper;
import kr.ac.usu.classroom.service.StudentClassroomService;
import kr.ac.usu.classroom.vo.ClassroomBoardVO;
import kr.ac.usu.classroom.vo.TestAnswerVO;
import kr.ac.usu.classroom.vo.TestQuestionVO;
import kr.ac.usu.classroom.vo.TestVO;
import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.lecture.vo.LectureVideoVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 학생 클래스룸 관련 기능 서비스 구현체
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
@Slf4j
@Service
public class StudentClassRoomServiceImpl implements StudentClassroomService {

	@Inject
	private StudentClassRoomMapper mapper;
	
	@Value("#{appInfo.excelFolderForMac}")
	private Resource excelFolderResourceForMac;
	@Value("#{appInfo.excelFolderForTest}")
	private Resource excelFolderResourceForWindow;
	@Value("#{appInfo.saveFolederForApplication}")
	private Resource saveFolderResource;
	
	private File excelFolder;
	private File saveFolder;
	
	@PostConstruct
	public void setExcelFolder() throws IOException{
		String osName = System.getProperty("os.name").toLowerCase();
		if(osName.contains("win")) {
			excelFolder = excelFolderResourceForWindow.getFile();
			saveFolder = saveFolderResource.getFile();
		}
		if(osName.contains("mac")) {
			excelFolder = excelFolderResourceForMac.getFile();
			saveFolder = excelFolder;
		}
		if(!excelFolder.exists()) {
			excelFolder.mkdirs();
		}
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		log.info("생성된 폴더 : {}",excelFolder);
	}
	
	
	
	
	private Map<String, String> setParamMap(String id, String semCd){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		return paramMap;
	}
	private Map<String, String> setParamMap(String id, String semCd, String lctreNo){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("semCd", semCd);
		paramMap.put("lctreNo", lctreNo);
		return paramMap;
	}
	
	@Override
	public List<LectureVO> retrieveClassRoomList(String id, String semCd) {
		Map<String, String> paramMap = setParamMap(id, semCd);
		return mapper.selectClassRoomList(paramMap);
	}

	@Override
	public List<ClassroomBoardVO> retrieveHomeWorkList(String id, String semCd, String lctreNo) {
		Map<String, String> paramMap = setParamMap(id, semCd, lctreNo);
		return mapper.selectHomeWorkList(paramMap);
	}

	@Override
	public List<TestQuestionVO> retrieveTestQuestionList(String id, String semCd, String lctreNo) {
		Map<String, String> paramMap = setParamMap(id, semCd, lctreNo);
		return mapper.selectTestQuestionList(paramMap);
	}

	@Override
	public TestQuestionVO retrieveTestQuestion(TestVO test) {
		TestQuestionVO testQuestion = mapper.selectTestQuestion(test);
		if(testQuestion == null) {
			throw new RuntimeException("이미 시험을 치룸");
		}
		testQuestion.setAtchFile(mapper.selectAttatchingFileList(testQuestion.getAtchFileNo()).get(0));
		return testQuestion;
	}

	@Override
	public List<AttatchingFileVO> retrieveAttatchingFileList(String atchFileNo) {
		return mapper.selectAttatchingFileList(atchFileNo);
	}

	@Override
	public List<ClassroomBoardVO> retrieveClassRoomBoardList(String id, String semCd, String lctreNo) {
		Map<String, String> paramMap = setParamMap(id, semCd, lctreNo);
		return mapper.selectClassRoomBoardList(paramMap);
	}

	@Override
	public List<LectureVideoVO> retrieveOnlineLectureList(String id, String semCd, String lctreNo) {
		Map<String, String> paramMap = setParamMap(id, semCd, lctreNo);
		return mapper.selectOnlineLectureList(paramMap);
	}

	@Override
	public AttatchingFileVO retrieveOneAttatchingFile(AttatchingFileVO fileInfo) {
		log.info("ServiceLogic FileInfo : {} ", fileInfo);
		return mapper.selectOneAttatchingFile(fileInfo);
	}
	
	@Override
	public List<String> getBase64(AttatchingFileVO atch) throws Exception {
		List<String> base64List = new ArrayList<String>();
		if (StringUtils.isBlank(atch.getAtchFileStreNm())) {
			throw new FileNotFoundException();
		}

		PDDocument document = null;

		try {
			File file = new File(atch.getAtchFileStrePath(), atch.getAtchFileStreNm());
			if (!file.exists()) {
				throw new FileNotFoundException();
			}
			String tempfileNameOnly = atch.getAtchFileNo();

			document = PDDocument.load(file);

			int pageCount = document.getNumberOfPages();
			System.out.println("pageCount : " + pageCount);

			PDFRenderer pdfRenderer = new PDFRenderer(document);
			for (int i = 0; i < pageCount; i++) {
				int pageNum = i + 1;

				BufferedImage imageObj = pdfRenderer.renderImageWithDPI(i, 100, ImageType.RGB);

				String imageFileName = tempfileNameOnly + "-" + pageNum + ".png";

				File outputfile = new File(saveFolder, imageFileName);
				ImageIO.write(imageObj, "png", outputfile);
				byte[] targetbyte = Files.readAllBytes(outputfile.toPath());
				
				String targetBase64Code = Base64.getEncoder().encodeToString(targetbyte);
				base64List.add(targetBase64Code);
				outputfile.delete();
//				System.out.println("output " + pageNum + "/" + pageCount + " : " + outputfile.getAbsolutePath());
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (document != null) {
					document.close();
				}
			} catch (Exception e) {
			}
		}
		return base64List;
	}

	@Override
	public void createSubmitTest(Map<String, String> paramMap, String id, String lctreNo, String testSe, int questionCnt) {
		List<TestAnswerVO> answerList = new ArrayList<TestAnswerVO>();
		for(int i = 1 ; i <= questionCnt ; i++) {
			String testQuesNo = String.valueOf(i);
			TestAnswerVO answerVO = new TestAnswerVO();
			answerVO.setLctreNo(lctreNo);
			answerVO.setStdntNo(id);
			answerVO.setTestQuesNo(testQuesNo);
			answerVO.setTestSe(testSe);
			String answer = paramMap.get(testQuesNo);
			log.info("{}번째 빼온 값 : {}",i,answer);
			answerVO.setSubmitAswper(answer);
			answerList.add(answerVO);
		}
		log.info("서비스단 인서트 전 list : {}",answerList);
		mapper.insertSubmitTestAnswer(answerList);
	}

	@Override
	public int retrieveTestResultInfo(String id, String lctreNo, String testSe) {
		TestVO test = new TestVO();
		test.setLctreNo(lctreNo);
		test.setStdntNo(id);
		test.setTestSe(testSe);
		return mapper.selectTestResultInfo(test);
	}

	@Override
	public void createSubmitAssignment(String id, String crNo, MultipartFile assignmentFile) throws Exception {
		
		AttatchingFileVO atch = new AttatchingFileVO(assignmentFile);
		atch.setAtchFileStrePath(crNo);
		atch.setAtchFileStrePath(saveFolder.getCanonicalPath());
		mapper.insertAssignmentFile(atch);
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("crNo", crNo);
		paramMap.put("atchFileNo", atch.getAtchFileNo());
		
		mapper.insertAssignment(paramMap);
		
		atch.saveTo(saveFolder);
		
	}

	@Override
	public AttatchingFileVO retrieveSubmittedAssignment(String id, String crNo) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("crNo", crNo);
		return mapper.selectSubmittedAssignment(paramMap);
	}

	@Override
	public ClassroomBoardVO retrieveClassRoomBoard(String id, String crNo) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("crNo", crNo);
		return mapper.selectClassroomBoard(paramMap);
	}




	@Override
	public boolean retrieveAuthorityFile(String id, String atchFileNo) {
		AttatchingFileVO atch = new AttatchingFileVO();
		atch.setStdntNo(id);
		atch.setAtchFileNo(atchFileNo);
		return mapper.selectAuthorityFile(atch) > 0;
	}

	
}