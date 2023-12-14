<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.testAnswerTable{position : fixed; right:10px; top:270px; width:500px; height : 700px; overflow: scroll; z-index: 9999;}
#checkBoxShowDiv{position : fixed; z-index: 9998;}
#checkBoxShowDiv .small_btn{width :5px; text-align: center;}
</style>
<c:choose>
	<c:when test="${testAlreadyDone eq true }">
		<script>
			Swal.fire({
		        icon: 'error',
		        title: '시험을 이미 응시했습니다!<br/>잘못 이루어진 요청입니다!',
		        showConfirmButton: false,
		        timer: 1500
		    });
		    setTimeout(function() {
				window.close();
		    }, 1500);
		</script>
	</c:when>
	<c:when test="${fileDoesNotExist eq true }">
		<script>
			Swal.fire({
		        icon: 'error',
		        title: "시험지 파일이 유실되었습니다!<br/>교수님에게 문의해주세요!",
		        showConfirmButton: false,
		        timer: 1500
		    });
		    setTimeout(function() {
				window.close();
		    }, 1500);
		</script>
	</c:when>
	<c:when test="${not empty list }">
		<c:set value="${data }" var="test" scope="page"/>
		<script>var remainTimeToTest = parseInt('${test.testQustTime}');</script>
		<script src="/resources/js/app/ksh/studentTestPagePreScript.js"></script>
		<c:remove var="data"/>
		<div id="checkBoxShowDiv">
			문항 체크
			<c:forEach begin="1" end="${test.questionCnt }" step="1" varStatus="showCnt">
				<button class="blue_btn small_btn" id="showBtn${showCnt.count }">${showCnt.count }</button>
			</c:forEach>
		</div>
		<div class="container-fluid">
			<div class="card2">
				<!-- 시험지pdf -->
				<div class="exam_wrap">
					<div class="mt-4 mb-5 blue_box">
						화면 마우스 이탈, 마우스 우클릭, 키보드 입력은 부정행위로 간주되며 부정행위가 두 번 이상 감지될 경우 불이익을 받을 수 있습니다.
					</div>
					<div class="row">
						<div class="exam_area col-xl-8">
							<c:forEach items="${list }" var="base64">
								<img src="data:image/png;base64,${base64}">
							</c:forEach>
						</div>
						<!-- 답안지 -->
						<div class="answer_sheet col-xl-4 testAnswerTable">
							<form id="testAnswerForm">
								<input type="hidden" name="questionCnt" value="${test.questionCnt }" />
								<table class="table_style03">
									<colgroup>
										<col width="12%">
										<col width="22%">
										<col width="22%">
										<col width="22%">
										<col width="22%">
									</colgroup>
									<tr>
										<th colspan="3">남은시간 : <span id="remainTimeViewArea"></span></th>
										<th colspan="2">제한시간 : ${test.testTimeNm }</th>
									</tr>
									<tr>
										<th>문/답</th>
										<th>1</th>
										<th>2</th>
										<th>3</th>
										<th>4</th>
									</tr>
									<c:forEach begin="1" end="${test.questionCnt }" step="1" varStatus="cnt">
										<tr>
											<td>${cnt.count }번</td>
											<c:forEach begin="1" end="4" step="1" varStatus="cntValue">
												<td><input type="radio" name="${cnt.count }" value="${cntValue.count }"></td>
											</c:forEach>
										</tr>
									</c:forEach>
								</table>
							</form>
							<button class="btn-info ft_left mt-4 btn_style01" id="autoFillBtn">자동채우기</button>
							<button class="btn-primary ft_right mt-4 btn_style01" id="submitBtn">제출하기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<script>
			Swal.fire({
		        icon: 'error',
		        title: "시스템 에러! 학사관리처에 문의해주세요!",
		        showConfirmButton: false,
		        timer: 1500
		    });
		    setTimeout(function() {
				window.close();
		    }, 1500);
		</script>
	</c:otherwise>
</c:choose>

<c:if test="${not empty list and fileDoesNotExist ne true and testAlreadyDone ne true}">

</c:if>
<script src="/resources/js/app/ksh/studentTest.js"></script>