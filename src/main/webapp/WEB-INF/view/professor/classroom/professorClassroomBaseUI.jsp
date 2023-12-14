<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<security:authentication property="principal" var="principal" />

<style>
.box_tit01 {
	width: 100%;
	border-radius: 3px;
	text-align: center;
	line-height: 40px;
	height: 40px;
	background: #f3f3f3;
	border-top: 2px solid #b2c1d4;
	font-size: 18px;
	color: #6b8bb9;
}

.tit02 {
	font-size: 18px;
}

.textbox {
	min-height: 150px;
	overflow-y: scroll
}

.lectureroom {
	width: 50%;
	float: left;
}

.ft-left {
	float: left;
}

.wd50 {
	width: 50%;
}

#classroomLectureChoiceSelect {
	width: 420px;
}

.wd500{min-height:500px;}
</style>


<!-- 시험출제 모달 -->
<div class="modal fade" id="testInsertModal" tabindex="-1" role="dial`og"
	aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalLabel01">시험 등록</h5>
				<button id="testInsertModalClose" class="close" type="button" data-bs-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>

			</div>
			<div class="modal-body "></div>
		</div>
	</div>
</div>
<!-- 모달끝 -->

<!-- Begin Page Content -->
<div class="container-fluid">
	<div class="card2">
		<div class="sub_tit02">클래스룸</div>
		<div class="sub_top_wrap"></div>
		<div class="select-container">
			<select id="classroomLectureChoiceSelect"
				class="form-control bg-light border-0 small" aria-label="Search"
				aria-describedby="basic-addon2">
				<option value="-1">강의선택</option>
				<c:forEach items="${lectureList}" var="lecture">
					<option value="${lecture.lctreNo}">${lecture.courseNm}-
						(${lecture.semstrYr} 년 / ${lecture.semstr}학기 /
						${lecture.lctreSe} / ${lecture.complSe} )</option>
				</c:forEach>
			</select>
		</div>
		<br />

		<div class="class_list_wrap">
			<div class="list_top">
				<button id="dataBtn" class="gray_btn small_btn active"
					data-tab="tab-2">자료관리</button>
				<button id="assignmentBtn" class="gray_btn small_btn"
					data-tab="tab-1">과제관리</button>
				<button id="testBtn" class="gray_btn small_btn"
					data-tab="tab-3">시험관리</button>
<!-- 				<button id="onlieLectureBtn" class="gray_btn small_btn" -->
<!-- 					data-tab="tab-4">공지사항</button> -->
			</div>
		</div>
		<!-- -------------------------------여기서부터가 탭 내용 시작  --------------------------------------------------------- -->
		<div class="tab_contents" style="height:500px;">
			
			<!-- -------------------------------여기서부터가 탭 - 2  --------------------------------------------------------- -->
			<div id="tab-1-content" class="tab-content wd500">
				<div id="assignmentBody"></div>
			</div>
			<!-- -------------------------------여기서부터가 탭 - 3  --------------------------------------------------------- -->
			<div id="tab-2-content" class="tab-content wd500 current">
				<div id="dataBody"></div>
			</div>
			<!-- -------------------------------여기서부터가 탭 - 1  --------------------------------------------------------- -->
			<div id="tab-3-content" class="tab-content  wd500">
				<div id="testBody"></div>
			</div>
			<!-- -------------------------------여기서부터가 탭 - 4  --------------------------------------------------------- -->
<!-- 			<div id="tab-4-content" class="tab-content wd500"> -->
<!-- 				<div id="noticeBody"></div> -->
<!-- 			</div> -->
		</div>
	</div>
	
</div>

<script src="${pageContext.request.contextPath}/resources/js/app/kjs/professorClassroomBaseUI.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app/kjs/professorClassroomTestView.js"></script>