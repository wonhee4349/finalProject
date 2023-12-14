<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table class="table table_style01 mt-4 table_center" id="classroomTestTable"
	width="100%" cellspacing="0">
	<colgroup>
		<col width="25%">
		<col width="25%">
		<col width="25%">
		<col width="25%">
	</colgroup>
	<tr>
		<th>시험구분</th>
		<th>문항수</th>
		<th>제한시간</th>
		<th>응시구분</th>
	</tr>
	<tbody id="classroomTestListBody">
		<c:if test="${empty list }">
			<tr><td colspan="4">해당 과목에 응시할 수 있는 시험 목록이 없습니다</td></tr>
		</c:if>
		<c:if test="${ not empty list }">
			<c:forEach items="${list }" var="test">
				<tr data-test-se="${test.testSe }" data-lctre-no="${test.lctreNo }">
					<td>${test.testSeNm }</td>
					<td>${test.questionCnt }문항</td>
					<td>${test.testTimeNm }</td>
					<c:if test="${test.questionable eq '응시완료' }">
						<td><button class="gray_btn small_btn getTestInfoBtn">응시완료</button></td>
					</c:if>
					<c:if test="${test.questionable eq '미응시' }">
						<td><button class="blue_btn small_btn getTestBtn">응시하기</button></td>
					</c:if>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>