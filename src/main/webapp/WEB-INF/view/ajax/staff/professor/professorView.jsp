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
.in01{margin-bottom:15px; width:100%;}
.in01 input{width:300px;}
p{margin:0;}
.tit_lable{width:100%; font-size:18px; color:#000;}
.span_tit{display:block; width:65px; float:left; margin-right:10px; text-align:left; line-height:37px}
.address_wrap{width:100%; height:42px;}
.wd500{width:500px;}
</style>

<div class="homework_wrap mb-4" id="firstprofessor">
	<div class="mt-4 mb-4 table_center">
		<div class="profile_img">
			<div class="pro_img" style="border-bottom: none;">
				<div class="sub_tit04">
					<span></span>교수 정보
				</div>
				<table class="table_style02 table_center  hover_none">
					<colgroup>
						<col width="20%">
						<col width="20%">
						<col width="60%">
					</colgroup>
					<tr>
						<td rowspan="8"><img height="152px" width="115px"
							src="${pageContext.request.contextPath }/staff/profileImage/${professor.prfsorNo}"></td>
					</tr>
					<tr>
						<th>교번</th>
						<td>${professor.prfsorNo}</td>
					</tr>
					<tr>
						<th>교수명</th>
						<td>${professor.prfsorNm}</td>
					</tr>
					<tr>
						<th>학과</th>
						<td>${professor.subject.subjctNm}</td>
					</tr>
				</table>
			</div>
		</div>
		<br />
		<div class="pro_table">
			<div class="sub_tit04">
				<span></span>인적 정보
			</div>
			<table class="table_style02 table_center hover_none">
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<tr>
					<th>생년월일</th>
					<td>${professor.prfsorIhidnum1}</td>
				</tr>
				<tr>
					<th>기본주소</th>
					<td>${professor.prfsorAdres1}</td>
				</tr>
				<tr>
					<th>상세주소</th>
					<td>${professor.prfsorAdres2}</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>${professor.prfsorTelno}</td>
				</tr>
				<tr>
					<th>국적</th>
					<td>${professor.prfsorNlty}</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>${professor.sexdstnSe}</td>
				</tr>
				<tr>
					<th>임용일</th>
					<td>${professor.prfsorEmplmn}</td>
				</tr>
				<tr>
					<th>고용형태</th>
					<td>${professor.prfsorEmplynForm}</td>
				</tr>
				<tr>
					<th>퇴직일</th>
					<td>${professor.prfsorRetire}</td>
				</tr>

			</table>
			<button type="button" id="btn1" class="btn btn-primary" value="수정" style="width:99px;">수정하기</button>

		</div>
	</div>
</div>

<!-- 수정 가능한 입력란 (처음에는 숨겨져 있음) -->
<div id="editFields" style="display: none;">
 <form id="professorUpdateForm" action="/staff/professor/staffProfessorUpdateData" method="post"> <!-- Add action and method -->
	<div class="container-fluid">
		<div>
			<div class="sub_top_wrap"></div>
			<security:authentication property="principal" var="principal" />		
			<%-- <!-- 이름 입력 -->
			<div class="in01">
				<label  class="tit_lable" style="display:none" for="prfsorNo">교번</label>
				<input type="hidden" name="prfsorNo" class="form-style01" value="${professor.prfsorNo}" readonly required />
				</div>
			<div class="in01">
			<label  class="tit_lable" for="prfsorNm">이름</label> <input type="text" name="prfsorNm"
				class="form-style01" value="${professor.prfsorNm}" readonly
				required />
			</div>
			
			<!-- 우편번호, 기본주소, 상세주소 입력 -->
			<div class="in01">
			
			<label for="prfsorZip" class="tit_lable">주소입력</label>
			<input type="text" name="prfsorZip" class="form-style01" id="sample6_postcode" style="width:300px; float:left; margin-right:10px;" value="${professor.prfsorZip}"  />
			<input class="ft_left btn btn-secondary" style="width:130px;" type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
			
			
			<input type="text" style="width:500px;"
				name="prfsorAdres1" class="form-style01" id="sample6_address"
				value="${professor.prfsorAdres1}"  />
				<label for="prfsorAdres2">상세주소</label>
				<input type="text" name="prfsorAdres2" class="form-style01" id="sample6_detailAddress"
				value="${professor.prfsorAdres2}"  />
			</div>
			
			
			<!-- 휴대폰 번호 입력 -->
			<div class="in01">
			<label  class="tit_lable" for="prfsorTelno">휴대폰 번호</label> <input type="text"
				name="prfsorTelno" class="form-style01"
				value="${professor.prfsorTelno}"  />
			</div>
			<!-- 임용일 선택 -->
			<div class="in01">
			<label  class="tit_lable" for="prfsorEmplmn">임용일</label> <input type="date"
				name="prfsorEmplmn" class="form-style01 datepicker"
				value="${professor.prfsorEmplmn}" readonly  /> 
			</div>
			
			<div class="in01">	
			<label  class="tit_lable" for="prfsorRetire">퇴직일</label> <input type="date"
				name="prfsorRetire" class="form-style01 datepicker"  />
			</div>
			
			<!-- 고용형태, 국적, 성별 선택 -->
			<div class="in01">
			<label  class="tit_lable" for="prfsorEmplynForm">고용형태</label> <select
				name="prfsorEmplynForm" class="form-style01" >
				<option value label="선택">
					<c:forEach var="emplmnForm" items="${emplmnForm}">
						<c:if test="${emplmnForm.comCodeNm eq professor.prfsorEmplynForm}">
							<option selected value="${emplmnForm.comCode}">${emplmnForm.comCodeNm}</option>
						</c:if>
						<c:if test="${emplmnForm.comCodeNm ne professor.prfsorEmplynForm}">
							<option value="${emplmnForm.comCode}">${emplmnForm.comCodeNm}</option>
						</c:if>
					</c:forEach>
					
			</select>
			</div>
			
			<div class="in01">
			<label for="prfsorNlty">국적</label> <select name="prfsorNlty"
				class="form-style01" >
				<option value label="선택">
					<c:forEach var="nlty" items="${nlty}">
					<c:if test="${nlty.comCodeNm eq professor.prfsorNlty}">
						<option selected value="${nlty.comCode}">${nlty.comCodeNm}</option>
						</c:if>
					<c:if test="${nlty.comCodeNm ne professor.prfsorNlty}">
						<option value="${nlty.comCode}">${nlty.comCodeNm}</option>
						</c:if>
					</c:forEach>
					
			</select>
			</div>
			
			<div class="in01">
			<label for="sexdstnSe">성별</label> <select name="sexdstnSe"
				class="form-style01" >
				<option value label="선택">
					<c:forEach var="sexdstn" items="${sexdstn}">
					<c:if test="${sexdstn.comCodeNm eq professor.sexdstnSe}">
						<option selected value="${sexdstn.comCode}">${sexdstn.comCodeNm}</option>
					</c:if>
					<c:if test="${sexdstn.comCodeNm ne professor.sexdstnSe}">
						<option value="${sexdstn.comCode}">${sexdstn.comCodeNm}</option>
					</c:if>
					</c:forEach>
			</select>
			</div>
			
			<!-- 학과 코드 선택 -->
			<div class="in01">
			<label for="subjctNo">학과 코드</label> <select name="subjctNo"
				class="form-style01" >
				<option value label="선택">				
					<c:forEach var="subject" items="${subject}">
					<c:if test="${subject.subjctNm eq professor.subject.subjctNm}">
						<option selected value="${subject.subjctNo}">${subject.college.clgNm} ${subject.subjctNm }</option>
					</c:if>
					<c:if test="${subject.subjctNm ne professor.subject.subjctNm}">
						<option value="${subject.subjctNo}">${subject.college.clgNm} ${subject.subjctNm }</option>
					</c:if>
					</c:forEach>
			</select>	
			</div>		  --%>
			

				  <!-- 교번 -->
				<label  class="tit_lable" style="display:none" for="prfsorNo">교번</label>
				<input type="hidden" name="prfsorNo" class="form-style01" value="${professor.prfsorNo}" readonly required />
			
				<!-- 교수 정보 테이블 -->
				<table class="table_style03 hover_none">
				  
				    <colgroup>
				    	<col width="20%;">
				    	<col width="80%;">
				    </colgroup>
				       <!-- 이름 -->
				    <tr>
				        <th><label for="prfsorNm">이름</label></th>
				        <td>
				            <input type="text" name="prfsorNm" class="form-style01 wd500" value="${professor.prfsorNm}" readonly required />
				        </td>
				    </tr>
				
				    <!-- 주소 입력 -->
				    <tr>
				        <th><label for="prfsorZip">주소입력</label></th>
				        <td>
				            <!-- 주소 입력 부분 -->
				           <div class="address_wrap">
					           	<span class="span_tit">우편번호</span>
					           	<input type="text" name="prfsorZip" class="form-style01 wd500" id="sample6_postcode" style="width:300px; float:left; margin-right:10px;" value="${professor.prfsorZip}" />
					           	 <input class="ft_left btn btn-secondary" style="width:110px;" type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
					           </div> 
				            <div class="address_wrap">
				         	    <span class="span_tit">주소</span><input type="text" style="width:424px;" name="prfsorAdres1" class="form-style01" id="sample6_address" value="${professor.prfsorAdres1}" />
				            </div>  
				             	<div class="address_wrap">
				             	<span class="span_tit">상세주소</span><input type="text" style="width:424px;"  name="prfsorAdres2" class="form-style01" id="sample6_detailAddress" value="${professor.prfsorAdres2}" />
				        	 </div> 
				        </td>
				    </tr>
				
				    <!-- 휴대폰 번호 입력 -->
				    <tr>
				        <th><label for="prfsorTelno">휴대폰 번호</label></th>
				        <td>
				            <input type="text" name="prfsorTelno" class="form-style01 wd500" value="${professor.prfsorTelno}" />
				        </td>
				    </tr>
				
				    <!-- 임용일 선택 -->
				    <tr>
				        <th><label for="prfsorEmplmn">임용일</label></th>
				        <td>
				            <input type="date" name="prfsorEmplmn" class="form-style01 datepicker wd500" value="${professor.prfsorEmplmn}" readonly />
				        </td>
				    </tr>
				
				    <!-- 퇴직일 선택 -->
				    <tr>
				        <th><label for="prfsorRetire">퇴직일</label></th>
				        <td>
				            <input type="date" name="prfsorRetire" class="form-style01 datepicker wd500" />
				        </td>
				    </tr>
				<tr>
				    <th><label for="prfsorEmplynForm">고용형태</label></th>
				    <td>
				        <select name="prfsorEmplynForm" class="form-style01 wd500">
				            <option value label="선택"></option>
				            <c:forEach var="emplmnForm" items="${emplmnForm}">
				                <c:if test="${emplmnForm.comCodeNm eq professor.prfsorEmplynForm}">
				                    <option selected value="${emplmnForm.comCode}">${emplmnForm.comCodeNm}</option>
				                </c:if>
				                <c:if test="${emplmnForm.comCodeNm ne professor.prfsorEmplynForm}">
				                    <option value="${emplmnForm.comCode}">${emplmnForm.comCodeNm}</option>
				                </c:if>
				            </c:forEach>
				        </select>
				    </td>
				</tr>
				
				<tr>
				    <th><label for="prfsorNlty">국적</label></th>
				    <td>
				        <select name="prfsorNlty" class="form-style01 wd500">
				            <option value label="선택"></option>
				            <c:forEach var="nlty" items="${nlty}">
				                <c:if test="${nlty.comCodeNm eq professor.prfsorNlty}">
				                    <option selected value="${nlty.comCode}">${nlty.comCodeNm}</option>
				                </c:if>
				                <c:if test="${nlty.comCodeNm ne professor.prfsorNlty}">
				                    <option value="${nlty.comCode}">${nlty.comCodeNm}</option>
				                </c:if>
				            </c:forEach>
				        </select>
				    </td>
				</tr>
				<tr>
				    <th><label for="sexdstnSe">성별</label></th>
				    <td>
				        <select name="sexdstnSe" class="form-style01 wd500">
				            <option value label="선택"></option>
				            <c:forEach var="sexdstn" items="${sexdstn}">
				                <c:if test="${sexdstn.comCodeNm eq professor.sexdstnSe}">
				                    <option selected value="${sexdstn.comCode}">${sexdstn.comCodeNm}</option>
				                </c:if>
				                <c:if test="${sexdstn.comCodeNm ne professor.sexdstnSe}">
				                    <option value="${sexdstn.comCode}">${sexdstn.comCodeNm}</option>
				                </c:if>
				            </c:forEach>
				        </select>
				    </td>
				</tr>
				
				<!-- 학과 코드 선택 -->
				<tr>
				    <th><label for="subjctNo">학과 코드</label></th>
				    <td>
				        <select name="subjctNo" class="form-style01 wd500">
				            <option value label="선택"></option>
				            <c:forEach var="subject" items="${subject}">
				                <c:if test="${subject.subjctNm eq professor.subject.subjctNm}">
				                    <option selected value="${subject.subjctNo}">${subject.college.clgNm} ${subject.subjctNm }</option>
				                </c:if>
				                <c:if test="${subject.subjctNm ne professor.subject.subjctNm}">
				                    <option value="${subject.subjctNo}">${subject.college.clgNm} ${subject.subjctNm }</option>
				                </c:if>
				            </c:forEach>
				        </select>
				    </td>
				</tr>
				
				</table>
								
							
			
			
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
			$("#firstprofessor").hide();
			// 수정 가능한 입력란을 표시
			$("#editFields").show();
		});

		// "저장" 버튼에 대한 이벤트 리스너 추가
		$("#saveChanges").click(function() {
			// 수정된 값들을 입력란에서 가져옴
			var editedPrfsorNm = $("#editPrfsorNm").val();
			var editedPrfsorIhidnum1 = $("#editPrfsorIhidnum1").val();
			// 다른 필요한 수정된 값들을 가져옴

			// 서버에 변경사항을 저장하기 위한 AJAX 요청 수행
			$.ajax({
				url : '/saveChanges', // 서버 엔드포인트에 맞게 URL 수정
				method : 'POST',
				data : {
					prfsorNo : "${professor.prfsorNo}",
					prfsorNm : editedPrfsorNm,
					prfsorIhidnum1 : editedPrfsorIhidnum1,
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

<script src="/resources/js/app/ljh/staffProfessorUpdateData.js"></script>
