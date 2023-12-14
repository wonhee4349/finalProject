<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.sub_tit04 {
	font-size: 18px;
	color: #304b73;
	padding-left: 0px;
	font-weight: 600;
	width: 100%;
	float: left;
	text-align: left;
	margin-bottom: 5px;
}

.sub_tit04 span {
	width: 8px;
	height: 8px;
	background: #304b73;
	display: block;
	float: left;
	margin-right: 8px;
	position: relative;
	top: 9px;
}

#btn1 {
	width: 80px;
	height: 30px;
	float: right;
	margin-top: 10px;
	text-align: center;
	padding-bottom: 30px;
}
.btn{width:98px !important; margin-right:30px  !important;}
</style>

<!-- 수정 가능한 입력란  -->
<div id="editFields">
 <form id="commonNoticeUpdateForm" action="/staff/board/staffCommonNoticeUpdateData" method="post"> <!-- Add action and method -->
	<div class="container-fluid">
		<div>
			<div class="sub_tit02">공지 사항 수정</div>
			<div class="sub_top_wrap"></div>
			<security:authentication property="principal" var="principal" />	
			<!-- 이름 입력 -->
			 <input type="hidden" name="boNo"
				class="form-style01" value="${board.boNo}" readonly
				required />
			
			<input type="hidden" name="boWrter"
				class="form-style01" readonly required value="${board.boWrter}"/>
					
			<input type="hidden" name="boDate"
				class="form-style01" value="${board.boDate}" readonly
				required />
			<table class="table table_style01 table_center hover_none">
			<tr>
			<th>공지 구분</th> 
			<td><select name="bdSe" class="form-style01" >
			<option value label="선택">
					<c:forEach var="bdse" items="${bdse}">
					<c:if test="${bdse.comCodeNm eq board.bdSe}">
						<option selected value="${bdse.comCode}">${bdse.comCodeNm}</option>
						</c:if>
					<c:if test="${bdse.comCodeNm ne board.bdSe}">
						<option value="${bdse.comCode}">${bdse.comCodeNm}</option>
						</c:if>
					</c:forEach>
					</select> </td>
				</tr>
				<tr>
			<!-- 제목 -->
			<th>제목 </th> 
			<td> <input type="text" name="boTitle" class="form-style01"
				value="${board.boTitle}"  required/> <td>
			</tr>
			</table>
					
			<!-- 내용 -->
			<table class="table table_style01 table_center hover_none">
			<tr>
			<th>내용<th>
			<td> <textarea style="font-size:20px;" id="bocn" rows="25" cols="80" wrap="hard" name="boCn" class="boxTA" required>${board.boCn}</textarea> </td>	
			</tr>
			</table>
		</div>
	</div>
	<input type="submit" class="btn btn-primary ft_right mt-4" value="수정하기"/>
            </form>
</div>

<!-- 수정 가능한 다른 입력란들을 추가 -->

<!-- JavaScript/jQuery 코드 -->
<script>
	$(function() {
		});

		// "저장" 버튼에 대한 이벤트 리스너 추가
		$("#saveChanges").click(function() {
			// 수정된 값들을 입력란에서 가져옴
			var editedBoTitle = $("#editedBoTitle").val();
			var editedBoCn = $("#editedBoCn").val();
			// 다른 필요한 수정된 값들을 가져옴

			// 서버에 변경사항을 저장하기 위한 AJAX 요청 수행
			$.ajax({
				url : '/saveChanges', // 서버 엔드포인트에 맞게 URL 수정
				method : 'POST',
				data : {
					boNo : "${board.boNo}",
					 boTitle : editedBoTitle,
					boCn : editedBoCn,
				// 다른 수정된 값들도 data 객체에 포함
				},
				success : function(response) {
					// 성공적인 응답 처리 (예: 성공 메시지 표시)
					alert('변경 사항이 성공적으로 저장되었습니다!');

					// 선택적: 페이지 새로고침 또는 페이지 새로 고침 없이 표시된 정보 업데이트
					// location.reload();
				},
				error : function(error) {
					// 오류 응답 처리 (예: 오류 메시지 표시)
					alert('변경 사항 저장 중 오류 발생: ' + error.responseText);
				}
			});
		});
	});
</script>

<script src="/resources/js/app/ljh/staffCommonNoticeUpdateData.js"></script>
