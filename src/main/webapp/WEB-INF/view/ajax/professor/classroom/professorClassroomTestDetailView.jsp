<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
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
     <div>			   
		<div class="sub_tit04"><span></span>시험 출제 정보</div>
			<br/><br/>
			
			 <div id="areaContainer" style="text-align: center;">
				<table class="table_style02 table_center">
					<tr>
						<th>시험구분</th>
						<td colspan="2">${classroomBoardVO.testSeNm}</td>
					</tr>
					<tr>
						<th>시험년도</th>
						<td>${classroomBoardVO.semstrSeYear}년</td>
						<th>시험학기</th>
						<td>${classroomBoardVO.semstrSeSemstr} 학기</td>
					</tr>
					<tr>
						<th>강의구분</th>
						<td>${classroomBoardVO.lecture.lctreSe}</td>
						<th>이수구분</th>
						<td>${classroomBoardVO.lecture.complSe}</td>
					</tr>
		   		</table>
				<br/><br/>
				<div class="sub_tit04"><span></span>시험문제 파일</div>
			</div>
			 
			<c:set value="${classroomBoardVO.atchFile}" var="atchFile"/>			
			<button type="button" class="btn btn-lg btn-primary" 
			onclick="location.href='${pageContext.request.contextPath }/professor/classroom/file/${atchFile.atchFileNo}'">시험문제 다운로드</button>
			<table class="table_style02 table_center">
				<tbody>
				
				</tbody>
	   		</table>
	   		<br/><br/>
			<div class="sub_tit04"><span></span>응시 학생 리스트</div>
			<br/><br/>
			<table class="table_style02 table_center">
			<thead>
				<tr>
					<th>학과</th>
					<th>학번</th>
					<th>학년</th>
					<th>학생명</th>
					<th>연락처</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty studentStareList}">
						<c:forEach items="${studentStareList}" var="studentStare">
							<tr>
								<td>${studentStare.submitAnswerPaper.student.subject.subjctNm}</td>
								<td>${studentStare.submitAnswerPaper.student.stdntNo}</td>
								<td>${studentStare.submitAnswerPaper.student.sknrgSttusGrade}학년</td>
								<td>${studentStare.submitAnswerPaper.student.stdntNm}</td>
								<td>${studentStare.submitAnswerPaper.student.stdntTelno}</td>
							</tr>	
						</c:forEach>
					</c:when>
					<c:when test="${empty studentStareList}">
						<tr>
							<td colspan="6"> 시험 응시한 학생이 존재하지 않습니다.</td>
						</tr>
					</c:when>
				</c:choose>
			</tbody>
	   		</table>
			<br/><br/>
 	</div> 				
 </div>

<script>
$(function(){
	// 모달 닫기 ---------------------------------------------------------------
	$("#testDetailViewModalClose").on("click",function(){
	    $("#testDetailViewModal").modal('hide');
	});
});
</script>	