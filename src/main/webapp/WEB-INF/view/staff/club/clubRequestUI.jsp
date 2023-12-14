<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.secondModal {margin-top: 200px;}
.pstatus01{width:100px;border-radius:100px;text-align:center;background:#f2f2f2;display:block;color: #4b4b4b;border: 2px solid #c3c3c3; margin:0 auto;}
.pstatus02{width:100px;border-radius:100px;text-align:center;background: #eaf7fd;display:block;color: #2547af;border: 2px solid #c9d7ff; margin:0 auto;}
.pstatus03{width:100px;border-radius:100px;text-align:center;background: #ffe7e7;display:block;color: #d53131;border: 2px solid #f39292; margin:0 auto;}
.search_top{height:60px; float:left;}
.search_bottom{float: right;}
.wd130{width: 152px !important;}
.s_option02{width: auto;float: left;height: 44px; display: flex;}
.select-container {width: 109px; position: relative;}
.custom-select02 {
	appearance: none;
	-webkit-appearance: none;
	-moz-appearance: none;
	width: 123px;
	padding: 6px;
	border: 1px solid #ced5e9;
	border-radius: 4px;
	background-color: #fff;
	cursor: pointer;
}
.select-arrow {
	position: relative;
	float: right;
	top: -31px;
	right: 0;
}
.custom-select02:active,.custom-select02:focus {border-color: #007bff;}
.custom-select02 option {padding: 10px;}
.option_tit{font-size: 18px;width: 60px;display: block;float: left;line-height: 39px;color: #000;font-weight: 100;}
.pagination{ justify-content: center;}
</style>

<!-- 모달 창 추가 -->
	<div class="modal fade" id="modal_open" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	    <div class="modal-dialog modal-xl" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">동아리 개설 신청</h5>
	              <button id="modal_openClose" class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
					    <span aria-hidden="true">×</span>
				  </button>

	            </div>
	            <div class="modal-body">
	            </div>
	        </div>
	    </div>
	</div>
<!-- 모달끝 -->

<!-- 반려 사유 입력하는 모달 -->
	<div class="modal fade mt-100" id="clubRefuseModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	    <div class="modal-dialog modal-xl" role="document" id="secondModal">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">동아리 신청 반려</h5>
	              <button id="clubRefuseModalClose" class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
					    <span aria-hidden="true">×</span>
				  </button>
	            </div>
	            <div>
	            	<div class="modal-body" style="padding:20px;">
	            		<div class="blue_box" style="margin-bottom:20px;">
	            			동아리개설 반려시 반려사유를 반드시 입력바랍니다.
	            		</div>
	            		<div class="sub_tit04 sub_tit05"><span></span>반려 사유 입력</div>
						<table class= "mt-4 table_center" id="dataTable" width="100%">
							<thead>
								<tr>
									<td>
										<textarea rows="5" cols="50" id="reasonContent" style="resize:none; width:100%; border:1px solid #f2f2f2"" required></textarea>
									</td>
								</tr>
							</thead>
							<tbody id="modalStudentBody"></tbody>
						</table>
						<div>
							<form id="refusedClubRequest" action="/staff/club/updateRefusedClubRequest" method="post">
								<input type="hidden" id="clubEsReturn" name="clubEsReturn">
								<input type="hidden" id="returnClubEsNo" name="clubEsNo">
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
                       <div class="sub_tit02">동아리 개설 신청</div>
                       <div id="searchUI" class="sub_top_wrap">
                       
							<div class="s_option ft-left wd200">
									<span class="option_tit" style="width: 60%;">동아리명</span>
									<input type="text" name="schoNm" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
							</div>
							
							
							<div class="search_bottom">
							
								
								<div class="s_option02 mr-2 ft-left">
									<span class="option_tit" style="width:80px;">승인 구분</span>
									<div class="select-container" style="width:120px;">
										<select id="semstrNo" class="custom-select02 searchFormUIInput" name="semstrNo">
											<option value label="전체" />
											<option value="" label="전체">
											<option value="01" label="승인 대기">
											<option value="02" label="승인 완료">
											<option value="03" label="반려">	
										</select>
										<div class="select-arrow">
											<i class="fa fa-caret-down"></i>
										</div>
									</div>
								</div>
							
								<div class="input-group-append" style="height: 38px;">
									<button class="btn btn-primary" id="searchBtn1" type="button">
										<i class="fas fa-search fa-sm" ></i>
									</button>
								</div>
								
							</div>

<!--                            selectbox -->
<!--                            <div class="s_option"> -->
<!--                                <div class="select-container"> -->
<!--                                    <select name="searchType" class="custom-select02"> -->
<!-- 			                            <option value="" label="전체" /> -->
<!-- 			                            <option value="clubEsNo" label="동아리명" /> -->
<!-- 			                            <option value="clubSe" label="구분" /> -->
<!-- 			                            <option value="confmSe" label="처리상태" /> -->
<!--                                    </select> -->
<!--                                    <div class="select-arrow"> -->
<!--                                        <i class="fa fa-caret-down"></i> -->
<!--                                    </div> -->
<!--                                </div> -->
<!--                            </div> -->
                           
<!--                            search -->
<!--                            <div class="d-none d-sm-inline-block  ml-md-3 my-2 my-md-0 navbar-search"> -->
<!--                                <div class="input-group wd300 ft_right"> -->
<!--                                    <input type="text" name="searchWord" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2"> -->
<!--                                    <div class="input-group-append"> -->
<!--                                        <button class="btn btn-primary" id="searchBtn" type="button"> -->
<!--                                            <i class="fas fa-search fa-sm" ></i> -->
<!--                                        </button> -->
<!--                                    </div> -->
<!--                                </div> -->
<!--                            </div>  -->

                       </div>
                       <form action="<c:url value='/staff/consultation/ajax/consultationRequest'/>" id="searchForm">
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
							           <th>학생명</th>
							           <th>동아리명</th>
							           <th>구분</th>
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

<script src="/resources/js/app/msy/staffClubRequest.js"></script>





