/**
 * <pre>
 * 학생 장학금 신청 페이지에서 사용하는 자바스크립트
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
	fn_paging(1);
    $(document).on("click",".scholarshipModalBtn", function () {
		var schlshipNo = $(this).parents('tr').data('schlshipNo');
//		console.log(schlshipNo);
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
        $('#scholarshipModal').modal('show');
    });
	
	$('.close').on('click',function(){
        $('#scholarshipModal').modal('hide');
	})
	
	$('#scholarshipModal').on('hidden.bs.modal',function(){
		$('#scholarshipModalTableBody').empty();
		console.log("모달 닫힌 후",$('#scholarshipModalTableBody').html());
		$('#modalSchlshipNo').val('');
	});
	
	$('#studentScholarshipRequestForm').on('submit',function(event){
		event.preventDefault();
		var requestTargetScholarshipNo = $('#modalSchlshipNo').val();
		console.log(requestTargetScholarshipNo);
		var settings = {
			url : `/student/scholarship/${requestTargetScholarshipNo}`
			, method : "post"
			, dataType : "json"
			, success : function(resp){
				console.log(resp);
				let icon = null;
				let title = null;
				if(resp.result == 'OK'){
					icon = "success";
					title = "신청 성공";
				}else{
					icon = "info";
					title = resp.message;
				}
				Swal.fire({
					icon: icon,
					title: title,
					showConfirmButton: false,
					timer: 1500
				});
				setTimeout(function() {
					// 처리
					if(resp.result == 'OK'){
						location.href="/student/scholarshipStatus"
					}
				}, 1500);
			}
			, error : function(xhr, status, err){
				console.log(err);
			}
		};
		$.ajax(settings);
	})
	
})


function makeStudentRequestableScholarshipList(scholarship){
	var studentRequestableScholarshipCode = `
		<tr data-schlship-no="${scholarship.schlshipNo}">
            <td>${scholarship.scholarshipList.schoNm}</td>
            <td>${scholarship.schlshipTrgter}</td>
            <td>${scholarship.pymntSe}</td>
            <td>
            	<button class="blue_btn small_btn mo scholarshipModalBtn">신청하기</button>
            </td>
        </tr>
	`;
	return studentRequestableScholarshipCode;
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

function fn_paging(page){
	var studentScholarshipPageInitAjaxSettings = {
		url : "/student/scholarship/scholarshipList"
		, method : "get"
		, dataType : "json"
		, data : {currPage : page}
		, success :function(resp){
			var paging = resp.paging;
			var dataList = paging.dataList;
			var targetCode = "";
			if(dataList?.length > 0 ){
				$.each(dataList , function(i,v){
					targetCode += makeStudentRequestableScholarshipList(v);
				})
			}else{
				targetCode = `<tr class="doDataTrTag"><td colspan="4">신청 가능한 장학금 목록이 없습니다</td></tr>`;
			}
			$('#requestableScholarshipListBody').html(targetCode);
			$('#scholarshipPaginationArea').html(paging.pagingHTML);
		}
		, error : function(xhr, status, err){
			console.log(err);
		}
	};
	$.ajax(studentScholarshipPageInitAjaxSettings);
}
