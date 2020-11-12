package com.mvc.AjaxService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mvc.dao.BoardDAO;
import com.mvc.dto.BoardDTO;

public class BoardService {
	
	HttpServletRequest req = null;
	HttpServletResponse resp = null;

	public BoardService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public void list() throws IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Gson gson = new Gson();
		boolean loginChk = false;
		
		// 1. 세션체크
		if(req.getSession().getAttribute("loginId") != null) {
			loginChk = true;
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = new BoardDTO();
			ArrayList<BoardDTO> list = null;
			try { // 2. 리스트 호출
				list = dao.list();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dao.resClose();
			}
			map.put("list", list);
			
			req.setAttribute("idx", dto.getIdx());
		}
		// 3. map 에 넣기
		map.put("login", loginChk);
		
		// 4. Gson 으로 json 형태 변환
		String obj = gson.toJson(map);
		System.out.println(obj);
		
		// 5. resp 객체로 보내기
		resp.setContentType("text/html; charset=UTF-8");
		resp.getWriter().println(obj);
	}

	public void delete() throws IOException {
		// javascript 배열로 보낼 경우 파라메터 이름 뒤에 [] 표시가 들어가야 한다.
		// form 전송 방식에서는 이렇게 할 필요가 없다.
		String[] delList = req.getParameterValues("delList[]");
		System.out.println(delList.length);
		boolean success = false;
		
		BoardDAO dao = new BoardDAO();
		try {
			if(dao.delete(delList) == delList.length) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("del", success);
			Gson gson = new Gson();
			String obj = gson.toJson(map);
			System.out.println("jsp로 보내지는 success 값 : "+obj);
			resp.getWriter().println(obj);
		}
	}

	public void detail() throws IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Gson gson = new Gson();
		String[] detailList = req.getParameterValues("idx");
		
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> detail = null;
		
		try {
			detail = dao.detail(detailList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
		}		
		map.put("detail", detail);
		
		String obj = gson.toJson(map);
		System.out.println(obj);
		
		resp.setContentType("text/html; charset=UTF-8");
		resp.getWriter().println(obj);
	}

	public void detailView() throws IOException {
		BoardDAO dao = new BoardDAO();
		String idx = (String) req.getSession().getAttribute("idx");
		System.out.println("session에 저장된 idx"+idx);
		BoardDTO dto = null;
		try {
			dto = dao.detailView(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			HashMap<String, Object> map = new HashMap<String, Object>();
			if(dto != null) {
				map.put("success", true);
				map.put("info", dto);
			}else {
				map.put("success", false);
			}
			Gson gson = new Gson();
			String obj = gson.toJson(map);
			System.out.println(obj);
			
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().println(obj);
		}		
		
	}

	public void updateForm() throws IOException {
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = null;
		String idx = (String) req.getSession().getAttribute("idx");
		try {
			dto = dao.detailView(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			HashMap<String, Object> map = new HashMap<String, Object>();
			if(dto != null) {
				map.put("success", true);
				map.put("info", dto);
			}else {
				map.put("success", false);
			}
			Gson gson = new Gson();
			String obj = gson.toJson(map);
			System.out.println(obj);
			
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().println(obj);
		}		
	}

	public void update() throws IOException {
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");		
		String idx = (String) req.getSession().getAttribute("idx");
		System.out.println(subject+content);
		
		boolean result = false;
		BoardDAO dao = new BoardDAO();
		
		try {
			result = dao.update(subject, content, idx);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("success", result);
			Gson gson = new Gson();
			String obj = gson.toJson(map);
			
			System.out.println(obj);
			
			resp.getWriter().println(obj);
		}
		
		
		
	}
	

}
