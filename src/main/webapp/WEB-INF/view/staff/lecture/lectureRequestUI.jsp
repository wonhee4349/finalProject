<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<style>
.secondModal {margin-top: 200px;}
.pstatus01{width:100px;border-radius:100px;text-align:center;background:#f2f2f2;display:block;color: #4b4b4b;border: 2px solid #c3c3c3; margin:0 auto;}
.pstatus02{width:100px;border-radius:100px;text-align:center;background: #eaf7fd;display:block;color: #2547af;border: 2px solid #c9d7ff; margin:0 auto;}
.pstatus03{width:100px;border-radius:100px;text-align:center;background: #ffe7e7;display:block;color: #d53131;border: 2px solid #f39292; margin:0 auto;}

</style>

<!-- 모달 창 추가 -->
	<div class="modal fade" id="modal_open" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	    <div class="modal-dialog modal-xl" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">강의 신청</h5>
	              <button id="modal_openClose" class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
					    <span aria-hidden="true">×</span>
				  </button>

	            </div>
	            <div class="modal-body ">
	            </div>
	        </div>
	    </div>
	</div>
<!-- 모달끝 -->

<!-- 반려 사유 입력하는 모달 -->
	<div class="modal fade mt-100" id="lectureRefuseModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true" style="margin-top:90px;">
	    <div class="modal-dialog modal-xs" role="document" id="secondModal">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">강의 반려</h5>
	              <button id="lectureRefuseModalClose" class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
					    <span aria-hidden="true">×</span>
				  </button>
	            </div>
	            <div>
	            	<div class="modal-body">
	            		<div class="sub_tit04 sub_tit05"><span></span>반려 사유 입력</div>
						<table class="table table_style01 mt-4 table_center" id="dataTable" width="100%">
							<thead>
								<tr>
									<td>
										<textarea rows="5" cols="50" id="reasonContent" style="resize:none;" required></textarea>
									</td>
								</tr>
							</thead>
							<tbody id="modalStudentBody"></tbody>
						</table>
						<div>
							<form id="refusedLectureRequest" action="/staff/lecture/updateRefusedLectureRequest" method="post">
								<input type="hidden" id="lctreReqstReturn" name="lctreReqstReturn">
								<input type="hidden" id="lctreReqstNo" name="lctreReqstNo">
								<input type="submit" value="저장" class="btn btn-primary ft_right" style=" margin-bottom: 15px;">
							</form>
						</div>
					</div>
	            </div>
	        </div>
	    </div>
	</div>
<!-- 모달끝 -->

<!-- Begin Page Content -->
               <div class="container-fluid">
                   <div class="card2">
                       <div class="sub_tit02">강의 신청</div>
                       <div id="searchUI" class="sub_top_wrap">

                           <!-- selectbox -->
                           <div class="s_option">
                               <div class="select-container">
                                   <select name="searchType" class="custom-select02">
			                            <option value="" label="전체" />		                           
			                            <option value="prfsorNm" label="교수명" />
			                            <option value="courseNm" label="과목명" />
                                   </select>
                                   <div class="select-arrow">
                                       <i class="fa fa-caret-down"></i>
                                   </div>
                               </div>
                           </div>
                           
                           <!-- search -->
                           <div class="d-none d-sm-inline-block  ml-md-3 my-2 my-md-0 navbar-search">
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
                       <form action="<c:url value='/staff/lecture/ajax/lectureRequest'/>" id="searchForm">
							<input type="hidden" name="searchType">
							<input type="hidden" name="searchWord">
							<input type="hidden" name="page">
						</form>
                       <div>
                           <table class="table table_style01 mt-4 table_center" id="dataTable" width="100%" cellspacing="0">
								<thead>
	                               <colgroup>
		                        		<col width="10%">
		                        		<col width="25%">
		                        		<col width="25%">
		                        		<col width="25%">
		                        		<col width="15%">
		                       		</colgroup>
	                               <tr>
	                                   <th>번호</th>
							            <th>교수명</th>
							            <th>강의구분</th>
							            <th>강의명</th>
							            <th>처리상태</th>
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

<script src="/resources/js/app/ljh/staffLectureRequest.js"></script>














