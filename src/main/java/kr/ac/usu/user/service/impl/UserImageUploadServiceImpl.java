package kr.ac.usu.user.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.user.mapper.UserImageUploadMapper;
import kr.ac.usu.user.service.UserImageUploadService;
import lombok.extern.slf4j.Slf4j;

/**
 * 유저 이미지 업로드 서비스 구현체
 * @author 김석호
 * @since 2023. 11. 29.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자          수정내용
 * --------         --------    ----------------------
 * 2023. 11. 29.      김석호         최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Service
public class UserImageUploadServiceImpl implements UserImageUploadService {

	@Inject
	private UserImageUploadMapper mapper;
	
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

	@Override
	public void createUserImage(String id, String role, MultipartFile imageFile) throws IOException, RuntimeException {
		
		AttatchingFileVO target = new AttatchingFileVO(imageFile);
		target.setAtchFileStrePath(saveFolder.getCanonicalPath());
		mapper.insertAttatchingFile(target);
		
		String table = null;
		String cols = null;
		switch (role) {
			case "STUDENT":
				table = "STUDENT_INFORMATION";
				cols = "STDNT_NO";
				break;
			case "PROFESSOR":
				table = "PROFESSOR";
				cols = "PRFSOR_NO";
				break;
			case "STAFF":
				table = "STAFF";
				cols = "SKLSTF_NO";
				break;
			default:
				throw new RuntimeException("유저의 롤이 잘못 됨");
		}
		
		AttatchingFileVO atch = mapper.selectUserImageInfo(id);
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("table", table);
		paramMap.put("cols", cols);
		paramMap.put("atchFileNo", target.getAtchFileNo());
		mapper.updateUserImageInfo(paramMap);
		
		if(atch != null) {
			mapper.deleteOriginAttatchingFile(atch);
			File origin = new File(atch.getAtchFileStrePath(), atch.getAtchFileStreNm());
			origin.delete();
		}
		
		target.saveTo(saveFolder);
	}

}