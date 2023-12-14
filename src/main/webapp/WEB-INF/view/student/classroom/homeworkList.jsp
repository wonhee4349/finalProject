<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Begin Page Content -->
<div class="container-fluid">

	<div class="card2">
		<div class="sub_tit02">과제</div>
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
			
			
			
			
			
			<form
				class="d-none d-sm-inline-block  ml-md-3 my-2 my-md-0 navbar-search">
				<div class="input-group wd300 ft_right">
					<input type="text" class="form-control bg-light border-0 small"
						aria-label="Search" aria-describedby="basic-addon2">
					<div class="input-group-append">
						<button class="btn btn-primary" type="button">
							<i class="fas fa-search fa-sm"></i>
						</button>
					</div>
				</div>
			</form>
		</div>
		<div>
			<table class="table table_style01 mt-4 table_center" id="dataTable"
				width="100%" cellspacing="0">
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
						<button id="open_modal" class="blue_btn small_btn"
							data-bs-toggle="modal" data-bs-target="#modal_open">제출하기</button>

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




			<div aria-label="Page navigation example nav_center" id="classroomHomeWorkListPaginationArea">
			</div>

		</div>
	</div>



</div>
<!-- /.container-fluid -->
