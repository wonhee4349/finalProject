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
function gostaffNoticeView(boNo){
	console.log("boNo:" + boNo); 
$("#deleteNoticeBtn").data("boNo", boNo);
	let settings = {
		url : "/board/staffNoticeView",
		method : "GET",
		data : {boNo : boNo},
		dataType : "html"
	};
	$.ajax(settings).done(function(cont){
		$(content).html(cont);
	})
}

$("#deleteNoticeBtn").on("click", function() {
     let boNo = $(this).data("boNo");
	//boNo:BD01231127001    
	console.log("boNo:" + boNo);
	let data = {"boNo":boNo};
	console.log("data : "+JSON.stringify(data));
    // 삭제 요청 보내기
    $.ajax({
        url:"/board/deleteNotice",
		contentType:"application/json;charset=utf-8",
        type:"POST",
        data:JSON.stringify(data),
		dataType:"text",
        success: function(response) {
			console.log("response : ",response);
            handleDeleteResponse(response);
        },
        error: function(xhr, textStatus, errorThrown) {
            console.error("메일 삭제 중 에러 발생", errorThrown);
            handleDeleteResponse("error");
        }
    });
});

function handleDeleteResponse(response) {
    switch (response) {
        case "success":
            Swal.fire({
				title: "삭제 성공!",
				icon: "success",
				showConfirmButton: false,
               	timer: 1500
				});
				setTimeout(function() {
	               location.href = "/staff/staffNoticeListUI";
	            }, 1500);
				break;
        case "fail":
           Swal.fire({
				title: "삭제 실패!",
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