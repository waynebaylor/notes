package com.baylorsc.notes.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.baylorsc.notes.model.Note;

@Repository
@Transactional
public class NoteManager extends Manager 
{
	@Autowired
	private TagManager tagManager;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	public Long createNote(Long userId, String content) {
		// create the note.
		String sql = "insert into note(user_id, content) values(:user_id, :content)"; // FIXME 
		
		this.session().createSQLQuery(sql)
			.setParameter("user_id", userId)
			.setParameter("content", content)
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
		String sql = "select id, user_id as userId, content from note where user_id = :user_id"; // FIXME
		
		Object result = this.session().createSQLQuery(sql)
			.addScalar("id", StandardBasicTypes.LONG)
			.addScalar("userId", StandardBasicTypes.LONG)
			.addScalar("content", StandardBasicTypes.STRING)
			.setParameter("user_id", userId)
			.setResultTransformer(Transformers.aliasToBean(Note.class))
			.list();
		
		return (List<Note>)result;
	}
	
	private List<String> parseTags(String content) {
		List<String> tagNames = new ArrayList<String>();
		
		// FIXME
		
		return tagNames;
	}
}
