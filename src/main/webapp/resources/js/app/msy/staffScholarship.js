/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 10.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 10.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	 
	//------------------------------------------------------------------------------------------------
 	
 	function makeTrTag(scholarship){
		let trTag = `
			<tr data-scholarship-no="${scholarship.schlshipNo}" id="open_modal" data-toggle="modal" data-target="#modal_open">
                <td>${scholarship.rnum}</td>
                <td>${scholarship.semstrYear}년</td>
                <td>${scholarship.semstrNo}학기</td>
                <td>${scholarship.scholarshipList.schoNm}</td>       
                <td>${scholarship.pymntSe}</td>
            </tr>
		`; 
		return trTag;
	 };
	 
	//------------------------------------------------------------------------------------------------
 	
 	function makeTotalTrTag(scholarshipList){
		let totalTrTag = `
			<tr>
                <td>${scholarshipList.rnum}</td>
                <td>${scholarshipList.schoNm}</td>
            </tr>
		`; 
		return totalTrTag;
	 };
	 
	//------------------------------------------------------------------------------------------------
	
	$("#searchTotalForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/staff/scholarship/ajax/totalScholarshipList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
            let totalScholarshipList = resp.paging.dataList;
            let trTags = null;
            if(totalScholarshipList.length > 0){
                $.each(totalScholarshipList, function(idx, totalScholarship){
                    trTags += makeTotalTrTag(totalScholarship);
                });
                $(totalPagingArea).html(resp.paging.pagingHTML);
            }else{
                trTags += `
                    <tr>
                    	<td colspan="2" style="text-align: center;">등록된 장학금이 없습니다.</td>
                    </tr>
                `;
                $(totalPagingArea).empty();
            }
            $("#listTotalBody").html(trTags);
        });
	}).submit();
	
	//------------------------------------------------------------------------------------------------
	 $("#searchForm").on("submit", function(event){
        event.preventDefault();
        let data = $(this).serialize();
        $.getJSON(`/staff/scholarship/ajax/scholarshipList?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
            let scholarshipList = resp.paging.dataList;
            let trTags = null;
            if(scholarshipList.length > 0){
                $.each(scholarshipList, function(idx, scholarship){
                    trTags += makeTrTag(scholarship);
                });
                $(pagingArea).html(resp.paging.pagingHTML);
            }else{
                trTags += `
                    <tr>
                    	<td colspan="5" style="text-align: center;">등록된 장학금이 없습니다.</td>
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
		let what = $(trTag).data("scholarshipNo");
		let url = "/staff/scholarship/ajax/scholarshipView?what="+what;
		$.get(url)
			.done(function(resp){
				$modal.find(".modal-body").html(resp);
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
	});
	
	
	$("#insertTotalBtn").on("click", function(){
		$("#totalScholarshipInsertModal").modal('show');
	 });
	 
    $(".close").click(function(){
        $("#modal_open").modal('hide');
    });
    
    $("#totalScholarshipInsertModal-close").click(function(){
        $("#totalScholarshipInsertModal").modal('hide');
    });
    
    //------------------------------------------------------------------------------------------------
           
    $('.class_list_wrap button').click(function () {
        var tab_id = $(this).attr('data-tab');
        $('.class_list_wrap button').removeClass('active');
        $('.tab-content').removeClass('current');
        $(this).addClass('active');
        $("#" + tab_id).addClass('current');
    });
    
    //------------------------------------------------------------------------------------------------
    
    $("#insertTotalScholarshipForm").on("submit", function(e){
		e.preventDefault();
		let scholarshipNm = $("#scholarshipNm").val();
		$("#insertScholarshipName").val(scholarshipNm);
		let data = $(this).serialize();
		let settings = {
			url : "/staff/scholarship/insertTotalScholarship"
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
    
    //------------------------------------------------------------------------------------------------
      
    $('#totalScholarshipInsertModal').on('hidden.bs.modal', function () {
		$("#scholarshipNm").val('');
        $("#insertScholarshipName").val('');
    });
          
    //------------------------------------------------------------------------------------------------
        
    $("#scholarshipInsertModal").on("show.bs.modal", function(event){
		let currentYear = new Date().getFullYear();
		let $modal = $(this);
		let trTag = event.relatedTarget;
		let url = "/staff/scholarship/ajax/scholarshipInsertView";
		$.get(url)
			.done(function(resp){
				$modal.find(".scholarshipInsertModal-body").html(resp);
				$("#inputSemstrYear").val(currentYear);
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".scholarshipInsertModal-body").empty();
	});
	
           
	$(".scholarshipInsertModal").click(function(){
        $("#scholarshipInsertModal").modal('hide');
    });
    
    $("#scholarshipInsertBtn").click(function(){
		$("#scholarshipInsertModal").modal('show');
	 });
	 
	$('#scholarshipInsertModal').on('hidden.bs.modal', function () {
		$("#scholarshipNm").val('');
        $("#insertScholarshipName").val('');
    });
	 
	//------------------------------------------------------------------------------------------------
    
    $("#scholarshipInsertForm").on("submit", function(e){
		e.preventDefault();
		let inputScholarship = $("#inputScholarship").val();
		let inputSemstrNo = $("#inputSemstrNo").val();
		let inputPymntSe = $("#inputPymntSe").val();
		let inputSemstrYear = $("#inputSemstrYear").val();
		let inputSchlshipAmount = $("#inputSchlshipAmount").val();
		let inputSchlshipTrgter = $("#inputSchlshipTrgter").val();
		let inputSchlshipSelectn = $("#inputSchlshipSelectn").val();
		let inputStndtReq = $("#inputStndtReq").val();
		
		$("#schlshipNo").val(inputScholarship);
		$("#semstrNo").val(inputSemstrYear+inputSemstrNo);
		$("#pymntSe").val(inputPymntSe);
		$("#schlshipAmount").val(inputSchlshipAmount);
		$("#schlshipTrgter").val(inputSchlshipTrgter);
		$("#schlshipSelectn").val(inputSchlshipSelectn);
		$("#stndtReq").val(inputStndtReq);
		
		let data = $(this).serialize();
		
		let settings = {
			url : "/staff/scholarship/insertScholarship"
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
					icon: icon
				}).then((res)=>{
					if(resp.success){
						location.reload();
					}
				});
			}
			, error : function(xhr, status, err){
				console.log(err);
					alert("잘못된 요청 발생!");
			}
		};
		$.ajax(settings)
	});
    
 });
 function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
}

function insertContent(){
	
	$("#inputScholarship").val("SC002");
	$("#inputSemstrYear").val("2024");
	$("#inputSemstrNo").val("1");
	$("#inputPymntSe").val("01");
	$("#inputSchlshipAmount").val(3000000);
	$("#inputStndtReq").val("N");
	$("#inputSchlshipTrgter").val("재학생");
	$("#inputSchlshipSelectn").val("졸업예정자가 아니면서 학과에서 수석인 학생. F학점이 있는경우 제외");
}