<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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


<!-- modal -->
<div class="modal fade" id="modal_open" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
    <div class="modal-dialog modal-xl" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLabel01">교과목 상세정보</h5>
              <button class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
				    <span aria-hidden="true">×</span>
			  </button>

            </div>
            <div class="modal-body ">
            </div>
        </div>
    </div>
</div>


<div class="container-fluid">

	<div class="card2">
		<div class="sub_tit02">교과목 내역</div>
		<div class="sub_top_wrap">

		 
			<!-- selectbox -->
			<div class="s_option">
				<div class="select-container">
					<select class="custom-select02" name="searchType" id="searchSelect">
						<option value="" label="전체" />
						<option value="courseNm" label="교과목" />
						<option value="subjctNm" label="학과" />
						<option value="clgNm" label="단과대학" />
					</select>
					<div class="select-arrow">
						<i class="fa fa-caret-down"></i>
					</div>
				</div>
			</div>
			<!-- search -->
			<div id="searchUI" class="d-none d-sm-inline-block  ml-md-3 my-2 my-md-0 navbar-search">
					<div class="input-group wd300 ft_right">
						<input type="text" name="searchWord"
							placeholder="검색어 입력"
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
		<form action="<c:url value='/professor/course/ajax/courseList'/>" id="searchForm">
			<input type="hidden" name="searchType" />
			<input type="hidden" name="searchWord" />
			<input type="hidden" name="page" />
		</form>
		<div>
			<table class="table table_style01 mt-4 table_center" id="dataTable" width="30%" cellspacing="0">
				<thead>
				<colgroup>
					<col width="10%">
					<col width="15%">
					<col width="30%">
					<col width="15%">
					<col width="20%">
				</colgroup>
				<tr>
					<th>단과대학</th>
					<th>학과</th>
					<th>교과목</th>
					<th>이수구분</th>
					<th>이수학점</th>
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

<script src="${pageContext.request.contextPath}/resources/js/app/kjs/professorCourse.js"></script>