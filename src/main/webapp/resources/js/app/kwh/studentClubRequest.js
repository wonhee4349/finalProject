/**
 * <pre>
 * 
 * </pre>
 * @author 김원희
 * @since 2023. 11. 15.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 24.      김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */


$(function() {

	$("#clubForm").on("submit", function(event) {
		event.preventDefault(); // 우리가 준 이벤트가 아니공, 원래가지고 있는 거 막을 때

		var formData = new FormData(document.querySelector('#clubForm'));

		// FormData를 JSON 객체로 변환합니다.
		var jsonData = {};
		formData.forEach(function(value, key) {
			jsonData[key] = value;
		});

		// JSON 객체를 문자열로 변환합니다.
		var jsonString = JSON.stringify(jsonData);

		/*$.ajax({  
			url : "/student/club/ajax/clubOpenRequest",
			method : "POST",
			contentType : "application/json",  // get방식에는 사용하지 않음
			dataType : "text",
			data: jsonString,
			success:function(resp){
						let icon = null;
				let title = null;
				if(resp.success){
					title = "동아리 개설 신청 완료!";
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
					location.href = "/student/club/clubRequestList";
				}, 1500);
			}
		   , error:function(xhr){
				console.log("Error:",xhr.status);
			}
		})
*/
		$.ajax({
			url: "/student/club/ajax/clubOpenRequest",
			method: "POST",
			contentType: "application/json",
			dataType: "text", // "json"에서 "text"으로 변경
			data: jsonString,
			success: function(resp) {
				let title = "동아리 개설 신청 완료!";
				let icon = "success";

				// 파싱된 JSON 객체로 변경
				try {
					resp = JSON.parse(resp);
				} catch (e) {
					console.error("JSON 파싱 오류:", e);
				}

				if (resp.success !== undefined && !resp.success) {
					title = "실패했습니다!";
					icon = "error";
				}

				Swal.fire({
					title: title,
					icon: icon,
					showConfirmButton: false,
					timer: 1500
				});
				// 성공 또는 실패 상태에 상관없이 리다이렉트 수행
				setTimeout(function() {
					location.href = "/student/club/clubRequestList";
				}, 1500);

				if (resp.success) {
					setTimeout(function() {
						location.href = "/student/club/clubRequestList";
					}, 1500);
				}
			},
			error: function(xhr) {
				console.log("Error:", xhr.status);
				console.log(xhr.responseText);
			}
		});



	});



	//단과대학 선택 된 대학 학과 리스트 select 넣기
	$("#buldNo").change(function() {
		let selectBuilding = $(this).val();

		$.ajax({
			url: '/student/club/clubOpenRequest',
			method: 'GET',
			dataType: 'json',
			data: { buldNo: selectBuilding },
			success: function(resp) {

				console.log(resp);

				let fcltsNo = $("#fcltsNo");

				fcltsNo.empty();

				$.each(resp, function(idx, buldList) {
					fcltsNo.append($("<option>").text(buldList.fcltsPurps + "(" + buldList.fcltsNm + ")").val(buldList.fcltsNo));
				});
			},
			error: function(error) {
				console.error('에러 발생 !', error);
			}
		});
	});
	$("#inputDataOpen").on("click", function() {
		$("#clubesnm").val("등산이 좋아");
		$("#clubSe").val("02");
		$("#buldNo").val("AA").change();  // 건물 선택을 강제로 발생시키기 위해 .change() 추가
		$("#fcltsNo").val("AA101");
		$("#clubesintrcn").val("평일이나 주말에 다같이모여 여러산을 다니는 동아리입니다. 다같이 모여 등산하면 힘도 덜 들고 재밌습니다~");
	});

});
