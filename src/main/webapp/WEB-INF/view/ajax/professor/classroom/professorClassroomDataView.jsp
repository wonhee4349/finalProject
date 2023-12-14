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

.w500{width:500px;}
</style>



<script>
// 문서가 로드된 후 실행
document.addEventListener("DOMContentLoaded", function() {
	// 모달 열기 버튼 클릭 시 모달 창 열기
	document.getElementById("openModal").addEventListener(
			"click",
			function() {
				var modal = new bootstrap.Modal(document
						.getElementById("openModal"));
				modal.show();
			});
	// 모달 닫기 버튼 클릭 시 모달 창 닫기
	document.querySelector(".close").addEventListener(
			"click",
			function() {
				var modal = bootstrap.Modal.getInstance(document
						.getElementById("openModal"));
				modal.hide();
			});
});
</script>

<!-- 자료 등록 모달 -->
<div class="modal fade" id="addDataBtnModal" tabindex="-1"
	role="dial`og" aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
	
		<form id="dataClassroomForm" action="/professor/classroom/Data/new" method="POST" enctype="multipart/form-data">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalLabel01">자료 등록</h5>
					<button class="close" type="button" data-bs-dismiss="modal" id="addDataBtnModalClose"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				
				<div class="pd50">
					<div class="sub_tit04">
						<span></span>자료 정보 입력
					</div>
					<input type="hidden" id="lctreNo" name="lctreNo" value="${lctreNo}" /> 
					<input type="hidden" id="dataCrSe" name="crSe" value="03"/>
					
					<table class="table table_style01 table_center" id="dataTable"
						width="100%" cellspacing="0">
						<tr>
							<th>제목</th>
							<td><input type="text" id="dataCrTitle" name="crTitle" class="form-style01 w500" required="required" /></td>
						</tr>
					</table>
				
					<div class="gray_box mt-4">
						<div class="white_bg">
							<input type="file" id="dataFile" name="dataFile">
						</div>
					</div>
					<div class="text-primary ft_right">등록 할 자료 파일을 등록 해주십시오.</div>
					<br />
					<div class="sub_tit04"><span></span>내용</div>
					<div>
						<textarea id="dataCrCn"
							name="crCn"
							class="form-control textbox" required></textarea>
					</div>
					<button class="btn btn-success mt-3" type="button" onclick="dataAutoInput()">일괄입력</button>
					<input type="submit" id="dataAddBtn" value="등록"class="btn btn-primary mt-4 ft_right" />
				</div>
				</div>
			<security:csrfInput/>	
		</form>
		
		<div class="dataModalBody "></div>

	</div>
</div>
<!-- 모달끝 -->

<!-- 자료 상세내역  모달 -->
<div class="modal fade" id="dataInfoViewModal" tabindex="-1" role="dial`og"
	aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalLabel01">자료 등록 정보</h5>
				<button id="dataInfoViewModalClose" class="close" type="button" data-bs-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>

			</div>
			<div class="dataInfoViewModalbody"></div>
		</div>
	</div>
</div>
<!-- 모달끝 -->




<div class="sub_top_wrap_pro">
	<div class="sub_tit05" style="width:70%;">
		<span></span>자료
	</div>
	<button class="btn btn-info ft_right" id="addDataModalViewBtn" data-toggle="modal" data-target="#addDataBtnModal">자료 등록</button>
	
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
	<tr>
		<th>년도</th>
		<th>학기</th>
		<th>제목</th>
		<th>강의구분</th>
		<th>이수구분</th>
		<th>등록일</th>
		<th>상세정보</th>
	</tr>
	</tr>
	</thead>
	<tbody id="dataListBody"></tbody>
</table>

<script src="${pageContext.request.contextPath}/resources/js/app/kjs/professorClassroomDataView.js"></script>