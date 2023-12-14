/**
 * <pre>
 * 학적변동신청내역 페이지에서 사용하는 자바스크립트
 * </pre>
 * @author 김석호
 * @since 2023. 11. 15.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일       수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 15.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 

var makeTrtagsSH = (req)=>{
	let code = `
		<tr data-sknrg-no="${req.sknrgNo}">
			<td>${req.sknrgSe}</td>
			<td>${req.targetSem}</td>
			<td>${req.sknrgDate}</td>
			<td>${req.confmSe}</td>
		</tr>
	`;
	return code;
}


$(()=>{
	$('.close').on('click',function(){
		$('#vacRequestInfo').modal('hide');
		$('#modalTbody').html('');
	})
	let settings = {
		url : "/student/ajax/register"
		, method : "GET"
		, dataType : 'json'
		,success : function(resp){
			//console.log("success 첫줄");
			let datas = resp.dataList;
			let trs = "";
			if(datas.length > 0){
				$.each(datas, function(i,req){
					trs += makeTrtagsSH(req);
				});
			}else{
				trs = "<tr><td colspan='4'>학적변동 신청 이력이 없습니다</td></tr>"
			}
			$('#listbody').html(trs);
		}
		, error : function(xhr, status, err){
			console.log("error 첫줄");
			console.log(err);
		}
	}
	
	$.ajax(settings);
	
})
	$('#listbody').on('click','tr',function(){
		
		let data = $(this).data('sknrgNo');
		console.log(data);
		let settings = {
			url : `/student/ajax/register/${data}`
			, method : "GET"
			, dataType : 'json'
			,success : function(resp){
				console.log("success 첫줄");
				let res = resp.register;
				console.log("res 정상");
				let code = makeModalTag(res)
				console.log("code 정상");
				
				$('#modalTbody').html(code);
				$('#vacRequestInfo').modal('show');
				
			}
			, error : function(xhr, status, err){
				console.log("error 첫줄");
				console.log(err);
			}
		};
		$.ajax(settings);
		
	})
var makeModalTag = function(req){
	let code = `
		<tr><th>신청번호</th><td>${req.sknrgNo }</td></tr>
		<tr><th>신청학기</th><td>${req.targetSem }</td></tr>
		<tr><th>신청구분</th><td>${req.sknrgSe }</td></tr>
		<tr><th>신청일</th><td>${req.sknrgDate }</td></tr>`;
	if(req.sknrgSe !="복학신청"){
		code += `<tr><th>대상학과</th><td>${req.subjctNm }</td></tr>`;
	}
	code +=`
		<tr><th>승인상태</th><td>${req.confmSe }</td></tr>
	`;
	let confmSe = req.confmSe;
	if(confmSe == "승인 대기"){
		code += `<tr><th>신청 취소</th><td><button data-sknrg-no="${req.sknrgNo}" class="btn btn-primary ft_right mt-4" id="cancelBtn">신청취소</button></td></tr>`
	}
	
	return code;
}

$(document).on('click','#cancelBtn',function(){
	console.log($(this).data('sknrgNo'));
	let data = $(this).data('sknrgNo');
	let settings = {
			url : `/student/ajax/register/${data}`
			, method : "DELETE"
			, dataType : 'json'
			, success : function(resp){
				console.log("success 첫줄");
				
				if (resp.success) {
					Swal.fire({
						title: "신청 취소 성공!",
						text: `${resp.message}`,
						icon: "success",
						showConfirmButton: false,
						timer: 1500
					});
					setTimeout(function() {
						location.reload();
					}, 1500);
				} else {
					Swal.fire({
						title: "잘못된 요청!!",
						text: `${resp.message}`,
						icon: "error",
						showConfirmButton: false,
						timer: 1500
					});
					setTimeout(function() {
						location.reload();
					}, 1500);
				}
				
				/*
				$.each($('#listbody').find('tr'),function(i,v){
					let no = $(v).data('sknrgNo');
					if(data == no){
						$(v).remove();
					}
				})
				*/
			}
			, error : function(xhr, status, err){
				console.log("error 첫줄");
				console.log(err);
			}
		};
		$.ajax(settings);
});



