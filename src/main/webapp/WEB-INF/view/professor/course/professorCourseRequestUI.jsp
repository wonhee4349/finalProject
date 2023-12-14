<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>

.ft-left{float:left;}

.wd50{width:50% ;}
</style>

<!-- modal -->
<div class="modal fade" id="modal_open" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
    <div class="modal-dialog modal-xl" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLabel01">교과목 개설신청 상세정보</h5>
              <button class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
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
		<div class="sub_tit02"><span></span>교과목 개설 신청</div>
		<div class="sub_top_wrap">
			
		</div>
			<br/>
			
			<div class="class_list_wrap">
				<div class="list_top">
			        <button class="gray_btn small_btn active" data-tab="tab-1">교과목 신청</button>
			        <button class="gray_btn small_btn" data-tab="tab-2">신청 목록</button>
				</div>
			</div>
		<div class="tab_contents" style="height:500px;">
		    <div id="tab-1-content" class="tab-content current">
		    		
		        <!-- 교과목 신청 입력 창 -->
		    	<security:authentication property="principal" var="principal"/>
       			<div>
       				<form id="professorCourseRequestForm" action="/professor/course/new" method="post">
				    	<!-- 학과 코드 선택 -->
				    	<div class="wd50 ft-left">
			                <label for="clgNo">단과대학</label>
			                <select name="clgNo" class="form-style01" required>
			                	<option value label="선택">
			                    <c:forEach var="college" items="${college}">
			                        <option value="${college.clgNo}">${college.clgNm}</option>
			                    </c:forEach>
			                </select>
		                </div>
				    	<div class="wd50 ft-left">
			                <label for="subjctNo">학과</label>
			                <select name="subjctNo" class="form-style01" required>
			                	<option value label="선택">
			                    <c:forEach var="subject" items="${subjectList}">
			                        <option value="${subject.subjctNo}">${subject.subjctNm}</option>
			                    </c:forEach>
			                </select>
		                </div>
		                <div class="wd50 ft-left">
			                <label for="complSe">이수구분</label>
			                <select name="complSe" class="form-style01" required>
			                	<option value="" label="선택">
			                    <c:forEach var="complSe" items="${confmSe}">
			                        <option value="${complSe.comCode}">${complSe.comCodeNm}</option>
			                    </c:forEach>
			                </select>	
		                </div>
		                
		                <div class="wd50 ft-left">
		                	 <label for="courseReqstNm">교과목명</label>
		            	    <input type="text" name="courseReqstNm" class="form-style01" required/>		                
		                </div>
		                <div class="wd50 ft-left">
		                	 <label for="coursePnt">이수학점</label>
		               	 	<input type="text" name="coursePnt" class="form-style01 datepicker"  required/>
		                </div>
		                 <div class="wd50 ft-left">
		                 	 <label for="prfsorNo">신청자</label>
		       		         <input type="text" name="prfsorNo" class="form-style01 datepicker"
		       		         	 value="${principal.realUser.prfsorNo}" readOnly />
		                 </div>
			            <input type="submit" class="btn btn-success ft_right mt-4" value="등록"/>
          			</form>
		     </div>
		  </div>
		  
		    <div id="tab-2-content" class="tab-content">
		        교과목 신청 목록
		        
			    <div class="sub_top_wrap">

					        <!-- selectbox -->
			        <div class="select-container">
			            <select class="custom-select02">
			                <option value="">전체</option>
			                <option value=coursePnt>학점</option>
			                <option value="complSe">이수구분</option>
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
				<form action="<c:url value='/professor/course/ajax/courseRequestList'/>"
					id="searchForm">
					<input type="hidden" name="searchType"> <input type="hidden"
						name="searchWord"> <input type="hidden" name="page">
				</form>
				<div>
					<table class="table table_style01 mt-4 table_center" id="dataTable"
						width="35%" cellspacing="0">
						<thead>
						<colgroup>
							<col width="5%">
							<col width="10%">
							<col width="20%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
						</colgroup>
						<tr>
							<th>번호</th>
							<th>학과</th>
							<th>교과목명</th>
							<th>이수구분</th>
							<th>학점</th>
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

<script src="${pageContext.request.contextPath}/resources/js/app/kjs/professorCourseRequest.js"></script>