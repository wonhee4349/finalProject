/**
 * <pre>
 * 
 * </pre>
 * @author 김원희
 * @since 2023. 11. 15.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 20.      김원희       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

const whModal = document.querySelector("#wonhee_modal");
const pagingArea = document.querySelector("#pagingArea");
const whDate = document.querySelector("[whDate]");
const whTime = document.querySelector("[whTime]");
const whCont = document.querySelector("[whCont]");
const whGubun = document.querySelector("[whGubun]"); 
// 전역함수
function whSelChg(select) {
    console.log("whSelChg 함수 호출됨");
    
    let selValue = select.value;

    let trs = document.querySelectorAll("#listBody > tr");

    if (selValue == "allList") {
        $("#listBody > tr").css("display", "table-row");
        return;
    }

    
    for (let i = 0; i < trs.length; i++) {
        let tr = trs[i];
        let firstTd = tr.children[0];

        if (firstTd.innerHTML != selValue) {
            tr.style.display = "none";
        } else {
            tr.style.display = "table-row";
        }
    }
}



    function fWhTrClick(pTr){
		  console.log("pTr:", pTr);
        whModal.style.display="block";
        whDate.value = pTr.children[2].innerHTML;
        whCont.value = pTr.children[1].textContent;

 		whTime.value = pTr.children[3].innerHTML;  
	
	
	

    } 

    function fWhModalHide(){
        whModal.style.display="none";
    }
   function makeTrTag(consultation){
    console.log("consultation.cnsltDtls:",consultation);
	  let cnsltSeText = consultation.cnsltSe === "01" ? "학사상담" : "취업상담";

    let trTag = `
        <tr data-consultNo="${consultation.cnsltNo}" onclick="fWhTrClick(this)">
            <td>${cnsltSeText}</td>
			<td class="text_left"><span class="text_min">${consultation.whMsg}</span></td>
            <td>${consultation.cnsltRequstDate}</td>
            <td>${consultation.cnsltRequstTime}</td>
			
        </tr>
    `;

    return trTag;
};


// 아작스 리스트 요청 함수
function getList(){
    // 처음엔 디버깅이 편하도록 필요한 값이 다 눈에 들어오겡,
    // 리팩토링(정리)은 기능 구현이 끝나면...

    // 서버에 보낼 데이타	
    let sendData = {
        searchType:$("#searchFormsearchType").val() ,
        searchWord:$("#searchFormSearchWord").val(),
        currentPage: $("#searchFormPage").val()
    }	


	
	//alert("sendData : " + JSON.stringify(sendData));

    $.ajax({
        type:"post",
        url:"/student/consulting/consultingFinishList",
        async:true,
        data:sendData,
        dataType:"json",  // JSON.parse("JSON형식의 문자열") => 자바스크립트객체가됨({}나 [])
        success:function(resp){
            console.log("서버가 준값",resp); // 항상 눈으로 확인
            let consultingList = resp.dataList;
            let trTags = null;
             if (consultingList.length > 0) {
                $.each(consultingList, function(idx, consultation){
                        trTags += makeTrTag(consultation);
                });
                $(pagingArea).html(resp.pagingHTML);
            } else {
                trTags += `
                    <tr>
                        <td colspan="5" style="text-align: center;">등록된 상담이 없습니다.</td>
                    </tr>
                `;
                $(pagingArea).empty();
            }

            $("#listBody").html(trTags);
        },
        error:function(xhr){
            console.log("Error",xhr.status);
        }
    })


}

 $(function () {
    getList();

    $("#searchUI").on("click", "#searchBtn", function (event) {
        let inputs = $(this).parents("#searchUI").find(":input[name]");
        $.each(inputs, function (idx, ipt) {
            let name = ipt.name;
            let value = $(ipt).val();
            $(searchForm).find(`:input[name=${name}]`).val(value);
        });
        $(searchForm).submit();
    });



//------------------------------------상담내역 디테일

$("#modal_open").on("show.bs.modal", function(event){
            alert("ppp");
			let $modal = $(this);
			let trTag = event.relatedTarget;
			let what = $(trTag).data("clubNo");
			let url = "/student/consulting/ajax/consultationView?what="+what;
			$.get(url)
				.done(function(resp){
					$modal.find(".modal-body").html(resp);
                    $("#modal_open").modal('show');
				});
		}).on("hidden.bs.modal", function(event){
			$(this).find(".modal-body").empty();
		});



    $("#modal_openClose").click(function () {
        $("#modal_open").modal('hide');
    });

    function fn_paging(page) {
        searchForm.page.value = page;
        searchForm.requestSubmit();
        searchForm.page.value = "";
    }
});


    $("#searchUI").on("click", "#searchBtn", function(event){
        let inputs = $(this).parents("#searchUI").find(":input[name]");
        $.each(inputs, function(idx, ipt){
            let name = ipt.name;
            let value = $(ipt).val();
            $(searchForm).find(`:input[name=${name}]`).val(value);
            });
            $(searchForm).submit();
    });
        
    //------------------------------------------------------------------------------------------------
        
   

 
    
    //------------------------------------------------------------------------------------------------
       
	

function fn_paging(page){
    searchForm.page.value = page;
    searchForm.requestSubmit();
    searchForm.page.value = "";
}