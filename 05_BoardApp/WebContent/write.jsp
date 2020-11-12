<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<h3>글쓰기</h3>
		<form action="write" method="post">
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="user_name" value="${user_name}" readonly/></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="subject" /></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><input type="text" name="content" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="작성"/>
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