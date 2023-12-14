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
.btn{margin-top: 10px}
</style>

<div class="homework_wrap mb-4">
     <div>
		<div class="pro_table">
			<div class="sub_tit04"><span></span>취업 상담</div>                     
			<div>
				<table class="table_style02 table_left table_center">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>학생명</th><td>${consultationInfo.student.stdntNm}</td></tr>
					<tr><th>학번</th><td>${consultationInfo.student.stdntNo}</td></tr>		
				</table>
			</div>
			<div>
				<table class="table_style02 table_right table_center">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>상담사</th><td>${consultationInfo.cnsltCnsltnt}</td></tr>
					<tr><th>상담일</th><td>${consultationInfo.cnsltDate} (${consultationInfo.tmtbSe})</td></tr>				
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
						<td style="text-align:left; padding-left:10px; padding-right:10px;">${consultationInfo.request.cnsltRequstCn}</td>
					</tr>			
				</table>
			</div>
			<div class="sub_tit04"><span></span>상담 내용</div>
			<div>
				<table class="table_style02 table_center">
					<colgroup>
						<col width="100%">
					</colgroup>
					<tr>
						<th>상담 내용</th>
					</tr>
					<tr>
						<td>
							<textarea rows="10" id="inputCnsltDtls" style="width:100%; resize:none; text-align:left; padding-left:10px; padding-right:10px;" required>${consultationInfo.cnsltDtls}</textarea>
						</td>
					</tr>			
				</table>
			</div>
			
			<form id="consultationModifyForm" class="clear" action="/staff/consultation/ajax/consultationModify" method="post">
				<input type="hidden" id="cnsltNo" name="cnsltNo" value="${consultationInfo.cnsltNo}">
				<input type="hidden" id="cnsltDtls" name="cnsltDtls" value="${consultationInfo.cnsltDtls}">
				<input type="submit" value="저장" class="btn btn-primary ft_right">
			</form>
			
		</div>
	</div>
 </div>