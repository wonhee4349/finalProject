/**
 * <pre>
 * 학적변동신청에 사용할 자바스크립트
 * </pre>
 * @author 김석호
 * @since 2023. 11. 15.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일       수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 15.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 

$(()=>{
	$('#registerForm').on('submit',function(e){
		e.preventDefault();
		let data = $(this).serialize();
		let setting = {
			url  : "/student/ajax/register"
			, method : 'POST'
			//, contentType : 'application/json; charset=utf-8'
			//, data : JSON.stringify(data)
			, data : data
			, dataType : 'json'
			, success : function(resp){
				if (resp.success) {
					Swal.fire({
						title: "신청 성공!",
						text: `${resp.message}`,
						icon: "success",
						showConfirmButton: false,
						timer: 1500
					});
					setTimeout(function() {
						location.href = "/student/subjectRequestList";
					}, 1500);
				} else {
					Swal.fire({
						title: "잘못된 요청!!",
						text: `${resp.message}`,
						icon: "error",
						showConfirmButton: false,
						timer: 1500
					});
					setTimeout(function() {
						location.reload();
					}, 1500);
				}
			}
			, error : function(xhr, status, err){
				console.log(err);
			}
		};
		
		$.ajax(setting);


	});

	$('#clg').on('change',function(){
		let data = this.value;
		let $target = $('.clgSubject');
		$target.css('display','none');
		if(data){
			let $targets = $(`.clg${data}`);
			// console.log($target);
			$targets.css('display','inline-block');
		}else{
			$target.css('display','inline-block');
		}
		$('#subjctNo').val('');
	}).trigger('change');


})