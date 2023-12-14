package kr.ac.usu.lecture.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sound.midi.MidiDevice.Info;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.usu.common.enumpkg.ServiceResult;
import kr.ac.usu.common.mapper.CommonOptionsMapper;
import kr.ac.usu.facilities.vo.CollegeVO;
import kr.ac.usu.lecture.service.StudentAttendanceLectureService;
import kr.ac.usu.lecture.vo.LectureVO;
import kr.ac.usu.lecture.vo.StudentAttendanceLectureVO;
import kr.ac.usu.paging.BootstrapPaginationRenderer;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.subject.vo.SubjectVO;
import kr.ac.usu.user.mapper.LoginMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 이재혁
 * @since 2023. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자               수정내용
 * --------     --------    ---------------------------------------
 * 2023. 11. 7.      이재혁      최초작성
 * 2023. 11. 11.     김석호      실시간 수강신청용 websocketHandler로 구현
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Slf4j
public class StudentAttendanceLectureController extends TextWebSocketHandler {
	
	private static List<WebSocketSession> sessions = new ArrayList<>();
	
	@Inject
	private StudentAttendanceLectureService service;
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		log.info("들어옴.. {}", session);
		String id = session.getPrincipal().getName();
		String currSemCd = LoginMapper.getCurrentSemesterCode();
		String preSemCd = LoginMapper.getPrevRegularSemesterCode();
		log.info("호우예 : {},{},{} ", id, currSemCd, preSemCd);
		int requestablePoint = getRequestablePoint(session);
		List<LectureVO> list = service.retrieveRequestedLectureList(id, currSemCd);
		List<LectureVO> prepareList = service.retrievePrepareLectureList(id, currSemCd);
		StudentAttendanceLectureVO message = new StudentAttendanceLectureVO();
		message.setCommand("INIT");
		message.setLectureList(list);
		message.setPrepareList(prepareList);
		message.setPnt(requestablePoint);
		goMessage(session, message);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 여기서 수강신청에 대한 로직을 처리할 예정
		String currSemCd = LoginMapper.getCurrentRegularSemesterCode();
		StudentAttendanceLectureVO messages = new StudentAttendanceLectureVO();
		
		// 로직 처리
		ObjectMapper getMapper = new ObjectMapper();
		StudentAttendanceLectureVO getMessage = getMapper.readValue(message.getPayload() , StudentAttendanceLectureVO.class );
		log.info("getMessage : {}", getMessage);
		if(getMessage.getCommand().equals("SEARCH")) {
			PaginationInfo<LectureVO> paging = new PaginationInfo<LectureVO>();
			LectureVO detailCondition = getMessage.getLecture();
			String currentPage = detailCondition.getCurrentPage();
			if(StringUtils.isNoneBlank(currentPage)) {
				paging.setCurrentPage(Integer.parseInt(currentPage));
			}else {
				paging.setCurrentPage(1);
			}
			detailCondition.setSemstrSe(currSemCd);
			paging.setDetailCondition(detailCondition);
			service.retrieveRequestableLectureList(paging);
			paging.setRenderer(new BootstrapPaginationRenderer());
			
			getMessage.setPaging(paging);
			goMessage(session, getMessage);
			return;
		}
		int requestablePoint = getRequestablePoint(session);
		getMessage.setPnt(requestablePoint);
		getMessage.setSemCd(currSemCd);
		String id = session.getPrincipal().getName();
		getMessage.setStdntNo(id);
		if(getMessage.getCommand().equals("LECTURECANCEL")) {
			lectureCancelHandler(session, getMessage);
			return;
		}
		if(getMessage.getCommand().equals("LECTUREREQUEST")) {
			lectureRequestHandler(session, getMessage);
			return;
		}
		if(getMessage.getCommand().equals("LECTUREPREPARE")) {
			lecturePrepareHandler(session, getMessage);
			return;
		}
		if(getMessage.getCommand().equals("PREPARECANCEL")) {
			lectureCancelPrepareHandler(session, getMessage);
			return;
		}
		
		/*
		수강취소 LECTURECANCEL
		수강신청 LECTUREREQUEST
		예비신청 LECTUREPREPARE
		예비취소 PREPARECANCEL
		*/
		
//		for(WebSocketSession target : sessions) {
//			goMessage(target, messages);
//		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
		log.info("나감.. {}" , session);
	}
	
	private void goMessage(WebSocketSession session, StudentAttendanceLectureVO message) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String target = objectMapper.writeValueAsString(message);
		session.sendMessage(new TextMessage(target));
	}
	
	private synchronized void goMessageAll(StudentAttendanceLectureVO message) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String target = objectMapper.writeValueAsString(message);
		TextMessage sendMessage = new TextMessage(target); 
		for(WebSocketSession session : sessions) {
			session.sendMessage(sendMessage);
		}
	}
	
	private int getRequestablePoint(WebSocketSession session) {
		String id = session.getPrincipal().getName();
		String currSemCd = LoginMapper.getCurrentSemesterCode();
		String preSemCd = LoginMapper.getPrevRegularSemesterCode();
		log.info("호우예 : {},{},{} ", id, currSemCd, preSemCd);
		return service.retrieveRequestablePoint(id, preSemCd, currSemCd);
	}
	
	private void lectureCancelHandler(WebSocketSession session, StudentAttendanceLectureVO attendance) throws Exception {
		ServiceResult result = null;
		String message = null;
		try {
			result = service.removeAttendanceLecture(attendance);
		} catch (Exception e) {
			message = "강의가 진행되어 취소할 수 없습니다.";
		}
		attendance.setMessage(message);
		if(message == null) {
			goMessageAll(attendance);
		}else {
			goMessage(session, attendance);
		}
	}
	private void lectureRequestHandler(WebSocketSession session, StudentAttendanceLectureVO attendance) throws Exception {
		ServiceResult result = null;
		String message = null;
		try {
			result = service.createAttendanceLecture(attendance);
			//기신청 : ALREADYDONE , 인원제한 : LIMITPERSON , 학점부족 : NOTENOUGHPOINT, 성공 : OK
			switch (result) {
			case ALREADYDONE:
				message = "이미 신청한 과목입니다";
				break;
			case LIMITPERSON:
				message = "정원 초과입니다";
				break;
			case NOTENOUGHPOINT:
				message = "신청할 수 있는 학점이 부족합니다";
				break;
			default:
				break;
			}
		} catch (Exception e) {
			log.info("강의 신청 중 오류발생 : {}",e);
		}
		if(result==null) {
			message = "시간표가 겹칩니다.";
		}
		attendance.setMessage(message);
		if(message==null) {
			goMessageAll(attendance);
		}else {
			goMessage(session, attendance);
		}
	}
	
	
	private void lecturePrepareHandler(WebSocketSession session, StudentAttendanceLectureVO attendance) throws Exception {
		ServiceResult result = null;
		String message = null;
		try {
			result = service.createPrepareLecture(attendance);
		} catch (Exception e) {
			message = "예비신청 중 오류발생";
			attendance.setMessage(message);
		}
		goMessage(session, attendance);
	}
	private void lectureCancelPrepareHandler(WebSocketSession session, StudentAttendanceLectureVO attendance) throws Exception {
		ServiceResult result = null;
		String message = null;
		try {
			result = service.removePrepareLecture(attendance);
		} catch (Exception e) {
			message = "예비취소 중 오류발생";
			attendance.setMessage(message);
		}
		goMessage(session, attendance);
	}
	
	
}
