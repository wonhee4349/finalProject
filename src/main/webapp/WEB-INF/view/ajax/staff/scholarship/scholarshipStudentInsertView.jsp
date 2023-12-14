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
.sub_tit05 {width: auto;
		    margin: 8px;
		    display: flex;
		    float: left;
		    margin-top: 15px;
    		margin-bottom: 22px;}
		    
		    
.s_option02{width: 210px;float: right;height: 44px;margin-right: 5px;display: flex;}
        .select-container02 {
            width: 250px;
            position: relative;
        }



        /* Style the select arrow */
        .select-arrow02 {
            position: relative;
            float: right;
            top: 7px;
            right: 19px;
        }


		    
</style>

 <div class="container-fluid">
 	
 		<div>
 			<div class="sub_tit04 sub_tit05"><span></span>학생 정보</div>
	 		<div id="modalSearchUI" class="sub_top_wrap">
	 			
	 			 			 
	 			<!-- search -->
	            <div class="d-none d-sm-inline-block  ml-md-3 my-2 my-md-0 navbar-search">            	 
	                <div class="input-group wd300 ft_right">
	                    <input type="text" name="searchWord" placeholder="학번 입력" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
	                    <div class="input-group-append">
	                        <button class="btn btn-primary" id="modalSearchBtn" type="button">
	                            <i class="fas fa-search fa-sm" ></i>
	                        </button>
	                    </div>
	                </div>
	            </div>
	 		</div>
 		</div>
            
		<form action="<c:url value='/staff/scholarshipStudent/ajax/scholarshipStudentInsertData'/>" id="modalSearchForm">

			<input type="hidden" name="searchWord">
		</form>
		
		<div>
			<table class="table table_style01 mt-4 table_center" id="dataTable" width="100%">
				<thead>
					<colgroup>
						<col width="15%">
						<col width="20%">
						<col width="15%">
						<col width="10%">
						<col width="15%">
						<col width="25%">
					</colgroup>
					<tr>
						<th>단과대</th>
						<th>학과</th>
						<th>학번</th>
						<th>학년</th>
						<th>이름</th>
						<th>휴대폰번호</th>
					</tr>
				</thead>
				<tbody id="modalStudentBody"></tbody>
			</table>
		</div>
		
		<div>
			<div class="sub_tit04 sub_tit05"><span></span>장학금</div>
			<div class="s_option02 ft_right">
				<div class="select-container02">				
				<div class="input-group wd300 ft_right" style="padding-top: 7px;">
	                <select id="scholarshipList" class="custom-select02" style="width:200px; text-align: left; padding-left: 15px;">
		              <option label="장학금" value="" />
		               <c:forEach items="${scholarshipList}" var="list">
		                  <option label="${list.scholarshipList.schoNm}" value="${list.schlshipNo}" />
		               </c:forEach>
		            </select>
		            <div class="select-arrow02">
						<i class="fa fa-caret-down"></i>
					</div>
	            </div>
				
					
				</div>
			</div>
		</div>
		<div>
			<table class="table table_style01 mt-4 table_center" id="dataTable" width="100%">
				<thead>
					<colgroup>
						<col width="30%">
						<col width="15%">
						<col width="15%">
						<col width="20%">
						<col width="20%">
					</colgroup>
					<tr>
						<th>장학금명</th>
						<th>지급연도</th>
						<th>지급학기</th>
						<th>지급액</th>
						<th>지급방식</th>
					</tr>
				</thead>
				<tbody id="modalScholarshipBody"></tbody>
			</table>
		</div>
		
		<form id="scholarshipStudentInsertForm" action="/staff/scholarshipStudent/insertScholarshipStudent" method="post">
			<input type="hidden" id="stdntNo" name="stdntNo">
			<input type="hidden" id="schlshipNo" name="schlshipNo">
			<input type="submit" value="저장" class="btn btn-primary ft_right">
		</form>

 	
 </div>

 <script src="/resources/js/app/msy/staffScholarshipStudentInsert.js"></script>
 
 
 
 
 
 
 
 
 
 
 