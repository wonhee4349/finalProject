/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 12. 5.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 12. 5.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(()=>{
   calendarDataload();
   boardData();
});

function calendarDataload(){
	$.ajax({
		type:"get",
		url:"/professor/ajax/professorMain",
		dataType:"json",
		success:(rslt)=>{
            console.log("-------------",rslt);
            calDraw(rslt);
		}
	})
};

const whCalendar = document.querySelector("#calendar");

function calDraw(schData){
        //캘린더 헤더 옵션
        const headerToolbar = {
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

function boardData(){
	$.getJSON(`/professor/mainPage/commonBoardList`, function (resp) {
        let commonBoardList = resp.common;
        let trTags = null;

        if (commonBoardList && commonBoardList.length > 0) {
            $.each(commonBoardList, function (idx, comBoard) {
                trTags += makeCommonBoardTag(comBoard);
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
    
    
    $.getJSON(`/professor/mainPage/commonNoticeList`, function (resp) {
        let noticeBoardList = resp.notice;
        let trTags = null;

        if (noticeBoardList && noticeBoardList.length > 0) {
            $.each(noticeBoardList, function (idx, noticeBoard) {
                trTags += makeNoticeBoardTag(noticeBoard);
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

function makeCommonBoardTag(board){
	let trTag = `
		<tr data-bo-no="${board.boNo}" onclick="moveToCommonBoard('${board.boNo}')" style="cursor: pointer;">
            <td>${board.rnum}</td>
            <td>${board.boTitle}</td>
            <td>${board.boDate}</td>
            <td>${board.boCnt}</td>
        </tr>
	`;
	return trTag;
}
function moveToCommonBoard(boNo){
	location.href=`/professor/board/commonNoticeView/${boNo}`;
}


function makeNoticeBoardTag(board){
	let trTag = `
		<tr data-bo-no="${board.boNo}" onclick="moveToNoticeBoard('${board.boNo}')" style="cursor: pointer;">
            <td>${board.rnum}</td>
            <td>${board.boTitle}</td>
            <td>${board.boDate}</td>
            <td>${board.boCnt}</td>
        </tr>
	`;
	return trTag;
}
function moveToNoticeBoard(boNo){
	location.href=`/professor/board/noticeView/${boNo}`;
}
