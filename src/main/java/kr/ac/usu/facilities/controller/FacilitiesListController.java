package kr.ac.usu.facilities.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.usu.facilities.mapper.StaffFacilitiesListMapper;
import kr.ac.usu.facilities.service.FacilitiesListService;
import kr.ac.usu.facilities.vo.BuildingVO;
import kr.ac.usu.facilities.vo.FacilitiesVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import kr.ac.usu.professor.mapper.StaffProfessorMapper;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ProfessorVO;
import kr.ac.usu.user.vo.StudentVO;

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
 * 2023. 11. 7.      작성자명       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/staff/facilities")
public class FacilitiesListController {
	@Inject
	private StaffFacilitiesListMapper mapper;
	@Inject
	private FacilitiesListService service;
	
	@GetMapping("ajax/facilitiesList")
	public String listData(
		@ModelAttribute("detailCondition") FacilitiesVO detailCondition
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		
		BuildingVO building = new BuildingVO();
		detailCondition.setBuilding(building);
		
		
		PaginationInfo<FacilitiesVO> paging = new PaginationInfo<>(10,5);
		paging.setDetailCondition(detailCondition);
		paging.setCurrentPage(currentPage);
		
		service.retrieveFacilitiesList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("ajax/facilitiesView")
	public String memberView(
		@RequestParam("who") String fcltsNo
		, Model model
	){
		FacilitiesVO facilities = service.retrieveFacilities(fcltsNo);
		List<BuildingVO> building = mapper.selectBuildingList(); 
		
		model.addAttribute("facilities", facilities);
		model.addAttribute("building", building);
		
		return "/ajax/staff/facilities/facilitiesView";
	}
	@PostMapping("/staffFacilitiesInsertData")
	public String staffFacilitiesData(
		FacilitiesVO prof
		, Model model
	) {
		try {
			int res = service.createFacilities(prof);
			model.addAttribute("success", true);
			model.addAttribute("message", "성공했습니다!");
		} catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "실패했습니다!");
		}
		return "jsonView";
	}
	
	@PostMapping("/staffFacilitiesUpdateData")
	public String staffFacilitiesUpdateData(
		FacilitiesVO prof
		, Model model
	) {
		try {
			int res = service.modifyFacilities(prof);
			model.addAttribute("success", true);
			model.addAttribute("message", "수정되었습니다!");
		} catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("message", "실패했습니다!");
		}
		return "jsonView";
	}
}
