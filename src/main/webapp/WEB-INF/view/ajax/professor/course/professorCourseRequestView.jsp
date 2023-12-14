<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

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
		<div class="sub_tit04"><span></span>학과</div>
			<br/><br/>
			<table class="table_style02 table_center">
				<tr>
					<th>단과대학</th>
					<td>${courseRequest.clgNm}</td>
					<th>학과</th>
					<td>${courseRequest.subjectvo.subjctNm}</td>
				</tr>
				<tr>
					<th>학과 전화번호</th>
					<td>${courseRequest.subjectvo.subjctTelno}</td>
					<th>학과 건물</th>
					<td>${courseRequest.buldNm}</td>
				</tr>
	   		</table>
			<br/><br/>
			 				             						
	   <div class="sub_tit04"><span></span>개설 교과목 정보</div>
			<table class="table_style02 table_center">
				<tr>
					<th>교과목명</th>
				</tr>
				<tr>
					<td>${courseRequest.courseReqstNm}</td>
				</tr>
	   		</table>
	   		<table class="table_style02 table_center">
	   		
				<tr>
					<th>학점</th>
					<td>${courseRequest.coursePnt} 학점</td>
				</tr>		
				<tr>
					<th>이수구분</th>
					<td>${courseRequest.complSe}</td>
				</tr>		
				<tr>
					<th>개설요청일</th>
					<td>${courseRequest.courseReqstDate}</td>
				</tr>		
				<tr>
					<th>승인구분</th>
					<td>${courseRequest.confmSe}</td>
				</tr>		
 			</table>
 	   </div>
 </div>
  			