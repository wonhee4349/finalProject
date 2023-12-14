/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2023. 11. 10.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 10.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
$(function(){
	 
	//------------------------------------------------------------------------------------------------
 	
 	function makeTrTag(facilities){
		let trTag = `
			<tr data-fclts-no="${facilities.fcltsNo}" id="open_modal" data-toggle="modal" data-target="#modal_open">
                <td>${facilities.rnum}</td>
                <td>${facilities.fcltsNm}</td>
                <td>${facilities.fcltsNmpr}</td>
                <td>${facilities.fcltsPurps}</td>
                <td>${facilities.building.buldNm}</td>
            </tr>
		`; 
		return trTag;
	 };
	 
	//------------------------------------------------------------------------------------------------
	
	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/staff/facilities/ajax/facilitiesList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
			let facilitiesList = resp.paging.dataList;
			let trTags = null;
			if(facilitiesList.length > 0){
				$.each(facilitiesList, function(idx, facilities){
					trTags += makeTrTag(facilities);
				});
				$(pagingArea).html(resp.paging.pagingHTML);
			}else{
				trTags += `
					<tr>
						<td colspan="5" style="text-align: center;">등록된 시설물이 없습니다.</td>
					</tr>
				`;
				$(pagingArea).empty();
			}
			$("#listBody").html(trTags);
		});
		return false;
	}).submit();
	
	//------------------------------------------------------------------------------------------------
 
	$("#searchUI").on("click", "#searchBtn", function(){
        let inputs = $(this).parents("#searchUI").find(":input[name]");
        $.each(inputs, function(idx, ipt){
            let name = ipt.name;
            let value = $(ipt).val();
            $("#searchForm").find(`:input[name=${name}]`).val(value);
          });
          $("#searchForm").submit();
    });
    
       //------------------------------------------------------------------------------------------------
        
    $("#modal_open").on("show.bs.modal", function(event){
		let $modal = $(this);
		let trTag = event.relatedTarget;
		let who = $(trTag).data("fcltsNo");
		let url = "/staff/facilities/ajax/facilitiesView?who="+who;
		$.get(url)
			.done(function(resp){
				$modal.find(".modal-body").html(resp);
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
	});
    
        
    //------------------------------------------------------------------------------------------------
        	   $(".close").click(function(){
        $("#modal_open").modal('hide');
    });
        	
        	
 });
 function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
}