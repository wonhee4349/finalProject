<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<style>
.sub_tit04{font-size: 18px;color: #304b73;padding-left:0px;font-weight: 600;width: 60%;float:left; text-align:left; margin-bottom:5px;}   
.sub_tit04 span{    width: 8px;
    height: 8px;
    background: #304b73;
    display: block;
    float: left;
    margin-right: 8px;
    position: relative;
    top: 9px;}
#btn1 {
width : 80px;
height : 30px;
float: right;
margin-top: 10px;
color : white;
text-align: center;
padding-bottom: 30px;
}
</style>

<div class="homework_wrap mb-4">
     <div class="mt-4 mb-4 table_center">			   				             						
  						<table class="table_style01 table_center hover_none">
	    					<colgroup>
	    						<col width="30%">
	    						<col width="70%">
	    					</colgroup>	    			
	    					<tr>
	    						<th>강의코드</th><td>${lecture.lctreNo}</td>
	    					</tr>
	    					<tr>
	    						<th>강의명</th><td>${lecture.course.courseNm}</td>
	    					</tr>
	    					<tr>
	    						<th>담당교수</th><td>${lecture.professor.prfsorNm}</td>
	    					</tr>
	    					<tr>
	    						<th>강의구분</th><td>${lecture.lctreSe}</td>
	    					</tr>		
	    					<tr>
	    						<th>수강인원</th><td>${lecture.lctreNmpr}</td>
	    					</tr>		
	    					<tr>
	    						<th>강의실</th><td>${lecture.facilities.fcltsNm}</td>
	    					</tr>		
	    					<tr>
	    						<th>학기</th><td>${lecture.semstrSe}</td>
	    					</tr>		
	    					<tr>
	    						<th>이수구분</th><td>${lecture.complSe}</td>
	    					</tr>		
  			   		</table>	
  			   	
  			</div> 				
  		</div>
  		
  		
  		
  		
  		
  		
  		