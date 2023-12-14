<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 모달 창 추가 -->
	<div class="modal fade" id="modal_open" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	    <div class="modal-dialog modal-xl" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">상담 신청내용</h5>
	              <button class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
					    <span aria-hidden="true">×</span>
				  </button>
	            </div>
	            <div class="modal-body ">
	            </div>
	        </div>
	    </div>
	</div>
<!-- 모달끝 -->

<!-- Begin Page Content -->
               <div class="container-fluid">

                   <div class="card2">
                       <div class="sub_tit03">상담 등록 목록</div>
                       <div class="sub_top_wrap">

                           <!-- selectbox -->
                           <div class="s_option">
                               <div class="select-container">
                                   <select class="custom-select02">
			                            <option value="" label="전체" />
			                            <option value="stdntNm" label="학생명" />
			                            <option value="stdntNo" label="학번" />
			                            <option value="stdntNo" label="학과" />
                                   </select>
                                   <div class="select-arrow">
                                       <i class="fa fa-caret-down"></i>
                                   </div>
                               </div>
                           </div>
                           
                           <!-- search -->
                           <div id="searchUI" class="d-none d-sm-inline-block  ml-md-3 my-2 my-md-0 navbar-search">
                               <div class="input-group wd300 ft_right">
                                   <input type="text" name="searchWord" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
                                   <div class="input-group-append">
                                       <button class="btn btn-primary" id="searchBtn" type="button">
                                           <i class="fas fa-search fa-sm" ></i>
                                       </button>
                                   </div>
                               </div>
                           </div> 

                       </div>
                       <form action="<c:url value='/professor/consultation/ajax/RecognizeConsultationRequest'/>" id="searchForm">
							<input type="hidden" name="searchType">
							<input type="hidden" name="searchWord">
							<input type="hidden" name="page">
						</form>
                       <div>
                           <table class="table table_style01 mt-4 table_center" id="dataTable" width="100%" cellspacing="0">
								<thead>
	                               <colgroup>
		                        		<col width="10%">
		                        		<col width="15%">
		                        		<col width="15%">
		                        		<col width="15%">
		                        		<col width="15%">
		                        		<col width="15%">
		                       		</colgroup>
	                               <tr>
	                                   <th>번호</th>
							            <th>학번</th>
							            <th>학생명</th>
							            <th>학과</th>
							            <th>승인구분</th>
							            <th>신청일</th>
	                               </tr>
								</thead>
								<tbody id="listBody"></tbody>
                           </table>
							
							<div class="clear"></div>

                           <div aria-label="Page navigation example nav_center">
                           		<span id="pagingArea"></span>
                           </div>

                       </div>
                   </div>
               </div>

<script src="${pageContext.request.contextPath}/resources/js/app/kjs/professorRecognizeConsultationRequest.js"></script>