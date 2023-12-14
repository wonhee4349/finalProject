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
</style>

<div class="homework_wrap mb-4">
     <div class=" mt-4 mb-4 table_center">
		<div class="pro_table">
			<div class="sub_tit04"><span></span>동아리 정보</div>                     
			<div>
				<table class="table_style02 table_left table_center">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>동아리명</th><td>${clubInfo.clubNm}</td></tr>
					<tr><th>구분</th><td>${clubInfo.clubSe}</td></tr>		
				</table>
			</div>
			<div>
				<table class="table_style02 table_right table_center">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr><th>동아리실</th><td>${clubInfo.facilities.building.buldNm}(${clubInfo.facilities.fcltsNm})</td></tr>
					<tr><th>회장</th><td>${clubInfo.student.stdntNm}</td></tr>				
				</table>
			</div>
			<div>
				<table class="table_style02 table_center">
					<colgroup>
						<col width="100%">
					</colgroup>
					<tr>
						<th>동아리 소개</th>
					</tr>
					<tr>
						<td>${clubInfo.clubIntrcn}</td>
					</tr>			
				</table>
			</div>
		</div>
  		<br />
		<div class="pro_table">
			<div class="sub_tit04"><span></span>회원 정보</div>  
<%-- 			<p>${clubMember[0].member }</p>  --%>
			<table class="table_style02 table_center">
				<colgroup>
					<col width="10%">
					<col width="25%">
					<col width="20%">
					<col width="25%">
					<col width="20%">
				</colgroup>
				<tr>
					<th>번호</th>
					<th>학번</th>
					<th>학생명</th>
					<th>전공</th>
					<th>직책</th>
				</tr> 
				<c:if test="${empty clubMember}">
					<tr>
						<td colspan="4">동아리 회원 없음</td>
					</tr>
				</c:if>
				<c:if test="${not empty clubMember}">
					<c:forEach items="${clubMember}" var="clubVO">
						<c:forEach items="${clubVO.member }" var="studentVO">
							<tr>
								<td>${studentVO.rnum}</td>
								<td>${studentVO.stdntNo}</td>
								<td>${studentVO.stdntNm}</td>
								<td>${studentVO.sknrgSttusMajor1}</td>
								<td>${studentVO.stdntPosition}</td>											
							</tr>			
						</c:forEach>
					</c:forEach>
				</c:if>
			</table>
		</div>
		<br />
	</div>
 </div>