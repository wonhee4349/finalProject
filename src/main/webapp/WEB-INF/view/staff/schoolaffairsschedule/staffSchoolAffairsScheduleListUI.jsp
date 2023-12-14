<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <style>
#btn1 {
	width: 50px;
	height: 10px;
	padding-bottom: 30px;
}

.search_top{height:60px; float:left;}
.search_bottom{float: right;}
.wd130{width: 152px !important;}
.s_option02{width: auto;float: left;height: 44px; display: flex;}
.select-container {width: 109px; position: relative;}
.custom-select02 {
	appearance: none;
	-webkit-appearance: none;
	-moz-appearance: none;
	width: 123px;
	padding: 6px;
	border: 1px solid #ced5e9;
	border-radius: 4px;
	background-color: #fff;
	cursor: pointer;
}
.select-arrow {
	position: relative;
	float: right;
	top: -31px;
	right: 0;
}
.custom-select02:active,.custom-select02:focus {border-color: #007bff;}
.custom-select02 option {padding: 10px;}
.option_tit{font-size: 18px;width: 45px;display: block;float: left;line-height: 39px;color: #000;font-weight: 100;}
.pagination{ justify-content: center;}
</style>

<!-- Begin Page Content -->
<div class="container-fluid">

	 <div class="card2">
                       <div class="sub_tit02">학사일정</div>
                       <div id="searchUI" class="sub_top_search">					                         
							<!-- search -->
							
							
							<div class="search_bottom">
								
								
								<div class="s_option02 mr-4 ft-left">
									<span class="option_tit" style="width:80px;">일정구분</span>
									<div class="select-container">
										<select class="custom-select02 searchFormUIInput" name="scduSe">
											<option value="" label="전체">
											<c:forEach items="${scduSe}" var="scd">
												<option value="${scd.comCode}" label="${scd.comCodeNm}">
											</c:forEach>
										</select>
										<div class="select-arrow">
											<i class="fa fa-caret-down"></i>
										</div>
									</div>
								</div>
									
								<div class="input-group-append">
									<button class="btn btn-primary" id="searchBtn" type="button">
										<i class="fas fa-search fa-sm" ></i>
									</button>
								</div>
							</div>


		</div>
		<form action="<c:url value='/staff/schoolaffairsschedule/ajax/staffschoolaffairsscheduleList'/>"
			id="searchForm">
			<input type="hidden" name="scduSe">
			<input type="hidden" name="page">
		</form>
		<div>
			<table class="table table_style01 mt-4 table_center" id="dataTable"
				width="100%" cellspacing="0">
				<thead>
				<colgroup>
					<col width="10%">
					<col width="20%">
					<col width="20%">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					
				</colgroup>
				<tr>
					<th>번호</th>
					<th>학사일정</th>
					<th>학기</th>
					<th>시작 날짜</th>
					<th>종료 날짜</th>
					<th>삭제</th>
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

<script src="/resources/js/app/ljh/staffSchoolAffairsScheduleData.js"></script>
<script src="/resources/js/app/ljh/staffSchoolAffairsScheduleDelete.js"></script>