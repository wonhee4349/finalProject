<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
#btn1 {
	width: 50px;
	height: 10px;
	padding-bottom: 30px;
}
</style>

<!-- Begin Page Content -->
<div class="container-fluid">

	<div class="card2">
		<div class="sub_tit02">일반 공지</div>
		<div class="sub_top_wrap">

			<!-- selectbox -->
			<div class="s_option">
				<div class="select-container">
					<select class="custom-select02">
						<option value="" label="전체" />
						<option value="boTitle" label="제목" />
						<option value="boWrter" label="작성자" />
					</select>
					<div class="select-arrow">
						<i class="fa fa-caret-down"></i>
					</div>
				</div>
			</div>

			<!-- search -->
			<div id="searchUI"
				class="d-none d-sm-inline-block  ml-md-3 my-2 my-md-0 navbar-search">
				<div class="input-group wd300 ft_right">
					<input type="text" name="searchWord"
						class="form-control bg-light border-0 small" aria-label="Search"
						aria-describedby="basic-addon2">
					<div class="input-group-append">
						<button class="btn btn-primary" id="searchBtn" type="button">
							<i class="fas fa-search fa-sm"></i>
						</button>
					</div>
				</div>
			</div>

		</div>
		<form action="<c:url value='/professor/board/ajax/professorCommonNoticeList'/>"
			id="searchForm">
			<input type="hidden" name="searchType"> <input type="hidden"
				name="searchWord"> <input type="hidden" name="page">
		</form>
		<div>
			<table class="table table_style01 mt-4 table_center" id="dataTable"
				width="100%" cellspacing="0">
				<thead>
				<colgroup>
					<col width="10%">
					<col width="20%">
					<col width="20%">
					<col width="10%">
					<col width="10%">
					<col width="20%">
				</colgroup>
				<tr>
					<th>번호</th>
					<th>글제목</th>
					<th>작성자</th>
					<th>구분</th>
					<th>작성날짜</th>
					<th>조회수</th>
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

<script src="/resources/js/app/ljh/professorCommonNoticeData.js"></script>