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
			if(rs != null) {rs.close();}
			if(ps != null) {ps.close();}
			if(conn != null) {conn.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean login(String id, String pw) {
		String sql = "SELECT * FROM member WHERE id=? AND pw=?";
		boolean result = false;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			
			rs = ps.executeQuery();
			
			result = rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return result;
	}
	
	public ArrayList<BoardDTO> boardList() {
	      String sql = "SELECT * FROM bbs";
	      ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
	      try {
	         ps =conn.prepareStatement(sql);
	         rs = ps.executeQuery();
	         
	         while(rs.next()){
	            BoardDTO dto =new BoardDTO();
	            dto.setIdx(rs.getString("idx"));
	            dto.setUser_name(rs.getString("user_name"));
	            dto.setContent(rs.getString("content"));   
	            dto.setSubject(rs.getString("subject"));
	            dto.setBhit(rs.getInt("bhit"));
	            dto.setReg_date(rs.getString("reg_date"));
	            
	            list.add(dto);   
	         }
	         System.out.println(list.size());
	      }catch(SQLException e){
	         e.printStackTrace();
	      }finally {
	         resClose();
	      }                     
	      return list;
	}

	public boolean write(String user_name, String subject, String content) {
		String sql = "INSERT INTO bbs (idx, user_name, subject, content, bHit) VALUES(bbs_seq.NEXTVAL,?,?,?,0)";
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_name);
			ps.setString(2, subject);
			ps.setString(3, content);
			
			System.out.println(user_name+subject+content);
			int success = ps.executeUpdate();
			System.out.println(success);
			if(success>0) {
				result = true;
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return result;
	}

	public BoardDTO detail(String idx) {
	      String sql = "SELECT * FROM bbs WHERE idx=?";
	      BoardDTO dto =new BoardDTO();
	      try {
	         ps =conn.prepareStatement(sql);
	         ps.setString(1, idx);
	         
	         rs = ps.executeQuery();
	         
	         while(rs.next()){	
	        	dto.setIdx(rs.getString("idx"));
	            dto.setUser_name(rs.getString("user_name"));
	            dto.setSubject(rs.getString("subject"));
	            dto.setContent(rs.getString("content"));   	    
	         }
	      }catch(SQLException e){
	         e.printStackTrace();
	      }finally {
	         resClose();
	      }                     
	      return dto;
	}

	public boolean delete(String idx) {
		String sql = "DELETE FROM bbs WHERE idx=?";
		boolean result = false;
	      try {
	         ps =conn.prepareStatement(sql);
	         ps.setString(1, idx);
	         
	         if(ps.executeUpdate()>0) {
	        	 result = true;
	        	 conn.commit();
	         }	         	         
	      }catch(SQLException e){
	         e.printStackTrace();
	      }finally {
	         resClose();
	      }                     
	      return result;
	}

	public boolean update(String subject, String content, String idx) {
		String sql = "UPDATE bbs SET subject=?, content=? WHERE idx=?";
		boolean result = false;
		try {
			//conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, subject);
			ps.setString(2, content);
			ps.setString(3, idx);
			
			int success = ps.executeUpdate();
			System.out.println(success);
			if(success>0) {
				//conn.commit();
				result = true;				
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
}
