<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Divider -->
			<hr class="sidebar-divider my-0">
			<!-- Divider -->
			<hr class="sidebar-divider">



			<!-- Nav Item - Utilities Collapse Menu -->

            <!-- 공지사항 -->
			<li class="nav-item">
				<a class="nav-link collapsed contentLink" data-link="" href="/student/board/studentCommonNoticeList" >
					<span>공지사항</span>
				</a>
			</li>
			
			
			
			<!-- 수강신청 -->
			 <li class="nav-item">
                <a class="nav-link collapsed contentLink" data-link="" href="/student/classApplication" >
                    <span>수강신청</span>
                </a>
            </li>
            
            <!-- 강의 -->	
			<li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse"  data-target="#class"
                    aria-expanded="true" aria-controls="collapsePages">
                    <span>수업</span>
                </a>
                <div id="class" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item contentLink" data-link="" href="/student/class">강의조회</a>
                        <a class="collapse-item contentLink" data-link="" href="/student/courseEvaluation">강의평가</a>
                    </div>
                </div>
            </li>
            
            <!-- 클래스룸 -->
            <li class="nav-item">
                <a class="nav-link collapsed contentLink" data-link="" href="/student/classroom" >
                    <span>클래스룸</span>
                </a>
            </li>
            
            <!-- 성적 -->
			<li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse"  data-target="#score"
                    aria-expanded="true" aria-controls="collapsePages">
                    <span>성적</span>
                </a>
                <div id="score" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item contentLink" data-link="" href=/student/currScore>금학기성적조회</a>
                        <a class="collapse-item contentLink" data-link="" href="/student/totalScore">전체성적조회</a>
                    </div>
                </div>
            </li>
            
            
            <!-- 학적관리 메뉴 -->
			<li class="nav-item">
			    <a class="nav-link collapsed" href="#"  data-target="#academic" data-toggle="collapse" aria-expanded="true" aria-controls="collapseUtilities">
			        <span>학적관리</span>
			    </a>
			    <div id="academic" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
			        <div class="bg-white py-2 collapse-inner rounded">
			            <a class="collapse-item contentLink" data-link="" href="/student/vacRequest">휴학신청</a>
			            <a class="collapse-item contentLink" data-link="" href="/student/backRequest">복학신청</a>
			            <a class="collapse-item contentLink" data-link="" href="/student/vacRequestListUI">휴학신청현황조회</a>
			            <a class="collapse-item contentLink" data-link="" href=/student/subjectRequest>복수전공/부전공/전과신청</a>
			            <a class="collapse-item contentLink" data-link="" href="/student/subjectRequestList">학적변동신청내역조회</a>
			        </div>
			    </div>
			</li>
            
            <!-- 상담 -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse"  data-target="#consulting"
                    aria-expanded="true" aria-controls="consulting">
                    <span>상담</span>
                </a>
                <div id="consulting" class="collapse" aria-labelledby="headingPages" data-toggle="collapse" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                       
                        <a class="collapse-item contentLink" data-link="" href="/student/consulting/consultingList">상담신청</a>
                        <a class="collapse-item contentLink" data-link="" href="/student/consulting/consultingFinishList">상담내역</a>
                    </div>
                </div>
            </li>	
            
            <!-- 동아리 -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse"  data-target="#club"
                    aria-expanded="true" aria-controls="club">
                    <span>동아리</span>
                </a>
                <div id="club" class="collapse" aria-labelledby="headingPages" data-toggle="collapse" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                       
                        <a class="collapse-item contentLink" data-link="" href="/student/club/clubList">동아리</a>
                        <a class="collapse-item contentLink" data-link="" href="/student/club/clubOpen">동아리개설</a>
                        <a class="collapse-item contentLink" data-link="" href="/student/club/clubRequestList">동아리개설내역</a>
                    </div>
                </div>
            </li>	
<%--
 사용하지 않을 메뉴바 주석처리
 2023.12.09 김석호
             <li class="nav-item">
                <a class="nav-link collapsed contentLink" data-link="" href="/student/facilities" >
                    <span>시설물</span>
                </a>
            </li>	
--%>
			<!-- 장학금 -->
             <li class="nav-item">
             	<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#scholarships"
                    aria-expanded="true" aria-controls="collapsePages">
                    <span>장학제도</span>
                </a>
                <div id="scholarships" class="collapse" aria-labelledby="headingPages" data-toggle="collapse" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item contentLink" data-link="" href=/student/scholarships>장학금신청</a>
                        <a class="collapse-item contentLink" data-link="" href="/student/scholarshipStatus">장학금신청내역/수혜내역</a>
                    </div>
                </div>
            </li>
            
            <!-- 등록금 -->
            <li class="nav-item">
                <a class="nav-link collapsed contentLink" data-link="" href="/student/tuition" >
                    <span>등록금</span>
                </a>
            </li>
            
            
            <!-- 증명서발급 -->
            <li class="nav-item">
                <a class="nav-link collapsed contentLink" data-link="" href="/student/certificate/certificateDown" >
                    <span>증명서발급</span>
                </a>
            </li>
   
		</ul>
		
		
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/app/ksh/studentMenubar.js"> --%>

</script>
		
		
		