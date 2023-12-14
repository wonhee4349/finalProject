<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 

<security:authorize url="/student">
<c:set value="student" var="actorForMail" />
<script>
	var actorForMail = 'student';
</script>
</security:authorize>
<security:authorize url="/staff">
<c:set value="staff" var="actorForMail" />
<script>
	var actorForMail = 'staff';
</script>
</security:authorize>
<security:authorize url="/professor">
<c:set value="professor" var="actorForMail" />
<script>
	var actorForMail = 'professor';
</script>
</security:authorize>


<style>
.write_area{height:200px !important; overflow-y:scroll}

</style>
<div class="container-fluid">
    <div class="card2"> 
        <div class="sub_tit02">쪽지쓰기</div>
        <form id="mailWriteForm">
            <!-- 쪽지 작성 폼 -->
             <security:authentication property="principal" var="principal"/> 
            <div class="form-group">
            
             <input type="hidden" value="${principal.username }" name="userNo" class="form-style01" id="userId" readonly />
                <label for="emailRcver">받는 사람</label>
                <input type="text" class="form-control" id="emailRcver" name="emailReceiver" required>
            </div>
            <div class="form-group">
                <label for="subject">제목</label>
                <input type="text" class="form-control" id="emailTitle" name="emailTitle" required>
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <textarea id="emailCn"  class="form-control write_area"  name="emailContent" required></textarea>
            </div>
          <!--   <div class="form-group">
                <label for="subject">첨부파일</label>
                <input type="file" class="form-control" id="file" name="file" required>
            </div> -->
            <button type="button" id="inputDataOpen" class="btn btn-success ft_left">일괄입력</button>
       
            <button id="saveMailBtn" type="button" class="btn btn-primary ft_right mt-2" onclick="saveMail()">쪽지 보내기</button>
        </form>
    </div>
</div>

<script src="/resources/js/app/kwh/mailWrite.js"></script>


