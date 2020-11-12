package com.mvc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mvc.dto.BoardDTO;

public class BoardService {

	public ArrayList<BoardDTO> list(int page) {
		
		int pagePerCnt = 5; // 페이지당 보여줄 게시물의 수
		int end = page*pagePerCnt;
		int start = (end-pagePerCnt)+1;
		
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
	
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
			String sql = "SELECT rnum, idx, user_name, subject, bHit, reg_date " + 
					"FROM(SELECT ROW_NUMBER() OVER(ORDER BY idx DESC) AS rnum" + 
					", idx, user_name, subject, bHit, reg_date FROM bbs) WHERE rnum BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
						
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setSubject(rs.getString("subject"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setbHit(rs.getInt("bHit"));
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null){rs.close();}
				if(ps != null){ps.close();}
				if(conn != null){conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		
		return list;
		
	}

}
