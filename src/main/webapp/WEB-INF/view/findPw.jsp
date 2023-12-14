<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en">

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

<body>

    <div class="container">

		<!-- logo -->
		<div class="login_logo">
        		<div class="logoimg"><img src="${pageContext.request.contextPath }/resources/img/logo.png"></div>
					<div class="logotxt">
						UMSEOK<br>
						<span>UNIVERSITY</span>
					</div>
        	</div>
		

        <!-- Outer Row -->
        <div class="row justify-content-center top10vh">
        	
            <div class="col-xl-6 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-5">비번찾기</h1>
                                    </div>
                                    <form class="find">
                                        <div class="find-group mb-2">
                                        	<label for="name" class="find_txt">이름</label>
                                            <input type="text" class="find_input"id="name" >
                                        </div>
                                         <div class="find-group mb-4">
                                        	<label for="birth" class="find_txt">생년월일</label>
                                            <input type="text" class="find_input"id="birth" >
                                        </div>
                                      
                                        
                                        
                                        <a href="" class="btn  btn-navy-primary btn-user btn-block mt-4 p-3 mb-5">
                                            찾기
                                        </a>
                                    
                                    </form>
                                    
                                   
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

</body>

</html>