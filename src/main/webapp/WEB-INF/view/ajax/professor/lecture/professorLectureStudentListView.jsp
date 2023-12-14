<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
			<div class="sub_tit04"><span></span>수강학생목록</div>
				<br/><br/>
				<table class="table_style02 table_center">
					<h4>학생 정보</h4>
			    	<colgroup>
						<col width="5%">
						<col width="15%">
						<col width="10%">
						<col width="20%">
						<col width="20%">
						<col width="10%">
					</colgroup>
					<thead>
						<tr>
							<th>학년</th>
							<th>학과</th>
							<th>학번</th>
							<th>이름</th>
							<th>연락처</th>
							<th>성별</th>
						</tr>
					</thead>
						<c:set var="studentList" value="${studentList}"></c:set>
							<c:if test="${empty studentList }">
								<tr>
									<td colspan="6">수강중인 학생목록이 존재하지 않습니다.</td>
								</tr>
							</c:if>
							<c:if test="${not empty studentList}">
								<c:forEach items="${studentList}" var="students">
									    <tr>
									        <td>${students.sknrgSttusGrade}학년</td>
									        <td>${students.sknrgSttusMajor1}</td>
									        <td>${students.stdntNo}</td>
									        <td>${students.stdntNm}</td>
									        <td>${students.stdntTelno}</td>
									        <td>${students.sexdstnSe}</td>
									    </tr>
								</c:forEach>
							</c:if>
		   		</table>
 	</div> 				
 </div>
  			