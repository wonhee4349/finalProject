/**

 * </pre>
 * @author 김재성
 * @since 2023. 11. 10.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 *  수정일         수정자       수정내용
 * --------     ------    ----------------
 * 2023.11.10.   김재성       최초작성
 * 2023.11.13.   김재성       강의내역 출력
 * 2023.11.15.   김재성       url 재수정
 * 2023.11.17.   김재성       상담 url 설정
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */  


// Template Html

// DomContentLoaded 이벤트 초기화 로직
 $(function(){
    /*
 	let searchType = $("#secarhForm")[0].searchType.value;
	let searchWord = $("#searchWord")[0].searchWord.value;

	let SearchVO = {
		searchType,
		searchWord,
		searchPage: "1"
	}
    */
 
    $("#lectureList").on("click", function(event){
		
        //틀만 가져오깅
        $.ajax({
            type:"get",
            url:"/professor/lectureListUI",
            //data: SearchVO,
            dataType:"html",
            //async:false,    // 틀이 들어가야 데이타를 넣을 수 있으므로
            success: function(rslt){
                $(content).html(rslt); 
            },
            error: function(xhr){
                console.log(xhr.status);
            }
        });


        /* 
        // 데이타 가져오깅, 검색조건이 없는 상황
        $.ajax({
    		url:'/professor/ajax/lectureList',
    		method :"GET",
//    		data: data,
    		type : "JSON",
    		success : function(rslt){
              console.log("체킁:",rslt); // 누느로 분석	

                let listTags = ""; 
                for(let i=0; i < rslt.length; i++){
                    listTags += makeTags(rslt[i])
                }

                // alert(listTags);
  			    $("#listBody").html(listTags);
    			
			},
    		error : function(xhr){
				console.log(xhr.status);
			}
    	})
        */
    });
    
   //교과목 리스트
    $("#courseList").on("click", function(event){
        
        $.ajax({
            type:"get",
            url:"/professor/courseListUI",
            dataType:"html",
            //async:false,
            success: function(rslt){
                $(content).html(rslt); 
            },
            error: function(xhr){
                console.log(xhr.status);
            }
        })
    }); 
    
    //상담신청 
    $("#consultationRequest").on("click", function(event){
        
        $.ajax({
            type:"get",
            url:"/professor/consultation/professorConsultationRequestListUI",
            dataType:"html",
            //async:false,
            success: function(rslt){
                $(content).html(rslt); 
            },
            error: function(xhr){
                console.log(xhr.status);
            }
        })
    });   
	
	//상담이력
    $("#consultation").on("click", function(event){
        
        $.ajax({
            type:"get",
            url:"/professor/consultation/professorConsultationUI",
            dataType:"html",
            //async:false,
            success: function(rslt){
                $(content).html(rslt); 
            },
            error: function(xhr){
                console.log(xhr.status);
            }
        })
    });    
});

