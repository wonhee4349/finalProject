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

    function makeTrTag(consultation){
        let trTag = `
            <tr data-consult-no="${consultation.cnsltNo}" id="open_modal" data-toggle="modal" data-target="#consultationDetail">
                <td>${consultation.rnum}</td>
                <td>${consultation.stdntNo}</td>
                <td>${consultation.student.stdntNm}</td>
                <td>${consultation.student.subject.college.clgNm}</td>
                <td>${consultation.student.sknrgSttusMajor1}</td>
                <td>${consultation.staff.sklstfNm}</td>
                <td>${consultation.cnsltDate}</td>
            </tr>
        `;
        return trTag;
    };
    
    //------------------------------------------------------------------------------------------------

	$("#searchForm").on("submit", function(event){
        event.preventDefault();
        let data = $(this).serialize();
        $.getJSON(`/staff/consultation/ajax/consultation?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
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
                    	<td colspan="7" style="text-align: center;">등록된 상담이 없습니다.</td>
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
	
    $("#searchUI").on("click", "#searchBtn", function(event){
        let inputs = $(this).parents("#searchUI").find(":input[name]");
        $.each(inputs, function(idx, ipt){
            let name = ipt.name;
            let value = $(ipt).val();
            $("#searchForm").find(`:input[name=${name}]`).val(value);
            });
            $("#searchForm").submit();
    });
        
    //------------------------------------------------------------------------------------------------
        
    $("#consultationDetail").on("show.bs.modal", function(event){
		let $modal = $(this);
		let trTag = event.relatedTarget;
		let what = $(trTag).data("consultNo");
		let url = "/staff/consultation/ajax/consultationView?what="+what;
		$.get(url)
			.done(function(resp){
				$modal.find(".modal-body").html(resp);
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
	});
	
	
    $("#consultationDetail-close").click(function(){
        $("#consultationDetail").modal('hide');
    });  
    
    //------------------------------------------------------------------------------------------------
    
    $("#consultationDetail-body").click("#consultationModifyBtn", function(event){
        $("#consultationDetail").modal('hide');

		let what = $(this).find("#consultationModifyBtn").data("consultationNo");
		let url = "/staff/consultation/ajax/consultationModifyView?what="+what;
		$.get(url)
			.done(function(resp){
				$(document).find("#consultationModify-body").html(resp);				
			});
        
        $("#consultationModify").modal('show');
        
    });
    
    $("#consultationModify-close").click(function(){
        $("#consultationModify").modal('hide');
    });
    
    $("#consultationModify").on("hidden.bs.modal", function(){
		$(this).find("#consultationModify-body").empty();
	});
    
    //------------------------------------------------------------------------------------------------
    
    $("#consultationModify-body").on("submit", "#consultationModifyForm", function(e){
		e.preventDefault();
		
		$("#cnsltDtls").val($("#inputCnsltDtls").val());
		
		let data = $(this).serialize();
		
		let settings = {
			url : "/staff/consultation/ajax/consultationModify"
			, method : "POST"
			, data : data
			// , data : JSON.stringify(data)
			, dataType : 'json'
			// , contentType : 'application/json; charset=utf-8'
			, success : function(resp){
				let icon = null;
				let title = null;
				if(resp.success){
					title = "수정되었습니다!";
					icon = "success";
				}else{
					title = "실패했습니다!";
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
	  
});
function fn_paging(page){
    searchForm.page.value = page;
    searchForm.requestSubmit();
    searchForm.page.value = "";
}