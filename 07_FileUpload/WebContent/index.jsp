<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<style></style>
</head>
<body>
		<form action="upload2" method="post" enctype="multipart/form-data">
			<input type="text" name="title"/>
			<input type="file" name="uploadFile"/>
			<input type="submit" value="업로드"/>
		</form>
</body>
<script></script>
</html>