package com.mvc.AjaxController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.AjaxService.BoardService;

@WebServlet({"/list","/detail","/delete","/update","/updateForm","/detailView"})
public class BoardController extends HttpServlet {

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
		BoardService service = new BoardService(req, resp);
		
		switch (reqAddr) {
		
		case "/list":
			service.list();
			break;

		case "/detail":
			System.out.println("상세보기 요청");
			String idx = req.getParameter("idx");
			System.out.println("idx"+idx);
			req.getSession().setAttribute("idx", idx);			
			resp.sendRedirect("detail.jsp");			
			break;
			
		case "/delete":
			System.out.println("삭제 요청");
			service.delete();
			break;
			
		case "/update":
			System.out.println("수정하기");
			service.update();
			break;		
			
		case "/updateForm":
			System.out.println("업데이트 폼 이동");
			service.updateForm();
			break;
			
		case "/detailView":
			System.out.println("상세보기 데이터 요청");
			service.detailView();
			break;

		}
	}

}
