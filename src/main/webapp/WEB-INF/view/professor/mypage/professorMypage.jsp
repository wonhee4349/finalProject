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
.profile_img{height:165px;}
.profile_img img {width:100%; height:100%;} 
.table_wd70 {width:70% !important; float:left; }
.table_style03 th {width:13%;}
.table_style03 td {width:20%;}
.table_style02 th {width:15%;}
.table_style02 td {width:30%;}
.table_style04 th {width:10%;}
.table_style04 td {width:15%;}
div label {margin-top: 10px; margin-bottom: 0px;}
.div_left {display: inline-block; width:30%; float:left;}
.div_right {display: inline-block; width:60%; float:right;}
.pass{width:70%; margin-left: 20px;}
.card3{height:740px;}
</style>        
    
<!-- Begin Page Content -->
<div class="container-fluid">					
	<security:authentication property="principal" var="user"/>					
	<div class="mypage_wrap">
		<div class="sub_tit02">마이페이지</div>
		
		<!-- 나의정보 -->
		<div class="my_content">			
			<div class="col-xl-12">		
				<div class="card3 row shadow mb-4">
					<div class="card-header2 py-3">
						<h6 class="m-0 font-weight-bold sub_tit03" style="display:inline; line-height: 42px;">${user.realUser.prfsorNm }님의 마이페이지</h6>
							<a href="javascript:;" class="ft_right" id="changePass" style="line-height: 42px;">비밀번호 변경</a>
<!-- 						<a href="script:;" class="ft_right" data-toggle="modal" data-target="#modifyPass" style="line-height: 42px;">비밀번호 변경</a> -->
						<span class="ft_right" style="line-height: 42px;">&nbsp;/&nbsp;</span>			
						<a href="script:;" class="ft_right" data-toggle="modal" data-target="#modal_open" style="line-height: 42px;">정보 수정</a>
					</div>
					<div class="my_profile_wrap02" style="margin-top: 30px;">
						<div class="sub_tit05" style="margin-top: 10px;"><span></span>인적 정보</div>     
						<table class="table_style01 table_center ft_left hover_none">							
		   					<tr>
		   						<td rowspan="5"><div class="profile_img" style="margin-top: 10px;"><img src="${pageContext.request.contextPath }/professor/profileImage"></div></td>
		   						<th>이름</th><td>${professorInfo.prfsorNm}</td>
		   						<th>사번</th><td>${professorInfo.prfsorNo}</td>
		   					<tr>
		   						<th>생년월일</th><td>${professorInfo.prfsorIhidnum1}</td>
		   						<th>연락처</th><td>${professorInfo.prfsorTelno}</td>
		   					</tr>
		   					<tr>		   						
		   						<th>국적</th><td>${professorInfo.prfsorNlty}</td>
		   						<th>성별</th><td>${professorInfo.sexdstnSe}</td>
		   					</tr>				
		   					<tr>
			   					<th>기본주소</th><td>${professorInfo.prfsorAdres1}</td>
			   					<th>상세주소</th><td>${professorInfo.prfsorAdres2}</td>
		   					</tr>
		   					<tr>
		   						<th>고용일</th><td>${professorInfo.prfsorEmplmn}</td>
		   						<th>퇴사일</th><td>${professorInfo.prfsorRetire}</td>	
		   					</tr>
	  					</table>
					</div>
					<br/>
					<div class="pro_table">
						<div class="sub_tit05"><span></span>학적 정보</div>  
						<table class="table_style01 table_center ft_left hover_none" style="width:100%;">
							<tr>
								<th>고용 형태</th><td>${professorInfo.prfsorEmplynForm}</td>
		   						<th>소속 단과대</th><td>${professorInfo.subject.college.clgNm}</td>
		   						<th>소속 학과</th><td>${professorInfo.subjctNm}</td>
							</tr>
		   				</table>
					</div>
					
					<div>
						<input type="hidden" id="tel" value="${professorInfo.prfsorTelno}">
						<input type="hidden" id="add1" value="${professorInfo.prfsorZip}">
						<input type="hidden" id="add2" value="${professorInfo.prfsorAdres1}">
						<input type="hidden" id="add3" value="${professorInfo.prfsorAdres2}">
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
	            <div class="modal-body ">
	            	<form id="updateMypage" action="/professor/mypage/updateMypage" method="post">
	            		<input type="hidden" value="${professorInfo.prfsorNo}" name="prfsorNo">
	            		
	            		<label for="profileImage">프로필 이미지</label>
						<input type="file" id="profileImage" name="profileImage" class="form-style01"/>
	            		
		            	<label for="prfsorTelno">연락처</label>
						<input type="text" id="prfsorTelno" name="prfsorTelno" class="form-style01" required/>
						
						<label for="sample6_postcode">우편번호</label><br/>
			            <input type="text" id="sample6_postcode" name="prfsorZip" class="form-style01" required style="display: inline-block; width: 71%;"/>
						<input type="button" id="zipBtn" value="우편번호 찾기" class="btn btn-primary" style="margin-left: 15px;"><br/>
						
						<label for="sample6_address">기본주소</label>
			            <input type="text" id="sample6_address" name="prfsorAdres1" class="form-style01 long_form" required/>
			            
			            <label for="sample6_detailAddress">상세주소</label>
			            <input type="text" id="sample6_detailAddress" name="prfsorAdres2" class="form-style01 long_form"/>
						
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
<%-- 	            		<input type="hidden" value="${professorInfo.prfsorNo}" name="prfsorNo"> --%>
	            	
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



<script src="/resources/js/app/msy/professorMypage.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.3.0/jquery.form.min.js" integrity="sha384-qlmct0AOBiA2VPZkMY3+2WqkHtIQ9lSdAsAn5RUJD/3vA5MKDgSGcdmIv4ycVxyn" crossorigin="anonymous"></script>
