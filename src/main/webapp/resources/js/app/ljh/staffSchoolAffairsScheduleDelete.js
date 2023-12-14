/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2023. 11. 28.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 28.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
function gostaffSchoolAffairsScheduleListUI(scduSe){
	console.log("scduSe:" + scduSe); 
$("#deleteSchoolAffairsScheduleBtn").data("scduSe", scduSe);
	let settings = {
		url : "/schoolaffairsschedule/staffSchoolAffairsScheduleListUI",
		method : "GET",
		data : {scduSe : scduSe},
		dataType : "html"
	};
	$.ajax(settings).done(function(cont){
		$(content).html(cont);
	})
}
$(()=>{
	$(document).on("click",".deleteSchoolAffairsScheduleBtn", function() {
		console.log('클릭됨');
		let scduSe = $(this).data("scduSe");
		let semterSe = $(this).data("semstrSe");
		//boNo:BD01231127001    
		console.log("scduSe:" + scduSe);
		let data = { "scduSe": scduSe, "semstrSe": semterSe };
		console.log("data : " + JSON.stringify(data));
		// 삭제 요청 보내기
		$.ajax({
			url: "/schoolaffairsschedule/deleteSchoolAffairsSchedule",
			contentType: "application/json;charset=utf-8",
			type: "POST",
			data: JSON.stringify(data),
			dataType: "text",
			success: function(response) {
				console.log("response : ", response);
				handleDeleteResponse(response);
			},
			error: function(xhr, textStatus, errorThrown) {
				console.error("메일 삭제 중 에러 발생", errorThrown);
				handleDeleteResponse("error");
			}
		});
	});

})

function handleDeleteResponse(response) {
    switch (response) {
        case "success":
           Swal.fire({
				title: "성공했습니다!",
				icon: "success",
				showConfirmButton: false,
				timer: 1500
				});
				setTimeout(function() {
	                location.reload();
	            }, 1500);
				break;
        case "fail":
            Swal.fire({
				title: "실패했습니다!",
				icon: "error",
				showConfirmButton: false,
				timer: 1500
				});
				setTimeout(function() {
	                location.reload();
	            }, 1500);
				break;
        case "error":
            console.log("메일 삭제 중 에러 발생");
            alert("메일 삭제 중 에러 발생");
            break;
        default:
            console.error("알 수 없는 응답:", response);
            break;
    }
}