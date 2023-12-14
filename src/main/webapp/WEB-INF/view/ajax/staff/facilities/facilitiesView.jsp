<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<style>
.sub_tit04{font-size: 18px;color: #304b73;padding-left:0px;font-weight: 600;width: 60%;float:left; text-align:left; margin-bottom:5px;}   
.sub_tit04 span{    width: 8px;
    height: 8px;
    background: #304b73;
    display: block;
    float: left;
    margin-right: 8px;
    position: relative;
    top: 9px;}
#btn1 {
width : 80px;
height : 30px;
float: right;
margin-top: 10px;
color : white;
text-align: center;
padding-bottom: 30px;
}
</style>

<div class="homework_wrap mb-4" id="firstfacilities">
     <div class="mt-4 mb-4 table_center">			   
  					<div class="sub_tit04"><span></span>시설물 정보</div> 				             						
  						<table class="table_style02 table_center">
	    					<colgroup>
	    						<col width="10%">
	    						<col width="10%">
	    					</colgroup>	    			
	    					<tr>
	    						<th>소속건물</th><td>${facilities.building.buldNm}</td>
	    					</tr>
	    					<tr>
	    						<th>시설물명</th><td>${facilities.fcltsNm}</td>
	    					</tr>
	    					<tr>
	    						<th>분류</th><td>${facilities.fcltsPurps}</td>
	    					</tr>
	    					<tr>
	    						<th>수용인원</th><td>${facilities.fcltsNmpr}</td>
	    					</tr>		
  			   		</table>	
  			   		<button id="btn1" class="btn btn-primary" value="수정">수정</button>
  			</div> 				
  		</div>
  		
  		<!-- 수정 가능한 입력란 (처음에는 숨겨져 있음) -->
<div id="editFields" style="display: none;">
 <form id="facilitiesUpdateForm" action="/staff/facilities/staffFacilitiesUpdateData" method="post"> <!-- Add action and method -->
	<div class="container-fluid">
		<div class="card2">
			<div class="sub_tit02">시설물 수정</div>
			<div class="sub_top_wrap"></div>
			<security:authentication property="principal" var="principal" />	
			<label for="fcltsNo">시설물 코드</label> <input type="hidden" name="fcltsNo"
				class="form-style01" value="${facilities.fcltsNo}" readonly
				required />
			<!-- 건물 선택 -->
			<label for="buldNo">건물 선택</label> <select
				name="buldNo" class="form-style01" >
				<option value label="선택">
					<c:forEach var="building" items="${building}">
						<c:if test="${building.buldNm eq facilities.building.buldNm}">
							<option selected value="${building.buldNo}">${building.college.clgNm} ${building.buldNm}</option>
						</c:if>
						<c:if test="${building.buldNm ne facilities.building.buldNm}">
							<option value="${building.buldNo}">${building.college.clgNm} ${building.buldNm}</option>
						</c:if>
					</c:forEach>
			</select> 
			
			
			<!-- 시설물명 -->
			<label for="fcltsNm">시설물명</label> <input type="text"
				name="fcltsNm" class="form-style01"
				value="${facilities.fcltsNm}"  />

			<!-- 분류 -->
			<label for="fcltsPurps">분류</label> <input type="text"
				name="fcltsPurps" class="form-style01 datepicker"
				value="${facilities.fcltsPurps}" /> 
				
			<label for="fcltsNmpr">수용인원</label> <input type="text"
				name="fcltsNmpr" class="form-style01 datepicker" value="${facilities.fcltsNmpr}"  />
				
				
		</div>
	</div>
	<input type="submit" class="btn btn-primary ft_right mt-4" value="수정하기"/>
            </form>
</div>

<!-- 수정 가능한 다른 입력란들을 추가 -->

<!-- JavaScript/jQuery 코드 -->
<script>
	$(function() {
		$("#btn1").click(function() {
			// 현재 표시되어 있는 정보를 숨김
			$("#firstfacilities").hide();
			// 수정 가능한 입력란을 표시
			$("#editFields").show();
		});

		// "저장" 버튼에 대한 이벤트 리스너 추가
		$("#saveChanges").click(function() {
			// 수정된 값들을 입력란에서 가져옴
			var editedfcltsNm = $("#editfcltsNm").val();
			var editedfcltsIhidnum1 = $("#editfcltsIhidnum1").val();
			// 다른 필요한 수정된 값들을 가져옴

			// 서버에 변경사항을 저장하기 위한 AJAX 요청 수행
			$.ajax({
				url : '/saveChanges', // 서버 엔드포인트에 맞게 URL 수정
				method : 'POST',
				data : {
					fcltsNo : "${facilities.fcltsNo}",
					fcltsNm : editedfcltsNm,
					fcltsIhidnum1 : editedfcltsIhidnum1,
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


<script src="/resources/js/app/ljh/staffFacilitiesUpdateData.js"></script>
  		
  			