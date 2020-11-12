package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.board.dto.BoardDTO;

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

	//리스트 보기
	public ArrayList<BoardDTO> list() {
		String sql = "SELECT idx, user_name, subject, reg_date, bHit FROM bbs ORDER BY idx DESC";		
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();//각 컬럼을 하나하나 변수에 담을수 없기에 DTO 생성
				dto.setIdx(rs.getInt("idx"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setSubject(rs.getString("subject"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setbHit(rs.getInt("bHit"));
				list.add(dto); // 생성된 DTO 가 1개 이상으로 예상될 경우 ArrayList 에 담아 준다.
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}	
		 return list;
	}
	
	private void resClose(){
		try {
			if(rs != null) {rs.close();}
			if(ps != null) {ps.close();}
			if(conn != null) {conn.close();}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public boolean write(String name, String subject, String content) {
		String sql="INSERT INTO bbs(idx,user_name,subject,content,bHit) "
				+"VALUES(bbs_seq.NEXTVAL,?,?,?,0)";
		boolean success = false;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, subject);
			ps.setString(3, content);
			if(ps.executeUpdate()>0) {
				success = true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;		
	}

	//상세보기
	public BoardDTO detail(String idx) {
		String sql="SELECT idx,user_name, subject, content, reg_date, bHit FROM bbs WHERE idx = ?";
		BoardDTO dto = null;
		try {
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
				dto.setbHit(rs.getInt("bHit"));
				upHit(dto.getIdx());//데이터를 가져오는데 성공하면 조회수를 올려 준다.
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			resClose();
		}
		return dto;
	}

	//조회수 올리기
	private void upHit(int idx) {
		String sql="UPDATE bbs SET bHit = bHit+1 WHERE idx=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idx);
			int suceess = ps.executeUpdate();
			System.out.println("조회수 올리기 성공 : "+suceess);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean del(String idx) {		
		String sql="DELETE FROM bbs WHERE idx=?";
		boolean result = false;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, idx);
			if(ps.executeUpdate()>0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return result;
	}

	public boolean update(String idx, String subject, String content) {
		boolean result = false;
		String sql="UPDATE bbs SET subject=?, content=? WHERE idx=?";		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, subject);
			ps.setString(2, content);
			ps.setString(3, idx);			
			if(ps.executeUpdate()>0) {
				result = true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return result;
	}
	
	

}











