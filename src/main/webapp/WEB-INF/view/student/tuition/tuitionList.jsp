<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<link href="${pageContext.request.contextPath }/resources/css/wh_style.css" rel="stylesheet">
<!-- Begin Page Content -->
<security:authentication property="principal" var="principal"/>
<style>
#studentTuitionContentBody{width : 100%}
#ifrTuitionBody{width : 100%; min-height : 500px; border:1px solid #e9e9e9;}
.option_tit{width:auto; margin-right:5px;}
#selectCurrSemCdDiv{text-align: left; float : left; display : flex;}
#printTuitionBtnDiv{display : none;}
.ml10{margin-left:10px;}
button{border:none;}
</style>

<script>
var tuitionCurrentSemesterForTuitionPage = '${currSemCd}';
var studentNameForTuitionPage = `${principal.realUser.stdntNm}`;
</script>

<div class="container-fluid">
	<div class="card2">
		<div class="sub_tit02">등록금</div>
		
		<div class="sub_top_wrap">
			
		
			<!-- selectbox -->
			<div class="s_option">
				<span class="option_tit">납부내역</span>
				<div class="select-container ft_right" style="width:130px">
					<select class="custom-select02" id="selectOptionSemCd" style="width:133px">
						<option value>학기선택</option>
						<c:forEach items="${list }" var="semCd">
							<option value="${semCd.key }" label="${semCd.value }" />
						</c:forEach>
					</select>
					<div class="select-arrow">
						<i class="fa fa-caret-down"></i>
					</div>
				</div>
			</div>
			<div id="selectCurrSemCdDiv ft_left"><button id="selectCurrSemCdBtn" class="btn btn-primary ml10">다음학기등록금조회</button></div>
		</div>
		<div id="studentTuitionContentBody">
			<iframe id="ifrTuitionBody">
			</iframe>
		</div>
		<div id="printTuitionBtnDiv ft_right"><button id="printTuitionBtn" class="btn-down ft_right">고지서 다운로드</button></div>
	</div>
</div>
<!-- /.container-fluid -->
<script src="/resources/js/app/ksh/studentTuition.js"></script>