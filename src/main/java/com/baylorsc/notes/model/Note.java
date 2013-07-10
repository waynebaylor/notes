package com.baylorsc.notes.model;

public class Note {

	private Long id;
	private Long userId;
	private String content;
	
	public Note() {
		this.id = (long)0;
		this.userId = (long)0;
		this.content = "";
	}
	
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
	
}
