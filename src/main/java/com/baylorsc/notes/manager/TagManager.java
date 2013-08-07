package com.baylorsc.notes.manager;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.baylorsc.notes.model.Tag;
import com.baylorsc.notes.model.User;

@Repository
@Transactional
public class TagManager extends Manager
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	public Long createTag(Long noteId, String name) {
		String sql = "insert into tag(note_id, name) values(:note_id, :name)"; 

		this.session().createSQLQuery(sql)
			.setParameter("note_id", noteId)
			.setParameter("name", name)
			.executeUpdate();
		
		Long tagId = this.lastInsertId();
		
		return tagId;
	}

	public List<Tag> findNoteTags(User user, Long noteId) {
		String sql = "select t.id, t.note_id as noteId, t.name from tag t where t.note_id = :note_id and t.note_id in (select n.id from note n where n.user_id = :user_id)"; 
		
		Object result = this.session().createSQLQuery(sql)
			.addScalar("id", StandardBasicTypes.LONG)
			.addScalar("noteId", StandardBasicTypes.LONG)
			.addScalar("name", StandardBasicTypes.STRING)
			.setParameter("note_id", noteId)
			.setParameter("user_id", user.getId())
			.setResultTransformer(Transformers.aliasToBean(Tag.class))
			.list();
			
		return (List<Tag>)result;
	}
	
	public void delete(User user, Long... noteIds) {
		String sql = "delete from tag t where t.note_id in (:note_ids) and t.note_id in (select n.id from note n where n.user_id = :user_id)";
		
		this.session().createSQLQuery(sql)
			.setParameter("user_id", user.getId())
			.setParameterList("noteIds", noteIds)
			.executeUpdate();
	}
}
