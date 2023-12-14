<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    // 문서가 로드된 후 실행
    document.addEventListener("DOMContentLoaded", function () {
        // 모달 열기 버튼 클릭 시 모달 창 열기
        document.getElementById("openModal").addEventListener("click", function () {
            var modal = new bootstrap.Modal(document.getElementById("openModal"));
            modal.show();
        });

        // 모달 닫기 버튼 클릭 시 모달 창 닫기
        document.querySelector(".close").addEventListener("click", function () {
            var modal = bootstrap.Modal.getInstance(document.getElementById("openModal"));
            modal.hide();
        });

    });
</script>

    
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

<!-- 반려 사유 입력하는 모달 -->
	<div class="modal fade mt-100" id="refuseConsultationModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true" style="margin-top:90px;">
	    <div class="modal-dialog modal-xs" role="document" id="secondModal">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">상담 신청 반려</h5>
	              <button id="refuseConsultationClose" class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
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
										<textarea rows="5" cols="50" id="refuseReasonContent" style="resize:none;" required></textarea>
									</td>
								</tr>
							</thead>
							<tbody id="modalStudentBody"></tbody>
						</table>
						<div>
							<form id="refusedConsultationRequest" action="/professor/consultation/updateRefuseConsultationRequest" method="post">
								<input type="hidden" id="cnsltRequstReturn" name="cnsltRequstReturn">
								<input type="hidden" id="cnsltNo" name="cnsltNo">
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
                       <div class="sub_tit02">상담 신청 목록</div>
                       <div class="sub_top_wrap">

                           <!-- selectbox -->
                           <div class="s_option">
                               <div class="select-container">
                                   <select class="custom-select02">
			                            <option value="" label="전체" />
			                            <option value="stdntNm" label="학생명" />
			                            <option value="stdntNo" label="학번" />
			                            <option value="cnsltRequstProcess" label="처리상태" />
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
                       <form action="<c:url value='/professor/consultation/ajax/consultationRequest'/>" id="searchForm">
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
							            <th>학번</th>
							            <th>학생명</th>
							            <th>학과</th>
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

<script src="${pageContext.request.contextPath}/resources/js/app/kjs/professorConsultationRequest.js"></script>