/**
 * <pre>
 * 
 * </pre>
 * @author 문선영
 * @since 2023. 11. 17.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 17.      문선영       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
 $(function(){
	//------------------------------------------------------------------------------------------------

    function makeStudentTrTag(student){
        let trTag = `
            <tr data-student-no="${student.stdntNo}">
                <td>${student.subject.college.clgNm}</td>
                <td>${student.sknrgSttusMajor1}</td>
                <td>${student.stdntNo}</td>
                <td>${student.sknrgSttusGrade}학년</td>]
                <td>${student.stdntNm}</td>]
                <td>${student.stdntTelno}</td>]
            </tr>
        `;
        return trTag;
    };
    
    function makeScholarshipTrTag(scholarship){
        let trTag = `
            <tr data-student-no="${scholarship.schlshipNo}">
                <td>${scholarship.scholarshipList.schoNm}</td>
                <td>${scholarship.semstrYear}년</td>
                <td>${scholarship.semstrNo}학기</td>
                <td>${scholarship.schlshipAmount}만원</td>
                <td>${scholarship.pymntSe}</td>
            </tr>
        `;
        return trTag;
    };
    
    //------------------------------------------------------------------------------------------------
	
    $("#modalSearchForm").on("submit", function(event){
        event.preventDefault();
        let data = $(this).serialize();
        $.getJSON(`/staff/scholarshipStudent/ajax/scholarshipStudentInsertData?${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거
            let trTags = null;
            console.log("확인 : ", resp.studentInfo)
            if(resp.studentInfo!=null){
				trTags += makeStudentTrTag(resp.studentInfo);
				$("#stdntNo").val(resp.studentInfo.stdntNo);
            }else{
                trTags += `
                    <tr>
                    	<td colspan="6" style="text-align: center;">학생을 선택해주세요.</td>
                    </tr>
                `;
                $(pagingArea).empty();               
            } 
            $("#modalStudentBody").html(trTags);
        });
       return false;
    }).submit();

    //------------------------------------------------------------------------------------------------
	
    $("#modalSearchUI").on("click", "#modalSearchBtn", function(event){
        let inputs = $(this).parents("#modalSearchUI").find(":input[name]");
        $.each(inputs, function(idx, ipt){
            let name = ipt.name;
            let value = $(ipt).val();
            $("#modalSearchForm").find(`:input[name=${name}]`).val(value);
            });
            $("#modalSearchForm").submit();
    });
        
    //------------------------------------------------------------------------------------------------
    
    $("#scholarshipList").on("change", function(event){
        let data = $(this).val();
        $.getJSON(`/staff/scholarshipStudent/ajax/scholarshipInsertData?scholarship=${data}`, function(resp){   // 요청 URL 설정 controller에 정해놓은거            
            let trTags = null;;
            if(resp.scholarshipInfo!=null){
				trTags += makeScholarshipTrTag(resp.scholarshipInfo);
				$("#schlshipNo").val(resp.scholarshipInfo.schlshipNo);
            }else{
                trTags += `
                    <tr>
                    	<td colspan="6" style="text-align: center;">장학금을 선택해주세요.</td>
                    </tr>
                `;
                $(pagingArea).empty();             
            } 
            $("#modalScholarshipBody").html(trTags);
        });
       return false;
    }).trigger("change");

    //------------------------------------------------------------------------------------------------
	
	$("#scholarshipStudentInsertForm").on("submit", function(e){
		e.preventDefault();
		let data = $(this).serialize();
		let settings = {
			url : "/staff/scholarshipStudent/insertScholarshipStudent"
			, method : "POST"
			, data : data
			// , data : JSON.stringify(data)
			, dataType : 'json'
			// , contentType : 'application/json; charset=utf-8'
			, success : function(resp){
				let icon = null;
				let title = null;
				if(resp.success){
					title = "성공했습니다!";
					icon = "success";
				}else{
					title = "실패했습니다!";
					icon = "error";
				}
				Swal.fire({
					title: title,
					icon: icon
				}).then((res)=>{
					if(resp.success){
						location.reload();
					}
				});
			}
			, error : function(xhr, status, err){
				console.log(err);
					alert("잘못된 요청 발생!");
			}
		};
		$.ajax(settings)
	});
	
	//------------------------------------------------------------------------------------------------
	
 });
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 