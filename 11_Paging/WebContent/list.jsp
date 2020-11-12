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
		
		.pageArea{
			margin: 10px;
		}
		.pageArea span{
			border: 1px solid gray;
			padding: 2px 10px;
			margin: 5px;
		}

		
		
		

	</style>
</head>
<body>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:forEach items="${list}" var="bbs">
				<tr>
					<td>${bbs.idx}</td>
					<td><a href="detail?idx=${bbs.idx}">${bbs.subject}</a></td>
					<td>${bbs.user_name}</td>
					<td>${bbs.reg_date}</td>
					<td>${bbs.bHit}</td>
				</tr>
			</c:forEach>				
		</table>
		<div class="pageArea">
			<a href="./?page=${currPage-1 }" class="box" style=''pointer-events: none"><span>이전 페이지</span></a>
			<span><b>${currPage }</b></span>
			<a href="./?page=${currPage+1}"><span>다음 페이지</span></a>
		</div>	
		<button style="margin-top: 5px;" onclick="location.href='writeForm.jsp'">글쓰기</button>
</body>
<script>
	console.log(document.querySelector('a href'));
	
</script>
</html>




