/**
 * </pre>
 * @author 김재성
 * @since 2023. 11. 23.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 *  수정일         수정자       수정내용
 * --------     ------    ----------------
 * 2023.11.23.   김재성       최초작성
 * 2023.11.24.   김재성       강의개설신청 목록
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

$(function() {
	let tab = $("#txtTab").val();
	
	if(tab=="tab2"){
		console.log("tab : " + tab);
		$("#btnTab2").trigger("click");
		$("#btnTab2").click();
	}
	
	 //단과대학 선택 된 대학 학과 리스트 select 넣기
     $("#clgSelect").change(function() {
        let selectCollege = $(this).val();
		
        $.ajax({
            url: '/professor/lecture/subjectList',
            method: 'GET',
			dataType: 'json',
            data: {clgNo:selectCollege},
            success: function(resp) {
				
				console.log('서버 응답:', resp);
				
                let subjectSelect = $("#subjctSelect");
				
				subjectSelect.empty();
				 
                $.each(resp, function(idx, subject) {
                    subjectSelect.append($("<option>").text(subject.subjctNm).val(subject.subjctNo));
                });
            },
            error: function (error) {
                console.error('에러 발생 !', error);
            }
        });
    });
	
	
	let coursePnt ;
	//학과 선택된 교과목 불러오기
    $("#subjctSelect").change(function() {
        let selectSubject = $(this).val();
		
		console.log("subject",selectSubject);
		
        $.ajax({
            url: '/professor/lecture/courseList',
            method: 'GET',
			dataType: 'json',
            data: {subjctNo:selectSubject},
            success: function(resp) {
				coursePnt = resp;
				
                let courseSelect = $("#courseSelect");
				
				courseSelect.empty();
				
				 
                $.each(resp, function(idx, course) {
                    courseSelect.append($("<option>").text(course.courseNm).val(course.courseNo));
                });
				
            },
            error: function (error) {
                console.error('에러 발생 !', error);
            }
        });
    });
	
	//선택된 교과목의 학점 넣기
	$("#courseSelect").change(function() {
		console.log("coursePnt",coursePnt);
		let courseSelect = $(this).val();
		
		$.each(coursePnt,function(idx,val){
			if(coursePnt[idx].courseNo == courseSelect){
				$("#selectSubjectCourseCoursePnt").val(coursePnt[idx].coursePnt+"학점");
			}
		});
		
    });
	
	
	let timeArr = [];
	$(document).on("change","[jsSel]",function(){
		timeArr.push(this.value);
		console.log("체킁",timeArr);
	});
	 

	//건물 선택된 강의실 불러오기
    $("#buldSelect").change(function() {
        let buldSelect = $(this).val();
		
		console.log("building",buldSelect);
		
        $.ajax({
            url: '/professor/lecture/facilitiesList',
            method: 'GET',
			dataType: 'json',
            data: {buldNo:buldSelect},
            success: function(resp) {
				
				console.log(resp);
				
                let fcltsSelect = $("#fcltsSelect");
				
				fcltsSelect.empty();
				 
                $.each(resp, function(idx, facilities) {
                    fcltsSelect.append($("<option>").text(facilities.fcltsPurps+"("+facilities.fcltsNm+")").val(facilities.fcltsNo));
                });
            },
            error: function (error) {
                console.error('에러 발생 !', error);
            }
        });
    });
	
	
	// 강의시간표 추가 버튼 기능
    function assignRemoveHandler(button) {
       button.click(function() {
          $(this).closest('tr').remove();

			$(".tmtbSe").each(function(idx){
				$(this).attr("name","lectureRequestInfoVOList["+idx+"].tmtbSe");
			});
       });
    }

        assignRemoveHandler($(".remove-button"));

    $(".add-button").click(function() {
            var newRow = $(".time-table-row").first().clone();

            assignRemoveHandler(newRow.find('.remove-button'));

            $(".time-table-row").last().after(newRow);

			$(".tmtbSe").each(function(idx){
				$(this).attr("name","lectureRequestInfoVOList["+idx+"].tmtbSe");
			});
     });

	 assignRemoveHandler($(".remove-button"));
	
	
	
	// 강의계획서 모두 작성한 후에 등록 버튼 눌렀을때
	$("#newLectureRequestForm").on("submit",function(event){
		event.preventDefault();
		
		let data = $(this).serialize();
		
		$.ajax({
			url:'/professor/lecture/newLectureRequest',
			method :"POST",
			data: data,
			type : "JSON",
			success : function(rslt){
				let icon = null;
				let title = null;
				if(rslt.success){
					title = "강의 개설 성공했습니다!";
					icon = "success";
				}else{
					title = "강의 개설 실패했습니다!";
					icon = "error";
				}
				Swal.fire({
					title: title,
					icon: icon,
					showConfirmButton: false,
               		timer: 1500
				});
				setTimeout(function() {
	                location.reload();
	                $("##tab-2-content").trigger("click");
					$("##tab-2-content").click();
	            }, 1500);
				/*
				if(rslt.success){
					var timerInterval;
					Swal.fire({
						title: "등록 완료!",
						html: "강의개설 신청 완료!",
						timer: 1500,
						timerProgressBar: true,
						didOpen: () => {
							Swal.showLoading();
							const timer = Swal.getPopup().querySelector("b");
							timerInterval = setInterval(() => {
								timer.textContent = `${Swal.getTimerLeft()}`;
							}, 100);
						},
						willClose: () => {
							clearInterval(timerInterval);
						}
					}).then((success) => {
						location.reload();
						$("#btnTab2").trigger("click");
						$("#btnTab2").click();
						$('.close').trigger('click');
					});
				}else{
					var timerInterval;
					Swal.fire({
						title: "등록 실패",
						html: "강의개설 신청 실패!",
						timer: 1500,
						timerProgressBar: true,
						didOpen: () => {
							Swal.showLoading();
							const timer = Swal.getPopup().querySelector("b");
							timerInterval = setInterval(() => {
								timer.textContent = `${Swal.getTimerLeft()}`;
							}, 100);
						},
						willClose: () => {
							clearInterval(timerInterval);
						}
					}).then((success) => {
					});
					
				}
				*/
			},
			error : function(xhr){
				console.log(xhr.status);
			}
		});
	});
	
	// 탭 변환
	$('.class_list_wrap button').click(function () {
        var tab_id = $(this).attr('data-tab');

        $('.class_list_wrap button').removeClass('active');
        $('.tab-content').removeClass('current');

        $(this).addClass('active');

        $("#" + tab_id + '-content').addClass('current');
    });

	
//------------------------- 강의개설신청 목록 javaScript --------------------------------------
	
	function makeTrTag(lectureRequest){
		
		let str = lectureRequest.semstrSe;
		let semYear = str.substr(0,4);
		let semstr = str.substr(4,1);
		
		let rowDate =lectureRequest.lctreReqstDate;
		let lctreReqstDate = rowDate.replace(/^(\d{2})(\d{2})(\d{2})$/, '$1/$2/$3');
		
		let listTag = `
						<tr data-lctre_reqst_no="${lectureRequest.lctreReqstNo}" id="modal_open" data-toggle="modal" data-target="#modal_open">
						<td>${semYear}년</td>
						<td>${semstr}학기</td>
						`;
			if(lectureRequest.course.subjctNm == null){
				listTag +=`<td>교양과목</td>`;
			}else{
				listTag +=`<td>${lectureRequest.course.subjctNm}</td>`;
			}
			listTag += `             
						<td>${lectureRequest.course.courseNm}</td>             
						<td>${lectureRequest.lctreSe}</td>             
						<td>${lectureRequest.professor.prfsorNm}</td>
						<td>${lctreReqstDate}</td>             
						`;
		let confm = lectureRequest.confmSe ;
		
		if(confm=="승인 완료"){
            listTag +=`<td><span class="pstatus02">${confm}</span></td></tr>`;
        }else if(confm=="반려"){
        	 listTag +=`<td><span class="pstatus03">${confm}</span></td></tr>`;
        }else {
			listTag +=`<td><span class="pstatus01">${confm}</span></td></tr>`;
		} 
		return listTag;
	 };
 	

	$("#searchForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serialize();
		$.getJSON(`/professor/lecture/ajax/lectureRequestList?${data}`, function(rslt){   // 요청 URL 설정 controller에 정해놓은거
			let lectureRequestList = rslt.paging.dataList;
			
			let lectureRequestTags = null;
			if(lectureRequestList.length > 0){
				$.each(lectureRequestList, function(idx, lectureRequest){
					lectureRequestTags += makeTrTag(lectureRequest);
				});
				$(pagingArea).html(rslt.paging.pagingHTML);
			}else{
				lectureRequestTags += `
					<tr>
						<td colspan="6" style="text-align: center;">강의개설 신청한 목록이 없습니다.</td>
					</tr>
				`;
				$(pagingArea).empty();
			}
			
			$("#listBody").html(lectureRequestTags);
		});
		return false;
	}).submit();
	

	$("#searchUI").on("click", "#searchBtn", function(event){
		let select = $(this).parents("#searchUI").find("#searchSelect");
			let selectName = select.attr("name");
			let	selectValue = select.val();
        
        let inputs = $(this).parents("#searchUI").find("input[name=searchWord]");
            let name = inputs.attr("name");
            let value = inputs.val();
            
            console.log("name , value 값 : ", name,value);
            $(searchForm).find(`:input[name=${name}]`).val(value);
            $(searchForm).find(`:input[name=${selectName}]`).val(selectValue);
            

            console.log("name , value 값 : ", name,value,selectName,selectValue);
			
            $("#searchForm").submit();
    });
    
    
    $("#modal_open").on("show.bs.modal", function(event){
		let $modal = $(this);
		let listTag = event.relatedTarget;
		
		let lctreReqstNo = listTag.getAttribute("data-lctre_reqst_no");
		
		console.log("courseReqstNo : ",lctreReqstNo);
		
		let url="/professor/lecture/ajax/lectureRequestView?lctreReqstNo="+lctreReqstNo;
		
		$.get(url).done(function(rslt){
				$modal.find(".modal-body").html(rslt);
		});
		
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
    });

	
	$("#lectureRequestClose").click(function(){
		$("#modal_open").modal("hide");
	});

});

 function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
};



function autoInput() {
    // 대학 선택 값
    let selectedCollege = "C";
    // 학과 선택 값
    let selectedSubject = "12";
    // 교과목 선택 값
    let selectedCourse = "C011";
    // 학점 선택 값
    let selectSubjectCourseCoursePnt = "3";
    // 건물 선택 값
    let buldSelect = "CC";
    // 강의실 선택 값
    let fcltsSelect = "CC305";
    // 학기 선택 값
    let semstrSelect = "01";
    // 강의형태 선택 값
    let lctreSelect = "01";
	
	// 성적평가기준
	let middleTest = "30";
	let finalTest = "40";
	let homework = "20";
	let attendance = "10";
	let lectureTarget = `
이 강좌의 목표는 학생들이 자바프로그래밍 언어를 이해하고 실무에서의 응용 능력을 키우는 데에 있습니다. 다음은 구체적인 강의목표입니다.

1.자바프로그래밍 언어의 기본 문법 및 구조를 학습하고, 객체지향 프로그래밍의 개념을 이해합니다.
2.다양한 자바 라이브러리 및 프레임워크를 활용하여 실제 응용 프로그램을 개발할 수 있는 능력을 키웁니다.
3.예외처리, 다중 스레드, 네트워크 프로그래밍과 같은 고급 주제에 대한 이해와 활용 능력을 강화합니다.
4.데이터베이스와의 연동, 웹 프로그래밍, 프레임워크(Spring) 사용 등 다양한 분야에서의 실전 능력을 강화합니다.
5.개발 프로세스에서의 효율적인 협업과 문제 해결 능력을 향상시킵니다.
	`;
	let lecturePlan = `
1주차 - 자바프로그래밍 소개 및 기초 문법
 - 자바프로그래밍 언어 소개
 - JDK 설치 및 환경 설정
 - 기본 문법: 변수, 자료형, 연산자
 - 제어문: 조건문, 반복문

2주차 - 객체지향 프로그래밍 기본 개념
 - 객체와 클래스의 이해
 - 상속과 다형성
 - 추상 클래스와 인터페이스
 - 객체지향 설계 원칙

3주차 - 예외 처리와 입출력
 - 예외 처리의 필요성
 - try-catch 블록
 - 파일 입출력 기본
 - 표준 입력/출력과 파일 입출력 실습

4주차 - 컬렉션 프레임워크
 - List, Set, Map 등의 컬렉션 인터페이스
 - ArrayList, HashSet, HashMap 등의 구현체
 - 컬렉션 프레임워크 활용 실습

5주차 - 스레드 프로그래밍
 - 멀티스레드 개념과 필요성
 - 스레드 생성과 제어
 - 동기화와 데드락 예방

6주차 - 네트워크 프로그래밍 기초
 - Socket 프로그래밍 소개
 - TCP/IP 통신 기초
 - 간단한 클라이언트-서버 어플리케이션 구현

7주차 - GUI 프로그래밍 기초 (Swing)
 - Java Swing 라이브러리 소개
 - 기본 컴포넌트 활용
 - 이벤트 처리 및 리스너 구현
8주차 - 데이터베이스 연동과 JDBC
 - JDBC 소개
 - 데이터베이스 연동 기초
 - SQL 쿼리 실행과 결과 처리

9주차 - 미니 프로젝트: 간단한 자바 어플리케이션 개발
 - 지금까지 학습한 내용을 활용하여 간단한 프로젝트 수행

10주차 - 웹 프로그래밍과 서블릿
 - 웹 프로그래밍 개념 소개
 - 서블릿의 역할과 생명주기
 - 간단한 웹 어플리케이션 구현

11주차 - JSP와 MVC 패턴
 - JSP의 기본 구조
 - MVC 아키텍처 이해
 - JSP와 서블릿을 활용한 웹 애플리케이션 개발

12주차 - 프레임워크 소개: Spring
 - Spring 프레임워크 개요
 - 의존성 주입(Dependency Injection) 이해
 - 간단한 Spring 기반 어플리케이션 구현

13주차 - 데이터베이스와의 연동 (Spring JDBC)
 - Spring JDBC를 활용한 데이터베이스 처리
 - DAO 패턴과의 결합

14주차 - 웹 프론트엔드 기초 (HTML, CSS, JavaScript)
 - HTML, CSS, JavaScript 기본 문법
 - 간단한 웹 페이지 제작 실습

15주차 - 종합 프로젝트 및 마무리
 - 지금까지 학습한 내용을 종합하여 프로젝트 진행
 - 학습 내용 복습 및 향후 학습 방향 논의`;	

let lectureNote =
`
참고 교재:
 - "자바의 정석" 또는 "Effective Java"와 같은 교재 활용
 - 강의노트 및 예제 코드는 온라인 강의 플랫폼에 제공

평가 방식:
 - 중간고사와 기말고사, 프로젝트 평가 등으로 평가
 - 출석 및 과제 참여도 고려

도움말 및 질문:
 - 강의 시간 이외에도 온라인 포럼이나 이메일을 통해 질문 환영
 - 수업 내용과 관련된 도움말 및 자료는 수시로 제공

오피스 아워:
 - 매주 정해진 시간에 오피스 아워를 통해 학생들의 질문에 답변
 - 개별 상담 및 도움이 필요한 경우 사전 약속 가능
`;	
	// 이수구분 선택 값
	let compleSelect = "01";
	
    // 대학 선택
    $("#clgSelect").val(selectedCollege).change();

    // 학과 선택 (대학 선택 후 학과를 비동기적으로 가져오기)
    $.ajax({
        url: '/professor/lecture/subjectList',
        method: 'GET',
        dataType: 'json',
        data: { clgNo: selectedCollege },
        success: function (resp) {
            // 학과 선택
            $("#subjctSelect").val(selectedSubject).change();
        },
        error: function (error) {
            console.error('에러 발생 !', error);
        }
    });

    // 교과목 선택 (학과 선택 후 교과목을 비동기적으로 가져오기)
    $("#subjctSelect").change(function () {
        let selectSubject = $(this).val();
        $.ajax({
            url: '/professor/lecture/courseList',
            method: 'GET',
            dataType: 'json',
            data: { subjctNo: selectSubject },
            success: function (resp) {
                // 교과목 선택
                $("#courseSelect").val(selectedCourse).change();
            },
            error: function (error) {
                console.error('에러 발생 !', error);
            }
        });
    });

    // 학점 입력
    $("#selectSubjectCourseCoursePnt").val(selectSubjectCourseCoursePnt);

    // 건물 선택
    $("#buldSelect").val(buldSelect).change();

    // 강의실 선택 (건물 선택 후 강의실을 비동기적으로 가져오기)
    $("#buldSelect").change(function () {
        let buldSelect = $(this).val();
        $.ajax({
            url: '/professor/lecture/facilitiesList',
            method: 'GET',
            dataType: 'json',
            data: { buldNo: buldSelect },
            success: function (resp) {
                // 강의실 선택
                $("#fcltsSelect").val(fcltsSelect).change();
            },
            error: function (error) {
                console.error('에러 발생 !', error);
            }
        });
    });
	
	// 호실 선택
	$("#fcltsSelect").val(fcltsSelect).change(); 

    // 학기 선택
    $("#semstrSelect").val(semstrSelect).change();

    // 강의형태 선택
    $("#lctreSelect").val(lctreSelect).change();

    // 이수구분 선택
    $("#compleSelect").val(compleSelect).change();

	// 성적평가기준
	 $("#middleTest").val(middleTest);
	 $("#finalTest").val(finalTest);
	 $("#homework").val(homework);
	 $("#attendance").val(attendance);

	// 강의계획서
	$("#lectureTarget").val(lectureTarget);
	$("#lecturePlan").val(lecturePlan);
	$("#lectureNote").val(lectureNote);
	
	$('.add-button').trigger('click');
	$('.add-button').trigger('click');
	
	$("[name='lectureRequestInfoVOList[0].tmtbSe']").val('A1');
	$("[name='lectureRequestInfoVOList[1].tmtbSe']").val('A2');
	$("[name='lectureRequestInfoVOList[2].tmtbSe']").val('A3');
}













