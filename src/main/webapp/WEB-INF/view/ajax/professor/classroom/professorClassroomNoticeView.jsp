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


<script>
// 문서가 로드된 후 실행

</script>

<!-- 공지사항 상세내역  모달 -->
<div class="modal fade" id="noticeInfoViewModal" tabindex="-1" role="dial`og"
	aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalLabel01">자료 등록 정보</h5>
				<button id="noticeInfoViewModalClose" class="close" type="button" data-bs-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="noticeInfoViewModalbody"></div>
		</div>
	</div>
</div>
<!-- 모달끝 -->

<!-- 자료 등록 모달 -->
<div class="modal fade" id="addNoticeBtnModal" tabindex="-1"
	role="dial`og" aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
	
		<form id="noticeClassroomForm" action="/professor/classroom/notice/new" method="POST" enctype="multipart/form-data">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalLabel01">공지사항 등록</h5>
					<button id="addNoticeBtnModalClose" class="close" type="button" data-bs-dismiss="modal" id="addNoticeBtnModalClose"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<br />
				<div class="sub_tit03">
					<span></span>공지사항 정보 입력
				</div>
				<input type="hidden" id="lctreNo" name="lctreNo" value="${lctreNo}" />
				<input type="hidden" id="noticeCrSe" name="crSe" value="04"/>
				
				<table class="table table_style01 table_center" id="dataTable"
					width="100%" cellspacing="0">
					<tr>
						<th>제목</th>
						<td><input type="text" id="noticeCrTitle" name="crTitle" class="form-style01" required="required" /></td>
					</tr>
				</table>
				<div class="sub_tit04">
					<span></span>공지사항과 함께 올릴 파일을 등록 해주십시오.
				</div>
				<div class="gray_box mt-4">
					<div class="white_bg">
						<input type="file" id="noticeFile" name="noticeFile">
					</div>
				</div>
				<br />
				<div class="tit02">내용</div>
				<div>
					<textarea id="NoticeCrCn"
						name="crCn"
						class="form-control textbox" required></textarea>
				</div>
				<input type="submit" id="noticeAddBtn" value="등록"class="btn btn-success" />
				</div>
			<security:csrfInput/>	
		</form>
		
		<div class="noticeModalBody "></div>

	</div>
</div>
<!-- 모달끝 -->


<div class="sub_tit03">
	<span></span>공지사항
</div>
<div class="sub_top_wrap"></div>
<button class="btn btn-info ft_right" id="addNoticeModalViewBtn" data-toggle="modal" data-target="#addNoticeBtnModal">공지사항 등록</button>


<table class="table table_style01 mt-4 table_center" id="dataTable"
	width="35%" cellspacing="0">
	<thead>
	<colgroup>
		<col width="10%">
		<col width="10%">
		<col width="40%">
		<col width="10%">
		<col width="10%">
		<col width="10%">
	</colgroup>
	<tr>
	<tr>
		<th>년도</th>
		<th>학기</th>
		<th>제목</th>
		<th>강의구분</th>
		<th>이수구분</th>
		<th>등록일</th>
		<th>상세정보</th>
	</tr>
	</tr>
	</thead>
	<tbody id="noticeListBody"></tbody>
</table>

<script src="${pageContext.request.contextPath}/resources/js/app/kjs/professorClassroomNoticeView.js"></script>