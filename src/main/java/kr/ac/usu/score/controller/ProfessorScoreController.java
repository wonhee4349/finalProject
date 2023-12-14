package kr.ac.usu.score.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.common.mapper.CommonOptionsMapper;
import kr.ac.usu.lecture.vo.AttendanceLectureVO;
import kr.ac.usu.lecture.vo.LectureEvaluationStandardVO;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.score.service.ProfessorScoreService;
import kr.ac.usu.score.vo.ScoreObjectionVO;
import kr.ac.usu.score.vo.ScoreVO;
import kr.ac.usu.user.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author PC-21
 *
 * @author 김재성
 * @since 2023. 12. 02.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 *   수정일          수정자           수정내용
 * ----------     --------    ----------------------
 * 2023.12.02.      김재성      최초작성
 * 2023.12.05.      김재성      성적이의신청
 * 2023.12.06.      김재성      성적등록
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/professor/score")
public class ProfessorScoreController {

	@Inject
	private ProfessorScoreService service;
	
	@Inject
	private CommonOptionsMapper commonMapper;
	
	@Value("#{appInfo.saveFolederForApplicationKJS}")
	private Resource saveFolderResource;
	
	//성적관리 UI
	@GetMapping("/scoreRegistrationUI")
	public String scoreRegistrationUI(
			Model model
			,Authentication principal
			
	) {
		String prfsorNo = principal.getName();
		List<LectureVO> lectureList =service.retrieveScoreProfessorLecture(prfsorNo);
		
		log.info("성적 메뉴 클릭시 가져오는 강의관련 데이터 : {}",lectureList);
		
		model.addAttribute("lectureList",lectureList);
		
		return "professor/score/professorScoreRegistrationUI";
	}
	
	
	
	// --------------------- 성적관리 Main View End -------------------------------------
	
	// 성적관리 성적이의신청 목록 Tab View 
	@GetMapping("/scoreObjectionList/{lctreNo}")
	public String scoreObjectionList(
			Model model
			,@PathVariable String lctreNo
	) {
//		String lctreNo = "E04523201";
		List<ScoreObjectionVO> scoreObjectList = service.retrieveScoreObjectionList(lctreNo);
		
		log.info("scoreObjectList 클래스룸 구분 코드 오는지 확인 : {}",scoreObjectList);
		
		model.addAttribute("scoreObjectList",scoreObjectList);
		model.addAttribute("lctreNo",lctreNo);
		
		return "ajax/professor/score/professorScoreObjectionView";
	}
	
	//성적이의신청 상세내역
	@GetMapping("/scoreObjectionDetailView/{lctreNo}/{stdntNo}")
	public String scoreObjectionDetailView(
			@PathVariable("lctreNo") String lctreNo
			,@PathVariable("stdntNo") String stdntNo
			,Model model
			
	) {
		log.info("매개변수 나오는지 확인 : {} : {}",lctreNo,stdntNo);
		
		AttendanceLectureVO attendanceLectureVO = new AttendanceLectureVO();
		
		attendanceLectureVO.setLctreNo(lctreNo);
		attendanceLectureVO.setStdntNo(stdntNo);
		
		ScoreObjectionVO scoreObjectionView = service.retrieveScoreObjectionDetailView(attendanceLectureVO);
		log.info("이의신청 상세정보 나오는지 확인 : {}",scoreObjectionView);
		
		model.addAttribute("scoreObjectionView",scoreObjectionView);
				
		
		return "ajax/professor/score/professorScoreObjectionDetailView";
	}
	
	// 성적이의신청 반려
	@PostMapping("/modifyRefuseScoreObjection")
	public String modifyRefuseScoreObjection(
			ScoreObjectionVO scoreObjection
			,Model model
			
	){
		// 승인구분 반려로 입력
		scoreObjection.setConfmSe("03");
		
		ServiceResult result = service.modifyRefuseScoreObjection(scoreObjection);
		
		if(ServiceResult.OK.equals(result)) {
			model.addAttribute("success",true);
			model.addAttribute("message","수정 되었습니다!");
		}else {
			model.addAttribute("success",false);
			model.addAttribute("message","실패 했습니다!");
		}
		
		log.info("반려 처리 한 값 확인 하기 : {}",scoreObjection);
		
		return "jsonView";
	}
	
	// 성적이의신청 정정
	@PostMapping("/modifyResetScoreObjection")
	public String modifyResetScoreObjection(
			ScoreObjectionVO scoreObjection
			,Model model
		){
		// 승인구분 승인완료로 입력
		scoreObjection.setConfmSe("02");
		
		log.info("정정 처리 한 값 확인 하기 : {}",scoreObjection);
		
		ServiceResult result = this.service.modifyResetScoreObjection(scoreObjection);
		
		if(ServiceResult.OK.equals(result)) {
			model.addAttribute("success",true);
			model.addAttribute("message","수정 되었습니다!");
		}else {
			model.addAttribute("success",false);
			model.addAttribute("message","실패 했습니다!");
		}
		
		return "jsonView";
	}
	
	// ---------------------------------------- 성적이의신청 관련 End ---------------------------------------------
	
	// 성적관리 성적등록 Tab 수강학생 리스트 
	@GetMapping("/scoreRegistrationList/{lctreNo}")
	public String scoreRegistrationList(
			 Model model
		   , @PathVariable String lctreNo
	) {
		
		List<StudentVO> studentList = service.retrieveScoreRegistrationStudentList(lctreNo);
		
		//log.info("scoreObjectList 클래스룸 구분 코드 오는지 확인 : {}",studentList);
		
		model.addAttribute("studentList",studentList);
		model.addAttribute("lctreNo",lctreNo);
		
		return "ajax/professor/score/professorScoreRegistrationView";
	}
	
	
	//성적등록 Modal Click 시 강의평가 기준
	@GetMapping("/scoreRegistrationView/{lctreNo}/{stdntNo}")
	public String scoreRegistrationView(
			@PathVariable("lctreNo") String lctreNo
			,@PathVariable("stdntNo") String stdntNo
			,Model model
			
	) {
		log.info("매개변수 나오는지 확인 : {} : {}",lctreNo,stdntNo);
		
		List<LectureEvaluationStandardVO> lctreEvlStdrList = service.retrieveLectureEvaluationStandard(lctreNo);
		
		log.info("값이 안오는지 확인 lctreEvlStdrList  : {}",lctreEvlStdrList);
		
		model.addAttribute("lctreEvlStdrList",lctreEvlStdrList);
		model.addAttribute("stdntNo",stdntNo);
		model.addAttribute("lctreNo",lctreNo);
		
		return "jsonView";
	}
	
	//성적 등록
	@PostMapping("/scoreRegistration")
	public String scoreRegistration(
			  ScoreObjectionVO scoreObjectionVO
			  ,Model model
	) {
		
		log.info("값이 안오는지 확인 scoreVO  : {}",scoreObjectionVO);
		try {
			ServiceResult result = service.createScoreRegistration(scoreObjectionVO);
			if(ServiceResult.OK.equals(result)) {
				model.addAttribute("success",true);
				model.addAttribute("message","수정 되었습니다!");
			}else {
				model.addAttribute("success",false);
				model.addAttribute("message","실패 했습니다!");
			}
		} catch (NullPointerException e) {
		    e.printStackTrace();
		}
		
		
		
		
		return "jsonView";
		
	}
}





