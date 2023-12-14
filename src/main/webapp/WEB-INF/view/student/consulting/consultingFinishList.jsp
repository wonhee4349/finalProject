<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<style>
.pstatus01 {
	width: 100px;
	border-radius: 100px;
	text-align: center;
	background: #f2f2f2;
	display: block;
	color: #4b4b4b;
	border: 2px solid #c3c3c3;
	margin: 0 auto;
}

.pstatus02 {
	width: 100px;
	border-radius: 100px;
	text-align: center;
	background: #eaf7fd;
	display: block;
	color: #2547af;
	border: 2px solid #c9d7ff;
	margin: 0 auto;
}

.pstatus03 {
	width: 100px;
	border-radius: 100px;
	text-align: center;
	background: #ffe7e7;
	display: block;
	color: #d53131;
	border: 2px solid #f39292;
	margin: 0 auto;
}

#wonhee_modal {
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	height: 100%;
	z-index: 999999;
	display: none;
	background: #00000070;
}

#modal_cont {
	width: 1140px;
	min-height: 500px;
	position: relative;
	display: flex;
	flex-direction: column;
	pointer-events: auto;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 0.3rem;
	outline: 0;
	margin: 0 auto;
	top: 150px;
}

.txt_box {
	width: 100%;
	min-height: 200px;
	overflow-y: scroll;
	border: 1px solid #f2f2f2;
}

.in_txt {
	border: none;
}

.wd85 {
	width: 85%;
	margin: 0 auto;
	border:none;
}
.text_min{    width: 400px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    display: block;}
.text_area{width:100%; min-height:250px; padding:20px;}
.sel_tit{width:67px; float:left; line-height:37px;}
</style>
<!-- 모달 창 추가 -->
<div id="wonhee_modal">
	<div id="modal_cont">
		<div class="modal-header">
			<div class="modal-title">
				<h5>상담내역</h5>
			</div>
			<button onclick="fWhModalHide()" class="close">×</button>
		</div>


		<div class="modal-body wd85 mt-4">
			<table class="table table_style01 table_center hover_none">
				<tr>
					<th>상담시간</th>
					<td><input class="in_txt" type="text" name="" id="" whTime><br>
					<th>희망상담일</th>
					<td><input class="in_txt" type="text" name="" id="" whDate><br>
					</td>
				</tr>
			</table>
			<br>
			<div class="sub_tit04">
				<span></span>상담내용
			</div>
			<div class="txt_box text_left">
				<textarea class="text_area" type="text" name="" id="" whCont></textarea>
			</div>
		</div>
	</div>

</div>

<!-- 모달끝 -->






<!-- Begin Page Content -->
<div class="container-fluid">

	<div class="card2">
		<div class="class_list_wrap">
			<div class="sub_tit02">상담 내역</div>

		</div>

		<div class="ft_left" style="width: 40%; margin: 8px;">
			<div class="sel_tit">상담구분</div>
			<div class="select-container ft_left">
				<select name="cnsltSe" id="searchFormCnsltSe"
					class="custom-select02" onchange="whSelChg(this)">
					<option value="allList">전체</option>
					<option value="학사상담" label="학사상담"></option>
					<option value="취업상담" label="취업상담"></option>
				</select>
				<div class="select-arrow">
					<i class="fa fa-caret-down"></i>
				</div>
			</div>
		</div>
		<!-- 상담신청내역 테이블 -->
		<div id="searchUI" class="sub_top_wrap">
			<!-- selectbox -->
			<div class="s_option">


				<div class="select-container">
					<select name="searchType" class="custom-select02">
						<option value="" label="전체" />

					</select>
					<div class="select-arrow">
						<i class="fa fa-caret-down"></i>
					</div>
				</div>
			</div>

			<!-- search -->
			<div
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
		<form action="<c:url value='/student/consulting/consultingFinishList'/>"id="searchForm">
			<input type="hidden" id="searchFormsearchType" name="searchType"value="${param.searchType}" /> <input type="hidden"
				id="searchFormSearchWord" name="searchWord"
				value="${param.searchWord}" /> <input type="hidden"
				id="searchFormPage" name="page" value="${param.page}" />
		</form>
		<div>
			<table class="table table_style01 mt-4 table_center hover_none" id="dataTable" width="100%" cellspacing="0">
				<colgroup>
					<col width="10%">
					<col width="50%">
					<col width="20%">
					<col width="15%">
				</colgroup>
				
				<thead>
				
				<tr>
					<th>상담구분</th>
					<th>상담내용</th>
					<th>상담일</th>
					<th>상담시간</th>
				</tr>
				</thead>
				<tbody id="listBody">

				</tbody>

			</table>

			<div class="clear"></div>
			<div aria-label="Page navigation example nav_center">
				<span id="pagingArea"></span>
			</div>
			<a href="/student/consulting/consultingSubmit"><button class="btn btn-primary ft_right">상담신청</button></a>
		</div>	
	</div>


</div>

<script src="/resources/js/app/kwh/studentConsultationFinishList.js"></script>