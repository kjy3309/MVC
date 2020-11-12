package com.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.service.BoardService;

@WebServlet({"/","/write","/detail","/del","/updateForm","/update"})
public class BoardContoller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		proccess(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		proccess(req,resp);
	}

	private void proccess(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//URI - Context => reqAddr
		String uri = req.getRequestURI();
		String ctx = req.getContextPath();
		String reqAddr = uri.substring(ctx.length());
		
		BoardService service = new BoardService(req, resp);
	
		switch(reqAddr) {
			case "/":
				System.out.println("글 리스트 보기 요청");
				// 서비스가 하는일이 너무 없어서 Controller 의 일을 맡길 예정
				service.list();
				break;
				
			case "/write":
				System.out.println("글 쓰기 보기 요청");
				service.write();
				break;
				
			case "/detail":
				System.out.println("글 보기 요청");
				service.detail();
				break;
				
			case "/del":
				System.out.println("글 삭제 보기 요청");
				service.del();
				break;
				
			case "/updateForm":
				System.out.println("수정 폼 이동 요청");
				service.updateForm();
				break;
				
			case "/update":
				System.out.println("수정");
				service.update();
				break;
				
				
		}
		
	}
	
	
	
	
	
	
	
	

}
