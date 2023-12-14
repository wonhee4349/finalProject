/**
 * <pre>
 * 학생메뉴 수업 > 강의조회 페이지에서 사용하는 자바스크립트파일
 * </pre>
 * @author 김석호
 * @since 2023. 11. 10.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일       수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 10.      김석호       최초작성
 * 2023. 12. 01.      김석호       조회전용 요청으로 변경
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
$(()=>{
	$('#semesterCodeOptions').on('change',function(){
		var semesterOptionValue = $(this).val();
		if(semesterOptionValue){
			$.getJSON(`/student/letureList/${semesterOptionValue}`)
			.done((res)=>{
				var dataList = res.list;
				var ajaxResultCode = "";
				if(dataList?.length > 0 ){
					$.each(dataList , function(i,v){
						ajaxResultCode += makeLectureListTrTags(v);
					});
				}else{
					ajaxResultCode = "<tr class='noDataTrTag'><td colspan='6'>잘못된 요청이 발생했습니다</td></tr>";
				}
				$('#listbody').html(ajaxResultCode)
			})
			.fail((xhr)=>{
				console.log(xhr);
			});
		}else{
			$('#listbody').html("<tr class='noDataTrTag'><td colspan='6'>학기를 선택해주세요</td></tr>")
		}
	});
	let seletedTarget = $('#semesterCodeOptions option').last().val();
//	console.log("여기요",seletedTarget);
	$('#semesterCodeOptions').val(seletedTarget).trigger('change');
	
})

var makeLectureListTrTags = (lecture)=>{
	let tag = `
		<tr data-lctre-no="${lecture.lctreNo}" class="dataTrTag">
			<td>${lecture.courseNm}</td>
			<td>${lecture.complSeNm}</td>
			<td>${lecture.coursePnt}</td>
			<td>${lecture.prfsorNm}</td>
			<td>${lecture.lctreNmpr}</td>
			<td>${lecture.tmtbSeNm}</td>
		</tr>
	`;
	return tag;
}
