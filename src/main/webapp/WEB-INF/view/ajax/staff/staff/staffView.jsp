<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<style>
.sub_tit04{font-size: 18px;color: #304b73;padding-left:0px;font-weight: 600;width: 100%;float:left; text-align:left; margin-bottom:5px;}   
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

.in01{margin-bottom:15px; width:100%;}
.in01 input{width:300px;}
p{margin:0;}
.tit_lable{width:100%; font-size:18px; color:#000;}
.span_tit{display:block; width:65px; float:left; margin-right:10px; text-align:left; line-height:37px}
.address_wrap{width:100%; height:42px;}
.wd500{width:500px;}

</style>

<div class="homework_wrap mb-4" id="firststaff">
     <div class="table_style01 mt-4 mb-4 table_center">
  				<div class="profile_img">
  					<div class="pro_img" style="border-bottom: none;">   
  					<div class="sub_tit04"><span></span>교직원 정보</div>              						
  						<table class="table_style02 table_center">
	    					<colgroup>
	    						<col width="20%">
	    						<col width="20%">
	    						<col width="60%">
	    					</colgroup>
	    					<tr><td rowspan="8"><img height="152px" width="115px" src="${pageContext.request.contextPath }/staff/profileImage/${staff.sklstfNo}"></td></tr>
	    					<tr>
	    						<th>사번</th><td>${staff.sklstfNo}</td>
	    					</tr>
	    					<tr>
	    						<th>이름</th><td>${staff.sklstfNm}</td>
	    					</tr>
	    					<tr>
	    						<th>성별</th><td>${staff.sklstfSexdstn}</td>
	    					</tr>
    					</table>
  					</div>
  				</div>
  				<br />
  				<div class="pro_table">
  				<div class="sub_tit04"><span></span>인적 정보</div>                     
   				<table class="table_style02 table_center">
   					<colgroup>
   						<col width="30%">
   						<col width="70%">
   					</colgroup>
					<tr><th>생년월일</th><td>${staff.sklstfIhidnum1}</td></tr>
   					<tr><th>기본주소</th><td>${staff.sklstfAdres1}</td></tr>
   					<tr><th>상세주소</th><td>${staff.sklstfAdres2}</td></tr>
   					<tr><th>연락처</th><td>${staff.sklstfTelno}</td></tr>
   					<tr><th>국적</th><td>${staff.sklstfNlty}</td></tr>  								
   					<tr><th>임사일</th><td>${staff.sklstfEncpn}</td></tr>
   					<tr><th>고용형태</th><td>${staff.emplymSe}</td></tr>
   					<tr><th>퇴사일</th><td>${staff.sklstfRetire}</td></tr>
   				
   				</table>
   				<button id="btn1" class="btn btn-primary" value="수정">수정</button>
  				</div>
     </div>
 </div>
 
 <!-- 수정 가능한 입력란 (처음에는 숨겨져 있음) -->
<div id="editFields" style="display: none;">
 <form id="staffUpdateForm" action="/staff/staff/staffStaffUpdateData" method="post"> <!-- Add action and method -->
	<div class="container-fluid">
		<div>
			<security:authentication property="principal" var="principal" />	
			<table class="table_style03  hover_none">
			<colgroup>
				    	<col width="20%;">
				    	<col width="80%;">
				    </colgroup>
			    <tr>
			        <th>사번</th>
			        <td>
			            <input type="text" name="sklstfNo" class="form-style01 wd500" value="${staff.sklstfNo}" readonly required />
			        </td>
			    </tr>
			    <tr>
			        <th>이름</th>
			        <td>
			            <input type="text" name="sklstfNm" class="form-style01 wd500" value="${staff.sklstfNm}" readonly required />
			        </td>
			    </tr>
			    <!-- Add similar rows for other input fields -->
			    <tr>
			        <th>주소입력</th>
			        <td>
			        	<div class="address_wrap">
			        		<span class="span_tit">우편번호</span>
			          	   <input type="text" name="sklstfZip" style="width:424px; float:left; margin-right:10px;" class="form-style01" id="sample6_postcode" value="${staff.sklstfZip}" />
			        	</div>
			        	<div class="address_wrap">
			        		<span class="span_tit">주소</span>
			          	     <input type="text" name="sklstfAdres1" class="form-style01 " id="sample6_address" style="width:424px;" value="${staff.sklstfAdres1}" />
			        	</div>
			        	<div class="address_wrap">
			        		<span class="span_tit">상세주소</span>
			          	   <input type="text" name="sklstfAdres2" class="form-style01 wd500" id="sample6_detailAddress" style="width:424px;" value="${staff.sklstfAdres2}" />
			        	</div>
			        	
			        	
			        </td>
			    </tr>
			   	    <tr>
			        <th>휴대폰 번호</th>
			        <td>
			            <input type="text" name="sklstfTelno" class="form-style01 wd500" value="${staff.sklstfTelno}" />
			        </td>
			    </tr>
			    <tr>
			        <th>입사일</th>
			        <td>
			            <input type="date" name="sklstfEncpn" class="form-style01 wd500 datepicker" value="${staff.sklstfEncpn}" readonly />
			        </td>
			    </tr>
			    <tr>
			        <th>퇴직일</th>
			        <td>
			            <input type="date" name="sklstfRetire" class="form-style01 wd500 datepicker" />
			        </td>
			    </tr>
			    <tr>
			        <th>고용형태</th>
			        <td>
			            <select name="emplymSe" class="form-style01 wd500" >
			                <option value label="선택">
									<c:forEach var="emplmnForm" items="${emplmnForm}">
										<c:if test="${emplmnForm.comCodeNm eq staff.emplymSe}">
											<option selected value="${emplmnForm.comCode}">${emplmnForm.comCodeNm}</option>
										</c:if>
										<c:if test="${emplmnForm.comCodeNm ne staff.emplymSe}">
											<option value="${emplmnForm.comCode}">${emplmnForm.comCodeNm}</option>
										</c:if>
									</c:forEach>
						</select>
			        </td>
			    </tr>
			    <tr>
			        <th>국적</th>
			        <td>
			            <select name="sklstfNlty" class="form-style01 wd500">
			                <option value label="선택">
									<c:forEach var="nlty" items="${nlty}">
										<c:if test="${nlty.comCodeNm eq staff.sklstfNlty}">
											<option selected value="${nlty.comCode}">${nlty.comCodeNm}</option>
										</c:if>
										<c:if test="${nlty.comCodeNm ne staff.sklstfNlty}">
											<option value="${nlty.comCode}">${nlty.comCodeNm}</option>
										</c:if>
									</c:forEach>
						</select>
			        </td>
			    </tr>
			    <tr>
			        <th>성별</th>
			        <td>
			            <select name="sklstfSexdstn" class="form-style01 wd500">
								<option value label="선택">
									<c:forEach var="sexdstn" items="${sexdstn}">
										<c:if test="${sexdstn.comCodeNm eq staff.sklstfSexdstn}">
											<option selected value="${sexdstn.comCode}">${sexdstn.comCodeNm}</option>
										</c:if>
										<c:if test="${sexdstn.comCodeNm ne staff.sklstfSexdstn}">
											<option value="${sexdstn.comCode}">${sexdstn.comCodeNm}</option>
										</c:if>
									</c:forEach>
						</select>
			        </td>
			    </tr>
</table>
			
			
			
			
			<%-- <!-- 이름 입력 -->
			<label for="sklstfNo">사번</label> <input type="text" name="sklstfNo"
				class="form-style01" value="${staff.sklstfNo}" readonly
				required />
			
			<label for="sklstfNm">이름</label> <input type="text" name="sklstfNm"
				class="form-style01" value="${staff.sklstfNm}" readonly
				required />

			<!-- 우편번호, 기본주소, 상세주소 입력 -->
			<input type="button" onclick="sample6_execDaumPostcode()"
				value="우편번호 찾기"><br> <label for="sklstfZip">우편번호</label>
			<input type="text" name="sklstfZip" class="form-style01"
				id="sample6_postcode" value="${staff.sklstfZip}"  />

			<label for="sklstfAdres1">기본주소</label> <input type="text"
				name="sklstfAdres1" class="form-style01" id="sample6_address"
				value="${staff.sklstfAdres1}"  /> <label
				for="sklstfAdres2">상세주소</label> <input type="text"
				name="sklstfAdres2" class="form-style01" id="sample6_detailAddress"
				value="${staff.sklstfAdres2}"  />

			<!-- 휴대폰 번호 입력 -->
			<label for="sklstfTelno">휴대폰 번호</label> <input type="text"
				name="sklstfTelno" class="form-style01"
				value="${staff.sklstfTelno}"  />

			<!-- 임용일 선택 -->
			<label for="sklstfEncpn">입사일</label> <input type="date"
				name="sklstfEncpn" class="form-style01 datepicker"
				value="${staff.sklstfEncpn}" readonly  /> 
				
			<label for="sklstfRetire">퇴직일</label> <input type="date"
				name="sklstfRetire" class="form-style01 datepicker"  />

			<!-- 고용형태, 국적, 성별 선택 -->
			<label for="emplymSe">고용형태</label> <select
				name="emplymSe" class="form-style01" >
				<option value label="선택">
					<c:forEach var="emplmnForm" items="${emplmnForm}">
						<c:if test="${emplmnForm.comCodeNm eq staff.emplymSe}">
							<option selected value="${emplmnForm.comCode}">${emplmnForm.comCodeNm}</option>
						</c:if>
						<c:if test="${emplmnForm.comCodeNm ne staff.emplymSe}">
							<option value="${emplmnForm.comCode}">${emplmnForm.comCodeNm}</option>
						</c:if>
					</c:forEach>
					
			</select> <label for="sklstfNlty">국적</label> <select name="sklstfNlty"
				class="form-style01" >
				<option value label="선택">
					<c:forEach var="nlty" items="${nlty}">
					<c:if test="${nlty.comCodeNm eq staff.sklstfNlty}">
						<option selected value="${nlty.comCode}">${nlty.comCodeNm}</option>
						</c:if>
					<c:if test="${nlty.comCodeNm ne staff.sklstfNlty}">
						<option value="${nlty.comCode}">${nlty.comCodeNm}</option>
						</c:if>
					</c:forEach>
					
			</select> <label for="sklstfSexdstn">성별</label> <select name="sklstfSexdstn"
				class="form-style01" >
				<option value label="선택">
					<c:forEach var="sexdstn" items="${sexdstn}">
					<c:if test="${sexdstn.comCodeNm eq staff.sklstfSexdstn}">
						<option selected value="${sexdstn.comCode}">${sexdstn.comCodeNm}</option>
					</c:if>
					<c:if test="${sexdstn.comCodeNm ne staff.sklstfSexdstn}">
						<option value="${sexdstn.comCode}">${sexdstn.comCodeNm}</option>
					</c:if>
					</c:forEach>
			</select>	 --%>
		</div>
		<input type="submit" class="btn btn-primary ft_right mt-4" value="수정하기"/>
	</div>
	
            </form>
</div>

<!-- 수정 가능한 다른 입력란들을 추가 -->

<!-- JavaScript/jQuery 코드 -->
<script>
	$(function() {
		$("#btn1").click(function() {
			// 현재 표시되어 있는 정보를 숨김
			$("#firststaff").hide();
			// 수정 가능한 입력란을 표시
			$("#editFields").show();
		});

		// "저장" 버튼에 대한 이벤트 리스너 추가
		$("#saveChanges").click(function() {
			// 수정된 값들을 입력란에서 가져옴
			var editedsklstfNm = $("#editsklstfNm").val();
			var editedsklstfIhidnum1 = $("#editsklstfIhidnum1").val();
			// 다른 필요한 수정된 값들을 가져옴

			// 서버에 변경사항을 저장하기 위한 AJAX 요청 수행
			$.ajax({
				url : '/saveChanges', // 서버 엔드포인트에 맞게 URL 수정
				method : 'POST',
				data : {
					sklstfNo : "${staff.sklstfNo}",
					sklstfNm : editedsklstfNm,
					sklstfIhidnum1 : editedsklstfIhidnum1,
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

<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var addr = ''; // 주소 변수
						var extraAddr = ''; // 참고항목 변수

						//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							addr = data.roadAddress;
						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							addr = data.jibunAddress;
						}

						/*                 // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
						 if(data.userSelectedType === 'R'){
						 // 법정동명이 있을 경우 추가한다. (법정리는 제외)
						 // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						 if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
						 extraAddr += data.bname;
						 }
						 // 건물명이 있고, 공동주택일 경우 추가한다.
						 if(data.buildingName !== '' && data.apartment === 'Y'){
						 extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
						 }
						 // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						 if(extraAddr !== ''){
						 extraAddr = ' (' + extraAddr + ')';
						 }
						 // 조합된 참고항목을 해당 필드에 넣는다.
						 document.getElementById("sample6_extraAddress").value = extraAddr;
						
						 } else {
						 document.getElementById("sample6_extraAddress").value = '';
						 } */

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample6_postcode').value = data.zonecode;
						document.getElementById("sample6_address").value = addr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("sample6_detailAddress")
								.focus();
					}
				}).open();
	}
</script>

<script src="/resources/js/app/ljh/staffStaffUpdateData.js"></script>