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

import com.mvc.dto.MemberDTO;

public class MemberDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	
	// 1. 커넥션 생성.. 생성자로 커넥션 생성해서 객체화 시 바로 실행할 수 있게끔
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 로그인
	public boolean login(String id, String pw) {
		// 2. 쿼리 실행
		String sql = "SELECT id FROM member WHERE id=? AND pw=?";
		boolean result = false;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			
			rs = ps.executeQuery();
			
			// 3. 결과값 반환
			result = rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}		
		return result;		
	}
	
	
	public boolean join(String id, String pw, String name, int age, String gender, String email) {
		
		String sql = "INSERT INTO member VALUES(?,?,?,?,?,?)";
		boolean result = false;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3, name);
			ps.setInt(4, age);
			ps.setString(5, gender);
			ps.setString(6, email);
			
			int sqlResult = ps.executeUpdate();
			
			if(sqlResult != 0) {
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		} finally {
			resClose();
		}
		
		return result;
	}

	
	
	// 4. 자원 반납
	public void resClose() {
		try {
			if(rs != null) {rs.close();}
			if(ps != null) {ps.close();}
			if(conn != null) {conn.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 멤버리스트 가져오기
	public ArrayList<MemberDTO> memberList() {
		
		// 1. connection 준비
		// 2. preparedStatement 준비
		String sql = "SELECT * FROM member";
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(); // 3. 쿼리문 실행
			
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO(); // DB 에서 가져온 데이터들을 여기에 담을 예정
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return list;
		// 5. 자원 반납
		
	}
	
	// 회원 삭제
	public boolean memberDel(String id) {
		String sql = "DELETE FROM member WHERE id=?";
		boolean result = false;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			int success = ps.executeUpdate();
			
			if(success > 0) {
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return result;
	}
	public MemberDTO detail(String id) {
		String sql = "SELECT * FROM member WHERE id=?";
		MemberDTO dto = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return dto;
	}

	public boolean update(MemberDTO dto) {
		String sql = "UPDATE member SET pw=?, name=?, age=?, gender=?, email=? WHERE id=?";
		boolean result = false;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getPw());
			ps.setString(2, dto.getName());
			ps.setInt(3, dto.getAge());
			ps.setString(4, dto.getGender());
			ps.setString(5, dto.getEmail());
			ps.setString(6, dto.getId());
			
			int success = ps.executeUpdate();
			
			if(success>0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
}
