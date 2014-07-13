package com.baylorsc.notes.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Manager 
{
    @Autowired
    private SessionFactory sessionFactory;
    
	public SessionFactory getSessionFactory() {
	    return this.sessionFactory;
	}
	
	public Session session() {
		return this.getSessionFactory().getCurrentSession();
	}
	
	public Long lastInsertId() {
		return (Long)this.session().createSQLQuery("select last_insert_id() as id")
			.addScalar("id", StandardBasicTypes.LONG)
			.uniqueResult();
	}
}
