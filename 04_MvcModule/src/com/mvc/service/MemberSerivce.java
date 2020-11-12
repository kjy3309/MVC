package com.mvc.service;

import java.util.ArrayList;

import com.mvc.dao.MemberDAO;
import com.mvc.dto.MemberDTO;

public class MemberSerivce {

	// 로그인 서비스
	public boolean login(String id, String pw) {
		
		// DB가 필요해? YES -> DAO 필요
		MemberDAO dao = new MemberDAO(); // 객체화 하면 connection 이 자동 생성
		boolean success = dao.login(id,pw);
		System.out.println(id+" 의 로그인 결과 : "+success);
		
		return success;
	}

	public boolean join(String id, String pw, String name, int age, String gender, String email) {
		
		MemberDAO dao = new MemberDAO();
		System.out.println(id+"/");
		boolean success = dao.join(id, pw, name, age, gender, email);
		
		return success;
	}

	// 회원 목록
	public ArrayList<MemberDTO> memberList() {
		MemberDAO dao = new MemberDAO();
		
		return dao.memberList();
		
	}

	// 회원 삭제
	public boolean memberDel(String id) {
		MemberDAO dao = new MemberDAO();		
		return dao.memberDel(id);
	}

	// 회원 상세 정보
	public MemberDTO detail(String id) {
		MemberDAO dao = new MemberDAO();	
		return dao.detail(id);
	}

	public boolean update(String id, String pw, String name, int age, String gender, String email) {
		MemberDAO dao = new MemberDAO();	
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.setAge(age);
		dto.setGender(gender);
		dto.setEmail(email);	
		
		return dao.update(dto);
	}

	

}
