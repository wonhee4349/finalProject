package kr.ac.usu.common.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.ac.usu.board.vo.AttatchingFileVO;
import kr.ac.usu.user.mapper.UserImageUploadMapper;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ImageStreamingController {
	
	@Inject
	private ResourceLoader loader;
	
	@Inject
	private UserImageUploadMapper mapper;
	
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
	
	@GetMapping("/staff/profileImage/{userId}")
	public ResponseEntity<Resource> studentImageStreaming(
			@PathVariable String userId
		) throws IOException{
		return getUserImageResource(userId);
	}
	
	@GetMapping({"/student/profileImage","/professor/profileImage","/staff/profileImage"})
	public ResponseEntity<Resource> mypageProfileImageStreaming(
			Authentication auth
			) throws IOException{
		return getUserImageResource(auth.getName());
	}
	
	private ResponseEntity<Resource> getUserImageResource(String id) throws IOException {
		Resource requestedResource = null;
		AttatchingFileVO atch = mapper.selectUserImageInfo(id);
		if(atch != null) {
			requestedResource = folderResource.createRelative(atch.getAtchFileStreNm());
		}
		HttpHeaders header = new HttpHeaders();
		String contentType = null;
		if(requestedResource == null || !requestedResource.exists()) {
			requestedResource = loader.getResource("resources/img/user_defaultImage.svg");
			Path filePath = Paths.get(requestedResource.getURI());
			contentType = Files.probeContentType(filePath);
			log.info("파일 리소스 처리중 파일경로 : {}",filePath);
			log.info("파일 리소스 처리중 컨텐츠타입 : {}",contentType);
		}else {
			contentType = atch.getMimeType();
		}
		header.add("Content-Type", contentType);
		return new ResponseEntity<Resource>(requestedResource,header,HttpStatus.OK);
		
	}
	
}
