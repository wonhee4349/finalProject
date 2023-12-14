/**
 * <pre>
 * 학생 등록금 페이지에서 사용하는 자바스크립트
 * tuitionList.jsp 페이지
 * </pre>
 * @author 김석호
 * @since 2023. 11. 24.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일            수정자       수정내용
 * --------          --------    ----------------------
 * 2023. 11. 24.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 

var $printTuitionBtnDiv = $('#printTuitionBtnDiv');
var $printTuitionBtn = $('#printTuitionBtn');
var $selectOptionSemCd = $('#selectOptionSemCd')
var isAbleToPrint = false;

var printSemesterCodeForTuition;

var currentUrlValueForPrintPdfTuitionPage;
$(()=>{
	$('#selectCurrSemCdBtn').on('click',function(){
		getTuitionPage('curr');
		$('#selectOptionSemCd').val('');
	});
	
	$selectOptionSemCd.on('change',function(){
		if(this.value){
			getTuitionPage(this.value);
		}else{
			currentUrlValueForPrintPdfTuitionPage = null;
			document.querySelector('#ifrTuitionBody').srcdoc = '';
			isAbleToPrint = false;
			printSemesterCodeForTuition = null;
			$printTuitionBtnDiv.css('display','none');
		}
	});
	$printTuitionBtn.on('click',function(){
		if(!isAbleToPrint){
			return;
		}
		var targetSemCdForPrint = $selectOptionSemCd.val();
		console.log("밸류 : ", targetSemCdForPrint)
		if(targetSemCdForPrint == ""){
			printSemesterCodeForTuition = tuitionCurrentSemesterForTuitionPage;
		}else{
			printSemesterCodeForTuition = `${targetSemCdForPrint.substr(0,4)}년도_${targetSemCdForPrint.substr(-1,1)}학기`;
		}
		savePDF($("#ifrTuitionBody").contents().find("#content")[0]);
	});
})



function getTuitionPage(semCd){
//	document.querySelector('#ifrTuitionBody').src = `/student/tuition/${semCd}`;
	console.log(semCd);
	currentUrlValueForPrintPdfTuitionPage = `/student/tuition/${semCd}`; 
	var getTuitionAjaxSettings = {
		url : currentUrlValueForPrintPdfTuitionPage
		, method : "get"
		, success : function(resp){
			document.querySelector('#ifrTuitionBody').srcdoc = resp;
			isAbleToPrint = true;
			$printTuitionBtnDiv.css('display','inline-block');
		}
		, error : function(xhr, status, err){
			console.log(err);
		}
	};
	$.ajax(getTuitionAjaxSettings);
}

function savePDF(node) {
	var pdfsaveSettings = {
		//logging : true,		// 디버그 목적 로그
		//proxy: "html2canvasproxy.php",
		allowTaint: false,	// cross-origin allow 
		useCORS: true,		// CORS 사용한 서버로부터 이미지 로드할 것인지 여부
		scale: 2			// 기본 96dpi에서 해상도를 두 배로 증가

	};
	html2canvas(node
//	, pdfsaveSettings
	).then(function(canvas) {
		// 캔버스를 이미지로 변환
		var imgData = canvas.toDataURL('image/png');

		var imgWidth = 190; // 이미지 가로 길이(mm) / A4 기준 210mm

		var imgHeight = canvas.height * imgWidth / canvas.width;

		var margin = 10; // 출력 페이지 여백설정
		var doc = new jsPDF('p', 'mm');
		var position = 0;

		// 첫 페이지 출력
		doc.addImage(imgData, 'PNG', margin, position, imgWidth, imgHeight);

		// 파일 저장
		var savePdfFileNameForTuition = null;
		if(tuitionCurrentSemesterForTuitionPage == printSemesterCodeForTuition){
			savePdfFileNameForTuition = `${printSemesterCodeForTuition}_${studentNameForTuitionPage}학생_등록금_고지서.pdf`
		}else{
			savePdfFileNameForTuition = `${printSemesterCodeForTuition}_${studentNameForTuitionPage}학생_등록금_납부내역.pdf`
		}
		doc.save(savePdfFileNameForTuition);
	});
}