/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2023. 11. 30.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 30.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
$(function(){
	  
	//------------------------------------------------------------------------------------------------
 	
 	function makeTrTag(lectureRequest){
		let trTag = `
			<tr data-lctre-reqst-no="${lectureRequest.lctreReqstNo}" data-lctre-se="${lectureRequest.lctreSe}" class="open_modal" data-toggle="modal" data-target="#modal_open">
                <td>${lectureRequest.rnum}</td>
                <td>${lectureRequest.professor.prfsorNm}</td>
                <td>${lectureRequest.lctreSeNm}</td>
                <td>${lectureRequest.course.courseNm}</td>             
		`; 
		
		if(lectureRequest.confmSeNm=="승인 대기"){
			trTag += `<td><span class="pstatus01">${lectureRequest.confmSeNm}</span></td></tr>`;
		}else if(lectureRequest.confmSeNm=="승인 완료"){
			trTag += `<td><span class="pstatus02">${lectureRequest.confmSeNm}</span></td></tr>`;
		}else{
			trTag += `<td><span class="pstatus03">${lectureRequest.confmSeNm}</span></td></tr>`;
		}
		return trTag;
	 };
	 
	//------------------------------------------------------------------------------------------------
	
	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/staff/lecture/ajax/lectureRequest?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
			let lectureRequestList = resp.paging.dataList;
			let trTags = null;
			if(lectureRequestList.length > 0){
				$.each(lectureRequestList, function(idx, lectureRequest){
					trTags += makeTrTag(lectureRequest);
				});
				$(pagingArea).html(resp.paging.pagingHTML);
			}else{
				trTags += `
					<tr>
						<td colspan="5" style="text-align: center;">강의 개설 신청 목록이 없습니다.</td>
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
    $(document).on("click",".open_modal",function(){
    	//data-lctre-se="01"
    	let lctreSe = $(this).data("lctreSe");
    	//lctreSe : 01    	
    	
    	sessionStorage.setItem("lctreSe0",lctreSe);
    	
    });
    
    //강의신청 목록의 특정 행 클릭시 실행
    $("#modal_open").on("show.bs.modal", function(event){
    	//alert("tt");
		let $modal = $(this);
		let trTag = event.relatedTarget;
		let what = $(trTag).data("lctreReqstNo");
		let url = "/staff/lecture/ajax/lectureRequestView?what="+what;
		$.get(url)
			.done(function(resp){
				console.log("resp : " + resp);
				$modal.find(".modal-body").html(resp);
				//alert(sessionStorage.getItem("lctreSe0"));
				//alert($("#appliedLectureRequest").children().eq(2).attr("name"));
				$("#appliedLectureRequest").children().eq(2).attr("value",sessionStorage.getItem("lctreSe0"));
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
	});
	
	//------------------------------------------------------------------------------------------------
       
    $("#modal_openClose").click(function(){
        $("#modal_open").modal('hide');
    }); 
    
    //------------------------------------------------------------------------------------------------
	$(document).on('click','#applyBtn' , function(){
		let data = $('#lctreReqstNo').val();
		console.log(data);
		let settings = {
			url : "/staff/lecture/updateAppliedLectureRequest"
			, method : "POST"
			, data : {lctreReqNo : data}
			// , data : JSON.stringify(data)
			, dataType : 'json'
			// , contentType : 'application/json; charset=utf-8'
			, success : function(resp){
				if(resp.success){
					Swal.fire({
						title: "승인 처리!",
						text: `${resp.message}`,
						icon: "success",
						showConfirmButton: false,
               			timer: 1500
					});
					setTimeout(function() {
		                location.reload();
		            }, 1500);
				}else{
					Swal.fire({
						title: "잘못된 요청!!",
						text: `${resp.message}`,
						icon: "error",
						showConfirmButton: false,
               			timer: 1500
					});
					setTimeout(function() {
		                location.reload();
		            }, 1500);
				}
			}
			, error : function(xhr, status, err){
				console.log(err);
				if(status == 400){
					alert("잘못된 요청 발생!");
				}
			}
		};
		$.ajax(settings);
	})

	$(".modal-body").on("submit", "#appliedLectureRequest", function(e){
		e.preventDefault();					
		let data = $(this).serialize();
		let settings = {
			url : "/staff/lecture/updateAppliedLectureRequest"
			, method : "POST"
			, data : data
			// , data : JSON.stringify(data)
			, dataType : 'json'
			// , contentType : 'application/json; charset=utf-8'
			, success : function(resp){
				if(resp.success){
					Swal.fire({
						title: "승인 처리!",
						text: `${resp.message}`,
						icon: "success"
					}).then((res)=>{
						location.reload();
					});
				}else{
					Swal.fire({
						title: "잘못된 요청!!",
						text: `${resp.message}`,
						icon: "error"
					}).then((res)=>{
						location.reload();
					});
				}
			}
			, error : function(xhr, status, err){
				console.log(err);
				if(status == 400){
					alert("잘못된 요청 발생!");
				}
			}
		};
		$.ajax(settings);
	});
	
	
	//------------------------------------------------------------------------------------------------       
	
		$(".modal-body").on("click", "#refuseBtn", function(){
		$("#lectureRefuseModal").modal('show');
	 });
	 
	 
	$('#lectureRefuseModal').on('show.bs.modal', function () {
		let lctreReqstNo = $(".modal-body #lctreReqstNo").val();
		console.log("lctreReqstNo : ", lctreReqstNo);
		$(".modal-body #lctreReqstNo").val(lctreReqstNo);
    });
       
       
	$("#lectureRefuseModalClose").click(function(){
        $("#lectureRefuseModal").modal('hide');
    });
    
    
	$('#lectureRefuseModal').on('hidden.bs.modal', function () {
		$("#reasonContent").val('');
        $("#lctreReqstReturn").val('');
        $("#lctreReqstNo").val('');
    });
    
    
    $("#refusedLectureRequest").on("submit", function(e){
		e.preventDefault();
		let reason = $("#reasonContent").val();
		if(!reason){
			alert("반려사유를 입력해주세요.");
			return false;
		}
		$("#lectureReqstReturn").val(reason);
		let data = $(this).serialize();
		let settings = {
			url : "/staff/lecture/updateRefusedLectureRequest"
			, method : "POST"
			, data : data
			// , data : JSON.stringify(data)
			, dataType : 'json'
			// , contentType : 'application/json; charset=utf-8'
			, success : function(resp){
				//console.log("success function 첫줄");
				if(resp.success){
					Swal.fire({
						title: "반려 처리!",
						text: `${resp.message}`,
						icon: "success",
						showConfirmButton: false,
               			timer: 1500
					});
					setTimeout(function() {
		                location.reload();
		            }, 1500);
				}else{
					Swal.fire({
						title: "잘못된 요청!!",
						text: `${resp.message}`,
						icon: "error",
						showConfirmButton: false,
               			timer: 1500
					});
					setTimeout(function() {
		                location.reload();
		            }, 1500);
				}
			}
			, error : function(xhr, status, err){
				console.log(err);
				if(status == 400){
					alert("잘못된 요청 발생!");
				}
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