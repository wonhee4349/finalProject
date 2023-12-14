<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/3.2.1/jquery.serializejson.min.js"></script>
<script>var sessionRemainTime = '${pageContext.session.maxInactiveInterval }'</script>
		
		
            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>



                <!-- Topbar Navbar -->
                <div class="logo">

                    <!--logo-->
                    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/professor">
                        <div class="sidebar-brand-text mx-3 logo_wrap">
                            <div class="logoimg">
                                <img src="${pageContext.request.contextPath }/resources/img/logo.png">
                            </div>
                            <div class="logotxt">
                                UMSEOK<br>
                                <span>UNIVERSITY</span>
                            </div>
                        </div>
                    </a>

                </div>
                <ul class="navbar-nav ml-auto">
                     <li class="nav-item  no-arrow" style="width:136px;">
                     		<a class="nav-link  contentLink" style="float:left; width:130px;" href="javascript:;"
                            id="sessionRefreshButton" role="button"  aria-haspopup="true"aria-expanded="false">
                            <i class="fas fa-clock fa-fw" style="margin-right:2px;"></i>시간연장
                            <span id="remainSessionTimeArea" class="badge badge-danger badge-counter" style="float:right; margin-top:4px"></span>
                        </a>
                            
                    </li>
   				<li class="nav-item dropdown no-arrow mx-1"><a class="nav-link dropdown-toggle contentLink" href="/professor/mail/mailList"
                            id="mailList" role="button"  aria-haspopup="true"
                            aria-expanded="false"> <i class="fas fa-envelope fa-fw"></i> <!-- Counter - Messages -->
                            <span id="mailCount" class="badge badge-danger badge-counter"></span>
                        </a>
                    </li>

                    <div class="topbar-divider d-none d-sm-block"></div>

                            	<security:authentication property="principal" var="user"/>
                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow"><a class="nav-link dropdown-toggle" href="#"
                            id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                            	${user.realUser.prfsorNm }(${user.realUser.prfsorNo })님 안녕하세요
                            </span>
                            <img class="img-profile rounded-circle" src="${pageContext.request.contextPath }/professor/profileImage">
                        </a> <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                            aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="/professor/mypage/professor/myPageUI"> <i
                                    class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> MyPage
                            </a>

                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal"> <i
                                    class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->
            <script src="/resources/js/app/ksh/sessionRefresh.js"></script>
          	 <script src="/resources/js/app/kwh/headerMenuStudent.js" ></script>  