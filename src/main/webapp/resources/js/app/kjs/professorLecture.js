/**
 * 
 */
/**

 * </pre>
 * @author 김재성
 * @since 2023. 11. 10.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 *  수정일         수정자       수정내용
 * --------     ------    ----------------
 * 2023.11.10.   김재성       최초작성
 * 2023.11.13.   김재성       강의내역 출력
 * 2023.11.14.   김재성       강의내역 수정
 * 2023.11.15.   김재성       강의내역 검색 수정
 * 2023.11.17.   김재성       강의 상세보기
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

$(function() {

	function makeTags(lecture) {
		let time = lecture.timetable;
		let tdTags = "";
		tdTags = `
			<tr data-lctre-no="${lecture.lctreNo}">
				<td>
				${lecture.semstrYr}년
				</td>
				<td>${lecture.semstr} 학기</td>
				<td>${lecture.courseNm}</td>
				`;
		if (lecture.buldNm != null && lecture.fcltsNm != null) {
			tdTags += `<td>${lecture.buldNm} (${lecture.fcltsNm})</td>`;
		}else if(lecture.buldNm ==null){
			tdTags += `<td>강의실 없음</td>`;
		}
		tdTags += `<td>`;
		
		if (time.length == 0) {
			tdTags += `시간표 없음`;
		} else {
			for (let i = 0; i < time.length; i++) {
				tdTags += `${time[i].tmtbSe}`;
				if (i < time.length - 1) {
	                tdTags += ', ';  // 각 시간표 항목 사이에 쉼표를 추가합니다.
	            }
			}
		}
		tdTags += `</td>`;
		tdTags += `
					<td>${lecture.lctreSe}</td>
					<td>${lecture.complSe}</td>
					<td>
						<button type="button" id="modal_open" data-toggle="modal" 
						data-target="#modal_open" class='btn btn-info'
						data-lctre-no="${lecture.lctreNo}">
						학생목록
						</button>
					</td> 
			   </tr>
			`;
		return tdTags;
	};

	$("#searchForm").on("submit", function(event) {
		event.preventDefault();
		let data= $("#searchForm").serialize();
		$.getJSON(`/professor/lecture/ajax/lectureList?${data}`, function(rslt) {
			
			console.log("재성 체킁:",rslt);
			
			let lectureList = rslt.paging.dataList;
			
			console.log("강의내역 리스트 : ", lectureList);
			
			let lectureTags = null;
			if (lectureList.length > 0) {
				$.each(lectureList, function(idx, lecture) {
					lectureTags += makeTags(lecture);
				});
				$(pagingArea).html(rslt.paging.pagingHTML);
			} else {
				lectureTags += `
					<tr>
						<td colspan="8" style="text-align: center;">강의중인 내역이 없습니다.</td>
					</tr>
				`;
				$(pagingArea).empty();
			}
			$("#listBody").html(lectureTags);
		});
		return false;
	}).submit();


	$("#searchUI").on("click", "#searchBtn", function(event) {

		let selectName = $("#searchSelect").attr("name");
		let selectValue = $("#searchSelect").val();
		let inputs = $(this).parents("#searchUI").find("input[name=searchWord]");
		let name = inputs.attr("name");
		let value = inputs.val();

		console.log("name , value 값 : ", name, value);
		$(searchForm).find(`:input[name=${name}]`).val(value);
		$(searchForm).find(`:input[name=${selectName}]`).val(selectValue);

		console.log("name , value 값 : ", name, value, selectName, selectValue);
		$("#searchForm").submit();
		//console.log("내려옴");
	});
	
	
	// 학생목록 리스트 
	$("#modal_open").on("show.bs.modal", function(event) {
		let $modal = $(this);
		let tdTags = event.relatedTarget;
		let lctreNo = $(tdTags).data("lctreNo");
		
		console.log("lctreNo : ", lctreNo);

		let url = "/professor/lecture/ajax/lectureStudentListView?lctreNo="+lctreNo;

		$.get(url).done(function(rslt) {

			$modal.find(".modal-body").html(rslt);
			//console.log("rslt 돌아온 값 : ", rslt);
		});

	}).on("hidden.bs.modal", function(event) {
		$(this).find(".modal-body").empty();
	});
	
	
	$("#modal_close").click(function(){
	    $("#modal_open").modal('hide');
	});

});

function fn_paging(page) {
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
};