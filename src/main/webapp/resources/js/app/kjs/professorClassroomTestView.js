/**
 * <pre>
 * 
 * </pre>
 * @author 김재성
 * @since 2023. 11. 27.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 	  수정일    		    수정자       수정내용
 * --------------     --------    ----------------------
 * 2023. 11. 27.     	김재성       최초작성
 * 2023. 11. 28.     	김석호       시험출제 함수 작성
 * 2023. 11. 29.     	김재성       시험출제 목록 리스트 조회
 * 2023. 11. 30.     	김재성       시험출제 상세 조회
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 

$(function(){
	$("#classroomLectureChoiceSelect").on("change",function(){
		let lctreNo = $(this).val();
		// 요청이 완료된 후에 실행할 함수
		function processTestList(testList) {

			let listTag = "";
			if (testList.length > 0) {
				$.each(testList, function(idx, testVoList) {
					console.log("왜안나올까 이게", testVoList);

					let str = testVoList.lecture.semstrSe;
					let year = str.substr(0, 4);
					let semstr = str.substr(4, 1);
					listTag += `
	                <tr>
	                    <td>${year}년</td>
	                    <td>${semstr}학기</td>
	                    <td>${testVoList.testSeNm}</td>
	                    <td>${testVoList.lecture.lctreSe}</td>
	                    <td>${testVoList.lecture.complSe}</td>
	                    <td>
	                        <button type="button" data-lctre-no="${testVoList.lecture.lctreNo}" data-test-se="${testVoList.testSe}" class="btn pro_btn_01 small_btn testDetailViewBtn"
 							>상세보기</button>
	                    </td>
	                </tr>
	            `;
				});
			} else {
				listTag += `
	            <tr>
	                <td colspan="6" style="text-align: center;">시험출제 목록이 없습니다.</td>
	            </tr>
	        `;
			}
			$("#listBody").html(listTag);
		}
		$.ajax({
			type: "get",
			url: `/professor/classroom/test/ajax/testList/${lctreNo}`,
			dataType: "json",
			success: function(rslt) {
				testList = rslt.testList;

				//console.log("왜안나올까 이게", testList);

				processTestList(testList); // 요청이 완료된 후에 실행할 함수 호출
			},
			error: function(xhr) {
				console.log(xhr.status);
			}
		});
	});
	//console.log("targetLectureNo 나와라 제발",lctreNo);
	
	//alert("pppp"+lctreNo);
    //$('#classroomLectureChoiceSelect').val(lctreNo);
	//  시험출제 목록 리스트 출력--------------------------------------------------------------
	
	
	
	
	
	
	
	
	// ----------------------------------- 시험출제 정보 View Modal ----------------------------------
	
	$(document).on("click",".testDetailViewBtn", function(event) {
		//event.preventDefault();
	    $("#testDetailViewModal").modal('show');
		let lctreNo = $(this).data("lctreNo");
		let testSe = $(this).data("testSe");
		//console.log("시험출제 상세정보 여는 함수에서 나오는 시험코드",testSe);
		console.log("시험출제 상세정보 여는 함수에서 나오는 강의번호",lctreNo);
		
		$.ajax({
			url: `/professor/classroom/test/testView/${lctreNo}/${testSe}`,
			type: "GET",
			contentType: false,
			processData : false,
			//data: formData,
			dataType: "html",
			success: function(rslt) {
				$(".testDetailViewModalbody").html(rslt);	
				
			},
			error: function(xhr, err, status) {
				// 에러 시 수행할 작업
				console.error(err);
				alert(xhr.status);
			}
		});
	});
	
	
	// 시험출제 상세내역 버튼 클릭 내역 보기
	$(document).on("show.bs.modal","#testInfoViewModal", function(event){
		let $modal = $(this);
		let trTag = event.relatedTarget;
		let targetLectureNo = $('#classroomLectureChoiceSelect').val();
		let url = `/professor/classroom/addTest/${targetLectureNo}`;
		$.get(url)
			.done(function(resp){
				$modal.find(".modal-body").html(resp);
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
	});
	

	
	
	
//-----------------------------------------------------------------------------------------
	//시험출제 입력 모달
	$(document).on("show.bs.modal","#testInsertModal", function(event){
			let $modal = $(this);
			let trTag = event.relatedTarget;
			let targetLectureNo = $('#classroomLectureChoiceSelect').val();
			let url = `/professor/classroom/addTest/${targetLectureNo}`;
			$.get(url)
				.done(function(resp){
					$modal.find(".modal-body").html(resp);
				});
			}).on("hidden.bs.modal", function(event){
				$(this).find(".modal-body").empty();
			});
	
	// 시험출제 모달 닫기 -----------------------------------------------
	$(document).on('click',"#testInsertModalClose",function(){
	    $("#testInsertModal").modal('hide');
	});    
	
	

    $(document).on('click',"#modal_openClose",function(){
        $("#modal_open").modal('hide');
    });    
	
	// 시험 및 답안 업로드 작업
	$(document).on("submit",'#testAddForm',function(e){
		e.preventDefault();
//		console.log('잘막힘');
	    let formData = new FormData($("#testAddForm")[0]);
		let excelFile = $("#answerFile");
		let testFile = $("#testFile");
//		console.log(excelFile);
//		console.log(testFile);
		let excelFileName = excelFile[0].files[0].name;
		console.log(excelFileName);
		
		let testFileName = testFile[0].files[0].name;
		console.log(testFileName);

		let lctreNo = $("#addTestLctreNo").val();
		console.log("lctreNo : " + lctreNo);
		
		//formData.append("lctreNo",lctreNo);
		
		if (excelFile == "" || excelFile == null) {
				//alert("답안파일을 선택해주세요.");
				Swal.fire({
				  icon: "error",
				  title: "답안파일을 선택해주세요.",
				  showConfirmButton: false,
				  timer: 2000
				});
				return false;
			} else if (!checkFileType(excelFileName,'xlsx')) {
				//alert("엑셀 파일만 업로드 가능합니다.");
				Swal.fire({
				  icon: "error",
				  title: "엑셀 파일만 업로드 가능합니다.",
				  showConfirmButton: false,
				  timer: 2000
				});
				return false;
			}
			
		if (testFile == "" || testFile == null) {
				//alert("시험문제파일을 선택해주세요.");
				Swal.fire({
				  icon: "error",
				  title: "시험문제파일을 선택해주세요.",
				  showConfirmButton: false,
				  timer: 2000
				});
				return false;
			} else if (!checkFileType(testFileName,'pdf')) {
				//alert("PDF 파일만 업로드 가능합니다.");
				Swal.fire({
				  icon: "error",
				  title: "PDF 파일만 업로드 가능합니다.",
				  showConfirmButton: false,
				  timer: 2000
				});
				return false;
			}
		
		/*if (!confirm("업로드 하시겠습니까?")) {
			
			return;
		}*/
	
		// Ajax를 사용하여 데이터 전송
		console.log("왔다");
		$.ajax({
			url: "/professor/classroom/test/new",
			type: "POST",
			contentType: false,
			processData : false,
			data: formData,
			dataType: "json",
			success: function(rslt) {	
				
				let testList = rslt.testList;
				
				let icon = null;
				let title = null;
				if(rslt.result){
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
					if(rslt.result){
						$('#classroomLectureChoiceSelect').trigger("change");
						$('#testInsertModalClose').trigger('click');
						$("#tab-3-content").trigger("click");
					}
				});
			},
			error: function(xhr, err, status) {
				// 에러 시 수행할 작업
				console.error(err);
				alert(xhr.status);
			}
		});
	});


}); //function(){} End

//엑셀 파일 업로드 ----------------------------------------------------
function checkFileType(fileName,targetMime) {
	if(fileName.indexOf(targetMime) == -1){
		return false;
	}else{
		return true;
	}
}







