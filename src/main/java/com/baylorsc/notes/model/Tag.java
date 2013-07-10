package com.baylorsc.notes.model;

public class Tag {

	private Long id;
	private Long noteId;
	private String name;
	
	// constructors
	public Tag() {
		this.id = (long)0;
		this.noteId = (long)0;
		this.name = "";
	}
	
	// methods
	// methods
	public Long getId() {
		return this.id;		
	}
	
	public Long getNoteId() {
		return this.noteId;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}
	
	public void setName(String name) {
		this.name = name;
	}	
}
