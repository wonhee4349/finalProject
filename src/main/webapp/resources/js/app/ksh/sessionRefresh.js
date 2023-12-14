/**
 * <pre>
 * 세션 시간 연장을 위한 헤더메뉴 자바스크립트
 * </pre>
 * @author 김석호
 * @since 2023. 12. 7.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일            수정자       수정내용
 * --------          --------    ----------------------
 * 2023. 12. 7.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 

(()=>{
    renderSessionRemainTime();
    tickTockTickTock();
})();

function tickTockTickTock(){
    sessionRemainTime = sessionRemainTime-1;
//    console.log(sessionRemainTime);
    renderSessionRemainTime();
    if(sessionRemainTime == 0){
        location.href = "/";
    }
    setTimeout(tickTockTickTock,1000);
}



function renderSessionRemainTime(){
        var reaminTimeMinute = parseInt(sessionRemainTime /60);
        var reaminTimeSecond = (sessionRemainTime % 60);
        var remainTimeTextValue = ``;
        remainTimeTextValue = `${String(reaminTimeMinute).padStart(2, "0")}:${String(reaminTimeSecond).padStart(2, "0")}`;
        $('#remainSessionTimeArea').html(remainTimeTextValue);
}


$('#sessionRefreshButton').on('click',function(){
	$.ajax({
		url : "/session"
		, dataType : "json"
		, method : "post"
		, success : function(resp){
//			console.log('`세션 연장 후 나온 값',resp);
			sessionRemainTime = resp;
			$.notify("로그인 시간이 연장되었습니다.");
			renderSessionRemainTime();
		}
		, error : function(xhr, status, err){
			console.log("error 첫줄");
			console.log(err);
		}
	})
})

$('#timetableBtn').on('click',function(){
	window.open("/student/timeTableUI",'title',
	`width=850
	,height=850
	,fullscreen=no
	,scrollbars=yes
	,toolbar=no
	,menubar=no
	,location=no
	,title=no
	,popup=yes`);
});

