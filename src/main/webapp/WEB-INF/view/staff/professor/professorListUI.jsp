<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
#btn1 {
	width: 50px;
	height: 10px;
	padding-bottom: 30px;
}

.search_top{height:60px; float:left;}
.search_bottom{float: right;}
.wd130{width: 152px !important;}
.s_option02{width: auto;float: left;height: 44px; display: flex;}
.select-container {width: 109px; position: relative;}
.custom-select02 {
	appearance: none;
	-webkit-appearance: none;
	-moz-appearance: none;
	width: 123px;
	padding: 6px;
	border: 1px solid #ced5e9;
	border-radius: 4px;
	background-color: #fff;
	cursor: pointer;
}
.select-arrow {
	position: relative;
	float: right;
	top: -31px;
	right: 0;
}
.custom-select02:active,.custom-select02:focus {border-color: #007bff;}
.custom-select02 option {padding: 10px;}
.option_tit{font-size: 18px;width: 45px;display: block;float: left;line-height: 39px;color: #000;font-weight: 100;}
.pagination{ justify-content: center;}
</style>

<!-- 모달 창 추가 -->
<div class="modal fade" id="modal_open" tabindex="-1" role="dialog"
	aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalLabel01">교수 정보</h5>
				<button class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>

			</div>
			<div class="modal-body "></div>
		</div>
	</div>
</div>




<!-- Begin Page Content -->
<div class="container-fluid">

	 					 <div class="card2">
                       <div class="sub_tit02">교수</div>
                       <div id="searchUI" class="sub_top_search">					                         
							<!-- search -->
							<div class="search_top">
								<div class="s_option02 ft-left wd130">
									<span class="option_tit">교번</span>
									<input type="text" name="prfsorNo" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
								</div>
								
								<div class="s_option ft-left wd130">
									<span class="option_tit">이름</span>
									<input type="text" name="prfsorNm" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
								</div>
							</div>			
							
							<div class="search_bottom">
								<div class="s_option02 mr-4 ft-left">
									<span class="option_tit">학과</span>
									<div class="select-container">
										<select id="subjctNo" class="custom-select02 searchFormUIInput" name="subjctNo">
											<option value="" label="전체" />
											<c:forEach items="${subject}" var="sub">
												<option class="clg${sub.clgNo} clgSubject" value="${sub.subjctNo}" label="${sub.subjctNm}"></option>
											</c:forEach>
										</select>
										<div class="select-arrow">
											<i class="fa fa-caret-down"></i>
										</div>
									</div>
								</div>
								
								<div class="s_option02 mr-4 ft-left">
									<span class="option_tit" style="width:80px;">고용 형태</span>
									<div class="select-container">
										<select class="custom-select02 searchFormUIInput" name="prfsorEmplynForm">
											<option value="" label="전체">
											<c:forEach items="${emplySe}" var="emp">
												<option value="${emp.comCode}" label="${emp.comCodeNm}">
											</c:forEach>
										</select>
										<div class="select-arrow">
											<i class="fa fa-caret-down"></i>
										</div>
									</div>
								</div>
								
								<div class="s_option02 mr-4 ft-left">
									<span class="option_tit">성별</span>
									<div class="select-container">
										<select class="custom-select02 searchFormUIInput" name="sexdstnSe">
											<option value="" label="전체">
											<c:forEach items="${genderSe}" var="gen">
												<option value="${gen.comCode}" label="${gen.comCodeNm}">
											</c:forEach>
										</select>
										<div class="select-arrow">
											<i class="fa fa-caret-down"></i>
										</div>
									</div>
								</div>
								
								<div class="s_option02 mr-4 ft-left">
									<span class="option_tit">국적</span>
									<div class="select-container">
										<select class="custom-select02 searchFormUIInput" name="prfsorNlty">
											<option value="" label="전체">
											<c:forEach items="${nltySe}" var="nlty">
												<option value="${nlty.comCode}" label="${nlty.comCodeNm}">
											</c:forEach>
										</select>
										<div class="select-arrow">
											<i class="fa fa-caret-down"></i>
										</div>
									</div>
								</div>
								
								<div class="input-group-append">
									<button class="btn btn-primary" id="searchBtn" type="button">
										<i class="fas fa-search fa-sm" ></i>
									</button>
								</div>
							</div>


		</div>
		<form action="<c:url value='/staff/professor/ajax/professorList'/>" id="searchForm">
							<input type="hidden" name="prfsorNo">
							<input type="hidden" name="prfsorNm">
							<input type="hidden" name="subjctNo">
							<input type="hidden" name="prfsorEmplynForm">
							<input type="hidden" name="sexdstnSe">
							<input type="hidden" name="prfsorNlty">
							<input type="hidden" name="page">
		</form>
		<div>
			<table class="table table_style01 mt-4 table_center" id="dataTable"
				width="100%" cellspacing="0">
				<thead>
				<colgroup>
					<col width="10%">
					<col width="10%">
					<col width="20%">
					<col width="20%">
					<col width="10%">
					<col width="10%">
				</colgroup>
				<tr>
					<th>번호</th>
					<th>교번</th>
					<th>교수명</th>
					<th>학과</th>
					<th>성별</th>
					<th>국적</th>
				</tr>
				</thead>
				<tbody id="listBody"></tbody>

			</table>



			<div aria-label="Page navigation example nav_center">
				<span id="pagingArea"></span>
			</div>

		</div>
	</div>



</div>

<script src="/resources/js/app/ljh/staffProfessorData.js"></script>