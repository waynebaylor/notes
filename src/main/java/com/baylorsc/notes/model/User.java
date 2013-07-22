package com.baylorsc.notes.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

	private Long id;
	
	@NotNull(message="Username is requred.")
	@Size(min=1, message="Username is required.")
	private String username;
	
	@NotNull(message="Password is required.")
	@Size(min=1, message="Password is required.")
	private String password;
	
	private boolean enabled;
	
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
