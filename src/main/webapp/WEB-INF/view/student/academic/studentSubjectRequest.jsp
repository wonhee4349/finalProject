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
		<div class="sub_tit02">학과 관련 학적 변동 신청</div>
		<div class="sub_top_wrap"></div>
		<div class="blue_box">
		학적이 변동된 사항에 대하여 재신청이 불가하오니 이점 유의하시기 바랍니다.
		</div>
		<div>
		<security:authentication property="principal" var="principal"/>
		<c:set value="${principal.realUser }" var="student" />
			<form id="registerForm">
				<input type="hidden" value='${semCd }' name="targetSem" readonly/>
				<table class="table table_style03 mt-4 table_center hover_none" id="dataTable" width="100%" cellspacing="0">
					<colgroup>
						<col width="25%">
					</colgroup>
					<tr>
						<th>신청 구분</th>
						<td>
							<select name="sknrgSe" class="custom-select04">
								<c:if test="${student.sknrgSttusMajor2 eq null }">
									<option value="02">복수전공신청</option>
								</c:if>
								<c:if test="${student.sknrgSttusMinor eq null }">
									<option value="03">부전공신청</option>
								</c:if>
								<option value="04">전과신청</option>
							</select>
						</td>
					</tr>
					<tr>
						
						<th>대상 학과</th>
						<td>
							<select id="clg" class="custom-select04 mr10">
								<option value label="전체"/>
								<c:forEach items="${collegeList }" var="college">
									<option value="${college.clgNo }" label="${college.clgNm }"/>
								</c:forEach>
							</select>
							<select name="subjctNo" id="subjctNo" required class="custom-select04">
								<option value label="선택" />
								<c:forEach items="${subjectList }" var="subject">
									<option class="clg${subject.clgNo } clgSubject" value="${subject.subjctNo }" label="${subject.subjctNm }" />
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<input type="submit" class="btn btn-primary ft_right mt-4"
					value="신청하기" />
			</form>
		</div>
	</div>
</div>


<script src="/resources/js/app/ksh/registerRequest.js"></script>