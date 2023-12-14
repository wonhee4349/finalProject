package kr.ac.usu.lecture.controller;


import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.common.mapper.CommonOptionsMapper;
import kr.ac.usu.course.vo.CourseVO;
import kr.ac.usu.facilities.vo.BuildingVO;
import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.facilities.vo.FacilitiesVO;
import kr.ac.usu.lecture.service.ProfessorLecturetService;
import kr.ac.usu.lecture.vo.LectureRequestVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.paging.vo.SearchVO;
import kr.ac.usu.schoolaffairsschedule.vo.SemesterVO;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.vo.ComCodeVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

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
 * 수정일            수정자           수정내용
 * --------       --------    ----------------------
 * 2023. 11. 7.     이재혁     최초작성
 * 2023. 11.10.     김재성	  수강중인 강의 목록 불러오기
 * 2023. 11.13.     김재성	  수강중인 강의 ajax 비동기 요청
 * 2023. 11.17.     김재성    교수 강의 수강학생 목록 조회 메서드 생성
 * 2023. 11.22.     김재성    교수 강의 개설 신청 Form html 작성
 * 2023. 11.23.     김재성    교수 강의 개설 신청 select 절 리스트
 * 2023. 11.24.     김재성    교수 강의 개설 신청목록 페이징 리스트
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/professor/lecture")
public class ProfessorLectureController {
	@Inject
	private ProfessorLecturetService service;
	@Inject
	private CommonOptionsMapper comOptionMapper;
	
	//강의목록 UI
	@GetMapping("/lectureListUI")
	public String LectureListUI() {
		
		return "professor/lecture/professorLectureUI";
	}
	
	//비동기식 검색조건 강의목록 리스트
	@GetMapping("/ajax/lectureList")
//	@ResponseBody
//	public PaginationInfo<LectureVO> lectureList(
	public String lectureList(
			@ModelAttribute("simpleCondition") SearchVO simpleCondition
			, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
			, Model model
			, Authentication principle
	) {
		
		PaginationInfo<LectureVO> paging = new PaginationInfo<>();
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		
		String prfsorNo = principle.getName();
		LectureVO lectureVO = new LectureVO();
		lectureVO.setPrfsorNo(prfsorNo);
		
		paging.setDetailCondition(lectureVO);
		
		log.info("검색 받아오는 값 : - simpleCondition : {}",simpleCondition);
		
		service.retrieveLectureList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		
		
//		return paging;
		return "jsonView";
	}
	
	// 교수강의 수강학생 목록 리스트
	@RequestMapping(value="/ajax/lectureStudentListView", method = RequestMethod.GET)
	public String lectureStudentListView(
			@RequestParam("lctreNo") String lctreNo
			, Model model
			) {
		
		log.info("교과목번호 가져온 값 : {}", lctreNo);
		
		List<StudentVO> studentList = service.retrieveLectureStudentList(lctreNo);
		
		log.info("studentList : {}" ,studentList);
		
		// 출결상태 구분
		String compleComCodeGrp = "SEC023";
		List<ComCodeVO> atendSe = comOptionMapper.getComeCodeList(compleComCodeGrp);
		
		model.addAttribute("atendSe",atendSe);
		model.addAttribute("studentList",studentList);
		
		return "ajax/professor/lecture/professorLectureStudentListView";
	}
	
	
	//강의 개설 신청 UI 페이지 이동 메소드
	@GetMapping("/newLectureRequestUI")
	public String newLectureRequest(
			Model model
	) {
		// 이수 구분
		String compleComCodeGrp = "SEC014";
		List<ComCodeVO> compleSe = comOptionMapper.getComeCodeList(compleComCodeGrp);	
		model.addAttribute("compleSe",compleSe);
		
		// 학기 구분
		String semstrComCodeGrp = "SEC018";
		List<ComCodeVO> semstrSe = comOptionMapper.getComeCodeList(semstrComCodeGrp);	
		model.addAttribute("semstrSe",semstrSe);
		
		// 강의 형태
		String lctreComCodeGrp = "SEC015";
		List<ComCodeVO> lctreSe = comOptionMapper.getComeCodeList(lctreComCodeGrp);	
		model.addAttribute("lctreSe",lctreSe);
		
		//건물 구분
		List<BuildingVO> building = comOptionMapper.getBuildingList();
		model.addAttribute("building",building);
		
		// 강의 형태
		String tmtbComCodeGrp = "SEC011";
		List<ComCodeVO> tmtbSe = comOptionMapper.getComeCodeList(tmtbComCodeGrp);	
		model.addAttribute("tmtbSe",tmtbSe);
		
		//단과대학 구분
		List<CollegeVO> college = comOptionMapper.getCollegeList();
		model.addAttribute("college",college);
		
		return"professor/lecture/professorLectureRequestUI";
	}
	
	// 단과대학 선택 별 학과 리스트
	@ResponseBody
	@GetMapping("/subjectList")
	public List<SubjectVO> subjectList(
		@RequestParam(value="clgNo") String clgNo
		,Model model
	) {
		log.info("프론트 보내온 매개변수 : {}",clgNo);
		//학과 구분
		List<SubjectVO> subject = comOptionMapper.getSubjectList(clgNo);
		log.info("돌아오는 학과 리스트 확인 : {}",subject);
		
		return subject;
	}
	
	// 학과 선택 별 교과목 리스트
	@ResponseBody
	@GetMapping("/courseList")
	public List<CourseVO> courseList(
			@RequestParam(value="subjctNo") String subjctNo
			,Model model
			) {
		log.info("&&&&&&&&&&&&&프론트 보내온 매개변수 : {}",subjctNo);
		//학과 구분
		List<CourseVO> course = comOptionMapper.getCourseList(subjctNo);
		log.info("&&&&&&&&&&&&&&돌아오는 학과 리스트 확인 : {}",course);
		
		return course;
	}
	
	// 건물 선택별 강의실 선택 리스트
	@ResponseBody
	@GetMapping("/facilitiesList")
	public List<FacilitiesVO> facilitiesList(
			@RequestParam(value="buldNo") String buldNo
			,Model model
			) {
		log.info("&&&&&&&&&&&&&&&&&&&&&&&프론트 보내온 매개변수 : {}",buldNo);
		//학과 구분
		List<FacilitiesVO> facilities = comOptionMapper.getFacilitiesList(buldNo);
		log.info("&&&&&&&&&&&&&&&&&&&돌아오는 학과 리스트 확인 : {}",facilities);
		
		return facilities;
	}
	
	// 강의 개설 신청 ->( 강의개설신청, 강의계획서, 강의평가기준, 강의개설정보)
	@PostMapping("/newLectureRequest")
	public String newLectureRequest(
			LectureRequestVO lectureRequestVO
			,Model model
			) {
		
		log.info("newLectureRequest->lectureRequestVO : " + lectureRequestVO);
		
		int result = this.service.newLectureRequest(lectureRequestVO);
		log.info("newLectureRequest->result : " + result);
		
		if(result > 0) {
			model.addAttribute("success",true);
			model.addAttribute("message","수정 되었습니다!");
		}else {
			model.addAttribute("success",false);
			model.addAttribute("message","실패 했습니다!");
		}
		
		return "jsonView";
	}
	
	//비동기식 검색조건 강의개설신청 목록 리스트
	@GetMapping("/ajax/lectureRequestList")
	public String lectureRequestList(
			@ModelAttribute("simpleCondition") SearchVO simpleCondition
			, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
			, Model model
			, Authentication principle
	) {
		PaginationInfo<LectureRequestVO> paging = new PaginationInfo<>(10,5);
		
		String prfsorNo = principle.getName();
		LectureRequestVO lectureRequsetVO = new LectureRequestVO();
		lectureRequsetVO.setPrfsorNo(prfsorNo);
		paging.setDetailCondition(lectureRequsetVO);
		
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		log.info("검색 받아오는 값 : - paging : {}",paging);
		
		service.retrieveLectureRequestList(paging);
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 강의 개설 신청 상세 조회
	@GetMapping("/ajax/lectureRequestView")
	public String professorLectureRequestView(
			@RequestParam("lctreReqstNo") String lctreReqstNo
			, Model model
			, Authentication principal
	) {
		
		log.info("신청번호 값 유무 확인 : {}", lctreReqstNo);
		String prfsorNo = principal.getName();
		
		
		LectureRequestVO lectureRequest = new LectureRequestVO();
		
		lectureRequest.setLctreReqstNo(lctreReqstNo);
		lectureRequest.setPrfsorNo(prfsorNo);
		
		LectureRequestVO lectureRequestView = service.retrieveLectureRequestView(lectureRequest);
		
		log.info("lectureRequestView : {}" ,lectureRequestView);
		
		model.addAttribute("lectureRequestView",lectureRequestView);
		
		return "ajax/professor/lecture/professorLectureRequestView";
	}
	
}
