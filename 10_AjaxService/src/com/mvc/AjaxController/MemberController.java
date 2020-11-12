package com.mvc.AjaxController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.AjaxService.MemberService;

@WebServlet({"/login","/join","/overlay","/logout","/loginCheck"})
public class MemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		proc(req, resp);
	}	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		proc(req, resp);
	}
		
	private void proc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String reqAddr = req.getRequestURI().substring(req.getContextPath().length());
		
		MemberService service = new MemberService(req, resp);
		
		switch(reqAddr){
		case "/login":
			System.out.println("로그인 요청"); // 2차 확인
			service.login();
			break;
		case "/join":
			System.out.println("회원가입 요청");
			service.join();
			break;
		case "/overlay":
			System.out.println("중복 아이디 체크 요청"); // 2차 확인
			service.overlay();
			break;
		case "/logout":
			
			break;
		case "/loginCheck":
			
			break;

		}
	}
}
