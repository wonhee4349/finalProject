<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<style>
.minHeight520 {min-height: 713px;}
.minHeight700 {min-height: 700px;}
.search_top{height:60px; float:left;}
.search_bottom{float: right;}
.wd130{width: 152px !important;}
.wd200{width: 220px !important;}
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
.pstatus01{width:100px;border-radius:100px;text-align:center;background:#f2f2f2;display:block;color: #4b4b4b;border: 2px solid #c3c3c3; margin:0 auto;}
.pstatus02{width:100px;border-radius:100px;text-align:center;background: #eaf7fd;display:block;color: #2547af;border: 2px solid #c9d7ff; margin:0 auto;}
.pstatus03{width:100px;border-radius:100px;text-align:center;background: #ffe7e7;display:block;color: #d53131;border: 2px solid #f39292; margin:0 auto;}
</style>

<!-- 모달 창 추가 -->
	<div class="modal fade" id="modal_open" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	    <div class="modal-dialog modal-xl" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">동아리 정보</h5>
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
                       <div class="sub_tit02">동아리</div>
                       <div id="searchUI" class="sub_top_wrap">
                       
							<div class="s_option ft-left wd200">
									<span class="option_tit" style="width: 60%;">동아리명</span>
									<input type="text" name="schoNm" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
							</div>
							
							
							<div class="search_bottom">
							
								
								<div class="s_option02 mr-2 ft-left">
									<span class="option_tit" style="width:42px;">구분</span>
									<div class="select-container" style="width:120px;">
										<select id="semstrNo" class="custom-select02 searchFormUIInput" name="semstrNo">
											<option value label="전체" />
<%-- 											<c:forEach items="${college}" var="col"> --%>
												<option value="1" label="학업"></option>
												<option value="2" label="스포츠"></option>
												<option value="3" label="취미"></option>
												<option value="4" label="종교"></option>
												<option value="5" label="기타"></option>
<%-- 											</c:forEach> --%>
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
<!-- 			                            <option value="clubNm" label="동아리명" /> -->
<!-- 			                            <option value="clubSe" label="구분" /> -->
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
                       <form action="<c:url value='/staff/club/ajax/clubList'/>" id="searchForm">
							<input type="hidden" name="searchType">
							<input type="hidden" name="searchWord">
							<input type="hidden" name="page">
						</form>
                       <div>
							<div style="min-height:630px;">
								<table class="table table_style01 mt-4 table_center" id="dataTable" width="100%" cellspacing="0">
									<thead>
		                               <colgroup>
			                        		<col width="5%">
			                        		<col width="20%">
			                        		<col width="30%">
			                        		<col width="30%">
			                        		<col width="15%">
			                       		</colgroup>
		                               <tr>
		                                   <th>번호</th>
								            <th>구분</th>
								            <th>동아리명</th>
								            <th>동아리실</th>
								            <th>동아리회장</th>
		                               </tr>
									</thead>
									<tbody id="listBody"></tbody>
	                               
	                           </table>
							</div>

							<div class="clear"></div>

                           <div aria-label="Page navigation example nav_center">
                           		<span id="pagingArea"></span>
                           </div>

                       </div>
                   </div>



               </div>
<script src="/resources/js/app/msy/staffClub.js"></script>