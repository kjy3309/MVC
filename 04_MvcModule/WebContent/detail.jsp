<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<style>
		table, td{
			border : 1px solid black;
			border-collapse: collapse;
		}
		td{
			padding: 5px;
			text-align:center;
		}
	</style>
	</head>
	<body>
		<h3>회원정보</h3>
		<form action="update" method="post">
			<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name="userId" value="${info.id}" readonly/></td>
				</tr>
				<tr>
					<td>PW</td>
					<td><input type="text" name="userPass" value="${info.pw}"/></td>
				</tr>
				<tr>
					<td>NAME</td>
					<td><input type="text" name="userName" value="${info.name}"/></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="text" name="age" value="${info.age}"/></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						남자: <input type="radio" name="gender" value="male" 
						<c:if test="${info.gender eq 'male'}"> checked</c:if>
						/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						여자: <input type="radio" name="gender" value="feme"
						<c:if test="${info.gender eq 'feme'}"> checked</c:if>
						/>
					</td>
				</tr>
				<tr>
					<td>EMAIL</td>
					<td><input type="email" name="email" value="${info.email}"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="회원정보 변경"/>
					</td>
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