/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2023. 11. 25.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 25.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
$(()=>{
	$('#noticeForm').on('submit',function(e){
		e.preventDefault();
		let data = $(this).serialize();
		//debugger;
		//# sourceURL=foo.coffee 
		console.log("서브밋 이벤트 핸들러");
		let settings = {
			url : "/staff/board/staffNoticeInsertData"
			, method : "POST"
			, data : data
			// , data : JSON.stringify(data)
			, dataType : 'json'
			// , contentType : 'application/json; charset=utf-8'
			, success : function(resp){
				//console.log("success function 첫줄");
				if(resp.success){
						Swal.fire({
						title: "공지 등록 성공!",
						text: `${resp.message}`,
						icon: "success",
						showConfirmButton: false,
               			timer: 1500
					});
					setTimeout(function() {
		                location.href = "/staff/staffNoticeListUI";
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
						
				
	$("#inputDataNoti").on("click",function(){
		$("#bdse").val("01");
		$("#botitle").val("2024년도 엄석대대 체육관(실내 경기장) 대관 행사 일정 안내");
		$("#bocn").val("12.2(토) 현재 12월 본교 체육관(실내 경기장) 대관 행사 일정을 안내하오니 체육관 이용에 참고하시기 바랍니다.<br> 24.01.30(목) 입학처 입시 전형 <br> 24.02.15(금) 경기일보 주관 행사 <br> 24.05.17(일)  세계태권도 세미나 <br> 24.07.21(목) 미디어학과 행사 <br> 24.11.23(토) 글로벌미래교육원 행사 <br> 24.12.24(일)체육관 마루바닥 공사");
	
	});
	
});