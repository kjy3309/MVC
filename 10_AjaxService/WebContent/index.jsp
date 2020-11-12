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
				border: 1px solid black;
				border-collapse: collapse;
				text-align: center;
				padding: 10px
			}
		</style>
	</head>
	<body>
		<h2>회원 관리 로그인</h2>
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name="userId" id="id"/></td>
			</tr>
			<tr>
				<td>PW</td>
				<td><input type="password" name="userPass" id="pw"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="login" id="login"/>
					<input type="button" value="회원가입" onclick="location.href='joinForm.jsp'"/>
				</td>
			</tr>
		</table>
	</body>	
<script>
	$("#login").click(function(){
		var id = $("#id").val();
		var pw = $("#pw").val();
		console.log(id+"/"+pw); // 1차 확인
		
		$.ajax({
			type:"post"
			,url:"login"
			,data:{
				userId:id
				,userPw:pw
			}
			,dataType:"JSON"
			,success:function(data){
				if(data.success){
					alert("로그인에 성공했습니다.");	
					location.href='list.jsp';
				}else{
					alert("아이디 또는 비밀번호를 확인하세요.");
				}
			}
			,error:function(e){
				console.log(e);
			}
		});
	})
</script>
</html>