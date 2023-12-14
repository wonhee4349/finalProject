/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 27.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 27.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 // onload 시작==================================================================================================
 
 


// GridStack Widget ////////////////////////////////////////////////////////////////////////////////////////////////////////

var comBoard = `
		<div gs-id="comBoard" class="grid-stack-item-content" style="background-color: white;">
			<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
				<h6 class="m-0 font-weight-bold text-primary">일반 공지</h6>
				<a class="ft_right" href="/student/board/studentCommonNoticeList"  style="font-size:30px;">+</a>
			</div>
			<div class="card-body" style="padding-top:10px;">
				<div class="chart-area" style="height: 200px;">
					<table class="table table_style01 table_center" id="dataTable" width="100%" cellspacing="0">
						<thead>
							<colgroup> 
								<col width="10%">
			                 	<col width="50%">
			                 	<col width="20%">
			                 	<col width="10%">
							</colgroup>
							<tr>
								<th>번호</th>
								<th>제목</th>
					            <th>작성일</th>
					            <th>조회수</th>
							</tr>
						</thead>
						<tbody id="comBoard-body"></tbody>                      
					</table>
				</div>
			</div>			
		</div>	
	`;

var cal = `
	<div gs-id="calendar" id="Wrapper" class="" style="background:white;">
		<div class="card shadow mb-4">
			<div class="card-header02 py-3">
				<p>학사일정</p>
				<span>학사일정을 확인하세요.</span>
			</div>
			<div class="card-body">
				<div id="Wrapper">
					<div id='calendar'></div>
				</div>
			</div>
		</div>
	</div>
`;


var mypage = `
	<div gs-id="mypage" id="moveToMypage" class="grid-stack-item-content list_box" style="overflow:hidden">
		<div class="row no-gutters align-items-center">
			<a href="/student/mypage/student/myPageUI">
				<div class="text">
					<strong class="tit">마이페이지</strong>
					<p class="txt1">마이페이지 바로가기입니다.</p>
				</div>
				<div class="bot">
					<div class="arrow"></div>
					<div class="icon">
						<img src="/resources/img/mail.png" alt="">
					</div>
				</div>
			</a>
		</div>
	</div>
`;

var saramin = `
	<div gs-id="saramin" id="moveToSaramin" class="grid-stack-item-content list_box bg01">
		<div class="row no-gutters align-items-center">
			<a href="https://www.saramin.co.kr/zf_user/public-recruit/job-fair-schedule" style="color:white;">
				<div class="text">
					<strong class="tit">채용설명회</strong>
					<p class="txt1">다양한 채용정보를 확인하세요</p>
				</div>
				<div class="bot">
					<div class="arrow"></div>
					<div class="icon">
						<img src="/resources/img/main_ico05.png" alt="">
					</div>
				</div>
			</a>
		</div>
	</div>
`;

var certificate = `
	<div gs-id="certificate" id="moveToCertificate" class="grid-stack-item-content list_box bg02">
		<div class="row no-gutters align-items-center">
			<a href="/student/certificate/certificateDown" style="color:white;">
				<div class="text">
					<strong class="tit">증명서발급</strong>
					<p class="txt1">원하시는 증명서를 발급받으세요.</p>
				</div>
				<div class="bot">
					<div class="arrow"></div>
					<div class="icon" style="top:-10px;">
						<img src="/resources/img/main_ico06.png" alt="">
					</div>
				</div>
			</a>
		</div>
	</div>
`;

// GridStack Widget 끝/////////////////////////////////////////////////////////////////////////////////////////////////////////











//Widget 설정/////////////////////////////////////////////////////////////////////////////////////////////////////////

var whCalendar = null;

var items = [
    { x: 0, y: 0, w: 6, h: 7, content: cal, widgetId : "calendar" },
    { x: 6, y: 2, w: 6, h: 3, content: comBoard, widgetId : "comBoard" },
    //{ x: 6, y: 3, w: 6, h: 3, content: noticeBoard, widgetId : "noticeBoard" },
    { x: 6, y: 0, w: 2, h: 2, content: mypage, widgetId : "mypage" },
    { x: 8, y: 0, w: 2, h: 2, content: saramin, widgetId : "saramin" },
    { x: 10, y: 0, w: 2, h: 2, content: certificate, widgetId : "certificate" }
];

let gridOption = {
    column: 12,                   // default 12 -> 6
    oneColumnSize: 100,          // default 768 -> 100
    minRow: 1,
    disableOneColumnMode: true,
    //margin: 10,
    disableResize: true,
    removable: '.trash',
    removable: true,
    disableOneColumnMode: true,
    resizable: { handles: 'e,se,s,sw,w' }
}
let grid = GridStack.init(gridOption);

////////////////////////////// onload 끝





// 컴팩트
function fcompact() {
    grid.compact();
}

// Widget 설정 끝/////////////////////////////////////////////////////////////////////////////////////////////////////////


function loadGrid(widget){
	
	grid.load(widget);
	let gridList = document.querySelectorAll('.grid-stack-item');
	
	$.ajax({
		type:"get",
		url:"/student/ajax/studentMain",
		dataType:"json",
		success:(rslt)=>{
            calDraw(rslt);
		}
	});
    
    
    for(let i=0; i<gridList.length; i++){
	    let menu = gridList[i].getAttribute('gs-id');
	    let menuName;
	    switch (menu) {
	        case 'mypage': menuName = '마이페이지'; break;
	        case 'calendar': menuName = '학사일정'; break;
	        case 'comBoard': menuName = '일반공지'; break;
	        case 'noticeBoard': menuName = '학사공지'; break;
	        case 'saramin': menuName = '취업박람회'; break;
	        case 'certificate': menuName = '증명서'; break;
    	}
	}
	
	grid.on('added removed change', function(e, items) {
		let str = '';
		/*
		if(e.type == "added"){
			console.log(items[0].grid.el.children[4].dataset.widgetId);
			items.addWidget
		}
		*/
		/*
		if(e.type == "removed"){
			var mypageWidget = items.find(item => item.widgetId === "mypage");
			
			console.log("//////////////////", mypageWidget);
			console.log("//////////////////", window.items);
		}
		*/
		/*grid.addWidget({widgetId:"mypage"});*/
		items.forEach(function(item) {
			 str += ' (x,y)=' + item.x + ',' + item.y;
		});
		
		console.log(e.type + ' ' + items.length + ' items:' + str );
    });
	
	// board
	$.getJSON(`/student/mainPage/commonBoardList`, function (resp) {
        let commonBoardList = resp.common;
        let trTags = null;

        if (commonBoardList && commonBoardList.length > 0) {
            $.each(commonBoardList, function (idx, comBoard) {
                trTags += makeBoardTag(comBoard);
            });
        } else {
            trTags += `
                <tr>
                    <td colspan="4" style="text-align:center; height:180px; line-height:155px;">게시글이 없습니다.</td>
                </tr>
            `;
        }
        $("#comBoard-body").html(trTags);
    });
    
    
    $.getJSON(`/student/mainPage/commonNoticeList`, function (resp) {
        let noticeBoardList = resp.notice;
        let trTags = null;

        if (noticeBoardList && noticeBoardList.length > 0) {
            $.each(noticeBoardList, function (idx, noticeBoard) {
                trTags += makeBoardTag(noticeBoard);
            });
        } else {
            trTags += `
                <tr>
                    <td colspan="4" style="text-align:center; height:180px; line-height:155px;">게시글이 없습니다.</td>
                </tr>
            `;
        }
        $("#noticeBoard-body").html(trTags);
    });
	
}







// 위젯 추가 ==================================================================================================================================================
   
// comBoard ---------------------------------------------------------------------------------------------------------------------
function addComBoard(menu) {
	comBoardCall();
	
	if (document.querySelector('div[gs-id="' + menu + '"]')) {
 		return;
 	}
	let menuName = event.target.innerText;
	
 	let newDiv = document.createElement("div");
  	newDiv.setAttribute('gs-id', menu);
//  	newDiv.setAttribute('gs-w', 6);
//  	newDiv.setAttribute('gs-h', 3);
  	newDiv.setAttribute('gs-no-resize', true);
  	newDiv.innerHTML = `
  		<div gs-id="comBoard" class="grid-stack-item-content" style="background-color: white;">
			<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
				<h6 class="m-0 font-weight-bold text-primary">일반 공지</h6>
				<a class="ft_right" href="/student/board/studentCommonNoticeList"  style="font-size:30px;">+</a>
			</div>
			<div class="card-body" style="padding-top:10px;">
				<div class="chart-area" style="height: 200px;">
					<table class="table table_style01 table_center" id="dataTable" width="100%" cellspacing="0">
						<thead>
							<colgroup> 
								<col width="10%">
			                 	<col width="50%">
			                 	<col width="20%">
			                 	<col width="10%">
							</colgroup>
							<tr>
								<th>번호</th>
								<th>제목</th>
					            <th>작성일</th>
					            <th>조회수</th>
							</tr>
						</thead>
						<tbody id="comBoard-body"></tbody>                      
					</table>
				</div>
			</div>			
		</div>
  		`;
	grid.el.appendChild(newDiv);
	grid.makeWidget(newDiv,{w:6 , h: 3 , widgetId : "comBoard"});
}

function comBoardCall(){
	$.getJSON(`/student/mainPage/commonBoardList`, function (resp) {
        let commonBoardList = resp.common;
        let trTags = null;

        if (commonBoardList && commonBoardList.length > 0) {
            $.each(commonBoardList, function (idx, comBoard) {
                trTags += makeBoardTag(comBoard);
            });
        } else {
            trTags += `
                <tr>
                    <td colspan="4" style="text-align:center; height:180px; line-height:155px;">게시글이 없습니다.</td>
                </tr>
            `;
        }
        $("#comBoard-body").html(trTags);
    });
}

//-----------------------------------------------------------------------------------------------------------------------------

// noticeBoard ---------------------------------------------------------------------------------------------------------------------
function addNoticeBoard(menu) {
	noticeBoardCall();
	
	if (document.querySelector('div[gs-id="' + menu + '"]')) {
 		return;
 	}
	let menuName = event.target.innerText;
	
 	let newDiv = document.createElement("div");
  	newDiv.setAttribute('gs-id', menu);
//  	newDiv.setAttribute('gs-w', 6);
//  	newDiv.setAttribute('gs-h', 3);
  	newDiv.setAttribute('gs-no-resize', true);
  	newDiv.innerHTML = `
  		<div gs-id="noticeBoard" class="grid-stack-item-content" style="background-color: white;">
			<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
				<h6 class="m-0 font-weight-bold text-primary">학사 공지</h6>
				<a class="ft_right" onClick="" href="javascript:;">더보기</a>
			</div>
			<div class="card-body" style="padding-top:10px;">
				<div class="chart-area" style="height: 200px;">
					<table class="table table_style01 table_center" id="dataTable" width="100%" cellspacing="0">
						<thead>
							<colgroup>
								<col width="10%">
			                 	<col width="50%">
			                 	<col width="20%">
			                 	<col width="10%">
							</colgroup>
							<tr>
								<th>번호</th>
								<th>제목</th>
					            <th>작성일</th>
					            <th>조회수</th>
							</tr>
						</thead>
						<tbody id="noticeBoard-body"></tbody>                      
					</table>
				</div>
			</div>			
		</div>
  		`;
	grid.el.appendChild(newDiv);
	grid.makeWidget(newDiv,{w:6 , h:3 , widgetId : "noticeBoard"});
}

function noticeBoardCall(menu){
	$.getJSON(`/student/mainPage/commonNoticeList`, function (resp) {
        let noticeBoardList = resp.notice;
        let trTags = null;

        if (noticeBoardList && noticeBoardList.length > 0) {
            $.each(noticeBoardList, function (idx, noticeBoard) {
                trTags += makeBoardTag(noticeBoard);
            });
        } else {
            trTags += `
                <tr>
                    <td colspan="4" style="text-align:center; height:180px; line-height:155px;">게시글이 없습니다.</td>
                </tr>
            `;
        }
        $("#noticeBoard-body").html(trTags);
    });
}

//-----------------------------------------------------------------------------------------------------------------------------

function addCalendar(menu) {
	if (document.querySelector('div[gs-id="' + menu + '"]')) {
 		return;
 	}
	let menuName = event.target.innerText;
	
	$.ajax({
		type:"get",
		url:"/student/ajax/studentMain",
		dataType:"json",
		success:(rslt)=>{
            calDraw(rslt);
		}
	});
	
 	let newDiv = document.createElement("div");
  	//newDiv.setAttribute('gs-id', menu);
  	newDiv.setAttribute('class', 'grid-stack-item ui-resizable-disabled');
//  	newDiv.setAttribute('gs-w', 6);
//  	newDiv.setAttribute('gs-h', 8);
  	//newDiv.setAttribute('gs-no-resize', true);
  	newDiv.innerHTML = `
  		<div class="grid-stack-item-content">
	  		<div gs-id="calendar" id="Wrapper" class="" style="background:white;">
				<div class="card shadow mb-4">
					<div class="card-header02 py-3">
						<p>학사일정</p>
						<span>학사일정을 확인하세요.</span>
					</div>
					<div class="card-body">
						<div id="Wrapper">
							<div id='calendar'></div>
						</div>
					</div>
				</div>
			</div>
		</div>
  		`;
	grid.el.appendChild(newDiv);
	grid.makeWidget(newDiv,{w:6 , h:8 , widgetId : "calendar" });
	newCalendar = new FullCalendar.Calendar(calendarEl, calendarOption);
  	newCalendar.render();
}


function addMypage(menu) {
	if (document.querySelector('div[gs-id="' + menu + '"]')) {
 		return;
 	}
	let menuName = event.target.innerText;
	console.log("menuName : ", menuName);
	
 	let newDiv = document.createElement("div");
  	//newDiv.setAttribute('gs-id', menu);
  	//newDiv.setAttribute('gs-w', 2);
  	//newDiv.setAttribute('gs-h', 2);
  	//newDiv.setAttribute('gs-no-resize', true);
  	newDiv.setAttribute('class', 'grid-stack-item ui-resizable-disabled');
//  	newDiv.setAttribute('gs-w', 2);
//  	newDiv.setAttribute('gs-h', 2);  	
//  	newDiv.setAttribute('gs-widgetid', 'mypage'); 
  	newDiv.innerHTML = `
  		<div class="grid-stack-item-content">
			<div gs-id="mypage" id="moveToMypage" class="grid-stack-item-content list_box" style="overflow:hidden;">
				<div class="row no-gutters align-items-center">
					<a href="/student/mypage/student/myPageUI">
						<div class="text">
							<strong class="tit">마이페이지</strong>
							<p class="txt1">마이페이지 바로가기입니다.</p>
						</div>
						<div class="bot">
							<div class="arrow"></div>
							<div class="icon">
								<img src="/resources/img/mail.png" alt="">
							</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	`;
	grid.el.appendChild(newDiv);
	grid.makeWidget(newDiv,{w:2,h:2,widgetId : "mypage"});
}

function addSrarmin(menu) {
	if (document.querySelector('div[gs-id="' + menu + '"]')) {
 		return;
 	}
	let menuName = event.target.innerText;
	
 	let newDiv = document.createElement("div");
  	//newDiv.setAttribute('gs-id', menu);
//  	newDiv.setAttribute('gs-w', 2);
//  	newDiv.setAttribute('gs-h', 2);
  	newDiv.setAttribute('class', 'grid-stack-item ui-resizable-disabled');
  	//newDiv.setAttribute('gs-no-resize', true);
  	newDiv.innerHTML = `
  		<div class="grid-stack-item-content">
	  		<div gs-id="saramin" id="moveToSaramin" class="grid-stack-item-content list_box bg01" style="overflow:hidden">
				<div class="row no-gutters align-items-center">
					<a href="https://www.saramin.co.kr/zf_user/public-recruit/job-fair-schedule" style="color:white;">
						<div class="text">
							<strong class="tit">채용설명회</strong>
							<p class="txt1">다양한 채용정보를 확인하세요</p>
						</div>
						<div class="bot">
							<div class="arrow"></div>
							<div class="icon">
								<img src="/resources/img/main_ico05.png" alt="">
							</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	`;
	grid.el.appendChild(newDiv);
	grid.makeWidget(newDiv,{w:2 , h:2 , widgetId : "saramin" });
}

function addCertificate(menu) {
	if (document.querySelector('div[gs-id="' + menu + '"]')) {
 		return;
 	}
	let menuName = event.target.innerText;
	
 	let newDiv = document.createElement("div");
  	//newDiv.setAttribute('gs-id', menu);
//  	newDiv.setAttribute('gs-w', 2);
//  	newDiv.setAttribute('gs-h', 2);
  	newDiv.setAttribute('class', 'grid-stack-item ui-resizable-disabled');
//  	newDiv.setAttribute('widgetId', 'mypage');
  	//newDiv.setAttribute('gs-no-resize', true);
  	newDiv.innerHTML = `
  		<div class="grid-stack-item-content">
	  		<div gs-id="certificate" id="moveToCertificate" class="grid-stack-item-content list_box bg02" style="overflow:hidden;">
				<div class="row no-gutters align-items-center">
					<a href="/student/certificate/certificateDown" style="color:white;">
						<div class="text">
							<strong class="tit">증명서발급</strong>
							<p class="txt1">원하시는 증명서를 발급받으세요.</p>
						</div>
						<div class="bot">
							<div class="arrow"></div>
							<div class="icon" style="top:-10px;">
								<img src="/resources/img/main_ico06.png" alt="">
							</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	`;
	grid.el.appendChild(newDiv);
	grid.makeWidget(newDiv,{w:2 , h:2 , widgetId : "certificate"});
}

// 위젯 추가 끝 ==================================================================================================================================================






function makeBoardTag(board){
	let trTag = `
		<tr data-bo-no="${board.boNo}" onclick="moveToBoard('${board.boNo}')" style="cursor: pointer;">
            <td>${board.rnum}</td>
            <td>${board.boTitle}</td>
            <td>${board.boDate}</td>
            <td>${board.boCnt}</td>
        </tr>
	`;
	return trTag;
}

function moveToBoard(boNo){
	location.href=`/student/board/studentNoticeDetail/${boNo}`;
}



// Full Calendar 설정 //////////////////////////////////////////////////////////////////////////////////////////////////////////

function calDraw(schData){
	
	whCalendar = document.querySelector("#calendar");
    //캘린더 헤더 옵션
    const headerToolbar = {
        /*left: 'prevYear,prev,next,nextYear today',
        center: 'title',
        right: 'dayGridMonth,dayGridWeek,timeGridDay'*/
        left: 'prev',
	    center: 'title',
	    right: 'next'
    }

    // 캘린더 생성 옵션(참공)
    const calendarOption = {
        height: '700px', // calendar 높이 설정
        expandRows: true, // 화면에 맞게 높이 재설정
        slotMinTime: '09:00', // Day 캘린더 시작 시간
        slotMaxTime: '18:00', // Day 캘린더 종료 시간
        // 맨 위 헤더 지정
        headerToolbar: headerToolbar,
        initialView: 'dayGridMonth',  // default: dayGridMonth 'dayGridWeek', 'timeGridDay', 'listWeek'
        locale: 'kr',        // 언어 설정
        selectable: true,    // 영역 선택
        selectMirror: true,  // 오직 TimeGrid view에만 적용됨, default false
        navLinks: false,      // 날짜,WeekNumber 클릭 여부, default false
        weekNumbers: false,   // WeekNumber 출력여부, default false
        editable: true,      // event(일정) 
        /* 시작일 및 기간 수정가능여부
        eventStartEditable: false,
        eventDurationEditable: true,
        */
        dayMaxEventRows: true,  // Row 높이보다 많으면 +숫자 more 링크 보임!
        /*
        views: {
            dayGridMonth: {
                dayMaxEventRows: 3
            }
        },
        */
        nowIndicator: true,
        events:schData,
    }

    // 캘린더 생성
    const calendar = new FullCalendar.Calendar(whCalendar, calendarOption);
    // 캘린더 그리깅
    calendar.render();

    // 캘린더 이벤트 등록
    calendar.on("eventClick", info => {
        console.log("eClick:", info);
        console.log('Event: ', info.event.extendedProps);
        console.log('Coordinates: ', info.jsEvent);
        console.log('View: ', info.view);
        // 재미로 그냥 보더색 바꾸깅
        info.el.style.borderColor = 'red';
    });
    calendar.on("dateClick", info => console.log("dateClick:", info));

}

/*var YrModal = null;
var calendarEl = null;
var mySchStart = null;
var mySchEnd = null;
var mySchTitle = null;
var mySchAllday = null;
var mySchBColor = null;
var mySchFColor = null;

function callCalendar(){
	YrModal = document.querySelector("#yrModal");
	calendarEl = document.querySelector('#calendar');
	mySchStart = document.querySelector("#schStart");
	mySchEnd = document.querySelector("#schEnd");
	mySchTitle = document.querySelector("#schTitle");
	mySchAllday = document.querySelector("#allDay");
	mySchBColor = document.querySelector("#schBColor");
	mySchFColor = document.querySelector("#schFColor");
	
	
		
	//캘린더 헤더 옵션
	var headerToolbar = {
	    left: 'prev',
	    center: 'title',
	    right: 'next'
	}
	
	// 캘린더 생성 옵션(참공)
	var calendarOption = {
	    height: '700px', // calendar 높이 설정
	    expandRows: true, // 화면에 맞게 높이 재설정
	    slotMinTime: '09:00', // Day 캘린더 시작 시간
	    slotMaxTime: '18:00', // Day 캘린더 종료 시간
	    // 맨 위 헤더 지정
	    headerToolbar: headerToolbar,
	    initialView: 'dayGridMonth',  // default: dayGridMonth 'dayGridWeek', 'timeGridDay', 'listWeek'
	    locale: 'kr',        // 언어 설정
	    selectable: true,    // 영역 선택
	    selectMirror: true,  // 오직 TimeGrid view에만 적용됨, default false
	    navLinks: false,      // 날짜,WeekNumber 클릭 여부, default false
	    weekNumbers: false,   // WeekNumber 출력여부, default false
	    editable: true,      // event(일정) 
	    /* 
	    eventStartEditable: false,
	    eventDurationEditable: true,
	    dayMaxEventRows: true,  // Row 높이보다 많으면 +숫자 more 링크 보임!
	    views: {
	        dayGridMonth: {
	            dayMaxEventRows: 3
	        }
	    },
	    
	    nowIndicator: true,
	    //events:[],
	    eventSources: [
	        './commonEvents.json',  // Ajax 요청 URL임에 유의!
	        './KYREvents.json',
	        './SYREvents.json'
	    ]
	}
	
	console.log("cccccccccccccccccccc : ",calendarEl);
	console.log("cccccccccccccccccccc : ",calendarOption);
	
	// 캘린더 생성
	var newCalendar = new FullCalendar.Calendar(calendarEl, calendarOption);
	// 캘린더 그리깅
	newCalendar.render();
}
*/


// Full Calendar 설정 끝 //////////////////////////////////////////////////////////////////////////////////////////////////////////









// 위젯 위치 저장 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

function saveFullGrid() {
	serializedFull = grid.save(true, false); // grid.save(content, option);
   	console.log("-------------",serializedFull);

	let data = JSON.stringify(serializedFull);

    
    let settings = {
		url : "student/mainPage/saveWidget"
		, method : "post"
		, data : data
		, dataType : 'json'
		, contentType : 'application/json'
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
					icon: icon
				}).then((res)=>{
					if(resp.success){
						location.reload();
					}
				});
		}
		, error : function(xhr, status, err){
			console.log(err);
			alert(err);
			//alert("잘못된 요청 발생!");
		}
	};
	$.ajax(settings);

	
	/*
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "student/mainPage/saveWidget", false); // 동기
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(data);
    
    if (xhr.status == 200) {
    	if($("#empNoInfo").data("empno") == 'admin'){
    		location.reload();
    	} else{
	    	window.location.href = "/";
    	}
    }*/
}


// 위젯 위치 저장 끝 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////




function changeWidget(portletList){
	var list = JSON.parse(portletList);
	
	let items = [];
	
	for (let idx in list) {
        if (list.hasOwnProperty(idx)) {
            let val = list[idx];
            items.push({
                widgetId: val.widgetId,
                userNo: val.userNo,
                content: val.content,
                x: val.x,
                y: val.y,
                w: val.w,
                h: val.h
            });
        }
    }
}

function primaryWidget(){
	
}




























