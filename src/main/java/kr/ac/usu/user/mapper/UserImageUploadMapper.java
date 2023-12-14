package kr.ac.usu.user.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.usu.board.vo.AttatchingFileVO;

/**
 * 유저 이미지 업로드 맵퍼
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
@Mapper
public interface UserImageUploadMapper {
	
	/**
	 * 유저 아이디로 기등록된 이미지 파일 정보 조회
	 * @param id
	 * @return 기등록 이미지 없을 경우 null
	 */
	public AttatchingFileVO selectUserImageInfo(@Param("id") String id);
	
	/**
	 * 파일 먼저 업로드
	 * @param atch
	 * @return
	 */
	public int insertAttatchingFile(AttatchingFileVO atch);
	
	/**
	 * 유저 아이디 key : id
	 * 타겟 테이블 key : table
	 * 타겟 컬럼 key : cols
	 * 통합첨부파일 번호 key : atchFileNo
	 * @param paramMap
	 * @return
	 */
	public int updateUserImageInfo(Map<String, String> paramMap);
	
	/**
	 * 이미지 파일이 이미 있는 경우 원래 파일 정보 삭제
	 * @param atch
	 * @return
	 */
	public int deleteOriginAttatchingFile(AttatchingFileVO atch);
}
