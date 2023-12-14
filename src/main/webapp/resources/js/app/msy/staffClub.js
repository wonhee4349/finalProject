/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 9.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	 
	//------------------------------------------------------------------------------------------------
 	
 	function makeTrTag(club){
		let trTag = `
			<tr data-club-no="${club.clubNo}" id="open_modal" data-toggle="modal" data-target="#modal_open">
                <td>${club.rnum}</td>
                <td>${club.clubSe}</td>
                <td>${club.clubNm}</td>
                <td>${club.facilities.building.buldNm}(${club.facilities.fcltsNm})</td>             
                <td>${club.student.stdntNm}</td>
            </tr>
		`; 
		return trTag;
	 };
	 
	//------------------------------------------------------------------------------------------------
	
	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/staff/club/ajax/clubList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
			let clubList = resp.paging.dataList;
			let trTags = null;
			if(clubList.length > 0){
				$.each(clubList, function(idx, club){
					trTags += makeTrTag(club);
				});
				$(pagingArea).html(resp.paging.pagingHTML);
			}else{
				trTags += `
					<tr>
						<td colspan="6" style="text-align: center;">등록된 동아리가 없습니다.</td>
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
		let url = "/staff/club/ajax/clubView?what="+what;
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