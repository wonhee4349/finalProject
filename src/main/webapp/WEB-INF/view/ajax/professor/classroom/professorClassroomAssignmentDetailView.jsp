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
#tdCn {
	text-align:left;
	padding-left : 10px;
	padding-right: 10px;
}
</style>




<div class="homework_wrap mb-4">
     <div>			   
		<div class="sub_tit04"><span></span>과제 출제 정보</div>
			<br/><br/>
			 <div id="areaContainer" style="text-align: center;">
				<table class="table_style02 table_center">
					<tr>
						<th>제목</th>
						<td colspan="2">${assignmentView.crTitle}</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${principal.realUser.prfsorNm}</td>
						<th>작성일</th>
						<td>${assignmentView.crDate}</td>
					</tr>
		   		</table>
				<br/><br/>
				<div class="sub_tit04"><span></span>과제 출제 파일</div>
			</div>
			 
			<c:set value="${assignmentView.atchFileNo}" var="atchFileNo"/>			
			<button type="button" class="btn btn-lg btn-success" 
			onclick="location.href='${pageContext.request.contextPath }/professor/classroom/file/${atchFileNo}'">자료 다운로드</button>
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
				<td id="tdCn">${assignmentView.crCn}</td>
			</tbody>
	   		</table>
			<br/><br/>
			<div class="sub_tit04"><span></span>과제 제출 내역</div>
			<br/><br/>
			<table class="table_style02 table_center">
			<colgroup>
				<col width="15%">
				<col width="10%">
				<col width="10%">
				<col width="20%">
				<col width="30%">
				<col width="15%">
			</colgroup>
			<thead>
				<tr>
					<th>학번</th>
					<th>학년</th>
					<th>학생명</th>
					<th>전화번호</th>
					<th>자료명(크기)</th>
					<th>다운로드</th>
				</tr>
			</thead>
			<tbody>
				
				<c:choose>
					<c:when test="${not empty assignmetSubmitStudentList}">
						<c:forEach items="${assignmetSubmitStudentList}" var="assignmet">
							<c:forEach items="${assignmet.lectureSubmit}" var="lectureSubmit">
								<tr>
									<td>${lectureSubmit.student.stdntNo}</td>
									<td>${lectureSubmit.student.sknrgSttusGrade}학년</td>
									<td>${lectureSubmit.student.stdntNm}</td>
									<td>${lectureSubmit.student.stdntTelno}</td>
									<td style="text-align:left">${lectureSubmit.atchFile.atchFileNm}(${lectureSubmit.atchFile.atchFileIndictMg})</td>
									<td>
									<c:set value="${lectureSubmit.atchFile.atchFileNo}" var="atchFileNo"/>
									<button type="button" class="btn btn-success" 
									onclick="location.href='${pageContext.request.contextPath }/professor/classroom/file/${atchFileNo}'">
									다운로드</button>
									</td>
								</tr>
							</c:forEach>
						</c:forEach>
					</c:when>
					<c:when test="${empty assignmetSubmitStudentList}">
						<tr>
							<td colspan="6"> 제출한 학생이 없습니다.</td>
						</tr>
					</c:when>
				</c:choose>
				
			</tbody>
	   		</table>
 	</div> 				
 </div>
 
  			