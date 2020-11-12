<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		table {
			margin-top: 20px;
		}
	</style>
</head>
<body>
	<button id="popup1">1번 팝업</button>
	<button id="popup2">2번 팝업</button>
	<button id="popup3">3번 팝업</button>
	<div id="listArea"></div>
	<div id="noticeArea"></div>
</body>
<script>
	// ajax 로 include
	$.ajax({
		type:"get",
		url:"./include/list.html",
		dataType:"html",
		success:function(data){
			document.getElementById("listArea").innerHTML=data;
		},
		error:function(e){
			console.log(e);
		}
	});
	
	// 특정한 부분만 들고오기
	$("button").click(function(e){
		console.log(e);
		//$("표현할 위치").load(불러올페이지 셀렉터?, callback);
		$("#noticeArea").load("./include/notice.html #"+e.target.id, function(res,stat){
			console.log(res); // 전체 소스
			console.log(stat); // 성공, 실패 여부
		});
	});
	
	
</script>
</html>