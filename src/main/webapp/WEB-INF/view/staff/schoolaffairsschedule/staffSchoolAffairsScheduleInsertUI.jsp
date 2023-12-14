<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.wd500{width:500px;}
.text_left{font-size: 17px;}
</style>
<div class="container-fluid">
    <div class="card2">
        <div class="sub_tit02">학사 일정 등록</div>
        <div class="sub_top_wrap">
        </div>
        <security:authentication property="principal" var="principal"/>
        <div>
        	<div class="blue_box" style="margin-bottom:20px; height:178px;"><p class="text_left" >본 학사일정표는 가장 먼저 다가오는 학사 일정 부터 보여집니다.
                  아직 기간이 많이 남았지만 중요한 일정이라면 등록하시고<br> 꼭 학생,교수,교직원들에게 따로 공지를 해서 일정을 확인 할 수 있게 해주시면 감사하겠습니다<br><br>※ 본 일정은 학교의 사정에 따라 변동될 수 있습니다. </p></div>
            <form id="schoolaffairsscheduleForm" action="/staff/schoolaffairsschedule/staffSchoolAffairsScheduleData" method="post"> <!-- Add action and method -->
                
              
				<table class="table_style01 mt-4">
					<tr>
						<th>종류</th>
						<td>
							<select id="ScscduSe" name="scduSe" class="form-style01 wd500" required>
								<option value label="선택">
									<c:forEach var="scdu" items="${scdu}">
										<option value="${scdu.comCode}">${scdu.comCodeNm}</option>
									</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>일정 시작일</th>
						<td> <input type="date" id="ScscduSeb" name="schafsBeginDate" class="form-style01 datepicker wd500"  required/></td>
					</tr>
					<tr>
						<th>일정 종료일</th>
						<td><input type="date" id="ScscduSee" name="schafsEndDate" class="form-style01 datepicker wd500"  required/></td>
					</tr>
				
				</table>

				<button type="button" id="inputDataSche" class="btn btn-success ft_left">일괄입력</button>
                <input type="submit" class="btn btn-primary ft_right mt-4" value="등록하기"/>
			
			<%-- <label for="scduSe">종류</label>
                <select id="ScscduSe" name="scduSe" class="form-style01" required>
                	<option value label="선택">
                    <c:forEach var="scdu" items="${scdu}">
                        <option value="${scdu.comCode}">${scdu.comCodeNm}</option>
                    </c:forEach>
                </select>                        
				

                <!-- 학기 입력 -->
                <input type="hidden" name="semstrSe" class="form-style01" placeholder="년도4자리+학기번호1자리"/>

                <!-- 날짜 선택 -->
                <label for="schafsBeginDate">일정 시작일</label>
                <input type="date" id="ScscduSeb" name="schafsBeginDate" class="form-style01 datepicker"  required/>

				<!-- 날짜 선택 -->
                <label for="schafsEndDate">일정 종료일</label>
                <input type="date" id="ScscduSee" name="schafsEndDate" class="form-style01 datepicker"  required/>
       

                <!-- 나머지 입력란들은 유사하게 추가 -->
				<button type="button" id="inputDataSche" class="btn btn-primary ft_right"  style="margin-top:20px;">일괄입력</button>
                <input type="submit" class="btn btn-primary ft_right mt-4" value="등록하기"/>--%> 
            </form>
            
            
            
        </div>
    </div>
</div>

<script src="/resources/js/app/ljh/staffSchoolAffairsScheduleInsertData.js"></script>

