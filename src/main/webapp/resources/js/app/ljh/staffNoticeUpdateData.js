/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2023. 11. 27.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 27.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
$(()=>{
	$('#noticeUpdateForm').on('submit',function(e){
		e.preventDefault();
		let data = $(this).serialize();
		//debugger;
		//# sourceURL=foo.coffee 
		console.log("서브밋 이벤트 핸들러");
		let settings = {
			url : "/staff/board/staffNoticeUpdateData"
			, method : "POST"
			, data : data
			// , data : JSON.stringify(data)
			, dataType : 'json'
			// , contentType : 'application/json; charset=utf-8'
			, success : function(resp){
				//console.log("success function 첫줄");
				if(resp.success){
				Swal.fire({
						title: "수정 성공!",
						text: `${resp.message}`,
						icon: "success",
						showConfirmButton: false,
						timer: 1500
					});
					setTimeout(function() {
		                location.reload();
		            }, 1500);
				}else{
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
				console.log(err);
				if(status == 400){
					alert("잘못된 요청 발생!");
				}
			}
		};
		
		$.ajax(settings);
	})
	
})