<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.noDataTrTag{text-align: center !important;}
#assignmentListBody .dataTrTag>td:nth-child(1){text-align: left; padding-left : 10px !important;}
#assignmentListBody .dataTrTag>td:nth-child(2){text-align: left !important;}
</style>
<table class="table table_style01 mt-4 table_center" id="assignmentTable" width="100%" cellspacing="0">
	<colgroup>
		<col width="35%">
		<col width="35%">
		<col width="10%">
		<col width="20%">
	</colgroup>
	<tr>
		<th>과제명</th>
		<th>내용</th>
		<th>등록일자</th>
		<th>제출여부</th>
	</tr>
	<tbody id="assignmentListBody">
		<c:if test="${empty list }">
			<tr class="noDataTrTag"><td colspan="4">제출해야 할 과제가 없습니다</td></tr>
		</c:if>
		<c:if test="${not empty list }">
			<c:forEach items="${list }" var="board">
				<tr data-cr-no="${board.crNo }" data-lctre-no="${board.lctreNo }" class="dataTrTag">
					<td>${board.crTitle }</td>
					<td>
						<span class="overflowHiddenEllipsis">${board.crCn }</span>
					</td>
					<td>${board.crDate }</td>
					<c:if test="${board.submitted eq '제출완료' }">
						<td><button class="gray_btn small_btn submittedBtn">제출완료</button></td>
					</c:if>
					<c:if test="${board.submitted eq '미제출' }">
						<td><button class="blue_btn small_btn submitBtn">제출하기</button></td>
					</c:if>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>

