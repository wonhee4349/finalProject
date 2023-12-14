/**
 * <pre>
 * 휴학신청 목록조회 페이지에서 사용하는 자바스크립트
 * </pre>
 * @author 김석호
 * @since 2023. 11. 14.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일       수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 14.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 

var makeTrtagsSH = function(vacRequest){
	let code = `
		<tr data-vac-no="${vacRequest.abssklNo}">
			<td>${vacRequest.reqSem}</td>
			<td>${vacRequest.abssklDate}</td>
			<td>${vacRequest.confmSe}</td>
		</tr>
	`;

	return code;
}


$(()=>{
	$('.close').on('click',function(){
		$('#vacRequestInfo').modal('hide');
		$('#modalTbody').empty();
	});

	let settings = {
		url : "/student/vacRequestList"
		, method : "GET"
		, dataType : 'json'
		,success : function(resp){
			//console.log("success 첫줄");
			let datas = resp.dataList;
			let trs = "";
			if(datas.length > 0){
				$.each(datas, function(i,vacRequest){
					trs += makeTrtagsSH(vacRequest);
				});
			}else{
				trs = "<tr><td colspan='3'>휴학 신청 이력이 없습니다</td></tr>"
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
		let data = $(this).data('vacNo');
		console.log(data);
		let settings = {
			url : `/student/vacRequest/${data}`
			, method : "GET"
			, dataType : 'json'
			,success : function(resp){
//				console.log("success 첫줄");
				let res = resp.data;
//				console.log("res 정상");
				let code = makeModalTag(res)
//				console.log("code 정상");
				
				$('#modalTbody').html(code);
		        $('#vacRequestInfo').modal('show');
				
			}
			, error : function(xhr, status, err){
//				console.log("error 첫줄");
				console.log(err);
			}
		};
		$.ajax(settings);
		
	})
var makeModalTag = function(vac){
	let code = `
		<tr><th>신청번호</th><td>${vac.abssklNo }</td></tr>
		<tr><th>신청학기</th><td>${vac.reqSem }</td></tr>
		<tr><th>신청일</th><td>${vac.abssklDate }</td></tr>
		<tr><th>신청사유</th><td>${vac.abssklCn }</td></tr>
		<tr><th>승인상태</th><td>${vac.confmSe }</td></tr>
	`;
	let confmSe = vac.confmSe;
	if(confmSe == "승인 대기"){
		code += `<tr><th>신청 취소</th><td><button data-absskl-no="${vac.abssklNo}" class="btn btn-primary ft_right mt-4" id="cancelBtn">신청취소</button></td></tr>`
	}
	
	return code;
}

$(document).on('click','#cancelBtn',function(){
	console.log($(this).data('abssklNo'));
	let data = $(this).data('abssklNo');
	let settings = {
			url : `/student/vacRequest/${data}`
			, method : "DELETE"
			, dataType : 'json'
			, success : function(resp){
				
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
				
			}
			, error : function(xhr, status, err){
				console.log("error 첫줄");
				console.log(err);
			}
		};
		$.ajax(settings);
});



