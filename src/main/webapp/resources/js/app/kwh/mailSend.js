/**
 * <pre>
 * 
 * </pre>
 * @author 김원희
 * @since 2023. 11. 9.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 27.      김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */


$(function() {
	
	//------------------------------------------------------------------------------------------------

	function makeTrTag(mail) {
		let trTag = `
			<tr>
                <td>${mail.emailRcver}</td>
                <td class="text_left"><a class="mailSendDetail" href="javascript:;" onClick="goMailDetail('${mail.emailNo}')"> ${mail.emailTitle}</a></td>
                <td>${mail.emailSndngDate}</td>             
            </tr>
		`; 
		return trTag;
	};
	
	

	//------------------------------------------------------------------------------------------------

	$("#searchForm").on("submit", function(event) {
		event.preventDefault();
		let data = $(this).serialize();
		console.log("체에킁:",data);   // 검색 조건 
		$.getJSON(`/${actorForMail}/mail/ajax/mailSendList?${data}`, function(resp) {   // 요청 URL 설정 controller에 정해놓은거
			console.log("돌아온 값:",resp);
			
			let mailList = resp.paging.dataList;
			let trTags = null;
			if (mailList.length > 0) {
				$.each(mailList, function(idx, mail) {
					trTags += makeTrTag(mail);
				});
				$(pagingArea).html(resp.paging.pagingHTML);
			} else {
				trTags += `
					<tr>
						<td colspan="6" style="text-align: center;">보낸 쪽지내역이 없습니다.</td>
					</tr>
				`;
				$(pagingArea).empty();
			}
			$("#listBody").html(trTags);
		});
		return false;
	}).submit();

	//------------------------------------------------------------------------------------------------


    
	$("#searchUI").on("click", "#searchBtn", function(event) {
		let inputs = $(this).parents("#searchUI").find(":input[name]");
		$.each(inputs, function(idx, ipt) {
			let name = ipt.name;
			let value = $(ipt).val();
			$(searchForm).find(`:input[name=${name}]`).val(value);
		});
		$(searchForm).submit();
	});
	
	
	


	//------------------------------------------------------------------------------------------------

});

function fn_paging(page) {
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
}

function goMailDetail(emailNo){
	console.log("emailNo:" + emailNo); 
	
	
	 $("#deleteMailBtn").data("emailNo", emailNo);
	let settings = {
		url : `/${actorForMail}/mail/mailSendDetail`,
		method : "GET",
		data : {emailNo : emailNo},
		dataType : "html"
	};
	$.ajax(settings).done(function (cont) {
    console.log("Received HTML content:", cont);
    $(content).html(cont);
    console.log("체에롱!");
    fMailCount();
});
}

$(document).ready(function() {
  

    // 쪽지 보내기 폼 submit 이벤트
    $("#mailWrite").on("submit", function(event) {
        event.preventDefault();
        let formData = new FormData($(this)[0]);

        $.ajax({
            url: `/${actorForMail}/mail/ajax/mailWrite`,
            type: "POST",
            data: formData,
            contentType: false,
            processData: false,
            success: function(resp) {
                
            },
            error: function(err) {
                
            }
        });
    });
    

    

$("#deleteMailBtn").on("click", function() {
     let emailNo = $("#emailNo").val();
    console.log("emailNo:" + emailNo);

    // 삭제 요청 보내기
    $.ajax({
        url: `/${actorForMail}/mail/deleteMail`,
        type: "POST",
        data: { emailNo: emailNo },
        success: function(response) {
            handleDeleteResponse(response);
        },
        error: function(xhr, textStatus, errorThrown) {
            console.error("쪽지 삭제 중 에러 발생", errorThrown);
            handleDeleteResponse("error");
        }
    });
});

function handleDeleteResponse(response) {
    switch (response) {
        case "success":
            console.log("쪽지 삭제 성공");
            Swal.fire({
                icon: 'success',
                title: '쪽지 삭제 성공',
                showConfirmButton: false,
                timer: 1500
                
            });
            setTimeout(function() {
                window.location.href = `/${actorForMail}/mail/mailList`;
            }, 1500);
            break;
        case "fail":
            console.log("쪽지 삭제 실패");
            Swal.fire({
                icon: 'error',
                title: '쪽지 삭제 실패',
                showConfirmButton: false,
                timer: 1500
            });
            break;
        case "error":
            console.log("쪽지 삭제 중 에러 발생");
            Swal.fire({
                icon: 'error',
                title: '쪽지 삭제 중 에러 발생',
                showConfirmButton: false,
                timer: 1500
            });
            break;
        default:
            console.error("알 수 없는 응답:", response);
            break;
    }
}

       
});












       








