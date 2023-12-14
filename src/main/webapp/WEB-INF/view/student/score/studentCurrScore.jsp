<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
#scoreObjectionModalFormScoreObjcCn{width : 90%}
</style>
<!-- modal -->
<div class="modal fade" id="scoreObjectionModal" tabindex="-1"
	role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalLabel01"><span id="lectureNamePlaceForScoreObjectionModal"></span> - 성적이의신청</h5>
				<button class="close" type="button" data-bs-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="homework_wrap mb-4">
					<form id="scoreObjectionModalForm">
					<div class="table_style01 mt-4 mb-4 table_center">
							<input type="hidden" id="scoreObjectionModalFormLctreNo" name="lctreNo" />
							<table class="wd100">
								<colgroup>
									<col width="30%">
									<col width="70%">
								</colgroup>
								<tr>
									<th>받은 성적</th>
									<td><span id="printScorePlaceForScoreObjectionModal"></span></td>
								</tr>
								<tr>
									<th>신청사유</th>
									<td style="padding:20px;">
										<input class="form-style01" style="width:100%; min-height:100px;" type="text" id="scoreObjectionModalFormScoreObjcCn" name="scoreObjcCn" />
									</td>
								</tr>
							</table>
					</div>
					<button type="button" class="btn btn-success ft_left mt-2" id="autoFillBtn">일괄입력</button>
					<input type="submit" class="btn btn-primary ft_right mt-2" value="이의신청" />
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- modal -->
<div class="modal fade" id="scoreObjectionInfoModal" tabindex="-1"
	role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalLabel01"><span id="lectureNamePlaceForScoreObjectionInfoModal"></span> - 이의신청정보</h5>
				<button class="close" type="button" data-bs-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="homework_wrap mb-4">
					<div class="table_style01 mt-4 mb-4 table_center">
							<table class="wd100">
								<colgroup>
									<col width="30%">
									<col width="70%">
								</colgroup>
								<tr>
									<th>신청 상태</th>
									<td><span id="statusPlaceForScoreObjectionInfoModal"></span></td>
								</tr>
								<tr>
									<th>받은 성적</th>
									<td><span id="printScorePlaceForScoreObjectionInfoModal"></span></td>
								</tr>
								<tr>
									<th>이의제기 제출사유</th>
									<td>
										<span id="scoreObjectionInfoModalReasonArea"></span>
									</td>
								</tr>
								<tr>
									<th>교수님 코멘트</th>
									<td>
										<span id="scoreObjectionInfoModalCommentArea"></span>
									</td>
								</tr>
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
		<div class="sub_tit02">금학기 성적 조회 - <span id="currenSemesterPrintArea" data-curr-sem-cd="${currSemCd }">${printCurrSemCd }</span> <span id="assertScoreArea"></span></div>
		<div class="sub_tit03">금학기 성적 열람기간에만 이의제기 신청이 가능합니다</div>
		<div class="sub_top_wrap">
			금학기 수강 강의에 대한 강의평가를 완료해야 조회가 가능합니다
		</div>
		<div>
			<table class="table table_style01 mt-4 table_center" id="currentSemesterScoreDataTable" width="100%" cellspacing="0">
				<colgroup>
					<col width="15%">
					<col width="30%">
					<col width="5%">
					<col width="20%">
					<col width="10%">
					<col width="20%">
				</colgroup>
				<tr>
					<th>강의코드</th>
					<th>강의명</th>
					<th>성적</th>
					<th>수강구분</th>
					<th>이수학점</th>
					<th>이의제기</th>
				</tr>
				<tbody id="scoreListBody">
					<tr>
						<td colspan="6">정보 없음</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.container-fluid -->
<script src="/resources/js/app/ksh/studentCurrScore.js"></script>
