<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
.form-style01{width:300px; display: inline;}
</style>

<div class="homework_wrap mb-4">
     <div>
		<div class="pro_table">
			<div class="sub_tit04"><span></span>학생 정보</div>                     
			<div>
				<table class="table_style02 table_left table_center hover_none">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>단과대</th><td>${studentInfo.subject.college.clgNm}</td></tr>
					<tr><th>학과</th><td>${studentInfo.sknrgSttusMajor1}</td></tr>		
				</table>
			</div>
			<div>
				<table class="table_style02 table_right table_center hover_none">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>학번</th><td>${studentInfo.stdntNo}</td></tr>				
					<tr><th>학생명</th><td>${studentInfo.stdntNm}</td></tr>
				</table>
			</div>
			
			<div class="sub_tit04"><span></span>학기 정보</div>                     
			<div>
				<table class="table_style02 table_left table_center hover_none">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr>
						<th>년도</th>
						<td>
							<span>${semester.semstrYr} 년</span>
						</td>
					</tr>	
				</table>
				<div>
				<table class="table_style02 table_right table_center hover_none">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr>
						<th>학기</th>
						<td>
							<span>${semester.semstr} 학기</span>
						</td>
					</tr>
				</table>
			</div>
			</div>

			
			<div class="sub_tit04"><span></span>등록금</div>                     
			<div>
				<table class="table_style02 table_center hover_none">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr>
						<th>입학금</th>
						<td>
							<input type="number" class="form-style01" id="inputEtrncf" name="inputEtrncf" style="text-align:right;" required>
							<span>원</span>
						</td>											
					</tr>
					<tr>
						<th>수업료</th>
						<td>
							<input type="number" class="form-style01" id="inputTutfee" name="inputTutfee" style="text-align:right;" required>
							<span>원</span>
						</td>
					</tr>
					<c:set var="schlshipAmt" value="0" />
					<c:forEach items="${studentScholarship.scholarship}" var="scholarship">
						<fmt:parseNumber value="${scholarship.schlshipAmount}" var="schlshipAmount"/>
						<c:set var="schlshipAmt" value="${schlshipAmt + schlshipAmount}" />					
					</c:forEach>	
					<tr>
						<th>장학금</th>
						<td style="text-align:right; padding-right: 178px;">
							<span>${schlshipAmt} 원</span>
						</td>
					</tr>				
				</table>
			</div>
		</div>
		<br>
		<form id="tuitionInsertForm" action="/staff/tuitionStudent/insertTuition" method="post">
			<input type="hidden" id="stdntNo" name="stdntNo" value="${studentInfo.stdntNo}">
			<input type="hidden" id="tutnEtrncf" name="tutnEtrncf">
			<input type="hidden" id="tutnTutfee" name="tutnTutfee">
			<input type="hidden" id="tutnSchlship" name="tutnSchlship" value="${schlshipAmt}">
			<input type="hidden" id="tutnSemstr" name="semstrNo" value="${semester.semstrYr}${semester.semstr}">
			<input type="submit" value="저장" class="btn btn-primary ft_right">
		</form>	
  		<br />
	</div>
 </div>