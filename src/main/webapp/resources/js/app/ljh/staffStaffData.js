/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2023. 11. 11.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 11.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
$(function(){
	 
	//------------------------------------------------------------------------------------------------
 	
 	function makeTrTag(staff){
		let trTag = `
			<tr data-sklstf-no="${staff.sklstfNo}" id="open_modal" data-toggle="modal" data-target="#modal_open">
                <td>${staff.rnum}</td>
                <td>${staff.sklstfNo}</td>
                <td>${staff.sklstfNm}</td>
                <td>${staff.emplymSe}</td>
                <td>${staff.sklstfSexdstn}</td>
                <td>${staff.sklstfNlty}</td>
            </tr>
		`; 
		return trTag;
	 };
	 
	//------------------------------------------------------------------------------------------------
	
	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/staff/staff/ajax/staffList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
			let staffList = resp.paging.dataList;
			let trTags = null;
			if(staffList.length > 0){
				$.each(staffList, function(idx, staff){
					trTags += makeTrTag(staff);
				});
				$(pagingArea).html(resp.paging.pagingHTML);
			}else{
				trTags += `
					<tr>
						<td colspan="6" style="text-align: center;">등록된 교직원이 없습니다.</td>
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
		let who = $(trTag).data("sklstfNo");
		let url = "/staff/staff/ajax/staffView?who="+who;
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