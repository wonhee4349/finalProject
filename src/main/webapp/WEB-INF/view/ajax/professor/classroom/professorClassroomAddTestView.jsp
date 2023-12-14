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
.pd50{padding:50px;}
</style>

<div class="pd50">

		<form id="testAddForm" name="testAddForm" action="/professor/classrooom/test/new" method="POST" enctype="multipart/form-data">
		<security:authorize access="isAuthenticated()">
			<security:authentication property="principal.realUser" var="realUser" />
		</security:authorize>
			<input type="hidden" name="lctreNo" id="addTestLctreNo" value="${lctreNo}"/>
			<div class="sub_tit04"><span></span>시험구분 선택</div>
			<select id="classroomLectureAddTestSelect" name="testSe" class="form-control bg-light border-0 small" 
			aria-label="Search" aria-describedby="basic-addon2">
			      <option value="-1">시험구분</option>
			      <c:forEach items="${testSe}" var="test">
			      		<option value="${test.comCode}">${test.comCodeNm }</option>
			      </c:forEach>
			</select>
			<br/>
			<div class="sub_tit04"><span></span>시험 제한시간 입력</div>
			<input type="number" id="testQustTime" name="testQustTime" placeholder="분단위 입력(ex. 20,30,60 Minutes)" class="form-control bg-light border-0 small" aria-describedby="basic-addon2"/>
			<br/><br/><br/>
							           
			<div class="sub_tit04"><span></span>등록 할 시험 파일을 등록 해주십시오. (pdf 파일 형식)</div>
			<div class="gray_box mt-4">
			<div class="white_bg"><input type="file" id="testFile" name="testFile"></div>
			</div>
			<br/>
			<div class="sub_tit04"><span></span>등록 할 답안 파일을 등록 해주십시오. (excel 파일 형식)</div>
			<div class="gray_box mt-4">
			<div class="white_bg"><input type="file" id="answerFile" name="answerFile"></div>
			</div>
			
			<input type="submit" class="btn btn-primary ft_right mt-3" value="등록"/>
		<security:csrfInput/>
		</form>
</div>