package com.mvc.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class MainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET");
		process(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST");		
		process(req,resp);				
	}
	
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int val1 = Integer.parseInt(req.getParameter("val1")) ;
		String oper = req.getParameter("oper");
		int val2 = Integer.parseInt(req.getParameter("val2"));
		
		Object result = null; // 오브젝트로 정하는 두가지 이유..
		// 1. 뭐든지 담을 수 있음
		// 2. 값을 담아줄 때 SetAttribute 가 있는데 object 를 사용함
		
		
		switch(oper) { // 원래는 model 이 할 일이지만 너무 간단하여 컨트롤러에서 처리
		case "+":
			result = val1 + val2;
			break;
			
		case "-":
			result = val1 - val2;
			break;
			
		case "*":
			result = val1 * val2;
			break;
			
		case "/":
			result = val1 / val2;
			break;
		}
		
		// 결과값 보내기 -> result.jsp
		req.setAttribute("dap", result);
		
		RequestDispatcher dis= req.getRequestDispatcher("result.jsp");
		dis.forward(req, resp);
	}
}
