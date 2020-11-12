<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<style></style>
</head>
<body>
	<!-- <form action="login" method="post"> -->
		<table >
			<tr>
				<th>ID</th>
				<td><input type="text" name="userId" id="userId"/></td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input type="password" name="userPw" id="userPw"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" id="login" value="login">
					<input type="button" id="join" value="join" onclick="location.href='join.jsp'">
				</td>
			</tr>
		</table>
	<!-- </form> -->
</body>
<script>
	$("#login").click(function(){
				
		$.ajax({
			type:"post", // method
			url:"login", // 
			data:{ // 파라메터
				userId: $("#userId").val(),
				userPw: $("#userPw").val()
			},
			dataType:"JSON", // 어떤 형태로 데이터를 받을까?
			success:function(d){
				alert(d.result);
			},
			error:function(e){
				console.log(e);
			}
		});
	});
</script>
</html>