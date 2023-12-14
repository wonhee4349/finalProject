/**
 * <pre>
 * 
 * </pre>
 * @author 김재성
 * @since 2023. 11. 17.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 	  수정일    		    수정자       수정내용
 * --------------     --------    ----------------------
 * 2023. 11. 17.     	김재성       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	 

    function makeTrTag(consultation){
		let rowData = consultation.request.cnsltRequstDate;
		let date = rowData.substring(2);
		let requestDate = date.replace(/^(\d{2})(\d{2})(\d{2})$/, '$1/$2/$3');
		
        let trTag = `
            <tr data-consult-no="${consultation.cnsltNo}" id="open_modal" data-toggle="modal" data-target="#modal_open">
                <td>${consultation.rnum}</td>
                <td>${consultation.stdntNo}</td>
                <td>${consultation.student.stdntNm}</td>
                <td>${requestDate}</td>
                <td>${consultation.cnsltDate}</td>
            </tr>
        `;
        return trTag;
    };
    

	$("#searchForm").on("submit", function(event){
        event.preventDefault();
        let data = $(this).serialize();
        $.getJSON(`/professor/consultation/ajax/professorConsultation?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
            let consultationList = resp.paging.dataList;
            let trTags = null;
            if(consultationList.length > 0){
                $.each(consultationList, function(idx, consultation){
                    trTags += makeTrTag(consultation);
                });
                $(pagingArea).html(resp.paging.pagingHTML);
            }else{
                trTags += `
                    <tr>
                    	<td colspan="5" style="text-align: center;">등록된 상담이 없습니다.</td>
                    </tr>
                `;
                $(pagingArea).empty();
            }
            $("#listBody").html(trTags);
        });
       return false;
    }).submit();


	
    $("#searchUI").on("click", "#searchBtn", function(event){
        let inputs = $(this).parents("#searchUI").find(":input[name]");
        $.each(inputs, function(idx, ipt){
            let name = ipt.name;
            let value = $(ipt).val();
            $(searchForm).find(`:input[name=${name}]`).val(value);
            });
            $(searchForm).submit();
    });
        

        
    $("#modal_open").on("show.bs.modal", function(event){
		let $modal = $(this);
		let trTag = event.relatedTarget;
		let consultNo = $(trTag).data("consultNo");
		let url = "/professor/consultation/ajax/professorConsultationView?consultNo="+consultNo;
		$.get(url)
			.done(function(resp){
				$modal.find(".modal-body").html(resp);
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
	});
	

        
        
});
function fn_paging(page){
    searchForm.page.value = page;
    searchForm.requestSubmit();
    searchForm.page.value = "";
}