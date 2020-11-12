package com.mvc.dto;

public class MemberDTO {
	// 여러 변수를 한번에 이동시킬 때 유용함
	// 일반적으로 DB 에서 꺼내오는 값들은 DTO 를 통해 가져온다.
	// DTO 는 Beans 또는 VO(Value Object) 라고도 불린다. (Model1 때 사용한적 있음)
	private String id;
	private String pw;
	private String name;
	private int age;
	private String gender;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String email;
}
