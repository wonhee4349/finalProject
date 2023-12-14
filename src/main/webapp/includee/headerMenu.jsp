<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>



                <!-- Topbar Navbar -->
                <div class="logo">

                    <!--logo-->
                    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="javascript:location.reload();">
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


                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none"><a class="nav-link dropdown-toggle" href="#"
                            id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
                        </a>

                    </li>

                    <!--  - Alerts -->
                    <li class="nav-item dropdown no-arrow mx-1"><a class="nav-link dropdown-toggle" href="#"
                            id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false"> <i class="fas fa-bell fa-fw"></i> <!-- Counter - Alerts -->
                            <span class="badge badge-danger badge-counter">3+</span>
                        </a>
                        <!-- Dropdown - Alerts -->
                        <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                            aria-labelledby="alertsDropdown">
                            <h6 class="dropdown-header">알림</h6>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-primary">
                                        <i class="fas fa-file-alt text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">2023,11,04</div>
                                    <span class="font-weight-bold">동호회 가입 승인되었습니다.</span>
                                </div>
                            </a> <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-success">
                                        <i class="fas fa-donate text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">2023,11,04</div>
                                    <span class="font-weight-bold">동호회 가입 승인되었습니다.</span>
                                </div>
                            </a> <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-warning">
                                        <i class="fas fa-exclamation-triangle text-white"></i>
                                    </div>
                                </div>

                            </a> <a class="dropdown-item text-center small text-gray-500" href="#">Show
                                All Alerts</a>
                        </div>
                    </li>

                    <!-- Nav Item - Messages -->
                    <li class="nav-item dropdown no-arrow mx-1"><a class="nav-link dropdown-toggle" href="#"
                            id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false"> <i class="fas fa-envelope fa-fw"></i> <!-- Counter - Messages -->
                            <span class="badge badge-danger badge-counter">7</span>
                        </a>
                    </li>

                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow"><a class="nav-link dropdown-toggle" href="#"
                            id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false"> <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas
                                McGee</span> <img class="img-profile rounded-circle"
                                src="${pageContext.request.contextPath }/resources/img/undraw_profile.svg">
                        </a> <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                            aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="#"> <i
                                    class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
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