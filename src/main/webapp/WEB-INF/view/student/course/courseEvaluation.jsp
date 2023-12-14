<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<security:authentication property="principal" var="principal"/>
<style>
.style_scores{}
.style_scores th{background:none; }
</style>
<c:if test="${result }">
	<script>
		var timerInterval;
		Swal.fire({
			title: "강의평가 완료!",
			html: "성실한 참여 감사합니다!!",
			timer: 2000,
			timerProgressBar: true,
			didOpen: () => {
				Swal.showLoading();
				const timer = Swal.getPopup().querySelector("b");
				timerInterval = setInterval(() => {
					timer.textContent = `${Swal.getTimerLeft()}`;
				}, 100);
			},
			willClose: () => {
				clearInterval(timerInterval);
			}
			}).then((result) => {
				location.href = "/student/currScore";
			});
	</script>
</c:if>

<!-- modal -->
<div class="modal fade" id="lectureEvaulationModal" tabindex="-1"
	role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalLabel01"><span id="lectureNamePlaceForLectureEvaulationModal"></span> / <span id="professorNamePlaceForLectureEvaulationModal"></span>교수 - 강의평가</h5>
				<button class="close" type="button" data-bs-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="homework_wrap mb-4">
					<form id="lectureRequestModalForm">
					<div>
							<input type="hidden" id="lectureRequestModalFormLctreNo" name="lctreNo" />
							<input type="hidden" id="lectureRequestModalFormStdntNo" name="stdntNo" />
							<table class="wd100 table_style01 hover_none">
								<colgroup>
									<col width="5%">
									<col width="45%">
									<col width="50%">
								</colgroup>
								<tr>
									<th>번호</th>
									<th>평가기준</th>
									<th>평가표</th>
								</tr>
								<tbody id="lectureEvaulationModalBody">
									<c:forEach items="${items }" var="item">
										<tr data-lctre-evl-iem-no="${item.lctreEvlIemNo }">
											<td>${item.lctreEvlIemNo }</td>
											<td class="txt_left" style="line-height:23px;">${item.lctreEvlIemQestn }</td>
											<td>
												<table class="style_scores">
													<colgroup>
														<col width="20%;">
														<col width="20%;">
														<col width="20%;">
														<col width="20%;">
														<col width="20%;">
													</colgroup>
													<tr>
														<c:forEach items="${scores }" var="score1">
															<th>${score1.lctreEvlScoreQstn }</th>
														</c:forEach>
													</tr>
													<tr>
														<c:forEach items="${scores }" var="score2">
															<c:if test="${score2.lctreEvlScoreNo eq 1 }">
															<td style="border:none;"><input type="radio" name="${item.lctreEvlIemNo }" value="${score2.lctreEvlScoreNo }" required /></td>
															</c:if>
															<c:if test="${score2.lctreEvlScoreNo ne 1 }">
															<td  style="border:none;"><input type="radio" name="${item.lctreEvlIemNo }" value="${score2.lctreEvlScoreNo }" /></td>
															</c:if>
														</c:forEach>
													</tr>
												</table>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						<br>
					</div>
					<button type="button" id="autoFillEvaulationBtn" class="btn btn-success">자동채우기</button>
					<input type="submit" class="btn btn-primary ft_right" value="저장하기" />
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 모달끝 -->

<!-- Begin Page Content -->
<div class="container-fluid">

	<div class="card2">
		<div class="sub_tit02">강의평가</div>
		<div class="sub_top_wrap">
			모든 강의에 대한 평가를 완료해야 금학기 성적을 열람할 수 있습니다
		</div>
		<div>
			<table class="table table_style01 mt-4 table_center" id="lectureListTable"
				width="100%" cellspacing="0">
				<colgroup>
					<col width="30%">
					<col width="15%">
					<col width="20%">
					<col width="15%">
					<col width="20%">
				</colgroup>
				<tr>
					<th>강의명</th>
					<th>교수명</th>
					<th>이수구분</th>
					<th>강의형태</th>
					<th>평가하기</th>
				</tr>
				<tbody id="lectureListTableBody">
					<c:forEach items="${lectureList }" var="lecture">
						<tr data-lctre-no="${lecture.lctreNo }" data-prfsor-nm="${lecture.prfsorNm }" data-course-nm="${lecture.courseNm }">
							<td>${lecture.courseNm }</td>
							<td>${lecture.prfsorNm }</td>
							<td>${lecture.complSe }</td>
							<td>${lecture.lctreSe }</td>
						<c:if test="${empty lecture.evaStatus }">
							<td>
								<button class="blue_btn small_btn lectureEvaulationBtn">평가하기</button>
							</td>
						</c:if>
						<c:if test="${not empty lecture.evaStatus }">
							<td>${lecture.evaStatus }</td>
						</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.container-fluid -->
<script>
var currStdntNo = '${principal.realUser.stdntNo }';
</script>
<script src="/resources/js/app/ksh/studentLectureEvaulation.js"></script>