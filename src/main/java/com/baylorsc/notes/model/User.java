package com.baylorsc.notes.model;

public class User {

	private Long id;
	private String username;
	private String password;
	private boolean enabled;
	
	// constructor
	public User() {
		this.id = (long)0;
		this.username = "";
		this.password = "";
		this.enabled = false;
	}
	
	// methods
	public Long getId() {
		return this.id;		
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean getEnabled() {
		return this.enabled;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
