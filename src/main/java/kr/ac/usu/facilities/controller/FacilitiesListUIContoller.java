package kr.ac.usu.facilities.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.ac.usu.facilities.mapper.StaffFacilitiesListMapper;
import kr.ac.usu.facilities.service.FacilitiesListService;
import kr.ac.usu.facilities.vo.BuildingVO;
import kr.ac.usu.professor.mapper.StaffProfessorMapper;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;

/**
 * 
 * @author PC-25
 *
 * @author 이재혁
 * @since 2023. 11. 10.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 10.      이재혁      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Controller
public class FacilitiesListUIContoller {
	
	@Inject
	private StaffFacilitiesListMapper mapper;
	
	@Inject
	private FacilitiesListService service;

	@GetMapping("/staff/facilitiesListUI")
	public String facilitiesView(Model model) {
		
		List<BuildingVO> building = mapper.selectBuildingList();
		
		model.addAttribute("building", building);
		
		return "staff/facilities/facilitiesListUI";
	}
	
	@GetMapping("/staff/facilities/staffFacilitiesInsertUI")
	public String staffFacilitiesInsertUI(
			Model model
		) {
		
		List<BuildingVO> building = mapper.selectBuildingList(); 
		
		model.addAttribute("building", building);
		
		return "staff/facilities/staffFacilitiesInsertUI";
	}
	
	@GetMapping("/ajax/staff/facilities/facilitiesView")
	public String staffFacilitiesUpdateView(
			Model model
		) {
		
		List<BuildingVO> building = mapper.selectBuildingList(); 
		
		model.addAttribute("building", building);
		
		return "/ajax/staff/facilities/facilitiesView";
	
	}
}
