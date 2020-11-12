package com.board.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDAO;
import com.board.dto.BoardDTO;

public class BoardService {
	
	HttpServletRequest req = null; 
	HttpServletResponse resp = null;

	public BoardService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	//리스트 보기 메서드
	public void list() throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();//DB 가 필요 하니깐 DAO 객체화		
		ArrayList<BoardDTO> list = dao.list();
		//서비스가 하는일이 없어서 Controller 의 일부 임무 수행
		req.setAttribute("list", list);
		RequestDispatcher dis = req.getRequestDispatcher("list.jsp");
		dis.forward(req, resp);
	}

	//글쓰기
	public void write() throws IOException {
		BoardDAO dao = new BoardDAO();
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("userName");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		System.out.println(name+" / "+subject+" / "+content);
		
		String page = "writeForm.jsp";
		
		if(dao.write(name, subject, content)) {
			page = "./";
		}		
		resp.sendRedirect(page);		
	}

	//상세 보기
	public void detail() throws ServletException, IOException {
		String idx = req.getParameter("idx");		
		BoardDAO dao = new BoardDAO();
		req.setAttribute("bbs",dao.detail(idx));
		RequestDispatcher dis = req.getRequestDispatcher("detail.jsp");
		dis.forward(req, resp);		
	}

	//글 삭제
	public void del() throws ServletException, IOException {
		String idx = req.getParameter("idx");
		System.out.println("idx : "+idx);
		//DB 필요 한가?
		BoardDAO dao = new BoardDAO();		
		String page = "detail";
		String msg = "삭제에 실패 했습니다.";
		//boolean success = dao.del(idx);	
		if(dao.del(idx)) {
			page = "/";
			msg = "삭제에 성공 했습니다.";
		}
		req.setAttribute("msg", msg);
		RequestDispatcher dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);		
	}

	//수정폼
	public void updateForm() throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		String idx = req.getParameter("idx");
		System.out.println("수정 IDX : "+idx);
		BoardDTO dto = dao.detail(idx);
		req.setAttribute("bbs", dto);
		RequestDispatcher dis = req.getRequestDispatcher("updateForm.jsp");
		dis.forward(req, resp);
	}

	//수정
	public void update() throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String idx = req.getParameter("idx");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		System.out.println(idx+" / "+subject+" / "+content);
		BoardDAO dao = new BoardDAO();
		String page="detail?idx="+idx;
		String msg="수정에 실패 했습니다.";
		if(dao.update(idx,subject,content)) {
			msg="수정에 성공 했습니다.";
		}
		req.setAttribute("msg", msg);
		RequestDispatcher dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);		
	}

}











