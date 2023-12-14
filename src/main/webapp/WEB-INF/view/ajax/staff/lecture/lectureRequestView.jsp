<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
.select-arrow02 {position: relative; float: right; top: 7px; right: 30px;}
.custom-select02{width:180px; margin-left: 10px;}
.sy_input{width:220px; margin:0 auto;}
.btn{margin-top: 15px}
</style>

<div class="homework_wrap mb-4">
     <div>
		<div class="pro_table">
			<div class="sub_tit04"><span></span>신청 강의</div>                     
			<div>
				<table class="table_style02 table_left table_center hover_none">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>강 의 명</th><td>${lectureRequestInfo.course.courseNm}</td></tr>		
					<tr><th>학기구분</th><td>${lectureRequestInfo.semstrSeNm}</td></tr>
					<tr><th>이수구분</th><td>${lectureRequestInfo.complSeNm}</td></tr>		
				</table>
			</div>
			<div>
				<table class="table_style02 table_right table_center">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>교 수</th><td>${lectureRequestInfo.professor.prfsorNm}</td></tr>
					<tr><th>신청일자</th><td>${lectureRequestInfo.lctreReqstDate}</td></tr>				
					<tr><th>승인 구분</th><td>${lectureRequestInfo.confmSeNm}</td></tr>				
				</table>
			</div><br>
		
			
			<c:if test="${lectureRequestInfo.confmSeNm eq '반려'}">
					<div class="sub_tit04"><span></span>반려 사유</div> 
					<div>
						<table class="table_style02 table_center">
							<colgroup>
								<col width="100%">
							</colgroup>
							<tr>
								<th>반려 사유</th>
							</tr>
							<tr>
								<td>${lectureRequestInfo.lctreReqstReturn}</td>
							</tr>					
						</table>
					</div>					
			</c:if>
						
			<c:if test="${lectureRequestInfo.confmSeNm eq '승인 대기'}">
				<br>
				<div>		
					<button class="btn btn-danger ft_right" id="refuseBtn" data-toggle="modal" data-target="lectureRefuseModal">반려</button>
					<button class="btn btn-primary ft_right" id="applyBtn" data-toggle="modal" data-target="lectureRefuseModal" style="margin-right:10px; ">승인</button>
					<input type="hidden" id="lctreReqstNo" value="${lectureRequestInfo.lctreReqstNo }"/>
					<%--
					<form id="appliedLectureRequest" action="/staff/lecture/updateAppliedLectureRequest" method="post">
						<input type="hidden" id="lctreReqstNo" name="lctreReqstNo" value="${lectureRequestInfo.lctreReqstNo}">
						<input type="hidden" id="lctreNmpr" name="lctreNmpr" value="30">						
						<input type="hidden" id="lctreSe0" name="lctreSe" value="">						
						<input type="hidden" id="complSe" name="complSe" value="${lectureRequestInfo.complSe}">								
						<input type="hidden" id="fcltsNo" name="fcltsNo" value="${lectureRequestInfo.facilities.fcltsNo}">								
						<input type="hidden" id="prfsorNo" name="prfsorNo" value="${lectureRequestInfo.professor.prfsorNo}">								
						<input type="hidden" id="semstrSe" name="semstrSe" value="${lectureRequestInfo.semstrSe}">								
						<input type="hidden" id="complSe" name="complSe" value="${lectureRequestInfo.complSe}">								
						<input type="hidden" id="couresNo" name="couresNo" value="${lectureRequestInfo.course.courseNo}">								
						<input type="submit" value="승인" class="btn btn-primary ft_right" id="applyBtn" style="margin-right:10px; ">
					</form>
					--%>
				
				</div>								
			</c:if>									
		</div>
  		<br />
	</div>
 </div>