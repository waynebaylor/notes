package com.baylorsc.notes.controller;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baylorsc.notes.manager.UserManager;

@Controller
@RequestMapping("/admin")
public class AdminController extends AuthController
{
	@Autowired
	private UserManager userManager;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public ModelAndView view() {
		ModelAndView m = new ModelAndView("adminView");
		
		m.addObject("users", this.userManager.findAllUsersStatus());
		
		return m;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/delete-user", method=RequestMethod.POST)
	public ModelAndView deleteUser(@Param Long[] userIds) {
		ModelAndView m = new ModelAndView("redirect:/admin/view");
		
		for(Long userId : userIds) {
			this.userManager.deleteUser(userId);
		}
		
		return m;
	}
}
