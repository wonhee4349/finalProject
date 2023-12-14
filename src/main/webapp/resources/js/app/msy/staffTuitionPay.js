/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 22.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 22.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
$(function(){
	//------------------------------------------------------------------------------------------------

    function makeTrTag(tuition){
        let trTag = `
            <tr data-tuition-no="${tuition.tutnNhtNo}" id="open_modal" data-toggle="modal" data-target="#tuitionDetail">
                <td>${tuition.rnum}</td>
                <td>${tuition.tutnPayYr}년</td>
                <td>${tuition.tutnPaySems}학기</td>
                <td>${tuition.student.stdntNo}</td>
                <td>${tuition.student.stdntNm}</td>
                <td>${tuition.student.subject.college.clgNm}</td>
                <td>${tuition.student.sknrgSttusMajor1}</td>
                <td>${tuition.tutnPay}</td>
            </tr>
        `;
        return trTag;
    };
    
    //------------------------------------------------------------------------------------------------
	
	$("#searchForm").on("submit", function(event){
        event.preventDefault();
        let data = $(this).serialize();
        $.getJSON(`/staff/tuitionPay/ajax/tuitionPayList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
            let tuitionList = resp.paging.dataList;
            let trTags = null;
            if(tuitionList.length > 0){
                $.each(tuitionList, function(idx, tuition){
                    trTags += makeTrTag(tuition);
                });
                $(pagingArea).html(resp.paging.pagingHTML);
            }else{
                trTags += `
                    <tr>
                    	<td colspan="7" style="text-align: center;">등록금 정보가 없습니다.</td>
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
            $(searchForm).find(`:input[name=${name}]`).val(value);
            });
            $(searchForm).submit();
    });
        
    //------------------------------------------------------------------------------------------------
    
    $("#tuitionDetail-close").click(function(){
        $("#tuitionDetail").modal('hide');
    });
    
    $("#tuitionDetail").on("show.bs.modal", function(event){
		let $modal = $(this);
		let trTag = event.relatedTarget;
		let what = $(trTag).data("tuitionNo");
		let url = "/staff/tuitionPay/ajax/tuitionDetailView?what="+what;
		$.get(url)
			.done(function(resp){
				$modal.find("#tuitionDetail-body").html(resp);				
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find("#tuitionDetail-body").empty();
	});
	
	//------------------------------------------------------------------------------------------------
    
	$("#tuitionDetail-body").click("#tuitionModifyBtn", function(event){
        $("#tuitionDetail").modal('hide');

		let what = $(this).find("#tuitionModifyBtn").data("tuitionNo");
		let url = "/staff/tuitionPay/ajax/tuitionModifyView?what="+what;
		$.get(url)
			.done(function(resp){
				$(document).find("#tuitionModify-body").html(resp);				
			});
        
        $("#tuitionModify").modal('show');
        
    });
    
    $("#tuitionModify-close").click(function(){
        $("#tuitionModify").modal('hide');
    });
    
    $("#tuitionModify").on("hidden.bs.modal", function(){
		$(this).find("#tuitionModify-body").empty();
	});
    
    //------------------------------------------------------------------------------------------------     

	$("#tuitionModify-body").on("submit", "#tuitionModifyForm", function(e){
		e.preventDefault();
		
		$("#tutnEtrncf").val($("#inputEtrncf").val());
		$("#tutnTutfee").val($("#inputTutfee").val());
		$("#tutnPayde").val($("#inputDate").val());
		
		let data = $(this).serialize();
		
		let settings = {
			url : "/staff/tuitionPay/ajax/tuitionModify"
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


























