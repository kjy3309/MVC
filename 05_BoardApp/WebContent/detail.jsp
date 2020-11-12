<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<form action="update" method="post">
		<table>
			<tr>
				<td>글번호</td>
				<td><input type="text" name="idx" value="${detail.idx}" readonly/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="user_name" value="${detail.user_name}" readonly/></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" value="${detail.subject}"/></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" name="content" value="${detail.content}"/></td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="수정하기"/>
				</td>
				<td>
					<a href="delete?idx=${detail.idx}&">글삭제</a>
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