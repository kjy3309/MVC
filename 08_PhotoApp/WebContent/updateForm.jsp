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
	table{
		width: 500px;
	}	
	
	table, th,td{
		border:  1px solid black;
		border-collapse: collapse;
		padding: 5px 10px;
	}
	
	td{
		text-align: center;
	}
	
	input[type='text']{
		width: 100%;
	}
	
	textarea {
		width: 100%;
		height: 150px;
		resize: none;
	}		
	</style>
</head>
<body>
	<form action="update" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>글번호</th>
				<td>
				${bbs.idx}
				<input type="hidden" name="idx" value="${bbs.idx}"/>
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${bbs.user_name}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" value="${bbs.subject}"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content">${bbs.content}</textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<a href="/photo/${bbs.newFileName }">${bbs.oriFileName }</a>
					<input type="file" name="photo"/>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="저장"/></td>
			</tr>
		</table>
	</form>
</body>
<script></script>
</html>