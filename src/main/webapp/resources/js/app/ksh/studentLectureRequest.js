/**
 * <pre>
 * 수강신청 페이지에서 사용할 자바스크립트
 * 민감한 파일이므로 터치 자제 부탁드립니다.
 * </pre>
 * @author 김석호
 * @since 2023. 11. 17.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일       수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 17.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
// 수강신청 가능 목록 table, tbody
var $lectureListTable = $('#lectureListTable'); 
var $lectureListBody = $('#lectureListBody');
// 수강신청 목록 table, tbody
var $requestedLectureListTable = $('#requestedLectureListTable');
var $requestLectureListBody = $('#requestLectureListBody');
// 예비신청 목록 table, tbody
var $prepareLectureListTable = $('#prepareLectureListTable');
var $prepareLectureListBody = $('#prepareLectureListBody');
// 페이징 Area
var $lectureListPaginationArea = $('#lectureListPaginationArea');
// 학점 위치
var $requestablePointArea = $('#requestablePointArea');

// 웹소켓 선언 위치
var attedanceSocket;

function makePrepareLectureTrTag(lecture){
	var makeForPrepareLectureTrTag = `
		<tr class="lectureSearchList ${lecture.lctreNo}" data-lctre-no="${lecture.lctreNo}">
			<td>${lecture.courseNm}</td>
			<td>${lecture.complSe}</td>
			<td>${lecture.coursePnt}</td>
			<td>${lecture.prfsorNm}</td>
			<td>
				<span class="lectureCurrentRequestPersonCount">${lecture.currCnt}</span> / <span class="lectureLimitRequestPersonCount">${lecture.lctreNmpr}</span>
			</td>`
			
		if(lecture.tmtbSeNm){
			makeForPrepareLectureTrTag += `
			<td>${lecture.fcltsNm}</td>
			<td>${lecture.tmtbSeNm}</td>
			`
		}else{
			makeForPrepareLectureTrTag += `<td colspan="2">${lecture.fcltsNm}</td>`;
		}
		makeForPrepareLectureTrTag += `
			<td>
				<button class="blue_btn small_btn requestLectureBtn lectureRequestBtn" data-command="LECTUREREQUEST">수강신청</button>
				<button class="gray_btn small_btn prepareLectureCancelBtn lectureRequestBtn" data-command="PREPARECANCEL">예비취소</button>
			</td>
		</tr>
	`;
	return makeForPrepareLectureTrTag;
}
function makeLectureTrTag(lecture){
	var makeForLectureTrTag = `
		<tr class="lectureSearchList ${lecture.lctreNo}" data-lctre-no="${lecture.lctreNo}">
			<td>${lecture.courseNm}</td>
			<td>${lecture.complSe}</td>
			<td>${lecture.coursePnt}</td>
			<td>${lecture.prfsorNm}</td>
			<td>${lecture.subjctNm}</td>
			<td>
				<span class="lectureCurrentRequestPersonCount">${lecture.currCnt}</span> / <span class="lectureLimitRequestPersonCount">${lecture.lctreNmpr}</span>
			</td>`
			
		if(lecture.tmtbSeNm){
			makeForLectureTrTag += `
			<td>${lecture.fcltsNm}</td>
			<td>${lecture.tmtbSeNm}</td>
			`
		}else{
			makeForLectureTrTag += `<td colspan="2">${lecture.fcltsNm}</td>`;
		}
		makeForLectureTrTag += `
			<td>
				<button class="blue_btn small_btn requestLectureBtn lectureRequestBtn" data-command="LECTUREREQUEST">수강신청</button>
				<button class="gray_btn small_btn prepareLectureBtn lectureRequestBtn" data-command="LECTUREPREPARE">예비신청</button>
			</td>
		</tr>
	`;
	return makeForLectureTrTag;
}

function makeMyLectureTrTag(lecture){
	var makeForMyLectureTrTag = `
		<tr class="mylecturelist ${lecture.lctreNo}" data-lctre-no="${lecture.lctreNo}">
			<td>${lecture.courseNm}</td>
			<td>${lecture.complSe}</td>
			<td>${lecture.coursePnt}</td>
			<td>${lecture.prfsorNm}</td>`;
			if(lecture.tmtbSeNm){
				makeForMyLectureTrTag +=
				`
				<td>${lecture.fcltsNm}</td>
				<td>${lecture.tmtbSeNm}</td>
				`
			}else{
				makeForMyLectureTrTag += `<td colspan="2">${lecture.fcltsNm}</td>`;
			}
			
			makeForMyLectureTrTag += `
			<td>
				<button class="status02 myLectureCancelBtn lectureRequestBtn" data-command="LECTURECANCEL">수강취소</button>
			</td>
		</tr>
	`;
	return makeForMyLectureTrTag;
}
// =============== window onload function 위치 ===============
$(()=>{
	$('#clg').on('change',function(){
		let data = this.value;
		let $target = $('.clgSubject');
		$target.css('display','none');
		if(data){
			let $targets = $(`.clg${data}`);
			// console.log($target);
			$targets.css('display','inline-block');
		}else{
			$target.css('display','inline-block');
		}
		$('#subjctNo').val('');
	}).trigger('change');
	
	// 검색창 이벤트
	$('#searchUI').on('click','.lectureSearchBtn',function(){
		console.log("검색버튼 클릭이벤트 핸들러 첫줄");
		var formData = $('#searchUI').serializeJSON();
		console.log(formData);
		var sendData = {};
		sendData.lecture = formData;
		sendData.command = "SEARCH";
		sendData.stdntNo = currStdntNo;
		fSend(sendData);
	})
	// 검색 UI Change Event Handler
	$('.searchFormUIInput').on('change',function(){
		$('#currentPage').val('');
		$('.lectureSearchBtn').trigger('click');
	})
	
	$(document).on('click','.lectureRequestBtn',function(){
		var target = $(this).parents('tr');
		var command = $(this).data('command');
		var lctreNo = target.data('lctreNo');
//		console.log("커맨드 : ",command);
//		console.log("강의코드 : ",lctreNo);
		fSend(makeMessage(command,lctreNo));
	});
	
	
	//=======================================WEB SOCKET================================================
	// 소켓 연결
	attedanceSocket = new WebSocket(`ws://${SERVERSIDEADDRESS}/student/attendance`);
	attedanceSocket.onopen = fOpen;
	attedanceSocket.onmessage = fOnMessage;
	//=================================================================================================
	
	
	
})

// ------------------------------------------------------ onload 밖에서 선언할 것들
// =========== 웹소켓 온메시지 핸들러
function fOnMessage(event){
	let message = event.data;
	//console.log(message);
	let data = JSON.parse(message);
	console.log(data)
	// 초기화 커맨드 처리
	if(data.command == "INIT"){
		fInit(data);
	}
	// 검색 커맨드 처리
	if(data.command == "SEARCH"){
		fSearch(data);
	}
	if(data.command == "LECTURECANCEL"){
		fLectureCancel(data);
	}
	if(data.command == "LECTUREREQUEST"){
		fLectureRequest(data);
	}
	if(data.command == "LECTUREPREPARE"){
		fLecturePrepare(data);
	}
	if(data.command == "PREPARECANCEL"){
		fPrepareCancel(data);
	}
}
// 웹소켓 온오픈핸들러
function fOpen(){
	$('.lectureSearchBtn').trigger('click');
}

// 메시지 보내는 핸들러
function fSend(data){
	attedanceSocket.send(JSON.stringify(data));
}

// 메시지화 핸들러
function makeMessage(command,lctreNo){
	let data = {command : command, lctreNo : lctreNo,stdntNo : currStdntNo}
	return data;
}

function fClose(event){
	attedanceSocket.close();
}
function fError(event){
	console.log("웹소켓오류 : " , event);
}

// 초기화 커맨드 핸들러
function fInit(message){
	$requestablePointArea.text(message.pnt);
	var requestedLectureListCode = "";
	var prepareLectureListCode = "";
	var myLectureInitDataList = message.lectureList;
	var prepareLectureInitDataList = message.prepareList;
	if(myLectureInitDataList?.length > 0){
		$.each(myLectureInitDataList, function(i,v){
			requestedLectureListCode += makeMyLectureTrTag(v);
		})
	}else{
		requestedLectureListCode = `
			<tr class="noDataTrTag">
				<td colspan="7">수강신청 목록이 없습니다</td>
			</tr>
		`;
	}
	if(prepareLectureInitDataList?.length > 0){
		$.each(prepareLectureInitDataList,function(i,v){
			prepareLectureListCode += makePrepareLectureTrTag(v);
		});
	}else{
		prepareLectureListCode = `
			<tr class="noDataTrTag">
				<td colspan="8">예비신청 목록이 없습니다</td>
			</tr>
		`;
	}
	$requestLectureListBody.html(requestedLectureListCode);
	$prepareLectureListBody.html(prepareLectureListCode);
}
// 검색 커맨드 핸들러
function fSearch(message){
	var searchPaging = message.paging;
	var dataList = searchPaging.dataList;
	var searchResultLectureListCode = "";
	if(dataList?.length > 0){
		$.each(dataList,function(i,v){
			searchResultLectureListCode += makeLectureTrTag(v);
		})
	}else{
		searchResultLectureListCode = `
		<tr class="noDataTrTag">
				<td colspan="8">검색된 목록이 없습니다</td>
			</tr>
		`;
	}
	$lectureListBody.html(searchResultLectureListCode);
	$lectureListPaginationArea.html(searchPaging.pagingHTML);
}

// 수강취소 커맨드 핸들러
function fLectureCancel(message){
	console.log(message);
	var receiveMessage = message.message;
	if(receiveMessage){
		if(currStdntNo == message.stdntNo){
			// 노티스 js로 오류메시지 띄운다
			$.notify(receiveMessage,'error');
		}
	}else{
		// 모든사람에 대한 로직
		var $changeTextTargetCancelLogic = $('#lectureListBody').find(`tr.${message.lctreNo}`).find('.lectureCurrentRequestPersonCount');
		var beforeTextCancelLogic = $changeTextTargetCancelLogic.text();
		var parsedValueCancelLogic = parseInt(beforeTextCancelLogic);
		var afterTextCancelLogic = parsedValueCancelLogic-1;
		$changeTextTargetCancelLogic.text(afterTextCancelLogic);
		
		//본인일 경우 로직
		if(currStdntNo == message.stdntNo){
			// 노티스 js로 성공메시지 띄우고 로직처리한다.
			$.notify('수강 취소 성공','success');
			var $targetNode = $requestLectureListBody.find(`tr.${message.lctreNo}`);
			console.log($targetNode);
			$targetNode.remove();
			var $afterTrTags = $requestLectureListBody.find('tr');
			console.log($afterTrTags);
			if($afterTrTags.length == 0){
				$requestLectureListBody.append(`<tr class="noDataTrTag"><td colspan="7">수강신청 목록이 없습니다</td></tr>`);
			}
			var beforePntLectureCancelLogic = $requestablePointArea.text();
			var targetPntLectureCancelLogic = message.lecture.coursePnt;
			var afterPntLectureCancelLogic = parseInt(beforePntLectureCancelLogic) + parseInt(targetPntLectureCancelLogic);
			$requestablePointArea.text(afterPntLectureCancelLogic);
		}
	}
}

// 수강신청 커맨드 핸들러
function fLectureRequest(message){
	console.log(message);
	var receiveMessage = message.message;
	if(receiveMessage){
		if(currStdntNo == message.stdntNo){
			// 노티스 js로 오류메시지 띄운다
			$.notify(receiveMessage,'error');
		}
	}else{
		// 본인일 경우 로직
		if(currStdntNo == message.stdntNo){
			// 노티스 js로 성공메시지 띄우고 로직처리한다.
			$.notify('수강신청 성공','success');
			$requestLectureListBody.find('.noDataTrTag').remove();
			var lectureRequestLogicAppendCode = makeMyLectureTrTag(message.lecture);
			$requestLectureListBody.append(lectureRequestLogicAppendCode);
			
			var beforePntLectureRequestLogic = $requestablePointArea.text();
			var targetPntLectureRequestLogic = message.lecture.coursePnt;
			var afterPntLectureRequestLogic = parseInt(beforePntLectureRequestLogic) - parseInt(targetPntLectureRequestLogic);
			$requestablePointArea.text(afterPntLectureRequestLogic);
			
		}
		// 모든사람에 대한 로직
		var $changeTextTargetRequestLogic = $('#lectureListBody').find(`tr.${message.lctreNo}`).find('.lectureCurrentRequestPersonCount');
		var $changeTextTargetRequestLogicForPrepared = $('#prepareLectureListBody').find(`tr.${message.lctreNo}`).find('.lectureCurrentRequestPersonCount');
		var beforeTextRequestLogic = $changeTextTargetRequestLogic.text();
		var beforeTextRequestLogicForPrepared = $changeTextTargetRequestLogicForPrepared.text();
		var parsedValueRequestLogic = parseInt(beforeTextRequestLogic);
		var parsedValueRequestLogicForPrepared = parseInt(beforeTextRequestLogicForPrepared);
		var afterTextRequestLogic = parsedValueRequestLogic+1;
		var afterTextRequestLogicForPrepared = parsedValueRequestLogicForPrepared+1;
		$changeTextTargetRequestLogic.text(afterTextRequestLogic);
		$changeTextTargetRequestLogicForPrepared.text(afterTextRequestLogicForPrepared);
	}
}
// 예비신청 커맨드 핸들러
function fLecturePrepare(message){
	console.log(message);
	var receiveMessage = message.message;
	if(receiveMessage){
		if(currStdntNo == message.stdntNo){
			// 노티스 js로 오류메시지 띄운다
			$.notify(receiveMessage,'error');
		}
	}else{
		// 노티스 js로 성공메시지 띄우고 로직처리한다.
		$.notify('예비신청 성공','success');
		$prepareLectureListBody.find('.noDataTrTag').remove();
		var lecturePrepareLogicAppendCode = makePrepareLectureTrTag(message.lecture);
		$prepareLectureListBody.append(lecturePrepareLogicAppendCode);
	}
}
// 예비취소 커맨드 핸들러
function fPrepareCancel(message){
	console.log(message);
	var receiveMessage = message.message;
	if(receiveMessage){
		if(currStdntNo == message.stdntNo){
			// 노티스 js로 오류메시지 띄운다
			$.notify(receiveMessage,'error');
		}
	}else{
		// 노티스 js로 성공메시지 띄우고 로직처리한다.
		$.notify('예비신청 취소 성공','success');
		$prepareLectureListBody.find(`tr.${message.lctreNo}`).remove();
		var afterPrepareCancelTrTags = $prepareLectureListBody.find('tr');
		if(afterPrepareCancelTrTags.length == 0){
			$prepareLectureListBody.append(`<tr class="noDataTrTag"><td colspan="8">예비신청 목록이 없습니다</td></tr>`);
		}
	}
}



function fn_paging(page){
	$('#currentPage').val(page);
	$('.lectureSearchBtn').trigger('click');
	$('#currentPage').val('');
}


