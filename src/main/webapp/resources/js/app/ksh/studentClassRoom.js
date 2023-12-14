/**
 * <pre>
 * 학생 ClassRoom 페이지에서 사용하는 자바스크립트
 * </pre>
 * @author 김석호
 * @since 2023. 11. 27.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일       수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 27.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

var tempTargetLctreNo;
var tempTargetCrNo;
var tempFileData;

var sec9 = document.querySelector('#ex9');
var btnUpload = sec9.querySelector('.btn-upload');
var inputFile = sec9.querySelector('input[type=file]');
var uploadBox = sec9.querySelector('.upload-box');

$(()=>{
	$('.close').on('click',function(){
		$('#assignmentSubmitModal').modal('hide');
		$('#assignmentInfoModal').modal('hide');
	})
	

	// 탭 변환
	$('.tabChangeBtn').on('click',function (){
        var tabId = $(this).data('tab');
		
        $('.tabChangeBtn').removeClass('active');
        $('.tab-content').removeClass('current');
		
        $(this).addClass('active');
		
        $(`#${tabId}`).addClass('current');
		$('#backToListBtn').trigger('click');
    });

	$('#classroomLectureListOption').on('change',function(){
		var targetLectureNo = $(this).val();
		if(targetLectureNo){
			selectLectureOptionFunction(targetLectureNo);
		}else{
			noselectionLecture();
		}
	});

	$(document).on('click','.getTestBtn',function(){
		var targetTrTag = $(this).parents('tr');
		var targetTestLectureNo = $(targetTrTag).data('lctreNo');
		var targetTestSe = $(targetTrTag).data('testSe');
		Swal.fire({
			title: "시험에 응시하시겠습니까?",
			text: "시험은 한 번만 응시할 수 있습니다",
			icon: "warning",
			showCancelButton: true,
			confirmButtonColor: "#3085d6",
			cancelButtonColor: "#d33",
			confirmButtonText: "응시",
			cancelButtonText : "취소"
		}).then((result) => {
			if (result.isConfirmed) {
				getTakingTestPage(targetTestLectureNo,targetTestSe);
			}
		});
	})
	
	$(document).on('click','.getTestInfoBtn',function(){
		var $thisBtn = $(this);
		console.log($thisBtn);
		var targetTrTag = $(this).parents('tr');
		var targetTestLectureNo = $(targetTrTag).data('lctreNo');
		var targetTestSe = $(targetTrTag).data('testSe');
		var testResultInfoAjaxSettings = {
			url : `/student/classroom/testInfo/${targetTestLectureNo}/${targetTestSe}`
			, method : "get"
			, dataType : "json"
			, success : function(res){
				$.notify(`${res}점/100점`)
			}
			, error : function(xhr, status, err){
				console.log(err);
			}
		};
		$.ajax(testResultInfoAjaxSettings);
	});
	
	// 제출 완료 버튼
	$(document).on('click','.submittedBtn',function(){
		var $targetTrtag = $(this).parents('tr');
		var targetCrNo = $targetTrtag.data('crNo');
		// 여기서 모달 오픈 전처리 필요
		var targetAssignmentTitle = $targetTrtag.find('td').eq(0).html();
		
		$('#assignmentTitleAreaForInfoModal').html(targetAssignmentTitle);

		getSubmittedAssignmentInformation(targetCrNo);

		
	});
	
	// 과제 제출버튼
	$(document).on('click','.submitBtn',function(){
		var $targetTrtag = $(this).parents('tr');
		var targetCrNo = $targetTrtag.data('crNo');
		var targetLectureNo = $targetTrtag.data('lctreNo');
//		console.log(targetCrNo, targetLectureNo);
		
		var targetAssignmentTitle = $targetTrtag.find('td').eq(0).html();
		
		// 여기서 모달 오픈 전처리 필요
		$('#assignmentSubmitLctreNo').val(targetLectureNo);
		$('#assignmentSubmitCrNo').val(targetCrNo);
		$('#assignmentTitleAreaForSubmitModal').html(targetAssignmentTitle);
		
		getAssignmentInformation(targetCrNo);
		
	});
	$('#assignmentSubmitModal').on('show.bs.modal',function(){
	});
	$('#assignmentInfoModal').on('show.bs.modal',function(){
//		console.log(tempTargetLctreNo);
//		console.log(tempTargetCrNo);
	});

	$('#assignmentSubmitModal').on('hidden.bs.modal',function(){
		$("#assignmentTitle").html('');
		$("#assignmentContent").html('');
		$("#assignmentAttatch").html('');
		$('#assignmentTitleAreaForSubmitModal').empty();
		$('#assignmentSubmitForm')[0].reset();
		tempFileData = null;
		$('#dragAndDropFileInfo').html('파일을 끌어다 놓으세요!');
	});
	$('#assignmentInfoModal').on('hidden.bs.modal',function(){
		tempTargetCrNo = null;
		tempTargetLctreNo = null;
		$('#assignmentTitleAreaForInfoModal').empty();
	});

	
	// 파일 업로드 모달쪽 처리

    uploadBox.addEventListener('dragenter', function(e) {
        //console.log('dragenter');
    });
    
    uploadBox.addEventListener('dragover', function(e) {
        e.preventDefault();
//        console.log('dragover');
		this.classList.add('fileOver');
//        this.style.backgroundColor = 'green';
    });
    
    uploadBox.addEventListener('dragleave', function(e) {
        //console.log('dragleave');
		this.classList.remove('fileOver');
//        this.style.backgroundColor = 'white';
    });
    
    uploadBox.addEventListener('drop', function(e) {
        e.preventDefault();
		this.classList.remove('fileOver');

        //console.log('drop');
//        this.style.backgroundColor = 'white';
        
        //console.dir(e.dataTransfer);

		var testData = e.dataTransfer.files;
        var data = e.dataTransfer.files[0];
//		console.log('제발.. : ',testData);
		//console.log('여기',e,dataTransfer);
//		console.log('파일정보',data);
		if(testData.length > 1){
			$('#dragAndDropFileInfo').notify('파일은 하나만 전송 가능합니다!');
			return false;
		}

		if(!(data.type == 'application/haansofthwp') && !(data.type.indexOf('officedocument')>0)){
			$('#dragAndDropFileInfo').notify('유효하지 않은 파일!');
			return false;
		}
		
		if(data.size >= 1024 * 1024 * 50){
			$('#dragAndDropFileInfo').notify('50MB 이상인 파일은 업로드할 수 없습니다!');
			return false;
		}
//		console.log('파일유효성검사 통과');
		if(data){
			tempFileData = data;
			$('#dragAndDropFileInfo').html(data.name);
		}
//		console.log('설정된 파일 : ',tempFileData);
    });
	
	$('#assignmentFile').on('change',function(e){
		tempFileData = null;
//		console.log(e.target.files[0].name);
		var targetFileData = e.target.files[0];
		if(!(targetFileData.type == 'application/haansofthwp') && !(targetFileData.type.indexOf('officedocument')>0)){
			$('#dragAndDropFileInfo').notify('유효하지 않은 파일!');
			return false;
		}
		if(targetFileData){
			tempFileData = targetFileData;
			$('#dragAndDropFileInfo').html(targetFileData.name);
		}else{
			targetFileData = null;
			$('#dragAndDropFileInfo').html('파일을 끌어다 놓으세요!');
		}
		console.log('설정된 파일 : ',tempFileData);
	});
	
	$('#assignmentSubmitForm').on('submit',function(e){
		e.preventDefault();
		if(tempFileData == null){
			$('#dragAndDropFileInfo').notify('파일선택필수!');
			return false;
		}
		var formData = new FormData();
		
		
		formData.append('assignmentFile', tempFileData, $('#dragAndDropFileInfo').html());
		formData.append('crNo', $('#assignmentSubmitCrNo').val());
		formData.append('lctreNo', $('#assignmentSubmitLctreNo').val());
		console.log("보내는 값",formData);
		
		var assignmentSubmitAjaxSettings = {
			url : "/student/classroom/assignment"
			, method: "POST"
			, contentType: false
			, processData : false
			, data: formData
			, dataType: "json"
			, success: function(resp) {
				if(resp.result){
					Swal.fire({
						icon: 'success',
						title: "제출 성공",
						showConfirmButton: false,
						timer: 1500
					});
					setTimeout(function() {
						// 처리
						$('#classroomLectureListOption').trigger('change');
						$('.close').trigger('click');
					}, 1500);
				}else{
					Swal.fire({
						icon: 'error',
						title: "제출 실패",
						showConfirmButton: false,
						timer: 1500
					});
				}
			}
			, error : function(xhr, err, status) {
				// 에러 시 수행할 작업
				console.error(err);
			}
		};
		$.ajax(assignmentSubmitAjaxSettings);
	});
	$(document).on('click','.fileDownLoadBtn',function(){
		$(this).data('atchFileNo');
		$(this).data('atchFileSn');
	});
	
	$(document).on('click','#classroomBoardListBody tr',function(){
		let booolean = $(this).hasClass('noDataTrTag');
		if(booolean){
			return;
		}
		let crNo = $(this).data('crNo');
		
		let ajaxSettings = {
			url : `/student/classroom/assignmentInfo/${crNo}`
			, method : "get"
			, dataType : "json"
			, error : function(xhr, err, status) {
				// 에러 시 수행할 작업
				console.error(err);
			}
			, success : function(resp){
				let assignmentInfo = resp.info;
				$("#boardDetailTitle").html(assignmentInfo.crTitle);
				$("#boardDetailCn").html(assignmentInfo.crCn);
				$("#boardDetailWriter").html(assignmentInfo.crWrter);
				$("#boardDetailDate").html(assignmentInfo.crDate);
				let atchHTMLCode = makeATagForAttatch(assignmentInfo.atchFile);
				$("#boardDetailAttatch").html(atchHTMLCode);
				$('#classroomBoardListTable').css('display','none');
				$('#boardDetailDiv').css('display','block');
			}
		};
		$.ajax(ajaxSettings);
	});
	
	$(document).on('click','#backToListBtn',function(){
			$('#boardDetailDiv').css('display','none');
			$('#classroomBoardListTable').css('display','table');
			$("#boardDetailTitle").html("");
			$("#boardDetailCn").html("");
			$("#boardDetailWriter").html("");
			$("#boardDetailDate").html("");
			$("#boardDetailAttatch").html("");
	});
	
	
	
});

function makeATagForAttatch(atchFile){
	// 파일 다운로드 버튼 위치
	return `<a href="/student/classroom/fileDownload/${atchFile.atchFileNo}/${atchFile.atchFileSn}" class="fileDownLoadBtn">${atchFile.atchFileNm}</a>`;
}
function makeATagForAttatchWithInfo(info){
	// 파일 다운로드 버튼 위치
	return `<a href="/student/classroom/fileDownload/${info.atchFileNo}/${info.atchFileSn}" class="fileDownLoadBtn">${info.atchFileNm}</a>`;
}

function getSubmittedAssignmentInformation(crNo){
	
	var submittedInfoAjaxSettings = {
		url : `/student/classroom/assignmentSubmitInfo/${crNo}`
		, method : "get"
		, dataType : "json"
		, error : function(xhr, err, status) {
			// 에러 시 수행할 작업
			console.error(err);
			$('.close').trigger('click');
		}
		, success : function(resp){
			var assignmentInfo = resp.info;
			$('#assignmentInfoBody').html(makeATagForAttatchWithInfo(assignmentInfo));
			$('#assignmentInfoModal').modal('show');
		}
	};
	$.ajax(submittedInfoAjaxSettings);
	
}

function getAssignmentInformation(crNo){
	var assignmentInfoAjaxSettings = {
		url : `/student/classroom/assignmentInfo/${crNo}`
		, method : "get"
		, dataType : "json"
		, error : function(xhr, err, status) {
			// 에러 시 수행할 작업
			console.error(err);
			$('.close').trigger('click');
		}
		, success : function(resp){
			var assignmentInfo = resp.info;
			$("#assignmentTitle").html(assignmentInfo.crTitle);
			$("#assignmentContent").html(assignmentInfo.crCn);
			var atchHTMLCode = makeATagForAttatch(assignmentInfo.atchFile);
			$("#assignmentAttatch").html(atchHTMLCode);
			$('#assignmentSubmitModal').modal('show');
		}
	};
	$.ajax(assignmentInfoAjaxSettings);
	
}


function getTakingTestPage(lctreNo,testSe){
	
	var targetTestURL = `/student/classroom/test/${lctreNo}/${testSe}`;
	var onlineTestPopup = window.open(targetTestURL,'title',`width=${screen.width}, height=${screen.height},fullscreen=yes,scrollbars=yes,toolbar=no,menubar=no,location=no,title=no,popup=yes`);
	onlineTestPopup.addEventListener('unload',function(){
		$('#classroomLectureListOption').trigger('change');
	})
}




function noselectionLecture(){
	var htmlCode = '<p>강의를 선택해주세요</p>';
	$('#assignmentDivBody').html(htmlCode);
	$('#testDivBody').html(htmlCode);
	$('#classroomBoardDivBody').html(htmlCode);
	$('#onlineLectureDivBody').html(htmlCode);
}

function selectLectureOptionFunction(lctreNo){
	getTestList(lctreNo);
	getAssignmentList(lctreNo);
	getclassroomBoardList(lctreNo);
	//getonlineLectureList(lctreNo);
}
function getTestList(lctreNo){
	var testListAjaxSettings = {
		url : `/student/classroom/test/${lctreNo}`
		, method : "get"
		, success : function(res){
			$('#testDivBody').html(res);
		}
		, error : function(xhr, status, err){
			console.log(err);
		}
	};
	$.ajax(testListAjaxSettings);
}
function getAssignmentList(lctreNo){
	var assignmentAjaxSettings = {
		url : `/student/classroom/assignment/${lctreNo}`
		, method : "get"
		, success : function(res){
			$('#assignmentDivBody').html(res);
		}
		, error : function(xhr, status, err){
			console.log(err);
		}
	};
	$.ajax(assignmentAjaxSettings);
}
function getclassroomBoardList(lctreNo){
	var classroomBoardAjaxSettings = {
		url : `/student/classroom/classroomBoard/${lctreNo}`
		, method : "get"
		, success : function(res){
			$('#classroomBoardDivBody').html(res);
		}
		, error : function(xhr, status, err){
			console.log(err);
		}
	};
	$.ajax(classroomBoardAjaxSettings);
}
/*
function getonlineLectureList(lctreNo){
	var onlineLectureAjaxSettings = {
		url : `/student/classroom/onlineLecture/${lctreNo}`
		, method : "get"
		, success : function(res){
			$('#onlineLectureDivBody').html(res);
		}
		, error : function(xhr, status, err){
			console.log(err);
		}
	};
	$.ajax(onlineLectureAjaxSettings);
}
*/
