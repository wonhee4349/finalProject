package kr.ac.usu.scholarship.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.scholarship.mapper.StaffScholarshipMapper;
import kr.ac.usu.scholarship.service.StaffScholarshipService;
import kr.ac.usu.scholarship.vo.ScholarshipListVO;
import kr.ac.usu.scholarship.vo.ScholarshipVO;
import lombok.RequiredArgsConstructor;

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
 * 2023. 11. 9.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class StaffScholarshipServiceImpl implements StaffScholarshipService{
	
	private final StaffScholarshipMapper scholarshipMapper;
	
	// 장학금 리스트
	@Override
	public void retrieveScholarshipList(PaginationInfo<ScholarshipVO> paging) {

		int totalRecord = scholarshipMapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ScholarshipVO> dataList = scholarshipMapper.selectScholarshipList(paging);
		paging.setDataList(dataList);
		
	}

	// 장학금 정보
	@Override
	public ScholarshipVO retrieveScholarshipInfo(String schlshipNo) {
		ScholarshipVO scholarshipInfo = scholarshipMapper.selectScholarshipInfo(schlshipNo);
		return scholarshipInfo;
	}

	// 총장학금 리스트
	@Override
	public void retrieveTotalScholarshipList(PaginationInfo<ScholarshipListVO> paging) {
		int totalRecord = scholarshipMapper.selectTotalScholarshipRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ScholarshipListVO> dataList = scholarshipMapper.selectTotalScholarshipList(paging);
		paging.setDataList(dataList);
		
	}

	// 새로운 장학금 인서트
	@Override
	public int createTotalScholarship(ScholarshipListVO schoNm) {
		int res = scholarshipMapper.insertTotalScholarship(schoNm);
		return res;
	}

	@Override
	// 장학금 추가 장학금 셀렉트박스 불러오기
	public List<ScholarshipListVO> retrieveInsertScholarshipList() {
		List<ScholarshipListVO> scholarshipList = scholarshipMapper.selectInsertScholarshipList();
		return scholarshipList;
	}

	// 학기별 장학금 추가
	@Override
	public int createScholarship(ScholarshipVO scholarship) {
		int res = scholarshipMapper.insertScholarship(scholarship);
		return res;
	}	

}
