package com.mvc.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dao.PhotoDAO;
import com.mvc.dto.PhotoDTO;

public class PhotoSerivce {
	
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	RequestDispatcher dis = null;

	public PhotoSerivce(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public void list() throws ServletException, IOException {
		PhotoDAO dao = new PhotoDAO();
		req.setAttribute("list", dao.list()); 
		dis = req.getRequestDispatcher("list.jsp");
		dis.forward(req, resp);
		
	}

	public void detail() throws ServletException, IOException {
		PhotoDAO dao = new PhotoDAO();
		String idx = req.getParameter("idx");
		req.setAttribute("bbs", dao.detail(idx));
		dis = req.getRequestDispatcher("detail.jsp");
		dis.forward(req, resp);
	}

	public void write() throws IOException {
		PhotoDAO dao = new PhotoDAO();
		
		UploadService upload = new UploadService(req);
		// 업로드서비스에서 user_name, subject, content 와
		// if.. oriFileName 존재하면 old file 과 new file을 dto에 저장시켜줌
		
		//1. 사진을 업로드 한다.		
		PhotoDTO dto = upload.regist();
		
		
		//2. 작성자,제목, 내용, 조회수 를 DB 에 넣어 준다. 3. 사진의 이름과 경로를 DB 에 저장한다.
		long pk = dao.write(dto);	
		String page = "writeForm.jsp";
		if(pk > 0) {
			page = "detail?idx="+pk;
		}
		resp.sendRedirect(page);		
	}

	// 삭제하기
	public void del() throws IOException {
				
		PhotoDAO dao = new PhotoDAO(); // 우선 db 불러오고
		UploadService upload = new UploadService(req);
		String idx = req.getParameter("idx");
		
		// 파일명 추출(photo 에서..)
		String fileName = dao.getFileName(idx);
		
		
		dao = new PhotoDAO(); // 이미 dao.getFileName(idx) 에서 connection 사용 후 반납하여 다시 생성시킴
		
		// 글 삭제(bbs 에서... -> photo)
		int success = dao.del(idx);
		
		// 파일 삭제
		if(success > 0 && fileName != null) {
			System.out.println("파일 삭제");
			upload.delete(fileName);
		}	
		
		resp.sendRedirect("./"); // 다 하고나서 리스트로 보내기 dot . 을 찍어줘야 경로지정이 제대로 가능
		
	}
	
	// updateForm 이동
	public void updateForm() throws ServletException, IOException {
		PhotoDAO dao = new PhotoDAO(); 
		
		// 가져오는 내용이 상세보기와 동일하므로 detail() 을 사용
		
		String idx = req.getParameter("idx");
		req.setAttribute("bbs", dao.detail(idx)); 
		dis = req.getRequestDispatcher("updateForm.jsp");
		dis.forward(req, resp);
	}

	
	// update
	public void update() throws IOException {
		
		// 업로드
		UploadService upload = new UploadService(req);
		PhotoDTO dto = upload.regist();
		
		// 글 내용도 수정 + 업로드 한 내용이 있으면 기존 내용을 삭제
		PhotoDAO dao = new PhotoDAO();
		dao.update(dto);
		
		if(dto.getNewFileName() != null) {	 // 새로 등록한 파일이 존재한다.. dto 에는 새로운 파일이름이 저장되어있음
			dao = new PhotoDAO();
			
			// 우선 이전 파일 이름을 가져와야 한다.
			int idx = dto.getIdx(); // 
			String prevFileName = dao.getFileName(String.valueOf(idx)); 
			
			// 이전 파일 이름 -> 새 파일 이름으로 photo table 에서 파일명 변경
			dao = new PhotoDAO();
			dao.updateFileName(prevFileName,dto.getOriFileName(),dto.getNewFileName(), dto.getIdx());
			
			
			//upload.delete(dto.getNewFileName()); // 이전 이름의 파일을 삭제
			upload.delete(prevFileName);			
		}
		resp.sendRedirect("detail?idx="+dto.getIdx());
		
	
	}

}
