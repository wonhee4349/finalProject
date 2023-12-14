<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.box_tit01 {
	width: 100%;
	border-radius: 3px;
	text-align: center;
	line-height: 40px;
	height: 40px;
	background: #f3f3f3;
	border-top: 2px solid #b2c1d4;
	font-size: 18px;
	color: #6b8bb9;
}
.tit02 {font-size: 18px;}
.textbox {min-height: 150px; overflow-y: scroll}
.lectureroom {width: 50%; float: left;}
.ft-left {float: left;}
.wd50 {width: 50%;}
#classroomLectureListOption {width: 420px;}
#ex9 .upload-box{
	

}
#assignmentFile{width:0px;height:0px; opacity: 0;}
.overflowHiddenEllipsis{display : inline-block; width : 350px; overflow:hidden;text-overflow:ellipsis; white-space: nowrap;}
.min500{min-height:500px;}
.pd20{padding:20px !important}
.sub_tit04{float:none;}
.line_box{border:1px solid #c9cfdf; text-align:Center; padding:20px; background: #f9f9f9;}
.down{
   
    width: 283px;
    border-radius: 0;
    margin: 0 auto;
    padding: 8px 50px;
    text-align: center;
    color: #000000;
}
.gray_down{
		border-radius: 0;
	background: #a7a7a7;
	padding: 8px 50px;
	margin-top: 10px;
	color: #fff !important;
	border: 1px solid #9b9b9b;}
.blue_box{border-radius:0 !important;}	
.fileOver{background-color: skyblue ; opacity: 0.7;}
</style>




<!-- Begin Page Content -->
<div class="container-fluid">
	<div class="card2">
		<div class="sub_tit02">클래스룸</div>
		<div class="sub_top_wrap"></div>

		<div class="select-container">
			<select id="classroomLectureListOption" class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
			<c:if test="${not empty list }">
				<option value>강의선택</option>
				<c:forEach items="${list}" var="lecture">
					<option value="${lecture.lctreNo}" label="${lecture.course.courseNm }" />
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<option value label="수강강의없음">
			</c:if>
			</select>
		</div>
		<br />

		<div class="class_list_wrap">
			<div class="list_top">
				<button class="gray_btn small_btn tabChangeBtn active" data-tab="classroomTab">클래스룸게시판</button>
				<button class="gray_btn small_btn tabChangeBtn" data-tab="assignmentTab">과제관리</button>
				<button class="gray_btn small_btn tabChangeBtn" data-tab="testTab">시험</button>
<%-- 				<button class="gray_btn small_btn tabChangeBtn" data-tab="onlineLectureTab">온라인강의</button> --%>
			</div>
		</div>
		<!-- -------------------------------여기서부터가 탭 내용 시작  --------------------------------------------------------- -->
		<div class="tab_contents" style="height: 500px;">

			<!-- -------------------------------여기서부터가 탭 - 1  --------------------------------------------------------- -->
			<div id="testTab" class="tab-content min500">
				<div id="testDivBody"><p>강의를 선택해주세요</p></div>
			</div>
			<!-- -------------------------------여기서부터가 탭 - 2  --------------------------------------------------------- -->
			<div id="assignmentTab" class="tab-content min500">
				<div id="assignmentDivBody"><p>강의를 선택해주세요</p></div>
			</div>
			<!-- -------------------------------여기서부터가 탭 - 3  --------------------------------------------------------- -->
			<div id="classroomTab" class="tab-content current min500">
				<div id="classroomBoardDivBody"><p>강의를 선택해주세요</p></div>
			</div>
			<!-- -------------------------------여기서부터가 탭 - 4  --------------------------------------------------------- -->
			<div id="onlineLectureTab" class="tab-content min500">
				<div id="onlineLectureDivBody"><p>강의를 선택해주세요</p></div>
			</div>
		</div>
	</div>
</div>


<!-- 과제 제출용 모달 -->
<div class="modal fade" id="assignmentSubmitModal" tabindex="-1" role="dialog" aria-labelledby="assignmentSubmitModalTitle" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="assignmentSubmitModalTitle">과제 제출 <span class="text-primary" style="font-size:18px;" id="assignmentTitleAreaForSubmitModal"></span> </h5>
				<button class="close" type="button" data-bs-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
			</div>
			<div class="modal-body">
				<div class="homework_wrap mb-4">
				
				<div class="sub_tit04"><span></span>과제내용</div>
				<table class="table_style01 hover_none">
					<colgroup>
						<col width="30%">
					</colgroup>
					<tr>
						<th>과제명</th>
						<td class="text_left"><div id="assignmentTitle"></div></td>
					</tr>
					<tr>
						<th>과제내용</th>
						<td class="text_left"><div id="assignmentContent"></div></td>
					</tr>
				
				</table>
				<div class="sub_tit04"><span></span>과제 다운로드</div>
				<div class="blue_box pd20 mb-2" >
					<div><span style="margin-right:5px;"><i class="fas fa-download fa-fw"></i></span>과제 다운로드</div>
					<div id="assignmentAttatch"></div>
				</div>
				
				<div class="sub_tit04 mt-4"><span></span>파일 업로드</div>
				
				<form id="assignmentSubmitForm">
					<div class="line_box">
							<input type="hidden" name="lctreNo" id="assignmentSubmitLctreNo" />
							<input type="hidden" name="crNo" id="assignmentSubmitCrNo" />
							<section id="ex9">
								<div class="upload-box">
									<div>한글파일과 오피스파일만 가능합니다</div>
									<span id="dragAndDropFileInfo">파일을 끌어다 놓으세요!</span>
									<div class="down">
										<label for="assignmentFile" class="btn gray_down">파일선택</label>
										<input class="btn-file d-none" type="file" name="assignmentFile" id="assignmentFile">
									</div>
									<!--파일 input box 형태-->
								</div>
							</section>
					 </div>	
					 <input type="submit" class="btn btn-primary ft_right mt-4" value="제출하기" />
					
				</form>
				
				
				<%--
					<div>과제 이름 : <div id="assignmentTitle"></div></div>
					<div>내용 : <div id="assignmentContent"></div></div>
					<div>첨부파일 : <div id="assignmentAttatch"></div></div>
					<form id="assignmentSubmitForm">
						<input type="hidden" name="lctreNo" id="assignmentSubmitLctreNo" />
						<input type="hidden" name="crNo" id="assignmentSubmitCrNo" />
						<section id="ex9">
							<div class="upload-box">
								<div>한글파일과 오피스파일만 가능합니다</div>
								<span id="dragAndDropFileInfo">파일을 끌어다 놓으세요!</span>
								<label for="assignmentFile" class="btn btn-info">파일선택</label>
								<input class="btn-file d-none" type="file" name="assignmentFile" id="assignmentFile">
								<!--파일 input box 형태-->
							</div>
						</section>
						<input type="submit" class="btn btn-primary ft_right mt-4" value="제출하기" />
					</form>
					
					 --%>  
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 과제 제출용 모달 끝 -->

<!-- 제출과제 정보용 모달 -->
<div class="modal fade" id="assignmentInfoModal" tabindex="-1" role="dialog" aria-labelledby="assignmentInfoModalTitle" aria-hidden="true">
	<div class="modal-dialog modal-s" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="assignmentInfoModalTitle"><span id="assignmentTitleAreaForInfoModal"></span> - 제출 정보</h5>
				<button class="close" type="button" data-bs-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
			</div>
			<div class="modal-body">제출 파일
				<div class="homework_wrap mb-4" id="assignmentInfoBody">
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 제출과제 정보용 모달 끝 -->




<script	src="/resources/js/app/ksh/studentClassRoom.js"></script>