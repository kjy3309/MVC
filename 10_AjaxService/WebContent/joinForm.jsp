<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<style>
			table, th{
				border: 1px solid black;
				border-collapse: collapse;
				padding: 12px;
			}
	</style>
</head>
	<body>
		<table>
			<td colspan="2" style="text-align: center">
			<h3>회원가입</h3><td>
			<tr>
				<th>아이디<th>
				<input type="text" name="id">
				<input type="button" value="ID 중복 체크" id="overlay">
			</tr>
			<tr>
				<th>비밀번호<th>
				<input type="password" name="pw">
			</tr>
			<tr>
				<th>이름<th>
				<input type="text" name="name">
			</tr>
			<tr>
				<th>나이<th>
				<input type="text" name="age">
			</tr>
			<tr>
				<th>성별<th>
				남자 :<input type="radio" name="gender" value="male"> 여자 :<input type="radio" name="gender" value="fema">
			</tr>
			<tr>
				<th>이메일<th>
				<input type="text" name="email">
			</tr>
			<tr>
				<th colspan="2" style="text-align: center"><input type="button" value="회원가입" id="join"></th>
			</tr>
		</table>
	</body>
<script>

var overChk = false;

	$("#overlay").click(function(){
		var id = $("input[name='id']").val();
		console.log(id);
		
		$.ajax({
			type:"get"
			,url:"overlay"
			,data:{"id":id}
			,dataType:"JSON"
			,success:function(data){
				console.log(data);
				if(data.overlay){
					alert("이미 사용중인 아이디입니다.");
					$("input[name='id']").val("");
				}else{
					alert("사용 가능한 아이디입니다.");
					overChk = true;
				}
			}
			,error:function(e){
				console.log(e);
			}
		});
	});
	
	$("#join").click(function(){
		if(overChk){
			var $id = $("input[name='id']");
			var $pw = $("input[name='pw']");
			var $name = $("input[name='name']");
			var $age = $("input[name='age']");
			var $gender = $("input[name='gender']:checked");
			var $email = $("input[name='email']");
			
			console.log($gender.val() == null);
			
			if($id.val() == ""){
				alert("아이디를 확인해 주세요.");
				$id.focus();
			}else if($pw.val() == ""){
				alert("비밀번호를 확인해 주세요.");
				$pw.focus();
			}else if($name.val() == ""){
				alert("이름을 확인해 주세요.");
				$name.focus();
			}else if($age.val() == ""){
				alert("나이를 확인해 주세요.");
				$age.focus();
			}else if($gender.val() == null){
				alert("성별을 선택해 주세요.");
			}else if($email.val() == ""){
				alert("이메일을 확인해 주세요.");
				$email.focus();
			}else{ // 모든 항목을 입력했을 경우
				console.log("아작스를 사용하여 서버로 전송하자");
				var param = {};
				param.id = $id.val();
				param.pw = $pw.val();
				param.name = $name.val();
				param.age = $age.val();
				param.gender = $gender.val();
				param.email = $email.val();
				console.log("param : ",param);
				
				$.ajax({
					type:"post"
					,url:"join"
					,data:param
					,dataType:"JSON"
					,success:function(data){
						if(data.success){
							alert('회원가입 성공!');
							location.href="index.jsp";
						}else{
							alert('회원가입 실패!.');
						}
					}
					,error:function(error){
						console.log(error);
					}
				});
			}
				
		}else{
			alert("중복 체크를 확인 해 주세요.");
			$("input[name='id']").focus();
		}
	})
		
	
</script>
</html>