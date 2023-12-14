<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.secondModal {margin-top: 200px;}
.sub_tit04{font-size: 18px;color: #304b73;padding-left:0px;font-weight: 600;width: 100%;float:left; text-align:left; margin-bottom:5px;}   
.sub_tit04 span{    width: 8px;
    height: 8px;
    background: #304b73;
    display: block;
    float: left;
    margin-right: 8px;
    position: relative;
    top: 9px;}
.profile_img{width:30%; float:left; height:213px;}   
.profile_img img {width:100%; height:100%;} 
.table_left {width:30% !important; float:left; }
.table_middle {width:30% !important; float:inherit; }
.table_right {width:30% !important; float:right; }
.tab-content {min-height: 700px;}
.minHeight600 {min-height: 600px;}
.minHeight700 {min-height: 700px;}
.div_left {display: inline-block; width:44%; float:left;}
.div_right {display: inline-block; width:40%; float:right;}
.form-style01 {width:185px;}
.long_form {width:400px;}
label{margin-top: 10px;}
.div_outer{margin-right: 35px; margin-left: 40px}
.line_box{border:1px solid #c9cfdf; text-align:Center; padding:20px;}
.down{
    background: #f9f9f9;
    width: 283px;
    border-radius: 0;
    margin: 0 auto;
    padding: 8px 50px;
    text-align: center;
    color: #000000;
    margin-bottom: 5px;
}
</style>

<!-- Begin Page Content -->
<div class="container-fluid">
	<div class="card2">
		<div class="sub_tit02">신입생 등록</div>
		
		<!-- 신입생 리스트 -->
		<div style="min-height: 270px; max-height: 270px;overflow-y: scroll;">
			<table class="table table_style01 mt-4 table_center" id="dataTable" width="100%" cellspacing="0">
				<thead>
					<colgroup>
						<col width="5%">
                     	<col width="20%">
                     	<col width="20%">
                     	<col width="10%">
                     	<col width="25%">
                     	<col width="10%">
					</colgroup>
					<tr>
						<th>번호</th>
						<th>학번</th>
			            <th>이름</th>
			            <th>학과</th>
			            <th>입학일</th>
			            <th>입학형태</th>
					</tr>
				</thead>
				<tbody id="listBody"></tbody>                      
			</table>
		</div>
		
		
		<!-- 신입생 등록 입력 폼 -->
		<div style="margin-top: 20px;">
			<div class="class_list_wrap">
				<div class="list_top">
					<button class="gray_btn small_btn active" data-tab="tab-1">일괄 등록</button>
					<button class="gray_btn small_btn" data-tab="tab-2">개별 등록</button>
				</div>
			</div>
			
			<div class="tab_contents2" style="height:500px;">
			
				<div id="tab-1" class="tab-content current" style="min-height: 500px;">			
					<form id="excelUploadForm" name="excelUploadForm" enctype="multipart/form-data" method="post" action="/staff/freshMan/ajax/excelUpload">
						<div class="contents">
							<div class="tit04">양식 다운로드</div>
							<div class="blue_box mb-4" style="padding:20px;">
								<div><span style="margin-right:5px;"><i class="fas fa-download fa-fw" ></i></span>양식 다운로드</div>
								<a href="/resources/excel/fresh_man_form.xlsx">신입생_엑셀_양식.xlsx</a>
			 				</div>
							<div class="tit04">파일 업로드</div>
							<dl class="vm_name line_box">
								<dd class="down"><input id="excelFile" type="file" name="excelFile"/></dd>
								<div>첨부파일은 한개만 등록 가능합니다.</div>
							</dl>        
						</div>            
						<div class="bottom">
							<button type="button" id="addExcelImpoartBtn" class="btn btn-primary ft_right" onclick="check()"><span>등록</span></button> 
						</div>
					</form> 
				</div>
	
	
				<div id="tab-2" class="tab-content" style="min-height: 500px;">
					<div class="div_left div_outer">
						<label for="inputStdntNm">학생명</label>
						<input type="text" id="inputStdntNm" class="form-style01" required/>
						
						<label for="stdntIhidnum1">주민등록번호</label><br/>
						<input type="text" id="inputStdntIhidnum1" class="form-style01" required style="display: inline-block; width:170px;"/> - 
						<input type="text" id="inputStdntIhidnum2" class="form-style01" required style="display: inline-block; width:170px;"/><br/>
						
						<label for="inputStdntTelno">휴대폰 번호</label>
			            <input type="text" id="inputStdntTelno" class="form-style01" placeholder="'-'포함하여 입력" required/>
			            
						<div class="div_left">
							<label for="inputClgNo">단과대</label>
				            <select id="inputClgNo" class="form-style01" required>
								<option value="" label="선택">
								<c:forEach items="${college}" var="col">
									<option value="${col.clgNo}" label="${col.clgNm}"></option>
								</c:forEach>
							</select>
						</div>
			            <div class="div_right">
				            <label for="inputSknrgSttusMajor1">전공</label>
							<select id="inputSknrgSttusMajor1" class="form-style01" required>
								<option value="" label="선택">
								<c:forEach items="${subject}" var="sub">
									<option class="clg${sub.clgNo} clgSubject" value="${sub.subjctNo}" label="${sub.subjctNm}"></option>
								</c:forEach>
							</select>
						</div>
						
						<div class="div_left">
							<label for="inputSknrgSttusEntsch">입학일</label>
				            <input type="date" id="inputSknrgSttusEntsch" class="form-style01 datepicker"/>
						</div>
			            <div class="div_right">
				            <label for="inputSknrgSttusEnterenceSe">입학 형태</label>			            
							<select id="inputSknrgSttusEnterenceSe" class="form-style01" required>
								<option value="" label="선택">
								<c:forEach items="${enterenceSe}" var="ent">
									<option value="${ent.comCode}" label="${ent.comCodeNm}">
								</c:forEach>
							</select>
						</div>				
					</div>
			
					<div class="div_left div_outer">
						<label for="inputStdntZip">우편번호</label><br/>
			            <input type="text" class="form-style01" id="sample6_postcode" required style="display: inline-block;"/>
						<input type="button" id="zipBtn" value="우편번호 찾기" class="btn btn-primary" style="margin-left: 15px;"><br/>
						
						<label for="inputStdntAdres1">기본주소</label>
			            <input type="text" class="form-style01 long_form" id="sample6_address" required/>
			            
			            <label for="inputStdntAdres2">상세주소</label>
			            <input type="text" class="form-style01 long_form" id="sample6_detailAddress"/>
			
						<div class="div_left">
							<label for="inputNltySe">국적</label>
				            <select id="inputNltySe" class="form-style01" required>
								<option value="" label="선택">
								<c:forEach items="${nltySe}" var="nlty">
									<option value="${nlty.comCode}" label="${nlty.comCodeNm}">
								</c:forEach>
							</select>
						</div>
			            <div class="div_right">
				            <label for="inputSexdstnSe">성별</label>
							<select id="inputSexdstnSe" class="form-style01" required>
								<option value="" label="선택">
								<c:forEach items="${genderSe}" var="gen">
									<option value="${gen.comCode}" label="${gen.comCodeNm}">
								</c:forEach>
							</select>
						</div>
						
						<div class="div_left">
							<label for="inputBankSe">은행</label>
				            <select id="inputBankSe" class="form-style01" required>
								<option value="" label="선택">
								<c:forEach items="${bankSe}" var="bank">
									<option value="${bank.comCode}" label="${bank.comCodeNm}">
								</c:forEach>
							</select>
						</div>
			            <div class="div_right">
				            <label for="inputStdntAcnutno">계좌번호</label>
							<input type="text" id="inputStdntAcnutno" class="form-style01" required/>
						</div>
					</div>
					
					<div>
						<button type="button" id="inputData" class="btn btn-success ft_left" onclick="inputData()" style="margin-top:20px;">일괄입력</button>
					</div>
					<form id="freshManInsertForm" action="/staff/freshMan/insertFreshMan" method="post">
						<input type="hidden" id="stdntNm" name="stdntNm">
						<input type="hidden" id="stdntIhidnum1" name="stdntIhidnum1">
						<input type="hidden" id="stdntIhidnum2" name="stdntIhidnum2">
						<input type="hidden" id="stdntTelno" name="stdntTelno">
						<input type="hidden" id="clgNo" name="clgNo">
						<input type="hidden" id="sknrgSttusMajor1" name="sknrgSttusMajor1">
						<input type="hidden" id="sknrgSttusEntsch" name="sknrgSttusEntsch">
						<input type="hidden" id="sknrgSttusEnterenceSe" name="sknrgSttusEnterenceSe">
						<input type="hidden" id="sknrgSttusGrade" name="sknrgSttusGrade">
						<input type="hidden" id="stdntZip" name="stdntZip">
						<input type="hidden" id="stdntAdres1" name="stdntAdres1">
						<input type="hidden" id="stdntAdres2" name="stdntAdres2">
						<input type="hidden" id="nltySe" name="nltySe">
						<input type="hidden" id="sexdstnSe" name="sexdstnSe">
						<input type="hidden" id="bankSe" name="bankSe">
						<input type="hidden" id="stdntAcnutno" name="stdntAcnutno">
						<input type="submit" value="등록" class="btn btn-primary ft_right applyBtn" style="margin-top:20px; margin-right:10px;">
					</form>
				</div>
			</div>
		</div>
	</div>
	
</div>











<script src="/resources/js/app/msy/staffFreshMan.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.3.0/jquery.form.min.js" integrity="sha384-qlmct0AOBiA2VPZkMY3+2WqkHtIQ9lSdAsAn5RUJD/3vA5MKDgSGcdmIv4ycVxyn" crossorigin="anonymous"></script>
























