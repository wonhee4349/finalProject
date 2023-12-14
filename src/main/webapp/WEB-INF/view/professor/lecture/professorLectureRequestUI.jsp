<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<security:authentication property="principal" var="principal" />
<style>
.box_tit01{width:100%;border-radius: 3px;text-align:center;line-height: 40px;height:40px;background: #f3f3f3;border-top: 2px solid #b2c1d4;font-size: 18px;color: #6b8bb9;}
.tit02{font-size:18px;}
.textbox{min-height:150px; overflow-y:scroll}
.lectureroom{width:50%; float:left;}
.ft-left{float:left;}
.wd50{width:50% ;}
.sub_tit05{float:none;margin-bottom: 0;height: 16px;}
.textbox::-webkit-scrollbar {
   display: none;
 }
</style>

<!-- modal -->
<div class="modal fade" id="modal_open" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
    <div class="modal-dialog modal-xl" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLabel01">강의개설신청 상세정보</h5>
              <button id="lectureRequestClose" class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
				    <span aria-hidden="true">×</span>
			  </button>
				
            </div>
            <div class="modal-body ">
            </div>
        </div>
    </div>
</div>

<div class="container-fluid">
	<div class="card2">
<!-- Begin Page Content -->
<form id="newLectureRequestForm" name="newLectureRequestForm" action="/professor/lecture/newLectureRequest" method="post">
	<security:authorize access="isAuthenticated()">
		<security:authentication property="principal.realUser" var="realUser" />
	</security:authorize>
		<input type="hidden" name="prfsorNo" value="${realUser.prfsorNo}"  />
		<input type="hidden" name="confmSe" value="01"  />
		<input type="hidden" name="tab" id="txtTab" value="${param.tab}" />
		
		
		<div class="sub_tit02">강의 개설 신청</div>
		<div class="sub_top_wrap"></div>
		<br/>
		<div class="class_list_wrap">
			<div class="list_top">
		        <button type="button" class="gray_btn small_btn active" id="btnTab1" data-tab="tab-1">강의신청 등록</button>
		        <button type="button" class="gray_btn small_btn" id="btnTab2" data-tab="tab-2">강의신청 목록</button>
			</div>
		</div>
		
		<div class="tab_contents" style="height:850px;">
		    <div id="tab-1-content" class="tab-content current">
		    
			<div class="sub_tit05"><span></span>성적 평가 기준</div>
			<br />
			<div class="box_tit01">(백분율 100% 기준 산출)</div>
			<table class="table table_style01 table_center" id="dataTable"
				width="100%" cellspacing="0">
				<colgroup>
					<col width="10%">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					<col width="13%">
				</colgroup>
				<tr>
					<th>중간고사</th>
					<td>
						<input type="hidden" name="lectureActionPlanVO.lectureEvaluationStandardVOList[0].scoreSe" 
							placeholder="%" value="01" 
							class="form-control bg-light border-0 small" />
						<input type="number" name="lectureActionPlanVO.lectureEvaluationStandardVOList[0].lctreEvlStdrReflct" placeholder="%" id="middleTest"
						class="form-control bg-light border-0 small" required>
					</td>
					<th>기말고사</th>
					<td>
						<input type="hidden" name="lectureActionPlanVO.lectureEvaluationStandardVOList[1].scoreSe" 
							placeholder="%" value="02" 
							class="form-control bg-light border-0 small" />
						<input type="number" name="lectureActionPlanVO.lectureEvaluationStandardVOList[1].lctreEvlStdrReflct" placeholder="%" id="finalTest"
						class="form-control bg-light border-0 small" required>
					</td>
					<th>과제</th>
					<td>
						<input type="hidden" name="lectureActionPlanVO.lectureEvaluationStandardVOList[2].scoreSe" 
							placeholder="%" value="03" 
							class="form-control bg-light border-0 small" />
						<input type="number" name="lectureActionPlanVO.lectureEvaluationStandardVOList[2].lctreEvlStdrReflct" placeholder="%" id="homework"
						class="form-control bg-light border-0 small" required>
					</td>
					<th>출석</th>
					<td>
						<input type="hidden" name="lectureActionPlanVO.lectureEvaluationStandardVOList[3].scoreSe" 
							placeholder="%" value="04" 
							class="form-control bg-light border-0 small" />
						<input type="number" name="lectureActionPlanVO.lectureEvaluationStandardVOList[3].lctreEvlStdrReflct" placeholder="%" id="attendance"
						class="form-control bg-light border-0 small" required>
					</td>
				</tr>
			</table>
	
			<div class="sub_tit05">
				<span></span>강의개설정보
			</div>
			<br />
			<div>
				<div class="box_tit01">작성자 : ${principal.realUser.prfsorNm} 교수</div>
				<table class="table table_style01 table_center" id="dataTable"
					width="100%" cellspacing="0">
					<colgroup>
						<col width="20%">
						<col width="20%">
						<col width="15%">
						<col width="15%">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<tbody id="inputBody">
						<tr>
							<th colspan="1">단과대학</th>
							<td colspan="2">
								<select id="clgSelect" class="form-control bg-light border-0 small" 
								aria-label="Search" aria-describedby="basic-addon2">
					                <option value="-1">단과대선택</option>
					                <c:forEach items="${college}" var="clg">
					                	<option value="${clg.clgNo}" label="${clg.clgNm}"/>
					                </c:forEach>
					            </select>
							</td>
							<th colspan="1">학과</th>
							<td colspan="4">
								<select id="subjctSelect" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
					                <option value="-1">학과선택</option>
					                <option value="NULL">교양과목</option>
					            </select>
							</td>
						</tr>
						<tr>
							<th colspan="1">교과목</th>
							<td colspan="2">
								<select name="courseNo" id="courseSelect" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
					                <option value="-1">교과목선택</option>
					            </select>
							</td>
							<th colspan="1">학점</th>
							<td colspan="4">
								<input type="text" placeholder="학점" readOnly value="" id="selectSubjectCourseCoursePnt"
									class="form-control bg-light border-0 small">
							</td>
						</tr>
						<tr>
							<th colspan="1">강의실</th>
							<td colspan="6">
								<select name="buldNo" id="buldSelect" class="form-control bg-light border-0 small lectureroom" aria-label="Search" aria-describedby="basic-addon2">
					                <option value="-1">건물</option>
					                <c:forEach items="${building}" var="buld">
					                	<option value="${buld.buldNo}" label="${buld.buldNm}" />
					                </c:forEach>
					            </select>
					            <select name="fcltsNo" id="fcltsSelect" class="form-control bg-light border-0 small lectureroom" aria-label="Search" aria-describedby="basic-addon2">
					                <option value="">강의실선택</option>
					            </select>
							</td>
						</tr>
						
						<tr>
							<th>학기</th>
							<td>
								<select name="semstrSe" id="semstrSelect" class="form-control bg-light border-0 small"" aria-label="Search" aria-describedby="basic-addon2">
					                <option value="-1">필수선택</option>
					                <c:forEach items="${semstrSe}" var="semstr">
					                	<option value="${semstr.comCode}">${semstr.comCodeNm}</option>
					                </c:forEach>
					            </select>					
							</td>
							<th>강의형태</th>
							<td>
								<select name="lctreSe" id="lctreSelect" class="form-control bg-light border-0 small"" aria-label="Search" aria-describedby="basic-addon2">
					                <option value="-1">필수선택</option>
					                <c:forEach items="${lctreSe}" var="lctre">
					                	<option value="${lctre.comCode}">${lctre.comCodeNm}</option>
					                </c:forEach>
					            </select>	
							</td>
							<th>이수구분</th>
							<td>
								<select name="complSe" id="compleSelect" class="form-control bg-light border-0 small"" aria-label="Search" aria-describedby="basic-addon2">
					                <option value="-1">필수선택</option>
					                <c:forEach items="${compleSe}" var="comple">
					                	<option value="${comple.comCode}">${comple.comCodeNm}</option>
					                </c:forEach>
					            </select>	
							</td>
						</tr>
					</tbody>
				</table>
				<br />
				
				<div class="sub_tit05"><span></span>강의 시간표
				<button type="button" class="btn btn-primary ft_right add-button" style="margin-right:14px;">추가</button>
				</div>
				<br/>
				<table class="table table_style01 table_center" id="dataTable"
					width="100%" cellspacing="0">
					<colgroup>
						<col width="10%">
						<col width="25%">
						<col width="25%">
						<col width="25%">
					</colgroup>
					<tbody class="timeTableBody">
						<tr class="time-table-row">
							<th colspan="2"  style="text-align:center;">강의시간표</th>
							
							<td colspan="2">
								<select name="lectureRequestInfoVOList[0].tmtbSe" class="form-control bg-light border-0 small tmtbSe" jsSel aria-label="Search" aria-describedby="basic-addon2">
					                <option value="">강의시간표선택</option>
					                <c:forEach items="${tmtbSe}" var="timetable">
					                	<option value="${timetable.comCode}" label="${timetable.comCodeDesc}" />
					                </c:forEach>
					            </select>
					        </td>
					        <td colspan="1">
						        <button type="button" class="btn btn-danger ft_right remove-button">삭제</button>
						    </td>
						</tr>
					</tbody>
				</table>
						
				<div class="sub_tit05">
				<span></span>강의계획서
				</div>
				<div class="tit02 mt-4">강의목표 및 개요</div>
				<div>
					<textarea id="lectureTarget" name="lectureActionPlanVO.lctreAcnutnoGoal" class="form-control textbox"></textarea>
				</div>
	
				<div class="tit02 mt-4">주차별 강의계획</div>
				<div>
					<textarea id="lecturePlan" name="lectureActionPlanVO.lctreAcnutnoPlan" class="form-control textbox"></textarea>
				</div>
	
				<div class="tit02 mt-4">참고사항</div>
				<div>
					<textarea id="lectureNote" name="lectureActionPlanVO.lctreAcnutnoRefer" class="form-control textbox"></textarea>
				</div>
				<button class="btn btn-success mt-3" type="button" id="autoResultLectureRequest" onclick="autoInput()">일괄입력</button>
				<button id="lectureActionPlanBtn" class="btn btn-primary ft_right mt-3" >신청하기</button>
			 </div>
			</div>
		<security:csrfInput/>	
		</form>		
			
			
			<!-- 강의개설신청 목록 리스트  -->
		<div id="tab-2-content" class="tab-content">
			<div class="container-fluid">
				<div class="card2">
				    <div class="sub_top_wrap">
						        <!-- selectbox -->
				        <div class="select-container">
				            <select class="custom-select02" name="searchType" id="searchSelect">
				                <option value="">전체</option>
				                <option value=coursePnt>강의명</option>
				                <option value="complSe">이수구분</option>
				                <option value="complSe">강의구분</option>
				                <option value="confmSe">승인구분</option>
				            </select>
				            <div class="select-arrow">
				                <i class="fa fa-caret-down"></i>
				            </div>
				        </div>
						<!-- search -->
						<div id="searchUI"
							class="d-none d-sm-inline-block  ml-md-3 my-2 my-md-0 navbar-search">
							<div class="input-group wd300 ft_right">
								<input type="text" name="searchWord"
									class="form-control bg-light border-0 small" aria-label="Search"
									aria-describedby="basic-addon2">
								<div class="input-group-append">
									<button class="btn btn-primary" id="searchBtn" type="button">
										<i class="fas fa-search fa-sm"></i>
									</button>
								</div>
							</div>
						</div>
			
					</div>
					<form action="<c:url value='/professor/lecture/ajax/lectureRequestList'/>"id="searchForm">
						<input type="hidden" name="searchType">
						<input type="hidden" name="searchWord">
						<input type="hidden" name="page">
					</form>
					<div>
						<table class="table table_style01 mt-4 table_center" id="dataTable"
							width="35%" cellspacing="0">
							<thead>
							<colgroup>
								<col width="8%">
								<col width="8%">
								<col width="15%">
								<col width="20%">
								<col width="10%">
								<col width="10%">
								<col width="15%">
								<col width="20%">
							</colgroup>
							<tr>
								<th>년도</th>
								<th>학기</th>
								<th>학과</th>
								<th>강의명</th>
								<th>강의구분</th>
								<th>신청자</th>
								<th>신청일</th>
								<th>승인구분</th>
							</tr>
							</thead>
							<tbody id="listBody"></tbody>
						
						</table>
			
						<div aria-label="Page navigation example nav_center">
							<span id="pagingArea"></span>
						</div>
					</div>
		    </div>
		</div>
	</div>
</div>	
</div>
<script src="${pageContext.request.contextPath}/resources/js/app/kjs/professorLectureRequest.js"></script>