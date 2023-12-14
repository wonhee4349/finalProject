/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2023. 11. 23.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 23.      이재혁       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
$(function(){

    $("#noticeList").on("click", function(event){
        let settings = {
            url : "/staff/staffNoticeListUI",
            method : "Get",
            dataType : "html"
        };
        $.ajax(settings).done(function(resp){
            $(content).html(resp);
        });
    });
    
});
