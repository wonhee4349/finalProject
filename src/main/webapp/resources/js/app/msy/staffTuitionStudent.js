/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 16.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 16.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	 
	 //------------------------------------------------------------------------------------------------

    function makeTrTag(tuition){
        let trTag = `
            <tr data-student-no="${tuition.stdntNo}">
                <td>${tuition.rnum}</td>
                <td>${tuition.stdntNo}</td>
                <td>${tuition.stdntNm}</td>
                <td>${tuition.sknrgSttusMajor1}</td>
                <td>${tuition.schoolRegisterHistory.sknrgsSe}</td>
                <td>${tuition.sknrgSttusGrade}학년</td>
                <td>
                	<div>
						<input type="button" data-student-no="${tuition.stdntNo}" value="고지" class="btn btn-primary" id="open_modal" data-toggle="modal" data-target="#modal_open" />
					</div>	
                </td>
            </tr>
        `;
        return trTag;
    };
    
    //------------------------------------------------------------------------------------------------
	
    $("#searchForm").on("submit", function(event){
        event.preventDefault();
        let data = $(this).serialize();
        $.getJSON(`/staff/tuitionStudent/ajax/tuitionStudentList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
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
                    	<td colspan="7" style="text-align: center;">납부대상자가 없습니다.</td>
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
		let who = $(trTag).data("studentNo");
		let url = "/staff/tuitionStudent/ajax/tuitionInsertView?who="+who;
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
    
    //------------------------------------------------------------------------------------------------
    
    $("#insertTuitionModal-body").on("submit", "#tuitionInsertForm", function(e){
		e.preventDefault();
		$("#tutnEtrncf").val($("#inputEtrncf").val());
		$("#tutnTutfee").val($("#inputTutfee").val());
		let data = $(this).serialize();
		let settings = {
			url : "/staff/tuitionStudent/insertTuition"
			, method : "POST"
			, data : data
			// , data : JSON.stringify(data)
			, dataType : 'json'
			// , contentType : 'application/json; charset=utf-8'
			, success : function(resp){
				let icon = null;
				let title = null;
				if(resp.success){
					title = "성공했습니다!";
					icon = "success";
				}else{
					title = "해당 학기 등록금이 이미 고지되어 있습니다.";
					icon = "error";
				}
				Swal.fire({
					title: title,
					icon: icon,
					showConfirmButton: false,
               		timer: 1500
				});
				setTimeout(function() {
	                location.reload();
	            }, 1500);
			}
			, error : function(xhr, status, err){
				console.log(err);
					alert("잘못된 요청 발생!");
			}
		};
		$.ajax(settings);
	});
	
	//------------------------------------------------------------------------------------------------
    
});
function fn_paging(page){
    searchForm.page.value = page;
    searchForm.requestSubmit();
    searchForm.page.value = "";
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 