/**
 * <pre>
 * 
 * </pre>
 * @author 김재성
 * @since 2023. 11. 29.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 	  수정일    		    수정자       수정내용
 * --------------     --------    ----------------------
 * 2023. 11. 29.     	김재성       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){

        
   	// 온라인강의 추가 모달 ------------------------------------------------
		$("#addOnlineLectureModalViewBtn").on("click", function() {
		    $("#addOnlineLectureBtnModal").modal('show');
		});
		
		$("#addOnlineLectureBtnModalClose").click(function(){
		    $("#addOnlineLectureBtnModal").modal('hide');
		});
		
		$("#addOnlineLectureBtnModal").on('hidden.bs.modal', function () {
		    $("#dataCrTitle").val('');
		    $("#dataCrCn").val('');
			$("#dataCrSe").val("-1");
			$("#dataFile").val("");
		});
//--------------------------------------------------------------------------------------------------------
	// 온라인강의 업로드 작업
	$(document).on("submit","#onlineLectureClassroomForm",function(event){
		event.preventDefault();
		
	    let formData = new FormData($("#onlineLectureClassroomForm")[0]);
		
		console.log("내용 왔는지 확인 !",formData.get("crCn"));
		
		let onlineLectureFile = $("#onlineLectureFile");
		console.log(onlineLectureFile);
		let FileName = onlineLectureFile[0].files[0].name;
		console.log(FileName);
		
		if (onlineLectureFile == "" || onlineLectureFile == null) {
				Swal.fire({
				  icon: "error",
				  title: "영상파일을 선택해주세요.",
				  showConfirmButton: false,
				  timer: 2000
				});
				return false;
			}
		
		if (!confirm("업로드 하시겠습니까?")) {
			return;
		}
		
		// Ajax를 사용하여 데이터 전송
		$.ajax({
			url: "/professor/classroom/onlineLecture/new",
			type: "POST",
			contentType: false,
			processData : false,
			data: formData,
			dataType: "json",
			success: function(rslt) {
				if(rslt.result){
					Swal.fire({
						title: "등록 완료",
						text: "영상 등록 성공!",
						icon: "success"
					});
					
					location.reload();
				}
			},
			error: function(xhr, err, status) {
				// 에러 시 수행할 작업
				console.error(err);
				alert(xhr.status);
			}
		});
	});
 });
	

//엑셀 파일 업로드 ----------------------------------------------------
function checkFileType(fileName,targetMime) {
	if(fileName.indexOf(targetMime) == -1){
		return false;
	}else{
		return true;
	}
}





