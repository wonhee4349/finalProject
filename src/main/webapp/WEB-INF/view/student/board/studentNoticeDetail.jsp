<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<link href="${pageContext.request.contextPath }/resources/css/wh_style.css" rel="stylesheet">

<style>
.ht250{height:250px;}
select, input{width:80% !important; margin:0 auto;}
table.hover_none tr:hover{background:none !important;}
</style>    
<!-- Begin Page Content -->
<div class="container-fluid">
    <div class="card2">
        <div class="sub_tit02">공지사항</div>

   
        <sec:authentication property="principal" var="principal"/>          
         
	<div class="view-title">${board.boTitle}</div>


	<div class="view-detail">
            <div class="view-util">
            	<dl class="writer">
                    <dt>작성자</dt>
                    <dd>
                    	${board.staff.sklstfNm}
					</dd>
				</dl>
				<dl class="count">
                    <dt>조회수</dt>
                    <dd> ${board.boCnt}</dd>
                </dl>
                <dl class="write">
                    <dt>등록일</dt>
                    <dd>${board.boDate}</dd>
                </dl>
            </div>
    </div>        
            <br>
		<div class="notice_text_box">
		<pre>${board.boCn}</pre>
		
		</div>	
           
		<a href="/student/board/studentCommonNoticeList"><button class="btn btn-navy-primary mt-4 ft_right btn_style_round">목록</button></a>
            
    </div>
</div>
<!-- /.container-fluid -->

<script src="/resources/js/app/kwh/studentCommonNoticeList.js"></script>