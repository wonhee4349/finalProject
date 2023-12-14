/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2023. 11. 23.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 23.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
$(function(){
	 
	//------------------------------------------------------------------------------------------------
 	
 	function makeTrTag(board){
		let trTag = `
			<tr data-bo-no="${board.boNo}">
                <td>${board.rnum}</td>
                <td>${board.boTitle}</td>
                <td>${board.staff.sklstfNm}</td>
                <td>${board.bdSe}</td>
                <td>${board.boDate}</td>
                <td>${board.boCnt}</td>             
            </tr>
		`; 
		return trTag;
	 };
	 
	//------------------------------------------------------------------------------------------------
	
	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/professor/board/ajax/professorNoticeList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
			let professorNoticeList = resp.paging.dataList;
			let trTags = null;
			if(professorNoticeList.length > 0){
				$.each(professorNoticeList, function(idx, board){
					trTags += makeTrTag(board);
				});
				$(pagingArea).html(resp.paging.pagingHTML);
			}else{
				trTags += `
					<tr>
						<td colspan="5" style="text-align: center;">등록된 공지가 없습니다.</td>
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
		let who = $(trTag).data("boNo");
		let url = "/professor/board/ajax/noticeUpdateView?who="+who;
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
        
        
	$('#listBody').on('click','tr',function(){
		location.href=`/professor/board/noticeView/${$(this).data('boNo')}`;
	})
 });
 function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
}