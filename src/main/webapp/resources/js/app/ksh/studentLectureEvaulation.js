/**
 * <pre>
 * 강의평가 페이지에서 사용하는 스크립트
 * </pre>
 * @author 김석호
 * @since 2023. 11. 22.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일            수정자       수정내용
 * --------          --------    ----------------------
 * 2023. 11. 22.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

$lectureNamePlaceForLectureEvaulationModal = $('#lectureNamePlaceForLectureEvaulationModal');
$professorNamePlaceForLectureEvaulationModal = $('#professorNamePlaceForLectureEvaulationModal');
$lectureRequestModalFormStdntNo = $('#lectureRequestModalFormStdntNo');
$lectureRequestModalFormLctreNo = $('#lectureRequestModalFormLctreNo');
$('#lectureEvaulationModal').on('hidden.bs.modal',function(){
		$('#lectureRequestModalFormLctreNo').val('');
		$('#lectureRequestModalForm')[0].reset();
		console.log("모달 닫힘");
		$lectureNamePlaceForLectureEvaulationModal.empty();
		$professorNamePlaceForLectureEvaulationModal.empty();
});
$('.close').on('click',function(){
    $('#lectureEvaulationModal').modal('hide');
})

$(()=>{
//	console.log('현재학생정보',currStdntNo);
	$(document).on('click','.lectureEvaulationBtn',function(){
//		console.log("눌렀고, this는 : ",this);
		$lectureRequestModalFormStdntNo.val(currStdntNo);
		var $lectureEvaulationTargetTr = $(this).parents('tr');
		var lectureEvaulationTargetLctreNo = $lectureEvaulationTargetTr.data('lctreNo');
		$lectureRequestModalFormLctreNo.val(lectureEvaulationTargetLctreNo);
		var lectureEvaulationTargetCourseNm = $lectureEvaulationTargetTr.data('courseNm');
		$lectureNamePlaceForLectureEvaulationModal.html(lectureEvaulationTargetCourseNm);
		var lectureEvaulationTargetPrfsorNm = $lectureEvaulationTargetTr.data('prfsorNm');
		$professorNamePlaceForLectureEvaulationModal.html(lectureEvaulationTargetPrfsorNm);
		$('#lectureEvaulationModal').modal('show');
	});
	
	$('#lectureRequestModalForm').on('submit',function(e){
		e.preventDefault();
//		console.log('서브밋 이벤트 발생!',);
		var targetData = $(this).serializeJSON();
//		console.log(targetData);
		
		var lectureEvaulationAjaxSettings = {
			url : "/student/courseEvaluation"
			, method : "post"
			, data : targetData
			, dataType : "json"
			, success : function(resp){
				if(resp.result){
					Swal.fire({
						title: "강의평가 완료!",
						icon: "success",
						showConfirmButton: false,
               			timer: 1000
					});
					setTimeout(function() {
		                location.reload();
		            }, 1000);
				}else{
					Swal.fire({
						title: "잘못된 요청!!",
						icon: "error",
						showConfirmButton: false,
               			timer: 1000
					});
					setTimeout(function() {
		                location.reload();
		            }, 1000);
				}
				
			}
			, error : function(xhr, status, err){
				console.log(err);
			}
		};
		$.ajax(lectureEvaulationAjaxSettings);
	});
	
	$('#autoFillEvaulationBtn').on('click',function(){
		console.log("자동채우기 버튼 클릭됨");
		var $targetRadios = $('#lectureRequestModalForm').find('[type=radio]').filter('[value=5]');
		$targetRadios.prop('checked',true);
	});
	
});