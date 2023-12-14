/**
 * </pre>

 * @author 김재성
 * @since 2023. 11. 26.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 *  수정일         수정자       수정내용
 * --------     ------    ----------------
 * 2023.11.26.   김재성       최초작성
 * 2023.11.27.   김재성       UI 메뉴 및 Form 재구성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
$(function() {
	
    let lctreNo = localStorage.getItem("lctreNo");
	//alert("lctreNo"+lctreNo);
	
	// 탭 변환
	$('.class_list_wrap button').click(function () {
        var tab_id = $(this).attr('data-tab');
		
        $('.class_list_wrap button').removeClass('active');
        $('.tab-content').removeClass('current');
		
        $(this).addClass('active');
		
        $("#" + tab_id + '-content').addClass('current');
    });

	$("#classroomLectureChoiceSelect").on("change",function(e){
		e.preventDefault();
		// 시험출제 탭 View 여는 함수
		let selectLecture = $(this).val();
		//let selectSemstrSe = $(this).find("option:selected").text();
		
		console.log("강의코드 오는지 확인 : ",selectLecture);
		
		$.ajax({
            type:"get",
            url:`/professor/classroom/test/${selectLecture}`,
            //data: {lctreNo : selectLecture},
            dataType:"html",
            //async:false,    // 틀이 들어가야 데이타를 넣을 수 있으므로
            success: function(rslt){
                $("#testBody").html(rslt); 
            },
            error: function(xhr){
                console.log(xhr.status);
            }
        });
		
		// 과제등록 탭 View 여는 함수
        $.ajax({
            type:"get",
            url:`/professor/classroom/assignment/${selectLecture}`,
            //data: {lctreNo : selectLecture},
            dataType:"html",
            //async:false,    // 틀이 들어가야 데이타를 넣을 수 있으므로
            success: function(rslt){
                $("#assignmentBody").html(rslt); 
            },
            error: function(xhr){
                console.log(xhr.status);
            }
        });
		
		// 자료등록 탭 View 여는 함수
        $.ajax({
            type:"get",
            url:`/professor/classroom/data/${selectLecture}`,
            //data: {lctreNo : selectLecture},
            dataType:"html",
            //async:false,    // 틀이 들어가야 데이타를 넣을 수 있으므로
            success: function(rslt){
                $("#dataBody").html(rslt); 
            },
            error: function(xhr){
                console.log(xhr.status);
            }
        });
		
		//온라인강의 탭 View 여는 함수
        $.ajax({
            type:"get",
            url:`/professor/classroom/onlineLecture/${selectLecture}`,
           // data: {lctreNo : selectLecture},
            dataType:"html",
            //async:false,    // 틀이 들어가야 데이타를 넣을 수 있으므로
            success: function(rslt){
                $("#lectureOnlineBody").html(rslt); 
            },
            error: function(xhr){
                console.log(xhr.status);
            }
        });

		//공지사항 탭 View 여는 함수
        $.ajax({
            type:"get",
            url:`/professor/classroom/notice/${selectLecture}`,
           // data: {lctreNo : selectLecture},
            dataType:"html",
            //async:false,    // 틀이 들어가야 데이타를 넣을 수 있으므로
            success: function(rslt){
                $("#noticeBody").html(rslt); 
            },
            error: function(xhr){
                console.log(xhr.status);
            }
        });
	});
	
	//alert("ppp"+lctreNo+"ppp");
	if(lctreNo !=null){
		$('#classroomLectureChoiceSelect').val(lctreNo).trigger("change");
		localStorage.removeItem("lctreNo");
		//alert("왔다 if");
	}else{
		$('#classroomLectureChoiceSelect').val("-1");
		//alert("왔다 else");
	}
	
	
	
	// ----------------------------------------------------------------------------------

});




