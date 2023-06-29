package com.exam.portal.entity;

public class JWTRequest {
	
	private String userName;
	
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public JWTRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public JWTRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
