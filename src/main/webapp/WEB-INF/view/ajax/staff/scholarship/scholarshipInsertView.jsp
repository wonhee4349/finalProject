<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
.profile_img{width:30%; float:left; height:213px;}   
.profile_img img {width:100%; height:100%;} 
.table_left {width:50% !important; float:left; }
.table_right {width:50% !important; float:right; }
.txtArea{resize:none; height: 150px;}   
.select-arrow02 {position: relative; float: right; top: 7px; right: 30px;}
.custom-select02{width:260px;}
.sy_input{width:285px; margin:0 auto;}
.form-style01{display: inline;}
th {text-align: center;}
</style>

<div class="homework_wrap mb-4">
     <div>
		<div class="pro_table">  
			<div>
				<table class="table_style02 table_left">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr>
						<th>장학금명</th>
						<td>
							<div class="sy_input" >
				                <select id="inputScholarship" class="custom-select02" required>
					              <option label="선택" value="" />
					               <c:forEach items="${scholarshipList}" var="list">
					                  <option label="${list.scholarshipList.schoNm}" value="${list.scholarshipList.schoSe}" />
					               </c:forEach>
					            </select>
					            <div class="select-arrow02">
									<i class="fa fa-caret-down"></i>
								</div>
				            </div>
						</td>
					</tr>
					<tr>
						<th>장학 년도</th>
						<td>
							<input type="number" class="form-style01" id="inputSemstrYear" name="inputSemstrYear" style="width: 77%; margin-left: 26px;" required>
							<span>년</span>
						</td>
					</tr>
					<tr>
						<th>장학 학기</th>
						<td>
							<div class="sy_input">
								<select id="inputSemstrNo" class="custom-select02" >
									<option label="1학기" value="1" />
									<option label="2학기" value="2" />
					            </select>
					            <div class="select-arrow02">
									<i class="fa fa-caret-down"></i>
								</div>
							</div>
						</td>
					</tr>
					
				</table>
			</div> 
			<div>
				<table class="table_style02 table_right">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr>
						<th>지급 방식</th>
						<td>
							<div class="sy_input">
								<select id="inputPymntSe" class="custom-select02" >
									<option label="등록금 감면" value="01" />
									<option label="현금 지급" value="02" />
					            </select>
					            <div class="select-arrow02">
									<i class="fa fa-caret-down"></i>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<th>지급액</th>
						<td>
							<input type="number" class="form-style01" id="inputSchlshipAmount" name="inputSchlshipAmount" style="width: 77%; margin-left: 26px;" required>
							<span>원</span>
						</td>
					</tr>
					<tr>
						<th>학생 신청 가능여부</th>
						<td>
							<div class="sy_input">
								<select id="inputStndtReq" class="custom-select02" >
									<option label="불가능" value="N" />
									<option label="가능" value="Y" />
					            </select>
					            <div class="select-arrow02">
									<i class="fa fa-caret-down"></i>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>       
			<div>
				<table class="table_style02 table_center">		
					<tr>
						<th>지급 대상</th>
					</tr>		
					<tr>
						<td>
							<textarea rows="5" class="form-style01 txtArea" id="inputSchlshipTrgter" required></textarea>
						</td>
					</tr>		
					<tr>
						<th>선발 기준</th>
					</tr>		
					<tr>
						<td>
							<textarea rows="5" class="form-style01 txtArea" id="inputSchlshipSelectn" required></textarea>
						</td>
					</tr>		
				</table>
			</div>
			<form id="scholarshipInsertForm" action="/staff/scholarshipStudent/insertScholarshipStudent" method="post">
				<input type="hidden" id="schlshipNo" name="schoSe">
				<input type="hidden" id="semstrNo" name="semstrNo">
				<input type="hidden" id="pymntSe" name="pymntSe">
				<input type="hidden" id="schlshipAmount" name="schlshipAmount">
				<input type="hidden" id="schlshipTrgter" name="schlshipTrgter">
				<input type="hidden" id="schlshipSelectn" name="schlshipSelectn">
				<input type="hidden" id="stndtReq" name="stndtReq">
				<input type="submit" value="저장" class="btn btn-primary ft_right" style="margin-top:10px; margin_botton:10px;'">
			</form>	
			<button class="btn btn-primary ft_right" onclick="insertContent()" style="margin:10px;">일괄입력</button>
		</div>
	</div>
 </div>
 
 <script src="/resources/js/app/msy/staffScholarship.js"></script>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 