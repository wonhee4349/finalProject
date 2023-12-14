/**
 * <pre>
 * 학생 전체성적 조회 페이지에서 사용하는 자바스크립트
 * </pre>
 * @author 김석호
 * @since 2023. 11. 16.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일       수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 11. 16.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 

function makeTrtags(data){
	let code = `
		<tr>
			<td>${data.lctreNo}</td>
			<td>${data.lecture.course.coursePnt}</td>
			<td>${data.lecture.course.courseNm}</td>
			<td>${data.printScore}</td>
			<td>${data.lecture.complSe}</td>
		</tr>
	`;
	return code;
}

$(()=>{
	$('#semCdForm').on('submit',function(e){
		e.preventDefault();
		let semCd = this.semCd.value;
//		console.log("선택된 학기코드 : ",semCd);
		
		let settings = {
			url : `/student/ajax/scoreList/${semCd}`
			, method : "get"
			, dataType : "json"
			, success : function(res){
//				console.log("success function 첫줄")
//				console.log(res);
				let code = "";
				if(res.dataList?.length > 0){
					$.each(res.dataList,function(i,v){
						code += makeTrtags(v);
					});
					let rank = res.rank;
					$('#subjectRankArea').html(rank.subjctRank);
					$('#subjectPeopleArea').html(rank.subjctPeople);
					$('#subjectGradeRankArea').html(rank.subjctGradeRank);
					$('#subjectGradePeopleArea').html(rank.subjctGradePeople);
				}else{
					code += `
						<tr>
							<td colspan="5">해당학기 성적 정보없음</td>
						</tr>
					`;
					$('#subjectRankArea').html('');
					$('#subjectPeopleArea').html('');
					$('#subjectGradeRankArea').html('');
					$('#subjectGradePeopleArea').html('');
				}
//				console.log(res.calScore);
				$('#calScoreArea').text(res.calScore);
				$('#scoreListBody').html(code);
			}
			, error : function(xhr, status, err){
				console.log(err);
			}
		}
		$.ajax(settings);
		
	})
	
	$('#semCd').on('change',function(){
		let data = this.value;
//		console.log("옵션데이터 : ",data);
		if(data){
			$('#semCdForm').submit();
		}else{
			let resetHTMLCode = `<tr>
				<td colspan="5">학기를 선택해주세요</td>
			</tr>`;
			$('#scoreListBody').html(resetHTMLCode);
			$('#calScoreArea').text('');
			$('#subjectRankArea').html('');
			$('#subjectPeopleArea').html('');
			$('#subjectGradeRankArea').html('');
			$('#subjectGradePeopleArea').html('');
		}
	});
	
})
