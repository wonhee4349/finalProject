<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="${pageContext.request.contextPath }/resources/css/wh_style.css" rel="stylesheet">

<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>

<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script>

function displayCertificateInfo() {
    // 선택한 값 가져오기
    var selectedCertificate = document.querySelector('input[name="agree"]:checked').value;

    // 발급부수 선택
    var numberOfCopiesElement = document.querySelector('#count_select');
    var numberOfCopies = numberOfCopiesElement.value;

    // 발급용도 선택
    var purposeElement = document.querySelector('#use_select');
    var purpose = purposeElement.value;

    // 체크 테이블 선택
    var checkTable = document.querySelector('#check_table');

    // 선택한 정보를 테이블에 추가
    var newRow = checkTable.insertRow(-1); // 테이블의 끝에 새로운 행 추가

    // 행에 셀 추가
    var cell1 = newRow.insertCell(0);
    var cell2 = newRow.insertCell(1);
    var cell3 = newRow.insertCell(2);
    var cell4 = newRow.insertCell(3);
    var cell5 = newRow.insertCell(4);
    var cell6 = newRow.insertCell(5);

    // 셀에 값 할당 - 금액
    var pricePerCopy = 1500; // 각 부수당 가격
    var totalPrice = numberOfCopies * pricePerCopy; // 전역 totalPrice에 할당
    cell5.innerHTML = totalPrice + "원";

    // 셀에 값 할당
    cell1.innerHTML = selectedCertificate;
    cell2.innerHTML = selectedCertificate;
    cell3.innerHTML = purpose;
    cell4.innerHTML = numberOfCopies;

    // 추가된 행에 삭제 버튼 추가
    var deleteButton = document.createElement('button');
    deleteButton.className = 'btn btn-danger';
    deleteButton.innerHTML = '삭제';
    deleteButton.onclick = function () {
       deleteRow(this);
    };
    cell6.appendChild(deleteButton);

    // 선택 안내 메시지 숨기기
    var guideMessage = document.querySelector('#check_table tr td[colspan="6"]');
    if (guideMessage) {
       guideMessage.parentNode.removeChild(guideMessage);
    }


    function deleteRow(button) {
       var row = button.parentNode.parentNode; // 삭제 버튼이 속한 행을 가져옴
       row.parentNode.removeChild(row); // 행을 삭제함

       // 삭제 후 테이블이 비었다면 다시 안내 메시지 추가
       if (checkTable.rows.length === 1) {
          addGuideMessage();
       }
    }

    // 삭제된 행이 없을 때 처음 안내 메시지 추가
    if (checkTable.rows.length === 1) {
       addGuideMessage();
    }

    function addGuideMessage() {
       var guideRow = checkTable.insertRow(-1);
       var guideCell = guideRow.insertCell(0);
       guideCell.colSpan = 5;
       guideCell.innerHTML = '증명서를 선택하세요.';
    }
 }

function requestPay() {
    try {
        // 선택된 증명서 정보를 가져오기
        var selectedCertificate = document.querySelector('input[name="agree"]:checked');
        if (!selectedCertificate) {
            alert('증명서를 선택해주세요.');
            return;
        }
        var selectedCertificateValue = selectedCertificate.value;

        var numberOfCopiesElement = document.querySelector('#count_select');
        var numberOfCopies = numberOfCopiesElement.value;

        var purposeElement = document.querySelector('#use_select');
        var purpose = purposeElement.value;

        // 라디오 버튼 확인 로직
        var kakaoRadio = document.getElementById('kakaopay');
        if (!kakaoRadio.checked) {
            alert('결제 방법을 선택해주세요.');
            return;
        }

        // 여기에서 totalPrice를 가져와야 합니다.
        var totalPriceElement = document.querySelector('#check_table tr:last-child td:nth-child(5)');
        var totalPrice = parseInt(totalPriceElement.innerText); // 금액을 정수형으로 변환

        var selectedPaymentMethod = '카카오페이';
        var selectPg = 'kakaopay.TC0ONETIME';

        // 결제 요청
        IMP.init('imp65138661');
        IMP.request_pay({
            pg: selectPg,
            merchant_uid: selectedCertificateValue, // 선택한 증명서에 맞게 merchant_uid 설정
            name: '증명서출력',
            pay_method: "card",
            amount: totalPrice
        }, function (rsp) {
            if (rsp.success) {
                alert("결제 완료!");
                // 여기에서 추가적인 로직을 구현할 수 있습니다.
            } else {
                alert(rsp.error_msg);
            }
        });
    } catch (error) {
        console.error('Error during payment request:', error);
    }
}


</script>
<!-- Begin Page Content -->
<div class="container-fluid">
	<div class="card2 ht800">
		<div class="sub_tit02">증명서발급</div>


		<div class="certificate_select_wrap">
			<div class="cert_box">
				<div class="cert_tit">증명서 선택</div>
				<div class="blue_box02">

					<label for="agree1" class="radio_box"> <input type="radio"
						id="agree1" name="agree" value="재학증명서" checked="checked" /> <span
						class="on"></span> 재학증명서
					</label> <label for="agree2" class="radio_box"> <input type="radio"
						id="agree2" name="agree" value="졸업증명서" /> <span class="on"></span>
						졸업증명서
					</label>
				</div>
			</div>
			<br>

			<table class="line_table">
				<tr>
					<th>발급부수</th>
					<td>
						<div class="s_option02">
							<div class="select-container">
								<select id="count_select" class="custom-select02">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
								</select>
								<div class="select-arrow">
									<i class="fa fa-caret-down"></i>
								</div>
							</div>
						</div>

					</td>
				</tr>
				<tr>
					<th>용도선택</th>
					<td>
						<div class="s_option02">
							<div class="select-container">
								<select id="use_select" class="custom-select02">
									<option value="취업 제출용">취업 제출용</option>
									<option value="자격증 발급용">자격증 발급용</option>
									<option value="신원 확인용">신원 확인용</option>
									<option value="기타 증명서류 제출용">기타 증명서류 제출용</option>
								</select>
								<div class="select-arrow">
									<i class="fa fa-caret-down"></i>
								</div>
							</div>
						</div>
					</td>
				</tr>

			</table>
			<button class="btn btn-primary ft_right mb50" onclick="displayCertificateInfo()">확인</button>

			<br>
			<div class="cert_tit mt-50" id="certificateHistoryTable">증명서 신청내역</div>
			<table id="check_table" class="line_table">
				<colgroup>
					<col width="14%">
					<col width="14%">
					<col width="14%">
					<col width="14%">
					<col width="14%">
					<col width="14%">
				</colgroup>
				<tr>
					<th>발급구분</th>
					<th>선택증명서</th>
					<th>발급용도</th>
					<th>매수</th>
					<th>금액</th>
					<th>삭제</th>
				</tr>
				<tr>
				   <td colspan="6">증명서를 선택하세요.</td>
				</tr>
			</table>
			<button id="payment" class="btn btn-primary ft_right mb50"  onclick="requestPay()">결제하기</button>

		</div>
		 <div class="Target_article__Xhj_7_paymentMethod"><span class="Target_name__8LHwW_payment"><strong class="Target_inner__BofoY Target_ellipsis__TQ_8f">결제 수단 </strong>선택하기</span></div>
                     <div class="bankSelect">
                        <div class="payment_radio"><input type="radio" id="creditcard" name="payment" value="creditcard"/><label id="paymentcheck" for="creditcard">신용카드</label></div>
                        <div class="payment_radio"><input type="radio" id="kakaopay" name="payment" value="kakaopay"/><label id="paymentcheck" for="kakaopay">카카오페이</label></div>
                        <div class="payment_radio"><input type="radio" id="tosspay" name="payment" value="tosspay"/><label id="paymentcheck" for="tosspay">토스</label></div>
                        <div class="payment_radio"><input type="radio" id="mutongjang" name="payment" value="mutongjang"/><label id="paymentcheck" for="mutongjang">무통장 입금</label></div>
                     </div>
        </div>	
			
			

		<div class="certificate_wrap">
			
				<div class="sub_tit04">재학증명서</div>
				<div>
				<table class="table_style05 table_wd70 table_center">
					<colgroup>
						<col width="40%">
						<col width="50%">
					</colgroup>
					<tr>
						<th>이름</th>
						<td name></td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td birth></td>
					</tr>
					<tr>
						<th>학번</th>
						<td hakbun></td>
					</tr>
					<tr>
						<th>학생명</th>
						<td hakname></td>
					</tr>
					<tr>
						<th>전공</th>
						<td major></td>
					</tr>

				</table>
				
				<div class="text01">위의 사실을 증명합니다.</div>
				<div class="logo_wrap02">
                            <div class="logoimg">
                                <img src="/resources/img/logo.png">
                            </div>
                            <div class="logotxt">
                                UMSEOK<br>
                                <span>UNIVERSITY</span>
                            </div>
                        </div>
				</div>
				
		</div>
		<button pdfdown>확인</button>
	</div>
</div>
<!-- <script src="/resources/js/app/kwh/studentPayment.js"></script>  -->
<script src="/resources/js/app/kwh/studentCertificateDown.js"></script>