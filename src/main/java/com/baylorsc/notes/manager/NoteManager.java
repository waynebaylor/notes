package com.baylorsc.notes.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.baylorsc.notes.model.Note;
import com.baylorsc.notes.model.User;

@Repository
@Transactional
public class NoteManager extends Manager 
{
	@Autowired
	private TagManager tagManager;
	
	@Autowired
	private QueryManager queryManager;
	
	public Long createNote(Long userId, String content) {
		// create the note.
		String sql = "insert into note(user_id, content, created_on) values(:user_id, :content, :created_on)"; 
		
		this.session().createSQLQuery(sql)
			.setParameter("user_id", userId)
			.setParameter("content", content)
			.setParameter("created_on", new Date())
			.executeUpdate();

		Long noteId = this.lastInsertId();
		
		// create the tags parsed from the content.
		List<String> tagNames = this.parseTags(content);
		for(String name : tagNames) {
			this.tagManager.createTag(noteId, name);
		}
		
		return noteId;
	}
	
	public List<Note> findAllNotes(Long userId) {
	    List<Note> notes = this.queryManager.beanList(
	            "Note.findAllNotes", 
	            Parameters.create()
	                .set("user_id", userId), 
	            Note.class);
	    
	    return notes;
	}
	
	public void delete(User user, Long... noteIds) {
		// delete any tags associaated with the notes first.
		this.tagManager.delete(user, noteIds);
		
		// delete the notes.
		String sql = "delete from note where user_id = :user_id and id in (:noteIds)";
		
		this.session().createSQLQuery(sql)
			.setParameter("user_id", user.getId())
			.setParameterList("noteIds", noteIds)
			.executeUpdate();
	}
	
	public Note findNote(User user, Long id) {
	    Note note = this.queryManager.uniqueBean(
	            "Note.findNote",
	            Parameters.create()
	                .set("id", id)
	                .set("user_id", user.getId()),
	            Note.class);
	    
		return note;
	}
	
	public void save(User user, Note note) {
		// delete tags.
		this.tagManager.delete(user, note.getId());
		
		// save new tags.
		List<String> tagNames = this.parseTags(note.getContent());
		for(String name : tagNames) {
			this.tagManager.createTag(note.getId(), name);
		}
		
		// save note.
		String sql = "update note set content = :content where id = :id and user_id = :user_id";
		
		this.session().createSQLQuery(sql)
			.setParameter("content", note.getContent())
			.setParameter("id", note.getId())
			.setParameter("user_id", user.getId())
			.executeUpdate();
	}
	
	public List<Note> findWithAnyTag(User user, String... tags) {
	    List<Note> notes = this.queryManager.beanList(
	            "Note.findWithAnyTag",
	            Parameters.create()
	                .set("user_id", user.getId())
	                .set("tags", tags),
	                Note.class);

	    return notes;
	}
	
	public List<Note> findContainingPhrase(User user, String phrase) {
	    List<Note> notes = this.queryManager.beanList(
	            "Note.findContainingPhrase",
	            Parameters.create()
	                .set("user_id", user.getId())
	                .set("phrase", phrase),
	            Note.class);
	    
	    return notes;
	}
	
	private List<String> parseTags(String content) {
		List<String> tagNames = new ArrayList<String>();
		
		// tags start with a # followed by alpha-numeric characters: #todo #reminder #recipe. 
		// if any other characters are found then it's not interpreted as a tag.
		String[] words = content.split("[\\s]+");
		for(String word : words) {
			if(word.matches("#[A-Za-z0-9-]+")) {
				tagNames.add(word.substring(1, word.length()));
			}
		}
		
		return tagNames;
	}
}
