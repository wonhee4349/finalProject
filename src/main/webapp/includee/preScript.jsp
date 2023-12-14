<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!-- Custom fonts for this template-->
<link href="${pageContext.request.contextPath }/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="${pageContext.request.contextPath }/resources/css/sb-admin-2.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/resources/css/layout.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/resources/css/font.css" rel="stylesheet">

<script src="${pageContext.request.contextPath }/resources/vendor/jquery/jquery.min.js"></script>

<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.debug.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="/resources/js/notify.min.js"></script>
<security:csrfMetaTags/>
	<c:set value="${pageContext.request.serverName }" var="serverSideAddress" />
<script>
	const SERVERSIDEADDRESS = '${serverSideAddress}';

	const csrfValue = $("meta[name='_csrf']").attr("content");
	const csrfHeader = $("meta[name='_csrf_header']").attr("content");
	
	
	$.ajaxSetup({
		headers : {
		[csrfHeader] : csrfValue
		}
   });

</script>