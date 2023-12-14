<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<style>
.ht250{height:260px !important}
.list_style01{list-style:none; padding:0; margin:bottom:10px;}
.list_style01 li{width:45%; float:left; display:block; margin-bottom: 12px;height: 74px;list-style}
.list_style01 li:last-child{float:right;}
input, select{width:240px !important; margin:0 auto;}
table.hover_none tr:hover{background:none !important;}
table th{line-height:30px;}
</style>


<!-- 공통 코드를 JSP 페이지에 전달하는 부분 -->
<script>
    // 공통 코드를 JavaScript 객체로 정의
   var consultationTimes = [
    { code: 'A1', label: '월요일 1교시 (09:00~10:00)' },
    { code: 'A2', label: '월요일 2교시 (10:00~11:00)' },
    { code: 'A3', label: '월요일 3교시 (11:00~12:00)' },
    { code: 'A4', label: '월요일 4교시 (12:00~13:00)' },
    { code: 'A5', label: '월요일 5교시 (13:00~14:00)' },
    { code: 'A6', label: '월요일 6교시 (14:00~15:00)' },
    { code: 'A7', label: '월요일 7교시 (15:00~16:00)' },
    { code: 'A8', label: '월요일 8교시 (16:00~17:00)' },
    { code: 'A9', label: '월요일 9교시 (17:00~18:00)' },
    { code: 'B1', label: '화요일 1교시 (09:00~10:00)' },
    { code: 'B2', label: '화요일 2교시 (10:00~11:00)' },
    { code: 'B3', label: '화요일 3교시 (11:00~12:00)' },
    { code: 'B4', label: '화요일 4교시 (12:00~13:00)' },
    { code: 'B5', label: '화요일 5교시 (13:00~14:00)' },
    { code: 'B6', label: '화요일 6교시 (14:00~15:00)' },
    { code: 'B7', label: '화요일 7교시 (15:00~16:00)' },
    { code: 'B8', label: '화요일 8교시 (16:00~17:00)' },
    { code: 'B9', label: '화요일 9교시 (17:00~18:00)' },
    { code: 'C1', label: '수요일 1교시 (09:00~10:00)' },
    { code: 'C2', label: '수요일 2교시 (10:00~11:00)' },
    { code: 'C3', label: '수요일 3교시 (11:00~12:00)' },
    { code: 'C4', label: '수요일 4교시 (12:00~13:00)' },
    { code: 'C5', label: '수요일 5교시 (13:00~14:00)' },
    { code: 'C6', label: '수요일 6교시 (14:00~15:00)' },
    { code: 'C7', label: '수요일 7교시 (15:00~16:00)' },
    { code: 'C8', label: '수요일 8교시 (16:00~17:00)' },
    { code: 'C9', label: '수요일 9교시 (17:00~18:00)' },
    { code: 'D1', label: '목요일 1교시 (09:00~10:00)' },
    { code: 'D2', label: '목요일 2교시 (10:00~11:00)' },
    { code: 'D3', label: '목요일 3교시 (11:00~12:00)' },
    { code: 'D4', label: '목요일 4교시 (12:00~13:00)' },
    { code: 'D5', label: '목요일 5교시 (13:00~14:00)' },
    { code: 'D6', label: '목요일 6교시 (14:00~15:00)' },
    { code: 'D7', label: '목요일 7교시 (15:00~16:00)' },
    { code: 'D8', label: '목요일 8교시 (16:00~17:00)' },
    { code: 'D9', label: '목요일 9교시 (17:00~18:00)' },
    { code: 'E1', label: '금요일 1교시 (09:00~10:00)' },
    { code: 'E2', label: '금요일 2교시 (10:00~11:00)' },
    { code: 'E3', label: '금요일 3교시 (10:00~11:00)' },
    { code: 'E4', label: '금요일 4교시 (12:00~13:00)' },
    { code: 'E5', label: '금요일 5교시 (13:00~14:00)' },
    { code: 'E6', label: '금요일 6교시 (14:00~15:00)' },
    { code: 'E7', label: '금요일 7교시 (15:00~16:00)' },
    { code: 'E8', label: '금요일 8교시 (16:00~17:00)' },
    { code: 'E9', label: '금요일 9교시 (17:00~18:00)' },
    { code: 'F1', label: '토요일 1교시 (09:00~10:00)' },
    { code: 'F2', label: '토요일 2교시 (10:00~11:00)' },
    { code: 'F3', label: '토요일 3교시 (11:00~12:00)' },
    { code: 'F4', label: '토요일 4교시 (12:00~13:00)' },
    { code: 'F5', label: '토요일 5교시 (13:00~14:00)' },
    { code: 'F6', label: '토요일 6교시 (14:00~15:00)' },
    { code: 'F7', label: '토요일 7교시 (15:00~16:00)' },
    { code: 'F8', label: '토요일 8교시 (16:00~17:00)' },
    { code: 'F9', label: '토요일 9교시 (17:00~18:00)' }
]; 

   function populateConsultationTimes(selectedDate) {
       var selectElement = document.getElementById('cnsltRequstTime');
       selectElement.innerHTML = ""; // 기존 옵션 제거

       // 선택한 날짜에 해당하는 시간만 필터링
       var filteredTimes = consultationTimes.filter(function (time) {
           return time.code.startsWith(selectedDate);
       });

       for (var i = 0; i < filteredTimes.length; i++) {
           var option = document.createElement('option');
           option.value = filteredTimes[i].code;
           option.text = filteredTimes[i].label;
           selectElement.add(option);
       }
   }

   // 페이지 로드 시 공통 코드를 선택 요소에 추가
   window.onload = function () {
       populateConsultationTimes(""); // 초기에는 선택한 날짜가 없으므로 빈 문자열 전달
   };
</script>

<!-- Begin Page Content -->
<div class="container-fluid">

	<div class="card2">
		  <div class="sub_tit02">상담신청</div>
        <form id="consultationForm">
        <security:authentication property="principal" var="principal"/>
            <!-- 기존 양식 필드 -->
            <input type="hidden" value="${principal.username }" name="stdntNo" class="form-style01" id="stdntNo" readonly />
            <input type="hidden" value="01" name="cnsltRequstProcess" id="cnsltRequstProcess" />
          
            <table class="table table_style01 mt-4 table_center hover_none">
            	<colgroup>
            		<col width="20%">
            		<col width="30%">
            		<col width="20%">
            		<col width="30%">
            	</colgroup>
            	<tr>
            		<th colspan="1">상담유형</th>
            		<td colspan="3">
	                    <select id="cnsltSe" class="form-style01 ft_left" name="cnsltSe">
	                        <option value="01">학사 상담</option>
	                        <option value="02">취업 상담</option>
	                    </select>
                    </td>    		                       		
            	</tr>  
            	
            	<tr>
                    <th>희망 상담일</th>
                    <td class="text_left">
                        <!-- 희망 상담일을 위한 입력 필드 -->
                        <input  type="date" id="cnsltRequstDate" class="form-style01 ft_left" name="cnsltRequstDate">
                    </td>
                    <th>상담시간</th>
                    <td>
                        <!-- 상담 시간을 위한 선택 필드 -->
                        <select id="cnsltRequstTime" class="form-style01 ft_left" name="cnsltRequstTime"></select>
                    </td>
                </tr>
                      
            </table>
             

            <!-- 상담 유형을 위한 새로운 필드 추가 -->
            <div class="clear"></div> 
            <!-- 상담내용 -->
            <label for="cnsltRequstCn" style="font-size:18px;">상담내용</label>
            <textarea name="cnsltRequstCn" class="form-style01 ht250 scroll-y" id="cnsltRequstCn" required ></textarea>

            <!-- 제출 버튼 -->
            <button type="button" id="inputDataOpen" class="btn btn-success ft_left mt-2">일괄입력</button>
            <input style="width:100px !important;"  type="submit" class="btn btn-primary mt-2 ft_right" type="button" id="cnsltInsert" value="상담신청">
        </form>
	</div>

</div>
<!-- /.container-fluid -->
<script src="/resources/js/app/kwh/studentConsultation.js"></script>
