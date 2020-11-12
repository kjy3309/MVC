<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="calc" method="post">
		<input name="val1" type="text"/>
		<select name="oper">
			<option value="+">+</option>
			<option value="-">-</option>
			<option value="*">*</option>
			<option value="/">/</option>
		</select>
		<input name="val2" type="text"/>
		<input type="submit" value="전송"/>
	</form>
</body>
</html>