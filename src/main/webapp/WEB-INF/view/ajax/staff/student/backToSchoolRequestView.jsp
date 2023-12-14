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
.refuseBtn{margin-top:10px; margin-bottom: 10px;}
.applyBtn{margin-top:10px; margin-bottom: 10px;}
</style>



<div class="homework_wrap mb-4">
     <div>
		<div class="pro_table">
			<div class="sub_tit04"><span></span>신청 학생</div>      
			<div>             
				<table class="table_style02 table_left table_center">
					<colgroup>
						<col width="35%">
						<col width="65%">
					</colgroup>
					<tr><th>학번</th><td>${backToSchoolInfo.stdntNo}</td></tr>
					<tr><th>학생명</th><td>${backToSchoolInfo.student.stdntNm}</td></tr>				
					<tr><th>연락처</th><td>${backToSchoolInfo.student.stdntTelno}</td></tr>																	
				</table>
			</div>  
			<div>             
				<table class="table_style02 table_right table_center">
					<colgroup>
						<col width="35%">
						<col width="65%">
					</colgroup>			
					<tr><th>단과대</th><td>${backToSchoolInfo.student.subject.college.clgNm}</td></tr>				
					<tr><th>전공학과</th><td>${backToSchoolInfo.student.sknrgSttusMajor1}</td></tr>				
					<tr><th>학년</th><td>${backToSchoolInfo.student.sknrgSttusGrade}학년</td></tr>	
				</table>
			</div><br/>
			<div class="sub_tit04" style="margin-top: 20px"><span></span>신청 내용</div>
			<div>
				<table class="table_style02 table_left table_center" style="margin-bottom:30px;">
					<colgroup>
						<col width="35%">
						<col width="65%">
					</colgroup>
					<tr><th>신청일</th><td>${backToSchoolInfo.sknrgDate}</td></tr>												
				</table>
			</div>
			<div>
				<table class="table_style02 table_right table_center">
					<colgroup>
						<col width="35%">
						<col width="65%">
					</colgroup>													
					<tr><th>승인구분</th><td>${backToSchoolInfo.confmSe}</td></tr>													
				</table>
				<input type="hidden" value="${backToSchoolInfo.sknrgNo}" id="hiddenSknrgNo">
			</div>
			
			<c:if test="${backToSchoolInfo.confmSe eq '반려'}">
					<div class="sub_tit04"><span></span>반려 사유</div> 
					<div>
						<table class="table_style02 table_center">
							<colgroup>
								<col width="100%">
							</colgroup>
							<tr>
								<th>반려 사유</th>
							</tr>
							<tr>
								<td>${backToSchoolInfo.sknrgReturn}</td>
							</tr>					
						</table>
					</div>					
			</c:if>
						
			<c:if test="${backToSchoolInfo.confmSe eq '승인 대기'}">
				<br>
				<div>
					<button class="btn btn-danger ft_right refuseBtn" data-toggle="modal" data-target="#refuseModal">반려</button>
					<form id="appliedBackToSchooleRequest" action="/staff/absence/updateAppliedBackToSchoolRequest" method="post">
						<input type="hidden" id="sknrgNo" name=sknrgNo value="${backToSchoolInfo.sknrgNo}">
						<input type="hidden" id="stdntNo" name="stdntNo" value="${backToSchoolInfo.stdntNo}">
						<input type="submit" value="승인" class="btn btn-primary ft_right applyBtn" style="margin-right:10px;">
					</form>
				</div>															
			</c:if>
			
			<div><input type="hidden" value="${backToSchoolInfo.sknrgNo}" id="hiddenSknrgNo"></div>
			
		</div>
  		<br />
	</div>
 </div>
 
 