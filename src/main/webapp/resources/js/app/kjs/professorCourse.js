/*
 * </pre>
 * @author 김재성
 * @since 2023. 11. 14.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 *  수정일         수정자       수정내용
 * --------     ------    ----------------
 * 2023.11.14.   김재성       최초작성
 * 2023.11.15.   김재성       교과목내역 리스트 출력
 * 2023.11.17.   김재성       url 변경
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){

	function makeTrTag(course){
		let listTag = `
			<tr data-course-no="${course.courseNo}" id="modal_open" data-toggle="modal" data-target="#modal_open">
				<td>${course.clgNm}(${course.clgNo})</td>
				<td>${course.subjctNm}(${course.subjctNo})</td>
				<td>${course.courseNm}</td>
				<td>${course.complSe}</td>             
				<td>${course.coursePnt} 학점</td>             
			</tr>
		`; 
		return listTag;
	 };
 	

	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/professor/course/ajax/courseList?${data}`, function(rslt){   // 요청 URL 설정 controller에 정해놓은거
			let courseList = rslt.paging.dataList;
			
			let courseTags = null;
			if(courseList.length > 0){
				$.each(courseList, function(idx, course){
					courseTags += makeTrTag(course);
				});
				$(pagingArea).html(rslt.paging.pagingHTML);
			}else{
				courseTags += `
					<tr>
						<td colspan="6" style="text-align: center;">등록된 교과목 목록이 없습니다.</td>
					</tr>
				`;
				$(pagingArea).empty();
			}
			$("#listBody").html(courseTags);
		});
		return false;
	}).submit();
	
	// getList();

	$("#searchUI").on("click", "#searchBtn", function(event){
		let select = $(this).parents("#searchUI").find("#searchSelect");
			let selectName = select.attr("name");
			let	selectValue = select.val();
        
        let inputs = $(this).parents("#searchUI").find("input[name=searchWord]");
            let name = inputs.attr("name");
            let value = inputs.val();
            
            console.log("name , value 값 : ", name,value);
            $(searchForm).find(`:input[name=${name}]`).val(value);
            $(searchForm).find(`:input[name=${selectName}]`).val(selectValue);
            
            console.log("name , value 값 : ", name,value,selectName,selectValue);
			
            $("#searchForm").submit();
    });
    
    
    $("#modal_open").on("show.bs.modal", function(event){
		let $modal = $(this);
		let listTag = event.relatedTarget;
		let courseNo = $(listTag).data("courseNo");
		
		console.log("courseNo : ",courseNo);
		
		let url="/professor/course/ajax/courseView?courseNo="+courseNo;
		
		$.get(url).done(function(rslt){
				$modal.find(".modal-body").html(rslt);
				console.log("rslt",rslt);
		});
		
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
    });
	
 });
 
 function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
};