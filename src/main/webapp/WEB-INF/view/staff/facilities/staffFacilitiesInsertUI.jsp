<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.wd500{width:500px;}
</style>
<div class="container-fluid">
    <div class="card2">
        <div class="sub_tit02">시설물 등록</div>
        <div class="sub_top_wrap">
        </div>
        <security:authentication property="principal" var="principal" />
        <div id="all">
            <form id="facilitiesForm" action="/staff/facilities/staffFacilitiesData" method="post">
             <input type="hidden" name="fcltsNo" class="form-style01" placeholder="자동 생성 됩니다" readonly />
			  <table class="table_style01">
			   
			    <tr>
			        <th>시설물명</th>
			        <td>
			            <input type="text" name="fcltsNm" id="fcltNm" class="form-style01 wd500" placeholder="시설물명 EX)OOO호" required />
			        </td>
			    </tr>
			    <tr>
			        <th>사용목적</th>
			        <td>
			            <input type="text" name="fcltsPurps" id="fcltPurps" class="form-style01 wd500" maxlength="6" required />
			        </td>
			    </tr>
			    <tr>
			        <th>수용 인원</th>
			        <td>
			            <input type="text" name="fcltsNmpr" id="fcltNmpr" class="form-style01 wd500 ft_left" placeholder="숫자만 입력하시오" oninput="validateNumberInput(this)" required />
			            <span id="errorMsg" style="color: red; display:block; float:left; line-height:36px; margin-left:10px; font-size:16px; "></span>
			        </td>
			    </tr>
			    <tr>
			        <th>건물 선택</th>
			        <td>
			            <select id="fcltbuldNo" name="buldNo" class="form-style01 wd500" required>
			                <option value label="선택">
			                    <c:forEach var="building" items="${building}">
			                        <option value="${building.buldNo}">${building.college.clgNm} ${building.buldNm}</option>
			                    </c:forEach>
			                </option>
			            </select>
			        </td>
			    </tr>
			</table>
			            	
            
            
              <%--  <!-- 시설물 코드 자동 생성 -->
                <input type="hidden" name="fcltsNo" class="form-style01" placeholder="자동 생성 됩니다" readonly />

                <!-- 이름 입력 -->
                <label for="fcltsNm">시설물명</label>
                <input type="text" name="fcltsNm" id="fcltNm" class="form-style01" placeholder="시설물명 EX)OOO호" required />

                <!-- 사용목적 입력 -->
                <label for="fcltsPurps">사용목적</label>
                <input type="text" name="fcltsPurps" id="fcltPurps" class="form-style01" maxlength="6" required />

                <!-- 수용 인원 입력 -->
                <label for="fcltsNmpr">수용 인원</label>
                <input type="text" name="fcltsNmpr" id="fcltNmpr" class="form-style01" placeholder="숫자만 입력하시오" oninput="validateNumberInput(this)" required />
                <span id="errorMsg" style="color: red;"></span>
				<br>
                <!-- 건물 선택 -->
                <label for="buldNo">건물 선택</label>
                <select id="fcltbuldNo" name="buldNo" class="form-style01" required>
                    <option value label="선택">
                        <c:forEach var="building" items="${building}">
                            <option value="${building.buldNo}">${building.college.clgNm} ${building.buldNm}</option>
                        </c:forEach>
                    </option>
                </select>--%>

                <!-- 나머지 입력란들은 유사하게 추가 -->
				<button type="button" id="inputDataFaci" class="btn btn-success ft_left"  style="margin-top:20px;">일괄입력</button>
                <input type="submit" class="btn btn-primary ft_right mt-4" value="등록하기" />
            </form>
        </div>
    </div>
</div>

<script src="/resources/js/app/ljh/staffFacilitiesInsertData.js"></script>
<script>
    function validateNumberInput(input) {
        const value = input.value;
        if (!/^\d+$/.test(value)) {
            document.getElementById("errorMsg").innerText = "숫자만 입력하세요";
        } else {
            document.getElementById("errorMsg").innerText = "";
        }
    }
</script>
