<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<table>
		<tr>
			<th>글 번호</th><td id="idx"></td>
		</tr>
		<tr>
			<th>작성자</th>	<td id="user_name"></td>
		</tr>
		<tr>
			<th>작성일</th>	<td id="reg_date"></td>
		</tr>
		<tr>
			<th>제목</th><td id="subject"></td>
		</tr>
		<tr>
			<th>내용</th><td id="content"></td>
		</tr>	
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='updateForm.jsp'"/>
			</td>
		</tr>		
	</table>		
</body>
<script>
	DetailCall();
	function DetailCall(){				
		$.ajax({
			type:"get"
			,url:'detailView'
			,data:{}
			,dataType:"JSON"
			,success:function(data){
				if(data.success){
					console.log(data);
					$("#idx").html(data.info.idx);
					$("#user_name").html(data.info.user_name);
					$("#reg_date").html(data.info.reg_date);
					$("#subject").html(data.info.subject);
					$("#content").html(data.info.content); 
				}else{
					alert('글을 불러오는데 이상이 생겼습니다.');
					location.href="list.jsp";
				}
				
			}
			,error:function(e){
				console.log(e);
			}
		});
	};	
</script>
</html>