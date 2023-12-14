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
.search_bottom{float: right; width:400px;}
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
.wd200{width:200px !important;}
.ft-left{float:left !important;}
.mr10{margin-right:10px;}
</style>

<!-- Begin Page Content -->
<div class="container-fluid">

	<div class="card2">
                       <div class="sub_tit02">학사 공지</div>
                       <div id="searchUI" class="sub_top_search">					                         
							<!-- search -->
							<div class="search_bottom">
								<div class="s_option02 ft-left wd200 mr10">
									<span class="option_tit">제목</span>
									<input type="text" name="boTitle" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
								</div>
								
								<div class="s_option ft-left wd130">
									<span class="option_tit" style="width:100px;">작성자</span>
									<input type="text" name="sklstfNm" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
								</div>
								
								<div class="input-group-append">
									<button class="btn btn-primary" id="searchBtn" type="button">
										<i class="fas fa-search fa-sm" ></i>
									</button>
								</div>
							</div>			
						</div>

		
		<form action="<c:url value='/staff/board/ajax/staffNoticeList'/>"
			id="searchForm">
			<input type="hidden" name="boTitle"> 
			<input type="hidden"name="sklstfNm"> 
			<input type="hidden" name="page">
		</form>
		<div>
			<table class="table table_style01 mt-4 table_center" id="dataTable"
				width="100%" cellspacing="0">
				<thead>
				<colgroup>
					<col width="10%">
					<col width="40%">
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



<script src="/resources/js/app/ljh/staffNoticeData.js"></script>