/**
 * <pre>
 * 학생 시험페이지에서 사용되는 자바스크립트
 * </pre>
 * @author 김석호
 * @since 2023. 11. 28.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일            수정자       수정내용
 * --------          --------    ----------------------
 * 2023. 11. 28.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 

document.addEventListener("DOMContentLoaded", ()=> {
	renderRemainTime();
	window.addEventListener('beforeunload', function(event) {
		testSubmitFunction();
	});
	window.setInterval(function(){
		remainTimeToTest = remainTimeToTest-1;
		renderRemainTime();
		if(remainTimeToTest == 0){
			testSubmitFunction();
		}
		if(remainTimeToTest == 300){
			notifyRemainFiveMinute();
		}
	},1000);
	
	$(document).on('change','input[type=radio]',function(){
		var targetQuestionNo = $(this).attr('name');
//		console.log('일단 이거',targetQuestionNo);
		var $targetBtn = $(`#showBtn${targetQuestionNo}`);
		if($targetBtn.hasClass('blue_btn')){
			$targetBtn.removeClass('blue_btn');
			$targetBtn.addClass('gray_btn');
		}
	});
	
	$(document).on('mouseleave',function(){
		sweethoneyAlert();
	})
	
	$('#submitBtn').on('click',function(){
		Swal.fire({
			title: "제출하시겠습니까?",
			text: "답변 작성 여부와 상관없이 답안지가 제출처리됩니다",
			icon: "warning",
			showCancelButton: true,
			confirmButtonColor: "#3085d6",
			cancelButtonColor: "#d33",
			confirmButtonText: "제출"
		}).then((result) => {
			if(result.isConfirmed){
				testSubmitFunction();
			}
		});
	});
	
	$('#autoFillBtn').on('click',function(){
		var $targetRadios = $('#testAnswerForm input[type=radio]').filter('[value=3]');
		$targetRadios.prop('checked',true).trigger('change');
	});
	
});

function notifyRemainFiveMinute(){
	var thisNotifyMessage = '5분남았습니다. 시간이 되면 자동제출됩니다.';
	$('#remainTimeViewArea').notify(thisNotifyMessage,'warn');
	$('#checkBoxShowDiv').notify(thisNotifyMessage,'warn');
}

function renderRemainTime(){
//	console.log(remainTimeToTest);
	var reaminTimeMinute = parseInt(remainTimeToTest /60);
	var reaminTimeSecond = (remainTimeToTest % 60);
	var remainTimeTextValue = ``;
	if(reaminTimeMinute == 0){
		remainTimeTextValue = `${reaminTimeSecond}초`;
	}else{
		remainTimeTextValue = `${reaminTimeMinute}분 ${reaminTimeSecond}초`;
	}
	$('#remainTimeViewArea').html(remainTimeTextValue);
}


