/**
 * <pre>
 * 
 * </pre>
 * @author 김원희
 * @since 2023. 11. 16.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 24      김원희      최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	   
	//------------------------------------------------------------------------------------------------
 	
 	function makeTrTag(clubRequest){
	console.log(clubRequest);
		let trTag = `
			<tr data-club-no="${clubRequest.clubEsNo}" id="open_modal" data-toggle="modal" data-target="#modal_open">
                <td class="text_center">${clubRequest.rnum}</td>
                <td class="left">${clubRequest.clubEsNm}</td>
                <td  class="text_center">${clubRequest.clubSe}</td>             

            
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
		$.getJSON(`/student/club/ajax/clubRequestList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
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
			let url = "/student/club/ajax/clubRequestView?what="+what;
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
	    
	   });  

       
	$("#clubRefuseModalClose").click(function(){
        $("#clubRefuseModal").modal('hide');
    });
    
 function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
}