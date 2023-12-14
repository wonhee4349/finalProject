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
.w500{width:500px !important;}
.pro_btn_01{border-radius: 100px;
    border: 2px solid #b0e5ed;
    padding: 1px 20px;
    color: #36b9cc;
}
</style>

<!-- 과제 상세내역  모달 -->
<div class="modal fade" id="assignmentInfoViewModal" tabindex="-1" role="dial`og"
	aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalLabel01">과제</h5>
				<button id="assignmentInfoViewModalClose" class="close" type="button" data-bs-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>

			</div>
			<div class="assignmentInfoViewModalbody"></div>
		</div>
	</div>
</div>
<!-- 모달끝 -->


<!-- 과제 등록 모달 -->
<div class="modal fade" id="addAssignmentBtnModal" tabindex="-1"
	role="dial`og" aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
	
		<form id="addAssignmentClassroomForm" action="/professor/classroom/assignment/new" method="POST" enctype="multipart/form-data">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalLabel01">과제 등록</h5>
					<button class="close" id="addAssignmentBtnModalClose" type="button" data-bs-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<br />
				<div class="pd50">
				<div class="sub_tit04">
					<span></span>과제 정보 입력
				</div>
				
				<input type="hidden" id="lctreNo" name="lctreNo" value="${lctreNo}" />
				<input type="hidden" id="assignmentCrSe" name="crSe" value="01"/>
				 
				<table class="table table_style03 table_center hover_none" id="dataTable"
					width="100%" cellspacing="0">'
					<colgroup>
						<col width="30%">
					</colgroup>
					<tr>
						<th>제목</th>
						<td><input type="text" id="assignmentCrTitle" name="crTitle" class="form-style01 w500" /></td>
					</tr>
				</table>
				
				<div class="gray_box mt-4">
					<div class="white_bg">
						<input type="file" id="assignmentFile" name="assignmentFile">
					</div>
				</div>
				<div class="text-primary ft_right">
					등록 할 과제파일을 등록 해주십시오.
				</div>				
				<br />
				<div class="sub_tit04">
					<span></span>과제 내용
				</div>
				<div>
					<textarea id="assignmentCrCn"
						name="crCn"
						class="form-control textbox"></textarea>
				</div>
				<button class="btn btn-success mt-3" type="button" id="autoResultLectureRequest" onclick="assignmentAutoInput()">일괄입력</button>
				<input type="submit" id="assignmentAddBtn" value="등록"class="btn btn-primary ft_right mt-2" />
				</div>
			<security:csrfInput/>
			</div>	
		</form>

		<div class="assingmentModalBody "></div>
	</div>
</div>
<!-- 모달끝 -->


<div class="sub_top_wrap_pro">
	<div class="sub_tit05" style="width:70%;">
		<span></span>과제</div>
	<button class="btn btn-info ft_right" id="addAssignmentModalViewBtn" data-toggle="modal" data-target="#addAssignmentBtnModal">과제 등록</button>
</div>

<table class="table table_style01 mt-4 table_center" id="dataTable"
	width="35%" cellspacing="0">
	<thead>
	<colgroup>
		<col width="10%">
		<col width="10%">
		<col width="40%">
		<col width="10%">
		<col width="10%">
		<col width="10%">
	</colgroup>
	<tr>
		<th>년도</th>
		<th>학기</th>
		<th>제목</th>
		<th>강의구분</th>
		<th>이수구분</th>
		<th>등록일</th>
		<th>상세정보</th>
	</tr>
	</thead>
	<tbody id="assingmentListBody"></tbody>
</table>

<script src="${pageContext.request.contextPath}/resources/js/app/kjs/professorClassroomAssignmentView.js"></script>