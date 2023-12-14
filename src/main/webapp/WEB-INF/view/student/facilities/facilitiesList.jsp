<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
    // 문서가 로드된 후 실행
    document.addEventListener("DOMContentLoaded", function () {
        // 모달 열기 버튼 클릭 시 모달 창 열기
        document.getElementById("openModal").addEventListener("click", function () {
            var modal = new bootstrap.Modal(document.getElementById("openModal"));
            modal.show();
        });

        // 모달 닫기 버튼 클릭 시 모달 창 닫기
        document.querySelector(".close").addEventListener("click", function () {
            var modal = bootstrap.Modal.getInstance(document.getElementById("openModal"));
            modal.hide();
        });

    });
    
    
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
        var selectElement = document.getElementById('tmtbSe');
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



    
	<!-- 모달 창 추가 -->
		<div class="modal fade" id="modal_open" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
		    <div class="modal-dialog modal-xl" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title" id="modalLabel01">시설물 예약하기</h5>
		              <button class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
						    <span aria-hidden="true">×</span>
					  </button>

		            </div>
		            <div class="modal-body">
		            	<div>
		            	<table>
		            		<tr>
		            		<input type="hidden" name="fcltsNo" />
		            		<th>대표자명</th>
		            		<td>
		            			<input type="text" class="form-style01"class="form-style01" name="fcltsReqstApplcnt"  required></input>
		            		</td>
		            		
		            		 <!-- 사용목적 입력 -->
			                <label for="fcltsPurps">사용목적</label>
			                <input type="text" name="fcltsPurps" class="form-style01"  required />
			
			                <!-- 수용 인원 입력 -->
			                <label for="fcltsNmpr">수용 인원</label>
			                <input type="text" name="fcltsNmpr" class="form-style01" placeholder="숫자만 입력하시오" oninput="validateNumberInput(this)" required />
			                <span id="errorMsg" style="color: red;"></span>
							<br>
							
							<tr>
								<th>희망날짜</th>
								<td><input type="date"  class="form-style01"  name="fcltsReqstDate"></td>
								<th>희망 시간</th>
								<td>
									  <select id="tmtbSe" class="form-style01" name="tmtbSe"></select>
                  
								</td>
							</tr>

	
		            	</table>
		            	
		            	</div>
		            </div>
		        </div>
		    </div>
		</div>
		<!-- 모달끝 --> 
		
		
<!-- Begin Page Content -->
<div class="container-fluid">

	<div class="card2">
		<div class="sub_tit02">시설물</div>
		<div class="sub_top_wrap">

			<!-- selectbox -->
			<div class="s_option">
				<div class="select-container">
					<select class="custom-select02">
						<option value="" label="전체" />
						<option value="clubNm" label="시설물명" />
						<option value="clubSe" label="시설물코드" />
					</select>
					<div class="select-arrow">
						<i class="fa fa-caret-down"></i>
					</div>
				</div>
			</div>

			<!-- search -->
			<div id="searchUI"
				class="d-none d-sm-inline-block  ml-md-3 my-2 my-md-0 navbar-search">
				<div class="input-group wd300 ft_right">
					<input type="text" name="searchWord"
						class="form-control bg-light border-0 small" aria-label="Search"
						aria-describedby="basic-addon2">
					<div class="input-group-append">
						<button class="btn btn-primary" id="searchBtn" type="button">
							<i class="fas fa-search fa-sm"></i>
						</button>
					</div>
				</div>
			</div>

		</div>
		<form action="<c:url value='/staff/facilities/ajax/facilitiesList'/>"
			id="searchForm">
			<input type="hidden" name="searchType"> <input type="hidden"
				name="searchWord"> <input type="hidden" name="page">
		</form>
		<div>
			<table class="table table_style01 mt-4 table_center" id="dataTable"
				width="100%" cellspacing="0">
				<thead>
				<colgroup>
					<col width="10%">
					<col width="25%">
					<col width="20%">
					<col width="20%">
					<col width="25%">
				</colgroup>
				<tr>
					<th>번호</th>
					<th>시설물명</th>
					<th>수용인원</th>
					<th>분류</th>
					<th>소속건물</th>
					
				</tr>
				</thead>
				<tbody id="listBody"></tbody>

			</table>



			<div aria-label="Page navigation example nav_center">
				<span id="pagingArea"></span>
			</div>

		</div>
	</div>



</div>


<script src="/resources/js/app/kwh/studentFacilitiesData.js"></script>