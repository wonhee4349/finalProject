/**
 * <pre>
 * 
 * </pre>
 * @author 김원희
 * @since 2023. 11. 16.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 27      김원희      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

const pdfBtn = document.querySelector("[pdfdown]");
let jsPDF = jspdf.jsPDF;

const makePdf = () => {
    // 캔버스를 이미지로 변환
    html2canvas($('.certificate_wrap')[0]).then(function (canvas) {
        var imgData = canvas.toDataURL('image/png');
        var imgWidth = 190;
        var pageHeight = 260;
        var imgHeight = parseInt(canvas.height * imgWidth / canvas.width);
        var heightLeft = imgHeight;
        var margin = 10;

        var doc = new jsPDF('p', 'mm', 'a4');
        var position = 30;

        doc.addImage(imgData, 'PNG', margin, position, imgWidth, imgHeight);
        heightLeft -= pageHeight;

        while (heightLeft >= 0) {
            position = heightLeft - imgHeight;
            doc.addPage();
            doc.addImage(imgData, 'PNG', margin, position, imgWidth, imgHeight);
            heightLeft -= pageHeight;
        }

        doc.save('재학증명서.pdf');

        // PDF 생성 및 다운로드 이후 certificate_wrap을 완전히 감춥니다.
        $('.certificate_wrap').hide();
    });
}

pdfBtn.addEventListener("click", makePdf);

function refreshScreen() {
    // PDF 다운로드 이후에 화면을 갱신하는 코드 작성
    // PDF를 숨기고, 기타 화면 조작을 수행할 수 있습니다.
    $('.certificate_wrap').hide();
    $('.down_box').show();  // PDF 다운로드 버튼이 있는 상자를 보여줍니다.
    // 화면을 갱신하는 기타 코드들을 추가할 수 있습니다.
}


// 학생 정보를 가져오는 함수
function getStudentInfo() {
    $.ajax({
        type: 'GET',
        url: '/student/certificate/ajax/certificateDown',
        success: function(resp) {
            // 성공적으로 학생 정보를 가져왔을 때 처리
            console.log("체크:", resp);
            displayStudentInfo(resp);
        },
        error: function(error) {
            // 에러 발생 시 처리
            console.error('Error fetching student information:', error);
        }
    });
}

// 학생 정보를 화면에 표시하는 함수
function displayStudentInfo(studentInfo) {
    console.log("확인:", studentInfo);
    // 여기서 studentInfo를 사용하여 화면에 정보를 표시하는 코드 작성
    // 예를 들어, jQuery를 사용하여 각 요소에 값을 설정하는 등의 작업을 수행
    $('[name]').text(studentInfo.STDNT_NM);
    $('[birth]').text(studentInfo.STDNT_IHIDNUM1);
    $('[major]').text(studentInfo.SUBJCT_NM);
    $('[hakbun]').text(studentInfo.STDNT_NO);
    $('[hakname]').text(studentInfo.STDNT_NM);
    $('[major]').text(studentInfo.SUBJCT_NM);
}

getStudentInfo();
