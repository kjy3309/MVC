package com.mvc.AjaxService;

import java.awt.image.RescaleOp;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mvc.dao.MemberDAO;

public class MemberService {

	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	
	public MemberService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp; 
	}

	public void login() throws IOException {
		String id = req.getParameter("userId");
		String pw = req.getParameter("userPw");
		System.out.println(id+"/"+pw); // 3차 확인
		
		boolean success = false;		
		MemberDAO dao = new MemberDAO();
		
		try { // 커넥션 닫는작업을 service 에서 처리해주기 위해 DAO 에서 예외처리를 throw 로 처리해준다.
			success = dao.login(id,pw);
			if(success) {
				req.getSession().setAttribute("loginId", id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("success", success);
			Gson json = new Gson();
			String obj = json.toJson(map);
			System.out.println("result : "+obj);
			
			// resp.setContentType("text/html charset=UTF-8"); // 한글깨짐 방지
			// resp.setHeader("Access-Control-Allow-Origin", "*"); // 크로스 도메인
			
			resp.getWriter().println(obj);
		}
	}

	public void overlay() throws IOException {
		String id = req.getParameter("id");
		boolean success = false;
		System.out.println(id); // 3차 확인
		MemberDAO dao = new MemberDAO();
		
		try {
			success = dao.overlay(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("overlay", success);
			Gson gson = new Gson();
			String obj = gson.toJson(map);
			
			System.out.println(obj); 
			
			resp.getWriter().println(obj);
		}
	}

	public void join() throws IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		boolean success = false;
		
		System.out.println(id+pw+name+age+gender+email);
		
		MemberDAO dao = new MemberDAO();
		
		try {
			success = dao.join(id, pw, name, age, gender, email);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("success", success);
			Gson gson = new Gson();
			String obj = gson.toJson(map);
			
			System.out.println(obj);
			
			resp.getWriter().println(obj);
		}
	}

}
