<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style type="text/css">
#loading {
	height: 100%;
	left: 0px;
	position: fixed;
	_position: absolute;
	top: 0px;
	width: 100%;
	filter: alpha(opacity = 50);
	-moz-opacity: 0.5;
	opacity: 0.5;
}
.loading {
	background-color: white;
	z-index: 999999;
}
#loading_img {
	position: absolute;
	top: 50%;
	left: 50%;
	height: 200px;
	margin-top: -75px; 
	margin-left: -75px; 
	z-index: 999999;
}
</style>
<script>
$(function() {
	var loadTargetURL = '${loadLocation}';
	console.log(loadTargetURL);
	var pageLoadAjaxSettings = {
		url : loadTargetURL
		, method : "get"
		, success : function(resp){
			$('#content').html(resp);
		}
		, error : function(xhr, status, err){
			console.log(err);
		}
	};
	$.ajax(pageLoadAjaxSettings);
});
</script>
<div id="loading" class="loading"></div><img id="loading_img" alt="loading" src="/resources/img/loadingImg.gif" />