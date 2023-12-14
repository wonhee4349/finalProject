<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/gridstack.js/9.2.2/gridstack.min.css" integrity="sha512-c0A8wU7VMWZRt4qqDc+AxarWQ2XFsQcrJGABe1eyCNxNfj2AI4+YYTytjGlHdrSk7HxA4jHakbXLzw/O/W5WmA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/gridstack.js/9.2.2/gridstack-extra.min.css" integrity="sha512-287EQpO1sItRDNvuCUARDlhpQs3qLRCMaidpOKp5BFu6EgcX3XxB92jmTvdXWW57Q9ImHcYqIHKx12EATT3sPA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link href="${pageContext.request.contextPath }/resources/css/wh_style.css" rel="stylesheet">

<script type="module" src="https://unpkg.com/ionicons@4.5.10-0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule="" src="https://unpkg.com/ionicons@4.5.10-0/dist/ionicons/ionicons.js"></script>

<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gridstack.js/9.2.2/gridstack-all.min.js" integrity="sha512-5HTU6mahI/Gm/8o09h6r4B4mTypnVZnM0VgWqLVKJDgBxqbxt7JTqraIdGINJ3fp/5ek/k73kmAE6UitSWPZhw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>

<style>
  .grid-stack { background: #transparent; } /*background: FAFAD2;*/
  .grid-stack-item-content { background-color: #transparent; text-align: center; font-size: 14px; }
  .grid-stack-item-removing {
    opacity: 0.8;
    filter: blur(5px);
  }
  #trash {
    background: rgb(217 228 245);
    border-radius: 10px;
    color:#2b65bb !important;
  }
  .userName {font-size: 25px;}
  .widget_a:focus, .widget_a:hover{text-decoration: none; color: inherit;}
  .widget_a{color: inherit;}
 .list_box{position: relative;padding-bottom: 36px;background: #ffffff;border-radius: 15px;/* margin-right: 28px; */color: #000;/* float: left; */transition: .7s;box-shadow: 0 .15rem 1.75rem 0 rgba(58,59,69,.15)!important;width: 80% !important;top: 19px;padding: 20px 20px;margin: 0 auto;height: 200px;text-align: left;}
.icon{float:right; position:relative; }
.tit{font-size:20px;}

.bot{width:100%;}
.bg01{background:#6199ed; color:#fff;}
.bg02{background:#0abfbb; color:#fff;}
.arrow{
    position: relative;
    width: 37px;
    height: 23px;
    border-radius: 50vh;
    background: #f3f3f3;
}
.arrow:before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: url(/resources/img/btn_arrow.png) no-repeat center;
}

</style>

<div class="col" id="empNoInfo" data-empno="${empNo }">
	<div class="row-md-2" style="margin-bottom: 10px;">
   		<a class="btn btn-outline-info me-1 mb-1 ft_right" onClick="saveFullGrid()" href="javascript:;" style="margin-top: 17px;">저장</a>
		<a class="btn btn-outline-info me-1 mb-1 ft_right" onclick="fcompact()" href="javascript:;" style="margin-right:10px; margin-top: 17px;">정렬</a>

   		<hr>
    	<h6 class="btn btn-info me-1 mb-1">위젯 추가</h6>

   		<a class="btn btn-soft-info me-1 mb-1" onClick="addMypage('mypage')" href="javascript:;">마이페이지</a>
    	<a class="btn btn-soft-info me-1 mb-1" onClick="addCalendar('calendar')" href="javascript:;">학사일정</a>
   		<a class="btn btn-soft-info me-1 mb-1" onClick="addComBoard('comBoard')" href="javascript:;">일반공지</a>
<!--    		<a class="btn btn-soft-info me-1 mb-1" onClick="addNoticeBoard('noticeBoard')" href="javascript:;">학사공지</a> -->
   		<a class="btn btn-soft-info me-1 mb-1" onClick="addSrarmin('saramin')" href="javascript:;">채용설명회</a>
   		<a class="btn btn-soft-info me-1 mb-1" onClick="addCertificate('certificate')" href="javascript:;">증명서</a>
		
	</div>
	<!-- gridStack Body -->
	<div class="row-md-10" style="min-height:500px;">
		
			<!-- 디비에 저장 된 내용이 있으면 불러옴 -->
			<c:if test="${!empty portletList}">
				<div>포틀릿</div>
				<div class="grid-stack" id="gridBody">
				  <div class="grid-stack-item">
				    <div class="grid-stack-item-content">
				    	<c:set var="portletList" value="${portletList}"/>
				    	<script>
					        document.addEventListener('DOMContentLoaded', function() {
					            changeWidget(jPortletList);
					        });
					    </script>
				    </div>
				  </div>
				</div>
			</c:if>
			<c:if test="${empty portletList}">
				<div class="grid-stack" id="gridBody">
<!-- 				  <div class="grid-stack-item"> -->
<!-- 				    <div class="grid-stack-item-content"> -->
<!-- 				    	<script> -->

<!-- 					    </script>			     -->
<!-- 				    </div> -->
<!-- 				  </div> -->
				</div>
				
			</c:if>
	</div>
	<div id="trash" style="padding: 5px; margin-bottom: 15px;" class="text-center">
		<div>
			<ion-icon name="trash" style="font-size: 300%"></ion-icon>
		</div>
		<div>
			<span>드래그해서 지우기</span>
      	</div>
   	</div>
</div>



<script src="/resources/js/app/msy/studentMainPage.js"></script>


<script type="text/javascript">
$(function(){	
	grid = GridStack.init({
		minRow: 1, // don't let it collapse when empty
		cellHeight: 70, // initial / auto
//       sizeToContent: true, // default to make them all fit
		acceptWidgets: true,
		removable: '#trash', // drag-out delete class
		disableOneColumnMode: true,
    });
    
    // widget 정보 가져오기 --------------------------------------------------------------------------
    
	let settings = {
		url : "/widgetLoad"
		, method : "get"
		// , data : JSON.stringify(data)
		, dataType : 'json'
		// , contentType : 'application/json; charset=utf-8'
		, success : function(resp){
			//console.log("success function 첫줄");
			var portletList = resp.portletList;
			
			if(portletList?.length>0){
				loadGrid(portletList);
				calDraw();
			}else{
				
				loadGrid(items);
				calDraw();
			}
		}
		, error : function(xhr, status, err){
			console.log(err);
			alert("잘못된 요청 발생!");
		}
	};
	$.ajax(settings);

	// widget 정보 가져오기 끝 --------------------------------------------------------------------------
    
   
    
    

});

</script>






