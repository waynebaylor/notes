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
import com.baylorsc.notes.model.User;

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
		String sql = "insert into note(user_id, content) values(:user_id, :content)"; 
		
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
		String sql = "select id, user_id as userId, content from note where user_id = :user_id"; 
		
		Object result = this.session().createSQLQuery(sql)
			.addScalar("id", StandardBasicTypes.LONG)
			.addScalar("userId", StandardBasicTypes.LONG)
			.addScalar("content", StandardBasicTypes.STRING)
			.setParameter("user_id", userId)
			.setResultTransformer(Transformers.aliasToBean(Note.class))
			.list();
		
		return (List<Note>)result;
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
		String sql = "select id, user_id as userId, content from note where id = :id and user_id = :user_id"; 
		
		Object result = this.session().createSQLQuery(sql)
			.addScalar("id", StandardBasicTypes.LONG)
			.addScalar("userId", StandardBasicTypes.LONG)
			.addScalar("content", StandardBasicTypes.STRING)
			.setParameter("id", id)
			.setParameter("user_id", user.getId())
			.setResultTransformer(Transformers.aliasToBean(Note.class))
			.uniqueResult();
		
		return (Note)result;
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
		String sql = "select distinct n.id, n.user_id as userId, n.content from note n inner join tag t on n.id = t.note_id where n.user_id = :user_id and t.name in (:tags)";
		
		Object result = this.session().createSQLQuery(sql)
			.addScalar("id", StandardBasicTypes.LONG)
			.addScalar("userId", StandardBasicTypes.LONG)
			.addScalar("content", StandardBasicTypes.STRING)
			.setParameter("user_id", user.getId())
			.setParameterList("tags", tags)
			.setResultTransformer(Transformers.aliasToBean(Note.class))
			.list();
		
		return (List<Note>)result;
	}
	
	public List<Note> findContainingPhrase(User user, String phrase) {
		String sql = "select distinct n.id, n.user_id as userId, n.content from note n where n.user_id = :user_id and n.content like concat('%', :phrase, '%')";
		
		Object result = this.session().createSQLQuery(sql)
			.addScalar("id", StandardBasicTypes.LONG)
			.addScalar("userId", StandardBasicTypes.LONG)
			.addScalar("content", StandardBasicTypes.STRING)
			.setParameter("user_id", user.getId())
			.setParameter("phrase", phrase)
			.setResultTransformer(Transformers.aliasToBean(Note.class))
			.list();
		
		return (List<Note>)result;
	}
	
	private List<String> parseTags(String content) {
		List<String> tagNames = new ArrayList<String>();
		
		// tags start with a # followed by alpha-numeric characters: #todo #reminder #recipe. 
		// if any other characters are found then it's not interpreted as a tag.
		String[] words = content.split("[\\s]+");
		for(String word : words) {
			if(word.matches("#[A-Za-z0-9]+")) {
				tagNames.add(word.substring(1, word.length()));
			}
		}
		
		return tagNames;
	}
}
