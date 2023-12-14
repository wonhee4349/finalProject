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
.sub_top_wrap_pro{width:100%; height:38px;}


</style>

<!-- 시험출제 모달 -->
<div class="modal fade" id="testInfoViewModal" tabindex="-1" role="dial`og"
	aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalLabel01">시험출제 정보</h5>
				<button id="testInfoViewModalClose" class="close" type="button" data-bs-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>

			</div>
			<div class="testInfoViewModalbody "></div>
		</div>
	</div>
</div>

<!-- 모달끝 -->


<!-- 시험출제상세내역 모달 -->
<div class="modal fade" id="testDetailViewModal" tabindex="-1" role="dial`og"
	aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalLabel01">시험출제 정보</h5>
				<button id="testDetailViewModalClose" class="close" type="button" data-bs-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>

			</div>
			<div class="testDetailViewModalbody "></div>
		</div>
	</div>
</div>
<!-- 모달끝 -->





<div class="sub_top_wrap_pro">
<div class="sub_tit05" style="width:70%;">
	<span></span>시험
</div>
<input type="hidden" value="${lctreNo}" id="selectLctreNoUsingData" />
<input id="#addTestBtn" type="button" value="시험추가" class="btn btn-info ft_right"  data-toggle="modal" data-target="#testInsertModal" />

</div>

<div>
	<table class="table table_style01 mt-4 table_center" id="dataTable"
		width="35%" cellspacing="0">
		<thead>
		<colgroup>
			<col width="10%">
			<col width="10%">
			<col width="20%">
			<col width="20%">
			<col width="10%">
			<col width="20%">
		</colgroup>
		<tr>
			<th>년도</th>
			<th>학기</th>
			<th>구분</th>
			<th>강의구분</th>
			<th>이수구분</th>
			<th>상세보기</th>
		</tr>
		</thead>
		
		<tbody id="listBody"></tbody>
	
	</table>
</div>

