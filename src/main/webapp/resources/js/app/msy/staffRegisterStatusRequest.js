/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 24.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 24.      문선영       최초작성
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
		}else if(tab_id=='tab-3'){
			$("#searchBtn3").trigger("click");
		}
    });
    
    $("#searchSe").val('tab-1');
    
    //------------------------------------------------------------------------------------------------
 
	 $("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		let searchSe = $("#searchSe").val();
		$.getJSON(`/staff/registerStatus/ajax/registerStatusData?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
			if(searchSe=='tab-1'){
				doubleMajorRequestList(resp);			
			}else if(searchSe=='tab-2'){
				minorRequestList(resp);
			}else{
				moveMajorRequestList(resp);
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
    
    $("#searchUI3").on("click", "#searchBtn3", function(event){
        let inputs = $(this).parents("#searchUI3").find(":input[name]");
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

	$("#requestModal").on("show.bs.modal", function(event){
		let $modal = $(this);
		let trTag = event.relatedTarget;
		let what = $(trTag).data("registerNo");
		let url = "/staff/registerStatus/ajax/doubleMajorRequestView?what="+what;
		$.get(url)
			.done(function(resp){
				$modal.find(".requestModal-body").html(resp);
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".requestModal-body").empty();
	});
	
	$("#requestModal-close").click(function(){
        $("#requestModal").modal('hide');
    });
    
    //------------------------------------------------------------------------------------------------
	
	$(".refuseBtn").on("click", function(){
		$("#refuseModal").modal('show');
	 });
	 
	$("#refuseModalClose").click(function(){
        $("#refuseModal").modal('hide');
    });
    
    $('#refuseModal').on('hidden.bs.modal', function () {
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
		
		$("#returnSknrgNo").val($("#hiddenSknrgNo").val());
		$("#sknrgReturn").val($("#reasonContent").val());
		
		let data = $(this).serialize();
		let settings = {
			url : "/staff/registerStatus/updateRefusedRequest"
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
	
	$(".requestModal-body").on("submit", "#appliedRequest", function(e){
		e.preventDefault();
		let data = $(this).serialize();
		let settings = {
			url : "/staff/registerStatus/updateAppliedRequest"
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

//===================================================================================
 
function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
}

function doubleMajorRequestList(resp){
	let doubleMajorRequestList = resp.paging.dataList;
	let trTags = null;
	if(doubleMajorRequestList.length > 0){
		$.each(doubleMajorRequestList, function(idx, doubleMajorRequest){
			trTags += makeTrTag(doubleMajorRequest);
		});
		$("#pagingArea1").html(resp.paging.pagingHTML);
	}else{
		trTags += `
			<tr>
				<td colspan="7" style="text-align: center;">복수전공 신청 목록이 없습니다.</td>
			</tr>
		`;
		$("#pagingArea1").empty();
	}
	$("#listBody1").html(trTags);
}

function minorRequestList(resp){
	let minorRequestList = resp.paging.dataList;
	let trTags = null;
	if(minorRequestList.length > 0){
		$.each(minorRequestList, function(idx, minorRequest){
			trTags += makeTrTag(minorRequest);
		});
		$("#pagingArea2").html(resp.paging.pagingHTML);
	}else{
		trTags += `
			<tr>
				<td colspan="7" style="text-align: center;">부전공 신청 목록이 없습니다.</td>
			</tr>
		`;
		$("#pagingArea2").empty();
	}
	$("#listBody2").html(trTags);
}

function moveMajorRequestList(resp){
	let minorRequestList = resp.paging.dataList;
	let trTags = null;
	if(minorRequestList.length > 0){
		$.each(minorRequestList, function(idx, moveMajorrRequest){
			trTags += makeTrTag(moveMajorrRequest);
		});
		$("#pagingArea3").html(resp.paging.pagingHTML);
	}else{
		trTags += `
			<tr>
				<td colspan="7" style="text-align: center;">전과 신청 목록이 없습니다.</td>
			</tr>
		`;
		$("#pagingArea3").empty();
	}
	$("#listBody3").html(trTags);
}
 
 
//------------------------------------------------------------------------------------------------

function makeTrTag(request){
	let trTag = `
		<tr data-register-no="${request.sknrgNo}" id="requestModal-open" data-toggle="modal" data-target="#requestModal">
            <td>${request.rnum}</td>
            <td>${request.stdntNo}</td>
            <td>${request.student.stdntNm}</td>
            <td>${request.student.subject.college.clgNm}</td>
            <td>${request.student.sknrgSttusMajor1}</td>
            <td>${request.sknrgDate}</td>             
	`; 
	
	if(request.confmSe=="승인 대기"){
		trTag += `<td><span class="pstatus01">${request.confmSe}</span></td></tr>`;
	}else if(request.confmSe=="승인 완료"){
		trTag += `<td><span class="pstatus02">${request.confmSe}</span></td></tr>`;
	}else{
		trTag += `<td><span class="pstatus03">${request.confmSe}</span></td></tr>`;
	}
	return trTag;
}

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 