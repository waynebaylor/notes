package com.baylorsc.notes.manager;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.baylorsc.notes.model.User;

@Component
public class UserManager 
{
	@Autowired
	@Qualifier("dsl")
	private DSLContext context;
	
	public boolean userExists(User user) {
		int count = this.context.resultQuery("select count(*) from user where username = ?", user.getUsername()).fetch().get(0).getValue(0, Integer.class);
		return count > 0;
	}
	
	public void createUser(User user) {
		this.context.execute(
				"insert into user(username, password, enabled) values(?, ?, ?)", 
				user.getUsername(), 
				user.getPassword(), 
				"T");
		
		Long userId = this.context.lastID().longValue();
		
		this.context.execute(
				"insert into role(user_id, authority) values(?, ?)", 
				userId,
				"ROLE_USER");
	}
	
	public User findUser(String username) {
		return null;
	}
}

