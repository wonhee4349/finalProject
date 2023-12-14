/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 23.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 23.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	 
	 //------------------------------------------------------------------------------------------------

    $('.class_list_wrap button').click(function () {
        var tab_id = $(this).attr('data-tab');
        $('.class_list_wrap button').removeClass('active');
        $('.tab-content').removeClass('current');
        $(this).addClass('active');
        $("#" + tab_id).addClass('current');
        $("#searchForm")[0].reset();
        $("#searchSe").val(tab_id);
        if(tab_id=='tab-2'){
			$("#searchBtn2").trigger("click");
		}
    });
    
    $("#searchSe").val('tab-1');
    
    //------------------------------------------------------------------------------------------------
	 
	 $("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		let searchSe = $("#searchSe").val();
		$.getJSON(`/staff/absence/ajax/absenceRequestData?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
			if(searchSe=='tab-1'){
				absenceRequestList(resp);			
			}else{
				backToSchoolRequestList(resp);
			}			
		});
		return false;
	}).submit();
		
	//------------------------------------------------------------------------------------------------

	$("#searchUI1").on("click", "#searchBtn1", function(event){
        let inputs = $(this).parents("#searchUI1").find(":input[name]");
        $.each(inputs, function(idx, ipt){
            let name = ipt.name;
            let value = $(ipt).val();           
            $("#searchForm").find(`:input[name=${name}]`).val(value);
        });
            $("#searchForm").submit();
    });
    
    //------------------------------------------------------------------------------------------------
    
    $("#searchUI2").on("click", "#searchBtn2", function(event){
        let inputs = $(this).parents("#searchUI2").find(":input[name]");
        $.each(inputs, function(idx, ipt){
            let name = ipt.name;
            let value = $(ipt).val();
            $("#searchForm").find(`:input[name=${name}]`).val(value);
        });
            $("#searchForm").submit();
    });
    
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

	$("#absenceModal").on("show.bs.modal", function(event){
		let $modal = $(this);
		let trTag = event.relatedTarget;
		let what = $(trTag).data("absenceNo");
		let url = "/staff/absence/ajax/absenceRequestView?what="+what;
		$.get(url)
			.done(function(resp){
				$modal.find(".absenceModal-body").html(resp);
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".absenceModal-body").empty();
	});
	
	$("#absenceModal-close").click(function(){
        $("#absenceModal").modal('hide');
    });

	//------------------------------------------------------------------------------------------------

	$("#backToSchoolModal").on("show.bs.modal", function(event){
		let $modal = $(this);
		let trTag = event.relatedTarget;
		let what = $(trTag).data("backNo");
		let url = "/staff/absence/ajax/backToSchoolRequestView?what="+what;
		$.get(url)
			.done(function(resp){
				$modal.find(".backToSchoolModal-body").html(resp);
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".backToSchoolModal-body").empty();
	});
	
	$("#backToSchoolModal-close").click(function(){
        $("#backToSchoolModal").modal('hide');
    });

	//------------------------------------------------------------------------------------------------
	
	$(".refuseBtn").on("click", function(){
		$("#refuseModal").modal('show');
	 });
	 
	$("#refuseModalClose").click(function(){
        $("#refuseModal").modal('hide');
    });
    
    $('#refuseModal').on('hidden.bs.modal', function () {
		$("#abssklNo").val('');
        $("#abssklReturn").val('');
        $("#sknrgNo").val('');
        $("#sknrgReturn").val('');
        $("#reasonContent").val('');
    });

	//------------------------------------------------------------------------------------------------

	$("#refusedRequest").on("submit", function(e){
		e.preventDefault();
		let reason = $("#reasonContent").val();
		if(!reason){
			$("#refusedRequest").notify("반려사유를 입력해주세요.");
			return false;
		}
		
		$("#returnAbssklNo").val($("#hiddenAbssklNo").val());
		$("#abssklReturn").val($("#reasonContent").val());
		
		$("#returnSknrgNo").val($("#hiddenSknrgNo").val());
		$("#sknrgReturn").val($("#reasonContent").val());
		
		let data = $(this).serialize();
		let settings = {
			url : "/staff/absence/updateRefusedRequest"
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

	$(".absenceModal-body").on("submit", "#appliedAbsenceRequest", function(e){
		e.preventDefault();
		let data = $(this).serialize();
		let settings = {
			url : "/staff/absence/updateAppliedAbsenceRequest"
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
					icon: icon
				}).then((res)=>{
					if(resp.success){
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

	$(".backToSchoolModal-body").on("submit", "#appliedBackToSchooleRequest", function(e){
		e.preventDefault();
		let data = $(this).serialize();
		let settings = {
			url : "/staff/absence/updateAppliedBackToSchoolRequest"
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
					icon: icon
				}).then((res)=>{
					if(resp.success){
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




 });
 
function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
}

//------------------------------------------------------------------------------------------------
 
function absenceRequestList(resp){
	let absenceRequestList = resp.paging.dataList;
	let trTags = null;
	if(absenceRequestList.length > 0){
		$.each(absenceRequestList, function(idx, absenceRequest){
			trTags += makeAbsenceTrTag(absenceRequest);
		});
		$("#pagingArea1").html(resp.paging.pagingHTML);
	}else{
		trTags += `
			<tr>
				<td colspan="7" style="text-align: center;">휴학 신청 목록이 없습니다.</td>
			</tr>
		`;
		$("#pagingArea1").empty();
	}
	$("#listBody1").html(trTags);
}

function backToSchoolRequestList(resp){
	let backToSchoolRequestList = resp.paging.dataList;
	let trTags = null;
	if(backToSchoolRequestList.length > 0){
		$.each(backToSchoolRequestList, function(idx, backToSchoolRequest){
			trTags += makeBackToSchoolTrTag(backToSchoolRequest);
		});
		$("#pagingArea2").html(resp.paging.pagingHTML);
	}else{
		trTags += `
			<tr>
				<td colspan="7" style="text-align: center;">복학 신청 목록이 없습니다.</td>
			</tr>
		`;
		$("#pagingArea2").empty();
	}
	$("#listBody2").html(trTags);
}

//------------------------------------------------------------------------------------------------
 
function makeAbsenceTrTag(absenceRequest){
	let trTag = `
		<tr data-absence-no="${absenceRequest.abssklNo}" id="absenceModal-open" data-toggle="modal" data-target="#absenceModal">
            <td>${absenceRequest.rnum}</td>
            <td>${absenceRequest.stdntNo}</td>
            <td>${absenceRequest.student.stdntNm}</td>
            <td>${absenceRequest.student.subject.college.clgNm}</td>
            <td>${absenceRequest.student.sknrgSttusMajor1}</td>
            <td>${absenceRequest.abssklDate}</td>             
	`;
	
	if(absenceRequest.confmSe=="승인 대기"){
		trTag += `<td><span class="pstatus01">${absenceRequest.confmSe}</span></td></tr>`;
	}else if(absenceRequest.confmSe=="승인 완료"){
		trTag += `<td><span class="pstatus02">${absenceRequest.confmSe}</span></td></tr>`;
	}else{
		trTag += `<td><span class="pstatus03">${absenceRequest.confmSe}</span></td></tr>`;
	}
	return trTag;
 };
 
 function makeBackToSchoolTrTag(backToSchoolRequest){
	let trTag = `
		<tr data-back-no="${backToSchoolRequest.sknrgNo}" id="open_modal" data-toggle="modal" data-target="#backToSchoolModal">
            <td>${backToSchoolRequest.rnum}</td>
            <td>${backToSchoolRequest.stdntNo}</td>
            <td>${backToSchoolRequest.student.stdntNm}</td>
            <td>${backToSchoolRequest.student.subject.college.clgNm}</td>
            <td>${backToSchoolRequest.student.sknrgSttusMajor1}</td>
            <td>${backToSchoolRequest.sknrgDate}</td>             
	`; 
	if(backToSchoolRequest.confmSe=="승인 대기"){
		trTag += `<td><span class="pstatus01">${backToSchoolRequest.confmSe}</span></td></tr>`;
	}else if(backToSchoolRequest.confmSe=="승인 완료"){
		trTag += `<td><span class="pstatus02">${backToSchoolRequest.confmSe}</span></td></tr>`;
	}else{
		trTag += `<td><span class="pstatus03">${backToSchoolRequest.confmSe}</span></td></tr>`;
	}
	return trTag;
 };
 
//------------------------------------------------------------------------------------------------
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 