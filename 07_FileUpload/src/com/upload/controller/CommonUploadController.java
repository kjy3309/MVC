package com.upload.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.service.CommonUploadService;

@WebServlet("/upload2")
public class CommonUploadController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		CommonUploadService service = new CommonUploadService();
		String path = service.upload(req);		
		System.out.println("path : "+path);
		req.setAttribute("path", path);
		RequestDispatcher dis = req.getRequestDispatcher("result.jsp");
		dis.forward(req, resp);
	}
	
	

}




