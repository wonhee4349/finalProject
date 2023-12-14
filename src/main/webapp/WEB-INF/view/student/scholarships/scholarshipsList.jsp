<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <!-- Begin Page Content -->
                <div class="container-fluid">
					<input type="hidden" id="studentScholarshipRequestPageCurrentPage"  />
                    <div class="card2">
                        <div class="sub_tit02">장학금 신청</div>
                        <div class="sub_top_wrap">
							신청 가능 목록
                        </div>
                        <div>
                            <table class="table table_style01 mt-4 table_center" id="dataTable" width="100%"
                                cellspacing="0">
                                <colgroup>
	                        		<col width="35%">
	                        		<col width="35%">
	                        		<col width="10%">
	                        		<col width="20%">
	                        	</colgroup>
	                            <tr>
	                                <th>장학금명</th>
	                                <th>장학대상</th>
	                                <th>지금방식</th>
	                                <th>상세보기/신청하기</th>
	                            </tr>
                                <tbody id="requestableScholarshipListBody"></tbody>
                            </table>
							<!-- 장학금페이지 페이지네이션 위치 -->
                            <div aria-label="Page navigation example nav_center" id="scholarshipPaginationArea">
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
                
<!-- 모달 창 추가 -->
		<div class="modal fade" id="scholarshipModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel01" aria-hidden="true">
		    <div class="modal-dialog modal-xl" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
						<h5 class="modal-title" id="modalLabel01">장학금 상세정보</h5>
					<button class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
		            </div>
		            <div class="modal-body">
		                <div class="homework_wrap mb-4">
		                    <div class="table_style01 mt-4 mb-4 table_center">
		                        <table class="wd100">
		                        	<colgroup>
		                        		<col width="20%">
		                        		<col width="70%">
		                        	</colgroup>
		                            <tbody id="scholarshipModalTableBody">
		                            </tbody>
		                        </table>
		                        <br>
		                    </div>
		                    <!-- 파일업로드 -->
		                    <form id="studentScholarshipRequestForm">
<!-- 			                    <div class="gray_box mt-4"> -->
<!-- 			                    	<div class="white_bg"> -->
<!-- 			                   			 <input type="file" ></input> -->
<!-- 			                   		 </div> -->
<!-- 			                    </div> -->
								<input type="hidden" id="modalSchlshipNo"/>
			                    <input type="submit" class="btn btn-primary ft_right mt-4" value="신청하기" />
		                    </form>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		<!-- 모달끝 -->
                
                
<script src="/resources/js/app/ksh/studentScholarship.js"></script>