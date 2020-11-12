package com.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class Calcontroller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		calc(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		calc(req,resp);
	}
	
	
		
	

	private void calc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int val1 = Integer.parseInt(req.getParameter("val1"));
		String oper = req.getParameter("oper");
		int val2 = Integer.parseInt(req.getParameter("val2"));
		
		Object result = null;
		
		switch (oper) {
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
		
		req.setAttribute("result", result);
		
		RequestDispatcher rd = req.getRequestDispatcher("/result.jsp");
		rd.forward(req, resp);
		
	}
}
