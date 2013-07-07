package com.baylorsc.notes.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegisterController 
{
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private StandardPasswordEncoder pwEncoder;
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view() {
		return "registerView";
	}
	
	@RequestMapping(value="/submit", method=RequestMethod.POST)
	public String save(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = this.pwEncoder.encode(request.getParameter("password"));
		
		logger.info("Registered user: "+username+" with password hash: "+password);
		
		return "redirect:/login/view";
	}
}
