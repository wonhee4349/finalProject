/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2023. 11. 22.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 22.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
//------------------------------------------------------------------------------------------------
 	
 	function makeTrTag(schoolaffairsschedule){
		let trTag = `
			<tr>
                <td>${schoolaffairsschedule.rnum}</td>
                <td>${schoolaffairsschedule.scduSeNm}</td>
                <td>${schoolaffairsschedule.semstrSeNm}</td>
                <td>${schoolaffairsschedule.schafsBeginDate}</td>        
                <td>${schoolaffairsschedule.schafsEndDate}</td> 
                <td><button type="button" data-semstr-se="${schoolaffairsschedule.semstrSe}"
                class="btn btn-danger ft_right deleteSchoolAffairsScheduleBtn" data-scdu-se="${schoolaffairsschedule.scduSe}" >삭제</button></td>       
            </tr>
                    
		`; 
		return trTag;
	 };
	 
	//------------------------------------------------------------------------------------------------
	
	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/staff/staffschoolaffairsschedule/ajax/staffschoolaffairsscheduleList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
			let schoolaffairsscheduleList = resp.paging.dataList;
			let trTags = null;
			if(schoolaffairsscheduleList.length > 0){
				$.each(schoolaffairsscheduleList, function(idx, schoolaffairsschedule){
					trTags += makeTrTag(schoolaffairsschedule);
				});
				$(pagingArea).html(resp.paging.pagingHTML);
			}else{
				trTags += `
					<tr>
						<td colspan="5" style="text-align: center;">등록된 학사일정이 없습니다.</td>
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
  	
      	
      	
 function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
}