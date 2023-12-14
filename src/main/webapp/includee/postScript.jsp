<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Bootstrap core JavaScript-->
	<script src="${pageContext.request.contextPath }/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="${pageContext.request.contextPath }/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/jquery.serializejson.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="${pageContext.request.contextPath }/resources/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="${pageContext.request.contextPath }/resources/vendor/chart.js/Chart.min.js"></script>
	<script src="/resources/js/ckeditor/ckeditor.js"></script>
	<!-- Page level custom scripts -->
<%-- 	<script src="${pageContext.request.contextPath }/resources/js/demo/chart-area-demo.js"></script> --%>
<%-- 	<script src="${pageContext.request.contextPath }/resources/js/demo/chart-pie-demo.js"></script> --%>


<c:if test="${ not empty retrievePage }">
	<script src="/resources/js/app/ksh/staffRetrieve.js"></script>
</c:if>