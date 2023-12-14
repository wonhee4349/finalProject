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
.text_min{
    width: 400px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    display: block;
}
</style>

<!-- 성적이의신청 상세내역 모달 -->
<div class="modal fade" id="scoreObjectionInfoViewModal" tabindex="-1" role="dial`og"
	aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalLabel01">성적이의신청 상세내역</h5>
				<button id="scoreObjectionInfoViewModalClose" class="close" type="button" data-bs-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>

			</div>
			<div class="scoreObjectionInfoViewModalbody"></div>
		</div>
	</div>
</div>
<!-- 모달끝 -->

<div class="sub_tit05">
	<span></span>성적 이의신청 목록
</div>
<div class="sub_top_wrap"></div>

<table class="table table_style01 mt-4 table_center" id="dataTable"
	width="35%" cellspacing="0">
	<thead>
	<colgroup>
		<col width="10%">
		<col width="5%">
		<col width="10%">
		<col width="10%">
		<col width="30%">
		<col width="10%">
		<col width="10%">
	</colgroup>
	<tr>
		<th>학과</th>
		<th>학년</th>
		<th>학번</th>
		<th>학생명</th>
		<th>신청내용</th>
		<th>승인구분</th>
		<th>상세보기</th>
	</tr>
	</thead>
	<tbody id="assingmentListBody">
		<c:set var="scoreObjectList" value="${scoreObjectList}"></c:set>
		
		<c:if test="${not empty scoreObjectList}">
			<c:forEach items="${scoreObjectList}" var="scoreObjection">
				<tr class="scoreObjectionInfoView">
					<td>${scoreObjection.student.subject.subjctNm}</td>
					<td>${scoreObjection.student.sknrgSttusGrade} 학년</td>
					<td>${scoreObjection.student.stdntNo}</td>
					<td>${scoreObjection.student.stdntNm}</td>
					<td><span class="text_min">${scoreObjection.scoreObjcCn}</span></td>
					
					<c:choose>
						<c:when test="${scoreObjection.confmSe eq '승인 완료'}">
							<td><span class="pstatus02">${scoreObjection.confmSe}</span></td>
						</c:when>
						<c:when test="${scoreObjection.confmSe eq '반려'}">
							<td><span class="pstatus03">${scoreObjection.confmSe}</span></td>
						</c:when>
						<c:otherwise>
							<td><span class="pstatus01">${scoreObjection.confmSe}</span></td>
						</c:otherwise>
					</c:choose>
					
					<td><button data-lctre-no="${scoreObjection.lctreNo}" data-stdnt-no="${scoreObjection.student.stdntNo}" class="btn btn-primary scoreObjectionDetail" type="button">상세보기</button></td>
				</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${ empty scoreObjectList}">
			<tr>
				<td colspan="7">성적이의신청 목록이 없습니다.</td>
			</tr>
		</c:if>
	</tbody>
</table>

<script>
$(function(){
	$("#scoreObjectionInfoViewModalClose").click(function(){
	    $("#scoreObjectionInfoViewModal").modal('hide');
	});
})
</script>