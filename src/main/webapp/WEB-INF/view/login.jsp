<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

           

<html lang="en">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

   

    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath }/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath }/resources/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/resources/css/layout.css" rel="stylesheet">

</head>
<style>
body{over-flow:hidden;}
.login_bg{width:100%;
height: 100vh;
    overflow: hidden;
    width: 100%;}
    
  .login_wrap{
 	position: absolute;
    top: 0;
    z-index: 9999;
    height: 100vh;
    width: 100%;
    background: #0000004a; over-flow:hidden;}  
.login_box{position: relative;margin: 0 auto;width: 540px;top: 100px;}    
.modal-dialog{position:relative; top:150px !important;} 
.modal-open .modal{z-index:99999999}
.modal-body{padding:50px !important;}
.modal-footer{border:none !important; }
.swal2-container{z-index: 9999999999999999999999999999999999999999999}
</style>
<body>
<!-- ======================================================================================================================================================= -->
  <!-- 아이디 찾기 모달 창 -->
<div class="modal fade" id="findIdModal" tabindex="-1" role="dialog" aria-labelledby="findIdModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="findIdModalLabel">아이디 찾기</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<!-- 모달 내용 추가 -->
				<!-- 예시: 아이디 찾기 폼 -->
<!-- 					<div class="form-group"> -->
<!-- 						<label for="userType">사용자 유형</label> -->
<!-- 						<div> -->
<!-- 							<input type="radio" class="userType" id="student" name="userType" value="student" checked>  -->
<!-- 							<label for="student">학생</label>  -->
<!-- 							<input type="radio" class="userType" id="professor" name="userType" value="professor"> -->
<!-- 							<label for="professor">교수</label>  -->
<!-- 							<input type="radio" class="userType" id="staff" name="userType" value="staff">  -->
<!-- 							<label for="staff">교직원</label> -->
<!-- 						</div> -->
<!-- 					</div> -->

					<div class="form-group">
						<label for="name">이름</label> <input type="text" class="form-control" id="findIdnameTag" name="name" required>
					</div>

					<div class="form-group">
						<label for="ssn">주민등록번호</label>
						<div class="d-flex">
							<input type="text" class="form-control" id="ssn1" name="ssn1"
								maxlength="6" required> <span class="mx-2">-</span> <input
								type="password" class="form-control" id="ssn2" name="ssn2"
								maxlength="7" required>
						</div>
					</div>

					<button type="button" class="btn btn-primary ft_right" id="idSendBtn1">아이디 찾기</button>
					<button type="button" id="inputDataStaf" class="btn btn-primary ft_right"  style="margin-right:10px;">일괄입력</button>
					
					<div class="modal-footer" id="resultModalFooter">
       				 <p id="resultMessage" style="text-align:Center;"></p>
    				</div>
			</div>
		</div>
	</div>
</div>

<!-- 비밀번호 찾기 모달 창 -->
<div class="modal fade" id="findPasswordModal" tabindex="-1" role="dialog" aria-labelledby="findPasswordModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="findPasswordModalLabel">비밀번호 찾기</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- 모달 내용 추가 -->
                <!-- 예시: 비밀번호 찾기 폼 -->
                <form action="${pageContext.request.contextPath}/findPassword" method="post" id="findPasswordForm">
                    <div class="form-group">
                        <label for="name">이름</label>
                        <input type="text" class="form-control" id="findPassnameTag" name="name" required>
                        <label for="userid">아이디</label>
                        <input type="text" class="form-control" id="userIdForFindPass" name="userid" required>
						<label for="ssn">주민등록번호</label>
						<div class="d-flex">
							<input type="text" class="form-control" id="ssn1ForFindPass" name="ssn1"
								maxlength="6" required> <span class="mx-2">-</span> <input
								type="password" class="form-control" id="ssn2ForFindPass" name="ssn2"
								maxlength="7" required>
						</div>
                        <!-- 첫 번째 이메일 입력란 -->
                        <label for="emailLeft1">이메일</label>
                        <div class="form-row">
                            <div class="col">
                                <input type="text" class="form-control" id="emailLeft1" name="emailLeft1" required>
                            </div>
                            <div class="col-auto">
                                <span>@</span>
                            </div>
                            <!-- 두 번째 이메일 입력란 -->
                            <div class="col">
                                <input type="text" class="form-control" id="emailLeft2" name="emailLeft2" required>
                            </div>
                        </div>
                        <!-- 이메일 도메인 선택창 -->
                        <div class="form-row mt-2">
                            <div class="col">
                                <select class="form-control" id="emailDomainSelect" name="emailDomainSelect" onchange="updateEmailDomain()">
                                    <option>선택</option>
                                    <option value="naver.com">네이버</option>
                                    <option value="nate.com">네이트</option>
                                    <option value="daum.net">다음</option>
                                    <option value="gmail.com">Gmail</option>
                                    <option value="direct">직접입력</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <input type="submit" id="passwordFindBtn" class="btn btn-primary ft_right" value="비밀번호 찾기" />
                    <button type="button" id="inputDatafass" class="btn btn-primary ft_right"  style="margin-right:10px;">일괄입력</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    // 이메일 도메인 선택창이 변경될 때 호출되는 함수
    function updateEmailDomain() {
        var selectedDomain = document.getElementById('emailDomainSelect').value;
        if (selectedDomain === 'direct') {
            document.getElementById('emailLeft2').readOnly = false;
        } else {
            document.getElementById('emailLeft2').readOnly = true;
            document.getElementById('emailLeft2').value = selectedDomain;
        }
    }
    
    //===========================================================================================================================================================
</script>

	  <div class="login_bg">
		<iframe name="ifrm" src="https://vod.kongju.ac.kr/common/player/player.jsp?type=vod&amp;id=da53edad-7bd6-465a-809e-c2b258fd5510&amp;speedYn=Y&amp;width=100%25&amp;autoStart=Y&amp;controlbarYn=N&amp;loopYn=Y" width="100%" height="100%" title="공주대학교 홍보동영상"></iframe>
	</div>
	
    <div class="login_wrap">

		<!-- logo -->
		<div class="login_logo">
        		<div class="logoimg"><img src="${pageContext.request.contextPath }/resources/img/logo.png"></div>
					<div class="logotxt" style="color:#fff;">
						UMSEOK<br>
						<span>UNIVERSITY</span>
					</div>
        	</div>

        <!-- Outer Row -->
        <div class="login_box">
        	
            <div >

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">로그인</h1>
                                    </div>
                                    <form class="user" action="${pageContext.request.contextPath }/login" method="post">
                                    	<security:csrfInput/>
                                        <div class="form-group">
                                            <input type="email" class="form-control form-control-user" name="userId"
                                                id="exampleInputEmail" aria-describedby="emailHelp"
                                                placeholder="ID">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-user" name="userPw"
                                                   id="exampleInputPassword" placeholder="Password">
                                        </div>
                                        
                                        <a href="javascript:;" id="loginBtn" class="btn btn-navy-primary btn-user btn-block">
                                            로그인
                                        </a>
                                    
                                    </form>
                                    <hr>
                                    <div>	                                
	            					 <div style="width:170px; margin:0 auto; font-size:20px;">
    									<a class="small" href="javascript:;" data-toggle="modal" data-target="#findIdModal">아이디찾기</a> |
    									<a class="small" href="javascript:;" data-toggle="modal" data-target="#findPasswordModal">비밀번호찾기</a>
										</div>
                                    </div>
                                   <div id="autoInputForQuickTest" style="margin-top:90px;">
										<button class="autoInputBtn btn"  data-target-user="std01">학생(e7e)</button>
										<button class="autoInputBtn btn"  data-target-user="std02">2학년학생</button>
										<button class="autoInputBtn btn"  data-target-user="prf01">교수(희)</button>
										<button class="autoInputBtn btn"  data-target-user="stf01">교직원</button>
                                   </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath }/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
	<script src="/resources/js/notify.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath }/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath }/resources/js/sb-admin-2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/app/login.js"></script>
</body>
<security:csrfMetaTags/>
<c:if test="${expired eq '만료' }">
<script>
	Swal.fire({
		title: "세션이 만료되었습니다",
		text: "다시 로그인 해주세요.",
		icon: "error"
	});
</script>
<c:remove var="expired"/>
</c:if>
<c:if test="${failure eq '실패' }">
<script>
	Swal.fire({
		title: "로그인 실패",
		text: "아이디나 비밀번호를 확인해주세요",
		icon: "error"
	});
</script>
<c:remove var="failure"/>
</c:if>


<script type="text/javascript">
$(function(){
	var idSendBtn1 = $("#idSendBtn1");
	
	const csrfValue = $("meta[name='_csrf']").attr("content");
	const csrfHeader = $("meta[name='_csrf_header']").attr("content");
	
	$.ajaxSetup({
		headers : {
		[csrfHeader] : csrfValue
		}
   });
	
	$("#btnFindId").on("click", function(){
		$("#findIdModal").modal("show");
	});
	
	idSendBtn1.on("click", function(){
		var userType = $(".userType").val();
		var name = $("#findIdnameTag").val();
		var ssn1 = $("#ssn1").val();
		var ssn2 = $("#ssn2").val();
		
		console.log(userType + " ::: " + name + " ::: " + ssn1 + " :: " + ssn2);
		
		var data = {
			name : name
			, ssn1 : ssn1
			, ssn2 : ssn2
		};
		
		
		var page = "/findId";
		
		$.ajax({
			type: "post",
			url : page,
			data : JSON.stringify(data),
			dataType: "json",
			contentType : "application/json;charset=utf-8",
			success:function(res){
				console.log(res);
	                $("#resultMessage").text(name + "님의 아이디는 '" + res + "' 입니다");
			}
		});
		
	});
		$("#inputDataStaf").on("click",function(){
			$("#findIdnameTag").val("이철희");
			$("#ssn1").val("030102");
			$("#ssn2").val("3023810");
			
		});
	
	$('#passwordFindBtn').on('click',function(e){
		e.preventDefault();
		var data = {
			id : $('#userIdForFindPass').val()
			, name : $('#findPassnameTag').val()
			, ssn1 : $('#ssn1ForFindPass').val()
			, ssn2 : $('#ssn2ForFindPass').val()
			, email : `\${$('#emailLeft1').val()}@\${$('#emailLeft2').val()}`
		}
		
		$.ajax({
			type: "post",
			url : "/findPassword",
			data : JSON.stringify(data),
			dataType: "json",
			contentType : "application/json;charset=utf-8",
			success:function(res){
				 if (res.result) {
		                Swal.fire({
		                    title: "비밀번호 찾기 성공!",
		                    text: "비밀번호를 초기화 시켜 입력하신 메일로 보내드렸습니다. 로그인 하시고 보안에 문제가 될 수 있으니 비밀번호 변경 부탁드립니다.",
		                    icon: "info"
		                }).then(function () {
		                    // 이 부분에 필요한 동작 추가 (예: 리다이렉션 등)
		                    location.href = location.reload();
		                });
		            } else {
		                // 서버 응답이 성공하지 않은 경우에 대한 처리
		                console.error("비밀번호 찾기 실패:", res.message);
		                Swal.fire({
		                    title: "비밀번호 찾기 실패!",
		                    text: "비밀번호 찾기에 실패했습니다. 입력 정보를 다시 확인해주세요.",
		                    icon: "error"
		                });
		            }
		        },
		        error: function (error) {
		            // AJAX 요청 자체가 실패한 경우에 대한 처리
		            console.error("비밀번호 찾기 요청 실패:", error);
		            Swal.fire({
		                title: "비밀번호 찾기 요청 실패!",
		                text: "비밀번호 찾기 요청 중에 오류가 발생했습니다. 나중에 다시 시도해주세요.",
		                icon: "error"
		            });
		        }
		    });
		});
	$("#inputDatafass").on("click",function(){
		$("#findPassnameTag").val("이철희");
		$("#userIdForFindPass").val("20221212");
		$("#ssn1ForFindPass").val("030102");
		$("#ssn2ForFindPass").val("3023810");
		$("#emailDomainSelect").val("naver.com").trigger("change");
		$("#emailLeft1").val("loveclimax94");
	
		
	});
});
</script>
</html>