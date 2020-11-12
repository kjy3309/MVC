package com.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dto.BoardDTO;
import com.mvc.service.BoardSerivce;

@WebServlet({"/login","/logout","/write","/main","/index","/detail","/delete","/update"})
public class MainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String reqAddr = req.getRequestURI().substring(req.getContextPath().length());
		BoardSerivce service = new BoardSerivce();
		RequestDispatcher dis = null;
		String msg;
		String page;
		String user_name;
		String subject;
		String content;
		
		switch (reqAddr) {
		
		case "/write":
			System.out.println("write 들어왔나요?");
			req.setCharacterEncoding("UTF-8");
			user_name = req.getParameter("user_name");
			subject = req.getParameter("subject");
			content = req.getParameter("content");
			
			msg = "게시글 작성 실패!";
			
			if(service.write(user_name, subject, content)) {
				msg = "글이 작성되었습니다.";
			}
			req.setAttribute("user_name", user_name);
			dis = req.getRequestDispatcher("main");
			dis.forward(req, resp);
			
			break;
			
		case "/login":
			String id = req.getParameter("userId");
			String pw = req.getParameter("userPass");
			
			boolean result = service.login(id,pw);
			
			msg = "로그인에 실패했습니다.";
			page = "index.jsp";
			
			if(result) {
				page = "main";
				msg = "로그인 성공";
				req.getSession().setAttribute("user_name", id);
			}
			System.out.println("로그인에 들어왔나요?");
			req.setAttribute("msg", msg);
			req.setAttribute("user_name", id);
			dis = req.getRequestDispatcher(page);
			dis.forward(req, resp);
			break;
			
		case "/logout":
			req.getSession().removeAttribute("user_name");
			resp.sendRedirect("index.jsp");
			break;
			
		case "/main":
	         System.out.println("메인에 들어왔나요?");
	         //1.파라메터 확인(X)
	         ArrayList<BoardDTO>list = service.boardList();
	         req.setAttribute("list", list);
	         dis = req.getRequestDispatcher("main.jsp");
	         dis.forward(req, resp);
	         //2.서비스 사용 여부 확인(X)      
	         break;
	         
		case "/detail":
			String idx = req.getParameter("idx");
			if(idx==null ) {
				idx = (String) req.getAttribute("idx");
			}
			BoardDTO dto=  service.detail(idx);
			req.setAttribute("detail", dto);
			dis = req.getRequestDispatcher("detail.jsp");
			dis.forward(req, resp);
			
			break;
			
		case "/delete":
			System.out.println("들어왔나요?");
			idx = req.getParameter("idx");
			msg ="삭제 실패!";
			if(service.delete(idx)) {
				msg ="삭제 성공!";
			}
			req.setAttribute("msg", msg);
			
			dis = req.getRequestDispatcher("main");
			dis.forward(req, resp);
			break;
			
		case "/update":
			req.setCharacterEncoding("UTF-8");
			idx = req.getParameter("idx");
			subject = req.getParameter("subject");
			content = req.getParameter("content");
			System.out.println(subject+"/ "+content);
			msg = "수정 실패";
			
			if(service.update(subject, content, idx)) {
				msg = "수정이 완료되었습니다.";
			}
			
			req.setAttribute("msg", msg);	
			req.setAttribute("idx", idx);
			
			dis = req.getRequestDispatcher("detail");
			dis.forward(req, resp);
			break;
		}
		
		
		
	}
}
