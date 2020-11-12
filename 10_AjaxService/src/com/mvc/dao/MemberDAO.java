package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public MemberDAO() {
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

	public boolean login(String id, String pw) throws SQLException {
		boolean success = false;
		String sql = "SELECT id FROM member WHERE id=? AND pw=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, pw);
		rs = ps.executeQuery();
		success = rs.next();
		System.out.println("success : "+success ); // 4차 확인
		
		return success;
	}

	public boolean overlay(String id) throws SQLException {
		boolean success = false;
		String sql = "SELECT id FROM member WHERE id=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		rs = ps.executeQuery();
		success = rs.next();
		System.out.println("success : "+success ); // 4차 확인
		return success;
	}

	public boolean join(String id, String pw, String name, String age, String gender, String email) throws SQLException {
		boolean success = false;
		String sql = "INSERT INTO member VALUES (?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, id);
		ps.setString(2, pw);
		ps.setString(3, name);
		ps.setInt(4, Integer.parseInt(age));
		ps.setString(5, gender);
		ps.setString(6, email);
		
		if(ps.executeUpdate() > 0) {
			success = true;
		}		
		
		return success;
	}

}
