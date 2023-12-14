<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<style>
.ht250{height:250px;}
select, input{width:80% !important; margin:0 auto;}
table.hover_none tr:hover{background:none !important;}
.btn_wrap{width:100%; height:60px;}
</style> 


<script>
	// 문서가 로드된 후 실행
	document.addEventListener("DOMContentLoaded", function() {
		// 모달 열기 버튼 클릭 시 모달 창 열기
		document.getElementById("openModal").addEventListener(
				"click",
				function() {
					var modal = new bootstrap.Modal(document
							.getElementById("openModal"));
					modal.show();
				});

		// 모달 닫기 버튼 클릭 시 모달 창 닫기
		document.querySelector(".close").addEventListener(
				"click",
				function() {
					var modal = bootstrap.Modal.getInstance(document
							.getElementById("openModal"));
					modal.hide();
				});

	});
</script>


<!-- 모달 창 추가 -->
<div class="modal fade" id="modal_open" tabindex="-1" role="dialog"
	aria-labelledby="modalLabel01" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalLabel01">공지 수정</h5>
				<button class="close" type="button" data-bs-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>

			</div>
			<div class="modal-body "></div>
		</div>
	</div>
</div>
   
<!-- Begin Page Content -->
<div class="container-fluid">
    <div class="card2">
        <div class="sub_tit02">학사 공지</div>

   
        <sec:authentication property="principal" var="principal"/>          
            <table class="table table_style01 table_center hover_none">
                <colgroup>
                    <col width="25%">
                    <col width="25%">
                    <col width="25%">
                </colgroup>
                <tr>
                    <th>공지 제목</th>
                    <td>
                        ${board.boTitle}
                    </td>   
                     <th>작성자</th>
                    <td>
                        ${board.staff.sklstfNm}
                    </td>    
                </tr>
            <tr>
               <th>작성일</th>
               <td>
                 ${board.boDate}
               </td>
               <th>조회수</th>
               <td>
                  ${board.boCnt}
               </td>
            </tr>
         </table>

            <br>

            <table class="table table_style01 table_center">
                <tr>
                    <th colspan="4">내 용</th>
                </tr>
                <tr>
                    <td colspan="4">
                    <pre style="font-size:20px;"> ${board.boCn}</pre>
                    </td>
                </tr>
            </table>
            
            <div class="btn_wrap">
                <button type="button" id="deleteNoticeBtn" 
                class="btn btn-danger ft_right" data-bo-no="${board.boNo}" >삭제</button>
                
            <button data-bo-no="${board.boNo}" id="open_modal" 
            data-toggle="modal" data-target="#modal_open" style="margin-right:8px;" class="btn btn-danger ft_right">
            수정      
            </button>
            </div>

            
    </div>
</div>
<!-- /.container-fluid -->

<script src="/resources/js/app/ljh/staffNoticeData.js"></script>
<script src="/resources/js/app/ljh/staffNoticeDelete.js"></script>
