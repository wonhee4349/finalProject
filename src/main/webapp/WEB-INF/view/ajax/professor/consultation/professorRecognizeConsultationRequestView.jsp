<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<security:authentication property="principal" var="principal"/>
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
.jswd50{width:50%; float:left;}
.form-controljs{
	height: calc(1.5em + .75rem + 2px);
    padding: .375rem .75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #6e707e;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #d1d3e2;
    border-radius: .35rem;}
.jstit{line-height:38px}    
.jspl50{padding-left:58px;}
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
					<tr><th>학번</th><td>${recognizeConsultation.student.stdntNo}</td></tr>		
					<tr><th>학생명</th><td>${recognizeConsultation.student.stdntNm}</td></tr>
					<tr><th>학과</th><td>${recognizeConsultation.student.sknrgSttusMajor1}</td></tr>		
				</table>
			</div>
			<div>
				<table class="table_style02 table_right table_center">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>학년</th><td>${recognizeConsultation.student.sknrgSttusGrade} 학년</td></tr>
					<tr><th>희망 상담일</th><td>${recognizeConsultation.cnsltRequstDate} (${recognizeConsultation.cnsltRequstTime})</td></tr>				
					<tr><th>승인 구분</th><td>${recognizeConsultation.cnsltRequstProcess}</td></tr>				
				</table>
			</div>
			<div class="sub_tit04"><span></span>신청 정보</div> 
			<div>
				<table class="table_style02 table_center">
					<colgroup>
						<col width="100%">
					</colgroup>
					<tr>
						<th>신청 내용</th>
					</tr>
					<tr>
						<td>${recognizeConsultation.cnsltRequstCn}</td>
					</tr>
				</table>
				<br/>	
				<c:if test="${not empty recognizeConsultation.cnsltRequstReturn}">
					<div class="sub_tit04"><span></span>상담 내역</div> 
					<div>
						<table class="table_style02 table_center">
								<colgroup>
									<col width="100%">
								</colgroup>			
							<tr>
								<th>상담완료일</th>
							</tr>
							<tr>
								<td>${recognizeConsultation.consulatation.cnsltDate}</td>
							</tr>
							<tr>
								<th>상담 내역</th>
							</tr>
							<tr>
								<td>${recognizeConsultation.consulatation.cnsltDtls}</td>
							</tr>
						</table>
					</div>
				</c:if>
					<br/>	
				<c:if test="${empty recognizeConsultation.cnsltRequstReturn}">
					<div class="sub_tit04"><span></span>상담내역 등록</div> 
					<div>
						<table class="table_style02 table_center">
								<colgroup>
									<col width="100%">
								</colgroup>
							<tr>
								<th>일정선택</th>
							</tr>								
							<tr>
								<td>
									<div class="jswd50">
									날짜 선택 &nbsp; <input type="date" id="consultationDate"  class="form-controljs"/>
									</div>
									<!-- 일정선택 select -->
									<div class="jswd50 jspl50">
										<div class="s_option wd100">
											<div class="jstit">시간표 선택</div>
											&nbsp;&nbsp;
											<div class="select-container">
											
												<select class="custom-select02" id="timetable">
														<option value="" label="시간선택" />
													<c:forEach items="${timetable}" var="time">
														<option value="${time.comCode}" label="${time.comCodeNm}" />
													 </c:forEach>
												</select>
												<div class="select-arrow">
													<i class="fa fa-caret-down"></i>
												</div>
											</div>
										</div>
									</div>
								</td>
							</tr>				
							<tr>
								<th>상담 내역 입력창</th>
							</tr>
							<tr>
								<td><textarea id="consultationContent" rows="10" cols="80" class="form-control"></textarea></td>
							</tr>

							
						</table>
					</div>
					<form id="createRecognizeConsultation" action="/professor/consultation/createProfessorRecognizeConsultation" method="post">
						<input type="hidden" id="recogCnsltNo" name="cnsltNo" value="${recognizeConsultation.consulatation.cnsltNo}" />
						<input type="hidden" id="recogCnsltCnltnt" name="cnsltCnsltnt" value="${principal.realUser.prfsorNo}" />
						<input type="hidden" id="recogStdntNo" name="stdntNo" value="${recognizeConsultation.student.stdntNo}" />
						<input type="hidden" id="recogCnsltDate" name="cnsltDate" value="" />
						<input type="hidden" id="recogCnsltDtls" name="cnsltDtls" value="" />
						<input type="hidden" id="recogTmtbSe" name="tmtbSe" value="" />
						<br/>
						<input type="submit" value="저장" class="btn btn-primary" style="width:928px;">
					</form>
				</c:if>
								
			</div>
		</div>
  		<br />
	</div>
 </div>
 
