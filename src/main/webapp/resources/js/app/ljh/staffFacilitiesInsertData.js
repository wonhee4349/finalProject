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
	$('#facilitiesForm').on('submit',function(e){
		e.preventDefault();
		let data = $(this).serialize();
		//debugger;
		//# sourceURL=foo.coffee 
		console.log("서브밋 이벤트 핸들러");
		let settings = {
			url : "/staff/facilities/staffFacilitiesInsertData"
			, method : "POST"
			, data : data		
			, dataType : 'json'		
			, success : function(resp){
					if(resp.success){
					Swal.fire({
						title: "승인 처리!",
						text: `${resp.message}`,
						icon: "success",
						showConfirmButton: false,
               			timer: 1500
					});
					setTimeout(function() {
		                location.href = "/staff/facilitiesListUI";
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
	$("#inputDataFaci").on("click",function(){
		$("#fcltNm").val("1101호");
		$("#fcltPurps").val("세미나실");
		$("#fcltNmpr").val("35");
		$("#fcltbuldNo").val("DD");
	
	});
	
});
