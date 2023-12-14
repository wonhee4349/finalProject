<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<style>
.ht250{height:250px;}
select, input{width:80% !important; margin:0 auto;}
table.hover_none tr:hover{background:none !important;}
.btn_wrap{width:100%; height:60px;}
</style> 



   
<!-- Begin Page Content -->
<div class="container-fluid">
    <div class="card2">
        <div class="sub_tit02">학사 공지</div>

   
        <sec:authentication property="principal" var="principal"/>          
            <!--  <table class="table table_style01 table_center hover_none">
                <colgroup>
                    <col width="25%">
                    <col width="25%">
                    <col width="25%">
                </colgroup>
                <tr>
                    <th>공지 제목</th>
                    <td>
                        ${board.boTitle}
                    </td>   
                     <th>작성자</th>
                    <td>
                        ${board.staff.sklstfNm}
                    </td>    
                </tr>
            <tr>
               <th>작성일</th>
               <td>
                 ${board.boDate}
               </td>
               <th>조회수</th>
               <td>
                  ${board.boCnt}
               </td>
            </tr>
         </table>

            <br>

            <table class="table table_style01 table_center">
                <tr>
                    <th colspan="4">내 용</th>
                </tr>
                <tr>
                    <td colspan="4">
                    <pre> ${board.boCn}</pre>
                    </td>
                </tr>
            </table>-->
       
       
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
                
<div class="btn_wrap"><a class="collapse-item" href="${pageContext.request.contextPath}/professor/professorNoticeListUI"><button class="btn btn-navy-primary mt-4 ft_right btn_style_round">목록</button></a></div>
     
            
    </div>
</div>
<!-- /.container-fluid -->

<script src="/resources/js/app/ljh/professorNoticeData.js"></script>
<script src="/resources/js/app/ljh/professorNoticeDelete.js"></script>
