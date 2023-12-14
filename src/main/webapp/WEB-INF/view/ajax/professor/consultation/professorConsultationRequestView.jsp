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
     <div class="table_style01 mt-4 mb-4 table_center">
		<div class="pro_table">
			<div class="sub_tit04"><span></span>신청 학생</div>                     
			<div>
				<table class="table_style02 table_left table_center">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>학번</th><td>${consultationRequest.student.stdntNo}</td></tr>		
					<tr><th>학생명</th><td>${consultationRequest.student.stdntNm}</td></tr>
					<tr><th>학과</th><td>${consultationRequest.student.sknrgSttusMajor1}</td></tr>		
				</table>
			</div>
			<div>
				<table class="table_style02 table_right table_center">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>학년</th><td>${consultationRequest.student.sknrgSttusGrade}</td></tr>
					<tr><th>희망 상담일</th><td>${consultationRequest.cnsltRequstDate} (${consultationRequest.cnsltRequstTime})</td></tr>				
					<tr><th>승인 구분</th><td>${consultationRequest.cnsltRequstProcess}</td></tr>				
				</table>
			</div>
			<div class="sub_tit04"><span></span>신청 내용</div> 
			<div>
				<table class="table_style02 table_center">
					<colgroup>
						<col width="100%">
					</colgroup>
					<tr>
						<th>신청 내용</th>
					</tr>
					<tr>
						<td>${consultationRequest.cnsltRequstCn}</td>
					</tr>			
				</table>
				<input type="hidden" value="${consultationRequest.cnsltNo}" id="hiddenCnsltRequstNo">
			</div>
			
			<c:if test="${consultationRequest.cnsltRequstProcess eq '반려'}">
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
								<td>${consultationRequest.cnsltRequstReturn}</td>
							</tr>					
						</table>
					</div>					
			</c:if>
						
			<c:if test="${consultationRequest.cnsltRequstProcess eq '승인 대기'}">
				<br>
				<div>
					<button class="btn btn-danger ft_right" id="refuseBtn" data-toggle="modal" data-target="#refuseConsultationModal">반려</button>
						
						<form id="addConsultationRequest" action="/professor/consultation/updateAllowConsultationRequest" method="post">
							<input type="hidden" id="cnsltNo" name="cnsltNo" value="${consultationRequest.cnsltNo}">
							<input type="submit" value="승인" class="btn btn-primary ft_right" id="addConsultationBtn" style="margin-right:10px;">
						</form>
				</div>	
			</c:if>
			
		</div>
  		<br />
	</div>
 </div>
 
