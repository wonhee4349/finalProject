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
.btn {margin-top: 10px;}
</style>

<div class="homework_wrap mb-4">
     <div>
		<div class="sub_tit04"><span></span>학생 정보</div>                     
		<div>
			<table class="table_style02 table_left table_center">
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<tr><th>단과대</th><td>${tuitionInfo.student.subject.college.clgNm}</td></tr>
				<tr><th>학과</th><td>${tuitionInfo.student.sknrgSttusMajor1}</td></tr>		
			</table>
		</div>
		<div>
			<table class="table_style02 table_right table_center">
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<tr><th>학번</th><td>${tuitionInfo.stdntNo}</td></tr>				
				<tr><th>학생명</th><td>${tuitionInfo.student.stdntNm}</td></tr>
			</table>
		</div><br>
		
		<div class="sub_tit04"><span></span>학기 정보</div>                     
		<div>
			<table class="table_style02 table_left table_center">
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<tr>
					<th>년도</th>
					<td>${tuitionInfo.tutnPayYr} 년</td>
				</tr>	
			</table>
			<div>
			<table class="table_style02 table_right table_center">
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<tr>
					<th>학기</th>
					<td>${tuitionInfo.tutnPaySems} 학기</td>
				</tr>
			</table>
		</div>
		</div><br>
		
		<div class="sub_tit04"><span></span>등록금</div>                     
		<div>
			<table class="table_style02 table_center">
				<colgroup>
					<col width="20%">
					<col width="30%">
					<col width="20%">
					<col width="30%">
				</colgroup>
				
				<c:set var="etrncf" value="${tuitionInfo.tutnEtrncf}"/>
				<c:if test="${tuitionInfo.tutnEtrncf == null}">
					<c:set var="etrncf" value="0"/>
				</c:if>
				
				<c:set var="schlship" value="${tuitionInfo.tutnSchlship}"/>
				<c:if test="${tuitionInfo.tutnSchlship == null}">
					<c:set var="schlship" value="0"/>
				</c:if>
				
				<fmt:parseNumber value="${etrncf}" var="etrncfNum" />
				<fmt:parseNumber value="${tuitionInfo.tutnTutfeeStr}" var="tutfeeNum" />
				<fmt:parseNumber value="${schlship}" var="schlshipNum" />
				
				<c:set var="totalAmt" value="${etrncfNum+tutfeeNum-schlshipNum}"></c:set>
				<fmt:formatNumber var="formattedTotalAmt" value="${totalAmt}" pattern="#,##0"/>
				
				<tr>
					<th>입학금</th><td style="text-align: right; padding-right:10px;">${etrncf}원</td>											
					<th>수업료</th><td style="text-align: right; padding-right:10px;">${tuitionInfo.tutnTutfeeStr}원</td>
				</tr>	
				<tr>
					<th>장학금</th><td style="text-align: right; padding-right:10px;">${schlship}원</td>
					<th>총 납부액</th><td style="text-align: right; padding-right:10px;">${formattedTotalAmt}원</td>
				</tr>			
			</table>
		</div><br>
		
		<div class="sub_tit04"><span></span>납부</div>
		<div>
			<table class="table_style02 table_left table_center">
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<tr><th>납부여부</th><td>${tuitionInfo.tutnPay}</td></tr>	
			</table>
		</div>
		<div>
			<table class="table_style02 table_right table_center">
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<tr><th>납부일</th><td><input type="date" id="inputDate" name="inputDate" value="${tuitionInfo.tutnPayde}"></td></tr>		
			</table>
		</div>
		<br>
		
		<form id="tuitionModifyForm" class="clear" action="/staff/tuitionPay/ajax/tuitionModify" method="post">
			<input type="hidden" id="tutnNhtNo" name="tutnNhtNo" value="${tuitionInfo.tutnNhtNo}">
			<input type="hidden" id="tutnEtrncf" name="tutnEtrncf" value="${etrncf}">
			<input type="hidden" id="tutnTutfee" name="tutnTutfee" value="${tuitionInfo.tutnTutfee}">
			<input type="hidden" id="tutnPayde" name="tutnPayde" value="${tuitionInfo.tutnPayde}">
			<input type="submit" value="저장" class="btn btn-primary ft_right">
		</form>	
		
	</div>
 </div>