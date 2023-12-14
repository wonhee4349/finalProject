/**
 * <pre>
 * 학생 왼쪽 메뉴바에 링크를 걸어주는 JS
 * </pre>
 * @author 김석호
 * @since 2023. 11. 10.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일       수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 10.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 

$(()=>{

	$(".contentLink").on("click",function(){
		let link = $(this).data('link');
		let settings = {
			url : link,
			method : "GET",
			dataType : "html"
		};
		$.ajax(settings).done(function(cont){
			$(content).html(cont);
		})

	})
})