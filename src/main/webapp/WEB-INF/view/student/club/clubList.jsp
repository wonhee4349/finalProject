<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



	
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
	            <div class="homework_wrap mb-4">
     <div>
		<div>                   
			<div>
				<table class="table_style01 table_left table_center">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>동아리명</th><td clubNm>${clubInfo.clubNm}</td></tr>
					<tr><th>구분</th><td clubSe>${clubInfo.clubSe}</td></tr>		
				</table>
			</div>
			<div>
				<table class="table_style01 table_right table_center">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>동아리실</th><td fcltsNm>${clubInfo.facilities.building.buldNm}(${clubInfo.facilities.fcltsNm})</td></tr>
					<tr><th>회장</th><td stdntNm >${clubInfo.student.stdntNm}</td></tr>				
				</table>
			</div>
			<div>
				<table class="table_style01 table_center">
					<colgroup>
						<col width="100%">
					</colgroup>
					<tr>
						<th>동아리 소개</th>
					</tr>
					<tr>
						<td clubIntrcn style="min-height:150px; padding-left:10px; text-align:left">${clubInfo.clubIntrcn}</td>
					</tr>			
				</table>
			</div>
		</div>

		<br />
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
                       <div class="sub_tit02">동아리</div>
                       <div class="sub_top_wrap">

                           <!-- selectbox -->
                           <div class="s_option">
                               <div class="select-container">
                                   <select class="custom-select02">
			                            <option value="" label="전체" />
			                            <option value="clubNm" label="동아리명" />
			                            <option value="clubSe" label="구분" />
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
                       <form action="<c:url value='/student/club/ajax/clubList'/>" id="searchForm">
							<input type="hidden" name="searchType">
							<input type="hidden" name="searchWord">
							<input type="hidden" name="page">
						</form>
                       <div>
                           <table class="table table_style01 mt-4 table_center" id="dataTable" width="100%" cellspacing="0">
								<thead>
	                               <colgroup>
		                        		<col width="5%">
		                        		<col width="10%">
		                        		<col width="30%">
		                        		<col width="30%">
		                        		<col width="10%">
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

							
							
							<div class="clear"></div>

                           <div aria-label="Page navigation example nav_center">
                           		<span id="pagingArea"></span>
                           </div>

                       </div>
                   </div>



               </div>

<script src="/resources/js/app/kwh/studentClub.js"></script>