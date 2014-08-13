package com.baylorsc.notes.manager;

import java.util.List;

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
    private QueryManager queryManager;
    
	public Long createTag(Long noteId, String name) {
		this.session().getNamedQuery("Tag.insert")
			.setParameter("note_id", noteId)
			.setParameter("name", name)
			.executeUpdate();
		
		Long tagId = this.lastInsertId();
		
		return tagId;
	}

	public List<Tag> findNoteTags(User user, Long noteId) {
		return this.queryManager.beanList(
		        "Tag.findNoteTags", 
		        Parameters.create()
		            .set("note_id", noteId)
		            .set("user_id", user.getId()), 
		        Tag.class);
	}
	
	public void delete(User user, Long... noteIds) {
		this.session().getNamedQuery("Tag.delete")
			.setParameter("user_id", user.getId())
			.setParameterList("note_ids", noteIds)
			.executeUpdate();
	}
}
