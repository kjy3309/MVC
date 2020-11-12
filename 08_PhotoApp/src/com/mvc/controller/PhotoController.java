package com.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.service.PhotoSerivce;

@WebServlet({"/","/error","/write","/updateForm","/update","/detail","/del"})
public class PhotoController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dual(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dual(req,resp);
	}
	
	private void dual(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String ctx = req.getContextPath();
		String reqAddr = uri.substring(ctx.length());
		
		PhotoSerivce service = new PhotoSerivce(req, resp);
		
		switch(reqAddr){
		case "/": 	
			System.out.println("리스트 요청");		
			service.list();
			break;	
		
		case "/detail":
			System.out.println("상세보기");
			service.detail();
			break;
			
		case "/write": 
			System.out.println("글쓰기");
			service.write();
			break;
			
		case "/del":	
			System.out.println("삭제 하기");
			service.del();
			break;
			
		case"/error": 
			System.out.println("에러 발생!");
			break;
		
		case "/updateForm": 
			System.out.println("수정 폼으로 이동");
			service.updateForm();
			break;
			
		case "/update": 
			System.out.println("수정하기");
			service.update();
			break;
			
	}
		
	}

}
