<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container-fluid">
<script>
    // 문서가 로드된 후 실행
    document.addEventListener("DOMContentLoaded", function () {
        // 모달 열기 버튼 클릭 시 모달 창 열기
        document.getElementById("openModal").addEventListener("click", function () {
            var modal = new bootstrap.Modal(document.getElementById("openModal"));
            modal.show();
        });

        // 모달 닫기 버튼 클릭 시 모달 창 닫기
        document.querySelector(".close").addEventListener("click", function () {
            var modal = bootstrap.Modal.getInstance(document.getElementById("openModal"));
            modal.hide();
        });

    });
</script>

    
	<!-- 모달 창 추가 -->
		<div class="modal fade" id="modal_open" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
		    <div class="modal-dialog modal-xl" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title" id="modalLabel01">강의 정보</h5>
		              <button class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
						    <span aria-hidden="true">×</span>
					  </button>

		            </div>
		            <div class="modal-body ">
		            </div>
		        </div>
		    </div>
		</div>
		<!-- 모달끝 -->
	<div class="card2">
		<div class="sub_tit02">강의</div>
		<div class="sub_top_wrap">

			<!-- selectbox -->
			<div class="s_option">
				<div class="select-container">
					<select class="custom-select02">
						<option value="" label="전체" />
						<option value="prfsorNm" label="교수명" />
						<option value="courseNm" label="과목명" />
					</select>
					<div class="select-arrow">
						<i class="fa fa-caret-down"></i>
					</div>
				</div>
			</div>

			<!-- search -->
			<div id="searchUI"
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
		<form action="<c:url value='/staff/leture/ajax/letureList'/>"
			id="searchForm">
			<input type="hidden" name="searchType"> <input type="hidden"
				name="searchWord"> <input type="hidden" name="page">
		</form>
		<div>
			<table class="table table_style01 mt-4 table_center" id="dataTable"
				width="100%" cellspacing="0">
				<thead>
				<colgroup>
					<col width="5%">
					<col width="15%">
					<col width="10%">
					<col width="10%">
					<col width="15%">
				</colgroup>
				<tr>
					<th>번호</th>
					<th>과목명</th>
					<th>교수명</th>
					<th>학기</th>
					<th>강의구분</th>
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

<script src="/resources/js/app/ljh/staffLectureData.js"></script>