package com.baylorsc.notes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController 
{
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public ModelAndView view(Model model) {
		// copy any flash attributes that were set.
		ModelAndView m = new ModelAndView();
		m.addAllObjects(model.asMap());
		m.setViewName("loginView");

		return m;
	}
	
	@RequestMapping(value="/failed", method=RequestMethod.GET)
	public ModelAndView failed() {
		ModelAndView m = new ModelAndView("loginView");
		m.addObject("errorMessage", "The username or password entered is invalid.");
		
		return m;
	}
}
