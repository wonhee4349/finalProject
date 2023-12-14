<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
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
.pd20{padding:20px}
.wd400{width:400px;}
</style>


<!-- 성적이의신청 정정 모달 -->
	<div class="modal fade mt-100" id="scoreRegistrationModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true" style="margin-top:90px;">
	    <div class="modal-dialog modal-xl" role="document" id="secondModal">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">성적 등록</h5>
	              <button id="scoreRegistrationModalClose" class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
					    <span aria-hidden="true">×</span>
				  </button>
	            </div>
	            <div>
           		<form id="scoreRegistrationForm" action="/professor/score/scoreRegistration" method="post">
					<security:authorize access="isAuthenticated()">
						<security:authentication property="principal.realUser" var="realUser" />
					</security:authorize>
	            	<div class="modal-body pd20">
	            		<div class="sub_tit04"><span></span>강의 평가 기준</div>
	            		<table class="table table_style01 mt-4 table_center hover_none" id="dataTable" width="100%" cellspacing="0">
	                        <colgroup>
                        		<col width="15%">
                        		<col width="10%">
                        		<col width="15%">
                        		<col width="10%">
                        		<col width="10%">
                        		<col width="10%">
                        		<col width="10%">
                        		<col width="10%">
                       		</colgroup>
                       		<tbody id="lectureEvalutionStandardBody"></tbody>
	            		</table>
	            		<div class="sub_tit04"><span></span>성적 채점</div>
	            		<table class="table table_style01 mt-4 table_center hover_none" id="dataTable" width="100%" cellspacing="0">
                              <colgroup>
                        		<col width="25%">

                       		</colgroup>
                              <tr>
                                  <th>중간고사</th>
                                  <td>
                                  <input type="hidden" name="scoreVO[0].scoreSe" value="01"/>
                                  <input class="form-control wd400" type="number" name="scoreVO[0].scoreScore" id="registMiddleScore" required/>
                                  </td>
                              </tr>
                              <tr>
                                  <th>기말고사</th>
                                  <td>
                                  <input type="hidden" name="scoreVO[1].scoreSe" value="02"/>
                                  <input class="form-control wd400" type="number" name="scoreVO[1].scoreScore" id="registFinalScore" required/>
                                  </td>
                              </tr>
                              <tr>
                                  <th>과제</th>
                                  <td>
                                  <input type="hidden" name="scoreVO[2].scoreSe" value="03"/>
                                  <input class="form-control wd400" type="number" name="scoreVO[2].scoreScore" id="registAssignmentScore" required/>
                                  </td>
                              </tr>
                              <tr>
                                  <th>출석</th>
                                  <td>
                                  <input type="hidden" name="scoreVO[3].scoreSe" value="04"/>
                                  <input class="form-control wd400" type="number" name="scoreVO[3].scoreScore" id="registAttendenceScore" required/>
                                  </td>
                              </tr>
                        </table>
								<input type="hidden" id="scoreRegistLctreNo" name="lctreNo" />
								<input type="hidden" id="scoreRegistStdntNo" name="stdntNo" />
								<button class="btn btn-success mt-3" type="button" id="autoResultLectureRequest" onclick="ScoreRegistAutoInput()">일괄입력</button>
								<input type="submit" value="저장" class="btn btn-primary ft_right" style=" margin-bottom: 15px;">
						</div>
					<security:csrfInput/>
					</form>
					</div>
	            </div>
	        </div>
	    </div>
<!-- 모달끝 -->


<div>
     <div class=" mt-4 mb-4 table_center">			   
		<form id="lectureAttendStudentListForm" action="/professor/lecture/attendStudentList" method="POST" >
			<div class="sub_tit05"><span></span>수강학생 목록</div>
				<br/><br/>
				
				<table class="table_style01 table_center hover_none">
					<thead>
			    	<colgroup>
						<col width="15%">
						<col width="10%">
						<col width="15%">
						<col width="10%">
						<col width="15%">
						<col width="10%">
						<col width="20%">
					</colgroup>
					
						<tr>
							<th>학과</th>
							<th>학년</th>
							<th>학번</th>
							<th>이름</th>
							<th>연락처</th>
							<th>성별</th>
							<th>성적입력</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="studentList" value="${studentList}" />
							<c:if test="${empty studentList }">
								<tr >
									<td colspan="8">수강중인 학생목록이 존재하지 않습니다.</td>
								</tr>
							</c:if>
							<c:if test="${not empty studentList}">
								<c:forEach items="${studentList}" var="students">
								    <tr>
								        <td>${students.sknrgSttusMajor1}</td>
								        <td>${students.sknrgSttusGrade}학년</td>
								        <td>${students.stdntNo}</td>
								        <td>${students.stdntNm}</td>
								        <td>${students.stdntTelno}</td>
								        <td>${students.sexdstnSe}</td>
										<c:forEach items="${students.scoreVO}" var="score" begin="0" end="0">
											<c:if test="${empty score.scoreScore}">
										        <td><button data-lctre-no="${students.lctreNo}" data-stdnt-no="${students.stdntNo}" class="btn btn-primary" id="scoreRegistration" 
										        			data-toggle="modal" data-target="#scoreRegistrationModal" type="button">성적등록</button>
										        </td>
					                        </c:if>
					                        <c:if test="${not empty score.scoreScore}">    
					                            <td>
					                                <button class="btn btn-success" type="button">등록완료</button>
					                            </td>
					                        </c:if>
					                    </c:forEach>
								    </tr>
								</c:forEach>
							</c:if>
					</tbody>
		   		</table>
		</form>
 			
 	</div> 				
 </div>

<script>
$(function(){
	$("#scoreRegistrationModalClose").click(function(){
	    $("#scoreRegistrationModal").modal('hide');
	});
	
	$("#scoreRegistrationModal").on('hidden.bs.modal', function () {
	    $("#registMiddleScore").val('');
	    $("#registFinalScore").val('');
		$("#registAssignmentScore").val("");
		$("#registAttendenceScore").val("");
	});
	
})
</script>