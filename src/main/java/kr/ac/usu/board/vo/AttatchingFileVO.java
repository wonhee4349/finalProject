package kr.ac.usu.board.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Base64;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 8.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 8.      문선영       최초작성
 * 2023. 11. 28.     김석호       바이트 기반 프로퍼티 추가
 * 2023. 11. 30.     김석호       권한검색용 학생학번 프로퍼티 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Data
@EqualsAndHashCode(of = {"atchFileNo", "atchFileSn"})
@NoArgsConstructor
public class AttatchingFileVO implements Serializable{
	
	private MultipartFile boFile;
	
	public AttatchingFileVO(MultipartFile boFile){
		super();
		this.boFile = boFile;
		this.atchFileNm = boFile.getOriginalFilename();
		this.atchFileMimeType = boFile.getContentType();
		this.atchFileMg = boFile.getSize();
		this.atchFileIndictMg = FileUtils.byteCountToDisplaySize(atchFileMg);
		this.atchFileStreNm = UUID.randomUUID().toString();
		
	}
	
	private String atchFileNo;
	
	private Integer atchFileSn;
	
	private String atchFileNm;
	
	private String atchFileStreNm;
	
	private String atchFileStrePath;
	
	private long atchFileMg;
	
	private String atchFileIndictMg;
	
	private String atchFileMimeType;
	
	public void saveTo(File saveFolder) throws IllegalStateException, IOException {
		if(boFile!=null) {
			boFile.transferTo(new File(saveFolder, atchFileStreNm));
		}
	}
	
	// 바이트기반
	private byte[] fileByteData;
	
	public void setFileByteData() throws IOException {
		if(StringUtils.isNotBlank(this.atchFileStreNm)) {
			File file = new File(this.atchFileStrePath, this.atchFileStreNm);
			this.fileByteData = Files.readAllBytes(file.toPath());
		}
	}
	
	public String getBase64() {
        if(this.fileByteData != null) {
        	return Base64.getEncoder().encodeToString(this.fileByteData);
        }else {
        	return null;
        }
	}
	
	
	private String stdntNo;
	private String mimeType;
}
