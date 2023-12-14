<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body></body>


<security:authorize url="/student">
<script type="text/javascript">
console.log("학생");
var redirectTargetUrl = "/student";
</script>
</security:authorize>
<security:authorize url="/professor">
<script type="text/javascript">
console.log("교수");
var redirectTargetUrl = "/professor";
</script>
</security:authorize>
<security:authorize url="/staff">
<script type="text/javascript">
console.log("교직원");
var redirectTargetUrl = "/staff";
</script>
</security:authorize>
<security:authorize access="isAnonymous()">
<script type="text/javascript">
console.log("익명사용자");
var redirectTargetUrl = "/";
</script>
</security:authorize>

<script>
	Swal.fire({
		icon: "error",
		title: "권한 없음",
		text: "요청하신 페이지에 대한 권한이 없습니다.",
	}).then((res)=>{
		location.href = redirectTargetUrl;
	});
</script>
</html>