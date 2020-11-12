package com.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.service.BoardService;

@WebServlet("/")
public class MainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pageParam = req.getParameter("page");
		int page = 1;
		
		if(pageParam != null) {
			page = Integer.parseInt(pageParam);			
		}
		
		if(page > 1) {
			req.setAttribute("number", true);
		}else {
			req.setAttribute("number", false);
		}
		
		BoardService service = new BoardService();
		req.setAttribute("list", service.list(page));
		req.setAttribute("currPage", page);
		
		RequestDispatcher dis = req.getRequestDispatcher("list.jsp");
		dis.forward(req, resp);
	}
	
}
