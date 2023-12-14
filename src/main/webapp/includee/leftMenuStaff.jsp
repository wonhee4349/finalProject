<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Sidebar -->
		<ul
			class="navbar-nav bg-staff sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Divider -->
			<hr class="sidebar-divider my-0">
			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#schedule"
                    aria-expanded="true" aria-controls="collapsePages">
                    <span>행정 관리</span>
                </a>
                <div id="schedule" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                       
                        <a class="collapse-item" href="/staff/staffSchoolAffairsScheduleUI">학사 일정</a>
                        <a class="collapse-item" href="/staff/schoolaffairsschedule/staffSchoolAffairsScheduleInsertUI">학사일정 등록</a>
                        <a class="collapse-item" href="/staff/staffNoticeListUI">학사 공지</a>                      
                        <a class="collapse-item" href="/staff/staffCommonNoticeListUI">일반 공지</a>
                        <a class="collapse-item" href="/staff/board/staffNoticeInsertUI">공지 등록</a>

                    </div>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#scholarship"
                    aria-expanded="true" aria-controls="collapsePages">
                    <span>장학금 / 등록금 관리</span>
                </a>
                <div id="scholarship" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                       
                        <a class="collapse-item" href="/staff/scholarship/scholarshipListUI">장학금</a>
                        <a class="collapse-item" href="/staff/scholarshipStudent/scholarshipStudentListUI">장학생</a>
                        <a class="collapse-item" href="/staff/tuitionStudent/tuitionStudentListUI">등록금 고지</a>
                        <a class="collapse-item" href="/staff/tuitionPay/tuitionPayListUI">등록금 납부</a>
                    </div>
                </div>
            </li>	
			<li class="nav-item">
			    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#student"
			       aria-expanded="true" aria-controls="collapseUtilities">
			        <span>학생 관리</span>
			    </a>
			    <div id="student" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
			        <div class="bg-white py-2 collapse-inner rounded">
<!-- 			            <a class="collapse-item" href="javascript:;" id="studentList">재학생</a> 비동기 요청-->
			            <a class="collapse-item" href="/staff/freshMan/freshManUI">신입생 등록</a>
			            <a class="collapse-item" href="/staff/student/studentListUi">학생 조회</a>
			            <a class="collapse-item" href="/staff/graduation/graduationListUI">졸업생 조회</a>
			            <a class="collapse-item" href="/staff/absence/absenceRequestListUI">휴/복학 관리</a>
			            <a class="collapse-item" href="/staff/registerStatus/registerStatusListUI">학적 관리</a>
			              
			        </div>
			    </div>
			</li>	
             <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#staff"
                    aria-expanded="true" aria-controls="collapsePages">
                    <span>교직원 관리</span>
                </a>
                <div id="staff" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="/staff/professorListUI">교수</a>
                        <a class="collapse-item" href="/staff/professor/staffProfessorInsertUI">교수 등록</a>
                        <a class="collapse-item" href="/staff/staffListUI">교직원</a>
                        <a class="collapse-item" href="/staff/staff/staffStaffInsertUI">교직원 등록</a>
                    </div>
                </div>
            </li>	
             <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#lecture"
                    aria-expanded="true" aria-controls="collapsePages">
                    <span>강의 관리</span>
                </a>
                <div id="lecture" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                       
                        <a class="collapse-item" href="/staff/course/courseListUI">교과목</a>
                        <a class="collapse-item" href="/staff/course/courseRequestListUI">교과목 신청</a>
                        <a class="collapse-item" href="/staff/lecture/lectureListUI">개설강의</a>
                        <a class="collapse-item" href="/staff/lecture/lectureRequestListUI">개설강의 신청</a>
                    </div>
                </div>
            </li>	
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#counseling"
                    aria-expanded="true" aria-controls="consulting">
                    <span>상담 관리</span>
                </a>
                <div id="counseling" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                       
                        <a class="collapse-item" href="/staff/consultation/consultationUI">상담 내역</a>
                        <a class="collapse-item" href="/staff/consultation/consultationRequestListUI">상담신청 목록</a>
                    </div>
                </div>
            </li>	
             
             <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#club"
                    aria-expanded="true" aria-controls="collapsePages">
                    <span>동아리 관리</span>
                </a>
                <div id="club" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                       
                        <a class="collapse-item" href="/staff/club/clubListUI">동아리</a>
                        <a class="collapse-item" href="/staff/club/clubRequestListUI">동아리 개설신청 목록</a>
                    </div>
                </div>
            </li>	
<!--             <li class="nav-item"> -->
<!--                 <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#facilities" -->
<!--                     aria-expanded="true" aria-controls="collapsePages"> -->
<!--                     <span>시설물 관리</span> -->
<!--                 </a> -->
<!--                 <div id="facilities" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar"> -->
<!--                     <div class="bg-white py-2 collapse-inner rounded">                     -->
<!--                         <a class="collapse-item" href="/staff/facilitiesListUI">시설물</a>               -->
<!--                         <a class="collapse-item" href="/staff/facilities/staffFacilitiesInsertUI">시설물 등록</a> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </li> -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#retrieve"
                    aria-expanded="true" aria-controls="collapsePages">
                    <span>통계 조회</span>
                </a>
                <div id="retrieve" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">                    
                        <a class="collapse-item" href="/staff/retrieve/studentListOnSubject">학과별 재학생</a>              
                        <a class="collapse-item" href="/staff/retrieve/absenceStudentList">휴학생</a>              
                    </div>
                </div>
            </li>
		</ul>
<!-- <script src="/resources/js/app/msy/staffUI.js" ></script> -->
<!-- <script src="/resources/js/app/ljh/staffProfessorContent.js" ></script> -->
<!-- <script src="/resources/js/app/ljh/staffFacilitiesContent.js" ></script> -->
<!-- <script src="/resources/js/app/ljh/staffStaffContent.js" ></script> -->
<!-- <script src="/resources/js/app/ljh/staffCourseContent.js" ></script> -->
<!-- <script src="/resources/js/app/ljh/staffLectureContent.js" ></script> -->
<!-- <script src="/resources/js/app/ljh/staffSchoolAffairsScheduleContent.js" ></script> -->
