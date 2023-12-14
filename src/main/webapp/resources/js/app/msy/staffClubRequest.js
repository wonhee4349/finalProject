/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 16.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 16.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	   
	//------------------------------------------------------------------------------------------------
 	
 	function makeTrTag(clubRequest){
		let trTag = `
			<tr data-club-no="${clubRequest.clubEsNo}" id="open_modal" data-toggle="modal" data-target="#modal_open">
                <td>${clubRequest.rnum}</td>
                <td>${clubRequest.student.stdntNm}</td>
                <td>${clubRequest.clubEsNm}</td>
                <td>${clubRequest.clubSe}</td>             
		`; 
		
		if(clubRequest.confmSe=="승인 대기"){
			trTag += `<td><span class="pstatus01">${clubRequest.confmSe}</span></td></tr>`;
		}else if(clubRequest.confmSe=="승인 완료"){
			trTag += `<td><span class="pstatus02">${clubRequest.confmSe}</span></td></tr>`;
		}else{
			trTag += `<td><span class="pstatus03">${clubRequest.confmSe}</span></td></tr>`;
		}
		return trTag;
	 };
	 
	//------------------------------------------------------------------------------------------------
	
	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/staff/club/ajax/clubRequest?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
			let clubRequestList = resp.paging.dataList;
			let trTags = null;
			if(clubRequestList.length > 0){
				$.each(clubRequestList, function(idx, clubRequest){
					trTags += makeTrTag(clubRequest);
				});
				$(pagingArea).html(resp.paging.pagingHTML);
			}else{
				trTags += `
					<tr>
						<td colspan="5" style="text-align: center;">동아리 신청 목록이 없습니다.</td>
					</tr>
				`;
				$(pagingArea).empty();
			}
			$("#listBody").html(trTags);
		});
		return false;
	}).submit();
	
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
		let what = $(trTag).data("clubNo");
		let url = "/staff/club/ajax/clubRequestView?what="+what;
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
    
    $("#refuseBtn").on("click", function(){
		$("#clubRefuseModal").modal('show');
	 });
       
       
	$("#clubRefuseModalClose").click(function(){
        $("#clubRefuseModal").modal('hide');
    });
    
    
	$('#clubRefuseModal').on('hidden.bs.modal', function () {
		$("#reasonContent").val('');
        $("#clubEsReturn").val('');
        $("#clubEsNo").val('');
    });
    
    
    $("#refusedClubRequest").on("submit", function(e){
		e.preventDefault();
		let reason = $("#reasonContent").val();
		if(!reason){
			$("#reasonContent").notify("반려사유를 입력해주세요.");
			return false;
		}
		let clubEsNo = $("#hiddenClubEsNo").val();
		$("#clubEsReturn").val(reason);
		$("#returnClubEsNo").val(clubEsNo);
		let data = $(this).serialize();
		let settings = {
			url : "/staff/club/updateRefusedClubRequest"
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
    
    $(".modal-body").on("submit", "#appliedClubRequest", function(e){
		e.preventDefault();
		let data = $(this).serialize();
		let settings = {
			url : "/staff/club/updateAppliedClubRequest"
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
 function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
}