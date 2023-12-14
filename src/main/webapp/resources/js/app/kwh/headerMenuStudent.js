/**
 * <pre>
 * 
 * </pre>
 * @author 김원희
 * @since 2023. 11. 9.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 9.      김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
const fMailCount = () => {
    $.ajax({
        url: "/mail/count",
        type: "get",
        contentType: "application/json",
        success: function(resp) {
            console.log("체킁:",resp);
            $('#mailCount').text(resp.emailCount);

        },
        error: function(err) {
            console.log(err);
        }
    });
}

// DomContentLoaded
$(()=>{
    fMailCount();
})

    

