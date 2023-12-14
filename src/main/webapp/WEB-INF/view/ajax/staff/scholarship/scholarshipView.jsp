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
</style>

<div class="homework_wrap mb-4">
     <div>
		<div class="pro_table">
			<div class="sub_tit04"><span></span>장학금 구분</div>                     
			<div>
				<table class="table_style02 table_left table_center">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>장학금명</th><td>${scholarshipInfo.scholarshipList.schoNm}</td></tr>
					<tr><th>장학 년도</th><td>${scholarshipInfo.semstrYear}년도 (${scholarshipInfo.semstrNo}학기)</td></tr>		
				</table>
			</div>
			<div>
				<table class="table_style02 table_right table_center">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>지급액</th><td>${scholarshipInfo.schlshipAmountStr}</td></tr>
					<tr><th>지급 방식</th><td>${scholarshipInfo.pymntSe}</td></tr>				
				</table>
			</div>
			<div class="sub_tit04"><span></span>장학금 지급</div> 
			<div>
				<table class="table_style02 table_center">
					<colgroup>
						<col width="100%">
					</colgroup>
					<tr>
						<th>지급 대상</th>
					</tr>
					<tr>
						<td>${scholarshipInfo.schlshipTrgter}</td>
					</tr>			
				</table>
			</div>
			<br>
			<div>
				<table class="table_style02 table_center">
					<colgroup>
						<col width="100%">
					</colgroup>
					<tr>
						<th>선발 기준</th>
					</tr>
					<tr>
						<td>${scholarshipInfo.schlshipSelectn}</td>
					</tr>			
				</table>
			</div>
		</div>
  		<br />
	</div>
 </div>