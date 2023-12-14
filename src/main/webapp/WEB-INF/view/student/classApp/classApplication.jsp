<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 해당 컨텐츠를 옵저버가 감지하기 위해 만들어놓은 div -->
<div id="thisIsAttendanceLecturePage"></div>
<security:authentication property="principal" var="principal"/>
<c:if test="${not empty message }">
	<script>
		var timerInterval;
		Swal.fire({
			title: "사용할 수 없는 메뉴",
			html: "${message}",
			timer: 2000,
			timerProgressBar: true,
			didOpen: () => {
				Swal.showLoading();
				const timer = Swal.getPopup().querySelector("b");
				timerInterval = setInterval(() => {
					timer.textContent = `${Swal.getTimerLeft()}`;
				}, 100);
			},
			willClose: () => {
				clearInterval(timerInterval);
			}
			}).then((result) => {
				location.href = "/student";
			});
	</script>
</c:if>
<c:if test="${empty message }">
    <script>
	var currStdntNo = '${principal.realUser.stdntNo }';
        $(document).ready(function () {
            $('.class_list_wrap button').click(function () {
                var tab_id = $(this).attr('data-tab');
                $('.class_list_wrap button').removeClass('active');
                $('.tab-content').removeClass('current');
                $(this).addClass('active');
                $("#" + tab_id).addClass('current');
            });
        });
    </script>
<style>
.wd100{width:100% !important;}
#searchUI{display:flex;}
.option_tit{width:auto; margin-right:5px;}
.lectureSearchList td:nth-child(1){text-align: left; padding-left : 10px !important;}
.lectureSearchList td:nth-child(7){text-align: left; padding-left : 10px !important;}
.lectureSearchList td:nth-child(8){text-align: left; padding-left : 10px !important;}
.mylecturelist td:nth-child(1){text-align: left; padding-left : 10px !important;}
.mylecturelist td:nth-child(5){text-align: left; padding-left : 10px !important;}
.mylecturelist td:nth-child(6){text-align: left; padding-left : 10px !important;}
.custom-select04{width:124px !important;}
.w40{width:44px;}
.w50{width:54px;}
.w60{width:64px;}
</style>    

<!-- Begin Page Content -->
<div class="container-fluid">

	<div class="card2">
		<div class="sub_tit02">수강신청 - ${principal.realUser.stdntNm } 학생 수강신청 가능 학점 : [ <span id="requestablePointArea"></span> 학점 ]</div>
		<div class="sub_top_wrap wd100">
			<form id="searchUI" class="wd100">
				<!-- search -->
				<div class="s_option mr-4 ft-left">
					<span class="option_tit w60">교과구분</span>
					<div class="select-container">
						<select class="custom-select02 searchFormUIInput" name="complSe">
							<option value label="전체" />
							<c:forEach items="${complSeList }" var="complSe">
								<option value="${complSe.KEY }" label="${complSe.VALUE }" />
							</c:forEach>
						</select>
						<div class="select-arrow">
							<i class="fa fa-caret-down"></i>
						</div>
					</div>
				</div>
				<div class="s_option mr-4 ft-left">
					<span class="option_tit w40">학점</span>
					<div class="select-container">
						<select class="custom-select02 searchFormUIInput" name="coursePnt">
							<option value label="전체" />
							<c:forEach items="${coursePntList }" var="coursePnt">
								<option value="${coursePnt.KEY }" label="${coursePnt.VALUE }" />
							</c:forEach>
						</select>
						<div class="select-arrow">
							<i class="fa fa-caret-down"></i>
						</div>
					</div>
				</div>
				<div class="s_option mr-4 ft-left" >
					<span class="option_tit w60 ">강의요일</span>
					<div class="select-container">
						<select class="custom-select02 searchFormUIInput" name="courseDayOfWeek">
							<option value label="전체" />
							<c:forEach items="${dayOfWeekList }" var="dayOfWeek">
								<option value="${dayOfWeek.KEY }" label="${dayOfWeek.VALUE }" />
							</c:forEach>
						</select>
						<div class="select-arrow">
							<i class="fa fa-caret-down"></i>
						</div>
					</div>
				</div>
				<div class="s_option mr-4 ft-left" >
					<span class="option_tit w50">단과대</span>
					<div class="select-container">
						<select id="clg" class="custom-select02 mr10 searchFormUIInput" name="clgNo">
							<option value label="전체"/>
							<c:forEach items="${collegeList }" var="college">
								<option value="${college.clgNo }" label="${college.clgNm }"/>
							</c:forEach>
						</select>
						<div class="select-arrow">
							<i class="fa fa-caret-down"></i>
						</div>
					</div>
				</div>
				<div class="s_option mr-4 ft-left" >
					<span class="option_tit w40">학과</span>
					<div class="select-container">
						<select name="subjctNo" id="subjctNo" class="custom-select02 searchFormUIInput">
							<option value label="선택" />
							<option class="clgSubject" value="00" label="교양과목" />
							<c:forEach items="${subjectList }" var="subject">
								<option class="clg${subject.clgNo } clgSubject" value="${subject.subjctNo }" label="${subject.subjctNm }" />
							</c:forEach>
						</select>
						<div class="select-arrow">
							<i class="fa fa-caret-down"></i>
						</div>
					</div>
				</div>
				<div class="input-group wd300 ft-left mr-2">
				<span class="option_tit">강의명</span>
					<input type="text" class="form-control bg-light border-0 small searchFormUIInput" name="courseNm"
						aria-label="Search" aria-describedby="basic-addon2"  >
				</div>
				<div class="input-group wd300  ft-left">
				<span class="option_tit">교수명</span>
					<input type="text" class="form-control bg-light border-0 small searchFormUIInput" name="prfsorNm"
						aria-label="Search" aria-describedby="basic-addon2">
					<div class="input-group-append" style="height:38px;">
						<button class="btn btn-primary lectureSearchBtn" type="button">
							<i class="fas fa-search fa-sm"></i>
						</button>
					</div>
				</div>
				<input  type="hidden" name="currentPage" id="currentPage" />
			</form>
		</div>
		<div>
			<table class="table table_style01 mt-4 table_center" id="lectureListTable"
				width="100%" cellspacing="0">
				<colgroup>
					<col width="20%">
					<col width="10%">
					<col width="5%">
					<col width="7%">
					<col width="8%">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					<col width="20%">
				</colgroup>
				<tr>
					<th>강의명</th>
					<th>교과구분</th>
					<th>학점</th>
					<th>담당교수</th>
					<th>학과</th>
					<th>현재원 / 정원</th>
					<th>강의실</th>
					<th>시간표</th>
					<th>신청하기</th>
				</tr>
				<tbody id="lectureListBody"></tbody>
			</table>



			<!-- 강의목록 페이징 위치 -->
			<div aria-label="Page navigation example nav_center" id="lectureListPaginationArea">
			</div>

			<!-- 수강신청 확인 -->
			<div class="class_list_wrap">
				<div class="list_top">
					<button class="gray_btn small_btn active" data-tab="tab-1">수강신청</button>
					<button class="gray_btn small_btn" data-tab="tab-2">예비신청</button>
				</div>
			</div>


			<div class="tab_contents">
				<div id="tab-1" class="tab-content current">
				<!-- 신청목록 테이블 -->
				<table class="table table_style01 mt-4 table_center" id="requestedLectureListTable"
					width="100%" cellspacing="0">
					<colgroup>
						<col width="30%">
						<col width="10%">
						<col width="5%">
						<col width="10%">
						<col width="15%">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<tr>
						<th>강의명</th>
						<th>교과구분</th>
						<th>학점</th>
						<th>담당교수</th>
						<th>강의실</th>
						<th>시간표</th>
						<th>수강취소</th>
					</tr>
					<tbody id="requestLectureListBody"></tbody>
	
				</table>
				
				</div>
				<div id="tab-2" class="tab-content">
				 	<table class="table table_style01 mt-4 table_center" id="prepareLectureListTable"
						width="100%" cellspacing="0">
				<colgroup>
					<col width="25%">
					<col width="10%">
					<col width="5%">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					<col width="20%">
				</colgroup>
				<tr>
					<th>강의명</th>
					<th>교과구분</th>
					<th>학점</th>
					<th>담당교수</th>
					<th>현재원 / 정원</th>
					<th>강의실</th>
					<th>시간표</th>
					<th>신청하기</th>
				</tr>
				<tbody id="prepareLectureListBody"></tbody>
			</table>
				</div>
			</div>
			
	</div>



</div>
<!-- /.container-fluid -->

<script src="/resources/js/app/ksh/studentLectureRequest.js"></script>
</c:if>