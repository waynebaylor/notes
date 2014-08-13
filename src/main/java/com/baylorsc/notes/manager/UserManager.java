package com.baylorsc.notes.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.baylorsc.notes.model.User;

@Repository
@Transactional
public class UserManager extends Manager
{
    @Autowired
    private QueryManager queryManager;
    
	public boolean userExists(User user) {
		Object result = this.session().getNamedQuery("User.userExists")
			.setParameter("username", user.getUsername())
			.uniqueResult();
		
		int count = (Integer)result;
		
		return count > 0;
	}
	
	public Long createUser(User user) {
		this.session().getNamedQuery("User.insert")
			.setParameter("username", user.getUsername())
			.setParameter("password", user.getPassword())
			.setParameter("enabled", "T")
			.executeUpdate();
		
		Long userId = this.lastInsertId();
		
		this.session().getNamedQuery("Role.insert")
			.setParameter("user_id", userId)
			.setParameter("authority", "ROLE_USER")
			.executeUpdate();
		
		return userId;
	}
	
	public User findUser(String username) {
	    return this.queryManager.uniqueBean(
	            "User.findUser", 
	            Parameters.create()
	                .set("username", username), 
	            User.class);
	}
	
	public List<User> findAllUsers() {
	    return this.queryManager.beanList(
	            "User.findAllUsers", 
	            Parameters.create(), 
	            User.class);
	}
	
	public List<Map<String, Object>> findAllUsersStatus() {
	    return this.queryManager.beanList(
	            "User.findAllUsersStatus", 
	            Parameters.create());
	}
	
	public void deleteUser(Long userId) {
		this.session().getNamedQuery("Role.delete")
			.setParameter("user_id", userId)
			.executeUpdate();
		
		this.session().getNamedQuery("User.delete")
			.setParameter("id", userId)
			.executeUpdate();
	}
}

