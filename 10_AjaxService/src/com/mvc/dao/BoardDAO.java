package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mvc.dto.BoardDTO;

public class BoardDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public BoardDAO() {		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void resClose() {
		try {
			if(rs!=null) {rs.close();}
			if(ps!=null) {ps.close();}
			if(conn!=null) {conn.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<BoardDTO> list() throws SQLException {

		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		String sql = "SELECT * FROM bbs";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			BoardDTO dto = new BoardDTO();
			dto.setIdx(rs.getInt("idx"));
			dto.setUser_name(rs.getString("user_name"));
			dto.setSubject(rs.getString("subject"));
			dto.setContent(rs.getString("content"));
			dto.setReg_date(rs.getDate("reg_date"));
			dto.setbHit(rs.getInt("bhit"));
			
			list.add(dto);
		}
		
		return list;
	}

	public int delete(String[] delList) throws SQLException {
		String sql = "DELETE FROM bbs WHERE idx=?";
		int delCount = 0;
		
		for(String idx : delList) {
			System.out.println("삭제할 글 번호 : "+idx);
			ps = conn.prepareStatement(sql);
			ps.setString(1, idx);
			delCount += ps.executeUpdate();
		}
		
		System.out.println("삭제한 갯수 : "+delCount);		
		return delCount;
	}

	public ArrayList<BoardDTO> detail(String[] detailList) throws SQLException {
		
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		String sql = "SELECT * FROM bbs WHERE idx=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, detailList[0]);
		rs = ps.executeQuery();
		
		if(rs.next()) {
			BoardDTO dto = new BoardDTO();
			dto.setIdx(rs.getInt("idx"));
			dto.setUser_name(rs.getString("user_name"));
			dto.setSubject(rs.getString("subject"));
			dto.setContent(rs.getString("content"));
			dto.setReg_date(rs.getDate("reg_date"));
			dto.setbHit(rs.getInt("bhit"));
			
			list.add(dto);
		}
		
		return list;
	}

	public BoardDTO detailView(String idx) throws SQLException {
		
		BoardDTO dto = null;
		String sql = "SELECT * FROM bbs WHERE idx=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, idx);
		rs = ps.executeQuery();
		
		if(rs.next()) {
			dto = new BoardDTO();
			dto.setIdx(rs.getInt("idx"));
			dto.setUser_name(rs.getString("user_name"));
			dto.setSubject(rs.getString("subject"));
			dto.setContent(rs.getString("content"));
			dto.setReg_date(rs.getDate("reg_date"));
			dto.setbHit(rs.getInt("bhit"));			
		}
		System.out.println(dto);
		
		return dto;		
	}

	public boolean update(String subject, String content, String idx) throws SQLException {
		String sql = "UPDATE bbs SET subject=?, content=? WHERE idx=?";
		boolean result = false;
		ps = conn.prepareStatement(sql);
		ps.setString(1, subject);
		ps.setString(2, content);
		ps.setString(3, idx);
		if(ps.executeUpdate() > 0) {
			result = true;
		}
		
		return result;
		
	}
}
