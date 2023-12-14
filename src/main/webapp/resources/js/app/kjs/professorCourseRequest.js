/**
 * 
 */
/**

 * </pre>
 * @author 김재성
 * @since 2023. 11. 20.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 *  수정일         수정자       수정내용
 * --------     ------    ----------------
 * 2023.11.20.   김재성       최초작성
 * 2023.11.25.   김재성       모달 기능 및 swift alert 기능 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 

// 문서가 로드된 후 실행
document.addEventListener("DOMContentLoaded", function () {
    // 모달 열기 버튼 클릭 시 모달 창 열기
document.getElementById("openModal").addEventListener("click", function () {
    var modal = new bootstrap.Modal(document.getElementById("openModal"));
    modal.show();
});

// 모달 닫기 버튼 클릭 시 모달 창 닫기
document.querySelector(".close").addEventListener("click", function () {
    var modal = bootstrap.Modal.getInstance(document.getElementById("openModal"));
    modal.hide();
});

});


$(document).ready(function () {
    $('.class_list_wrap button').click(function () {
        var tab_id = $(this).attr('data-tab');

        // 모든 버튼과 콘텐츠에서 'active' 및 'current' 클래스를 제거합니다.
        $('.class_list_wrap button').removeClass('active');
        $('.tab-content').removeClass('current');

        // 클릭된 버튼에 'active' 클래스를 추가합니다.
        $(this).addClass('active');

        // 해당하는 탭 콘텐츠에 'current' 클래스를 추가합니다.
        $("#" + tab_id + '-content').addClass('current');
    });
});
 

 $(function(){

	function makeTrTag(courseRequest){
		let listTag = `
			<tr data-course_reqst_no="${courseRequest.courseReqstNo}" id="modal_open" data-toggle="modal" data-target="#modal_open">
				<td>${courseRequest.rnum}</td>
				<td>${courseRequest.clgNm}</td>
				<td>${courseRequest.courseReqstNm}</td>             
				<td>${courseRequest.complSe}</td>             
				<td>${courseRequest.coursePnt}</td>
				<td>${courseRequest.courseReqstDate}</td>             
		`; 
		let confm = courseRequest.confmSe ;
		
		if(confm=="승인 완료"){
            listTag +=`<td><span class="pstatus02">${confm}</span></td></tr>`;
        }else if(confm=="반려"){
        	 listTag +=`<td><span class="pstatus03">${confm}</span></td></tr>`;
        }else {
			listTag +=`<td><span class="pstatus01">${confm}</span></td></tr>`;
		} 
		
		
		return listTag;
	 };
 	

	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/professor/course/ajax/courseRequestList?${data}`, function(rslt){   // 요청 URL 설정 controller에 정해놓은거
			let courseRequestList = rslt.paging.dataList;
			
			let courseRequestTags = null;
			if(courseRequestList.length > 0){
				$.each(courseRequestList, function(idx, courseRequest){
					courseRequestTags += makeTrTag(courseRequest);
				});
				$(pagingArea).html(rslt.paging.pagingHTML);
			}else{
				courseRequestTags += `
					<tr>
						<td colspan="6" style="text-align: center;">개설 신청한 교과목 목록이 없습니다.</td>
					</tr>
				`;
				$(pagingArea).empty();
			}
			
			$("#listBody").html(courseRequestTags);
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
		
		
		// let courseReqstNo = $(listTag).data("courseReqstNo");
		let courseReqstNo = listTag.getAttribute("data-course_reqst_no");
		
		console.log("courseReqstNo : ",courseReqstNo);
		
		let url="/professor/course/ajax/courseRequestView?courseReqstNo="+courseReqstNo;
		
		$.get(url).done(function(rslt){
				$modal.find(".modal-body").html(rslt);
		});
		
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
    });


	//교과목 개설 신청
	$('#professorCourseRequestForm').on('submit',function(event){
		event.preventDefault();
		let data = $(this).serialize();
		
		//console.log("data 값 잘 들어오는지 확인 : ",data);
		let settings = {
			url:"/professor/course/new"
			, method:"POST"
			, data : data
			// , data : JSON.stringify(data)
			,dataType : 'json'
			// ,ContentType : 'application/json;charset=utf-8'
			, success : function(resp){
				let icon = null;
				let title = null;
				if(resp.success){
						title = "교과목 신청 성공!";
						icon = "success";
				}else{
						title ="잘못된 요청!!";
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
			, error : function(xhr, status, error){
				console.log(error);
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
};