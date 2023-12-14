<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
		        
<style>
.sub_tit04{font-size: 18px;color: #304b73;padding-left:0px;font-weight: 600;width: 100%;float:left; text-align:left; margin-bottom:5px;}   
.sub_tit04 span{
	width: 8px;
    height: 8px;
    background: #304b73;
    display: block;
    float: left;
    margin-right: 8px;
    position: relative;
    top: 9px;}
.profile_img{width:80%; margin:0 auto}
.profile_img img {width:100%; height:100%;} 
.table_wd70 {width:70% !important; float:left; }
.table_style02 th {width:15%;}
.table_style02 td {width:30%;}
.table_style04 th {width:10%;}
.table_style04 td {width:15%;}
div label {margin-top: 10px; margin-bottom: 0px;}
.div_left {display: inline-block; width:30%; float:left;}
.div_right {display: inline-block; width:60%; float:right;}
.pass{width:70%; margin-left: 20px;}
.pd20{padding:20px;}
.img_upload{background:#f2f2f2; padding:20px; text-align:center;}
</style>        
    
<!-- Begin Page Content -->
<div class="container-fluid">					
	<security:authentication property="principal" var="user"/>					
	<div class="mypage_wrap">
		<div class="sub_tit02">마이페이지</div>
		
		<!-- 나의정보 -->
		<div class="my_content">			
			<div class="col-xl-12">		
				<div class="card3 row shadow mb-4" style="height:750px; padding:30px 50px">
					<div class="card-header2 py-3">
						<h6 class="m-0 font-weight-bold sub_tit03" style="display:inline; line-height: 42px;">${user.realUser.stdntNm }님의 마이페이지</h6>
						<a href="javascript:;" class="ft_right" id="changePass" style="line-height: 42px;">비밀번호 변경</a>
<!-- 						<a href="script:;" class="ft_right" data-toggle="modal" data-target="#modifyPass" style="line-height: 42px;">비밀번호 변경</a> -->
						<span class="ft_right" style="line-height: 42px;">&nbsp;/&nbsp;</span>			
						<a href="script:;" class="ft_right" data-toggle="modal" data-target="#modal_open" style="line-height: 42px;">정보 수정</a>
					</div>
					<div class="my_profile_wrap02">
						<div class="sub_tit04 mt-4" style="margin-top: 10px;"><span></span>인적 정보</div>     			
						<table class="table_style03 table_center  hover_none " style="width:100%; height:300px;">	
		   					<colgroup>
								<col width="24%">
								<col width="18%">
								<col width="20%">
								<col width="18%">
								<col width="20%">

							</colgroup>
		   					
		   					<tr>
		   						<td rowspan="5"><div class="profile_img"><img src="${pageContext.request.contextPath }/student/profileImage"></div></td>
		   						<th>이름</th><td>${studentInfo.stdntNm}</td>
		   						<th>학번</th><td>${studentInfo.stdntNo}</td>		   						
		   					</tr>
		   					<tr>
		   						<th>생년월일</th><td>${studentInfo.stdntIhidnum1}</td>
		   						<th>연락처</th><td>${studentInfo.stdntTelno}</td>
		   					</tr>
		   					<tr>
		   						<th>국적</th><td>${studentInfo.nltySe}</td>
		   						<th>성별</th><td>${studentInfo.sexdstnSe}</td>
		   					</tr>			
		   					<tr>
		   						<th>은행명</th><td>${studentInfo.bankSe}</td>
		   						<th>계좌번호</th><td>${studentInfo.stdntAcnutno}</td>
		   					</tr>
		   					<tr>
			   					<th>기본주소</th><td>${studentInfo.stdntAdres1}</td>
			   					<th>상세주소</th><td>${studentInfo.stdntAdres2}</td>
		   					</tr>
	  					</table>
					</div>
					<p style="clear:both"></p>
					<div class="pro_table">
						<div class="sub_tit04 mt-4"><span></span>학적 정보</div>  
						<table class="table_style03 table_center hover_none" style="width:100%;">
							<colgroup>
								<col width="8%">
								<col width="16%">
								<col width="8%">
								<col width="16%">
								<col width="8%">
								<col width="16%">
								<col width="8%">
								<col width="16%">
							</colgroup>
							<tr>
								<th>학적 상태</th><td>${studentRegister.schoolRegisterHistory.sknrgsSe}</td>
								<th>학년</th><td>${studentRegister.sknrgSttusGrade}학년</td>
		   						<th>단과대</th><td>${studentRegister.subject.college.clgNm}</td>
		   						<th>1전공</th><td>${studentRegister.sknrgSttusMajor1}</td>
							</tr>
							<tr style="margin-top: 10px;">
								<th>입학일</th><td>${studentRegister.sknrgSttusEntsch}</td>
		   						<th>졸업일</th><td>${studentRegister.sknrgSttusGrdtn}</td>		   						
		   						<th>2전공</th><td>${studentRegister.sknrgSttusMajor2}</td>
		   						<th>부전공</th><td>${studentRegister.sknrgSttusMinor}</td>
		   					</tr>
		   				</table>
					</div>
					
					<div>
						<input type="hidden" id="tel" value="${studentInfo.stdntTelno}">
						<input type="hidden" id="add1" value="${studentInfo.stdntZip}">
						<input type="hidden" id="add2" value="${studentInfo.stdntAdres1}">
						<input type="hidden" id="add3" value="${studentInfo.stdntAdres2}">
						<input type="hidden" id="bank1" value="${studentInfo.bankNo}">
						<input type="hidden" id="bank2" value="${studentInfo.stdntAcnutno}">
					</div>
				</div>		
			</div>
		</div>
	</div>
 </div>
<!-- /.container-fluid -->

<!-- 수정 모달 창 -->
	<div class="modal fade" id="modal_open" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	    <div class="modal-dialog modal-m" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">수정</h5>
	              <button class="close modifyMypage" type="button" data-bs-dismiss="modal" aria-label="Close">
					    <span aria-hidden="true">×</span>
				  </button>

	            </div>
	            <div class="modal-body pd20">
	            	<form id="updateMypage" action="/student/mypage/updateMypage" method="post">
	            		<input type="hidden" value="${studentInfo.stdntNo}" name="stdntNo">
	            		
	            		
	            		<label for="profileImage">프로필 이미지</label>
	            		<div class="img_upload">	            			
							<input type="file" id="profileImage" name="profileImage" style="width:200px;"/>
    					</div>
		            	<label for="stdntTelno">연락처</label>
						<input type="text" id="stdntTelno" name="stdntTelno" class="form-style01" required/>
						
						<label for="sample6_postcode">우편번호</label><br/>
			            <input type="text" id="sample6_postcode" name="stdntZip" class="form-style01" required style="display: inline-block; width: 71%;"/>
						<input type="button" id="zipBtn" value="우편번호 찾기" class="btn btn-primary" style="margin-left: 15px;"><br/>
						
						<label for="sample6_address">기본주소</label>
			            <input type="text" id="sample6_address" name="stdntAdres1" class="form-style01 long_form" required/>
			            
			            <label for="sample6_detailAddress">상세주소</label>
			            <input type="text" id="sample6_detailAddress" name="stdntAdres2" class="form-style01 long_form"/>
			            
			            <div class="div_left">
				            <label for="bankSe">은행</label>
				            <select id="bankSe" name="bankSe" class="form-style01" required>
								<c:forEach items="${bankSe}" var="bank">
								    <option value="${bank.comCode}" label="${bank.comCodeNm}"
								            <c:if test="${bank.comCode eq studentInfo.bankNo}">selected</c:if>>
								    </option>
								</c:forEach>
							</select>
						</div>
						
						<div class="div_right">
				            <label for="inputStdntAcnutno">계좌번호</label>
							<input id="stdntAcnutno" name="stdntAcnutno" type="text" class="form-style01" required/>
						</div>
						
						<input type="submit" value="저장" class="btn btn-primary ft_right applyBtn" style="margin-top:20px; margin-right:10px;">
					</form>
	            </div>
	        </div>
	    </div>
	</div>
<!-- 모달끝 -->

<!-- 모달 창 추가 -->
	<div class="modal fade" id="modifyPass" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	    <div class="modal-dialog modal-sm" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">비밀번호 변경</h5>
	              <button class="close modifyPassClose" type="button" data-bs-dismiss="modal" aria-label="Close">
					    <span aria-hidden="true">×</span>
				  </button>

	            </div>
<!-- 	            <div class="modifyPass-body "> -->
<!-- 	            	<form id="updatePass" action="" method="post"> -->
<%-- 	            		<input type="hidden" value="${studentInfo.stdntNo}" name="stdntNo"> --%>
	            		
<!-- 	            		<label for="inputStdntAdres1" style="margin-left:20px;">* 현재 비밀번호 *</label> -->
<!-- 			            <input type="password" id="pass1" name="realPass" class="form-style01 long_form pass" required/> -->
			            
<!-- 			            <label for="inputStdntAdres1" style="margin-left:20px;">* 변경 비밀번호 *</label> -->
<!-- 			            <input type="password" id="pass2" name="checkPass" class="form-style01 long_form pass" required/> -->
			            
<!-- 			            <input type="submit" value="저장" class="btn btn-primary ft_right applyBtn" style="margin-right: 16px; margin-top: 25px; margin-bottom: 13px;"> -->
<!-- 	            	</form> -->
<!-- 	            </div> -->
	        </div>
	    </div>
	</div>
<!-- 모달끝 -->



<script src="/resources/js/app/msy/studentMypage.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.3.0/jquery.form.min.js" integrity="sha384-qlmct0AOBiA2VPZkMY3+2WqkHtIQ9lSdAsAn5RUJD/3vA5MKDgSGcdmIv4ycVxyn" crossorigin="anonymous"></script>
