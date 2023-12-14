/**
 * <pre>
 * 로그인페이지에 필요한 스크립트
 * </pre>
 * @author 김석호
 * @since 2023. 11. 9.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일       수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      김석호       최초작성
 * 2023. 12. 5.      김석호       로그인 엔터키 추가
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 
var std01 = '20221212';
var std02 = '20220505';
var prf01 = '00000144';
var stf01 = '11000001';


$(loginBtn).on('click', function(){
	let id = $('#exampleInputEmail').val();
	let password = $('#exampleInputPassword').val();
	let pass = true;
	if(!id){
		$('#exampleInputEmail').notify('아이디를 입력해주세요');
		pass= false;
	}
	if(!password){
		$('#exampleInputPassword').notify('비밀번호를 입력해주세요');
		pass= false;
	}
	if(!pass){
		$('#exampleInputEmail').focus();
		return;
	}
    let tForm = $(this).parent('form');
	console.log(tForm);
	tForm.submit();
});
$('.autoInputBtn').on('click',function(){
	var autologintarget = $(this).data('targetUser');
	console.log(autologintarget);
	var targetUser = window[autologintarget];
	console.log(targetUser);
	$('#exampleInputEmail').val(targetUser);
	$('#exampleInputPassword').val(targetUser);
	
});

$('#exampleInputEmail').on('keydown',function(e){
	if(e.keyCode != 13){
		return;
	}
	$('#exampleInputPassword').focus();
	
})
$('#exampleInputPassword').on('keydown',function(e){
	if(e.keyCode != 13){
		return;
	}
	$('#loginBtn').trigger('click');
})
