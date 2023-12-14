<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<security:authentication property="principal" var="principal" />
<style>
.sub_tit04{font-size: 18px;color: #304b73;padding-left:0px;font-weight: 600;width: 60%;float:left; text-align:left; margin-bottom:5px;}   
.sub_tit04 span{    width: 8px;
    height: 8px;
    background: #304b73;
    display: block;
    float: left;
    margin-right: 8px;
    position: relative;
    top: 9px;}
#btn1 {
width : 80px;
height : 30px;
float: right;
margin-top: 10px;
color : white;
text-align: center;
padding-bottom: 30px;
}
</style>




<div class="homework_wrap mb-4">
     <div class="table_style01 mt-4 mb-4 table_center">			   
		<div class="sub_tit04"><span></span>시험 출제 정보</div>
			<br/><br/>
			
			 <div id="areaContainer" style="text-align: center;">
			 	
			
			<table class="table_style02 table_center">
				<tr>
					<th>제목</th>
					<td colspan="2">${viewInfo.crTitle}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${principal.realUser.prfsorNm}</td>
					<th>작성일</th>
					<td>${viewInfo.crDate}</td>
				</tr>
	   		</table>
			<br/><br/>
			<div class="sub_tit04"><span></span>자료 이미지</div>
			
			  </div>
			 
			<c:set value="" var="atchFileNo"/>			
			<button type="button" class="btn btn-lg btn-info" 
			onclick="location.href='${pageContext.request.contextPath }/professor/classroom/file/${viewInfo.atchFileNo}'">자료 다운로드</button>
			<table class="table_style02 table_center">
				<tbody>
				
				</tbody>
	   		</table>
	   		<br/><br/>
			<div class="sub_tit04"><span></span>내용</div>
			<br/><br/>
			<table class="table_style02 table_center">
			<thead>
				<tr>
					<th>내용</th>
				</tr>
			</thead>
			<tbody>
				<td>${viewInfo.crCn}</td>
			</tbody>
	   		</table>
			<br/><br/>
 	</div> 				
 </div>
  			