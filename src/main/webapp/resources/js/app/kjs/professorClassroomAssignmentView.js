/**
 * </pre>
 * @author 김재성

 * @since 2023. 11. 28.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 	  수정일    		    수정자       수정내용
 * --------------     --------    ----------------------
 * 2023. 11. 28.     	김재성       최초작성
 * 2023. 11. 30.     	김재성       과제등록 목록 리스트 
 * 2023. 12. 01.     	김재성       과제등록 상세내역  
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){


//  과제등록 목록 리스트 출력--------------------------------------------------------------

let lctreNo = $("#selectLctreNoUsingData").val();
console.log("targetLectureNo 나와라 제발",lctreNo);

$.ajax({
    type:"get",
    url:`/professor/classroom/test/ajax/assignmentList/${lctreNo}`,
    dataType:"json",
    success: function(rslt){
		 let assignmentList = rslt.assignmentList;

		console.log("클래스룸 과제 목록",assignmentList);
		 
		 let listTag =""; 
			if(assignmentList.length > 0){
				$.each(assignmentList, function(idx, assignmentVoList){
					//console.log("클래스룸 과제 목록",assignmentVoList);
					
					let str =  assignmentVoList.lecture.semstrSe;
					let year = str.substr(0,4);
					let semstr = str.substr(4,1);
					listTag += `
						<tr>
				            <td>${year}년</td>
				            <td>${semstr}학기</td>
				            <td style="text-align:left">${assignmentVoList.crTitle}</td>
							<td>${assignmentVoList.lecture.lctreSe}</td>
				            <td>${assignmentVoList.lecture.complSe}</td>
				            <td>${assignmentVoList.crDate}</td>
						<td>
							<button type="button" class="btn pro_btn_01 small_btn assignmentInfoBtn" 
							data-cr-no=${assignmentVoList.crNo} data-toggle="modal" data-target="">상세보기</button>
						</td>
						</tr>
				`;
				});
			}else{
				listTag += `
					<tr>
						<td colspan="7" style="text-align: center;">과제등록 목록이 없습니다.</td>
					</tr>
				`;
			}
			$("#assingmentListBody").html(listTag);	
    },
    error: function(xhr){
        console.log(xhr.status);
    }
});

   // ----------------------------------- 과제등록 정보 View Modal ----------------------------------
	
	$(document).on("click",".assignmentInfoBtn", function(event) {
		//event.preventDefault();
	    $("#assignmentInfoViewModal").modal('show');
		
		let crNo = $(this).data("crNo");
		console.log("클래스룸 번호 확인 : ",crNo);
		
		$.ajax({
			url: `/professor/classroom/assignment/view/${crNo}`,
			type: "GET",
			contentType: false,
			processData : false,
			//data: formData,
			dataType: "html",
			success: function(rslt) {
				$(".assignmentInfoViewModalbody").html(rslt);	
				
			},
			error: function(xhr, err, status) {
				// 에러 시 수행할 작업
				console.error(err);
				alert(xhr.status);
			}
		});
	});
	
	// 과제등록 상세내역  모달 닫기 ---------------------
	$("#assignmentInfoViewModalClose").click(function(){
		   $("#assignmentInfoViewModal").modal('hide');
	});
	
        
   	// 과제추가 모달 ------------------------------------------------
		$("#addAssignmentModalViewBtn").on("click", function() {
		    $("#addAssignmentBtnModal").modal('show');
		});
		
		$("#addAssignmentBtnModalClose").on("click",function(){
		    $("#addAssignmentBtnModal").modal('hide');
		});
		
		$("#addAssignmentBtnModal").on('hidden.bs.modal', function () {
		    $("#assignmentCrTitle").val('');
		    $("#assignmentCrCn").val('');
			$("#assignmentCrSe").val("-1");
			$("#assignmentFile").val("");
		});
//--------------------------------------------------------------------------------------------------------
	// 과제 업로드 작업
	$(document).on("submit","#addAssignmentClassroomForm",function(event){
		event.preventDefault();
		
		console.log('잘막힘');
	    let formData = new FormData($("#addAssignmentClassroomForm")[0]);
		
		console.log("내용 왔는지 확인 !",formData.get("crCn"));
		
		let assignmentFile = $("#assignmentFile");
		console.log(assignmentFile);
		let FileName = assignmentFile[0].files[0].name;
		console.log(FileName);
		
		if (assignmentFile == "" || assignmentFile == null) {
				//alert("답안파일을 선택해주세요.");
				Swal.fire({
				  icon: "error",
				  title: "자료파일을 선택해주세요.",
				  showConfirmButton: false,
				  timer: 2000
				});
				return false;
			}
		
		Swal.fire({
		  title: "등록 하시겠습니까?",
		  icon: "question",
		  showCancelButton: true,
		  confirmButtonColor: "#3085d6",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "네",
		  cancelButtonText: "아니오"
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url: "/professor/classroom/assignment/new",
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
								$("#addAssignmentBtnModal").modal('hide');
								$('addAssignmentBtnModalClose').trigger('click');
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
			url: "/professor/classroom/assignment/new",
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
						$('#classroomLectureChoiceSelect').trigger('change');
						$('#addAssignmentBtnModalClose').trigger('click');
					}
				});
				
				
				
				if(rslt.result){
				var timerInterval;
					Swal.fire({
						title: "과제 등록 성공!",
						html: "과제 등록 성공!",
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
						$('#addAssignmentBtnModalClose').trigger('click');
						
					});
				}else{
					var timerInterval;
					Swal.fire({
						title: "과제 등록 실패",
						html: "과제 등록 실패!",
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
		});*/
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



// 과제 일괄 입력
function assignmentAutoInput(){
	
	let assignmentCrTitle = "객체지향 프로그래밍 나만의 학생 목록 만들기";
	let assignmentCrCn =`안녕하세요! 이번 과제에서는 나만의 학생 목록을 만들어보는 것이 목표입니다.
여러분은 자바 프로그래밍을 활용하여 간단한 학생 관리 시스템을 만들어 볼 거에요.

과제 요구사항으로는
1.학생 클래스 만들기: Student 클래스를 정의하여 학생의 이름, 학번, 성별, 전화번호, 성적 등을 표현해보세요.
2.학생 목록 관리 클래스 만들기: 학생 객체들을 관리하는 클래스를 만들어보세요. 여기서 컬렉션을 활용해보면 좋아요.
3.사용자와 소통하기: 사용자에게 간단한 명령을 받아서, 학생 목록을 추가하거나 조회하는 등의 기능을 구현해보세요.
4.컬렉션 사용: ArrayList 또는 다른 적절한 컬렉션을 사용하여 여러 학생을 효율적으로 관리해보세요.
5.추가적인 기능 (선택 사항): 시간이 남으면 다음과 같은 추가적인 기능을 넣어보세요.

너무 부담가지지 마시고 편안하게 공부한다 생각하며 준비해보면 좋을거같네요.
`;
	
	$("#assignmentCrTitle").val(assignmentCrTitle);
	$("#assignmentCrCn").val(assignmentCrCn);
}

