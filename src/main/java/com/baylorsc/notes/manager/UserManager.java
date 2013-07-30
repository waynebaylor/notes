package com.baylorsc.notes.manager;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.baylorsc.notes.model.User;

@Repository
@Transactional
public class UserManager extends Manager
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public boolean userExists(User user) {
		String sql = "select count(*) as count from user where username = :username";
		
		Object result = this.session().createSQLQuery(sql)
			.addScalar("count", StandardBasicTypes.INTEGER)
			.setParameter("username", user.getUsername())
			.uniqueResult();
		
		int count = (Integer)result;
		
		return count > 0;
	}
	
	public Long createUser(User user) {
		String sql = "insert into user(username, password, enabled) values(:username, :password, :enabled)";
		this.session().createSQLQuery(sql)
			.setParameter("username", user.getUsername())
			.setParameter("password", user.getPassword())
			.setParameter("enabled", "T")
			.executeUpdate();
		
		Long userId = this.lastInsertId();
		
		sql = "insert into role(user_id, authority) values(:user_id, :authority)";
		this.session().createSQLQuery(sql)
			.setParameter("user_id", userId)
			.setParameter("authority", "ROLE_USER")
			.executeUpdate();
		
		return userId;
	}
	
	public User findUser(String username) {
		String sql = "select id, username, enabled from user where username = :username";
		Object result = this.session().createSQLQuery(sql)
			.setParameter("username", username)
			.setResultTransformer(Transformers.aliasToBean(User.class))
			.uniqueResult();
		
		return (User)result;
	}
	
	public List<User> findAllUsers() {
		String sql = "select id, username, enabled from user";
		List<?> results = this.session().createSQLQuery(sql)
			.setResultTransformer(Transformers.aliasToBean(User.class))
			.list();
		
		return (List<User>)results;
	}
	
	public List<Map<String, Object>> findAllUsersStatus() {
		String sql = "select u.id, u.username, u.enabled, r.authority from user u left join role r on u.id = r.user_id and r.authority = 'ROLE_ADMIN'";
		List<?> results = this.session().createSQLQuery(sql)
			.addScalar("id", StandardBasicTypes.LONG)
			.addScalar("username", StandardBasicTypes.STRING)
			.addScalar("enabled", StandardBasicTypes.TRUE_FALSE)
			.addScalar("authority", StandardBasicTypes.STRING)
			.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
			.list();
		
		return (List<Map<String, Object>>)results;
	}
	
	public void deleteUser(Long userId) {
		String sql = "delete from role where user_id = :user_id";
		this.session().createSQLQuery(sql)
			.setParameter("user_id", userId)
			.executeUpdate();
		
		sql = "delete from user where id = :id";
		this.session().createSQLQuery(sql)
			.setParameter("id", userId)
			.executeUpdate();
	}
}

