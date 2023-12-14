var selectedCertificateValue = generateUUID(); // 이 부분을 수정해서 주문 번호를 생성하는 함수를 호출하도록 변경

function generateUUID() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random() * 16 | 0,
            v = c === 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}



function generateOrderKey() {
    var timestamp = new Date().getTime(); // 현재 시간을 밀리초로 얻음
    var orderKey = "usu" + timestamp; // 주문번호에 시간 정보 추가
    return orderKey;
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
        var orderKey = generateOrderKey();
        // 결제 요청
        IMP.init('imp65138661');
        IMP.request_pay({
            pg: selectPg,
            merchant_uid: selectedCertificateValue, // 선택한 증명서에 맞게 merchant_uid 설정
            name: '증명서출력',
            pay_method: "card",
            merchant_uid: orderKey,
            amount: totalPrice
        }, function (rsp) {
            if (rsp.success) {
                alert("결제 완료!");
                showDownloadBox();
            } else {
                alert(rsp.error_msg);
            }
        });
    } catch (error) {
        console.error('Error during payment request:', error);
    }
}

// 다운로드 박스 표시 함수
function showDownloadBox() {
    var downloadBox = document.querySelector('.down_box');
    if (downloadBox) {
        downloadBox.style.display = 'block';
    }
}
