/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 25.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 25.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	 
	$.getJSON(`/staff/freshMan/freshManList`, function (resp) {
        let freshManList = resp.freshManList;
        let trTags = null;

        if (freshManList && freshManList.length > 0) {
            $.each(freshManList, function (idx, freshMan) {
                trTags += makeTrTag(freshMan);
            });
        } else {
            trTags += `
                <tr>
                    <td colspan="6" style="text-align:center; height:180px; line-height:155px;">신입생 목록이 없습니다.</td>
                </tr>
            `;
        }
        $("#listBody").html(trTags);
    });
	 
	//------------------------------------------------------------------------------------------------
	 
	$('.class_list_wrap button').click(function () {
		var tab_id = $(this).attr('data-tab');
		console.log(tab_id);
        $('.class_list_wrap button').removeClass('active');
        $('.tab-content').removeClass('current');
        $(this).addClass('active');
        $("#" + tab_id).addClass('current');
    });
    
    //------------------------------------------------------------------------------------------------
 
	 $("#zipBtn").on("click", function(){
		 sample6_execDaumPostcode();
	 });
	 
	 //------------------------------------------------------------------------------------------------
	 
	 $('#inputClgNo').on('change',function(){
		let data = this.value;
		console.log(data);
		let $target = $('.clgSubject');
		$target.css('display','none');
		if(data){
			let $targets = $(`.clg${data}`);
			console.log($target);
			$targets.css('display','inline-block');
		}else{
			$target.css('display','inline-block');
		}
		$('#inputSknrgSttusMajor1').val('');
	}).trigger('change');
	
	//------------------------------------------------------------------------------------------------
	
	$("#freshManInsertForm").on("submit", function(e){
		e.preventDefault();
		
		$("#stdntNm").val($("#inputStdntNm").val());
		$("#stdntIhidnum1").val($("#inputStdntIhidnum1").val());
		$("#stdntIhidnum2").val($("#inputStdntIhidnum2").val());
		$("#stdntTelno").val($("#inputStdntTelno").val());
		$("#clgNo").val($("#inputClgNo").val());
		$("#sknrgSttusMajor1").val($("#inputSknrgSttusMajor1").val());
		$("#sknrgSttusEntsch").val($("#inputSknrgSttusEntsch").val());
		$("#sknrgSttusEnterenceSe").val($("#inputSknrgSttusEnterenceSe").val());
		$("#stdntZip").val($("#sample6_postcode").val());
		$("#stdntAdres1").val($("#sample6_address").val());
		$("#stdntAdres2").val($("#sample6_detailAddress").val());
		$("#nltySe").val($("#inputNltySe").val());
		$("#sexdstnSe").val($("#inputSexdstnSe").val());
		$("#bankSe").val($("#inputBankSe").val());
		$("#stdntAcnutno").val($("#inputStdntAcnutno").val());
		if($("#sknrgSttusEnterenceSe").val() == '01'){
			$("#sknrgSttusGrade").val(1);
		}else{
			$("#sknrgSttusGrade").val(2);
		}
		
		let data = $(this).serialize();
		let settings = {
			url : "/staff/freshMan/insertFreshMan"
			, method : "POST"
			, data : data
			// , data : JSON.stringify(data)
			, dataType : 'json'
			// , contentType : 'application/json; charset=utf-8'
			, success : function(resp){
				let icon = null;
				let title = null;
				if(resp.success){
					title = "성공했습니다!";
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
	                location.reload();
	            }, 1500);
			}
			, error : function(xhr, status, err){
				console.log(err);
					alert("잘못된 요청 발생!");
			}
		};
		$.ajax(settings);
	});
	 
 });

//===============================================================================================================
 
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

//------------------------------------------------------------------------------------------------

function checkFileType(filePath) {
	var fileFormat = filePath.split(".");
	if (fileFormat.indexOf("xlsx") > -1) {
		return true;
	} else {
		return false;
	}
 
}

function check() {
	var file = $("#excelFile").val();
	if (file == "" || file == null) {
		//alert("파일을 선택해주세요.");
		$("#excelFile").notify("파일을 선택해주세요.");
		return false;
	} else if (!checkFileType(file)) {
		//alert("엑셀 파일만 업로드 가능합니다.");
		$("#excelFile").notify("엑셀 파일만 업로드 가능합니다.");
		return false;
	}
	
	Swal.fire({
	  title: "업로드 하시겠습니까?",
	  icon: "question",
	  showCancelButton: true,
	  confirmButtonColor: "#3085d6",
	  cancelButtonColor: "#d33",
	  confirmButtonText: "네",
	  cancelButtonText: "아니오"
	}).then((result) => {
	  if (result.isConfirmed) {
		var options = {
			type : "POST",
			success : function(data) {
				let icon = null;
				let title = null;
				if(data.result){
					title = "성공했습니다!";
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
	                location.reload();
	            }, 1500);
			}
			
		};
		$("#excelUploadForm").ajaxSubmit(options);
	  }
	});
 
	/*if (confirm("업로드 하시겠습니까?")) {
		var options = {
			success : function(data) {
				console.log("data : ",data);
				console.log("result : ",data.result);
				if(data.result){
					alert("모든 데이터가 업로드 되었습니다.");
					location.reload();				
				}else{
					alert("업로드에 실패했습니다.");	
				}
			},
			type : "POST"
		};
		$("#excelUploadForm").ajaxSubmit(options);
    
	}*/
}

//------------------------------------------------------------------------------------------------
 
function makeTrTag(freshMan){
	let trTag = `
		<tr>
            <td>${freshMan.rnum}</td>
            <td>${freshMan.stdntNo}</td>
            <td>${freshMan.stdntNm}</td>
            <td>${freshMan.sknrgSttusMajor1}</td>
            <td>${freshMan.sknrgSttusEntsch}</td>             
            <td>${freshMan.sknrgSttusEnterenceSe}</td>
        </tr>
	`; 
	return trTag;
};

function inputData(){
	$("#inputStdntNm").val("문뽕실");
	$("#inputStdntIhidnum1").val("050728");
	$("#inputStdntIhidnum2").val("4123469");
	$("#inputStdntTelno").val("010-1234-1234");
	$("#inputClgNo").val("A");
	$("#inputSknrgSttusMajor1").val("01");
	$("#inputSknrgSttusEntsch").val("2024-03-02");
	$("#inputSknrgSttusEnterenceSe").val("01");
	$("#sample6_postcode").val("05315");
	$("#sample6_address").val("서울 강동구 양재대로123길 7");
	$("#sample6_detailAddress").val("102-304");
	$("#inputNltySe").val("KR");
	$("#inputSexdstnSe").val("F");
	$("#inputBankSe").val("001");
	$("#inputStdntAcnutno").val("123-123456-12345");
}































 
 
 
 
 
 
 
 
 
 
 