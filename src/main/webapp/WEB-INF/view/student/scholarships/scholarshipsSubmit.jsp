<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
		$(document).ready(function () {
			$('.class_list_wrap button').click(function () {
			var tab_id = $(this).attr('data-tab');
			$('.class_list_wrap button').removeClass('active');
			$('.tab-content').removeClass('current');
			$(this).addClass('active');
			$("#" + tab_id).addClass('current');
		});
	});
</script>

<!-- 모달 창 추가 -->
<div class="modal fade" id="scholarshipModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
    <div class="modal-dialog modal-xl" role="document">
        <div class="modal-content">
            <div class="modal-header">
				<h5 class="modal-title" id="modalLabel01">장학금 상세정보</h5>
			<button class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
            </div>
            <div class="modal-body">
                <div class="homework_wrap mb-4">
                    <div class="table_style01 mt-4 mb-4 table_center">
                        <table class="wd100">
                        	<colgroup>
                        		<col width="20%">
                        		<col width="70%">
                        	</colgroup>
                            <tbody id="scholarshipModalTableBody">
                            </tbody>
                        </table>
                        <br>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

		        

	
    
   <!-- Begin Page Content -->
<div class="container-fluid">
	<div class="card2">
		<div class="sub_tit02">장학금 현황 조회</div>
			<div class="class_list_wrap">
				<div class="list_top">
					<button class="gray_btn small_btn active" data-tab="tab-2">수혜내역</button>
					<button class="gray_btn small_btn" data-tab="tab-1">신청목록</button>
				</div>
			</div>


			<div class="tab_contents">
				<div id="tab-1" class="tab-content">
				<!-- 신청목록 테이블 -->
				<table class="table table_style01 mt-4 table_center" id="requestScholarshipListTable"
					width="100%" cellspacing="0">
					<colgroup>
						<col width="20%">
						<col width="20%">
						<col width="20%">
						<col width="20%">
						<col width="20%">
					</colgroup>
					<tr>
						<th>장학금명</th>
						<th>장학금액</th>
						<th>지급방식</th>
						<th>신청일자</th>
						<th>선발기간</th>
					</tr>
					<tbody id="requestScholarshipListBody"></tbody>
	
				</table>
				
				</div>
				<div id="tab-2" class="tab-content current">
					<div class="input-group wd300  ft-left">
						<span class="option_tit">학기</span>
						<div class="select-container" id="scholarshipSemCdSelectContainer">
							
							<div class="select-arrow" style="margin-right:8px;">
								<i class="fa fa-caret-down"></i>
							</div>
						</div>
						<div class="input-group-append">
							<button class="btn btn-primary" style="height:38px;" id="scholarshipListSearchBtn" type="button">
								<i class="fas fa-search fa-sm"></i>
							</button>
						</div>
					</div>
				 	<table class="table table_style01 mt-4 table_center" id="studentScholarshipListTable"
						width="100%" cellspacing="0">
					<colgroup>
						<col width="25%">
						<col width="25%">
						<col width="25%">
						<col width="25%">
					</colgroup>
					<tr>
						<th>장학금명</th>
						<th>장학금액</th>
						<th>지급방식</th>
						<th>수혜학기</th>
					</tr>
				<tbody id="studentScholarshipListBody"></tbody>
			</table>
				</div>
			</div>
	</div>
</div>                <!-- /.container-fluid -->
<script src="/resources/js/app/ksh/studentScholarshipStatus.js"></script>