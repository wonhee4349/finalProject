/**
 * <pre>
 * 
 * </pre>
 * @author 김재성
 * @since 2023. 11. 17.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 	  수정일    		    수정자       수정내용
 * --------------     --------    ----------------------
 * 2023. 11. 21.     	김재성       최초작성
 * 2023. 11. 22.     	김재성       상담등록 js 작성
 * 2023. 11. 25.     	김재성       alert swift로 변경
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	 
	document.addEventListener("DOMContentLoaded", function () {
        // 모달 열기 버튼 클릭 시 모달 창 열기
        document.getElementById("openModal").addEventListener("click", function () {
            var modal = new bootstrap.Modal(document.getElementById("openModal"));
            modal.show();
        });

        // 모달 닫기 버튼 클릭 시 모달 창 닫기
        document.querySelector(".close").addEventListener("click", function () {
            var modal = bootstrap.Modal.getInstance(document.getElementById("openModal"));
            modal.hide();
        });
    });
	 
	  
 	
 	function makeTrTag(recognizeConsultationRequest){
		
		console.log("recognizeConsultationRequest",recognizeConsultationRequest);
		
		let cnsltDtls = recognizeConsultationRequest.cnsltReqstReturn;
		let trTag = `
			<tr data-consult-no="${recognizeConsultationRequest.cnsltNo}" id="modal_open" data-toggle="modal" data-target="#modal_open">
                <td>${recognizeConsultationRequest.rnum}</td>
                <td>${recognizeConsultationRequest.stdntNo}</td>
                <td>${recognizeConsultationRequest.student.stdntNm}</td>
                <td>${recognizeConsultationRequest.student.sknrgSttusMajor1}</td>             
                <td>${recognizeConsultationRequest.cnsltRequstProcess}</td>
                <td>${recognizeConsultationRequest.cnsltRequstDate}</td>
			</tr>
		`;
		 
       /*
		if(cnsltDtls!=null){
            trTag +=`<td style="background-color:skyblue;">상담완료</td></tr>`;
        }else{
        	 trTag +=`<td style="background-color:#fc747d;">상담미등록</td></tr>`;
        }
	  */
		return trTag;
	 };
	 
	
	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/professor/consultation/ajax/professorRecognizeConsultationRequest?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
			let consultationRecognizeRequestList = resp.paging.dataList;
			let trTags = null;
			if(consultationRecognizeRequestList.length > 0){
				$.each(consultationRecognizeRequestList, function(idx, recognizeConsultationRequest){
					trTags += makeTrTag(recognizeConsultationRequest);
				});
				$(pagingArea).html(resp.paging.pagingHTML);
			}else{
				trTags += `
					<tr>
						<td colspan="5" style="text-align: center;">등록 할 상담신청이 없습니다.</td>
					</tr>
				`;
				$(pagingArea).empty();
			}
			$("#listBody").html(trTags);
		});
		return false;
	}).submit();
	
	
	$("#searchUI").on("click", "#searchBtn", function(event){
        let inputs = $(this).parents("#searchUI").find(":input[name]");
        $.each(inputs, function(idx, ipt){
            let name = ipt.name;
            let value = $(ipt).val();
            $(searchForm).find(`:input[name=${name}]`).val(value);
            });
            $(searchForm).submit();
    });
        
        
    $("#modal_open").on("show.bs.modal", function(event){
		let $modal = $(this);
		let trTag = event.relatedTarget;
		let consultNo = $(trTag).data("consultNo");
		let url = "/professor/consultation/ajax/professorRecognizeConsultationView?consultNo="+consultNo;
		$.get(url)
			.done(function(rslt){
				$modal.find(".modal-body").html(rslt);
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
	});
	
   

    $("#modal_openClose").click(function(){
        $("#modal_open").modal('hide');
    });
    

	
	// 상담신청 승인완료된 상담 - 상담등록
    $(".modal-body").on("submit", "#createRecognizeConsultation", function(event){
		event.preventDefault();
		
		let content = $("#consultationContent").val();
		let recogDate =$("#consultationDate").val();
		let recogTimetable =$("#timetable").val();
		
		console.log("content",content);
		
		if(!content){
			Swal.fire({
			  icon: "warning",
			  title: "상담 등록내용을 입력해주세요!",
			  showConfirmButton: false,
			  timer: 1500
			});
			return false;
		}
		
		$("#recogCnsltDate").val(recogDate);
		$("#recogCnsltDtls").val(content);
		$("#recogTmtbSe").val(recogTimetable);
		
		let data = $(this).serialize();
		
		let settings = {
			url : "/professor/consultation/createProfessorRecognizeConsultation"
			, method :"POST"
			, data : data
			// , data : JSON.stringify(data)
			, dataType : 'json'
			// , contentType : 'application/json;charset=utf-8'
			, success : function(resp){
						let icon = null;
				let title = null;
				if(resp.success){
					title = "등록되었습니다!";
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
 function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
}