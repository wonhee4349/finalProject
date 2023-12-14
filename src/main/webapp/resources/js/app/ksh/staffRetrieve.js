/**
 * <pre>
 * 교직원 통계페이지에서 사용할 스크립트
 * </pre>
 * @author 김석호
 * @since 2023. 12. 08.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일       수정자       수정내용
 * --------     --------    ----------------------
 * 2023. 12. 08.      김석호       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */


var subjectPeopleBox = document.querySelector('#subjectPeopleCountChart');
var absenceBox = document.querySelector('#absenceStudentListData');

function number_format(number, decimals, dec_point, thousands_sep) {
  // *     example: number_format(1234.56, 2, ',', ' ');
  // *     return: '1 234,56'
  number = (number + '').replace(',', '').replace(' ', '');
  var n = !isFinite(+number) ? 0 : +number,
    prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
    sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
    dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
    s = '',
    toFixedFix = function(n, prec) {
      var k = Math.pow(10, prec);
      return '' + Math.round(n * k) / k;
    };
  // Fix for IE parseFloat(0.55).toFixed(0) = 0;
  s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
  if (s[0].length > 3) {
    s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
  }
  if ((s[1] || '').length < prec) {
    s[1] = s[1] || '';
    s[1] += new Array(prec - s[1].length + 1).join('0');
  }
  return s.join(dec);
}

$(()=>{
	if(subjectPeopleBox){
		getInitDataList();
	}
	if(absenceBox){
		console.log("휴학생 페이지~ㅎㅎ");
	}
	
	
});
var varDataList = {};
function getInitDataList(){
	$.ajax({
		url : '/staff/retrieve/ajax/studentListOnSubject'
		, dataType : 'json'
		, success : function(resp){
			varDataList = resp;
			rendersubjectPeopleChart(varDataList.subjectPeople);
		}
		, error : function(xhr, status, err){
			console.log(err);
		}
	});
	
	
}

function rendersubjectPeopleChart(param){
	console.log("핸들링 해야할 데이터 : ",param)
	let subjectList = [];
	let subjectData = [];
	let realDataDataset = [];
	// 학년별 데이터 들어갈 곳
	let grade1Data = {
		label : "1학년"
		, backgroundColor : "#ffbd23"
		, data : []
	};
	let grade2Data = {
		label : "2학년"
		, backgroundColor : "#1cc88a"
		, data : []
	};
	let grade3Data = {
		label : "3학년"
		, backgroundColor : "#36b9cc"
		, data : []
	};
	let grade4Data = {
		label : "4학년"
		, backgroundColor : "#4e73df"
		, data : []
	};
	$.each(param,function(i,v){
		if(!subjectList.includes(v.SUBJCT_NM)){
			subjectList.push(v.SUBJCT_NM);
		}
	});
	console.log("학과리스트 : ",subjectList);
	for(let idx = 0 ; idx < param.length ; idx=idx+4){
		grade1Data.data.push(param[idx].CNT);
		grade2Data.data.push(param[idx+1].CNT);
		grade3Data.data.push(param[idx+2].CNT);
		grade4Data.data.push(param[idx+3].CNT);
	}
	//console.log(subjectData);
	realDataDataset.push(grade1Data);
	realDataDataset.push(grade2Data);
	realDataDataset.push(grade3Data);
	realDataDataset.push(grade4Data);
	
	let realData = {};
	realData.labels = subjectList;
	realData.datasets = realDataDataset;
	var subjectPeopleChart = new Chart(subjectPeopleBox,{
		type : "bar"
		,options : {
			scales: {
				xAxes: [{
					gridLines: {
						offsetGridLines: true
					}
				}]
				, yAxes: [{
					ticks: {
						min: 0
						, max: 60
						, fontSize : 14
						,stepSize : 10
					}
				}]
			}
		}
		, data : realData
	});
}

