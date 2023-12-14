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

	$('.modal-body').on("click", '.class_list_wrap button', function () {
		var tab_id = $(this).attr('data-tab');
		console.log(tab_id);
		$('.modal-body .class_list_wrap button').removeClass('active');
		$('.modal-body .tab-content').removeClass('current');
		$(this).addClass('active');
		$("#" + tab_id).addClass('current');
    });

	//------------------------------------------------------------------------------------------------

    function makeTrTag(student){
		let sknrgsSe = "";
		if (student.sknrgsSe === '01') {
	        sknrgsSe = '재학';
	    } else if (student.sknrgsSe === '02') {
	        sknrgsSe = '휴학';
	    } else {
	        sknrgsSe = '졸업';
	    }
		
        let trTag = `
            <tr data-student-no="${student.stdntNo}" id="open_modal" data-toggle="modal" data-target="#modal_open">
                <td>${student.rnum}</td>
                <td>${student.stdntNo}</td>
                <td>${student.stdntNm}</td>
                <td>${student.subject.college.clgNm}</td>
                <td>${student.sknrgSttusMajor1}</td>
                <td>${sknrgsSe}</td>
                <td>${student.sknrgSttusGrade}학년</td>
                <td>${student.sexdstnSe}</td>
                <td>${student.nltySe}</td>
            </tr>
        `;
        return trTag;
    };
    
    //------------------------------------------------------------------------------------------------

    $("#searchForm").on("submit", function(event){
        event.preventDefault();
        let data = $(this).serialize();
        console.log(data);
        $.getJSON(`/staff/student/ajax/studentList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
            let studentList = resp.paging.dataList;
            let trTags = null;
            if(studentList.length > 0){
                $.each(studentList, function(idx, student){
                    trTags += makeTrTag(student);
                });
                $(pagingArea).html(resp.paging.pagingHTML);
            }else{
                trTags += `
                    <tr>
                    	<td colspan="9" style="text-align: center;">등록된 학생이 없습니다.</td>
                    </tr>
                `;
                $(pagingArea).empty();
            }
            $("#listBody").html(trTags);
        });
       return false;
    }).submit();
    
    //------------------------------------------------------------------------------------------------
    
    $('#clgNo').on('change',function(){
		let data = this.value;
		console.log(data);
		let $target = $('.clgSubject');
		$target.css('display','none');
		if(data){
			let $targets = $(`.clg${data}`);
			console.log($target);
			$targets.css('display','inline-block');
		}else{
			$target.css('display','inline-block');
		}
		$('#sknrgSttusMajor1').val('');
	}).trigger('change');

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
		let who = $(trTag).data("studentNo");
		let url = "/staff/student/ajax/studentView?who="+who;
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