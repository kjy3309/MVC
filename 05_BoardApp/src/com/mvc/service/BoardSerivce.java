package com.mvc.service;

import java.util.ArrayList;

import com.mvc.dao.BoardDAO;
import com.mvc.dto.BoardDTO;

public class BoardSerivce {

	public boolean write(String user_name, String subject, String content) {
		
		BoardDAO dao = new BoardDAO();
		
		return dao.write(user_name, subject, content);
	}

	public boolean login(String id, String pw) {
		BoardDAO dao = new BoardDAO();
		
		return dao.login(id, pw);
	}
	
	public ArrayList<BoardDTO> boardList() {
		BoardDAO dao = new BoardDAO();
	      return dao.boardList();
	}

	public BoardDTO detail(String idx) {
		BoardDAO dao = new BoardDAO();
		return dao.detail(idx);
		
		
	}

	public boolean delete(String idx) {
		BoardDAO dao = new BoardDAO();
		return dao.delete(idx);
		
	}

	public boolean update(String subject, String content, String idx) {
		BoardDAO dao = new BoardDAO();
		return dao.update(subject, content, idx);
	}
}
