<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<style>
.ht250{height:250px;}
select, input{width:80% !important; margin:0 auto;}
table.hover_none tr:hover{background:none !important;}
table td{padding-left:10px; text-align:left;}
</style>    
<!-- Begin Page Content -->
<div class="container-fluid">
    <div class="card2">
        <div class="sub_tit02">동아리 개설하기</div>

        <form id="clubForm"  method="POST" >
        <sec:authentication property="principal" var="principal"/>
             <input type="hidden" value="${principal.username }" name="stdntNo" class="form-style01" id="stdntNo" readonly />
             <input type="hidden" value="01" name="confmSe" />
            <table class="table table_style01 table_center hover_none">
                <colgroup>
                    <col width="25%">
                    <col width="25%">
                    <col width="25%">
                </colgroup>
                <tr>
                    <th>동아리명</th>
                    <td>
                        <input type="text" id="clubesnm" class="form-style01" name="clubEsNm" required></input>
                    </td>   
                     <th>동아리구분</th>
                    <td>
                        <select id="clubSe" class="form-style01" name="clubSe">
                        	<option value="-1">선택</option>
                    		<c:forEach items="${comCodeList}" var="whCom">
                    			<option value="${whCom.COM_CODE }">${whCom.COM_CODE_NM }</option>
                    		</c:forEach>
                    	</select>
                    </td>    
                </tr>
				<tr>
					<th>희망 동아리실</th>
					<td>
						<select id="buldNo" class="form-style01" name="buldNo" required>
								<option value="-1">선택</option>
								<c:forEach items="${buildList}" var="build">
									<option value="${build.BULD_NO}">${build.BULD_NM}</option>
								</c:forEach>
						</select>
					</td>
					<th>동아리실 호수</th>
					<td>
						<select id="fcltsNo" class="form-style01" name="fcltsNo" required>
								<option value="-1">선택</option>
						</select>
					</td>
				</tr>
			</table>

            <br>

            <table class="table table_style01 table_center hover_none">
                <tr>
                    <th colspan="4">동아리 소개</th>
                </tr>
                <tr>
                    <td colspan="4" style="padding:10px;">
                        <textarea id="clubesintrcn" class="form-style01 ht250 scroll-y " name="clubEsIntrcn" required></textarea>
                    </td>
                </tr>
            </table>
		<button type="button" id="inputDataOpen" class="btn btn-success ft_left">일괄입력</button>
            <button id="clubInsert" type="submit" class="btn btn-primary ft_right">개설신청</button>
        </form>
    </div>
</div>
<!-- /.container-fluid -->

<script src="/resources/js/app/kwh/studentClubRequest.js"></script>
