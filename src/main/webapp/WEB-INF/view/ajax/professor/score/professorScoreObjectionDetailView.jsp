<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<security:authentication property="principal" var="principal" />
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
.mr10{margin-right:10px;}
.pd20{padding:20px;}

</style>


<!-- 성적이의신청 반려 모달 -->
	<div class="modal fade mt-100" id="refuseScoreObjectionModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true" style="margin-top:90px;">
	    <div class="modal-dialog modal-xs" role="document" id="secondModal">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">성적 반려 등록</h5>
	              <button id="refuseScoreObjectionModalClose" class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
					    <span aria-hidden="true">×</span>
				  </button>
	            </div>
	            <div>
	            	<div class="modal-body pd20">
	            		<div class="sub_tit04 "><span></span>반려 사유 입력</div>
						<table class=" mt-4 table_center" id="dataTable" width="100%">
							<thead>
								<tr>
									<td>
										<textarea rows="5" cols="50" id="refuseScoreObjectionContent" style="resize:none; border:1px solid #f2f2f2; height:237px; margin-top:20px" required></textarea>
									</td>
								</tr>
							</thead>
						</table>
						<div>
							<form id="refuseScoreObjectionForm" action="/professor/score/modifyRefuseScoreObjection" method="post">
								<security:authorize access="isAuthenticated()">
									<security:authentication property="principal.realUser" var="realUser" />
								</security:authorize>	
									<input type="hidden" id="stdntNo" name="stdntNo" value="${scoreObjectionView.student.stdntNo}"/>
									<input type="hidden" id="lctreNo" name="lctreNo" value="${scoreObjectionView.lctreNo}"/>
									<input type="hidden" id="scoreObjcReturnTextArea" name="scoreObjcReturn" value=""/>
									<div class="pd20">
										<button class="btn btn-success" type="button" id="autoResultLectureRequest" onclick="ScoreRefuseAutoInput()">일괄입력</button>
										<input type="submit" value="저장" id="refuseScoreObjectionBtn" class="btn btn-primary ft_right" style=" margin-bottom: 15px;">
									</div>
								<security:csrfInput/>
							</form>
							
						</div>
					</div>
	            </div>
	        </div>
	    </div>
	</div>
<!-- 모달끝 -->

<!-- 성적이의신청 정정 모달 -->
	<div class="modal fade mt-100" id="reSetScoreObjectionModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true" style="margin-top:90px;">
	    <div class="modal-dialog modal-xs" role="document" id="secondModal">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">성적 정정 입력</h5>
	              <button id="reSetScoreObjectionModalClose" class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
					    <span aria-hidden="true">×</span>
				  </button>
	            </div>
	            <div>
           		<form id="resetScoreObjectionForm" action="/professor/score/modifyResetScoreObjection" method="post">
					<security:authorize access="isAuthenticated()">
						<security:authentication property="principal.realUser" var="realUser" />
					</security:authorize>
	            	<div class="modal-body pd20">
	            		<div class="sub_tit04"><span></span>점수 재채점</div>
	            		<table class="table table_style03 mt-4 table_center  hover_none" id="dataTable" width="100%" cellspacing="0">
                              <colgroup>
                        		<col width="25%">
                       		</colgroup>
                              <tr>
                                  <th>중간고사</th>
                                  <td>
                                  <input type="hidden" name="scoreVO[0].scoreSe" value="01"/>
                                  <input type="number" name="scoreVO[0].scoreScore" id="resetMiddleScore" class="form-control" required/>
                                  </td>
                              </tr>
                              <tr>
                                  <th>기말고사</th>
                                  <td>
                                  <input type="hidden" name="scoreVO[1].scoreSe" value="02"/>
                                  <input type="number" name="scoreVO[1].scoreScore" id="resetFinalScore"  class="form-control" required/>
                                  </td>
                              </tr>
                              <tr>
                                  <th>과제</th>
                                  <td>
                                  <input type="hidden" name="scoreVO[2].scoreSe" value="03"/>
                                  <input type="number" name="scoreVO[2].scoreScore" id="resetAssignmentScore" class="form-control"  required/>
                                  </td>
                              </tr>
                              <tr>
                                  <th>출석</th>
                                  <td>
                                  <input type="hidden" name="scoreVO[3].scoreSe" value="04"/>
                                  <input type="number" name="scoreVO[3].scoreScore" id="resetAttendanceScore"  class="form-control" required/>
                                  </td>
                              </tr>
                        </table>
	            		<div class="sub_tit04"><span></span>정정 사유 입력</div>
						<table class=" hover_none" id="dataTable" width="100%">
								<tr>
									<td>
										<textarea rows="5" cols="50" id="resetScoreTextArea" style="resize:none; border:1px solid #f2f2f2; height:237px; width:100%" required></textarea>
									</td>
								</tr>
							<tbody id="reSetScoreObjectionModalBody"></tbody>
						</table>
						<div>
								<input type="hidden" id="resetScoreObjectionContent" name="scoreObjcReturn" />
								<input type="hidden" id="lctreNo" name="lctreNo" value="${scoreObjectionView.lctreNo}"/>
								<input type="hidden" id="stdntNo" name="stdntNo" value="${scoreObjectionView.student.stdntNo}"/>
								<button class="btn btn-success mt-3" type="button" id="autoResultLectureRequest" onclick="ScoreResetAutoInput()">일괄입력</button>
								<input type="submit" value="저장" class="btn btn-primary ft_right" style=" margin-bottom: 15px;">
						</div>
					</div>
					<security:csrfInput/>
					</form>
	            </div>
	        </div>
	    </div>
	</div>
<!-- 모달끝 -->


<div class="homework_wrap mb-4">
     <div>
		<div class="pro_table">
			<div class="sub_tit04 mt-4"><span></span>신청 학생</div>                     
			<div>
				<table class="table_style02 table_center hover_none">
					<colgroup>
						<col width="10%">
						<col width="40%">
						<col width="10%">
						<col width="40%">
					</colgroup>
					<tr><th colspan="1">학과</th><td colspan="3">${scoreObjectionView.student.subjctNm}</td></tr>		
					<tr>
					<th>학번</th><td>${scoreObjectionView.student.stdntNo}</td>
					<th>학년</th><td>${scoreObjectionView.student.sknrgSttusGrade} 학년</td>
					</tr>		
					<tr>
					<th>학생명</th><td>${scoreObjectionView.student.stdntNm}</td>
					<th>연락처</th><td>${scoreObjectionView.student.stdntTelno}</td>
					</tr>
				</table>
			</div>
			<br/><br/>
			<div class="sub_tit04"><span></span>성적</div>
			<div>
				<table class="table_style02  table_center hover_none">
					<colgroup>
						<col width="13%">
						<col width="13%">
						<col width="13%">
						<col width="13%">
						<col width="13%">
						<col width="13%">
						<col width="13%">
						<col width="13%">
					</colgroup>
					<tr>
					<c:forEach items="${scoreObjectionView.scoreVO}" var="score">
					<th>${score.scoreSe}</th>
					<td>${score.scoreScore} 점</td>
					</c:forEach>
					</tr>
				</table>
			</div>
			<br/><br/>
			<div class="sub_tit04"><span></span>신청 내용</div> 
			<div>
				<table class="table_style02 table_center hover_none">
					<colgroup>
						<col width="100%">
					</colgroup>
					<tr>
						<th>신청 내용</th>
					</tr>
					<tr>
						<td style="text-align:left; padding-left:10px; padding-right:10px;">${scoreObjectionView.scoreObjcCn}</td>
					</tr>			
				</table>
			</div>
			
			<c:if test="${scoreObjectionView.confmSe eq '반려'}">
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
								<td style="text-align:left; padding-left:10px; padding-right:10px;">${scoreObjectionView.scoreObjcReturn}</td>
							</tr>					
						</table>
					</div>					
			</c:if>
			
			<c:if test="${scoreObjectionView.confmSe eq '승인 완료'}">
					<div class="sub_tit04"><span></span>정정 내용</div> 
					<div>
						<table class="table_style02 table_center hover_none">
							<colgroup>
								<col width="100%">
							</colgroup>
							<tr>
								<th>정정 내용</th>
							</tr>
							<tr>
								<td style="text-align:left; padding-left:10px; padding-right:10px;">${scoreObjectionView.scoreObjcReturn}</td>
							</tr>					
						</table>
					</div>					
			</c:if>
						
			<c:if test="${scoreObjectionView.confmSe eq '승인 대기'}">
				<br>
				<div style="display:flex; float:right;">
					<button class="btn btn-danger mr10 ft_left" id="refuseScoreObjectionBtn" style="width:80px;" data-toggle="modal" data-target="#refuseScoreObjectionModal">반려</button>
					<button class="btn btn-primary ft_right" id="resetScoreObjectionBtn" data-toggle="modal" data-target="#reSetScoreObjectionModal">성적 정정</button>
				</div>	
			</c:if>
			
		</div>
  		<br />
	</div>
 </div>
 

 <script>
$(function(){
	$("#refuseScoreObjectionModalClose").click(function(){
	    $("#refuseScoreObjectionModal").modal('hide');
	});
	
	$("#refuseScoreObjectionModal").on('hidden.bs.modal', function () {
	    $("#refuseScoreObjectionContent").val('');
	});
	
	$("#reSetScoreObjectionModalClose").click(function(){
	    $("#reSetScoreObjectionModal").modal('hide');
	});
	
	$("#reSetScoreObjectionModal").on('hidden.bs.modal', function () {
	    $("#resetMiddleScore").val('');
	    $("#resetFinalScore").val('');
		$("#resetAssignmentScore").val("");
		$("#resetAttendanceScore").val("");
		$("#resetScoreTextArea").val("");
	});
})
</script>