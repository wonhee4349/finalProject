<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js'></script>
   <link href="${pageContext.request.contextPath }/resources/css/wh_style.css" rel="stylesheet">
<!-- <div class="main_top_content_wrap" style="height: 107px;">
	<security:authentication property="principal" var="user"/>
	<div class="welcom_txt" style="margin-top:10px;"><span class="userName">${user.realUser.sklstfNm} </span>님 안녕하세요.</div>
</div> -->
    <script>
        $(document).ready(function () {
            $('.class_list_wrap02 button').click(function () {
                var tab_id = $(this).attr('data-tab');
                $('.class_list_wrap02 button').removeClass('active');
                $('.tab-content').removeClass('current');
                $(this).addClass('active');
                $("#" + tab_id).addClass('current');
            });
        });
    </script>			
			
<!-- Begin Page Content -->
<div class="container-fluid mt-4">
		<div class="row">
				<div class="col-lg-6 mb-4">
					<div class="card shadow mb-4">
					<!-- 학사일정 -->
						<div class="card-header02 py-3">
							<p>학사일정</p>
							<span>학사일정을 확인하세요.</span>
						</div>
						<!-- 실제 화면을 담을 영역 -->
						<div class="card-body">
							<div id="Wrapper">
								<div id='calendar'></div>
							</div>
						</div>
					</div>	
				</div>
				
				<div class="col-lg-6 mb-4">
					<div class="list_item_wrap">
						<!-- 마이페이지 -->
						<div class="list_item" role="group" aria-label="2 / 4">
							<a href="/staff/mypage/staff/myPageUI">
								<div class="text">
									<strong class="tit">마이페이지</strong>
									<p class="txt1">마이페이지 바로가기입니다.</p>
								</div>
								<div class="bot">
									<div class="arrow"></div>
									<div class="icon">
										<img src="/resources/img/mail.png" alt="">
									</div>
								</div>
							</a>
						</div>
						<!-- 마이페이지 끝-->
						<!-- 쪽지 -->
						<div class="list_item bg01" role="group" aria-label="2 / 4">
							<a href="/staff/mail/mailList" style="color: white;">
								<div class="text">
									<strong class="tit">쪽지</strong>
									<p class="txt1">원하는 분에게 쪽지를 보내보세요.</p>
								</div>
								<div class="bot">
									<div class="arrow"></div>
									<div class="icon" style="top:-20px;">
										<img src="/resources/img/main_ico01.png" alt="">
									</div>
								</div>
							</a>
						</div>
						<!-- 쪽지끝 -->
						<!-- 상담 -->
						<div class="list_item bg02 mr-0" role="group" aria-label="2 / 4">
							<a href="/staff/consultation/consultationRequestListUI" style="color: white;">
								<div class="text">
									<strong class="tit">상담</strong>
									<p class="txt1">상담신청을 확인하세요.</p>
								</div>
								<div class="bot">
									<div class="arrow"></div>
									<div class="icon">
										<img src="/resources/img/main_ico02.png" alt="">
									</div>
								</div>
							</a>
						</div>
						<!-- 상담끝 -->
					</div>
					
			<!-- 공지탭 -->
			<div class="card02 shadow ">
				<div class="class_list_wrap02">
					<div class="list_top mb-4">
						<button class="gray_btn small_btn active" data-tab="tab-1">일반공지</button>
						<button class="gray_btn small_btn" data-tab="tab-2">학사공지</button>
					</div>
				</div>
			
					<div class="tab_contents ht400 scroll_none">
						<!-- 탭 1-->
						<div id="tab-1" class="tab-content current border_none pd-0">
						<a class="ft_right" href="/staff/staffCommonNoticeListUI" style="font-size:30px;">+</a>	
							<div class="mb-4"> 
										
										<div gs-id="comBoard" class="ht400" style="background-color: white;">
											
											<div style="padding-top: 10px;">
												<div class="chart-area" style="height: 200px;">
													<table class="table table_style01 table_center" id="dataTable"
														width="100%" cellspacing="0">
														<thead>
														<colgroup>
															<col width="10%">
															<col width="50%">
															<col width="20%">
															<col width="10%">
														</colgroup>
														<tr>
															<th>번호</th>
															<th>제목</th>
															<th>작성일</th>
															<th>조회수</th>
														</tr>
														</thead>
														<tbody id="comBoard-body"></tbody>
													</table>
												</div>
											</div>
										</div>
							</div>
						</div>
						<!-- 탭 1//-->
						<!-- 탭 2-->
						<div id="tab-2" class="tab-content border_none pd-0 ">
								<div class="mb-4">
									<div gs-id="noticeBoard" class="ht400" style="background-color: white;">
											<a class="ft_right" href="/staff/staffNoticeListUI"  style="font-size:30px;">+</a>
										<div  style="padding-top: 10px;">
											<div class="chart-area" style="height: 200px;">
												<table class="table table_style01 table_center" id="dataTable"
													width="100%" cellspacing="0">
													<thead>
													<colgroup>
														<col width="10%">
														<col width="50%">
														<col width="20%">
														<col width="10%">
													</colgroup>
													<tr>
														<th>번호</th>
														<th>제목</th>
														<th>작성일</th>
														<th>조회수</th>
													</tr>
													</thead>
													<tbody id="noticeBoard-body"></tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
						</div>
						<!-- 탭 2//-->
					</div>
				</div>	
			</div>
				
		</div>
		
		
			
		
		
		
		
		
	</div>

				<!-- /.container-fluid -->
				
				
<script src="${pageContext.request.contextPath }/resources/js/app/kwh/staffMain.js"></script>

				