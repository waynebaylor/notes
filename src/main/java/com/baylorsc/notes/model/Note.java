package com.baylorsc.notes.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Note {

	private Long id;
	private Long userId;
	
	@NotNull(message="Content is required.")
	@Size(min=1, message="Content is required.")
	private String content;
	
	private Date createdOn;
	
	// methods
	public Long getId() {
		return this.id;		
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}	
}
