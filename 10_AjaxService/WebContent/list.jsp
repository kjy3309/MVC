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
		<button style="margin-bottom: 5px;" onclick="location.href='writeForm.jsp'">글쓰기</button>
		<button style="margin-bottom: 5px;" onclick="del()">삭제</button>
		<table>
			<thead>
				<tr>
					<th>삭제</th>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>		
</body>
<script>
	// board 리스트 불러오기
	
	
	function listCall(){
		$.ajax({
			type:"post"
			,url:"list"
			,data:{}
			,dataType:"JSON"
			,success:function(data){
				if(!data.login){
					alert("로그인이 필요한 서비스입니다.");
					location.href="index.jsp";
				}else{
					drawTable(data.list);
				}
			}
			,error:function(e){
				console.log(e);
			}
		});
	};	
	
	function drawTable(list){
		console.log(list);
		/* for(var i=0; i<list.length; i++){
			$('table').append('<tr><td>'+list[i].idx+'</td><td>'+list[i].subject+'</td><td>'+list[i].user_name+'</td><td>'+list[i].reg_date+'</td><td>'+list[i].bHit+'</td></tr>');
		};		 */		
		var content = "";		
		$('tbody').empty();
		
		list.forEach(function(item,num){
			console.log(item, num);
			content = '<tr><td><input type="checkbox" value='+item.idx+'></td><td>'+list[num].idx+'</td><td>'+"<a href='detail?idx="+list[num].idx+"'>"+list[num].subject+'</a></td><td>'+list[num].user_name+'</td><td>'+list[num].reg_date+'</td><td>'+list[num].bHit+'</td></tr>';
			$('tbody').append(content);
		})
	};
	listCall();
	
	
	function del(){
		var checkArr = [];
		$("input[type='checkbox']:checked").each(function(idx,item){
			//console.log(item);			
			//console.log(idx, $(this).val());
			checkArr.push($(this).val());
		});
		console.log(checkArr);
		
		$.ajax({
			type:"get"
			,url:"delete"
			,data:{"delList":checkArr}
			,dataType:"JSON"
			,success:function(result){
				if(result.del){
					alert('삭제에 성공했습니다.');
					listCall();
				}
			}
			,error:function(e){
				console.log(e)
			}
		});		
	}
	
	
</script>
</html>