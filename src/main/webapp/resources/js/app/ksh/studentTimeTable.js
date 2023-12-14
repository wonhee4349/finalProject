/**
 * <pre>
 * 시간표 창에서 사용하는 자바스크립트
 * </pre>
 * @author 김석호
 * @since 2023. 12. 09.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일       수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 12. 09.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 


$(()=>{
	$.ajax({
		url : "/student/timetable"
		, method : "get"
		, dataType : "json"
		, success : function(resp){
			console.log(resp);
			let timeList = resp.data;
			if(timeList?.length>0){
				let getStart = 0;
				let timeListSize = timeList.length;
				$.each(timeList,function(i,v){
					let $targetTd = $(`#${v.TMTB_SE}`);
					if(i!=0){
						if(timeList[i].COURSE_NM != timeList[i-1].COURSE_NM){
							getStart = getStart+1;
						}
					}
					$targetTd.html(`<div class="timedata${getStart}"><span class="course-nm">${v.COURSE_NM}</span><br/>[${v.PRFSOR_NM} - ${v.FCLTS_NM}]</div>`);
					
				});
			}
			
		}
		, error : function(xhr, status, err){
			console.log(err);
		}
	});
});