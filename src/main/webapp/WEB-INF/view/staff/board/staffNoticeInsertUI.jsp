<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>

</style>
<div class="container-fluid">
    <div class="card2">
        <div class="sub_tit02">공지 등록</div>
        <div class="sub_top_wrap">
        </div>
        <security:authentication property="principal" var="principal"/>
        <div id="all">
            <form id="noticeForm" action="/staff/board/staffNoticeData" method="post"> <!-- Add action and method -->
           <table class="table_style01 mt-4">
                <!-- 교번 자동 생성 -->
                <input type="hidden" name="boNo" class="form-style01" placeholder="자동 생성 됩니다" readonly/>                         
                
                <!-- 작성자 -->
                <input type="hidden" name="boWrter" class="form-style01" placeholder="자동 입력" readonly/>
                
                <!-- 작성일 -->
                <input type="hidden" name="boDate" class="form-style01" placeholder="자동 입력" readonly/>
          <tr>      
           
               <th>게시물 구분</th>
               <td> <select id="bdse" name="bdSe" class="form-style01" style="width:400px;" required>
                	<option value label="선택" >
                    <c:forEach var="bdSe" items="${bdse}">
                        <option value="${bdSe.comCode}">${bdSe.comCodeNm}</option>
                    </c:forEach>
                </select> </td> 
           </tr>     
               
            <tr>    
                 <!-- 제목입력 -->
                <th>제목</th> 
                <td> <input id="botitle" type="text" name="boTitle" class="form-style01" placeholder="제목" style="width:400px;" required/> <td>                       
			</tr>
              
             <tr>   <!-- 내용 입력 -->
               <th>상세 내용</th>
               <td class="text_left"> <textarea style="font-size:20px;" id="bocn" rows="25" cols="80" wrap="hard" name="boCn" class="boxTA" required></textarea> </td> 
              </tr> 
              </table>    
        	 <button type="button" id="inputDataNoti" class="btn btn-primary ft_right"  style="margin-top:24px; margin-right:24px; margin-left:20px;">일괄입력</button> 
              <input type="submit" class="btn btn-primary ft_right mt-4" value="등록하기"/> 
           
            </form>
        </div>
    </div>
</div>

<script src="/resources/js/app/ljh/staffNoticeInsertData.js"></script>
<script>   $(function () {
    CKEDITOR.replace('sanctnSourc');
}); </script>
