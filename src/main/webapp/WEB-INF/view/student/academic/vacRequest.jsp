<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 추가한 스크립트 -->
<c:if test="${not empty message }">
	<script>
		Swal.fire({
			title: "사용할 수 없는 메뉴",
			icon: "info",
			html: "${message}",
			showConfirmButton: false,
           	timer: 1500
			});
			setTimeout(function() {
				location.href = "/student";
            }, 1500);
	</script>
</c:if>

   <!-- Begin Page Content -->
       <div class="container-fluid">

           <div class="card2">
               <div class="sub_tit02">휴학신청</div>
               <div class="sub_top_wrap">

               </div>
		<security:authentication property="principal" var="principal"/>
                        <div>
							<form id="absenceForm">
			                       <input type="hidden" value="${principal.realUser.stdntNo }" name="stdntNo" class="form-style01" readonly />
                            	<table class="table table_style04 mt-4 table_center hover_none" id="dataTable" width="100%" cellspacing="0">
	                                <colgroup>
			                        		<col width="25%">
			                       </colgroup>
	                                <tr>
	                                    <th>휴학사유</th>
										<td>
											<input type="text" required name="abssklCn" class="form-style01 ht300 scroll-y"></input>
										</td>
	                                </tr>
	                                <tr>
	                                    <th>복학예정연도</th>
										<td><input type="text" value='' name="abssklYr" class="form-style01" maxlength="4" placeholder="예) 25년도 : 2025" /></td>
	                                </tr>
	                                <tr>
	                                    <th>복학예정연도 및 학기</th>
										<td><input type="text" value='' name="abssklSemstr" class="form-style01" maxlength="5" placeholder="예) 25년도 1학기 : 20251" /></td>
	                                </tr>
								</table>
							 <input type="submit" class="btn btn-primary ft_right mt-4" value="제출하기"/>
							</form>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->


<script src="/resources/js/app/ksh/vacRequest.js"></script>                
                
                