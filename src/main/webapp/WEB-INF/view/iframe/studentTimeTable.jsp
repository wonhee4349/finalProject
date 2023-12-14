<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>시간표</title>
</head>
<link href="/resources/css/timetable.css" rel="stylesheet">

<body>

<div class="time_table_wrap">
	<div class="tit">시간표</div>
	<table class="table table-border time_table" id="timetableTable">
			<colgroup>
				<col width="10%">
				<col width="15%">
				<col width="15%">
				<col width="15%">
				<col width="15%">
				<col width="15%">
				<col width="15%">
			</colgroup>
			<tr>
			<th></th>
			<th>월</th>
			<th>화</th>
			<th>수</th>
			<th>목</th>
			<th>금</th>
			<th>토</th>
		</tr>
		<tr>
			<th>1교시</th>
			<td id="A1"></td>
			<td id="B1"></td>
			<td id="C1"></td>
			<td id="D1"></td>
			<td id="E1"></td>
			<td id="F1"></td>
		</tr>
		<tr>
			<th>2교시</th>
			<td id="A2"></td>
			<td id="B2"></td>
			<td id="C2"></td>
			<td id="D2"></td>
			<td id="E2"></td>
			<td id="F2"></td>
		</tr>
		<tr>
			<th>3교시</th>
			<td id="A3"></td>
			<td id="B3"></td>
			<td id="C3"></td>
			<td id="D3"></td>
			<td id="E3"></td>
			<td id="F3"></td>
		</tr>
		<tr>
			<th>4교시</th>
			<td id="A4"></td>
			<td id="B4"></td>
			<td id="C4"></td>
			<td id="D4"></td>
			<td id="E4"></td>
			<td id="F4"></td>
		</tr>
		<tr>
			<th>5교시</th>
			<td id="A5"></td>
			<td id="B5"></td>
			<td id="C5"></td>
			<td id="D5"></td>
			<td id="E5"></td>
			<td id="F5"></td>
		</tr>
		<tr>
			<th>6교시</th>
			<td id="A6"></td>
			<td id="B6"></td>
			<td id="C6"></td>
			<td id="D6"></td>
			<td id="E6"></td>
			<td id="F6"></td>
		</tr>
		<tr>
			<th>7교시</th>
			<td id="A7"></td>
			<td id="B7"></td>
			<td id="C7"></td>
			<td id="D7"></td>
			<td id="E7"></td>
			<td id="F7"></td>
		</tr>
		<tr>
			<th>8교시</th>
			<td id="A8"></td>
			<td id="B8"></td>
			<td id="C8"></td>
			<td id="D8"></td>
			<td id="E8"></td>
			<td id="F8"></td>
		</tr>
		<tr>
			<th>9교시</th>
			<td id="A9"></td>
			<td id="B9"></td>
			<td id="C9"></td>
			<td id="D9"></td>
			<td id="E9"></td>
			<td id="F9"></td>
		</tr>
	</table>
</div>
</body>
<script src="/resources/js/app/ksh/studentTimeTable.js"></script>
</html>