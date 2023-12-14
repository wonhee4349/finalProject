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
    
<!-- 장학생 상세보기 모달 -->
	<div class="modal fade" id="modal_open" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	    <div class="modal-dialog modal-xl" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">장학생</h5>
	              <button class="close" id="closeScholarshipStudentModal" type="button" data-bs-dismiss="modal" aria-label="Close">
					    <span aria-hidden="true">×</span>
				  </button>

	            </div>
	            <div class="modal-body ">
	            </div>
	        </div>
	    </div>
	</div>
<!-- 모달끝 -->

<!-- 장학생 추가 모달 -->
	<div class="modal fade" id="scholarshipStudentModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	    <div class="modal-dialog modal-xl" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">장학생 등록</h5>
	              <button class="close" id="closeScholarshipStudentInsertModal" type="button" data-bs-dismiss="modal" aria-label="Close">
					    <span aria-hidden="true">×</span>
				  </button>

	            </div>
	            <div class="modal-body " id="insertScholarshipStudent-body">
	            </div>
	        </div>
	    </div>
	</div>
<!-- 모달끝 -->

<!-- Begin Page Content -->
	<div class="container-fluid">

		<div class="card2"  style="height: 970px;">
			<div class="sub_tit02">장학생</div>
                       
				<div>
					<div class="class_list_wrap">
						<div class="list_top">
							<button class="gray_btn small_btn active" data-tab="tab-1">장학생 신청 목록</button>
							<button class="gray_btn small_btn" data-tab="tab-2">장학생 목록</button>
						</div>
					</div>                     
					<div class="tab_contents2">
					
						
						<form action="<c:url value='/staff/scholarshipStudent/ajax/scholarshipStudentList'/>" id="searchForm">
							<input type="hidden" name="searchSe" id="searchSe">
							<input type="hidden" name="stdntNo">
							<input type="hidden" name="stdntNm">
							<input type="hidden" name="semstrNo">
							<input type="hidden" name="schoNm">
							<input type="hidden" name="page">
						</form>	
						
						<!-- tab-1 start -->				
						<div id="tab-1" class="tab-content current minHeight700">
							<div id="searchUI1" class="sub_top_wrap">                                   
									<div class="search_top">
									<div class="s_option02 ft-left wd130">
										<span class="option_tit">학번</span>
										<input type="text" name="stdntNo" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
									</div>
									
									<div class="s_option ft-left wd130">
										<span class="option_tit">이름</span>
										<input type="text" name="stdntNm" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
									</div>
								</div>
								
								<div class="s_option ft-left wd200">
										<span class="option_tit" style="width: 60%;">장학금명</span>
										<input type="text" name="schoNm" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
								</div>
								
								
								<div class="search_bottom">
								
									
									<div class="s_option02 mr-2 ft-left">
										<span class="option_tit" style="width:42px;">학기</span>
										<div class="select-container" style="width:120px;">
											<select id="semstrNo" class="custom-select02 searchFormUIInput" name="semstrNo">
												<option value label="전체" />
	<%-- 											<c:forEach items="${college}" var="col"> --%>
													<option value="20242" label="2024년 (2학기)"></option>
													<option value="20241" label="2024년 (1학기)"></option>
													<option value="20232" label="2023년 (2학기)"></option>
													<option value="20231" label="2023년 (1학기)"></option>
													<option value="20222" label="2022년 (2학기)"></option>
													<option value="20221" label="2022년 (1학기)"></option>
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
	                       </div>
	                       
							<div style="min-height: 640px;">
								<div class="minHeight520">
									<table class="table table_style01 mt-4 table_center" id="dataTable2" width="100%" cellspacing="0">
										<thead>
			                               <colgroup>
				                        		<col width="10%">
				                        		<col width="20%">
				                        		<col width="15%">
				                        		<col width="30%">
				                        		<col width="25%">
				                       		</colgroup>
			                               <tr>
			                                   <th>번호</th>
			                                   <th>학번</th>
			                                   <th>학생명</th>
									           <th>장학금명</th>
									           <th>신청일</th>
			                               </tr>
										</thead>
										<tbody id="listRequestBody"></tbody>                               
									</table>
								</div>
								<div class="clear"></div>
								<div aria-label="Page navigation example nav_center">
	                           		<span id="pagingArea1"></span>
								</div>
	                       </div>
						</div>		
						<!-- tab-1 end -->
						
						<!-- tab-2 start -->
						<div id="tab-2" class="tab-content minHeight700">
							
							<div id="searchUI2" class="sub_top_wrap">
							
								<div class="search_top">
									<div class="s_option02 ft-left wd130">
										<span class="option_tit">학번</span>
										<input type="text" name="stdntNo" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
									</div>
									
									<div class="s_option ft-left wd130">
										<span class="option_tit">이름</span>
										<input type="text" name="stdntNm" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
									</div>
								</div>
								
								<div class="s_option ft-left wd200">
										<span class="option_tit" style="width: 60%;">장학금명</span>
										<input type="text" name="schoNm" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
								</div>
								
								
								<div class="search_bottom">
								
									
									<div class="s_option02 mr-2 ft-left">
										<span class="option_tit" style="width:42px;">학기</span>
										<div class="select-container" style="width:120px;">
											<select id="semstrNo" class="custom-select02 searchFormUIInput" name="semstrNo">
												<option value label="전체" />
	<%-- 											<c:forEach items="${college}" var="col"> --%>
													<option value="20242" label="2024년 (2학기)"></option>
													<option value="20241" label="2024년 (1학기)"></option>
													<option value="20232" label="2023년 (2학기)"></option>
													<option value="20231" label="2023년 (1학기)"></option>
													<option value="20222" label="2022년 (2학기)"></option>
													<option value="20221" label="2022년 (1학기)"></option>
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
							</div>		
							
							<div>
								<div class="minHeight520" style="min-height: 675px;">
									<table class="table table_style01 mt-4 table_center" id="dataTable" width="100%" cellspacing="0">
										<thead>
			                               <colgroup>
				                        		<col width="10%">
				                        		<col width="20%">
				                        		<col width="15%">
				                        		<col width="25%">
				                        		<col width="30%">
				                       		</colgroup>
			                               <tr>
			                                   <th>번호</th>
			                                   <th>학번</th>
			                                   <th>학생명</th>
			                                   <th>장학 학기</th>
									           <th>장학금명</th>
			                               </tr>
										</thead>
										<tbody id="listScholarshipStudentBody"></tbody>                               
									</table>
								</div>
								<div>
									<button data-toggle="modal" data-target="#scholarshipStudentModal" class="btn btn-primary ft_right" id="insertBtn" type="button">등록</button>
								</div>
								<div class="clear"></div>
								<div aria-label="Page navigation example nav_center">
	                           		<span id="pagingArea2"></span>
								</div>
	                       </div>
						</div>		
						<!-- tab-2 end -->
						
						<form action="<c:url value='/staff/scholarshipStudent/ajax/scholarshipStudentList'/>" id="searchForm2">
							<input type="hidden" name="searchSe" id="searchSe">
							<input type="hidden" name="stdntNo">
							<input type="hidden" name="stdntNm">
							<input type="hidden" name="semstrNo">
							<input type="hidden" name="schoNm">
							<input type="hidden" name="page">
						</form>			      
				</div>
			</div>
		</div>
	</div>

<script src="/resources/js/app/msy/staffScholarshipStudent.js"></script>









