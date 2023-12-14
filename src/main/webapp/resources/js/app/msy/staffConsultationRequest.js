/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 13.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 13.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	  
	//------------------------------------------------------------------------------------------------
 	
 	function makeTrTag(consultationRequest){
		let trTag = `
			<tr data-consult-no="${consultationRequest.cnsltNo}" id="open_modal" data-toggle="modal" data-target="#modal_open">
                <td>${consultationRequest.rnum}</td>
                <td>${consultationRequest.stdntNo}</td>
                <td>${consultationRequest.student.stdntNm}</td>
                <td>${consultationRequest.student.subject.college.clgNm}</td>
                <td>${consultationRequest.student.sknrgSttusMajor1}</td>             
                <td>${consultationRequest.cnsltRequstDate}</td>
		`; 
		
		if(consultationRequest.cnsltRequstProcess=="승인 대기"){
			trTag += `<td><span class="pstatus01">${consultationRequest.cnsltRequstProcess}</span></td></tr>`;
		}else if(consultationRequest.cnsltRequstProcess=="승인 완료"){
			trTag += `<td><span class="pstatus02">${consultationRequest.cnsltRequstProcess}</span></td></tr>`;
		}else{
			trTag += `<td><span class="pstatus03">${consultationRequest.cnsltRequstProcess}</span></td></tr>`;
		}
		return trTag;
	 };
	 
	//------------------------------------------------------------------------------------------------
	
	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/staff/consultation/ajax/consultationRequest?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
			let consultationRequestList = resp.paging.dataList;
			let trTags = null;
			if(consultationRequestList.length > 0){
				$.each(consultationRequestList, function(idx, consultationRequest){
					trTags += makeTrTag(consultationRequest);
				});
				$("#pagingArea").html(resp.paging.pagingHTML);
			}else{
				trTags += `
					<tr>
						<td colspan="6" style="text-align: center;">상담 신청 목록이 없습니다.</td>
					</tr>
				`;
				$("#pagingArea").empty();
			}
			$("#listBody").html(trTags);
		});
		return false;
	}).submit();
	
	//------------------------------------------------------------------------------------------------
    
    $('#clgNo').on('change',function(){
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
		$('#sknrgSttusMajor1').val('');
	}).trigger('change');
	
	//------------------------------------------------------------------------------------------------
	
	$("#searchUI").on("click", "#searchBtn", function(event){
        let inputs = $(this).parents("#searchUI").find(":input[name]");
        $.each(inputs, function(idx, ipt){
            let name = ipt.name;
            let value = $(ipt).val();
            $(searchForm).find(`:input[name=${name}]`).val(value);
		});
		$(searchForm).submit();
    });
        
    //------------------------------------------------------------------------------------------------
        
    $("#modal_open").on("show.bs.modal", function(event){
		let $modal = $(this);
		let trTag = event.relatedTarget;
		let what = $(trTag).data("consultNo");
		let url = "/staff/consultation/ajax/consultationRequestView?what="+what;
		$.get(url)
			.done(function(resp){
				$modal.find(".modal-body").html(resp);
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
	});
	
	//------------------------------------------------------------------------------------------------
       
    $("#modal_openClose").click(function(){
        $("#modal_open").modal('hide');
    }); 
    
    //------------------------------------------------------------------------------------------------
       
	$(".modal-body").on("submit", "#appliedConsultationRequest", function(e){
		e.preventDefault();
				
		let staff = $("#inputStaff").val();
		if(!staff){
			Swal.fire("상담사를 선택해주세요.");
			return false;
		}
		
		$("#cnsltCnsltnt").val(staff);
		let data = $(this).serialize();
		let settings = {
			url : "/staff/consultation/updateAppliedConsultationRequest"
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
	
	//------------------------------------------------------------------------------------------------       
	
	$(".modal-body").on("click", "#refuseBtn", function(){
		$("#consultationRefuseModal").modal('show');
	 });
	 
	 
	$('#consultationRefuseModal').on('show.bs.modal', function () {
		let cnsltNo = $(".modal-body #hiddenCnsltNo").val();
		console.log("cnsltNo : ", cnsltNo);
		$(".modal-body #cnsltNo").val(cnsltNo);
    });
       
       
	$("#consultationRefuseModalClose").click(function(){
        $("#consultationRefuseModal").modal('hide');
    });
    
    
	$('#consultationRefuseModal').on('hidden.bs.modal', function () {
		$("#reasonContent").val('');
        $("#cnsltRequstReturn").val('');
        $("#cnsltNo").val('');
    });
    
    
    $("#refusedConsultationRequest").on("submit", function(e){
		e.preventDefault();
		let reason = $("#reasonContent").val();
		if(!reason){
			Swal.fire("반려사유를 입력해주세요.");
			return false;
		}
		$("#cnsltRequstReturn").val(reason);
		let data = $(this).serialize();
		let settings = {
			url : "/staff/consultation/updateRefusedConsultationRequest"
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
	
	//------------------------------------------------------------------------------------------------
       
    
 });
function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
}

function inputData(){
	$("#reasonContent").val("상담 가능한 시간이 아닙니다.");
}









