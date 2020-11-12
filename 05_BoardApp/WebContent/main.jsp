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
		table, td, th {
			border: 1px solid black;
			border-collapse: collapse;
			padding: 5px 10px;
		}
		
		table {
			margin-top: 20px;
		}
	</style>
</head>
<body>

	<table>
		<tr>
			<th>IDX</th>
			<th>USER_NAME</th>
			<th>SUBJECT</th>
			<th>BHIT</th>
			<th>등록날짜</th>
		</tr>
	   <c:forEach items="${list}" var="board">
	      <tr>
	         <td>${board.idx}</td>
	         <td>${board.user_name}</td>
	         <td><a href="detail?idx=${board.idx}">${board.subject}</a></td>
	         <td>${board.bhit}</td>
	         <td>${board.reg_date}</td>
	      </tr>
	    </c:forEach>
			<tr>
				<a href="write.jsp?user_name=${user_name}">글쓰기</a>&nbsp;&nbsp;
				<a href="logout">로그아웃</a>
			</tr>
		
	</table>
</body>
<script>
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	}
</script>
</html>