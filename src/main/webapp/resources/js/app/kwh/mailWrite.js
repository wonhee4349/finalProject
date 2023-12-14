/**
 * <pre>
 * 
 * </pre>
 * @author 김원희
 * @since 2023. 11. 16.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 16.     김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

$("#inputDataOpen").on("click", function(){
    $("#emailRcver").val("00000144");
    $("#emailTitle").val("존경하는 교수님, 안녕하세요. ");
    $("#emailCn").val("교수님, 안녕하세요. 다가오는 기말고사 일정과 시험 방식에 대한 안내가 필요합니다. 또한 시험에 대한 준비를 위한 조언이나 가이드라인이 있을 경우에도 알려주시면 감사하겠습니다.");
});

function saveMail() {

	var emailSender = document.getElementById('userId').value;

	var emailRcver = document.getElementById('emailRcver').value;
	var emailTitle = document.getElementById('emailTitle').value;
	var emailCn = document.getElementById('emailCn').value;


	var dataToSend = {
		emailSender: emailSender,
		emailRcver: emailRcver,
		emailTitle: emailTitle,
		emailCn: emailCn
	};

	$.ajax({
		type: "post",
		url: `/${actorForMail}/mail/saveMail`,
		contentType: "application/json",

		data: JSON.stringify(dataToSend),
		success: function(response) {	
			 if (response === 'success') {
			 
                Swal.fire({ 
 					title: "쪽지 전송 성공.",
                    text: "쪽지 전송완료되었습니다.",
						icon: "success",
						showConfirmButton: false,
               			timer: 1500              			
                }).then((res) => {
                    let settings = {
                        url: `/${actorForMail}/mail/mailList`,
                        method: "GET",
                        dataType: "html"
                    };
                    location.href = `/${actorForMail}/mail/mailList`;
                });
            } else {
                Swal.fire({
                    title: "쪽지 전송 실패",
                    text: "쪽지 전송 실패했습니다.",
                    icon: "error"
                });
            }
		},
		error: function(error) {
			console.error(error);
		}

	});
};