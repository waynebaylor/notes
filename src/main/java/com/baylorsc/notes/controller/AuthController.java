package com.baylorsc.notes.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.baylorsc.notes.manager.UserManager;
import com.baylorsc.notes.model.User;

@Component
public abstract class AuthController 
{
	@Autowired
	private UserManager userManager;
	
	@ModelAttribute("user")
	public Principal getAuthenticatedUser() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	protected User getCurrentUser() {
		return this.userManager.findUser(this.getAuthenticatedUser().getName());
	}
}
