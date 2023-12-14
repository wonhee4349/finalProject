/**
 * </pre>
 * @author 김재성
 * @since 2023. 12. 04.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 *  수정일         수정자       수정내용
 * --------     ------    ----------------
 * 2023.12.04.   김재성     최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
$(function() {
	
	// 성적관리에서 select로 강의 고른 강의코드
	let selectLctreNo = $("#scoreLectureChoiceSelect").val();
	
	// 탭 변환
	$('.class_list_wrap button').click(function () {
        var tab_id = $(this).attr('data-tab');
		
        $('.class_list_wrap button').removeClass('active');
        $('.tab-content').removeClass('current');
		
        $(this).addClass('active');
		
        $("#" + tab_id + '-content').addClass('current');
    });

	$("#scoreLectureChoiceSelect").on("change",function(e){
		e.preventDefault();
		let selectLecture = $(this).val();
		
		console.log("scoreObjection 선택된 강의 번호 : ",selectLecture);
		
		// 성적이의신청 리스트
		$.ajax({
            type:"get",
            url:`/professor/score/scoreObjectionList/${selectLecture}`,
            dataType:"html",	
            //async:false,    // 틀이 들어가야 데이타를 넣을 수 있으므로
            success: function(rslt){
                $("#scoreObjectionBody").html(rslt); 
            },
            error: function(xhr){
                console.log(xhr.status);
            }
        });
		
		//성적등록 수강학생 리스트
		$.ajax({
            type:"get",
            url:`/professor/score/scoreRegistrationList/${selectLecture}`,
            dataType:"html",	
            //async:false,    // 틀이 들어가야 데이타를 넣을 수 있으므로
            success: function(rslt){
                $("#scoreRegistrationBody").html(rslt); 
            },
            error: function(xhr){
                console.log(xhr.status);
            }
        });
	});
	
   	// 성적이의신청 모달 ------------------------------------------------
	$(document).on("click",".scoreObjectionDetail", function() {
		
	    let stdntNo = $(this).data("stdntNo");
		let lctreNo = $(this).data("lctreNo");
		console.log("학생번호 왜 안올까 확인 : ",stdntNo);
		
		$.ajax({
            type:"get",
            url:`/professor/score/scoreObjectionDetailView/${lctreNo}/${stdntNo}`,
            dataType:"html",	
            //async:false,    // 틀이 들어가야 데이타를 넣을 수 있으므로
            success: function(rslt){
                $(".scoreObjectionInfoViewModalbody").html(rslt); 
            },
            error: function(xhr){
                console.log(xhr.status);
            }
        });

		$("#scoreObjectionInfoViewModal").modal('show');
		
	});
	
	//성적이의신청 반려 업데이트
	$(document).on("submit","#refuseScoreObjectionForm", function(event){
        event.preventDefault();
		
		let content = $("#refuseScoreObjectionContent").val();
		
		console.log("반려사유 값이 오는지 안오는지 확인 : ",content);
		
		$("#scoreObjcReturnTextArea").val(content);
		
		if(!content){
			//alert("반려사유를 입력해주세요.");
			Swal.fire({
			  icon: "warning",
			  title: "반려 사유를 입력해주세요.",
			  showConfirmButton: false,
			  timer: 1500
			});
			return false;
		}
		
        let data = $(this).serialize();
		let settings = {
			url : "/professor/score/modifyRefuseScoreObjection"
			, method : "POST"
			, data : data
			, dataType : 'json'
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
					$('#refuseScoreObjectionModalClose').trigger('click');
					$("#tab-2-content").trigger("click");
					$('#scoreLectureChoiceSelect').trigger('change');
					$(".modal-backdrop").remove();
	            }, 1500);
			}
			, error : function(xhr, status, err){
				console.log(err);
					alert("잘못된 요청 발생!",xhr.status);
			}
		};
		$.ajax(settings);

	});
	
	// 성적이의신청 정정 업데이트
	$(document).on("submit","#resetScoreObjectionForm", function(event){
        event.preventDefault();
		
		let content = $("#resetScoreTextArea").val();
		
		//console.log("반려사유 값이 오는지 안오는지 확인 : ",content);
		
		$("#resetScoreObjectionContent").val(content);
		
		if(!content){
			//alert("반려사유를 입력해주세요.");
			Swal.fire({
			  icon: "warning",
			  title: "정정 사유를 입력해주세요.",
			  showConfirmButton: false,
			  timer: 3000
			});
			return false;
		}
		
        let data = $(this).serialize();
		let settings = {
			url : "/professor/score/modifyResetScoreObjection"
			, method : "POST"
			, data : data
			, dataType : 'json'
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
					$('#refuseScoreObjectionModalClose').trigger('click');
					$("#tab-2-content").trigger("click");
					$('#scoreLectureChoiceSelect').trigger('change');
					$(".modal-backdrop").remove();
	            }, 1500);
			}
			, error : function(xhr, status, err){
				console.log(err);
					alert("잘못된 요청 발생!",xhr.status);
			}
		};
		$.ajax(settings);

	});
	
	
	// 성적등록 모달 ------------------------------------------------
	$(document).on("click","#scoreRegistration", function() {
		
	    let stdntNo = $(this).data("stdntNo");
		let lctreNo = $(this).data("lctreNo");
		console.log("학생번호 왜 안올까 확인 : ",stdntNo);
		
		$.ajax({
            type:"get",
            url:`/professor/score/scoreRegistrationView/${lctreNo}/${stdntNo}`,
            dataType:"json",	
            //async:false,    // 틀이 들어가야 데이타를 넣을 수 있으므로
            success: function(rslt){
				let evlList = rslt.lctreEvlStdrList;
				
				let lctreNo = rslt.lctreNo;
				let stdntNo = rslt.stdntNo;
				
				$("#scoreRegistLctreNo").val(lctreNo);
				$("#scoreRegistStdntNo").val(stdntNo);
				
				console.log("혹시나 오는지 확인 해보기  : ",rslt.stdntNo);
				console.log("혹시나 오는지 확인 해보기  : ",rslt.lctreNo);
				
				console.log("evlList : ",evlList);
				
				newList = [];
				newList[0] = evlList[2]; 
				newList[1] = evlList[1]; 
				newList[2] = evlList[0]; 
				newList[3] = evlList[3]; 
				
				
				
				let trTag = `<tr>`;
				if(newList.length > 0){
	                $.each(newList, function(idx,evl){
							
						trTag += `
								<th>${evl.scoreSe}</th>
								<td>${evl.lctreEvlStdrReflct} %</td>
						`;
					});
				 trTag += `</tr>`;	
				}else{
	                trTag += `
	                    <tr>
	                    	<td colspan="2" style="text-align: center;">평가기준이 없습니다.</td>
	                    </tr>
	                `;
				}
				$("#lectureEvalutionStandardBody").html(trTag); 
            },
            error: function(xhr){
                console.log(xhr.status);
            }
        });
		
	});
	
	
	
	// 성적 등록
	$(document).on("submit","#scoreRegistrationForm", function(event){
        event.preventDefault();
		
		let lctreNo = $("#scoreRegistLctreNo").val();
		let stdntNo = $("#scoreRegistStdntNo").val();
		
		console.log("혹시나 오는지 확인 해보기  : ",stdntNo);
		console.log("혹시나 오는지 확인 해보기  : ",lctreNo);
				
        //let data = $(this).serialize();
		let formData = new FormData($("#scoreRegistrationForm")[0]);
		
		console.log("data 정보 체크 : ", formData);
		
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
					url: "/professor/score/scoreRegistration",
					type: "POST",
					contentType: false,
					processData : false,
					data: formData,
					dataType: "JSON",
					success: function(rslt) {
						let icon = null;
						let title = null;
						if(rslt.success){
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
							if(rslt.success){
								$("#scoreRegistrationModal").modal('hide');
								$('#scoreRegistrationModalClose').trigger('click');
								$('#scoreLectureChoiceSelect').trigger('change');
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
	
		// 자료 등록 상세내역 모달 닫기 -------
	$("#scoreRegistrationModalClose").click(function(){
	    $("#scoreRegistrationModal").modal('hide');
	});
	
});

// 성적이의신청 반려 자동입력
function ScoreRefuseAutoInput(){
	let refuseScoreObjectionContent = `
반가워요, 수업에서 제출하신 성적 이의신청에 대한 검토가 완료되었습니다. 안타깝게도 이의신청을 반려하게 되었으며, 이에 대한 이유를 안내드립니다.
이의신청에 대한 구체적인 내용 및 이유가 명확히 제시되지 않았습니다. 무엇에 대한 이의를 제기하는지 자세히 설명해 주셔야 합니다.`;
	
	$("#refuseScoreObjectionContent").val(refuseScoreObjectionContent);
}

// 성적이의신청 정정 자동입력
function ScoreResetAutoInput(){
	
	let resetMiddleScore = "99";
	let resetFinalScore = "99";
	let resetAssignmentScore = "99";
	let resetAttendanceScore = "99";
	let resetScoreTextArea =`열심히 수행한 학업에 대한 감사의 말씀을 전합니다.
성적 이의신청이 많았던 가운데 귀하의 신청이 정정되었습니다. 
귀하의 특정 과제나 시험 등에 대한 재평가를 통해 성적이 재조정되었으니 만족하시길 바랍니다.
`;
	
	$("#resetMiddleScore").val(resetMiddleScore);
	$("#resetFinalScore").val(resetFinalScore);
	$("#resetAssignmentScore").val(resetAssignmentScore);
	$("#resetAttendanceScore").val(resetAttendanceScore);
	$("#resetScoreTextArea").val(resetScoreTextArea);
}	

function ScoreRegistAutoInput(){
	let registMiddleScore = "99";
	let registFinalScore = "99";
	let registAssignmentScore = "99";
	let registAttendenceScore = "99";
	
	$("#registMiddleScore").val(registMiddleScore);
	$("#registFinalScore").val(registFinalScore);
	$("#registAssignmentScore").val(registAssignmentScore);
	$("#registAttendenceScore").val(registAttendenceScore);
}