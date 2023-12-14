<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 추가한 스크립트 -->
<script>
    // 문서가 로드된 후 실행
    document.addEventListener("DOMContentLoaded", function () {
        // 모달 열기 버튼 클릭 시 모달 창 열기
        document.getElementById("open_modal").addEventListener("click", function () {
            var modal = new bootstrap.Modal(document.getElementById("modal_open"));
            modal.show();
        });

        // 모달 닫기 버튼 클릭 시 모달 창 닫기
        document.querySelector(".close").addEventListener("click", function () {
            var modal = bootstrap.Modal.getInstance(document.getElementById("modal_open"));
            modal.hide();
        });

    });
</script>

    
 	<!-- 모달 창 추가 -->
		<div class="modal fade" id="modal_open" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
		    <div class="modal-dialog modal-xl" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title" id="modalLabel01">제출하기</h5>
		              <button class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
						    <span aria-hidden="true">×</span>
					  </button>

		            </div>
		            <div class="modal-body ">
		                <div class="homework_wrap mb-4">
		                    <div class="table_style01 mt-4 mb-4 table_center">
		                        <table class="wd100">
		                        	<colgroup>
		                        		<col width="30%">
		                        		<col width="50%">
		                        		<col width="20%">
		                        	</colgroup>
		                            <tr>
		                                <th>과목명</th>
		                                <th>과제명</th>
		                                <th>마감일</th>
		                            </tr>
		                            <tr>
		                            	<td>전자정부프레임</td>
		                            	<td>달력만들기</td>
		                            	<td>2023-12-30</td>
		                            </tr>
		                            <tr>
		                            	<td>전자정부프레임</td>
		                            	<td>달력만들기</td>
		                            	<td>2023-12-30</td>
		                            </tr>
		                            <tr>
		                            	<td>전자정부프레임</td>
		                            	<td>달력만들기</td>
		                            	<td>2023-12-30</td>
		                            </tr>
		                            <tr>
		                            	<td>전자정부프레임</td>
		                            	<td>달력만들기</td>
		                            	<td>2023-12-30</td>
		                            </tr>
		                        </table>
		                        <br>
		                    </div>
		                    <!-- 파일업로드 -->
		                    <div class="gray_box mt-4">
		                    	<div class="white_bg">
		                   			 <input type="file"></input>
		                   		 </div>
		                    </div>
		                    
		                    <button class="btn btn-primary ft_right mt-4">제출하기</button>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		<!-- 모달끝 -->

		        
           
    
   <!-- Begin Page Content -->
                <div class="container-fluid">

                    <div class="card2">
                        <div class="sub_tit02">등록금</div>
                        <div class="sub_top_wrap">

                            <!-- selectbox -->
                            <div class="s_option">
                                <span class="option_tit">강의명</span>
                                <div class="select-container">
                                    <select class="custom-select02">
                                        <option value="option1">Option 1</option>
                                        <option value="option2">Option 2</option>
                                        <option value="option3">Option 3</option>
                                    </select>
                                    <div class="select-arrow">
                                        <i class="fa fa-caret-down"></i>
                                    </div>
                                </div>
                            </div>
                            <!-- search -->
                            <form class="d-none d-sm-inline-block  ml-md-3 my-2 my-md-0 navbar-search">
                                <div class="input-group wd300 ft_right">
                                    <input type="text" class="form-control bg-light border-0 small" aria-label="Search"
                                        aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>
                            <table class="table table_style01 mt-4 table_center" id="dataTable" width="100%"
                                cellspacing="0">
                                <colgroup>
		                        		<col width="25%">
		                        		<col width="40%">
		                        		<col width="25%">
		                       </colgroup>
                                <tr>
                                    <th>강의명</th>
                                    <th>과제명</th>
                                    <th>마감일</th>
                                    <th>제출하기</th>
                                </tr>
                                <tr>
                                    <td>jsp</td>
                                    <td>jsp</td>
                                    <td class="date">23-11-30</td>
                                    <td>
                                    	<button id="open_modal" class="blue_btn small_btn" data-bs-toggle="modal" data-bs-target="#modal_open">제출하기</button>

                                    </td>
                                </tr>
                                <tr>
                                    <td>jsp</td>
                                    <td>jsp</td>
                                    <td class="date">23-11-30</td>
                                    <td><button class="gray_btn small_btn">제출하기</button></td>
                                </tr>
                                <tr>
                                    <td>jsp</td>
                                    <td>jsp</td>
                                    <td class="date">23-11-30</td>
                                    <td><button class="gray_btn small_btn">제출하기</button></td>
                                </tr>
                                <tr>
                                    <td>jsp</td>
                                    <td>jsp</td>
                                    <td class="date">23-11-30</td>
                                    <td><button class="gray_btn small_btn">제출하기</button></td>
                                </tr>
                                <tr>
                                    <td>jsp</td>
                                    <td>jsp</td>
                                    <td class="date">23-11-30</td>
                                    <td><button class="gray_btn small_btn">제출하기</button></td>
                                </tr>
                                <tr>
                                    <td>jsp</td>
                                    <td>jsp</td>
                                    <td class="date">23-11-30</td>
                                    <td><button class="gray_btn small_btn">제출하기</button></td>
                                </tr>
                            </table>




                            <div aria-label="Page navigation example nav_center">
                                <ul class="pagination">
                                    <li class="page-item"><a class="page-link" href="#" aria-label="Previous"> <span
                                                aria-hidden="true">&laquo;</span>
                                        </a></li>
                                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                    <li class="page-item"><a class="page-link" href="#" aria-label="Next"> <span
                                                aria-hidden="true">&raquo;</span>
                                        </a></li>
                                </ul>
                            </div>

                        </div>
                    </div>



                </div>
                <!-- /.container-fluid -->
