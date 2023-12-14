/**
 * <pre>
 * 
 * </pre>
 * @author 김원희
 * @since 2023. 11. 9.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */


$(function() {

	//------------------------------------------------------------------------------------------------

	function makeTrTag(club) {
		let trTag = `
				<tr data-club-no="${club.clubNo}" id="open_modal" data-toggle="modal" data-target="#modal_open">
      
                <td>${club.rnum}</td>
                <td>${club.clubSe}</td>
                <td class="text_left">${club.clubNm}</td>
                <td>${club.facilities.building.buldNm}</td>    
/*                <td>${club.student.stdntNm}</td>*/
                </tr>
		`;
		return trTag;
	};


	//------------------------------------------------------------------------------------------------

	$("#searchForm").on("submit", function(event) {
		event.preventDefault();

		let data = $(this).serialize();
		$.getJSON(`/student/club/ajax/clubList?${data}`, function(resp) {   // 요청 URL 설정 controller에 정해놓은거
			console.log("data:",resp);
			let clubList = resp.paging.dataList;
			let trTags = null;
			if (clubList.length > 0) {
				$.each(clubList, function(idx, club) {
					trTags += makeTrTag(club);
				});
				$(pagingArea).html(resp.paging.pagingHTML);
			} else {
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

	$("#searchUI").on("click", "#searchBtn", function(event) {
		let inputs = $(this).parents("#searchUI").find(":input[name]");
		$.each(inputs, function(idx, ipt) {
			let name = ipt.name;
			let value = $(ipt).val();
			$(searchForm).find(`:input[name=${name}]`).val(value);
		});
		$(searchForm).submit();
	});

	//------------------------------------------------------------------------------------------------
    
	$("#modal_open").on("hidden.bs.modal",function(event){
		//$(this).find(".modal-body").empty();		
	})

	$("#modal_open").on("show.bs.modal", function(event){
		let $modal = $(this);
		let clubNo= event.relatedTarget.dataset.clubNo;
		
		$.ajax({
			type:"get",
			url:"/student/club/ajax/clubView?clubNo="+clubNo,
			dataType:"json",
			success:function(resp){
				console.log("원희대박",resp);
				$("[clubNm]").html(resp.clubNm);
				$("[clubSe]").html(resp.clubSe);
				$("[fcltsNm]").html(resp.facilities.building.buldNm);
				$("[stdntNm]").html(resp.student.stdntNm);
				$("[clubIntrcn]").html(resp.clubIntrcn);
				//$modal.find(".modal-body").html(resp);				
			},
			error:function(xhr){
				console.log("Error:",xhr.status)
			}
		})

	});
	
	//------------------------------------------------------------------------------------------------    
    $(".close").click(function(){
        $("#modal_open").modal('hide');
    });
        
 });


function fn_paging(page) {
	searchForm.page.value = page;
	searchForm.requestSubmit();  // 이 부분을 수정해보세요.
	searchForm.page.value = "";
}



