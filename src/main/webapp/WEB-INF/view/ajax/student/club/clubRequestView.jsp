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
table td{padding-left:10px; text-align:left !important;}
</style>



<div class="homework_wrap mb-4">
     <div class="sub_tit04"><span></span>신청 학생</div>  
		<div>
			                   
			<div>
				<table class="table_style01 table_center">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>학번</th><td>${clubRequestInfo.stdntNo}</td></tr>		
					<tr><th>학생</th><td>${clubRequestInfo.student.stdntNm} (${clubRequestInfo.student.sknrgSttusMajor1})</td></tr>
				</table>
			</div>
			<div class="sub_tit04"><span></span>동아리</div>      
			<div>             
				<table class="table_style01 table_left hover_none">
					<colgroup>
						<col width="35%">
						<col width="65%">
					</colgroup>
					<tr><th>동아리명</th><td>${clubRequestInfo.clubEsNm}</td></tr>
					<tr><th>구분</th><td>${clubRequestInfo.clubSeNm}</td></tr>				
				</table>
			</div>  
			<div>             
				<table class="table_style01 table_right hover_none">
					<colgroup>
						<col width="35%">
						<col width="65%">
					</colgroup>			
					<tr><th>희망 동아리실</th><td>${clubRequestInfo.buldNo}(${clubRequestInfo.fcltsNoNm})</td></tr>				
					<tr><th>승인 여부</th><td>${clubRequestInfo.confmSe}</td></tr>				
				</table>
			</div>
			<div class="sub_tit04"><span></span>동아리 소개</div> 
			<div>
				<table class="table_style01 hover_none">
					<colgroup>
						<col width="100%">
					</colgroup>
					<tr>
						<th>동아리 소개</th>
					</tr>
					<tr>
						<td>${clubRequestInfo.clubEsIntrcn}</td>
					</tr>			
				</table>
				<input type="hidden" value="${clubRequestInfo.clubEsNo}" id="hiddenClubEsNo">
			</div>
			
			<c:if test="${clubRequestInfo.confmSe eq '반려'}">
					<div class="sub_tit04"><span></span>반려 사유</div> 
					<div>
						<table class="table_style01 hover_none">
							<colgroup>
								<col width="100%">
							</colgroup>
							<tr>
								<th>반려 사유</th>
							</tr>
							<tr>
								<td>${clubRequestInfo.clubEsReturn}</td>
							</tr>					
						</table>
					</div>					
			</c:if>
						
			
			
		</div>
  		<br />
	</div>

 