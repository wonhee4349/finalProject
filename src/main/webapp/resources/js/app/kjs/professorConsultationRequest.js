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
 * 2023. 11. 17.     	김재성       최초작성
 * 2023. 11. 25.     	김재성       모달 기능 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
 	
 	function makeTrTag(consultationRequest){
		let trTag = `
			<tr data-consult-no="${consultationRequest.cnsltNo}" id="open_modal" data-toggle="modal" data-target="#modal_open">
                <td>${consultationRequest.rnum}</td>
                <td>${consultationRequest.stdntNo}</td>
                <td>${consultationRequest.student.stdntNm}</td>
                <td>${consultationRequest.student.sknrgSttusMajor1}</td>             
		`;
		let process = consultationRequest.cnsltRequstProcess ;
		
		if(process=="승인 완료"){
            trTag +=`<td><span class="pstatus02">${process}</span></td></tr>`;
        }else if(process=="반려"){
        	 trTag +=`<td><span class="pstatus03">${process}</span></td></tr>`;
        }else {
			trTag +=`<td><span class="pstatus01">${process}</span></td></tr>`;
		} 
		return trTag;
	 };
	 
	
	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/professor/consultation/ajax/consultationRequest?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
			let consultationRequestList = resp.paging.dataList;
			let trTags = null;
			if(consultationRequestList.length > 0){
				$.each(consultationRequestList, function(idx, consultationRequest){
					trTags += makeTrTag(consultationRequest);
				});
				$(pagingArea).html(resp.paging.pagingHTML);
			}else{
				trTags += `
					<tr>
						<td colspan="5" style="text-align: center;">상담 신청 목록이 없습니다.</td>
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
		let url = "/professor/consultation/ajax/consultationRequestView?consultNo="+consultNo;
		$.get(url)
			.done(function(resp){
				$modal.find(".modal-body").html(resp);
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
	});
	
   

    $("#modal_openClose").click(function(){
        $("#modal_open").modal('hide');
    });
    
//--------------------------------------------------------------------------------------------------------
	
	$(".modal-body").on("click", "#refuseBtn", function(){
		$("#refuseConsultationModal").modal('show');
	 });
	
    $("#refuseBtn").on("click", function(){
		$("#refuseConsultationModal").modal('show');
	 });
    
    $("#refuseConsultationModalClose").click(function(){
        $("#refuseConsultationModal").modal('hide');
    });
    
    $('#refuseConsultationModal').on('hidden.bs.modal', function () {
		$("#cnsltRequstReturn").val('');
        $("#cnsltNo").val('');
    });	
   
	
	
	// 상담신청 반려
    $("#refusedConsultationRequest").on("submit", function(event){
		event.preventDefault();
		let refuseReasonContent = $("#refuseReasonContent").val();
		let requestNumber =$("#hiddenCnsltRequstNo").val();
		
		if(!refuseReasonContent){
			//alert("반려사유를 입력해주세요.");
			Swal.fire({
			  icon: "warning",
			  title: "반려사유를 입력해주세요.",
			  showConfirmButton: false,
			  timer: 1500
			});
			return false;
		}
		
		$("#RequestCnsltNo").val(requestNumber);
		$("#cnsltRequstReturn").val(refuseReasonContent);
		
		let data = $(this).serialize();
		let settings = {
			url : "/professor/consultation/updateRefuseConsultationRequest"
			, method : "POST"
			, data : data
			// , data : JSON.stringify(data)
			, dataType : 'json'
			// , contentType : 'application/json;charset=utf-8'
			, success : function(resp){
		let icon = null;
				let title = null;
				if(resp.success){
					title = "상담이 반려되었습니다!";
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
	
   // 상담신청 승인
    $(".modal-body").on("submit", "#addConsultationRequest", function(event){
		event.preventDefault();
		
		let data = $(this).serialize();
		
		console.log("data$$$$$$$$$$$$$$$$$$$$$$$$$$$",data);
		
		let settings = {
			url : "/professor/consultation/updateAllowConsultationRequest"
			, method :"POST"
			, data : data
			// , data : JSON.stringify(data)
			, dataType : 'json'
			// , contentType : 'application/json;charset=utf-8'
			, success : function(resp){
						let icon = null;
				let title = null;
				if(resp.success){
					title = "승인되었습니다!";
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