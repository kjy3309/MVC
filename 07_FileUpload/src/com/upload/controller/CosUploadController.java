package com.upload.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.service.CosUploadService;

@WebServlet("/upload")
public class CosUploadController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		CosUploadService service = new CosUploadService();
		String uploadPath = service.upload(req);
		req.setAttribute("path", uploadPath);
		RequestDispatcher dis = req.getRequestDispatcher("result.jsp");
		dis.forward(req, resp);
	}
	
	

}










