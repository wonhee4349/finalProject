/**
 * <pre>
 * 학생 테스트페이지에서 상단에 로드될 자바스크립트
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

var flagToDenyExam = false;
var isDoneTest = false;


function blockToMouseRightClick(){
	sweethoneyAlert();
	return false;
}
function blockToMouseDrag(){
	return false;
}
function blockToKeyDown(){
	sweethoneyAlert();
	return false;
}
function blockCopyImageDown(){
	console.log('이미지복사방지 이벤트');
}
function testSubmitFunction(){
	console.log('제출처리함수');
	if(isDoneTest){
		return;
	}
	isDoneTest = true;
	var submitData = $('#testAnswerForm').serializeJSON();
	console.log(submitData);
	var testSubmitAjaxSettings = {
		url : location.pathname
		, method : "post"
		, data : submitData
		, dataType : 'json'
		, success : function(resp){
//			alert('성공. 테스트용 쓰레드붙잡는 알림창');
		}
		, error : function(xhr, status, err){
			console.log(err);
//			alert('실패. 테스트용 쓰레드붙잡는 알림창');
		}
		,complete : function(xhr,status){
			window.close();
		}
	};
	$.ajax(testSubmitAjaxSettings);
}

function sweethoneyAlert(){
	if(!flagToDenyExam){
		Swal.fire({
			title: '부정행위감지!',
			text: '부정행위로 간주될 행위가 감지되었습니다. 재시도 시 불이익을 받을 수 있습니다.',
			icon: 'error',
			confirmButtonText: '확인',
			background : '#fff'
		});
		flagToDenyExam = true;
	}else{
		testSubmitFunction();
	}
}

document.body.oncontextmenu = blockToMouseRightClick;
document.body.onselectstart = blockToMouseDrag;
document.body.ondragstart = blockCopyImageDown;
document.body.onkeydown = blockToKeyDown;
// document.addEventListener('keypress',)