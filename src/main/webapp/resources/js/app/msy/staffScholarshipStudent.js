/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 15.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 15.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	 
	//------------------------------------------------------------------------------------------------
	 
	$('.class_list_wrap button').click(function () {
        var tab_id = $(this).attr('data-tab');
        $('.class_list_wrap button').removeClass('active');
        $('.tab-content').removeClass('current');
        $(this).addClass('active');
        $("#" + tab_id).addClass('current');
    });
    
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
 	
 	function makeTrTag(scholarshipStudent){
		let trTag = `
			<tr data-student-no="${scholarshipStudent.stdntNo}" id="open_modal" data-toggle="modal" data-target="#modal_open">
                <td>${scholarshipStudent.rnum}</td>
                <td>${scholarshipStudent.stdntNo}</td>
                <td>${scholarshipStudent.student.stdntNm}</td>
                <td>${scholarshipStudent.scholarship.semstrYear}년 (${scholarshipStudent.scholarship.semstrNo}학기)</td>       
                <td>${scholarshipStudent.scholarship.scholarshipList.schoNm}</td>
            </tr>
		`; 
		return trTag;
	 };
	 
	//------------------------------------------------------------------------------------------------
	
	 $("#searchForm").on("submit", function(event){
        event.preventDefault();
        let data = $(this).serialize();
        $.getJSON(`/staff/scholarshipStudent/ajax/scholarshipStudentList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
            let scholarshipStudentList = resp.paging.dataList;
            let trTags = null;
            if(scholarshipStudentList.length > 0){
                $.each(scholarshipStudentList, function(idx, scholarshipStudent){
                    trTags += makeTrTag(scholarshipStudent);
                });
                $("#pagingArea2").html(resp.paging.pagingHTML);
            }else{
                trTags += `
                    <tr>
                    	<td colspan="5" style="text-align: center;">등록된 장학생이 없습니다.</td>
                    </tr>
                `;
                $("#pagingArea2").empty();
            }
            $("#listScholarshipStudentBody").html(trTags);
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
   
    $("#scholarshipStudentModal").on("show.bs.modal", function(event){
		let $modal = $(this);
		let url = "/staff/scholarshipStudent/ajax/scholarshipStudentInsertView";
		$.get(url)
			.done(function(resp){
				$modal.find(".modal-body").html(resp);
				changeTrigger();
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
	});
	
	//------------------------------------------------------------------------------------------------               
       
    $("#closeScholarshipStudentModal").click(function(){
        $("#modal_open").modal('hide');
    });
    
    $("#closeScholarshipStudentInsertModal").click(function(){
        $("#scholarshipStudentModal").modal('hide');
    });
    
    //------------------------------------------------------------------------------------------------
	 
	function makeRequestTrTag(request){
        let trTag = `
            <tr data-student-no="${request.stdntNo}" id="open_modal" data-toggle="modal" data-target="#modal_open">
                <td>${request.rnum}</td>
                <td>${request.stdntNo}</td>
                <td>${request.student.stdntNm}</td>]
                <td>${request.scholarship.scholarshipList.schoNm}</td>
                <td>${request.schlshipReqstDate}</td>
            </tr>
        `;
        return trTag;
    };
    
    //------------------------------------------------------------------------------------------------
    
    $("#searchForm2").on("submit", function(event){
        event.preventDefault();
        let data = $(this).serialize();
        $.getJSON(`/staff/scholarshipStudent/ajax/scholarshipRequestList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
            console.log(resp);        
            let semesterList = resp.semesterInfo
            console.log(semesterList);
            let optionTags = `
            	<select name="searchWord2" class="custom-select02">
					<option value="" label="장학 학기" />
            `;
            $.each(semesterList, function(idx, semester){
				optionTags += `
					<option label="${semester.semstrYear}년 (${semester.semstrNo}학기)" value="${semester.semstrYear}${semester.semstrNo}"/>
				`;
			});
			optionTags += `
				</select>
                <div class="select-arrow">
                   <i class="fa fa-caret-down"></i>
                </div>
                <div class="input-group-append">
                   <button class="btn btn-primary" id="searchBtn2" type="button">
                       <i class="fas fa-search fa-sm" ></i>
                   </button>
                </div>
			`;
			$("#searchArea").html(optionTags);
						
            let scholarshipRequestList = resp.paging.dataList;
            let trTags = null;
            if(scholarshipRequestList.length > 0){
                $.each(scholarshipRequestList, function(idx, scholarshipRequest){
                    trTags += makeRequestTrTag(scholarshipRequest);
                });
                $("#pagingArea1").html(resp.paging.pagingHTML);
            }else{
                trTags += ` 
                    <tr>
                    	<td colspan="5" style="text-align: center;">장학생 신청 정보가 없습니다.</td>
                    </tr>
                `;
                $("#pagingArea1").empty();
            }
            $("#listRequestBody").html(trTags);
        });
       return false;
    }).submit();
        
	//------------------------------------------------------------------------------------------------
    
    $("#searchUI2").on("click", "#searchBtn2", function(event){
        let inputs = $(this).parents("#searchUI2").find(":input[name]");
        $.each(inputs, function(idx, ipt){
            let name = ipt.name;
            let value = $(ipt).val();
            $("#searchForm2").find(`:input[name=${name}]`).val(value);
            });
            $("#searchForm2").submit();
    });    
    
    //------------------------------------------------------------------------------------------------
    
    
    //------------------------------------------------------------------------------------------------
    
});
function fn_paging(page){
   searchForm.page.value = page;
   searchForm.requestSubmit();
   searchForm.page.value = "";
}

function changeTrigger(){
	$("#scholarshipList").trigger("change");
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 