/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2023. 11. 23.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 23.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
$(()=>{
	$('#schoolaffairsscheduleForm').on('submit',function(e){
		e.preventDefault();
		let data = $(this).serialize();
		//debugger;
		//# sourceURL=foo.coffee 
		console.log("서브밋 이벤트 핸들러");
		let settings = {
			url : "/staff/staffschoolaffairsschedule/staffSchoolAffairsScheduleInsertData"
			, method : "POST"
			, data : data
			// , data : JSON.stringify(data)
			, dataType : 'json'
			// , contentType : 'application/json; charset=utf-8'
			, success : function(resp){
			//console.log("success function 첫줄");
				if(resp.success){
					Swal.fire({
						title: "학사일정 등록 성공!",
						text: `${resp.message}`,
						icon: "success",
						showConfirmButton: false,
						timer: 1500
					});
					setTimeout(function(){
						location.href = "/staff/staffSchoolAffairsScheduleUI";
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
	
	
	$("#inputDataSche").on("click",function(){
		$("#ScscduSe").val("03");
		$("#ScscduSeb").val("2023-12-25");
		$("#ScscduSee").val("2023-12-29");
	
	});
	
});