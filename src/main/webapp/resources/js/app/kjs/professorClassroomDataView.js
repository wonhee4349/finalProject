/**
 * </pre>

 * @author 김재성
 * @since 2023. 12. 01.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 	  수정일    		    수정자       수정내용
 * --------------     --------    ----------------------
 * 2023. 12. 01.     	김재성       최초 작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	

	
	
	// -- 자료 등록 리스트 출력 ------------------------------------------
	let lctreNo = $("#selectLctreNoUsingData").val();
	//console.log("targetLectureNo 나와라 제발",lctreNo);
	
	$.ajax({
	    type:"get",
	    url:`/professor/classroom/test/ajax/data/${lctreNo}`,
	    dataType:"json",
	    success: function(rslt){
			 let dataList = rslt.dataList;
	
			 console.log("클래스룸 자료 목록",dataList);
			 
			 let listTag =""; 
				if(dataList.length > 0){
					$.each(dataList, function(idx, dataList){
						//console.log("클래스룸 자료 목록",dataList);
					    let str =  dataList.lecture.semstrSe;
						let year = str.substr(0,4);
						let semstr = str.substr(4,1);
						listTag += `
							<tr >
					            <td>${year}년</td>
					            <td>${semstr}학기</td>
					            <td style="text-align:left">${dataList.crTitle}</td>
								<td>${dataList.lecture.lctreSe}</td>
					            <td>${dataList.lecture.complSe}</td>
					            <td>${dataList.crDate}</td>
							<td>
								<button type="button" data-cr-no=${dataList.crNo} 
									class="btn pro_btn_01 small_btn dataInfoBtn" data-toggle="modal"
								 		data-target="dataInfoViewModal">상세보기</button>
							</td>
							</tr>
					`;
					});
				}else{
					listTag += `
						<tr>
							<td colspan="7" style="text-align: center;">자료 목록이 없습니다.</td>
						</tr>
					`;
				}
				$("#dataListBody").html(listTag);	
	    },
	    error: function(xhr){
	        console.log(xhr.status);
	    }
	});
	
	
   // ----------------------------------- 시험출제 정보 View Modal ----------------------------------
	
	$(document).on("click",".dataInfoBtn", function(event) {
		//event.preventDefault();
	    $("#dataInfoViewModal").modal('show');
		
		let crNo = $(this).data("crNo");
		console.log("클래스룸 번호 확인 : ",crNo);
		//console.log("시험출제 상세정보 여는 함수에서 나오는 강의번호",lctreNo);
		
		$.ajax({
			url: `/professor/classroom/data/view/${crNo}`,
			type: "GET",
			contentType: false,
			processData : false,
			//data: formData,
			dataType: "html",
			success: function(rslt) {
				$(".dataInfoViewModalbody").html(rslt);	
				
			},
			error: function(xhr, err, status) {
				// 에러 시 수행할 작업
				console.error(err);
				alert(xhr.status);
			}
		});
	});
	
	// 자료 등록 상세내역 모달 닫기 -------
	$("#dataInfoViewModalClose").click(function(){
	    $("#dataInfoViewModal").modal('hide');
	});
	
	
        
	// 자료 추가 모달 ------------------------------------------------
	$("#addDataModalViewBtn").on("click", function() {
	    $("#addDataBtnModal").modal('show');
	});
	
	$("#addDataBtnModalClose").on("click",function(){
	    $("#addDataBtnModal").modal('hide');
	});
	
	$("#addDataBtnModal").on('hidden.bs.modal', function () {
	    $("#dataCrTitle").val('');
	    $("#dataCrCn").val('');
		$("#dataCrSe").val("-1");
		$("#dataFile").val("");
	});
//--------------------------------------------------------------------------------------------------------
	// 자료 업로드 작업
	$(document).on("submit","#dataClassroomForm",function(event){
		event.preventDefault();
		
	    let formData = new FormData($("#dataClassroomForm")[0]);
		
		console.log("내용 왔는지 확인 !",formData.get("crCn"));
		
		let dataFile = $("#dataFile");
		console.log(dataFile);
		let FileName = dataFile[0].files[0].name;
		console.log(FileName);
		
		if (dataFile == "" || dataFile == null) {
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
		  title: "등록하시겠습니까?",
		  icon: "question",
		  showCancelButton: true,
		  confirmButtonColor: "#3085d6",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "네",
		  cancelButtonText: "아니오"
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url: "/professor/classroom/data/new",
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
							/*	Swal.close();*/
								$("#addDataBtnModal").modal('hide');
								$('#addDataBtnModalClose').trigger('click');
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


// 일괄 입력 버튼
function dataAutoInput(){
	let dataCrTitle = "Java 프로그래밍 기초강의 자료 파일 올려드립니다.";
	
	let dataCrCn = `반갑습니다 학생 여러분, 프로그래밍을 배우는데 있어 꼭 필요하고 꼭 익혀야 하며 기본 중에 
기본인 프로그래밍 기초에 대해 설명 해놓은 파일을 업로드 해드리니 , 모두들 다운 받아 꼭 숙지하시길 바랍니다.
혹시 궁금한점 있다면 쪽지 남겨준다면 시간 되는대로 성심 성의껏 쪽지 답변 드릴테니 수업 꼭 참여하시고 적극적인 학업을 보여주세요.
`;
	$("#dataCrTitle").val(dataCrTitle);
	$("#dataCrCn").val(dataCrCn);
}


