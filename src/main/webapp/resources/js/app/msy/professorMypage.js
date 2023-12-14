/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 29.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 29.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	 
	//------------------------------------------------------------------------------------------------
	
	$("#modal_open").on('show.bs.modal', function () {	   		
	    $("#prfsorTelno").val($('#tel').val());
	    $("#sample6_postcode").val($('#add1').val());
	    $("#sample6_address").val($('#add2').val());
	    $("#sample6_detailAddress").val($('#add3').val());
	});
	
	$("#modifyPass").on('hidden.bs.modal', function () {	   		
	    $("#pass1").val('');
	    $("#pass2").val('');
	});
	
	//------------------------------------------------------------------------------------------------
	 
	$(".modifyMypage").click(function(){
		$("#modal_open").modal('hide');
	});
	
	$(".modifyPassClose").click(function(){
		$("#modifyPass").modal('hide');
	});
	
	//------------------------------------------------------------------------------------------------
	
	$("#zipBtn").on("click", function(){
		 sample6_execDaumPostcode();
	});
	
	//------------------------------------------------------------------------------------------------

	$("#updateMypage").on("submit", function(e){
		e.preventDefault();
		
		let profileImage = $("#updateMypage")[0];
		let formData = new FormData(profileImage);
		
		let settings = {
			url : "/professor/mypage/updateMypage"
			, method : "POST"
			, data : formData
			// , data : JSON.stringify(data)
			, dataType : 'json'
			, contentType:false
			, processData:false
			// , contentType : 'application/json; charset=utf-8'
			, success : function(resp){
				let icon = null;
				let title = null;
				if(resp){
					title = "성공했습니다!";
					icon = "success";
				}else{
					title = "실패했습니다!";
					icon = "error";
				}
				Swal.fire({
					title: title,
					icon: icon
				}).then((res)=>{
					if(resp){
						location.reload();
					}
				});
			}
			, error : function(xhr, status, err){
				console.log(err);
				alert("잘못된 요청 발생!");
			}
		};
		$.ajax(settings);
	});

	//------------------------------------------------------------------------------------------------

	$("#changePass").on("click", function(){
		formValues = checkPass();		
	});

/*
	$('#updatePass').on("submit",function(e) {
		e.preventDefault();
		
        var realPass = $("#pass1").val();
        var inputPass = $("#pass2").val();
        
        let data = $(this).serialize();
        
        if(!realPass || realPass.trim() === ""){
            alert("현재 비밀번호를 입력하세요.");
        }else if(!inputPass || inputPass.trim() === ""){
			alert("변경할 비밀번호를 입력하세요.");
		} else{
            $.ajax({
                type: 'post',
                url: '/professor/mypage/checkPass',
                data: data,
                datatype: "text"
            }).done(function(result){
                console.log("보내짐------------",result);
                if(result){
                    console.log("비밀번호 일치");
                    // 비밀번호 일치하면 업데이트 함수 호출
                    updatePass(data);
                    location.reload();
                } else if(!result){
                    console.log("비밀번호 틀림");
                    // 비밀번호가 일치하지 않으면
                    alert("비밀번호가 맞지 않습니다.");
                   location.reload();
                }
            }).fail(function(error){
                alert(JSON.stringify(error));
            })
        }
    });
	 */
 });

 
 // 우편번호 검색
 function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = '';
            var extraAddr = '';

            if (data.userSelectedType === 'R') {
                addr = data.roadAddress;
            } else { 
                addr = data.jibunAddress;
            }
            
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}

// 비밀번호 변경
function updatePass(data){
	
	Swal.fire({
	  title: "수정하시겠습니까?",
	  icon: "question",
	  showCancelButton: true,
	  confirmButtonColor: "#3085d6",
	  cancelButtonColor: "#d33",
	  confirmButtonText: "네",
	  cancelButtonText: "아니오"
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
	            type: 'post',
	            url: '/professor/mypage/updatePass',
	            data: data
	        }).done(function(res){
				let icon = null;
				let title = null;
				if(res){
					title = "성공했습니다!";
					icon = "success";			
				}else{
					title = "실패했습니다!";
					icon = "error";
				}
				Swal.fire({
					title: title,
					icon: icon
				}).then((rest)=>{
					if(res){
						location.reload();
					}
				});
	        }).fail(function(error){
	            alert(JSON.stringify(error));
	        });
		}
	});
	
	/*
	const confirmCheck = confirm("수정하시겠습니까?");	
	console.log("checkPass 확인----",data);
	
	if(confirmCheck == true){
        $.ajax({
            type: 'post',
            url: '/professor/mypage/updatePass',
            data: data
        }).done(function(result){
			console.log(result);
            if(result){
                alert("비밀번호 수정이 완료되었습니다.");
            } else{
                alert("변경 실패");
            }
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
    */
}

async function checkPass(){
	var paswords = await Swal.fire({
	  title: "비밀번호 수정",
	  html: `
		<label for="inputStdntAdres1" style="margin-bottom: 10px;">* 현재 비밀번호 *</label>
		<input type="password" id="pass1" name="realPass" class="form-style01 long_form pass" style="margin-left: 70px;" required/>
		
		<label for="inputStdntAdres1" style="margin-top:20px; margin-bottom:10px;">* 변경 비밀번호 *</label>
		<input type="password" id="pass2" name="checkPass" class="form-style01 long_form pass" style="margin-left: 70px;" required/>
	  `,
	  focusConfirm: false,
	  preConfirm: () => {		  
		return {
			realPass : $("#pass1").val(),
			inputPass : $("#pass2").val()
		};
	  }
	});
	var formValues = paswords.value;
	if(!formValues.realPass || formValues.realPass.trim() === ""){
		Swal.fire("현재 비밀번호를 입력하세요.");
    }else if(!formValues.inputPass || formValues.inputPass.trim() === ""){
		Swal.fire("변경할 비밀번호를 입력하세요.");
	} else{
        $.ajax({
            type: 'post',
            url: '/professor/mypage/checkPass',
            data: formValues,
            datatype: "text"
        }).done(function(result){
            if(result){
                updatePass(formValues);
            } else if(!result){
				Swal.fire("비밀번호가 맞지 않습니다.");
            }
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    }
}


















