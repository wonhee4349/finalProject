<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Sidebar -->
<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
	id="accordionSidebar" style="background:#039f9c">

	<!-- Divider -->
	<hr class="sidebar-divider my-0">
	<!-- Divider -->
	<hr class="sidebar-divider">


	<li class="nav-item">
	    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#course"
	       aria-expanded="true" aria-controls="collapseUtilities">
	        <span>강의관리</span>
	    </a>
	    <div id="course" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
	        <div class="bg-white py-2 collapse-inner rounded">
	            <a class="collapse-item" href="${pageContext.request.contextPath}/professor/lecture/lectureListUI">강의내역</a>
	            <a class="collapse-item" href="${pageContext.request.contextPath}/professor/lecture/newLectureRequestUI">강의개설신청</a>
	            <a class="collapse-item" href="${pageContext.request.contextPath}/professor/course/courseListUI">교과목내역</a>				              
	            <a class="collapse-item" href="${pageContext.request.contextPath}/professor/course/courseRequestUI">교과목개설신청</a>
	        </div>
	    </div>
	</li>
	
	<li class="nav-item">
	    <a class="nav-link collapsed" href="${pageContext.request.contextPath}/professor/classroom/classroomBaseUI"
	       aria-expanded="true" aria-controls="collapseUtilities">
	        <span>클래스룸</span>
	    </a>
	</li>
	
	<li class="nav-item">
	    <a class="nav-link collapsed" href="${pageContext.request.contextPath}/professor/score/scoreRegistrationUI"
	       aria-expanded="true" aria-controls="collapseUtilities">
	        <span>성적관리</span>
	    </a>
	</li>
   
      <li class="nav-item">
          <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#counseling"
              aria-expanded="true" aria-controls="consulting">
              <span>상담관리</span>
          </a>
		  	
          <div id="counseling" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
              <div class="bg-white py-2 collapse-inner rounded">
                  <a class="collapse-item" href="${pageContext.request.contextPath}/professor/consultation/professorConsultationRequestListUI">상담신청목록</a>
                  <a class="collapse-item" href="${pageContext.request.contextPath}/professor/consultation/professorRecognizeConsultationRequestUI">상담등록</a>
                  <a class="collapse-item" href="${pageContext.request.contextPath}/professor/consultation/professorConsultationUI">상담이력</a>
              </div>
          </div>
      </li>	
       
       <li class="nav-item">
          <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#notice"
              aria-expanded="true" aria-controls="collapsePages">
              <span>공지사항</span>
              </a>
            	<div id="notice" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
              		<div class="bg-white py-2 collapse-inner rounded">                   
                      <a class="collapse-item" href="${pageContext.request.contextPath}/professor/professorNoticeListUI">학사공지</a>                      
                      <a class="collapse-item" href="${pageContext.request.contextPath}/professor/professorCommonNoticeListUI">일반공지</a>

                 </div>
             </div>
      </li>	
      
      	<!-- Nav Item - Utilities Collapse Menu -->
	<li class="nav-item">
	    <a class="nav-link collapsed" href="/professor/mypage/professor/myPageUI" >
	        <span>마이페이지</span>
	    </a>
	</li>
</ul>