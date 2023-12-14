/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 9.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){

	// 동아리 UI
    $("#clubList").on("click", function(event){
        let settings = {
            url : "/staff/club/clubListUI",
            method : "Get",
            dataType : "html"
        };
        $.ajax(settings).done(function(resp){
            $(content).html(resp);
        });
    });
    
    // 재학생 UI
    $("#studentList").on("click", function(event){
        let settings = {
            url : "/staff/student/studentListUi",
            method : "Get",
            dataType : "html"
        };
        $.ajax(settings).done(function(resp){
            $(content).html(resp);
        });
    });
    
    // 졸업생 UI
    $("#graduationList").on("click", function(event){
        let settings = {
            url : "/staff/graduation/graduationListUI",
            method : "Get",
            dataType : "html"
        };
        $.ajax(settings).done(function(resp){
            $(content).html(resp);
        });
    });
    
    // 장학금 UI
    $("#scholarshipList").on("click", function(event){
        let settings = {
            url : "/staff/scholarship/scholarshipListUI",
            method : "Get",
            dataType : "html"
        };
        $.ajax(settings).done(function(resp){
            $(content).html(resp);
        });
    });
    
    // 상담 신청 UI
    $("#consultationRequestList").on("click", function(event){
        let settings = {
            url : "/staff/consultation/consultationRequestListUI",
            method : "Get",
            dataType : "html"
        };
        $.ajax(settings).done(function(resp){
            $(content).html(resp);
        });
    });
    
    // 상담 UI
    $("#consultation").on("click", function(event){
        let settings = {
            url : "/staff/consultation/consultationUI",
            method : "Get",
            dataType : "html"
        };
        $.ajax(settings).done(function(resp){
            $(content).html(resp);
        });
    });
    
    // 장학생 UI
    $("#scholarshipStudent").on("click", function(event){
        let settings = {
            url : "/staff/scholarshipStudent/scholarshipStudentListUI",
            method : "Get",
            dataType : "html"
        };
        $.ajax(settings).done(function(resp){
            $(content).html(resp);
        });
    });
    
    // 동아리 개설 신청 UI
    $("#clubRequestList").on("click", function(event){
        let settings = {
            url : "/staff/club/clubRequestListUI",
            method : "Get",
            dataType : "html"
        };
        $.ajax(settings).done(function(resp){
            $(content).html(resp);
        });
    });
    
    // 신입생 등록 UI
    $("#studentInsert").on("click", function(event){
        let settings = {
            url : "/staff/student/studentInsertUI",
            method : "Get",
            dataType : "html"
        };
        $.ajax(settings).done(function(resp){
            $(content).html(resp);
        });
    });
    
    //  납부대상자	 UI
    $("#tuitionStudent").on("click", function(event){
        let settings = {
            url : "/staff/tuitionStudent/tuitionStudentListUI",
            method : "Get",
            dataType : "html"
        };
        $.ajax(settings).done(function(resp){
            $(content).html(resp);
        });
    });
    
});