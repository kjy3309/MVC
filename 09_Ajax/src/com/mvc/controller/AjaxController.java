package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/login")
public class AjaxController extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("userId");
		String pw = req.getParameter("userPw");
		
		System.out.println(id+"/"+pw);
		
		// {"result":"로그인 성공"}
		// json 과 가장 비슷한 형태인 map을 생성
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", "로그인 성공");
		
		Gson json = new Gson();
		String obj = json.toJson(map);
		System.out.println(obj);
		
		// 응답 보내기
		resp.setContentType("text/html; charset=UTF-8");
		// js 에서는 서로 다른 도메인에서는 통신하지 못하게 한다.
		resp.setHeader("Access-Control-Allow-Origin", "*"); // cross-domain 허용
		PrintWriter writer = resp.getWriter();
		writer.println(obj);
		
		
	}

}
