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
		// create the associated tags.
		String sql = ""; // FIXME

		this.session().createSQLQuery(sql)
		.setParameter("noteId", noteId)
		.setParameter("name", name)
		.executeUpdate();
		
		Long tagId = this.lastInsertId();
		
		return tagId;
	}

	public List<Tag> findAllTags(Long noteId) {
		String sql = ""; // FIXME
		
		Object result = this.session().createSQLQuery(sql)
			.addScalar("id", StandardBasicTypes.LONG)
			.addScalar("noteId", StandardBasicTypes.LONG)
			.addScalar("name", StandardBasicTypes.STRING)
			.setResultTransformer(Transformers.aliasToBean(Tag.class))
			.list();
			
		return (List<Tag>)result;
	}
	
	public void delete(User user, Long... noteIds) {
		String sql = ""; // FIXME
		
//		this.session().createSQLQuery(sql)
//			.setParameter("user_id", user.getId())
//			.setParameterList("noteIds", noteIds)
//			.executeUpdate();
	}
}
