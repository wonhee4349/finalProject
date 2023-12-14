/**
 * <pre>
 * 
 * </pre>
 * @author 김원희
 * @since 2023. 11. 15.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 20.      김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 





 $(function(){

    $("#consultationForm").on("submit", function(e){
		e.preventDefault();
		var formData = new FormData(document.getElementById('consultationForm'));
		
		console.log("상담신청!!!!!!",formData);

	    // FormData를 JSON 객체로 변환합니다.
	    var jsonData = {};
	    formData.forEach(function(value, key){
	        jsonData[key] = value;
			
	    });

	    // JSON 객체를 문자열로 변환합니다.
	    var jsonString = JSON.stringify(jsonData);
        console.log(jsonString);
		let settings = {
            url : "/student/consulting/ajax/studentConsultation",
            method : "POST",
            dataType : "json",
			data: jsonData,
			success:function(resp){
						let icon = null;
				let title = null;
				if(resp.success){
					title = "신청되었습니다!";
					icon = "success";
				}else{
					title = "실패했습니다!";
					icon = "error";
				}
				Swal.fire({
					title: title,
					icon: icon,
					showConfirmButton: false,
               		timer: 1500
				});
				setTimeout(function() {
	                location.href = "/student/consulting/consultingList";
	            }, 1500);
			}
			, error : function(xhr, status, err){
				console.log(err);
					alert("잘못된 요청 발생!");
			}
		};
        $.ajax(settings).done(function(resp){
            $(content).html(resp);
        });
    });
	$("#inputDataOpen").on("click",function(){
		$("#cnsltSe").val("02");
		$("#cnsltRequstDate").val("2023-12-22"); 
		$("#cnsltRequstTime").val("A1");
		$("#cnsltRequstCn").val("곧 졸업인데 취업이 너무 불안합니다. 취업 잘 할 수 있는 방법 알고 싶어요.");
		
	});

    
});