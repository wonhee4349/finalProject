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

#scoreLectureChoiceSelect {
	width: 420px;
}
.min500{min-height:500px;}
</style>

<!-- Begin Page Content -->
<div class="container-fluid">
	<div class="card2">
		<div class="sub_tit02">성적 관리</div>
		<div class="sub_top_wrap"></div>

		<div class="select-container">
			<select id="scoreLectureChoiceSelect"
				class="form-control bg-light border-0 small" aria-label="Search"
				aria-describedby="basic-addon2">
				<option value="-1">강의선택</option>
				<c:forEach items="${lectureList}" var="lecture">
					<option value="${lecture.lctreNo}">${lecture.courseNm}-
						(${lecture.semstrYr}년 / ${lecture.semstr} 학기 /
						${lecture.lctreSe} / ${lecture.complSe} )</option>
				</c:forEach>
			</select>
		</div>
		<br />
		
		<div class="class_list_wrap">
			<div class="list_top">
				<button id="testBtn" class="gray_btn small_btn active"
					data-tab="tab-1">성적 등록</button>
				<button id="assignmentBtn" class="gray_btn small_btn"
					data-tab="tab-2">성적 이의신청</button>
			</div>
		</div>
		<!-- -------------------------------여기서부터가 탭 내용 시작  --------------------------------------------------------- -->
		<div class="tab_contents" style="height: 500px;">
			
			<!-- -------------------------------여기서부터가 탭 - 1  --------------------------------------------------------- -->
			<div id="tab-1-content" class="tab-content current min500">
				<div id="scoreRegistrationBody"></div>
			</div>
			<!-- -------------------------------여기서부터가 탭 - 2  --------------------------------------------------------- -->
			<div id="tab-2-content" class="tab-content min500">
				<div id="scoreObjectionBody"></div>
			</div>
		</div>
	</div>
	
</div>
<script src="${pageContext.request.contextPath}/resources/js/app/kjs/professorScoreRegistrationUI.js"></script>