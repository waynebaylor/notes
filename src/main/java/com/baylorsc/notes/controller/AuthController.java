package com.baylorsc.notes.controller;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class AuthController 
{
	@ModelAttribute("user")
	public Principal getAuthenticatedUser() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
