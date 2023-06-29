package com.exam.portal.entity;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
	
	private String authority;
	
	public Authority(String auth) {
		this.authority =auth;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}

}
