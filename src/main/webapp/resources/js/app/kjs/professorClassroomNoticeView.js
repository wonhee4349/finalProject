/**
 * </pre>

 * @author 김재성
 * @since 2023. 12. 01.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 	  수정일    		    수정자       수정내용
 * --------------     --------    ----------------------
 * 2023. 12. 01.     	김재성       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	let lctreNo = $("#selectLctreNoUsingData").val();
	//console.log("targetLectureNo 나와라 제발",lctreNo);
	
	$.ajax({
	    type:"get",
	    url:`/professor/classroom/test/ajax/notice/${lctreNo}`,
	    dataType:"json",
	    success: function(rslt){
			 let noticeList = rslt.noticeList;
	
			 console.log("클래스룸 공지사항 목록",noticeList);
			 
			 let listTag =""; 
				if(noticeList.length > 0){
					$.each(noticeList, function(idx, noticeList){
					  	
						let str = noticeList.lecture.semstrSe;
			            let year = str.substr(0, 4);
			            let semstr = str.substr(4, 1);
						
						listTag += `
							<tr >
								<td>${year}년</td>
	                   			<td>${semstr}학기</td>
					            <td>${noticeList.crTitle}</td>
								<td>${noticeList.lecture.lctreSe}</td>
					            <td>${noticeList.lecture.complSe}</td>
					            <td>${noticeList.crDate}</td>
							<td>
								<button type="button" data-cr-no=${noticeList.crNo} 
									class="btn pro_btn_01  small_btn noticeInfoBtn" data-toggle="modal" 
										data-target="">상세보기</button>
							</td>
							</tr>
					`;
					});
				}else{
					listTag += `
						<tr>
							<td colspan="7" style="text-align: center;">공지사항 목록이 없습니다.</td>
						</tr>
					`;
				}
				$("#noticeListBody").html(listTag);	
	    },
	    error: function(xhr){
	        console.log(xhr.status);
	    }
	});
	
	// ----------------------------------- 시험출제 정보 View Modal ----------------------------------
	
	$(document).on("click",".noticeInfoBtn", function(event) {
		//event.preventDefault();
	    $("#noticeInfoViewModal").modal('show');
		
		let crNo = $(this).data("crNo");
		console.log("클래스룸 번호 확인 : ",crNo);
		//console.log("시험출제 상세정보 여는 함수에서 나오는 강의번호",lctreNo);
		
		$.ajax({
			url: `/professor/classroom/notice/view/${crNo}`,
			type: "GET",
			contentType: false,
			processData : false,
			//data: formData,
			dataType: "html",
			success: function(rslt) {
				$(".noticeInfoViewModalbody").html(rslt);	
				
			},
			error: function(xhr, err, status) {
				// 에러 시 수행할 작업
				console.error(err);
				alert(xhr.status);
			}
		});
	});
        
	// 공지사항 추가 모달 ------------------------------------------------
	$("#addNoticeModalViewBtn").on("click", function() {
	    $("#addNoticeBtnModal").modal('show');
	});
	
	$("#addNoticeBtnModalClose").click(function(){
	    $("#addNoticeBtnModal").modal('hide');
	});
	
	$("#addNoticeBtnModal").on('hidden.bs.modal', function () {
	    $("#NoticeCrTitle").val('');
	    $("#NoticeCrCn").val('');
		$("#NoticeCrSe").val("-1");
		$("#NoticeFile").val("");
	});
//--------------------------------------------------------------------------------------------------------
	// 공지사항 업로드 작업
	$(document).on("submit","#noticeClassroomForm",function(event){
		event.preventDefault();
		
	    let formData = new FormData($("#noticeClassroomForm")[0]);
		
		//console.log("내용 왔는지 확인 !",formData.get("crCn"));
		
		let noticeFile = $("#noticeFile");
		console.log(noticeFile);
		let FileName = noticeFile[0].files[0].name;
		console.log(FileName);
		
		if (noticeFile == "" || noticeFile == null) {
				//alert("답안파일을 선택해주세요.");
				Swal.fire({
				  icon: "error",
				  title: "공지사항 파일을 선택해주세요.",
				  showConfirmButton: false,
				  timer: 2000
				});
				return false;
			}
		/*
		if (!confirm("업로드 하시겠습니까?")) {
			return;
		}
		*/
		
		Swal.fire({
		  title: "업로드 하시겠습니까?",
		  icon: "question",
		  showCancelButton: true,
		  confirmButtonColor: "#3085d6",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "네",
		  cancelButtonText: "아니오"
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url: "/professor/classroom/notice/new",
					type: "POST",
					contentType: false,
					processData : false,
					data: formData,
					dataType: "json",
					success: function(rslt) {
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
					icon: icon,
					showConfirmButton: false,
               		timer: 1500
						}).then((res)=>{
							if(rslt.result){
								$("#addNoticeBtnModal").modal('hide');
								$('#addAssignmentBtnModalClose').trigger('click');
								$('#classroomLectureChoiceSelect').trigger('change');
								$(".modal-backdrop").remove();
							}
						});
						
					},
					error: function(xhr, err, status) {
						// 에러 시 수행할 작업
						console.error(err);
						alert(xhr.status);
					}
				});
			}
		});
		
		/*
		// Ajax를 사용하여 데이터 전송
		$.ajax({
			url: "/professor/classroom/notice/new",
			type: "POST",
			contentType: false,
			processData : false,
			data: formData,
			dataType: "json",
			success: function(rslt) {
				if(rslt.result){
				var timerInterval;
					Swal.fire({
						title: "공지사항 등록 성공!",
						html: "공지사항 등록 성공!",
						timer: 1500,
						timerProgressBar: true,
						didOpen: () => {
							Swal.showLoading();
							const timer = Swal.getPopup().querySelector("b");
							timerInterval = setInterval(() => {
							timer.textContent = `${Swal.getTimerLeft()}`;
							}, 100);
						},
						willClose: () => {
							clearInterval(timerInterval);
						}
					}).then((result) => {
						$('#classroomLectureChoiceSelect').trigger('change');
						$('.close').trigger('click');
					});
				}else{
					var timerInterval;
					Swal.fire({
						title: "공지사항 등록 실패",
						html: "공지사항 등록 실패!",
						timer: 1500,
						timerProgressBar: true,
						didOpen: () => {
							Swal.showLoading();
							const timer = Swal.getPopup().querySelector("b");
							timerInterval = setInterval(() => {
								timer.textContent = `${Swal.getTimerLeft()}`;
							}, 100);
						},
						willClose: () => {
							clearInterval(timerInterval);
						}
					}).then((result) => {
					});
				}
			},
			error: function(xhr, err, status) {
				// 에러 시 수행할 작업
				console.error(err);
				alert(xhr.status);
			}
		});
		*/
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





