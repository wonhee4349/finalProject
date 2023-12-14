<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
a{color:#000;}
</style>

<!-- Begin Page Content -->
               <div class="container-fluid">
                   <div class="card2">
                       <div class="sub_tit02">쪽지함</div>
                       <div class="mail_top_wrap">
                           <ul class="list_stye_round">
								<a class="contentLink" href="/${actorForMail }/mail/mailList"><li class="on">받은쪽지</li></a>
								<a class="contentLink" href="/${actorForMail }/mail/mailSendList"><li >보낸쪽지</li></a>
							</ul>
						
                       </div>
                       <div class="sub_top_wrap">    
							
							
                           <!-- selectbox -->
                           <div class="s_option">
                               <div class="select-container">
                                   <select class="custom-select02">
			                            <option value="" label="전체" />
			                            <option value="maileSend" label="보낸이" />
                                   </select>
                                   <div class="select-arrow">
                                       <i class="fa fa-caret-down"></i>
                                   </div>
                               </div>
                           </div>
                           
                           <!-- search -->
                           <div id="searchUI" class="d-none d-sm-inline-block  ml-md-3 my-2 my-md-0 navbar-search">
                               <div class="input-group wd300 ft_right">
                                   <input type="text" name="searchWord" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
                                   <div class="input-group-append">
                                       <button class="btn btn-primary" id="searchBtn" type="button">
                                           <i class="fas fa-search fa-sm" ></i>
                                       </button>
                                   </div>
                               </div>
                           </div> 

                       </div>
                       <form action="<c:url value='/${actorForMail }/mail/ajax/mail'/>" id="searchForm">
							<input type="hidden" name="searchType">
							<input type="hidden" name="searchWord">
							<input type="hidden" name="page">
						</form>
                       <div>
                           <table class="table table_style01 mt-4 table_center" id="dataTable" width="100%" cellspacing="0">
								<thead>
	                               <colgroup>
		                        		<col width="20%">
		                        		<col width="50%">
		                        		<col width="30%">
		                       		</colgroup>
	                               <tr>
							            <th>보낸이</th>
							            <th>제목</th>
							            <th>날짜</th>
	                               </tr>
								</thead>
								<tbody id="listBody"></tbody>
                               
                           </table>

							
							<button  href="javascript:;" id="mailWrite" class="btn btn-primary ft_right mt-2">쪽지보내기</button>
							<div class="clear"></div>

                           <div aria-label="Page navigation example nav_center">
                           		<span id="pagingArea"></span>
                           </div>

                       </div>
                       
                   </div>



               </div>
<script src="/resources/js/app/kwh/mail.js"></script>