package com.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// "/" 라는 요청은 최상위 root 이기 때문에 모든 요청을 다 받아준다.
// 그래서 특정 요청만 받는 것으로 제한하고 싶으면 "/" 빼줘야 한다.
// 하지만 "/" 가 빠지면 최초 요청 시 404 가 발생한다.
@WebServlet(urlPatterns = {"/main","/index"}) // "/" , "/main" , "/index" 로 와도 MainController 로 연결해준다.
public class MainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET 으로 요청!!!");
		
		// 우리가 원하는 URL 만 뽑아내기
		String uri = req.getRequestURI();
		System.out.println(uri); // /02_multiUrl/
		
		String context = req.getContextPath();
		System.out.println(context); // /02_multiUrl
		
		String reqAddr = uri.substring(context.length());
		System.out.println(reqAddr);
		
		// "/" -> index.jsp , "/main" -> main.jsp , "/index" -> index.jsp
		switch (reqAddr) {
		case "/":
			System.out.println("index.jsp 로 보낸다");
			break;

		case "/main":
			System.out.println("main.jsp 로 보낸다");
			break;
		
		case "/index":
			System.out.println("index.jsp 로 보낸다");
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST 로 요청!!!");
	}
	
	
}
