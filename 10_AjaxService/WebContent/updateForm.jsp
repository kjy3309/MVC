<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<form action="update" method="post">
		<table>
			<tr>
				<th>글번호</th>
				<td id="idx"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td id="user_name"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" id="subject" value=""/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" id="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="수정하기" id="update" /></td>
			</tr>
		</table>
	</form>
</body>
<script>
	UpdateCall();
	
	$("#update").click(function(){
		var param = {};
		param.subject = $("#subject").val();
		param.content = $("#content").val();
		
		
		console.log("param : ",param);
		
		$.ajax({
			type:"post"
			,url:"update"
			,data:param
			,dataType:"JSON"
			,success:function(data){
				if(data.success){
					alert('수정 성공!');
					location.href="detail";
				}else{
					alert('수정 실패!.');
				}
			}
			,error:function(error){
				console.log(error);
			}
		});

	})
	
	function UpdateCall(){				
	$.ajax({
		type:"get"
		,url:'updateForm'
		,data:{}
		,dataType:"JSON"
		,success:function(data){
			if(data.success){
				console.log(data);
				$("#idx").html(data.info.idx);
				$("#user_name").html(data.info.user_name);
				$("#reg_date").html(data.info.reg_date);
				$("#subject").val(data.info.subject);
				$("#content").html(data.info.content); 
			}else{
				alert('글을 불러오는데 이상이 생겼습니다.');
				location.href="list.jsp";
			}
			
		}
		,error:function(e){
			console.log(e);
		}
	});
	
	
};	
</script>
</html>