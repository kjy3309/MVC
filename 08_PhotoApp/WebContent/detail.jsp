<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<style>
		table, th, td{
			border: 1px solid black;
			border-collapse: collapse;
			padding: 5px 10px;
		}
	</style>
</head>
<body>
	<table>
		<tr>
			<th>글 번호</th><td>${bbs.idx}</td>
		</tr>
		<tr>
			<th>작성자</th>	<td>${bbs.user_name}</td>
		</tr>
		<tr>
			<th>제목</th><td>${bbs.subject}</td>
		</tr>
		<tr>
			<th>내용</th><td>${bbs.content}</td>
		</tr>	
		<tr>
			<th>첨부 사진</th>
			<td>
				<a href="/photo/${bbs.newFileName }"  target="_blank">${bbs.oriFileName }</a>
			</td>
		</tr>	
		<tr>
			<td colspan="2">
				<input type="button" value="삭제" onclick="location.href='del?idx=${bbs.idx}'"/>
				<input type="button" value="수정" onclick="location.href='updateForm?idx=${bbs.idx}'"/>
				<input type="button" value="목록" onclick="location.href='./'"/>
			</td>
		</tr>		
	</table>		
</body>
<script>
var msg="${msg}";
if(msg != ""){
	alert(msg);
}
</script>
</html>