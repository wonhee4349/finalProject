<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <!-- Begin Page Content -->
<div class="container-fluid">
	<div class="card2">
		<div class="sub_tit02">휴학신청 내역</div>
		<div>
			<table class="table table_style01 mt-4 table_center" id="dataTable" width="100%"
                cellspacing="0">
				<colgroup>
					<col width="25%">
					<col width="40%">
					<col width="25%">
				</colgroup>
				<tr>
					<th>신청학기</th>
					<th>신청일</th>
					<th>승인상태</th>
				</tr>
				<tbody id="listbody">
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- 모달 창 추가 -->
<div class="modal fade" id="vacRequestInfo" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLabel01">휴학신청 상세내역</h5>
              <button class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
				    <span aria-hidden="true">×</span>
			  </button>

            </div>
            <div class="modal-body">
                <div class="pro_table width80" style="margin:0 auto">
      				<table class="table_style02 table_center">
      					<colgroup>
      						<col width="30%">
      						<col width="70%">
      					</colgroup>
      					<tbody id="modalTbody">
      					</tbody>
      				</table>
   				</div>
            </div>
        </div>
    </div>
</div>
<!-- /.container-fluid -->

<script src="/resources/js/app/ksh/vacRequestList.js"  ></script>