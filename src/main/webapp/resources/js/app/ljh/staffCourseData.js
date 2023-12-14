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

$(function(){
	 
	//------------------------------------------------------------------------------------------------
 	
 	function makeTrTag(course){
		let trTag = `
			<tr>
                <td>${course.rnum}</td>
                <td>${course.clgNm}</td>                
                <td>${course.subjctNm}</td>
                <td>${course.courseNm}</td>
                <td>${course.complSe}</td>         
                <td>${course.coursePnt}학점</td>
            </tr>
		`; 
		return trTag;
	 };
	 
	//------------------------------------------------------------------------------------------------
	
	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/staff/course/ajax/courseList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
			let courseList = resp.paging.dataList;
			let trTags = null;
			console.log(resp);
			if(courseList.length > 0){
				$.each(courseList, function(idx, course){
					trTags += makeTrTag(course);
				});
				$(pagingArea).html(resp.paging.pagingHTML);
			}else{
				trTags += `
					<tr>
						<td colspan="5" style="text-align: center;">등록된 교과목이 없습니다.</td>
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
        	
 });
 function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
}