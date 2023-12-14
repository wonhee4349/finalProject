<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
.tab-content {min-height: 700px;}
.minHeight600 {min-height: 600px;}
.minHeight700 {min-height: 700px;}

.search_top{height:60px; float:left;}
.search_bottom{float: right;}
.wd130{width: 250px !important;}
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
	                <h5 class="modal-title" id="modalLabel01">장학금</h5>
					<button id="modal_close" class="close" type="button" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>

	            </div>
	            <div class="modal-body ">
	            </div>
	        </div>
	    </div>
	</div>
<!-- 모달끝 -->

<!-- 총장학금 추가 모달 -->
	<div class="modal fade mt-100" id="totalScholarshipInsertModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true" style="margin-top:90px;">
	    <div class="modal-dialog modal-xs" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">장학금 등록</h5>
					<button id="totalScholarshipInsertModal-close" class="close" type="button" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
	            </div>
	            <div class="totalScholarshipInsertModal-body" style="padding:10px;">
					<table class="table table_style01 mt-4 table_center" id="dataTable" width="100%">
						<tr>
							<th>장학금명</th>
							<td>
								<input type="text" id="scholarshipNm" name="schoNm" style="width: 90%;" required>
							</td>
						</tr>
					</table>
					<div>
						<form id="insertTotalScholarshipForm" action="/staff/scholarship/insertTotalScholarship" method="post">
							<input type="hidden" id="insertScholarshipName" name="schoNm">
							<input type="submit" value="저장" class="btn btn-primary ft_right" style=" margin-bottom: 15px;">
						</form>
					</div>
	            </div>
	        </div>
	    </div>
	</div>
	
<!-- 학기별 장학금 추가 모달 -->
	<div class="modal fade" id="scholarshipInsertModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	    <div class="modal-dialog modal-xl" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">학기별 장학금 등록</h5>
					<button id="modal_close" class="close scholarshipInsertModal" type="button" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>

	            </div>
	            <div class="scholarshipInsertModal-body ">
	            </div>
	        </div>
	    </div>
	</div>
<!-- 모달끝 -->

<!-- Begin Page Content -->
	<div class="container-fluid">

		<div class="card2" style="height: 935px;">
			<div class="sub_tit02">장학금</div>
					
			<div>
				<div class="class_list_wrap">
					<div class="list_top">
						<button class="gray_btn small_btn active" data-tab="tab-1">학기별 장학금</button>
						<button class="gray_btn small_btn" data-tab="tab-2">장학금 목록</button>
					</div>
				</div>
			
				<div class="tab_contents2">
				
					<!-- tab-1 start -->
					<div id="tab-1" class="tab-content current">
						<div id="searchUI1" class="sub_top_wrap">
							<div class="search_top">
								<div class="s_option02 mr-4 ft-left">
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
								
								<div class="s_option ft-left wd130">
									<span class="option_tit" style="width: 50%;">장학금명</span>
									<input type="text" name="schoNm" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
								</div>
							</div>
							
							<div class="search_bottom">
								<div class="s_option02 mr-4 ft-left">
									<span class="option_tit" style="width:80px;">지급방식</span>
									<div class="select-container">
										<select class="custom-select02 searchFormUIInput" name="pymntSe">
											<option value="" label="전체">
											<option value="01" label="등록금 감면">
											<option value="02" label="현금 지급">								
										</select>
										<div class="select-arrow">
											<i class="fa fa-caret-down"></i>
										</div>
									</div>
								</div>
								
								<div class="input-group-append ml-2" style="height: 38px;">
									<button class="btn btn-primary" id="searchBtn" type="button">
										<i class="fas fa-search fa-sm" ></i>
									</button>
								</div>
								
							</div>
							
			            </div>

						<div style="min-height: 640px;">
							<table class="table table_style01 mt-4 table_center" id="dataTable" width="100%" cellspacing="0">
								<thead>
									<colgroup>
										<col width="10%">
										<col width="15%">
										<col width="15%">
										<col width="30%">
										<col width="30%">
									</colgroup>
									<tr>
										<th>번호</th>
										<th>연도</th>
										<th>학기</th>
										<th>장학금명</th>
										<th>지급방식</th>
									</tr>
								</thead>
								<tbody id="listBody"></tbody>                      
							</table>
						</div>
		
						<div>
							<button class="btn btn-primary ft_right" id="scholarshipInsertBtn" type="button">등록</button>
						</div>
		
						<div class="clear"></div>
		
						<div aria-label="Page navigation example nav_center">
							<span id="pagingArea"></span>
						</div>
						
					</div>
					
					<form action="<c:url value='/staff/scholarship/ajax/scholarshipList'/>" id="searchForm">
						<input type="hidden" name="searchSe" id="searchSe">
						<input type="hidden" name="semstrNo">
						<input type="hidden" name="schoNm">
						<input type="hidden" name="pymntSe">
						<input type="hidden" name="page">
					</form>							
				
					<div id="tab-2" class="tab-content" style="min-height: 700px;">
						<div id="searchUI1" class="sub_top_wrap"><div class="search_top"></div></div>
					
						<form action="<c:url value=''/>" id="searchTotalForm">
							<input type="hidden" name="searchType">
							<input type="hidden" name="searchWord">
							<input type="hidden" name="page">
						</form>
					
						<div style="min-height: 640px;">
							<table class="table table_style01 mt-4 table_center" id="dataTotalTable" width="100%" cellspacing="0">
								<thead>
									<colgroup>
										<col width="40%">
										<col width="60%">
									</colgroup>
									<tr>
										<th>번호</th>
										<th>장학금명</th>
									</tr>
								</thead>
								<tbody id="listTotalBody"></tbody>                      
							</table>
							
							<div>
								<button class="btn btn-primary ft_right" id="insertTotalBtn" data-toggle="modal" data-target="totalScholarshipInsertModal">등록</button>
							</div>
			
							<div class="clear"></div>
			
							<div aria-label="Page navigation example nav_center">
								<span id="totalPagingArea"></span>
							</div>
							
						</div>
						
			            
											
					</div>	
					
							
				</div>				
			</div>
		</div>
	</div>
<script src="/resources/js/app/msy/staffScholarship.js"></script>