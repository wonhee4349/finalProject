/**
 * <pre>
 * 휴학신청 페이지에서 사용하는 자바스크립트
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

$(()=>{
	$('#absenceForm').on('submit',function(e){
		e.preventDefault();
		let data = $(this).serialize();
		//debugger;
		//# sourceURL=foo.coffee 
		console.log("서브밋 이벤트 핸들러");
		let settings = {
			url : "/student/vacRequest"
			, method : "POST"
			, data : data
			// , data : JSON.stringify(data)
			, dataType : 'json'
			// , contentType : 'application/json; charset=utf-8'
			, success : function(resp){
				//console.log("success function 첫줄");
				
				if (resp.success) {
					Swal.fire({
						title: "신청 성공!",
						text: `${resp.message}`,
						icon: "success",
						showConfirmButton: false,
						timer: 1500
					});
					setTimeout(function() {
						location.href = "/student/vacRequestListUI";
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
						location.href = "/student";
					}, 1500);
				}
				
			}
			, error : function(xhr, status, err){
				console.log(err);
			}
		};
		
		$.ajax(settings);
	})
	
	
})