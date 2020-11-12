package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.mvc.dto.PhotoDTO;

public class PhotoDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public PhotoDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 리스트 불러오기
	public ArrayList<PhotoDTO> list() {
		String sql = "SELECT idx, subject, user_name, reg_date, bHit FROM bbs ORDER BY idx DESC";
		ArrayList<PhotoDTO> list = new ArrayList<PhotoDTO>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PhotoDTO dto = new PhotoDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setSubject(rs.getString("subject"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setbHit(rs.getInt("bHit"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return list;
		
	}
	
	private void resClose() {
		try {
			if(rs != null) {rs.close();}
			if(ps != null) {ps.close();}
			if(conn != null) {conn.close();}
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	// 상세보기
	public PhotoDTO detail(String idx) {
		String sql = "SELECT b.idx, b.subject, b.user_name, b.content, p.oriFileName, p.newFileName FROM bbs b, photo p WHERE b.idx=p.idx(+) AND b.idx=?";
		// "SELECT b.idx, b.subject, b.user_name, b.content, p.oriFileName, p.newFileName FROM bbs b LEFT JOIN photo p ON b.idx = p.idx WHERE b.idx = 95;
		PhotoDTO dto = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, idx);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				dto = new PhotoDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setSubject(rs.getString("subject"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setContent(rs.getString("content"));
				dto.setOriFileName(rs.getString("oriFileName"));
				dto.setNewFileName(rs.getString("newFileName"));
				upHit(idx);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	private void upHit(String idx) {
		String sql="UPDATE bbs SET bHit = bHit+1 WHERE idx=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, idx);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public long write(PhotoDTO dto) {
		String sql="INSERT INTO bbs(idx, user_name, subject, content,bHit) VALUES(bbs_seq.NEXTVAL,?,?,?,0)";
		long pk = 0;		
		try {
			// new String[]{"반환 받을 컬럼 명"} , new int[]{"반환받을 컬럼 번호"}		
			//ojdbc 8 버전 이상에서만 가능
			
			ps = conn.prepareStatement(sql,new String[] {"idx"});
			ps.setString(1, dto.getUser_name());
			ps.setString(2, dto.getSubject());
			ps.setString(3, dto.getContent());
			ps.executeUpdate();
			
			// 방금 insert 한 데이터의 idx 가져오기
			rs = ps.getGeneratedKeys();

			if(rs.next()) {
				pk = rs.getLong(1);
				System.out.println("idx : "+pk);
				if(dto.getOriFileName() != null) {
					sql = "INSERT INTO photo(fileIdx,idx,oriFileName,newFileName)"
							+"VALUES(photo_seq.NEXTVAL,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setLong(1, pk);
					ps.setString(2, dto.getOriFileName());
					ps.setString(3, dto.getNewFileName());
					ps.executeUpdate();
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			resClose();
		}
		
		return pk;
	}

	public String getFileName(String idx) {
		String newFileName = null;
		String sql = "SELECT newFileName FROM photo WHERE idx=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, idx);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				newFileName = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		System.out.println(newFileName);
		return newFileName;
	}

	public int del(String idx) {
		String sql = "DELETE FROM bbs WHERE idx=?";
		int success = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, idx);
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			resClose();
		}
		
		return success;
	}

	// 글 수정
	public int update(PhotoDTO dto) {
		// 수정 쿼리
		String sql = "UPDATE bbs SET subject=?, content=? WHERE idx=?";
		int success = 0;
		// 실행
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getSubject());
			ps.setString(2, dto.getContent());
			ps.setInt(3, dto.getIdx());
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			resClose();
		}
		// 결과 반환
		return success;
	}

	public void updateFileName(String prev, String oriFileName, String newFileName, int idx) {
		// photo 테이블에 newFileName 을 새로운 파일 명으로 변경
		String sql = "";
		
		try {
			if(prev != null) { // 기존 파일이 있는 경우 (UPDATE)
				sql = "UPDATE photo set oriFileName=?, newFileName=? WHERE idx=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, oriFileName);
				ps.setString(2, newFileName);
				ps.setInt(3, idx);
			}else { // 없는 경우에는 (INSERT)
				sql = "INSERT INTO photo(fileIdx,idx,oriFileName,newFileName) VALUES(photo_seq.NEXTVAL,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, idx);
				ps.setString(2, oriFileName);
				ps.setString(3, newFileName);
			}			
			ps.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
	}
	
	
}
