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
.mail_con{height:250px; overflow-y:scroll}
.s_tit{font-size:17px; margin-bottom:17px;}
.blue_txt{color:#568cd9}
.under_line{border-bottom:1px solid #f2f2f2;}
</style>

<div class="container-fluid">
    <div class="card2"> 
        <div class="sub_tit02">쪽지보기</div>
        <form id="mailWriteForm">
            <!-- 쪽지 작성 폼 -->
             <security:authentication property="principal" var="principal"/> 
            <div class="form-group">
             <input type="hidden" value="${principal.username }"  name="userNo" class="form-style01" id="userId" readonly />
                <div class="s_tit blue_txt">보낸사람</div>
                <div id="emailSender" name="emailSender" class="under_line form-control">${email.emailSender}</div>
            </div>
            <div class="form-group">
                <div class="s_tit blue_txt">제목</div>
                <div class="form-control" id="emailTitle" name="emailTitle">${email.emailTitle}</div>
            </div>
            <div class="form-group">
                <div class="s_tit blue_txt">내용</div>
				<div class="form-control mail_con" id="emailCn" name="emailCn">
					${email.emailCn}</div>

			</div>
			<input type="hidden" value="${email.emailNo }" id="emailNo"  name="emailNo"/>
			<!-- 삭제 버튼 추가 -->
            <button type="button" id="deleteMailBtn" class="btn btn-danger ft_right" data-emailNo="${email.emailNo}" >삭제</button>

        </form>
    </div>
</div>
<script src="/resources/js/app/kwh/mailSend.js"></script>

