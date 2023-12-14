/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2023. 11. 28.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 28.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
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
 * 2023. 11. 28.      이재혁     최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	  
	//------------------------------------------------------------------------------------------------
 	
 	function makeTrTag(courseRequest){
		let trTag = `
			<tr data-course-reqst-no="${courseRequest.courseReqstNo}" id="open_modal" data-toggle="modal" data-target="#modal_open">
                <td>${courseRequest.rnum}</td>
                <td>${courseRequest.courseReqstNm}</td>
                <td>${courseRequest.professor.prfsorNm}</td>
                <td>${courseRequest.professor.subject.subjctNm}</td>            
		`; 
		
		if(courseRequest.confmSeNm=="승인 대기"){
			trTag += `<td><span class="pstatus01">${courseRequest.confmSeNm}</span></td></tr>`;
		}else if(courseRequest.confmSeNm=="승인 완료"){
			trTag += `<td><span class="pstatus02">${courseRequest.confmSeNm}</span></td></tr>`;
		}else{
			trTag += `<td><span class="pstatus03">${courseRequest.confmSeNm}</span></td></tr>`;
		}
		return trTag;
	 };
	 
	//------------------------------------------------------------------------------------------------
	
	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/staff/course/ajax/courseRequest?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
			let courseRequestList = resp.paging.dataList;
			let trTags = null;
			if(courseRequestList.length > 0){
				$.each(courseRequestList, function(idx, courseRequest){
					
					console.log(courseRequest);
					trTags += makeTrTag(courseRequest);
				});
				$(pagingArea).html(resp.paging.pagingHTML);
			}else{
				trTags += `
					<tr>
						<td colspan="5" style="text-align: center;">교과목 신청 목록이 없습니다.</td>
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
		let what = $(trTag).data("courseReqstNo");
		let url = "/staff/course/ajax/courseRequestView?what="+what;
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
       
	$(".modal-body").on("submit", "#appliedCourseRequest", function(e){
		e.preventDefault();					
		let data = $(this).serialize();
		let settings = {
			url : "/staff/course/updateAppliedCourseRequest"
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
	
	$(".modal-body").on("click", "#refuseBtn", function(){
		$("#courseRefuseModal").modal('show');
	 });
	 
	 
	$('#courseRefuseModal').on('show.bs.modal', function () {
		let courseReqstNo = $(".modal-body #courseReqstNo").val();
		console.log("courseReqstNo : ", courseReqstNo);
		$(".modal-body #courseReqstNo").val(courseReqstNo);
    });
       
       
	$("#courseRefuseModalClose").click(function(){
        $("#courseRefuseModal").modal('hide');
    });
    
    
	$('#courseRefuseModal').on('hidden.bs.modal', function () {
		$("#reasonContent").val('');
        $("#courseReqstReturn").val('');
        $("#courseReqstNo").val('');
    });
    
    
    $("#refusedCourseRequest").on("submit", function(e){
		e.preventDefault();
		let reason = $("#reasonContent").val();
		if(!reason){
			alert("반려사유를 입력해주세요.");
			return false;
		}
		$("#courseReqstReturn").val(reason);
		let data = $(this).serialize();
		let settings = {
			url : "/staff/course/updateRefusedCourseRequest"
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