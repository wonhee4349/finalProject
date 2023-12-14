<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.in01{margin-bottom:15px; width:100%;}
.in01 input{width:300px;}
p{margin:0;}
.tit_lable{width:100%; font-size:18px; color:#000;}
.span_tit{display:block; width:65px; float:left; margin-right:10px; text-align:left; line-height:37px}
.address_wrap{width:100%; height:42px;}
.wd500{width:500px;}
</style>
<div class="container-fluid">
    <div class="card2">
        <div class="sub_tit02">교직원 등록</div>
        <div class="sub_top_wrap">
        </div>
        <security:authentication property="principal" var="principal"/>
        <div id="all">
            <form id="staffForm" action="/staff/staff/staffStaffData" method="post"> <!-- Add action and method -->
            <input type="hidden" name="sklstfNo" class="form-style01" placeholder="자동 생성 됩니다" readonly/> 
            <input type="hidden" name="sklstfPassword" class="form-style01" placeholder="자동 생성 됩니다" readonly/>             
           
           
					<table class="table_style03 hover_none">
					 
				    <colgroup>
				    	<col width="20%;">
				    	<col width="80%;">
				    </colgroup>

					    <tr>
					        <th>이름</th>
					        <td>
					            <input type="text" name="sklstfNm" id="staffNm" class="form-style01 wd500" placeholder="이름" required/>
					        </td>
					    </tr>
					    <tr>
					        <th>주민등록번호</th>
					        <td style="text-align:left;">
					            <input type="text" name="sklstfIhidnum1" id="staffIhidnum1" class="form-style01 wd500" maxlength="6" required style="display: inline-block; width:170px;"/>
					            - <input type="password" name="sklstfIhidnum2" id="staffIhidnum2" class="form-style01 wd500" maxlength="7"  required style="display: inline-block; width:170px;"/>
					        </td>
					    </tr>
					    <tr>
					        <th>주소</th>
					        <td>
					            <!-- 우편번호, 기본주소, 상세주소 입력 -->
					            <div class="address_wrap">
					            
						            <span class="span_tit">우편번호</span>
						            <input type="text" name="sklstfZip"  class="form-style01 wd500" id="sample6_postcode" style="width:310px; float:left; margin-right:10px;" required/>
						            <input type="button" onclick="sample6_execDaumPostcode()" class="btn btn-primary " style="width:110px; float:left;" value="우편번호 찾기">
					            </div>
					            <div class="address_wrap">
					            
					            <span class="span_tit">기본주소</span>
					            <input type="text" name="sklstfAdres1" style="width:426px;"  class="form-style01" id="sample6_address" required/>
					            </div>
					            <div class="address_wrap">
					            <span class="span_tit">상세주소</span>
					            <input type="text" name="sklstfAdres2"  class="form-style01 wd500" id="sample6_detailAddress" style="width:426px" required/>
					            </div>
					        </td>
					    </tr>
					    <tr>
					        <th>휴대폰 번호</th>
					        <td>
					            <!-- 휴대폰 번호 입력 -->

					            <input type="text" name="sklstfTelno" id="staffTelno" class="form-style01 wd500" required/>
					        </td>
					    </tr>
					    <tr>
					        <th>임용일</th>
					        <td>
					            <!-- 임용일 선택 -->

					            <input type="date" name="sklstfEncpn" id="staffEmplmn" class="form-style01 wd500 datepicker"  required/>
					        </td>
					    </tr>
					    <tr>
					        <th>고용형태</th>
					        <td>
					            <!-- 고용형태 선택 -->

					            <select id="staffemplymSe" name="emplymSe" class="form-style01 wd500" required>
					                <option value label="선택"></option>
					                <c:forEach var="emplmnForm" items="${emplmnForm}">
					                    <option value="${emplmnForm.comCode}">${emplmnForm.comCodeNm}</option>
					                </c:forEach>
					            </select>
					        </td>
					    </tr>
					    <tr>
					        <th>국적</th>
					        <td>
					            <!-- 국적 선택 -->
					            <select id="staffNlty" name="sklstfNlty" class="form-style01 wd500" required>
					                <option value label="선택"></option>
					                <c:forEach var="nlty" items="${nlty}">
					                    <option value="${nlty.comCode}">${nlty.comCodeNm}</option>
					                </c:forEach>
					            </select>
					        </td>
					    </tr>
					    <tr>
					        <th>성별</th>
					        <td>
					            
					                <select id="staffSexdstn" name="sklstfSexdstn" class="form-style01 wd500" required>
					                	<option value label="선택">
					                    <c:forEach var="sexdstn" items="${sexdstn}">
					                        <option value="${sexdstn.comCode}">${sexdstn.comCodeNm}</option>
					                    </c:forEach>
					                </select>    
					              </td>
					              </tr>
					  </table>      
					           
           
           
   
         

                <!-- 나머지 입력란들은 유사하게 추가 -->
				<button type="button" id="inputDataStaf" class="btn btn-success ft_left"  style="margin-top:20px;">일괄입력</button>
                <input type="submit" class="btn btn-primary ft_right mt-4" value="등록하기"/>
            </form>
        </div>
    </div>
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
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
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>

<script src="/resources/js/app/ljh/staffStaffInsertData.js"></script>

