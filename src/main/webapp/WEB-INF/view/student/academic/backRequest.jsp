<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<div class="container-fluid">
	<div class="card2">
		<div class="sub_tit02">복학 신청</div>
		<div class="sub_top_wrap"></div>
		<div>
			<form id="registerForm">
				<input type="hidden" value='01' name="sknrgSe" readonly/>
				<input type="hidden" value='${semCd }' name="targetSem" readonly/>
				<table class="table table_style04 mt-4 table_center hover_none" id="dataTable" width="100%" cellspacing="0">
					<colgroup>
						<col width="25%">
					</colgroup>
					<tr>
						<th>복학학기</th>
						<td>
							<input type="text" value='${semCd }' class="form-style01" disabled/>
						</td>
					</tr>
				</table>
				<input type="submit" class="btn btn-primary ft_right mt-4"
					value="복학신청하기" />
			</form>
		</div>
	</div>
</div>

<script src="/resources/js/app/ksh/registerRequest.js"></script>
