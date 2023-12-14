/**
 * <pre>
 * 학생 금학기성적 조회 페이지에서 사용하는 자바스크립트
 * studentCurrScore.jsp 에서 사용
 * </pre>
 * @author 김석호
 * @since 2023. 11. 22.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일            수정자       수정내용
 * --------          --------    ----------------------
 * 2023. 11. 22.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
var currenSemesterCodeForScoreAjax = $('#currenSemesterPrintArea').data('currSemCd');
// 이의제기 신청 모달 내부
var $lectureNamePlaceForScoreObjectionModal = $('#lectureNamePlaceForScoreObjectionModal');
var $scoreObjectionModalFormLctreNo = $('#scoreObjectionModalFormLctreNo');
var $printScorePlaceForScoreObjectionModal = $('#printScorePlaceForScoreObjectionModal');
var $scoreObjectionModalFormScoreObjcCn = $('#scoreObjectionModalFormScoreObjcCn');
// 이의제기 정보 모달 내부
var $lectureNamePlaceForScoreObjectionInfoModal = $('#lectureNamePlaceForScoreObjectionInfoModal');
var $printScorePlaceForScoreObjectionInfoModal = $('#printScorePlaceForScoreObjectionInfoModal');
var $scoreObjectionInfoModalReasonArea = $('#scoreObjectionInfoModalReasonArea');
var $scoreObjectionInfoModalCommentArea = $('#scoreObjectionInfoModalCommentArea');
var $statusPlaceForScoreObjectionInfoModal = $('#statusPlaceForScoreObjectionInfoModal');
// 모달
var $scoreObjectionModal = $('#scoreObjectionModal');
var $scoreObjectionInfoModal = $('#scoreObjectionInfoModal');
$('.close').on('click',function(){
    $('#scoreObjectionModal').modal('hide');
    $('#scoreObjectionInfoModal').modal('hide');
});
$scoreObjectionModal.on('hidden.bs.modal',function(){
	$('#scoreObjectionModalForm')[0].reset();
	$lectureNamePlaceForScoreObjectionModal.empty();
	$printScorePlaceForScoreObjectionModal.empty();
});
$scoreObjectionInfoModal.on('hidden.bs.modal',function(){
	$lectureNamePlaceForScoreObjectionInfoModal.empty();
	$printScorePlaceForScoreObjectionInfoModal.empty();
	$scoreObjectionInfoModalCommentArea.empty();
});


document.addEventListener("DOMContentLoaded", function(){
	$(document).on('click','.scoreObjectionBtn',objectionBtnClickEvenHandler);
	checkForLectureEvaulation();
});

function checkForLectureEvaulation(){
	var currentSemesterLectureEvaulationCheckAjaxSetting = {
		url : `/student/ajax/checkLectureEvaulation/${currenSemesterCodeForScoreAjax}`
		, method : "get"
		, dataType : "json"
		, success : function(resp){
			if(!resp.result){
				Swal.fire({
					icon: 'info',
					title: "강의평가 미완료!",
					html: "미완료된 강의평가가 있습니다!",
					showConfirmButton: false,
					timer: 1500
				});
				setTimeout(function() {
					// 처리
					location.href = "/student/courseEvaluation";
				}, 1500);
				return;
			}
			getCurrenSemesterScoreList();
		}
		, error : function(xhr, status, err){
			console.log(err);
		}
	};
	$.ajax(currentSemesterLectureEvaulationCheckAjaxSetting);
}

function getCurrenSemesterScoreList(){
	console.log(currenSemesterCodeForScoreAjax);
	var currentSemesterScoreInitAjaxSettings = {
		url : `/student/ajax/scoreList/${currenSemesterCodeForScoreAjax}`
		, method : "get"
		, dataType : "json"
		, success : function(res){
//			console.log("success function 첫줄")
//			console.log(res);
			var currentSemesterScoreCode = "";
			if(res.dataList?.length > 0 && res.calScore >= 0){
				$.each(res.dataList,function(i,v){
					currentSemesterScoreCode += makeCurrentSemesterScoreTrTags(v);
				})
				if(res.calScore < 0){
					$('#assertScoreArea').text("성적 미입력");
				}else{
					$('#assertScoreArea').text("예상학점 : "+res.calScore);
				}
			}else{
				currentSemesterScoreCode += `
					<tr>
						<td colspan="6">금학기 성적 정보없음</td>
					</tr>
				`;
			}
//			console.log(res.calScore);
			$('#scoreListBody').html(currentSemesterScoreCode);
		}
		, error : function(xhr, status, err){
			console.log(err);
		}
	};
	$.ajax(currentSemesterScoreInitAjaxSettings);
}

function makeCurrentSemesterScoreTrTags(score){
	var currentSemesterScoreTrTagCode = `
		<tr data-lctre-no="${score.lctreNo}">
			<td>${score.lctreNo}</td>
			<td>${score.lecture.course.courseNm}</td>
			<td>${score.printScore}</td>
			<td>${score.lecture.complSe}</td>
			<td>${score.lecture.course.coursePnt}</td>
	`;
	var scoreObjectionStatus = `${score.scoreObjection.confmSe}`;
	if(scoreObjectionStatus == "null"){
		currentSemesterScoreTrTagCode += `<td><button class="btn-primary small_btn scoreObjectionBtn" data-command="OBJECTION">이의제기</button></td>`; 
	}
	if(scoreObjectionStatus == "01"){
		currentSemesterScoreTrTagCode += `<td><button class="btn-secondary small_btn scoreObjectionBtn" data-command="CHECK">신청완료</button></td>`; 
	}
	if(scoreObjectionStatus == "02"){
		currentSemesterScoreTrTagCode += `<td><button class="btn-success small_btn scoreObjectionBtn" data-command="CONFIRM">이의승인</button></td>`; 
	}
	if(scoreObjectionStatus == "03"){
		currentSemesterScoreTrTagCode += `<td><button class="btn-danger small_btn scoreObjectionBtn" data-command="REJECT">이의반려</button></td>`; 
	}
		
	currentSemesterScoreTrTagCode += `</tr>`;
	
	return currentSemesterScoreTrTagCode;
}

function objectionBtnClickEvenHandler(){
//	console.log("버튼이벤트 핸들러 첫줄 this : ",this);
	var $this = $(this);
	var $thisParentTr = $this.parents('tr');
	var targetLectureName = $thisParentTr.find('td').eq(1).text();
	var objectionBtnCommand = $this.data('command');
	var objectionTargetLctreNo = $thisParentTr.data('lctreNo');
	var targetLecturePrintScore = $thisParentTr.find('td').eq(2).text();
	if(objectionBtnCommand == "OBJECTION"){
		scoreObjectionModalOpen(objectionTargetLctreNo,targetLectureName,targetLecturePrintScore);
	}else{
		getObjectionInformation(objectionTargetLctreNo,targetLectureName,targetLecturePrintScore);
	}
	
//	console.log(targetLectureName);
//	console.log(objectionBtnCommand);
//	console.log(targetLecturePrintScore);
}

function scoreObjectionModalOpen(lctreNo,lectureName,printScore){
	$scoreObjectionModalFormLctreNo.val(lctreNo);
	$lectureNamePlaceForScoreObjectionModal.html(lectureName);
	$printScorePlaceForScoreObjectionModal.html(printScore);
	$scoreObjectionModal.modal('show');
}

function getObjectionInformation(lctreNo,lectureName,printScore){
	$lectureNamePlaceForScoreObjectionInfoModal.html(lectureName);
	$printScorePlaceForScoreObjectionInfoModal.html(printScore);
	var getObjectionInformationAjaxSettings = {
		url : `/student/scoreObjection/${lctreNo}`
		, method : "get"
		, dataType : "json"
		,success : function(resp){
//			console.log(resp);
			var objectionInfo = resp.objectionInfo;
			if(objectionInfo.scoreObjcReturn != "null"){
				$scoreObjectionInfoModalCommentArea.text(objectionInfo.scoreObjcReturn)
			}
			$statusPlaceForScoreObjectionInfoModal.text(objectionInfo.confmSe);
			$scoreObjectionInfoModalReasonArea.text(objectionInfo.scoreObjcCn);
			$scoreObjectionInfoModal.modal('show');
		}
		, error : function(xhr, status, err){
//			console.log(err);
		}
	};
	$.ajax(getObjectionInformationAjaxSettings);
}
$('#scoreObjectionModalForm').on('submit',function(e){
	e.preventDefault();
//	console.log("여기 this : ",this);
	var scoreObjectionData = $(this).serializeJSON();
//	console.log(scoreObjectionData);
	var scoreObjectionModalFormAjaxSettings = {
		url : "/student/scoreObjection"
		, method : "post"
		, dataType : "json"
		, data : scoreObjectionData
		,success : function(resp){
//			console.log(resp.result);
			if (resp.result == "OK") {
				Swal.fire({
					title: "신청 성공!",
					icon: "success",
					showConfirmButton: false,
					timer: 1300
				});
				setTimeout(function() {
					location.reload();
				}, 1300);
			} else {
				Swal.fire({
					title: "잘못된 요청!!",
					icon: "error",
					showConfirmButton: false,
					timer: 1300
				});
				setTimeout(function() {
					location.reload();
				}, 1300);
			}
			
		}
		, error : function(xhr, status, err){
			console.log(err);
		}
	};
	$.ajax(scoreObjectionModalFormAjaxSettings);
});
$('#autoFillBtn').on('click',function(){
	$scoreObjectionModalFormScoreObjcCn.val('교수님.. 저 정말 열심히 했는데 한번만 더 검토 부탁드립니다.');
});