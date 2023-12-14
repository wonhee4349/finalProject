<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- Begin Page Content -->
<style>
.black{color:#000;}
.score_list{position: relative;top: 10px; min-height: 100px; width:254px; margin:0 auto;  border: 2px solid #dee4f7; padding:0;}
.optiontit{color:#000;width: 100%;height: 38px;background: #e8edfb;text-align: center;padding: 10px 5px;}
option {border-bottom:1px solid #f2f2f2;height: 32px;line-height: 34px;vertical-align: text-bottom;padding-top: 7px; text-align:center;}
.left_tit{text-align: left; color: #4e73df;float: left; display: block;width: 100px;}
.pd20{padding:20px;}
.total_wrap{width: 100%;margin-top: 36px; padding: 0px 20px; margin: 0 auto; position:absolute; top:10px;}
.total{width: 20%;margin: 20px auto;height: 20px;float: right;margin-right: 41px;}
.left_tit{text-align:right; color:#4e73df; display:block; float:left;}
.total_wrap .score{    width: 60px; float: right; text-align: right;}
.score_table{    width: 95%;margin: 0 auto;position:absolute;  top:70px; }
.left_table{min-height:383px;}
.table_style01{border-top:2px solid #6582e0 !important;}
</style>
<div class="container-fluid">
<c:set value="${dataMap }" var="info"/>
	<div class="card2">
		<div class="sub_tit02">전체성적확인</div>
		<div class="sub_top_wrap"></div>
		<div>
			<table class="table table_style01 mt-4 table_center hover_none" id="informationAboutScoreTable" width="100%" cellspacing="0">
				<tr>
					<td class="text-primary">전체 이수 학점</td>
					<td class="black">${info.allCredit }</td>
					<td></td>
					<td class="text-primary">총 평균 학점</td>
					<td class="black">${totalScore }</td>
				</tr>
				<tr>
					<th>취득학점</th>
					<th>전공필수</th>
					<th>전공선택</th>
					<th>교양필수</th>
					<th>교양선택</th>
				</tr>
				<tr>
					<td>${info.acceptedCredit }</td>
					<td>${info.requiredMajorCredit }</td>
					<td>${info.notRequiredMajorCredit }</td>
					<td>${info.requiredCultureCredit }</td>
					<td>${info.notRequiredCultureCredit }</td>
				</tr>
				<tr>
					<th>1전공</th>
					<th>2전공</th>
					<th>부전공</th>
					<th>비대면영상</th>
					<th>비대면화상</th>
				</tr>
				<tr>
					<td>${info.major1Credit }</td>
					<td>${info.major2Credit }</td>
					<td>${info.minorCredit }</td>
					<td>${info.onlineLectureCredit }</td>
					<td>${info.untactLectureCredit }</td>
				</tr>
			</table>
			<form id="semCdForm">
				<table class="table table_style02 mt-4 table_center hover_none pd-20"
					id="totalSemesterScoreTable" width="100%" cellspacing="0">
					<colgroup>
						<col width="25%">
						<col width="65%">
					</colgroup>
					<tr>
						<th>학기선택</th>
						<th>상세정보</th>
					</tr>
					<tr>
						<td class="pd20"><c:set value="${fn:length(dataList)}" var="dataSize" />
							<c:if test="${dataSize > 0 }">
								<select name="semCd" id="semCd" class="form-control score_list" size="${dataSize +1}">
									<option class="optiontit" value label="학기 선택" />
									<c:forEach items="${dataList }" var="map">
										<option value="${map.SEM_CD }" label="${map.SEM_NM }" />
									</c:forEach>
								</select>
							</c:if>
							<c:if test="${dataSize == 0 }">
								<select name="semCd" id="semCd" size="1">
									<option value="" label="수강 학기 정보 없음" />
								</select>
							</c:if> 
							
						</td>
						<td style="position:relative">
							<div class="left_table">
								<div class="total_wrap">
									<div class="total"><span class="left_tit">선택학기 학점</span> <div  class="score"><span id="calScoreArea"></span></div></div>
									<div class="total"><span class="left_tit">학과석차</span> <div  class="score"><span id="subjectRankArea"></span>/<span id="subjectPeopleArea"></span></div></div>
									<div class="total"><span class="left_tit">학년석차</span> <div  class="score"><span id="subjectGradeRankArea"></span>/<span id="subjectGradePeopleArea"></span></div></div>
								</div>
								<table id="seletedSemesterScoreInformationTable" class="score_table" width="100%" cellspacing="0">
									<colgroup>
										<col width="20%">
										<col width="15%">
										<col width="25%">
										<col width="10%">
										<col width="20%">
									</colgroup>
									<tr>
										<th>강의코드</th>
										<th>이수학점</th>
										<th>강의명</th>
										<th>성적</th>
										<th>수강구분</th>
									</tr>
									<tbody id="scoreListBody" >
										<tr>
											<td colspan="5">학기를 선택해주세요</td>
										</tr>
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</form>


		</div>
	</div>
</div>

<!-- /.container-fluid -->
<script src="/resources/js/app/ksh/studentScoreList.js"></script>