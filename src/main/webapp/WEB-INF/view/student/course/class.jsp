<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.option_tit{width:auto; margin-right:5px;}
#listbody td:nth-child(1){text-align: left; padding-left : 10px !important;}
#listbody td:nth-child(6){text-align: left;}
</style>

<!-- Begin Page Content -->
<div class="container-fluid">
	<div class="card2">
		<div class="sub_tit02">강의조회</div>
		<div class="sub_top_wrap">
			<!-- selectbox -->
			<div class="s_option mr-4">
				<span class="option_tit">이수학기</span>
				<div class="select-container03">
					<select class="custom-select03" id="semesterCodeOptions">
						<c:if test="${empty list }">
							<option value label="이수한 학기 없음" />
						</c:if>
						<c:if test="${not empty list }">
							<c:forEach items="${list }" var="semMap">
								<option value="${semMap.semCd }" label="${semMap.semNm }" />
							</c:forEach>
						</c:if>
					</select>
					<div class="select-arrow03">
						<i class="fa fa-caret-down"></i>
					</div>
				</div>
			</div>
		</div>
		<div>
			<table class="table table_style01 mt-4 table_center" id="dataTable"
				width="100%" cellspacing="0">
				<colgroup>
					<col width="30%">
					<col width="15%">
					<col width="10%">
					<col width="15%">
					<col width="10%">
					<col width="25%">
				</colgroup>
				<thead>
					<tr>
						<th>교과목명</th>
						<th>교과구분</th>
						<th>학점</th>
						<th>담당교수</th>
						<th>정원</th>
						<th>시간표</th>
					</tr>
				</thead>
				<tbody id="listbody">
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.container-fluid -->
<script src="/resources/js/app/ksh/classList.js"></script>
