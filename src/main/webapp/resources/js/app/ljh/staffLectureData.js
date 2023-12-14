/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2023. 11. 13.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 13.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 	function makeTrTag(lecture){
 		console.log(lecture);
		let trTag = `
			<tr data-lctre-no="${lecture.lctreNo}" id="open_modal" data-toggle="modal" data-target="#modal_open">
				<td>${lecture.rnum}</td>
                <td>${lecture.course.courseNm}</td>
                <td>${lecture.professor.prfsorNm}</td>
                <td>${lecture.semstrSeNm}</td>
                <td>${lecture.lctreSe}</td>
            </tr>
		`; 
		return trTag;
	 };
 $(function(){
	 
	//------------------------------------------------------------------------------------------------
 	
	 
	//------------------------------------------------------------------------------------------------
	
	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/staff/lecture/ajax/lectureList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
			let lectureList = resp.paging.dataList;
			let trTags = null;
			if(lectureList.length > 0){
				$.each(lectureList, function(idx, lecture){
					trTags += makeTrTag(lecture);
				});
				$(pagingArea).html(resp.paging.pagingHTML);
			}else{
				trTags += `
					<tr>
						<td colspan="5" style="text-align: center;">등록된 강의가 없습니다.</td>
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
		console.log("여기요1",trTag);
		let who = $(trTag).data("lctreNo");
		console.log("여기요",who);
		let url = "/staff/lecture/ajax/lectureView?who="+who;
		$.get(url)
			.done(function(resp){
				$modal.find(".modal-body").html(resp);
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
	});
        
    //------------------------------------------------------------------------------------------------
        	
 });
 function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
}