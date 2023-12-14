<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<style>
.sub_tit04{font-size: 18px;color: #304b73;padding-left:0px;font-weight: 600;width: 100%;float:left; text-align:left; margin-bottom:5px;}   
.sub_tit04 span{
	width: 8px;
    height: 8px;
    background: #304b73;
    display: block;
    float: left;
    margin-right: 8px;
    position: relative;
    top: 9px;}
.profile_img{width:30%; float:left; height:256px;}   
.profile_img img {width:100%; height:100%;} 
.table_wd70 {width:70% !important; float:left; }
.tab_contents2 {height: 500px; max-height: 500px; overflow: scroll;}
.tab-content {max-height: 550px; overflow: auto;}
</style>

<div class="homework_wrap mb-4">
	<div class="my_profile_wrap02">
		<div>
			<div class="sub_tit04"><span></span>학적 정보</div>     
			<div class="profile_img"><img src="${pageContext.request.contextPath }/staff/profileImage/${graduationInfo.stdntNo}"></div>         						
			<table class="table_style02 table_wd70 table_center ft_left">
				<colgroup>
					<col width="40%">
					<col width="50%">
				</colgroup>
				<tr>
					<th>학번</th><td>${graduationInfo.stdntNo}</td>
				</tr>
				<tr>
					<th>학생명</th><td>${graduationInfo.stdntNm}</td>
				</tr>
				<tr>
					<th>단과대</th><td>${graduationRegister.subject.college.clgNm}</td>
				</tr>
				<tr>
					<th>전공</th><td>${graduationRegister.sknrgSttusMajor1}</td>
				</tr>
				<tr>
					<th>2전공</th><td>${graduationRegister.sknrgSttusMajor2}</td>
				</tr>
				<tr>
					<th>부전공</th><td>${graduationRegister.sknrgSttusMinor}</td>
				</tr>
			</table>
		</div>
	</div>
	<br />
	
	<div>
		<div class="class_list_wrap">
			<div class="list_top">
				<button class="gray_btn small_btn active" data-tab="tab-1" style="margin-top: 45px;">인적 정보</button>
				<button class="gray_btn small_btn" data-tab="tab-2">장학금 정보</button>
				<button class="gray_btn small_btn" data-tab="tab-3">등록금 정보</button>
				<button class="gray_btn small_btn" data-tab="tab-4">동아리 정보</button>
				<button class="gray_btn small_btn" data-tab="tab-5">성적 정보</button>
			</div>
		</div>
		
		<div class="tab_contents2">
		
			<div id="tab-1" class="tab-content current">
  				<div class="pro_table">
  					<div class="sub_tit04"><span></span>인적 정보</div>                     
	   				<table class="table_style02 table_center">
	   					<colgroup>
	   						<col width="30%">
	   						<col width="70%">
	   					</colgroup>
						<tr><th>생년월일</th><td>${graduationInfo.stdntIhidnum1}</td></tr>
	   					<tr><th>기본주소</th><td>${graduationInfo.stdntAdres1}</td></tr>
	   					<tr><th>상세주소</th><td>${graduationInfo.stdntAdres2}</td></tr>
	   					<tr><th>연락처</th><td>${graduationInfo.stdntTelno}</td></tr>
	   					<tr><th>국적</th><td>${graduationInfo.nltySe}</td></tr>
	   					<tr><th>성별</th><td>${graduationInfo.sexdstnSe}</td></tr>
	   					<tr><th>입학일</th><td>${graduationInfo.sknrgSttusEntsch}</td></tr>
	   					<tr><th>졸업일</th><td>${graduationInfo.sknrgSttusGrdtn}</td></tr>
	   					<tr><th>입학구분</th><td>${graduationInfo.sknrgSttusEnterenceSe}</td></tr>
	   					<tr><th>은행명</th><td>${graduationInfo.bankSe}</td></tr>
	   					<tr><th>계좌번호</th><td>${graduationInfo.stdntAcnutno}</td></tr>	   				
	   				</table>
	   			</div>
  			</div>
  			
			<div id="tab-2" class="tab-content">
  				<div class="pro_table">
  					<div class="sub_tit04"><span></span>장학금 정보</div>                     
	   				<table class="table_style02 table_center">
	   					<colgroup>
	   						<col width="10%">
							<col width="25%">
							<col width="15%">
							<col width="10%">
							<col width="20%">
							<col width="20%">
	   					</colgroup>
						<tr>
							<th>번호</th>
							<th>장학금명</th>
							<th>연도</th>
							<th>학기</th>
							<th>금액</th>
							<th>지급방식</th>
						</tr>
						<c:if test="${empty graduationScholarship.scholarship}">
							<tr>
								<td colspan="6">장학 목록 없음</td>
							</tr>
						</c:if>
						<c:if test="${not empty graduationScholarship.scholarship}">
							<c:forEach items="${graduationScholarship.scholarship}" var="scholarship">
								<tr>
									<td>${scholarship.rnum}</td>
									<td>${scholarship.scholarshipList.schoNm}</td>
									<td>${scholarship.semstrYear}년</td>
									<td>${scholarship.semstrNo}학기</td>
									<td style="text-align: right;">${scholarship.schlshipAmountStr}</td>
									<td>${scholarship.pymntSe}</td>
								</tr>
							</c:forEach>
						</c:if>
	   				</table>
  				</div>
  			</div>
  			
  			<div id="tab-3" class="tab-content">
  				<div class="pro_table">
  					<div class="sub_tit04"><span></span>등록금 정보</div>                     
	   				<table class="table_style02 table_center">
	   					<colgroup>
	   						<col width="5%">
							<col width="15%">
							<col width="5">
							<col width="15%">
							<col width="15%">
							<col width="15%">
							<col width="15%">
							<col width="15%">
	   					</colgroup>
						<tr>
							<th>번호</th>
							<th>연도</th>
							<th>학기</th>
							<th>입학금</th>
							<th>수업료</th>
							<th>장학금</th>
							<th>납부액</th>
							<th>납부일</th>
						</tr>
	   					<c:if test="${empty graduationTuition.tuition}">
							<tr>
								<td colspan="8">등록금 목록 없음</td>
							</tr>
						</c:if>
						<c:if test="${not empty graduationTuition.tuition}">
							<c:forEach items="${graduationTuition.tuition}" var="tuition">
								<tr>
									<td>${tuition.rnum}</td>
									<td>${tuition.semester.semstrYr}년</td>
									<td>${tuition.semester.semstr}학기</td>
									<td style="text-align: right;">${tuition.tutnEtrncfStr}</td>
									<td style="text-align: right;">${tuition.tutnTutfeeStr}</td>
									<td style="text-align: right;">${tuition.tutnSchlshipStr}</td>
									<td style="text-align: right;">${tuition.realPayStr}</td>
									<td>${tuition.tutnPayde}</td>
								</tr>
							</c:forEach>
						</c:if>  				
	   				</table>
  				</div>
  			</div>
  			
  			<div id="tab-4" class="tab-content">
  				<div class="pro_table">
  					<div class="sub_tit04"><span></span>동아리</div>                     
	   				<table class="table_style02 table_center">
	   					<colgroup>
	   						<col width="10%">
							<col width="20%">
							<col width="20%">
							<col width="30%">
							<col width="20%">
	   					</colgroup>
						<tr>
							<th>번호</th>
							<th>구분</th>
							<th>동아리명</th>
							<th>동아리실</th>
							<th>직책</th>
						</tr>
	   					<c:if test="${empty graduationClub.club}">
							<tr>
								<td colspan="5">동아리 목록 없음</td>
							</tr>
						</c:if>
						<c:if test="${not empty graduationClub.club}">
							<c:forEach items="${graduationClub.club}" var="club">
								<tr>
									<td>${club.rnum}</td>
									<td>${club.clubSe}</td>
									<td>${club.clubNm}</td>
									<td>${club.facilities.building.buldNm}(${club.facilities.fcltsNm})</td>
									<td>${club.student.stdntPosition}</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</div>
			
			<div id="tab-5" class="tab-content">
				<div class="pro_table">
					<div class="sub_tit04"><span></span>강의 및 성적</div>                     
					<table class="table_style02 table_center">
						<colgroup>
							<col width="5%">
							<col width="15%">
							<col width="10%">
							<col width="25%">
							<col width="10%">
							<col width="15%">
							<col width="10%">
							<col width="10%">
						</colgroup>
						<tr>
							<th>번호</th>
							<th>연도</th>
							<th>학기</th>
							<th>강의명</th>
							<th>이수구분</th>
							<th>이수학점</th>
							<th>교수</th>
							<th>학점</th>
						</tr>
						<c:if test="${empty lectureList}">
							<tr>
								<td colspan="8">성적 정보 없음</td>
							</tr>
						</c:if>
						<c:if test="${not empty lectureList}">
							<c:forEach items="${lectureList}" var="list">
								<tr>
									<td>${list.rnum}</td>
									<td>${list.semstrYr}년</td>
									<td>${list.semstrSe}학기</td>
									<td>${list.courseNm}</td>
									<td>${list.complSeNm}</td>
									<td>${list.coursePnt}</td>
									<td>${list.prfsorNm}</td>
									<td>${list.printScore}</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</div>
		</div>
	</div>
 </div>