<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
#boardDetailDiv{display:none;}
.noDataTrTag{text-align: center !important;}
#classroomBoardListBody .dataTrTag>td:nth-child(1){text-align: left; padding-left : 10px !important;}
#classroomBoardListBody .dataTrTag>td:nth-child(2){text-align: left !important;}
#classroomBoardListBody .dataTrTag>td:nth-child(3){text-align: left !important;}
</style>
<table class="table table_style01 mt-4 table_center" id="classroomBoardListTable" width="100%" cellspacing="0">
	<colgroup>
		<col width="15%">
		<col width="25%">
		<col width="40%">
		<col width="10%">
		<col width="10%">
	</colgroup>
	<tr>
		<th>구분</th>
		<th>제목</th>
		<th>내용</th>
		<th>작성자</th>
		<th>작성일</th>
	</tr>
	<tbody id="classroomBoardListBody">
		<c:if test="${empty list }">
			<tr class="noDataTrTag"><td colspan="5">등록된 자료 없음</td></tr>
		</c:if>
		<c:if test="${not empty list }">
			<c:forEach items="${list }" var="board">
				<tr data-cr-no="${board.crNo }" class="dataTrTag">
					<td>${board.crSeNm }</td>
					<td>${board.crTitle }</td>
					<td>
						<span class="overflowHiddenEllipsis">${board.crCn }</span>
					</td>
					<td>${board.professor.prfsorNm }</td>
					<td>${board.crDate }</td>
				</tr>
			</c:forEach>
		</c:if>
		
	</tbody>
</table>

<!-- 원희누나 공지사항 갖고온것 -->
<%-- 원희누나 공지사항 갖고온것 --%>
<div class="container-fluid" id="boardDetailDiv">
	<div class="view-title" id="boardDetailTitle"></div>
	<div class="view-detail">
            <div class="view-util">
				<dl class="count">
                    <dt>첨부파일</dt>
                    <dd id="boardDetailAttatch"></dd>
                </dl>
            	<dl class="writer">
                    <dt>작성자</dt>
                    <dd>
                    	<span id="boardDetailWriter">${board.staff.sklstfNm}</span>
					</dd>
				</dl>
                <dl class="write">
                    <dt>등록일</dt>
                    <dd id="boardDetailDate"></dd>
                </dl>
            </div>
    </div>        
            <br>
		<div class="notice_text_box" id="boardDetailCn">
		</div>	
		<button class="btn btn-navy-primary mt-4 ft_right btn_style_round" id="backToListBtn">목록</button>
</div>