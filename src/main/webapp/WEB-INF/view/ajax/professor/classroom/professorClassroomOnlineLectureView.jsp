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
</style>

<div class="sub_tit03"><span></span>온라인강의</div>
<div class="sub_top_wrap"></div>

<!-- 과제 등록 모달 -->
<div class="modal fade" id="addOnlineLectureBtnModal" tabindex="-1"
	role="dial`og" aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
	
		<form id="onlineLectureClassroomForm" action="/professor/classroom/onlineLecture/new" method="POST" enctype="multipart/form-data">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalLabel01">영상강의 등록</h5>
					<button class="close" type="button" data-bs-dismiss="modal" id="addOnlineLectureBtnModalClose"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<br />
				<div class="sub_tit03">
					<span></span>온라인강의 등록 정보 입력
				</div>
				<input type="hidden" id="lctreNo" name="lctreNo" value="${lctreNo}" /> 
				<table class="table table_style01 table_center" id="dataTable"
					width="100%" cellspacing="0">
					<colgroup>
						<col width="10%">
						<col width="20%">
						<col width="10%">
						<col width="20%">
					</colgroup>
					<tr>
						<th colspan="1">주차</th>
						<td colspan="1">
							<select name="lctreVdWeek" id="onlineLectureLctreVdWeek" class="form-control bg-light border-0 small"" aria-label="Search" aria-describedby="basic-addon2">
				                <option value="-1">주차선택</option>
				                <c:forEach begin="1" end="15" step="1" var="week">
				                	<option value="${week}">${week}주차</option>
				                </c:forEach>
				            </select>
			            </td>
					</tr>
					<tr>
						<th>강의기준시간</th>
						<td><input type="text" id="lctreVdStdrTm" name="lctreVdStdrTm" class="form-style01" placeholder="분 단위 입력" /></td>
						<th>시청시작시간</th>
						<td><input type="text" id="lctreVdBeginTm" name="lctreVdBeginTm" class="form-style01" placeholder="ex) 00:00"/></td>
						<th>시청종료시간</th>
						<td><input type="text" id="lctreVdEndTm" name="lctreVdEndTm" class="form-style01" placeholder="ex) 00:00"/></td>
					</tr>
				</table>
				<div class="sub_tit04">
					<span></span>등록 할 영상파일을 등록 해주십시오.
				</div>
				<div class="gray_box mt-4">
					<div class="white_bg">
						<input type="file" id="onlineLectureFile" name="onlineLectureFile">
					</div>
				</div>
				<br/>
				<input type="submit" id="onlineLectureAddBtn" value="등록"class="btn btn-success" />
				</div>
			<security:csrfInput/>	
		</form>
		
		<div class="onlineLectureModalBody "></div>

	</div>
</div>
<!-- 모달끝 -->


<div class="sub_top_wrap"></div>
<button class="btn btn-info ft_right" id="addOnlineLectureModalViewBtn" data-toggle="modal" data-target="addOnlineLectureBtnModal">영상 등록</button>

	<table class="table table_style01 mt-4 table_center" id="dataTable"
		width="35%" cellspacing="0">
		<thead>
		<colgroup>
			<col width="5%">
			<col width="10%">
			<col width="20%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
		</colgroup>
		<tr>
			<th>번호</th>
			<th>학과</th>
			<th>교과목명</th>
			<th>이수구분</th>
			<th>학점</th>
			<th>신청일</th>
			<th>승인구분</th>
		</tr>
		</thead>
		<tbody id="listBody"></tbody>
	</table>

<script src="${pageContext.request.contextPath}/resources/js/app/kjs/professorClassroomOnlineLectureView.js"></script>