/**
 * <pre>
 * scholarshipsSubmit 페이지에서 사용하는 자바스크립트
 * </pre>
 * @author 김석호
 * @since 2023. 11. 21.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일            수정자       수정내용
 * --------          --------    ----------------------
 * 2023. 11. 21.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
$(()=>{
	$('#scholarshipListSearchBtn').on('click',function(){
		var scholarshipSearchTargetSemCd = $('#scholarshipSemCd').val();
		var scholarshipSearchAjaxSettings = {
			url : "/student/scholarship/List"
			, method : "get"
			, dataType : "json"
			, data : {semCd : scholarshipSearchTargetSemCd}
			, success : function(resp){
				var studentScholarshipList = resp.list;
				var studentScholarshipListCode = "";
				if(studentScholarshipList?.length > 0){
					$.each(studentScholarshipList,function(i,v){
						studentScholarshipListCode += makeStudentScholarshipListTrTag(v);
					})
				}else{
					studentScholarshipListCode = `<tr class="noDataTrTag"><td colspan="4">장학금 수혜내역이 없습니다</td></tr>`;
				}
				$('#studentScholarshipListBody').html(studentScholarshipListCode);
			}
			, error : function(xhr, status, err){
				console.log(err);
			}
		};
		$.ajax(scholarshipSearchAjaxSettings);
	})
	
	var scholarshipStatusInitSettings = {
		url : "/student/scholarship/status"
		, method : "get"
		, dataType : "json"
		, success : function(resp){
			var initDataList = resp.list;
			var initTrTagCode = "";
			if(initDataList?.length > 0){
				console.log(initDataList);
				$.each(initDataList,function(i,v){
					initTrTagCode += makeRequestScholarshipTrTag(v);
				})
			}else{
				initTrTagCode = `<tr class="noDataTrTag"><td colspan="5">장학금 신청 내역이 없습니다</td></tr>`;
			}
			$('#requestScholarshipListBody').html(initTrTagCode);
			
		}
		, error : function(xhr, status, err){
			console.log(err);
		}
	};
	$.ajax(scholarshipStatusInitSettings);
	var scholarshipSemCdAjaxSetting = {
		url : "/student/scholarship/semCdList"
		,method : "get"
		, dataType : "json"
		,success : function(resp){
			$('#scholarshipSemCdSelectContainer').prepend(makeSemCdSelectTag(resp.list));
		}
		, error : function(xhr, status, err){
			console.log(err);
		}
	};
	$.ajax(scholarshipSemCdAjaxSetting);
	
	$('#requestScholarshipListBody').on("click","tr", function () {
//		console.log(this);
		var trTagClickEventTarget = $(this).data('schlshipNo');
		if(trTagClickEventTarget){
			trTagclickEvent(trTagClickEventTarget);
	        $('#scholarshipModal').modal('show');
		}
    });
	$('#studentScholarshipListBody').on("click","tr", function () {
//		console.log(this);
		var trTagClickEventTarget = $(this).data('schlshipNo');
		if(trTagClickEventTarget){
			trTagclickEvent(trTagClickEventTarget);
	        $('#scholarshipModal').modal('show');
		}
    });
		
	$('.close').on('click',function(){
        $('#scholarshipModal').modal('hide');
	})
	
	$('#scholarshipModal').on('hidden.bs.modal',function(){
		$('#scholarshipModalTableBody').empty();
		console.log("모달 닫힌 후",$('#scholarshipModalTableBody').html());
		$('#modalSchlshipNo').val('');
	});
	
	$('button').on('click',function(){
		var targetOnTabTwo = $(this).data('tab');
		if(targetOnTabTwo != "tab-2"){
			return;
		}
		$('#scholarshipListSearchBtn').trigger('click');
	})
	$('#scholarshipListSearchBtn').trigger('click');
})

function makeSemCdSelectTag(semCdList){
	var targetSelectTagCode = `<select id="scholarshipSemCd" class="custom-select02"><option value label="전체"/>`;
	if(semCdList?.length > 0){
		$.each(semCdList,function(i,v){
			targetSelectTagCode += `<option value="${v.key}" label="${v.value}"/>`;
		})
		targetSelectTagCode += `</select>`;
	}
	return targetSelectTagCode;
}

function trTagclickEvent(schlshipNo){
//	console.log("tr클릭이벤트핸들러 첫줄",schlshipNo);
	getScholarshipInformation(schlshipNo)
	
	
	
}

function makeRequestScholarshipTrTag(scholarship){
	var requestScholarshipTrTagCode = `
	<tr data-schlship-no="${scholarship.schlshipNo}">
		<td>${scholarship.scholarship.scholarshipList.schoNm}</td>
		<td>${scholarship.scholarship.schlshipAmountStr}</td>
		<td>${scholarship.scholarship.pymntSe}</td>
		<td>${scholarship.schlshipReqstDate}</td>
		<td>${scholarship.result}</td>
	</tr>
	`;
	return requestScholarshipTrTagCode;
}

function getScholarshipInformation(schlshipNo){
	var settings = {
		url : `/student/scholarship/${schlshipNo}`
		, method : "get"
		, dataType : "json"
		, success : function(resp){
			console.log(resp)
			$('#modalSchlshipNo').val(resp.scholarship.schlshipNo);
			var targetCode = makeScholarshipInformation(resp.scholarship);
			$('#scholarshipModalTableBody').html(targetCode);
		}
		, error : function(xhr, status, err){
			console.log(err);
		}
	};
	$.ajax(settings);
}


function makeScholarshipInformation(scholarship){
	var scholarshipInformationcode = `
		<tr><th>장학금명</th><td>${scholarship.scholarshipList.schoNm}</td></tr>
		<tr><th>장학금액</th><td>${scholarship.schlshipAmountStr}</td></tr>
		<tr><th>장학대상</th><td>${scholarship.schlshipTrgter}</td></tr>
		<tr><th>선발기준</th><td>${scholarship.schlshipSelectn}</td></tr>
		<tr><th>지급방식</th><td>${scholarship.pymntSe}</td></tr>
	`;
	return scholarshipInformationcode;
}
function makeStudentScholarshipListTrTag(scholarship){
	var studentScholarshipListCode = `
		<tr data-schlship-no="${scholarship.schlshipNo}">
			<td>${scholarship.scholarship.scholarshipList.schoNm}</td>
			<td>${scholarship.scholarship.schlshipAmountStr}</td>
			<td>${scholarship.scholarship.pymntSe}</td>`;
	var semCd = `${scholarship.scholarship.semstrNo}`;
	var year = semCd.substr(0,4);
	var sem = semCd.substr(4,1);
		studentScholarshipListCode += `
			<td>${year}년${sem}학기</td>
		</tr>
	`;
	return studentScholarshipListCode;
}