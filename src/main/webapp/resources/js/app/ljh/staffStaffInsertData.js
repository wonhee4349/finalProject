/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2023. 11. 20.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 20.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
$(()=>{
	$('#staffForm').on('submit',function(e){
		e.preventDefault();
		let data = $(this).serialize();
		//debugger;
		//# sourceURL=foo.coffee 
		console.log("서브밋 이벤트 핸들러");
		let settings = {
			url : "/staff/staff/staffStaffInsertData"
			, method : "POST"
			, data : data
			// , data : JSON.stringify(data)
			, dataType : 'json'
			// , contentType : 'application/json; charset=utf-8'
			, success : function(resp){
				//console.log("success function 첫줄");
				if(resp.success){
					Swal.fire({
						title: "등록 성공!",
						text: `${resp.message}`,
						icon: "success",
						showConfirmButton: false,
               			timer: 1500
					});
					setTimeout(function() {
		                location.href = "/staff/staffListUI";
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
	});
	
	$("#inputDataStaf").on("click",function(){
		$("#staffNm").val("오깐부");
		$("#staffIhidnum1").val("911120");
		$("#staffIhidnum2").val("1589515");
		$("#sample6_postcode").val("90726");
		$("#sample6_address").val("경상남도 창원시 문원동 293번길");
		$("#sample6_detailAddress").val("237동 290호");
		$("#staffTelno").val("010-6865-2123");
		$("#staffEmplmn").val("2023-12-08");
		$("#staffemplymSe").val("02");
		$("#staffNlty").val("SK");
		$("#staffSexdstn").val("F");
		
	});
	
});
