<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.secondModal {margin-top: 200px;}
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
.minHeight600 {min-height: 627px;}
.minHeight700 {min-height: 700px;}

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
.pstatus01{width:100px;border-radius:100px;text-align:center;background:#f2f2f2;display:block;color: #4b4b4b;border: 2px solid #c3c3c3; margin:0 auto;}
.pstatus02{width:100px;border-radius:100px;text-align:center;background: #eaf7fd;display:block;color: #2547af;border: 2px solid #c9d7ff; margin:0 auto;}
.pstatus03{width:100px;border-radius:100px;text-align:center;background: #ffe7e7;display:block;color: #d53131;border: 2px solid #f39292; margin:0 auto;}
</style>


<!-- Begin Page Content -->
<div class="container-fluid">
	<div class="card2">
		<div class="sub_tit02">휴/복학 관리</div>
		
		<div>
			<div class="class_list_wrap">
				<div class="list_top">
						<button class="gray_btn small_btn active" data-tab="tab-1">휴학 신청</button>
						<button class="gray_btn small_btn" data-tab="tab-2">복학 신청</button>
					</div>
			</div>
			
			<div class="tab_contents2">
			
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
						
						<div class="search_bottom">
							<div class="s_option02 mr-4 ft-left">
								<span class="option_tit">단과대</span>
								<div class="select-container">
									<select id="clgNo" class="custom-select02 searchFormUIInput" name="clgNo">
										<option value label="전체" />
										<c:forEach items="${college}" var="col">
											<option value="${col.clgNo}" label="${col.clgNm}"></option>
										</c:forEach>
									</select>
									<div class="select-arrow">
										<i class="fa fa-caret-down"></i>
									</div>
								</div>
							</div>
							
							<div class="s_option02 mr-4 ft-left">
								<span class="option_tit">학과</span>
								<div class="select-container">
									<select id="sknrgSttusMajor1" class="custom-select02 searchFormUIInput" name="sknrgSttusMajor1">
										<option value label="전체" />
										<c:forEach items="${subject}" var="sub">
											<option class="clg${sub.clgNo} clgSubject" value="${sub.subjctNo}" label="${sub.subjctNm}"></option>
										</c:forEach>
									</select>
									<div class="select-arrow">
										<i class="fa fa-caret-down"></i>
									</div>
								</div>
							</div>
							
							<div class="s_option02 mr-4 ft-left">
								<span class="option_tit" style="width:80px;">승인 구분</span>
								<div class="select-container">
									<select class="custom-select02 searchFormUIInput" name="confmSe">
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
					</div>
					<div>
						<div class="minHeight600">
							<table class="table table_style01 mt-4 table_center" id="dataTable" width="100%" cellspacing="0">
								<thead>
									<colgroup>
		                        		<col width="5%">
		                        		<col width="15%">
		                        		<col width="10%">
		                        		<col width="15%">
		                        		<col width="15%">
		                        		<col width="10%">
		                        		<col width="10%">
		                       		</colgroup>
									<tr>
										<th>번호</th>
										<th>학번</th>
										<th>이름</th>
										<th>단과대</th>
										<th>학과</th>
										<th>신청일</th>
										<th>승인 구분</th>
									</tr>
								</thead>
								<tbody id="listBody1"></tbody>                       
							</table>
						</div>
			
						<div aria-label="Page navigation example nav_center">
							<span id="pagingArea1"></span>
						</div>
					</div>
				</div>
				<!-- tab-1 end -->
				
				<div id="tab-2" class="tab-content minHeight700">
					<div id="searchUI2" class="sub_top_wrap">						                
						<!-- search -->
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
						
						<div class="search_bottom">
						
							<div class="s_option02 mr-4 ft-left">
								<span class="option_tit">단과대</span>
								<div class="select-container">
									<select id="clgNo" class="custom-select02 searchFormUIInput" name="clgNo">
										<option value label="전체" />
										<c:forEach items="${college}" var="col">
											<option value="${col.clgNo}" label="${col.clgNm}"></option>
										</c:forEach>
									</select>
									<div class="select-arrow">
										<i class="fa fa-caret-down"></i>
									</div>
								</div>
							</div>
							
							<div class="s_option02 mr-4 ft-left">
								<span class="option_tit">학과</span>
								<div class="select-container">
									<select id="sknrgSttusMajor1" class="custom-select02 searchFormUIInput" name="sknrgSttusMajor1">
										<option value label="전체" />
										<c:forEach items="${subject}" var="sub">
											<option class="clg${sub.clgNo} clgSubject" value="${sub.subjctNo}" label="${sub.subjctNm}"></option>
										</c:forEach>
									</select>
									<div class="select-arrow">
										<i class="fa fa-caret-down"></i>
									</div>
								</div>
							</div>
							
							<div class="s_option02 mr-4 ft-left">
								<span class="option_tit" style="width:80px;">승인 구분</span>
								<div class="select-container">
									<select class="custom-select02 searchFormUIInput" name="confmSe">
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
								<button class="btn btn-primary" id="searchBtn2" type="button">
									<i class="fas fa-search fa-sm" ></i>
								</button>
							</div>
						
						</div>
					</div>
					<div>
						<div class="minHeight600">
							<table class="table table_style01 mt-4 table_center" id="dataTable" width="100%" cellspacing="0">
								<thead>
									<colgroup>
		                        		<col width="5%">
		                        		<col width="15%">
		                        		<col width="10%">
		                        		<col width="15%">
		                        		<col width="15%">
		                        		<col width="10%">
		                        		<col width="10%">
		                       		</colgroup>
									<tr>
										<th>번호</th>
										<th>학번</th>
										<th>이름</th>
										<th>단과대</th>
										<th>학과</th>
										<th>신청일</th>
										<th>승인 구분</th>
									</tr>
								</thead>
								<tbody id="listBody2"></tbody>                       
							</table>
						</div>
						<div aria-label="Page navigation example nav_center">
							<span id="pagingArea2"></span>
						</div>
					</div>
				</div>
				
				<form action="<c:url value='/staff/absence/ajax/absenceRequestData'/>" id="searchForm">
					<input type="hidden" name="searchSe" id="searchSe">
					<input type="hidden" name="stdntNo">
					<input type="hidden" name="stdntNm">
					<input type="hidden" name="clgNo">
					<input type="hidden" name="sknrgSttusMajor1">
					<input type="hidden" name="confmSe">
					<input type="hidden" name="page">
				</form>
				
			</div>
		</div>
	</div>
</div>



<!-- 모달 -->

<!-- 휴학 신청 상세보기 모달 -->
	<div class="modal fade" id="absenceModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	    <div class="modal-dialog modal-xl" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">휴학 신청</h5>
	              <button id="absenceModal-close" class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
					    <span aria-hidden="true">×</span>
				  </button>

	            </div>
	            <div class="absenceModal-body">
	            </div>
	        </div>
	    </div>
	</div>
<!-- 모달끝 -->

<!-- 복학 신청 상세보기 모달 -->
	<div class="modal fade" id="backToSchoolModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
	    <div class="modal-dialog modal-xl" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">복학 신청</h5>
	              <button id="backToSchoolModal-close" class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
					    <span aria-hidden="true">×</span>
				  </button>

	            </div>
	            <div class="backToSchoolModal-body">
	            </div>
	        </div>
	    </div>
	</div>
<!-- 모달끝 -->

<!-- 반려 사유 입력하는 모달 -->
	<div class="modal fade mt-100" id="refuseModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true" style="margin-top:90px;">
	    <div class="modal-dialog modal-xl" role="document" id="secondModal">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalLabel01">신청 반려</h5>
	              <button id="refuseModalClose" class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
					    <span aria-hidden="true">×</span>
				  </button>
	            </div>
	            <div>
	            	<div class="modal-body" style="padding:20px;">
	            		<div class="blue_box" style="margin-bottom:20px;">
	            			반려시 반려사유를 반드시 입력바랍니다.
	            		</div>
	            		<div class="sub_tit04"><span></span>반려 사유 입력</div>
						<table class="tmt-4 table_center" id="dataTable" width="100%">
							<thead>
								<tr>
									<td>
										<textarea rows="5" cols="50" id="reasonContent" style="resize:none; width:100%; border:1px solid #f2f2f2" required></textarea>
									</td>
								</tr>
							</thead>
							<tbody id="modalStudentBody"></tbody>
						</table>
						<div>
							<form id="refusedRequest" action="/staff/club/updateRefusedClubRequest" method="post">
								<input type="hidden" id="abssklReturn" name="abssklReturn">
								<input type="hidden" id="returnAbssklNo" name="abssklNo">
								<input type="hidden" id="returnSknrgNo" name="sknrgReturn">
								<input type="hidden" id="sknrgNo" name="sknrgNo">
								<input type="submit" value="저장" class="btn btn-primary ft_right" style=" margin-bottom: 15px;">
							</form>
						</div>
					</div>
	            </div>
	        </div>
	    </div>
	</div>
<!-- 모달끝 -->

<script src="/resources/js/app/msy/staffAbsenceRequest.js"></script>





















