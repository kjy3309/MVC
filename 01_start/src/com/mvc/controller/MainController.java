package com.mvc.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// "/" 로 view 에서 요청이 왔을 경우...

@WebServlet("/")
public class MainController extends HttpServlet {

	// get 으로 요청했을 경우 이곳으로..
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GET 으로 요청");
		
		String param = req.getParameter("type");
		System.out.println(param);
		
		Object result = null; // 응답 값을 담을 변수
		
		// 파라메터 값에 따라 다른 응답값을 준다.
		if(param == null) {
			result = "안녕하세요.";
		}else if(param.equals("date")) {
			result = new Date();
		}else {
			result = "파라메터 값이 범위를 벗어납니다.";
		}
		
		// 결과값을 담아 보낸다.(result 라는 이름으로) -> jsp에서 ${result} 로 받을 수 있어짐
		// response 는 특정 페이지로 보낼 때 값을 담을 수 없기에 request 객체로 응답한다.
		req.setAttribute("result", result);
		
		
		// 담은 값을 특정 페이지로 forward 한다.
		RequestDispatcher dis = req.getRequestDispatcher("/result.jsp"); // 어느페이지로 갈지 붙여줌.
		dis.forward(req, resp); // 전송
	}

	/// post 로 요청했을 경우 이곳으로..
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("POST 로 요청");
	}
	
}
