<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<style>
			table, th{
				border: 1px solid black;
				border-collapse: collapse;
				padding: 12px;
			}
	</style>
</head>
	<body>
		<form action="join" method="post">
			<table>
				<td colspan="2" style="text-align: center">
				<h3>회원가입</h3><td>
				<tr>
					<th>아이디<th>
					<input type="text" name="id">
				</tr>
				<tr>
					<th>비밀번호<th>
					<input type="password" name="pw">
				</tr>
				<tr>
					<th>이름<th>
					<input type="text" name="name">
				</tr>
				<tr>
					<th>나이<th>
					<input type="text" name="age">
				</tr>
				<tr>
					<th>성별<th>
					<input type="radio" name="gender" value="male">남 <input type="radio" name="gender" value="feme">여
				</tr>
				<tr>
					<th>이메일<th>
					<input type="text" name="email">
				</tr>
				<tr>
					<th colspan="2" style="text-align: center"><input type="submit" value="전송"></th>
				</tr>
			</table>
		</form>
	</body>
<script>
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	}
</script>
</html>