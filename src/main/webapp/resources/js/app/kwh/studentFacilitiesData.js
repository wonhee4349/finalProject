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
 * 2023. 11. 25.      김원희       최초작성
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
		$.getJSON(`/student/facilities/ajax/facilitiesList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
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
        

	//예약하기 모달
	
	
	$("#listBody").on("click", ".reserve-btn", function () {
    // 클릭한 버튼에서 시설 번호를 가져옵니다.
    let facilityNo = $(this).data("fclts-no");
    $.ajax({
        type: "POST",
        url: "/student/facilities/ajax/student/facilitiesList",
        data: { fcltsNo: facilityNo },
        success: function (response) {
            // 성공적인 응답을 처리합니다 (필요하면).
            console.log("예약 성공!");
        },
        error: function (error) {
            // 오류 응답을 처리합니다 (필요하면).
            console.error("예약 실패:", error);
        }
    });
});


        
    //------------------------------------------------------------------------------------------------
        	
 });
 function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
}