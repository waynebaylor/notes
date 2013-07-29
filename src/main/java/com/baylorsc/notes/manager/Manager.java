package com.baylorsc.notes.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;

public abstract class Manager 
{
	public abstract SessionFactory getSessionFactory();
	
	public Session session() {
		return this.getSessionFactory().getCurrentSession();
	}
	
	public Long lastInsertId() {
		return (Long)this.session().createSQLQuery("select last_insert_id() as id")
			.addScalar("id", StandardBasicTypes.LONG)
			.uniqueResult();
	}
}
