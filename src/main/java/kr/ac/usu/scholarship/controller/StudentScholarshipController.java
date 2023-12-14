package kr.ac.usu.scholarship.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.scholarship.service.StudentScholarshipService;
import kr.ac.usu.scholarship.vo.ScholarshipRequestVO;
import kr.ac.usu.scholarship.vo.ScholarshipVO;
import kr.ac.usu.user.mapper.LoginMapper;

@Controller
@RequestMapping("/student/scholarship")
public class StudentScholarshipController {
	
	@Inject
	private StudentScholarshipService service;
	
	@GetMapping("scholarshipList")
	public String getRequestableScholarshipList(
			Model model
			, String currPage
		) {
		PaginationInfo<ScholarshipVO> paging = new PaginationInfo<ScholarshipVO>();
		if(StringUtils.isNotBlank(currPage)) {
			paging.setCurrentPage(Integer.parseInt(currPage));
		}else {
			paging.setCurrentPage(1);
		}
		String semCd = LoginMapper.getNextRegularSemesterCode();
		ScholarshipVO detailCondition = new ScholarshipVO();
		detailCondition.setSemstrNo(semCd);
		paging.setDetailCondition(detailCondition);
		service.retrieveScholarshipList(paging);
		paging.setRenderer(new BootstrapPaginationRenderer());
		model.addAttribute("paging", paging);
		return "jsonView";
	}
	
	@GetMapping("{schlshipNo}")
	public String getScholarshipInformation(
			@PathVariable String schlshipNo
			, Model model
		) {
		ScholarshipVO scholarship = service.retrieveScholarship(schlshipNo);
		model.addAttribute("scholarship", scholarship);
		return "jsonView";
	}
	
	@PostMapping("{schlshipNo}")
	public String studentRequestSholarship(
			Model model
			, Authentication auth
			, @PathVariable String schlshipNo
		) {
		ScholarshipRequestVO scholarshipRequest = new ScholarshipRequestVO();
		scholarshipRequest.setSchlshipNo(schlshipNo);
		scholarshipRequest.setStdntNo(auth.getName());
		try {
			ServiceResult result = service.createStudentScholarship(scholarshipRequest);
			model.addAttribute("result", result);
		} catch (Exception e) {
			model.addAttribute("message", "이미 신청한 장학금입니다");
		}
		return "jsonView";
	}
	
	@GetMapping("status")
	public String getStudentScholarshipStatus(
			Model model
			, Authentication auth
		) {
		String semCd = LoginMapper.getNextRegularSemesterCode();
		List<ScholarshipRequestVO> list = service.retrieveStudentRequestScholarshipList(auth.getName(), semCd);
		model.addAttribute("list", list);
		return "jsonView";
	}
	
	@GetMapping("semCdList")
	public String getScholarshipSemCdList(
			Model model
			, Authentication auth
		) {
		model.addAttribute("list", service.retrieveStudentScholarSemCdList(auth.getName()));
		return "jsonView";
	}
	
	@GetMapping("List")
	public String getStudentScholarship(
			Model model
			, String semCd
			, Authentication auth
		) {
		model.addAttribute("list", service.retrieveStudentScholarList(auth.getName(), semCd));
		return "jsonView";
	}
	
}
