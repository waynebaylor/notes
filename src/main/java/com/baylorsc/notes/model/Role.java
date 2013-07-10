package com.baylorsc.notes.model;

public class Role {

	private Long id;
	private Long userId;
	private String authority;
	
	// constructor
	public Role() {
		this.id = (long)0;
		this.userId = (long)0;
		this.authority = "";
	}
	
	// methods
	public Long getId() {
		return this.id;		
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
	public String getAuthority() {
		return this.authority;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}	
}
